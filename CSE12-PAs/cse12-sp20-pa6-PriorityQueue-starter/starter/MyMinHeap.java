//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

/**
 * This file contains the MyMinHeap class. It is useful for storing elements
 * in a heap, and it will store according to the smallest element having 
 * higher priority.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class can percolate elements as necessary, remove and re-percolate, and
 * add elements. The List list will hold all our elements and will be 
 * manipulated accordingly.
 */
public class MyMinHeap<E extends Comparable<E>>
{
    protected List<E> list;

    /**
     * No parameter constructor
     */
    public MyMinHeap() {
        list = new ArrayList<E>();
    }

    /**
     * Set list to contain a given collection. Throw exception if list is null 
     * and/or contains a null element.
     *
     * @param collection The collection in question
     */
    public MyMinHeap(Collection<? extends E> collection) {
        if(collection == null) {
            throw new NullPointerException();
	}
        if(collection.contains(null)) {
            throw new NullPointerException();
        }
        list = new ArrayList<E>(collection);

	//percolate by priority to maintain heap properties
        for(int i = 0; i < list.size(); i++) {
            percolateDown(i);
        }
    }

    /**
     * Swap two elements at the given indices
     *
     * @param from The first index
     * @param to The second index
     */
    protected void swap(int from, int to) {
        E element = list.get(from);
        list.set(from, list.get(to));
        list.set(to, element);
    }

    /**
     * Get the parent index of the given index
     *
     * @param index The given index
     * @return The parent's index
     */
    protected int getParentIdx(int index) {
        return (index-1)/2;
    }

    /**
     * Get the left child index of the given index
     *
     * @param index The given index
     * @return The left child's index
     */
    protected int getLeftChildIdx(int index) {
        return index*2+1;
    }

    /**
     * Get the right child index of the given index
     *
     * @param index The given index
     * @return The right child's index
     */
    protected int getRightChildIdx(int index) {
        return index*2+2;
    }
    
    /**
     * Get the minimum child index of the given index
     *
     * @param index The given index
     * @return The minimum child's index
     */
    protected int getMinChildIdx(int index) {
        int leftie = getLeftChildIdx(index);
        int rightie = getRightChildIdx(index);
        if(leftie >= list.size()) {
            return -1;
        }
        if(leftie == list.size()-1) {
            return leftie;
        }
        if(list.get(rightie).compareTo(list.get(leftie)) < 0) {
            return rightie;
        }
        else {
            return leftie;
        }
    }

    /**
     * Percolate an element up as necessary
     *
     * @param index The element's index
     */
    protected void percolateUp(int index) {
        int currentPos = index;
	int parent = getParentIdx(currentPos);
        while(parent >= 0 && list.get(parent)
                .compareTo(list.get(currentPos)) > 0) {
            swap(currentPos, parent);
	    currentPos = parent;
	    parent = getParentIdx(currentPos);
        }
    }

    /**
     * Percolate an element down as necessary
     *
     * @param index The elmeent's index
     */
    protected void percolateDown(int index) {
        int currentPos = index;
	int minChild = getMinChildIdx(currentPos);
        while(minChild >= 0 && minChild < list.size() && list.get(minChild)
                .compareTo(list.get(currentPos)) < 0) {
            swap(currentPos, minChild);
            currentPos = minChild;
	    minChild = getMinChildIdx(currentPos);
        }
    }
    
    /**
     * Delete an element at a given index and return the element
     *
     * @param index The given index
     * @return The element removed
     */
    protected E deleteIndex(int index) {
        E getThis = list.get(index);
        list.remove(index);

        //Account for edge cases where size is 0 or size is 1
        if(index < list.size()-1 && list.size() > 1) {
            list.add(index, list.get(list.size()-1));
            list.remove(list.size()-1);
            if(getThis.compareTo(list.get(index)) > 0) {
                percolateUp(index);
            }
            else if(getThis.compareTo(list.get(index)) < 0) {
                percolateDown(index);
            }
        }
        return getThis;
    }

    /**
     * Insert an element and percolate as necessary. Throw exception if element
     * is null.
     *
     * @param element The element in question
     */
    public void insert(E element) {
        if(element == null) {
            throw new NullPointerException();
        }
	list.add(element);
        percolateUp(list.size()-1);
    }

    /**
     * Return the minimum element, A.K.A. the top of the heap. Return null if
     * heap is empty.
     * 
     * @return The element in question
     */
    public E getMin() {
        if(list.size() == 0) {
            return null;
        }
        else {
            return list.get(0);
        }
    }

    /**
     * Remove and return the minimum element. Return null if heap is empty
     *
     * @return The element in question
     */
    public E remove() {
        if(list.size() == 0) {
            return null;
        }
        else {
            return deleteIndex(0);
        }
    }

    /**
     * Return the size of the heap
     *
     * @return The size of list
     */
    public int size() {
        return list.size();
    }

    /**
     * Clear the heap
     */
    public void clear() {
        list.clear();
    }		
}
