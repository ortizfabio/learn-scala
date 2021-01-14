package com.bytetrend.sandbox.java.challenge;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileConverterDblVrfy {

  private static final String XML = "xml";
  private static final String CSV = "csv";
  private static final String CSV_HEADER = "classroom id, classroom_name, teacher_1_id, teacher_1_last_name," +
    " teacher_2_id, teacher_2_last_name, teacher_2_first_name, student_id, student_last_name," +
    " student_first_name, student_grade";
  private static final String XML_HEADER = "<?xml version=\"1.0\"?>";
  private static final String CM = ", ";


  public static void main(String args[]) throws Exception {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    try {
      Scanner reader = new Scanner(System.in);
      String firstLine = reader.nextLine();
      ReaderType readerType = firstLine.indexOf("xml") > 1 ? ReaderType.XML : ReaderType.CSV;
      ReaderHandler sourceHandler = ReaderType.toFileType(readerType.extension).getReader(reader);
      //Since there is no parameter to indicate the output type right now we hard code this.
      WriterType writerType = (CSV.equalsIgnoreCase(readerType.extension) ?
        WriterType.toFileType(XML) : WriterType.toFileType(CSV));
      PrintWriter writer = new PrintWriter(System.out);
      sourceHandler.process(writerType.getWriter(writer));
    } catch (Exception e) {
      e.printStackTrace(System.err);
      System.err.println("Error processing input reason: " + e);
      System.exit(-1);
    }
    System.exit(0);
  }

  public static final boolean isEmpty(String str) {
    if (str == null || str.trim().length() == 0)
      return true;
    return false;
  }

  /**
    * A factory of readers that know how to read data
    * from the given input system type.
    */
  enum ReaderType {
    XML(XmlReader.class, "xml"),
    CSV(CsvReader.class, "csv");

    private Class handler;
    private String extension;

    ReaderType(Class clazz, String ext) {
      handler = clazz;
      extension = ext;
    }

    public ReaderHandler getReader(Scanner reader) throws Exception {
      Constructor c = handler.getConstructor(Scanner.class);
      return (ReaderHandler) c.newInstance(reader);
    }

    public static ReaderType toFileType(String extension) {
      return ReaderType.valueOf(extension.toUpperCase());
    }
  }

  /**
    * This is a factory of writers. Base on the type of data
    * instantiates a writer that knows how to write data to
    * that type.
    */
  enum WriterType {
    XML(XmlWriter.class, "xml"),
    CSV(CsvWriter.class, "csv");

    private Class handler;
    private String extension;

    WriterType(Class clazz, String ext) {
      handler = clazz;
      extension = ext;
    }

    public String getExtension() {
      return extension;
    }

    public WriterHandler getWriter(PrintWriter writer) throws Exception {

      Constructor c = handler.getConstructor(PrintWriter.class);
      return (WriterHandler) c.newInstance(writer);
    }

    public static WriterType toFileType(String extension) {
      return WriterType.valueOf(extension.toUpperCase());
    }
  }

  /**
    * Reads from an specific source type and converts the input
    * to a canonical model.
    */
  public interface ReaderHandler {

    void process(WriterHandler writerHandler) throws Exception;
  }

  static public class CsvReader implements ReaderHandler {
    private final Scanner sourceReader;
    private static final int TOKEN_COUNT = 12;
    private final int CLASSROOM_ID = 0;
    private final int CLASSROOM_NAME = 1;
    private final int TEACHER_1_ID = 2;
    private final int TEACHER_1_LNAME = 3;
    private final int TEACHER_1_FNAME = 4;
    private final int TEACHER_2_ID = 5;
    private final int TEACHER_2_LNAME = 6;
    private final int TEACHER_2_FNAME = 7;
    private final int STUDENT_ID = 8;
    private final int STUDENT_LNAME = 9;
    private final int STUDENT_FNAME = 10;
    private final int STUDENT_GRADE = 11;

    public CsvReader(Scanner reader) {
      sourceReader = reader;
    }

    @Override
    public void process(WriterHandler writerHandler) throws Exception {
      try {
        School school = new School();
        String line;
        while (!isEmpty(line = sourceReader.nextLine())) {
          String[] tokens = line.split(",");
          if (tokens.length == TOKEN_COUNT) {
            Classroom classroom = getClassroom(school, tokens);
            teachers(classroom, tokens);
            student(classroom, tokens);
          }
        }
        writerHandler.process(school);
      } catch (Exception e) {
        e.printStackTrace();
        throw new Exception(e);
      }
    }

    private void student(Classroom classroom, String[] tokens) {
      if (!isEmpty(tokens[STUDENT_ID]) && !isEmpty(tokens[STUDENT_FNAME])
        && !isEmpty(tokens[STUDENT_LNAME])) {
        Person student = new Person(tokens[STUDENT_ID],
          tokens[STUDENT_FNAME], tokens[STUDENT_LNAME]);
        classroom.addStudent(student);
      }
    }

    private void teachers(Classroom classroom, String[] tokens) {
      Person teacher;
      if (!isEmpty(tokens[TEACHER_1_ID]) && !isEmpty(tokens[TEACHER_1_FNAME]) && !isEmpty(tokens[TEACHER_1_LNAME])) {

        teacher = new Person(tokens[TEACHER_1_ID],
          tokens[TEACHER_1_FNAME], tokens[TEACHER_1_LNAME]);
        classroom.addTeacher(teacher);
      }
      if (!isEmpty(tokens[TEACHER_2_ID]) && !isEmpty(tokens[TEACHER_2_FNAME]) && !isEmpty(tokens[TEACHER_2_LNAME])) {
        teacher = new Person(tokens[TEACHER_2_ID],
          tokens[TEACHER_2_FNAME], tokens[TEACHER_2_LNAME]);
        classroom.addTeacher(teacher);
      }
    }

    private Classroom getClassroom(School school, String[] tokens) {
      String id = tokens[CLASSROOM_ID];
      Classroom classroom = school.getClassroom(id);
      if (classroom == null) {
        classroom = new Classroom(id, tokens[CLASSROOM_NAME], tokens[STUDENT_GRADE]);
        school.addClassroom(classroom);
      }
      return classroom;
    }
  }

  static public class XmlReader implements ReaderHandler {
    private final Scanner sourceReader;
    Pattern tagPattern = Pattern.compile("<(\\S+?) (.*?)>");
    Pattern singleAttr = Pattern.compile("(\\w+)=\"(.*?)\"");
    Pattern doubleAttr = Pattern.compile("(\\w+)=\"(.*?)\" +?(\\w+)=\"(.*?)\"");
    Pattern tripleAttr = Pattern.compile("(\\w+)=\"(.*?)\" +?(\\w+)=\"(.*?)\" +?(\\w+)=\"(.*?)\"");

    public XmlReader(Scanner reader) {
      sourceReader = reader;
    }

    @Override
    public void process(WriterHandler writerHandler) throws Exception {
      try {
        School school = buildModel();
        writerHandler.process(school);
      } catch (Exception e) {
        e.printStackTrace();
        throw new Exception(e);
      }
    }

    /**
      * Returns the canonical model based on the XML input stream
      * provided
      *
      * @return
      */
    private School buildModel() {
      School school = new School();

      String line;
      String tag;
      String grade = null;
      Classroom c = null;
      while (!isEmpty(line = sourceReader.nextLine())) {
        Matcher startTagMtch = tagPattern.matcher(line);
        boolean tagFound = startTagMtch.find();
        if (tagFound) { //found XML start tag
          tag = startTagMtch.group(1);
          String attr = startTagMtch.group(2);
          if ("student".equals(tag)) {
            c.addStudent(getPerson(attr));
          } else if ("teacher".equals(tag)) {
            c.addTeacher(getPerson(attr));
          } else {
            if ("school".equals(tag)) {
              school.setId(findId(attr));
            } else if ("grade".equals(tag)) {
              grade = findId(attr);
            } else if ("classroom".equals(tag)) {
              c = getClassroom(attr, grade);
              school.addClassroom(c);
            }
          }

        }
      }

      return school;
    }

    private String findId(String attr) {
      Matcher attrMtch = singleAttr.matcher(attr);
      if (attrMtch.find()) {
        return attrMtch.group(2);
      }
      return null;
    }

    private Person getPerson(String attr) {
      Matcher attrMtch = tripleAttr.matcher(attr);
      if (attrMtch.find()) {
        return new Person(attrMtch.group(2), attrMtch.group(4), attrMtch.group(6));
      }
      return null;
    }

    private Classroom getClassroom(String attr, String grade) {
      Matcher attrMtch = doubleAttr.matcher(attr);
      if (attrMtch.find()) {
        return new Classroom(attrMtch.group(2), attrMtch.group(4), grade);
      }
      return null;
    }

  }

  /**
    * Interface that any writer or sink needs to implement
    * to write the canonical model to the underline representation.
    */
  public interface WriterHandler {
    void process(School school);
  }

  /**
    * Writes a canonical model to a CSV representation.
    */
  static public class CsvWriter implements WriterHandler {
    private final PrintWriter outputWriter;

    public CsvWriter(PrintWriter writer) {
      outputWriter = writer;
    }

    @Override
    public void process(School school) {
      outputWriter.println(CSV_HEADER);
      for (Map.Entry<String, Classroom> entry : school.classrooms.entrySet()) {
        Classroom c = entry.getValue();
        String line;
        for (Person s : c.students.values()) {
          line = c.id + CM + c.getName() + CM;
          line = fillPerson(c.getTeacher(0), line);
          line = fillPerson(c.getTeacher(1), line);
          line = fillPerson(s, line);
          line = line + c.getGrade();
          outputWriter.println(line);
        }
      }
      outputWriter.flush();
    }

    private String fillPerson(Person teacher, String line) {
      if (teacher != null) {
        return line + teacher.getId() + CM + teacher.getLastName() + CM + teacher.getFirstName() + CM;
      }
      return line + CM + CM + CM;
    }
  }

  /**
    * Writes a canonical model to an XML representation.
    */
  static public class XmlWriter implements WriterHandler {
    private static final String FMT_SCHOOL_ID = "<school id=\"%s\">";
    private static final String FMT_GRADE_ID = "<grade id=\"%s\">";
    private static final String FMT_CLASSROOM = "<classroom id=\"%s\" name=\"%s\">";
    private static final String FMT_PERSON = "<%s id=\"%s\" first_name=\"%s\" last_name=\"%s\"/>";

    private final PrintWriter outputWriter;

    public XmlWriter(PrintWriter writer) {
      outputWriter = writer;
    }

    @Override
    public void process(School school) {
      outputWriter.println(XML_HEADER);
      outputWriter.println(String.format(FMT_SCHOOL_ID, school.getId()));
      outputWriter.flush();
      String grade = null;
      for (Map.Entry<String, Classroom> entry : school.classrooms.entrySet()) {
        Classroom c = entry.getValue();
        grade = processGrade(c, grade);
        processClassroom(c);
      }
      if (school.classrooms.size() > 0)
        outputWriter.println("</grade>");
      outputWriter.println("</school>");
      outputWriter.flush();
    }

    private void processClassroom(Classroom c) {
      Person p = null;
      outputWriter.println(String.format(FMT_CLASSROOM, c.getId(), c.getName()));
      for (int i = 0; i < c.getTeacherCount(); i++) {
        p = c.getTeacher(i);
        outputWriter.println(String.format(FMT_PERSON, "teacher", p.getId(), p.firstName, p.lastName));
      }
      for (Person s : c.students.values()) {
        outputWriter.println(String.format(FMT_PERSON, "student", s.getId(), s.firstName, s.lastName));
      }
      outputWriter.println("</classroom>");
      outputWriter.flush();
    }

    private String processGrade(Classroom c, String grade) {
      if (!c.getGrade().equals(grade)) {
        if (grade != null) {
          outputWriter.println("</grade>");
        }
        grade = c.getGrade();
        outputWriter.println(String.format(FMT_GRADE_ID, grade));
      }
      return grade;
    }
  }

  /**
    * School is the canonical model of the system. All data read
    * is transform to this model. Every reader process the input
    * into this model. Every writer writes this model to the output.
    */
  static public class School {

    private String id = "100";
    private HashMap<String, Classroom> classrooms = new HashMap<>();

    public Classroom getClassroom(String id) {
      return classrooms.get(id);
    }

    public void addClassroom(Classroom classroom) {
      classrooms.put(classroom.id, classroom);
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id.trim();
    }
  }

  /**
    * Components of the school collect teachers and students
    */
  static public class Classroom {

    private final String id;
    private final String name;
    private final String grade;
    private final Map<String, Person> teachers = new HashMap<>(2);
    // Used to keep teacher's order first, second teacher
    private final List<String> teacherOrder = new ArrayList<>(2);
    private final Map<String, Person> students = new HashMap<>();

    public String getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public String getGrade() {
      return grade;
    }


    public Classroom(String id, String name, String grade) {
      this.id = id.trim();
      this.name = name.trim();
      this.grade = grade.trim();
    }

    public Person getTeacher(int i) {
      if (i > -1 && i < teachers.size())
        return teachers.get(teacherOrder.get(i));
      return null;

    }

    public int getTeacherCount() {
      return teacherOrder.size();
    }

    public void addTeacher(Person teacher) {
      if (teachers.get(teacher.getId()) == null) {
        teachers.put(teacher.getId(), teacher);
        teacherOrder.add(teacher.getId());
      }

    }

    public void addStudent(Person student) {
      if (students.get(student.getId()) == null) {
        students.put(student.getId(), student);
      }
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Classroom)) return false;
      Classroom classroom = (Classroom) o;
      return id.equals(classroom.getId());
    }

    @Override
    public int hashCode() {
      return Objects.hash(id);
    }
  }

  /**
    * Represents either an student or a teacher.
    */
  static public class Person {
    private final String id;
    private final String firstName;
    private final String lastName;

    public Person(String id, String firstName, String lastName) {
      this.id = id.trim();
      this.firstName = firstName.trim();
      this.lastName = lastName.trim();
    }

    public String getId() {
      return id;
    }

    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Person)) return false;
      Person person = (Person) o;
      return id.equals(person.id) &&
        firstName.equals(person.firstName) &&
        lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, firstName, lastName);
    }
  }

}