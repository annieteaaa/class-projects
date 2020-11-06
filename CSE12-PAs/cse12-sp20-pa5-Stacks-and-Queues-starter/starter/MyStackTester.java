//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu


import org.junit.*;
import static org.junit.Assert.*;

public class MyStackTester {
    
    MyStack<Integer> stackOne;
    private final static int DEFAULT_CAP = 10;

    /**
     * Test everything is instantiated correctly and works as intended
     */
    @Test
    public void testSanity() {
        stackOne = new MyStack<Integer>(DEFAULT_CAP);
        assertEquals(0, stackOne.theStack.size());
        assertEquals(0, stackOne.theStack.size);
        assertEquals(0, stackOne.theStack.front);
        assertEquals(0, stackOne.theStack.rear);
        Integer[] expected = {null, null, null, null, null, null, null, null,
                null, null};
        assertEquals(expected, stackOne.theStack.data);
        assertTrue(stackOne.empty());
        stackOne.push(3);
        assertTrue(!stackOne.empty());
        assertEquals(3, (int)stackOne.peek());
        stackOne.push(5);
        stackOne.push(4);
        stackOne.push(420);
        assertEquals(420, (int)stackOne.pop());
        assertEquals(4, (int)stackOne.pop());
        assertEquals(5, (int)stackOne.peek());
        assertEquals(5, (int)stackOne.pop());
        assertEquals(3, (int)stackOne.pop());
        assertEquals(null, stackOne.pop());
        assertTrue(stackOne.empty());
    }
}
