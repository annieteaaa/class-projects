//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

/**
 * This file contains the MyStack class. This is useful for when you want a 
 * first in last out implementation of the MyDeque class.

/**
 * This class utilizes the MyDeque data structure as a variable called theStack.
 */
class MyStack<E> implements StackInterface<E> {
    MyDeque<E> theStack;

    /**
     * Create a MyStack and initialize theStack to the given capacity
     *
     * @param initialCapacity The capacity to be set
     */
    public MyStack(int initialCapacity) {
        theStack = new MyDeque<E>(initialCapacity);
    }

    /**
     * Check if the stack is empty
     *
     * @return true if empty
     */
    public boolean empty() {
        return theStack.size() == 0;
    }

    /**
     * Add item to top of stack
     *
     * @param e The element to be added
     */
    public void push(E e) {
        theStack.addLast(e);
    }
    
    /**
     * Remove item from top of stack
     *
     * @return The element removed
     */
    public E pop() {
        return theStack.removeLast();
    }

    /**
     * Return item at the top of stack
     *
     * @return The element in question
     */
    public E peek() {
        return theStack.peekLast();
    }
}
