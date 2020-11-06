//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

/**
 * This file contains the class BinaryTreeTester to test our BinaryTree class 
 * code.
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Our tester class to test BinaryTree methods
 */
public class BinaryTreeTester {

    private BinaryTree<Integer> empty;
    private BinaryTree<Integer> one;
    private BinaryTree<Integer> many;

    private static final int FIVE = 5;

    ArrayList<Integer> listfew;

    private static final Integer[] arrfew = {1, 7, 3, 0};

    @Before
    public void setup() {
        empty = new BinaryTree<Integer>();
        one = new BinaryTree<Integer>(FIVE);
	listfew = new ArrayList<>(Arrays.asList(arrfew));
	many = new BinaryTree<Integer>(listfew);
    }

    @Test
    public void testConstruct() {
        BinaryTree<Integer> testing = new BinaryTree<>(3);
	assertTrue(testing.containsBFS(3));
	assertEquals(1, testing.getSize());
	assertEquals(0, testing.getHeight());
	assertEquals(3, (int)testing.minValue());

	BinaryTree<Integer> testing2 = new BinaryTree<>();
	assertTrue(!testing2.containsBFS(2));
	assertEquals(0, testing2.getHeight());
	assertEquals(0, testing2.getSize());
	assertNull(testing2.minValue());
	assertNull(testing2.root);

	BinaryTree<Integer> testing3 = new BinaryTree<>(listfew);
	assertTrue(testing3.containsBFS(0));
	assertTrue(testing3.containsBFS(1));
	assertTrue(testing3.containsBFS(7));
	assertTrue(testing3.containsBFS(3));
	assertTrue(!testing3.containsBFS(2));
	assertEquals(4, testing3.getSize());
	assertEquals(2, testing3.getHeight());
	assertEquals(0, (int)testing3.minValue());
	assertEquals(1, (int)testing3.root.getData());
    }

    @Test
    public void testAdd() {
        try {
	    empty.add(null);
	    fail("Exception expected");
	}
	catch(NullPointerException e) {
	    //pass
	}
	empty.add(6);
	assertEquals(1, empty.getSize());
	assertEquals(0, empty.getHeight());
	empty.add(4);
	assertEquals(2, empty.getSize());
	assertEquals(1, empty.getHeight());
	assertTrue(empty.containsBFS(6));
	assertTrue(empty.containsBFS(4));
	assertTrue(!empty.containsBFS(0));
	assertEquals(4, (int)empty.minValue());
	empty.add(6);
	empty.remove(6);
	assertTrue(empty.containsBFS(6));
    }

    @Test
    public void testRemove() {
	try {
	    one.remove(null);
	    fail("Exception expected");
	}
	catch(NullPointerException e) {
	    //pass
	}
	assertTrue(!empty.remove(4));
	assertEquals(0, empty.getSize());
	assertTrue(!empty.containsBFS(4));
	assertNull(empty.minValue());
	assertTrue(!one.remove(4));
	assertTrue(one.remove(5));
	assertEquals(0, one.getSize());
	assertTrue(!one.containsBFS(5));
	assertNull(one.minValue());
	assertTrue(!many.remove(4));
	assertEquals(4, many.getSize());
	assertTrue(!many.containsBFS(4));
	assertTrue(many.remove(3));
	assertEquals(3, many.getSize());
	assertTrue(!many.containsBFS(3));
	assertTrue(many.containsBFS(0));
	assertEquals(0, (int)many.minValue());
	assertTrue(many.remove(0));
	assertEquals(2, many.getSize());
	assertTrue(!many.containsBFS(0));
	assertEquals(1, (int)many.minValue());
	assertTrue(!many.remove(0));
	assertEquals(2, many.getSize());
	assertTrue(!many.containsBFS(0));
	assertEquals(1, (int)many.minValue());
    }

    @Test
    public void testContainsBFS() {
	try {
	    empty.containsBFS(null);
	    fail("Exception expected");
	}
	catch(NullPointerException e) {
	    //pass
	}
	assertTrue(!empty.containsBFS(3));
	empty.add(5);
	assertEquals(1, empty.getSize());
	assertTrue(empty.containsBFS(5));
	empty.add(6);
	assertTrue(empty.containsBFS(5));
	assertTrue(empty.containsBFS(6));
	assertTrue(!empty.containsBFS(4));
	assertEquals(2, empty.getSize());
	assertEquals(5, (int)empty.root.getData());
	empty.remove(5);
	assertTrue(!empty.containsBFS(5));
	assertTrue(empty.containsBFS(6));
	assertEquals(1, empty.getSize());

	assertTrue(many.containsBFS(7));
	assertTrue(many.containsBFS(1));
	assertTrue(many.containsBFS(3));
	assertTrue(many.containsBFS(0));
	assertTrue(!many.containsBFS(2));

	assertTrue(one.containsBFS(5));
	assertTrue(!one.containsBFS(4));
    }

    @Test
    public void testGetHeight() {
	assertEquals(0, empty.getHeight());
	assertEquals(0, one.getHeight());
	assertEquals(2, many.getHeight());
	many.add(2);
	assertEquals(2, many.getHeight());
	many.add(2);
	assertEquals(2, many.getHeight());
	many.add(2);
	assertEquals(2, many.getHeight());
	many.add(2);
	assertEquals(3, many.getHeight());	
    }

    @Test
    public void testGetSize() {
	assertEquals(0, empty.getSize());
	assertEquals(1, one.getSize());
	assertEquals(4, many.getSize());
	one.remove(5);
	assertEquals(0, one.getSize());
	many.remove(7);
	assertEquals(3, many.getSize());
	many.remove(1);
	assertEquals(2, many.getSize());
	many.remove(0);
	assertEquals(1, many.getSize());
	many.add(6);
	assertEquals(2, many.getSize());
	many.remove(3);
	assertEquals(1, many.getSize());
	many.remove(6);
	assertEquals(0, many.getSize());
	
	empty.add(5);
	assertEquals(1, empty.getSize());
    }

    @Test
    public void testMinValue() {
	assertNull(empty.minValue());
	assertEquals(5, (int)one.minValue());
	assertEquals(0, (int)many.minValue());
	many.remove(0);
	assertEquals(1, (int)many.minValue());
	many.add(-5);
	assertEquals(-5, (int)many.minValue());
	one.add(-10);
	assertEquals(-10, (int)one.minValue());
	empty.add(7);
	assertEquals(7, (int)empty.minValue());
	many.remove(3);
	assertEquals(-5, (int)many.minValue());
    }
}
