/** NAME: Annie Tong
 *  ID: A15770705
 *  EMAIL: actong@ucsd.edu
 */

import java.util.*;

/** This creates a Linked List. Use Nodes to add elements to your list as well
 * as access and remove elements. Great for easily adding elements to the head
 * or tail. This contains an iterator as well as a Node class all in the
 * MyLinkedList class.
 *  
 */

/** Keep track of elements using Nodes. */
public class MyLinkedList<E> extends AbstractList<E> {

    //track number of elements
	int nelems;

    //dummy head and tail Nodes
	Node head;
	Node tail;

    /** Store data as a Node, with previous and next Node pointers */
	protected class Node {
		E data;
		Node next;
		Node prev;

		public Node(E element)
		{
			data = element;
		}

        /**
         * Set previous Node.
         *
         * @param p The Node in question
         */
		public void setPrev(Node p)
		{
			prev = p;
		}

        /**
         * Set next Node.
         *
         * @param n The Node in question
         */
		public void setNext(Node n)
		{
			next = n;
		}

        /**
         * Set the data element.
         *
         * @param e The data to be set
         */
		public void setElement(E e)
		{
			data = e;
		}

        /**
         * Get the next Node.
         *
         * @return The next Node
         */
		public Node getNext()
		{
			return next;
		}

        /**
         * Get the previous Node.
         *
         * @return The previous Node
         */
		public Node getPrev()
		{
			return prev;
		} 

        /**
         * Get the data.
         *
         * @return The data stored
         */
		public E getElement()
        {
			return data;
		} 
	}

	/** ListIterator implementation */ 
	protected class MyListIterator implements ListIterator<E> {

        //Facing the tail of the list
		boolean forward;

		boolean canRemoveOrSet;

        //Nodes left and right of the Iterator index
		Node left,right;

        //Current Iterator index (not list index)
		int idx;

		public MyListIterator()
		{
            forward = true;
            canRemoveOrSet = false;
            idx = 0;
            left = head;
            right = head.getNext();
		}

        /**
         * Add an element right before iterator index
         *
         * @param e The element in question
         */ 
		@Override
		public void add(E e)
		{
            if(e == null) {
                throw new NullPointerException("data is null");
            }
            MyLinkedList.this.add(idx, e);
            idx++;
            left = right.getPrev();
            canRemoveOrSet = false;
		}

        /**
         * Check if there is still an element towards the tail
         *
         * @return True or false to the question
         */
		@Override
		public boolean hasNext()
		{
			if(right == tail) {
                return false;
            }
            else {
                return true;
            }
		}

        /**
         * Check if there is still an element towards head direction
         *
         * @return True or false to the question
         */
		@Override
		public boolean hasPrevious()
		{
            if(idx == 0) {
			    return false;
            }
            else {
                return true;
            }
		}

        /**
         * Traverse towards the tail and give the next element. Throw
         * Exception if at the end of list.
         *
         * @return The next element
         */
		@Override
		public E next()
		{
            if(idx == size()) {
                throw new NoSuchElementException("End of list");
            }
            idx++;
            forward = true;
            left = right;
            right = right.getNext();
            canRemoveOrSet = true;
			return left.getElement();
		}

        /**
         * Return the index of the next element
         *
         * @return The index in question
         */
		@Override
		public int nextIndex()
		{
            return idx;
		}

        /**
         * Traverse towards the head and return the previous element. Throw 
         * exception if at the beginning of the list already
         *
         * @return The previous element.
         */
		@Override
		public E previous()
		{
            if(nextIndex() == 0) {
                throw new NoSuchElementException("Beginning of list");
            }
            idx--;
            forward = false;
            right = left;
            left = left.getPrev();
            canRemoveOrSet = true;
            return right.getElement();
		}

        /**
         * Return the previous element's index.
         *
         * @return The index in question
         */
		@Override
		public int previousIndex()
		{
			return idx-1;
		}

        /**
         * Remove element depending on the direction of the iterator. Throw
         * exception if currently cannot remove.
         *
         */
		@Override
		public void remove()
		{
            if(canRemoveOrSet == false) {
                throw new IllegalStateException("cannot remove");
            }
            if(forward == true) {
                left = left.getPrev();
                MyLinkedList.this.remove(idx-1);
            }
            else {
                right = right.getNext();
                MyLinkedList.this.remove(idx);
            }
            idx--;
            canRemoveOrSet = false;    
		}

