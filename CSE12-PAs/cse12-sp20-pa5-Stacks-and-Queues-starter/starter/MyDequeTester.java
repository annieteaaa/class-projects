//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

import org.junit.*;
import static org.junit.Assert.*;

public class MyDequeTester{

    private MyDeque<Integer> capZero;
    private MyDeque<Integer> empty;
    private MyDeque<Integer> oneElem;
    private MyDeque<Integer> capOne;
    private MyDeque<Integer> several;
    private MyDeque<Integer> full;
    private MyDeque<Integer> frontBehindRear;
    private MyDeque<Integer> middleArr;
    private final int SIZE = 7;
    private final int SIZETWO = 8;
    private final int SEVERAL = 3;
    private final int DEFAULT_CAP = 10;
    private final int EXPAND_FACTOR = 2;

    @Before
    public void setUp(){
        capZero = new MyDeque<>(0);
        empty = new MyDeque<>(SIZE);
        oneElem = new MyDeque<>(SIZE);
        capOne = new MyDeque<>(1);
        several = new MyDeque<>(SIZE);
        full = new MyDeque<>(SIZE);
        frontBehindRear = new MyDeque<>(SIZE);
	middleArr = new MyDeque<>(SIZETWO);

        oneElem.data[0] = 0;
        oneElem.size = 1;
        Integer[] severalArr = {0, 1, 2, null, null, null, null};
        several.data = severalArr;
        several.size = SEVERAL;
        several.rear = SEVERAL - 1;

        Integer[] fullArr = {0, 1, 2, 3, 4, 5, 6};
        full.data = fullArr;
        full.size = SIZE;
        full.rear = SIZE - 1;

        Integer[] frontBehindRearArr = {3, 4, null, null, 0, 1, 2};
        frontBehindRear.data = frontBehindRearArr;
        frontBehindRear.front = 4;
        frontBehindRear.rear = 1;
        frontBehindRear.size = 5;

        Integer[] middleArray = {null, null, null, 5, 2, 9, null, null};
        middleArr.data = middleArray;
        middleArr.front = 3;
        middleArr.rear = 5;
        middleArr.size = 3;
    }

    /**
     * Test the basics of each method
     */
    @Test
    public void testSanity() {

        //Test creating MyDeque of size 10
        MyDeque<Integer> sizeten = new MyDeque<>(DEFAULT_CAP);

        sizeten.addFirst(12);
        sizeten.addFirst(13);
        sizeten.addLast(17);

        //Test expandCapacity() works properly
        sizeten.expandCapacity();
        Integer[] expected = {13, 12, 17, null, null, null, null, null, null, 
                null, null, null, null, null, null, null, null, null, null, 
                null};
        assertEquals(expected, sizeten.data);
        assertEquals((int)3, (int)sizeten.size);
        assertEquals((int)0, (int)sizeten.front);
        assertEquals((int)2, (int)sizeten.rear);
        assertEquals(3, (int)sizeten.size);
        assertEquals(0, (int)sizeten.front);
        assertEquals(2, (int)sizeten.rear);

        //Test addFirst and addLast to deque containing several elements in the 
        //middle of the array
        middleArr.addLast(18);
        middleArr.addFirst(65);
        middleArr.addFirst(32);
        middleArr.addLast(78);
        middleArr.addLast(69);
        Integer[] expectedMid = {69, 32, 65, 5, 2, 9, 18, 78};
        assertEquals(expectedMid, middleArr.data);
        assertEquals((int)8, (int)middleArr.size);
        assertEquals((int)1, (int)middleArr.front);
        assertEquals((int)0, (int)middleArr.rear);

        //Test removeFirst and removeLast on deque containing several elements 
        //in the middle of the array
        assertEquals((int)32, (int)middleArr.removeFirst());
        assertEquals((int)69, (int)middleArr.removeLast());
        assertEquals((int)65, (int)middleArr.removeFirst());
        assertEquals((int)5, (int)middleArr.removeFirst());
        assertEquals((int)78, (int)middleArr.removeLast());
        assertEquals((int)18, (int)middleArr.removeLast());
        Integer[] expectedMidFinal = {null, null, null, null, 2, 9, null, null};
        assertEquals(expectedMidFinal, middleArr.data);
        assertEquals((int)2, (int)middleArr.size);
        assertEquals((int)4, (int)middleArr.front);
        assertEquals((int)5, (int)middleArr.rear);

        //Test peekFirst and peekLast on deque containing several elements at
        //the start of the array
        assertEquals((int)Integer.valueOf(0), (int)several.peekFirst());
        assertEquals((int)Integer.valueOf(2), (int)several.peekLast());
        assertEquals((int)Integer.valueOf(3), (int)several.size);
        assertEquals((int)Integer.valueOf(0), (int)several.front);
        assertEquals((int)Integer.valueOf(2), (int)several.rear);
        Integer[] expectedSeveral = {0, 1, 2, null, null, null, null};
        assertEquals(expectedSeveral, several.data);
    }

