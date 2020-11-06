//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

/** 
 * The file contains the MyBST class, which also contains its inner class 
 * MyBSTNode. This is useful for inserting elements that will get automatically
 * sorted.
 */

import java.util.ArrayList;

/**
 * MyBST uses MyBSTNodes to store keys and values. No duplicate keys are
 * allwed.
 */
public class MyBST<K extends Comparable<K>, V> {

    /**
     * MyBSTNode contains a key and value, as well as pointers to its parent,
     * left child, and right child. It is only used by the MyBST class, not 
     * the user directly.
     */
    static class MyBSTNode<K, V> {

        K key;
        V value;
        MyBSTNode<K, V> parent;
        MyBSTNode<K, V> left;
        MyBSTNode<K, V> right;

        /**
         * Simple constructor setting all but left and right
         *
         * @param key The key of the node
         * @param value Value of node
         * @param parent Parent of node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key
         *
         * @return Key in question
         */
        public K getKey() {
            return key;
        }

        /**
         * Return the value
         *
         * @return Value in question
         */
        public V getValue() {
            return value;
        }

        /**
         * Return the parent
         *
         * @return The parent of node
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Return the left child
         *
         * @return the left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * return the right child
         * 
         * @return the right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

	/**
         * Set a new key
         *
         * @param newKey The key in question
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Set a new value
         *
         * @param newValue The value in question
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Set a new parent
         *
         * @param newParent The parent in question
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Set a new left child
         *
         * @param newLeft The child in question
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Set a new right child
         *
         * @param newRight The child in question
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /**
         * Get the next largest key
         *
         * @return The next largest key
         */
        public MyBSTNode<K, V> successor() {
            if(getRight() != null) {
                if(getRight().getLeft() == null) {
                    return getRight();
                }
                else {
                    MyBSTNode<K, V> current = getRight();
                    while(current.getLeft() != null) {
                        current = current.getLeft();
                    }
                    return current;
                }
            }
            else {
                if(getParent() == null) {
                    return null;
                }
                if(getParent().getLeft() == this) {
                    return getParent();
                }
                else {
                    if(getLeft() != null) {
                        return null;
                    }
                    MyBSTNode<K, V> current = getParent();
                    while(current == current.getParent().getRight()) {
                        current = current.getParent();
                        if(current.getParent() == null) {
                            return null;
                        }
                    }
                    return current.getParent();
                }
            }
        }
    }

    MyBSTNode<K, V> root;
    int size;

    /**
     * Get the successor of a node
     *
     * @param node The node in question
     * @return Its successor
     */
    protected MyBSTNode<K, V> successor(MyBSTNode<K, V> node) {
        if(node == null) {
            return null;
        }
        else {
            return node.successor();
        }
    }

    /**
     * Return the size
     *
     * @return The size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * Insert a new key and value. Throw exception if key argument null
     *
     * @param key The key
     * @param value The value
     * @return The previous value. if no previous value, return null
     */
    public V insert(K key, V value) {
        if(key == null) {
            throw new NullPointerException();
        }
	MyBSTNode<K, V> current = root;
        while(current != null) {
            if(current.getKey().compareTo(key) == 0) {
                V previousValue = current.getValue();
                current.setValue(value);
                return previousValue;
            }
            else if(key.compareTo(current.getKey()) < 0) {
                if(current.getLeft() == null) {
                    current.setLeft(new MyBSTNode<K, V>(key, value, current));
                    size++;
                    return null;
                }
                else {
                    current = current.getLeft();
                }
            }
            else {
                if(current.getRight() == null) {
                    current.setRight(new MyBSTNode<K, V>(key, value, current));
                    size++;
                    return null;
                }
                else {
                    current = current.getRight();
                }
            }
        }
        root = new MyBSTNode<K, V>(key, value, null);
        size++;
        return null;
    }

    /**
     * Search for a key's value
     *
     * @param key The key in question
     * @return The value of the key. null if key doesn't exist
     */
    public V search(K key) {
        MyBSTNode<K, V> current = root;
        while(current != null) {
            if(current.getKey().compareTo(key) == 0) {
                return current.getValue();
            }
            else if(key.compareTo(current.getKey()) < 0) {
                current = current.getLeft();
            }
            else {
                current = current.getRight();
            }
        }
        return null;
    }

    /**
     * Remove a given key
     *
     * @param key The key in question
     * @return The removed key's value. null if not found
     */
    public V remove(K key) {
        MyBSTNode<K, V> current = root;
        if(key == null) {
            return null;
        }
        while(current != null) {
            if(key.compareTo(current.getKey()) == 0) {
                V removed = current.getValue();
                if(current.getLeft() == null && current.getRight() == null) {
                    if(current.getParent().getLeft() == current) {
                        current.getParent().setLeft(null);
                    }
                    else {
                        current.getParent().setRight(null);
                    }
                }
                else if(current.getLeft() == null || 
                        current.getRight() == null) {
                    if(current.getLeft() == null) {
                        if(current.getParent().getLeft() == current) {
                            current.getParent().setLeft(current.getRight());
                            current.getRight().setParent(current.getParent());
                        }
                        else {
                            current.getParent().setRight(current.getRight());
                            current.getRight().setParent(current.getParent());
                        }
                    }
                    else {
                        if(current.getParent().getLeft() == current) {
                            current.getParent().setLeft(current.getLeft());
                            current.getLeft().setParent(current.getParent());
                        }
                        else {
                            current.getParent().setRight(current.getLeft());
                            current.getLeft().setParent(current.getParent());
                        }

                    }
                }
                else {
                    current.setKey(current.successor().getKey());
                    current.setValue(current.successor().getValue());
                    if(current.successor().getParent().getRight() == 
                            current.successor()) {
                        if(current.successor().getLeft() == null && 
                                current.successor().getRight() == null) {
                            current.successor().getParent().setRight(null);
                        }
                        else {
                            current.successor().getParent().setRight
                                    (current.successor().getRight());
                            current.successor().getRight().setParent
                                    (current.successor().getParent());
                        }
                    }
		    else {
                        current.successor().getParent().setLeft(null);
                    }
                }
                size--;
                return removed;
            }
            else if(key.compareTo(current.getKey()) < 0) {
                current = current.getLeft();
            }
            else {
                current = current.getRight();
            }
        }
        return null;
    }

    /**
     * Return an ArrayList of the Nodes in order by key
     *
     * @return The ArrayList of nodes
     */
    public ArrayList<MyBSTNode<K, V>> inorder() {
        if(root == null) {
            return new ArrayList<MyBSTNode<K, V>>();
        }
        ArrayList<MyBSTNode<K, V>> temp = new ArrayList<>();
        MyBSTNode<K, V> current = root;
        while(current.getLeft() != null) {
            current = current.getLeft();
        }
        temp.add(current);
        while(successor(current) != null) {
            current = successor(current);
            temp.add(current);
        }
        return temp;
    }
}