        /**
         * Set the element according to the direction of the iterator. Throw
         * exception if currently cannot set or data is null.
         *
         * @param e The data in question.
         */
		@Override
		public void set(E e) 
		{
			if(e == null) {
                throw new NullPointerException("null data");
            }
            if(canRemoveOrSet == false) {
                throw new IllegalStateException("cannot set");
            }
            if(forward == true) {
                left.setElement(e);
            }
            else {
                right.setElement(e);
            }
		}
	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */
	public MyLinkedList()
	{
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
	}

    /**
     * Return number of elements.
     *
     * @return Number of elements
     */
	@Override
	public int size()
	{
		return nelems;
	}

    /**
     * Get element at an index. Throw exception if index invalid.
     *
     * @param index The index in question
     * @return The element at that index
     */
	@Override
	public E get(int index)
	{
        if(index < 0 || index >= nelems) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return getNth(index).getElement();
	}

    /** Add an element at a given index. Throw exception if data is null or
     * index is invalid.
     *
     * @param index The index in question
     * @param data The element to be added
     */
	@Override
	public void add(int index, E data) 
	{
		if(data == null) {
            throw new NullPointerException("Data is null");
        }
        if(index < 0 || index > nelems) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node newNode = new Node(data);

        //Check if adding to the end of the list
        if(index == nelems) {
            newNode.setPrev(tail.getPrev());
            newNode.setNext(tail);
            tail.getPrev().setNext(newNode);
            tail.setPrev(newNode);
        }
        else {
            Node reference = getNth(index);
            reference.getPrev().setNext(newNode);
            newNode.setPrev(reference.getPrev());
            newNode.setNext(reference);
            reference.setPrev(newNode);
        }
        nelems++;
	}

    /**
     * Add element to the end of the list. Throw exception if data is null
     *
     * @param data The element in question
     * @return True if successful
     */
	public boolean add(E data)
	{
        if(data == null) {
            throw new NullPointerException("Data is null");
        }
        Node newNode = new Node(data);
        newNode.setPrev(tail.getPrev());
        newNode.setNext(tail);
        tail.getPrev().setNext(newNode);
        tail.setPrev(newNode);
        nelems++;
		return true;
	}

    /**
     * Set the element at a given index. Throw exception if data is null or
     * index is invalid.
     *
     * @param index The index in question
     * @param data The element to be added
     * @return The previous data that is overwritten
     */
	public E set(int index, E data) 
	{
        if(data == null) {
            throw new NullPointerException("Data is null");
        }
        if(index < 0 || index >= nelems) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node indexNode = getNth(index);
        E previousData = indexNode.getElement();
        indexNode.setElement(data);
		return previousData;
	}

    /**
     * Remove element at a given index. Throw exception if index is invalid
     *
     * @param index The index in question
     * @return The removed element
     */
	public E remove(int index)
	{
        if(index < 0 || index >= nelems) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node indexNode = getNth(index);
        E indexData = indexNode.getElement();
        indexNode.getPrev().setNext(indexNode.getNext());
        indexNode.getNext().setPrev(indexNode.getPrev());
        nelems--;
		return indexData;
	}

    /**
     * Clear the whole list.
     */
	public void clear()
	{
		tail.setPrev(null);
        head.setNext(null);
        nelems = 0;
	}

    /**
     * Check if the list is empty.
     *
     * @return True or false if list is empty
     */
	public boolean isEmpty()
	{
        if(nelems == 0) {
            return true;
        }
        else {
            return false;
        }
	}

    /**
     * Get the Node at the given index. Throw exception if index is invalid.
     *
     * @param index The index in question
     * @return The Node at that index
     */
	protected Node getNth(int index)
	{
        if( index < 0 || index >= nelems ) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node nthNode;
        
        //Check whether traversing from the head or tail is faster
        if(index < nelems/2) {
            nthNode = head;
            for(int i = 0; i <= index; i++) {
                nthNode = nthNode.getNext();
            }
        }
        else {
            nthNode = tail;
            for(int i = nelems-1; i >= index; i--) {
                nthNode = nthNode.getPrev();
            }
        }
        return nthNode;
	}

    //implementations of Iterator class
	public Iterator<E> iterator()
	{
	    return new MyListIterator();
	}
	public ListIterator<E> listIterator()
	{
		return new MyListIterator();
	}

}

// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4
