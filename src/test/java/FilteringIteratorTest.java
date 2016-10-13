

import com.bytetrend.sandbox.java.FilteringIterator;
import com.bytetrend.sandbox.java.IObjectTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;


public class FilteringIteratorTest {
    private List<Integer> allEven = Arrays.asList(new Integer[]{new Integer(2), new Integer(4), new Integer(6), new Integer(8)});
    private IObjectTest evenNumberTest = new IObjectTest() {
        @Override
        public boolean test(Object o) {
            return (((Integer) o).intValue() % 2 == 0);
        }
    };
    private List<Integer> allOdd = Arrays.asList(new Integer[]{new Integer(1), new Integer(3), new Integer(5), new Integer(7)});
    private IObjectTest oddNumberTest = new IObjectTest() {
        @Override
        public boolean test(Object o) {
            return (((Integer) o).intValue() % 2 != 0);
        }
    };

    @Test
    public void testEvenNumber() {
        FilteringIterator<Integer> filterIterator = new FilteringIterator<>(allEven.iterator(), evenNumberTest);
        int count = 0;
        while (filterIterator.hasNext()) {
            filterIterator.next();
            count++;
        }
        assertEquals(allEven.size(), count);
    }

    @Test
    public void testOddNumber() {
        FilteringIterator<Integer> filterIterator = new FilteringIterator<>(allOdd.iterator(), oddNumberTest);
        int count = 0;
        while (filterIterator.hasNext()) {
            filterIterator.next();
            count++;
        }
        assertEquals(allOdd.size(), count);
    }

    @Test
    public void testEmptyList() {
        FilteringIterator<Integer> filterIterator = new FilteringIterator<>((new ArrayList<Integer>()).iterator(), oddNumberTest);
        int count = 0;
        while (filterIterator.hasNext()) {
            filterIterator.next();
            count++;
        }
        assertEquals(0, count);
    }

    @Test
    public void testMixedList() {
        List<Integer> mixedList = new ArrayList<>(allOdd);
        mixedList.addAll(allEven);
        FilteringIterator<Integer> filterIterator = new FilteringIterator<>(mixedList.iterator(), oddNumberTest);
        int count = 0;
        while (filterIterator.hasNext()) {
            filterIterator.next();
            count++;
        }
        assertEquals(allOdd.size(), count);
    }

}
