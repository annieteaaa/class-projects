//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

/**
 * This file contains code for a Deque class. You can use this to insert objects
 * to the front of back of your deque. This works in a circular array and the
 * capacity will be expanded as necessary. You can retrieve information about
 * the front and rear of the deque, as well as the size.
 */

/** 
 * Create a MyDeque with a valid initial capacity. Add objects as you like to
 * the deque, whether to the front or to the back.
 * size: stores the number of elements
 * front: stores the index of the element at the from
 * rear: stores the index of the element at the back
 * data: stores your elements in an Object array
 */
class MyDeque<E> implements DequeInterface<E> {

    Object[] data;
    int size;
    int rear;
    int front;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DOUBLE_CAPACITY = 2;

    public MyDeque(int initialCapacity) {
        if(initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
    }

    /**
     * Return the number of elements
     *
     * @return Number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Expand the capacity of the array
     */
    public void expandCapacity() {
        if(data.length == 0) {
            data = new Object[DEFAULT_CAPACITY];
        }
        else {
            Object[] temp = new Object[data.length*DOUBLE_CAPACITY];
            int count = 0;
            if(size == 0) {
                rear = 0;
            }
            else if(front <= rear) {
                for(int i = front; i <= rear; i++) {
                    temp[count] = data[i];
                    count++;
                }
                //For cases with more than one element
                if(front != rear) {
                    rear = size-1;
                }
                //for cases with a single element
                else {
                    rear = 0;
                }
            }
            else if(front > rear) {
                for(int i = front; i < data.length; i++) {
                    temp[count] = data[i];
                    count++;
                }
                for(int i = 0; i <= rear; i++) {
                    temp[count] = data[i];
                    count++;
                }
                rear = size-1;
            }
            data = temp;
            front = 0;
        }
    }

    /**
     * Add an element to the front of the deque. Throw NullPointerException if
     * element is null.
     *
     * @param element The element in question
     */
    public void addFirst(E element) {
        if(element == null) {
            throw new NullPointerException();
        }
        if(size == data.length) {
            expandCapacity();
        }

	//case where deque is empty
        if(front == rear && size == 0) {
            data[front] = element;
            size++;
        }
        else {
            int newfront;

             //case where modulo won't work as inteded
            if(front == 0) {
                newfront = data.length-1;
            }
            else {
                newfront = (front-1)%data.length;
            }
            data[newfront] = element;
            size++;
            front = newfront;
        }
    }

    /**
     * Add element to end of deque. Throw NullPointerException if element is
     * null
     *
     * @param element The element in question
     */
    public void addLast(E element) {
        if(element == null) {
            throw new NullPointerException();
        }
        if(size == data.length) {
            expandCapacity();
        }

        //case where deque is empty
        if(front == rear && size == 0) {
            data[rear] = element;
            size++;
        }
        else {
            int newrear = (rear+1)%data.length;
            data[newrear] = element;
            size++;
            rear = newrear;
        }
    }

    /**
     * Remove the first elmeent in the deque. Return null if deque is empty
     *
     * @return The element removed
     */
    @SuppressWarnings("unchecked")
    public E removeFirst() {
        if(front == rear && size == 0) {
            return null;
        }
        else {
            Object removed = data[front];
            data[front] = null;
	    
            //in cases where there are more than one element
            if(front != rear) {
                front = (front+1)%data.length;
            }
            size--;
            return (E) removed;
        }
    }

    /**
     * Remove the last element in the deque. Return null if deque is empty
     *
     * @return The element removed
     */
    @SuppressWarnings("unchecked")
    public E removeLast() {
        if(front == rear && size == 0) {
            return null;
        }
        else {
            Object removed = data[rear];
            data[rear] = null;
	    
            //case where deque has more than one element
            if(front != rear) {
                if(rear == 0) {
                    rear = data.length-1;
                }
                else {
                    rear = (rear-1)%data.length;
                }
            }
            size--;
            return (E) removed;
        }
    }

    /**
     * Return the first element in deque. Return null if deque is empty
     *
     * @return The element in question
     */
    @SuppressWarnings("unchecked")
    public E peekFirst() {
        if(front == rear && size == 0) {
            return null;
        }
        else {
            return (E) data[front];
        }
    }

    /**
     * Return the last element in deque. Return null if empty deque
     *
     * @return The element in question
     */
    @SuppressWarnings("unchecked")
    public E peekLast() {
        if(front == rear && size == 0) {
            return null;
        }
        else {
            return (E) data[rear];
        }
    }
}