    /**
     * Test constructor throws exception for invalid index
     */
    @Test
    public void testConstructorException(){
        try{
            MyDeque<Integer> illegal = new MyDeque<>(-1);
            fail("Did not catch constructor exception");
        }
        catch(IllegalArgumentException exc){
            //pass
        }
    }

    /**
     * Test Constructor instantiates the correct properties
     */
    @Test
    public void testConstructorNormal(){
        MyDeque<Integer> normal = new MyDeque<>(SIZE);
        assertEquals(new Integer[SIZE],  normal.data);
        assertEquals(0, normal.front);
        assertEquals(0, normal.rear);
        assertEquals(0, normal.size);
    }

    /**
     * Test addFirst with a null element
     */
    @Test
    public void testAddNullException(){
        try {
            empty.addFirst(null);
            fail("Exception expected");
        }
        catch(NullPointerException e) {
            //pass
        }
    }

    /**
     * Test addFirst to an empty deque
     */
    @Test
    public void testAddFirstEmptyMultiple(){
        empty.addFirst(0);
        empty.addFirst(1);
        empty.addFirst(2);
        Integer[] expected = {0, null, null, null, null, 2, 1};
        assertEquals(expected, empty.data);
        assertEquals(5, empty.front);
        assertEquals(0, empty.rear);
        assertEquals(3, empty.size);
        empty.addFirst(3);
        empty.addFirst(4);
        empty.addFirst(5);
        empty.addFirst(6);
        empty.addFirst(7);
        Integer[] expectedNew = {6, 5, 4, 3, 2, 1, 0, null, null, null, null, 
                null, null, 7};
        assertEquals(expectedNew, empty.data);
        assertEquals(8, empty.size);
        assertEquals(13, empty.front);
        assertEquals(6, empty.rear);
    }

    /**
     * Test addLast wtih null element
     */
    @Test
    public void testAddLastNullException(){
        try {
            empty.addLast(null);
        }
        catch(NullPointerException e) {
            //pass
        }
    }

    /**
     * Test addLast multiple times and at capacity
     */
    @Test
    public void testAddLastMultiple(){
        empty.addLast(0);
        empty.addLast(1);
        empty.addLast(2);
        empty.addLast(3);
        Integer[] expected = {0, 1, 2, 3, null, null, null};
        assertEquals(expected, empty.data);
        assertEquals(4, empty.size);
        assertEquals(0, empty.front);
        assertEquals(3, empty.rear);
        empty.addLast(4);
        empty.addLast(5);
        empty.addLast(6);
        empty.addLast(7);
        Integer[] expectedNew = {0, 1, 2, 3, 4, 5, 6, 7, null, null, null, null,
                null, null};
        assertEquals(expectedNew, empty.data);
        assertEquals(0, empty.front);
        assertEquals(empty.size-1, empty.rear);
        assertEquals(8, empty.size);
    }

    /** 
     * When elements are in the middle of the array
     */
    @Test
    public void testExpandCapacitySeveralEdge(){
        Integer[] severalEdge = {null, null, 0, 1, 2, null, null};
        several.data = severalEdge;
        several.front = 2;
        several.rear = 4;
        several.expandCapacity();
        Integer[] expanded = {0, 1, 2, null, null, null, null, null, null,
                null, null, null, null, null};
        assertEquals(expanded,  several.data);
        assertEquals(0, several.front);
        assertEquals(SEVERAL - 1, several.rear);
        assertEquals(SEVERAL, several.size);
    }

    /**
     * Test the size() method doesn't change anything
     */
    @Test
    public void testSize(){
        several.addFirst(5);
        several.addFirst(6);
        assertEquals(5, several.size());
        assertEquals(5, several.size);
    }

    /*
     * Test expandCapacity when front is greater than rear
     */
    @Test
    public void testExpandGreaterFront(){
        frontBehindRear.expandCapacity();
        Integer[] expected = {0, 1, 2, 3, 4, null, null, null, null, null, null,
                null, null, null};
        assertEquals(expected, frontBehindRear.data);
        assertEquals(0, (int)frontBehindRear.front);
        assertEquals(4, (int)frontBehindRear.rear);
        assertEquals(5, frontBehindRear.size);
    }

