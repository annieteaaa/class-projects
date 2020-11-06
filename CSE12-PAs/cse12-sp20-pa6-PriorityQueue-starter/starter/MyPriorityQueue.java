//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

/** 
 * This file contains the class MyPriorityQueue. Use this for when you need to
 * sort a list by priority and retrieve by priority.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/** 
 * This class will sort the minimum elements at the top of the heap. Minimum
 * elements have higher priority, and they are stored in an inner MyMinHeap.
 */
public class MyPriorityQueue<E extends Comparable<E>> {

    protected MyMinHeap<E> heap;

    /**
     * No argument constructor
     */
    public MyPriorityQueue() {
        heap = new MyMinHeap<E>();
    }

    /**
     * Constructor that takes in a list and sorts it
     *
     * @param collection The list in question
     */
    public MyPriorityQueue(Collection<? extends E> collection) {
        heap = new MyMinHeap<E>(collection);
    }

    /**
     * Add element and percolate if necessary. Throw exception if null
     *
     * @param element The element in question
     */
    public void push(E element) {
        heap.insert(element);
    }

    /**
     * Return element at the top. Return null if heap is empty
     *
     * @return The element in question
     */
    public E peek() {
        return heap.getMin();
    }

    /**
     * Remove and return element at top. Return null if heap is empty
     *
     * @return The element in question
     */
    public E pop() {
        return heap.remove();
    }

    /**
     * Return the number of elements
     *
     * @return The size of heap
     */
    public int getLength() {
        return heap.size();
    }

    /**
     * Clear the heap
     */
    public void clear() {
        heap.clear();
    }
}
