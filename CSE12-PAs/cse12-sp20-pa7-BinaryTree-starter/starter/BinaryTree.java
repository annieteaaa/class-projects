//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

/** 
 * This file contains the BinaryTree class. It's useful for storing Objects and
 * retrieving them. Use it when you don't care about the order of the Objects.
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Store Objects in a BinaryTree using the inner Node class.
 */
class BinaryTree<E extends Comparable<E>> {

    Node root;
    int size;

    /**
     * The inner Node class to store data in the tree. It points to its left 
     * and right child.
     */
    protected class Node {
        Node left;
        Node right;
        E data;

        /**
         * Create a Node storing the data
         *
         * @param data The data to be stored
         */
        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        /**
         * Set the left child of the Node
         *
         * @param left The Node to be set
         */
        public void setLeft(Node left) {
            this.left = left;
        }

        /**
         * Set the right child of the Node
         *
         * @param right The Node to be set
         */
        public void setRight(Node right) {
            this.right = right;
        }

        /**
         * Set the data of the Node
         *
         * @param data The data to be set
         */
        public void setData(E data) {
            this.data = data;
        }

        /**
         * Get the left child of the Node
         *
         * @return The left child
         */
        public Node getLeft() {
            return this.left;
        }

        /**
         * Get the right child of the Node
         *
         * @return The right child
         */
        public Node getRight() {
            return this.right;
        }

        /**
         * Get the data of the Node
         *
         * @return The data
         */
        public E getData() {
            return this.data;
        }
    }

    /**
     * Create an empty binary tree
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Create a binary tree with one root Node using the given data
     *
     * @param data The element to be stored in root
     */
    public BinaryTree(E data) {
        root = new Node(data);
	size = 1;
    }

    /**
     * Create a binary tree inserting elements from the given list
     *
     * @param list The list given
     */
    public BinaryTree(List<E> list) {
        for(E element: list) {
            this.add(element);
        }
    }

    /**
     * Add an element to the list. Throw exception if element is null
     *
     * @param element The element in question
     */
    public void add(E element) {
        if(element == null) {
            throw new NullPointerException();
        }
        if(getSize() == 0) {
            root = new Node(element);
	    size++;
        }
        else {

            //Using a queue to traverse the tree for an available null space
            Queue<Node> queue = new LinkedList<>();
            Node curr = root;
            queue.add(curr);
            while( !queue.isEmpty() ) {
                curr = queue.poll();
                if(curr.getLeft() == null) {
                    curr.setLeft(new Node(element));
                    size++;
                    break;
                }
                else if(curr.getRight() == null) {
                    curr.setRight(new Node(element));
                    size++;
                    break;
                }
                else {
                    queue.add(curr.getLeft());
                    queue.add(curr.getRight());
                }
            }
        }
    }

    /**
     * Remove an element if it exists from the tree. Throw exception if element
     * is null
     *
     * @param element The element in question
     * @return true if successfully removed
     */
    public boolean remove(E element) {
        if(element == null) {
            throw new NullPointerException();
        }
        if(getSize() == 0) {
            return false;
        }
        if(getSize() == 1) {
            if(root.getData().compareTo(element) == 0) {
                root = null;
                size--;
                return true;
            }
	    else {
                return false;
            }
        }

        //Using a queue to traverse the tree
        Queue<Node> queue = new LinkedList<>();
        boolean found = false;
        Node curr = root;
        Node toBeRemoved = null;
        queue.add(curr);
        while( !queue.isEmpty() ) {
            curr = queue.poll();
            if(curr.getData().compareTo(element) == 0 && !found) {
                toBeRemoved = curr;
                found = true;
            }
            if(curr.getLeft() != null) {
                queue.add(curr.getLeft());
            }
            if(curr.getRight() != null) {
                queue.add(curr.getRight());
            }
        }
        if(found) {
            toBeRemoved.setData(curr.getData());

            //traverse tree again to reset parent Node values before removing
            queue.clear();
            Node newcurr = root;
            queue.add(newcurr);
            while( !queue.isEmpty() ) {
                newcurr = queue.poll();
                if(newcurr.getLeft() != null) {
                    if(newcurr.getLeft() == curr) {
                        toBeRemoved.setData(curr.getData());
                        newcurr.setLeft(null);
                        size--;
                        return true;
                    }
                    else {
                        queue.add(newcurr.getLeft());
                    }
                }
                if(newcurr.getRight() != null) {
                    if(newcurr.getRight() == curr) {
                        toBeRemoved.setData(curr.getData());
                        newcurr.setRight(null);
                        size--;
                        return true;
                    }
                    else {
                        queue.add(newcurr.getRight());
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check if the tree contains a given element. Throw exception if element is
     * null
     *
     * @param element The element in question
     * @return true if found
     */
    public boolean containsBFS(E element) {
        if(element == null) {
            throw new NullPointerException();
        }
        if(getSize() == 0) {
            return false;
        }

        //Use a queue to traverse and look for element in tree
        Queue<Node> queue = new LinkedList<>();
        Node curr = root;
        queue.add(curr);
        while( !queue.isEmpty() ) {
            curr = queue.poll();
            if(curr.getData().compareTo(element) == 0) {
                return true;
            }
            if(curr.getLeft() != null) {
                queue.add(curr.getLeft());
            }
            if(curr.getRight() != null) {
                queue.add(curr.getRight());
            }
        }
        return false;
    }

    /**
     * Get the height of the tree
     *
     * @return The height of the tree
     */
    public int getHeight() {
        if(getSize() == 0) {
            return 0;
        }
	else {
            return (int)(Math.log(size)/Math.log(2));
	}
    }

    /**
     * Get the size of the tree
     *
     * @return size of tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Get the minimum element of the tree. Return null if tree is empty
     *
     * @return The element in question
     */
    public E minValue() {
        if(getSize() == 0) {
            return null;
        }
        else {

            //Use a queue to traverse and look for min
            Queue<Node> queue = new LinkedList<>();
            E min = root.getData();
            Node curr = root;
            queue.add(curr);
            while( !queue.isEmpty() ) {
                curr = queue.poll();
                if(curr.getData().compareTo(min) < 0) {
                    min = curr.getData();
                }
                if(curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if(curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            }
            return min;
        }
    }
}