    /**
     * Test expandCapacity on one element deque
     */
    @Test
    public void testExpandOneElement(){
        MyDeque<Integer> oneCap = new MyDeque<>(1);
        oneCap.addFirst(0);
        oneCap.addFirst(4);
        Integer[] expected = {0, 4};
        assertEquals(expected, oneCap.data);
        assertEquals(0, (int)oneCap.rear);
        assertEquals(1, (int)oneCap.front);
        assertEquals(2, (int)oneCap.size);
        oneCap.removeLast();
        oneCap.expandCapacity();
        Integer[] expectedTwo = {4, null, null, null};
        assertEquals(expectedTwo, oneCap.data);
        assertEquals(0, (int)oneCap.rear);
        assertEquals(0, (int)oneCap.front);
        assertEquals(1, (int)oneCap.size);
    }

    /**
     * Test expandCapacity on a full capacity
     */
    @Test
    public void testExpandFullArray(){
        full.addFirst(69);
        Integer[] expected = {0, 1, 2, 3, 4, 5, 6, null, null, null, null, null,
                null, 69};
        assertEquals(expected, full.data);
        assertEquals(13, (int)full.front);
        assertEquals(6, (int)full.rear);
        assertEquals(8, (int)full.size);
    }

    /*
     * Test removeFirst method
     */
    @Test
    public void testRemoveFirst(){
        Integer[] severalEdge = {1, null, null, null, null, -1, 0};
        several.data = severalEdge;
        several.front = 5;
        several.rear = 0;
        assertEquals(-1, several.removeFirst().intValue());
        Integer[] expected = {1, null, null, null, null, null, 0};
        assertEquals(expected, several.data);
        assertEquals(6, several.front);
        assertEquals(0, several.rear);
        assertEquals(2, several.size);
        assertEquals(0, several.removeFirst().intValue());
        Integer[] expectedNew = {1, null, null, null, null, null, null};
        assertEquals(expectedNew, several.data);
        assertEquals(0, several.front);
        assertEquals(0, several.rear);
        assertEquals(1, several.size);
        assertEquals(1, several.removeFirst().intValue());
        Integer[] expectedNewNew = {null, null, null, null, null, null, null};
        assertEquals(expectedNewNew, several.data);
        assertEquals(0, several.front);
        assertEquals(0, several.rear);
        assertEquals(0, several.size);
        assertEquals(null, several.removeFirst());
        assertEquals(0, (int)oneElem.removeFirst());
        assertEquals(0, oneElem.size);
        assertEquals(0, oneElem.rear);
        assertEquals(0, oneElem.front);
    }

    /*
     * Test removeLast method
     */
    @Test
    public void testRemoveLastSeveralEdge(){
        Integer[] severalEdge = {1, null, null, null, null, -1, 0};
        several.data = severalEdge;
        several.front = 5;
        several.rear = 0;
        assertEquals(1, several.removeLast().intValue());
        Integer[] expected = {null, null, null, null, null, -1, 0};
        assertEquals(expected, several.data);
        assertEquals(5, several.front);
        assertEquals(6, several.rear);
        assertEquals(2, several.size);
        assertEquals(0, several.removeLast().intValue());
        Integer[] expectedNew = {null, null, null, null, null, -1, null};
        assertEquals(expectedNew, several.data);
        assertEquals(5, several.front);
        assertEquals(5, several.rear);
        assertEquals(1, several.size);
        assertEquals(-1, several.removeLast().intValue());
        Integer[] expectedNewNew = {null, null, null, null, null, null, null};
        assertEquals(expectedNewNew, several.data);
        assertEquals(5, several.front);
        assertEquals(5, several.rear);
        assertEquals(0, several.size);
        assertEquals(null, several.removeLast());
    }

    /**
     * Test peekFirst returns correct value and changes nothing
     */
    @Test
    public void testPeekFirst(){
        empty.addFirst(5);
        assertEquals(5, (int)empty.peekFirst());
        assertEquals(0, empty.front);
        assertEquals(0, empty.rear);
        assertEquals(1, empty.size);
        empty.addLast(6);
        assertEquals(5, (int)empty.peekFirst());
        assertEquals(0, empty.front);
        assertEquals(1, empty.rear);
        assertEquals(2, empty.size);
        empty.addFirst(7);
        assertEquals(7, (int)empty.peekFirst());
        assertEquals(6, empty.front);
        assertEquals(1, empty.rear);
        assertEquals(3, empty.size);
    }

    /**
     * Test peekLast returns correct value and changes nothing
     */
    @Test
    public void testpeekLast(){
        empty.addFirst(5);
        assertEquals(5, (int)empty.peekLast());
        assertEquals(0, empty.front);
        assertEquals(0, empty.rear);
        assertEquals(1, empty.size);
        empty.addLast(6);
        assertEquals(6, (int)empty.peekLast());
        assertEquals(0, empty.front);
        assertEquals(1, empty.rear);
        assertEquals(2, empty.size);
        empty.addFirst(7);
        assertEquals(6, (int)empty.peekLast());
        assertEquals(6, empty.front);
        assertEquals(1, empty.rear);
        assertEquals(3, empty.size);
    }
}
