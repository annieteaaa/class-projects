//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

import org.junit.*;
import static org.junit.Assert.*;

public class MyQueueTester {
    
    MyQueue<Integer> queueOne;
    private static final int DEFAULT_CAP = 10;

    /**
     * Test that the queue is instantiated correctly and works as intended
     */
    @Test
    public void testSanity() {
        queueOne = new MyQueue<Integer>(DEFAULT_CAP);
        assertEquals(0, queueOne.theQueue.size);
        assertEquals(0, queueOne.theQueue.size());
        assertEquals(0, queueOne.theQueue.front);
        assertEquals(0, queueOne.theQueue.rear);
        Integer[] expected = {null, null, null, null, null, null, null, null,
                null, null};
        assertEquals(expected, queueOne.theQueue.data);
        assertTrue(queueOne.empty());
        queueOne.enqueue(5);
        assertTrue(!queueOne.empty());
        assertEquals(5, (int)queueOne.peek());
        queueOne.enqueue(6);
        queueOne.enqueue(7);
        queueOne.enqueue(8);
        assertEquals(5, (int)queueOne.peek());
        assertEquals(5, (int)queueOne.dequeue());
        assertEquals(6, (int)queueOne.peek());
        assertEquals(6, (int)queueOne.dequeue());
        assertEquals(7, (int)queueOne.dequeue());
        assertEquals(8, (int)queueOne.dequeue());
        assertEquals(null, queueOne.dequeue());
        assertTrue(queueOne.empty());
    }
}
