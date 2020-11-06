//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

/**
 * This file contains code for the MyQueue class. Use it for when you want a
 * first in first out type of deque.
 */

/**
 * MyQueue uses MyDeque as its underlying data structure, here the variable 
 * theQueue. 
 */
public class MyQueue<E> implements QueueInterface<E> {
    
    MyDeque<E> theQueue;

    public MyQueue(int initialCapacity) {
        theQueue = new MyDeque<E>(initialCapacity);
    }

    /**
     * Check if queue is empty
     *
     * @return true if empty
     */
    public boolean empty() {
        return theQueue.size == 0;
    }

    /**
     * Push an element to the end of the queue
     *
     * @param e The element in question
     */
    public void enqueue(E e) {
        theQueue.addLast(e);
    }

    /**
     * Pop an element from the front of the queue
     *
     * @return The element removed
     */
    public E dequeue() {
        return theQueue.removeFirst();
    }

    /**
     * Return the element at the front of the queue
     *
     * @return The element in question
     */
    public E peek() {
        return theQueue.peekFirst();
    }
}
