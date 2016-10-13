import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by db2admin on 4/21/2016.
 */

public class StreamTest {

    private static Collection<Dish> dishCollection;

    static List<Transaction> transactions;

    @BeforeClass
    public static void init() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        dishCollection = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    @Test
    public void testStream() {
        List<String> threeHighCaloricDishNames =
                dishCollection.stream()
                        .filter(d -> d.getCalories() > 300)
                        .map(Dish::getName)
                        .limit(3)
                        .collect(toList());
        assertEquals(3, threeHighCaloricDishNames.size());
    }

    @Test
    public void test2() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i ->
                                        numbers2.stream()
                                                //     .filter(j -> (i + j) % 3 == 0)
                                                .map(j -> new int[]{i, j})
                        ).filter(k -> (k[0] + k[1]) % 3 == 0)
                        .collect(toList());
        assertEquals(2, pairs.size());
    }

    @Test
    public void testReduce() {
        List<Integer> integers = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6});

        Optional<Integer> maxInt = integers.stream().reduce(Math::max);
        assertEquals(true, maxInt.isPresent());
        assertEquals(6, maxInt.get().intValue());
    }

    @Test
    public void testReduce2() {


        List<Transaction> tr2011 =
                transactions.stream()
                        .filter(transaction -> transaction.getYear() == 2011)
                        .sorted(comparing(Transaction::getValue))
                        .collect(toList());
        assertEquals(2, tr2011.size());
        assertTrue(tr2011.get(0).getValue() < tr2011.get(1).getValue());
    }

    @Test
    public void testReduce3() {
        List<String> tr2011 =
                transactions.stream()
                        .map(Transaction -> Transaction.getTrader().getCity())
                        .distinct()
                        .sorted()
                        .collect(toList());
        assertEquals(2, tr2011.size());
        assertTrue("Cambridge".equals(tr2011.get(0).toString()));
    }
    public static void process(Runnable r){
        r.run();
    }

    @Test
    public void lambdaTest(){
        Runnable r1 = () -> System.out.println("Hello World 1");
        Runnable r2 = new Runnable(){
            public void run(){
                System.out.println("Hello World 2");
            }
        };
        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World 3"));
    }
}
