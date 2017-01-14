package com.bytetrend.sandbox.scala.xml


object Samples {
  val labPhoneBook =
    <phonebook>
      <descr>
        This is the <b>phonebook</b> of the
        <a href="http://acme.org">ACME</a> corporation.
      </descr>
      <entry>
        <name>Burak Emir</name>
        <phone where="work">+41 21 693 68 67</phone>
      </entry>
    </phonebook>;

  def main(args: Array[String]) = {
    Console.println(labPhoneBook)


  }
}


object verboseBook {
  import scala.xml.{ UnprefixedAttribute, Elem, Node, Null, Text, TopScope }
  val pbookVerbose =
    Elem(null, "phonebook", Null, TopScope,
      Elem(null, "descr", Null, TopScope,
        Text("This is a "),
        Elem(null, "b", Null, TopScope, Text("sample")),
        Text("description")
      ),
      Elem(null, "entry", Null, TopScope,
        Elem(null, "name", Null, TopScope, Text("Burak Emir")),
        Elem(null, "phone", new UnprefixedAttribute("where","work", Null), TopScope,
          Text("+41 21 693 68 67"))
      )
    )

  def main(args: Array[String]) =
    Console.println( pbookVerbose )

}