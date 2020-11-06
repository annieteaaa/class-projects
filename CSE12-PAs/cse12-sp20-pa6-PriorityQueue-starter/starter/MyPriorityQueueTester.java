//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

/**
 * File for PriorityQueue tester
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*; 

/**
 * Our Tester class to test the methods
 */
public class MyPriorityQueueTester {
    private MyPriorityQueue<Integer> smallQueue;
    private MyPriorityQueue<Integer> medQueue;

    private Integer[] smallArray = {5, 2, 3};
    private Integer[] medArray = {1, 4, 9, 2, 12};

    @Before
    public void setUp() {

    }

    @Test
    public void testConstruct() {
        smallQueue = new MyPriorityQueue<Integer>(Arrays.asList(smallArray));
        try {
            medQueue = new MyPriorityQueue<Integer>(null);
            fail("Exception expected");
        }
        catch(NullPointerException e) {
            //pass
        }
    }
}
