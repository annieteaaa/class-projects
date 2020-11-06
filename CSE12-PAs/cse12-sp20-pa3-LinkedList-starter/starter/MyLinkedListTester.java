//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu
import org.junit.*;	
import static org.junit.Assert.*;	
import java.util.LinkedList;	
import java.util.ListIterator;	
import java.util.NoSuchElementException;

/**	
 *  Title: class MyLinkedListTester	
 *  @author Annie Tong	
 *  @version 3.0 05-April-2015	
 *  Student ID: A15770705
 *  CSE12 Account: cs12sp20bcd
 *  Date: 4/17/2020
 *	
 *  Description: Test the MyLinkedList class. Test each of its inner classes as
 *  well.	
 * */	

public class MyLinkedListTester	
{	
	private MyLinkedList<Integer> empty ;	
	private MyLinkedList<Integer> one ;	
	private MyLinkedList<Integer> several ;	
	private MyLinkedList<String>  slist ;
	private MyLinkedList<String>  slist2 ;
	private MyLinkedList<String>  slist3 ;	
	static final int DIM = 5;	
	static final int FIBMAX = 30;	
	private ListIterator<Integer> iterTest;
	private MyLinkedList.Node testing ;
	private MyLinkedList.Node testing2 ;
	private MyLinkedList.Node testing3; 	

	/**	
	 * Standard Test Fixture. An empty list, a list with one entry (0) and 	
	 * a list with several entries (0,1,2)	
	 */ 	
	@Before	
	public void setUp()	
	{	
		empty = new MyLinkedList<Integer>();	
		one = new MyLinkedList<Integer>();	
		one.add(0,new Integer(0));	
		several = new MyLinkedList<Integer>() ;	
		// List: 1,2,3,...,Dim	
		for (int i = DIM; i > 0; i--)	
			several.add(0,new Integer(i));	

		// List: "First","Last"	
		slist = new MyLinkedList<String>();	
		slist.add(0,"First");	
		slist.add(1,"Last");
		slist2 = new MyLinkedList<String>();
		slist3 = new MyLinkedList<String>();
		slist3.add("Zero");
		slist3.add("One");
		slist3.add("Two");
		testing = new MyLinkedList().new Node(new Integer(0));
		testing2 = new MyLinkedList().new Node(new Integer(1));
		testing3 = new MyLinkedList().new Node(new Integer(2));	
	}	

	/** Test node construction with non-null data */
	@Test
	public void testNodeNonNullConstruct()
	{
		MyLinkedList.Node tester = new MyLinkedList().new Node(new Integer(2));
		assertEquals("Check data", new Integer(2), tester.getElement());
	}

	/** Test node construction with null data */
	@Test
	public void testNodeNullConstruct()
	{
		MyLinkedList.Node tester = new MyLinkedList().new Node(null);
		assertEquals("Check data is null", null, tester.getElement());
	}

	/** Test Node class's getElement */
	@Test
	public void testNodeGetElement()
	{
		assertEquals("Check data", new Integer(0), 
				testing.getElement());
	}

	/** Test Node class's setElement */
	@Test
	public void testNodeSetElement()
	{
		testing.setElement(new Integer(12));
		assertEquals("Check data", new Integer(12), 
				testing.getElement());
	}

	/** Test single Node's getNext */
	@Test
	public void testNodeSingleGetNext() 
	{
		assertEquals("Check next", null, testing.getNext());
	}
	
	/** Test getNext on a node pointing to another node */
	@Test
	public void testNodeTwoGetNext() 
	{
		testing.setNext(testing2);
		assertEquals("Check next", testing2, testing.getNext());
	}

	/** Test single Node's getPrev */
	@Test
	public void testNodeSingleGetPrev() 
	{
		assertEquals("Check prev", null, testing.getPrev());
	}

	/** Test getPrev on a node pointing to another node */
	@Test
	public void testNodeTwoGetPrev()
	{
		testing.setPrev(testing2);
		assertEquals("Check prev", testing2, testing.getPrev());
	}

	/** Test setElement with an int argument */
	@Test
	public void testNodeSetIntElement()
	{
		testing.setElement(2);
		assertEquals("Check data", 2, testing.getElement());
	}
		
	/** Test setElement with a String argument */
	@Test
	public void testNodeSetStringElement()
	{
		testing.setElement("Hello");
		assertEquals("Check data", "Hello", testing.getElement());
	}

	/** Test setElement with a null argument */
	@Test
	public void testNodeSetNullElement()
	{
		testing.setElement(null);
		assertEquals("Check null", null, testing.getElement());
	}

	/** Test setNext on a node with a next already */
	@Test
	public void testNodeSetNextExist()
	{
		testing.setNext(testing3);
		testing.setNext(testing2);
		assertEquals("Check next", testing2, testing.getNext());
	}

	/** Test setNext on a newly constructed node */
	@Test
	public void testNodeSetNextNew()
	{
		testing.setNext(testing2);
		assertEquals("Check next", testing2, testing.getNext());
	}

	/** Test setPrev on a newly constructed node */
	@Test
	public void testNodeSetPrevNew()
	{
		testing.setPrev(testing2);
		assertEquals("Check prev", testing2, testing.getPrev());
	}

	/** Test setPrev on a node with a prev already */
	@Test
	public void testNodeSetPrevExist()
	{
		testing.setPrev(testing3);
		testing.setPrev(testing2);
		assertEquals("Check prev", testing2, testing.getPrev());
	}

	/** Test adding a string to empty List */
	@Test
	public void testAddStringEmpty()
	{
		slist2.add("Byebye");
		assertEquals("Check string", "Byebye", slist2.get(0));
	}

	/** Test adding a string to a one elmeent List */
	@Test
	public void testAddStringOne()
	{
		slist2.add("Hello");
		slist2.add("Bye-bye");
		assertEquals("Check string", "Bye-bye", slist2.get(1));
	}

	/** Test adding multiple times to an empty List */
	@Test
	public void testAddMultipleString()
	{
		slist2.add("a");
		slist2.add("b");
		slist2.add("c");
		assertEquals("Check string", "a", slist2.get(0));
		assertEquals("Check string", "b", slist2.get(1));
		assertEquals("Check string", "c", slist2.get(2));
	}

	/** Test adding a null element to list */
	@Test
	public void testAddNull()
	{
		try {
			slist.add(null);
			fail("Exception expected");
		}
		catch (NullPointerException e) {
			//pass
		}
	}

	/** Test add returns true */
	@Test
	public void testAddTrue()
	{
		assertTrue(one.add(1));
		assertTrue(one.add(2));
		assertTrue(one.add(3));
	}

	/** Test adding String to empty list using index 0 */
	@Test
	public void testAddZeroIndex()
	{
		slist2.add(0, "Hello");
		assertEquals("Check 0th element", "Hello", slist2.get(0));
	}

	/** Test add string to a three element list using index 4 */
	@Test
	public void testAddGreaterThanSize()
	{
		try {
			slist3.add(4, "Four");
			fail("Exception expected");
		}
		catch (IndexOutOfBoundsException e) {
			//pass
		}
	}

	/** Test adding to index -1 */
	@Test
	public void testAddNegativeIndex()
	{
		try {
			slist3.add(-1, "Negative");
			fail("Exception expected");
		}
		catch (IndexOutOfBoundsException e) {
			//pass
		}
	}

	/** Test adding to moddle */
	@Test
	public void testAddMiddle()
	{
		slist.add(1, "Middle");
		assertEquals("Check index 1", "Middle", slist.get(1));
	}

	/** Test adding to head */
	@Test
	public void testAddHead()
	{
		slist3.add(0, "Head");
		assertEquals("Check head", "Head", slist3.get(0));
	}

	/** Test adding to tail */
	@Test
	public void testAddTail()
	{
		slist3.add(3, "Tail");
		assertEquals("Check tail", "Tail", slist3.get(3));
	}

	/** Test adding multiple times randomly */
	@Test
	public void testAddMultiple()
	{
		slist2.add(0, "Hello");
		slist2.add("Bye");
		slist2.add("Bonjour");
		slist2.add(1, "Honhon");
		slist2.add(3, "Baguette");
		assertEquals("Check index 0", "Hello", slist2.get(0));
		assertEquals("Check index 1", "Honhon", slist2.get(1));
		assertEquals("Check index 2", "Bye", slist2.get(2));
		assertEquals("Check index 3", "Baguette", slist2.get(3));
		assertEquals("Check index 4", "Bonjour", slist2.get(4));
	}

	/** Test adding null with index */
	@Test
	public void addNullIndex()
	{
		try {
			slist2.add(0, null);
			fail("Exception expected");
		}
		catch (NullPointerException e) {
			//pass
		}
	}

	/** Test clearing non-empty list */
	@Test
	public void clearNonEmpty()
	{
		try {
			slist3.clear();
			assertEquals(0, slist3.size());
			slist3.get(0);
			fail("Exception expected");
		}
		catch (IndexOutOfBoundsException e) {
			//pass
		}
	}

	/** Test clearing 1-element list */
	@Test
	public void clearOneElement()
	{
		try {
			one.add("Hello");
			one.clear();
			assertEquals(0, one.size());
			one.get(0);
			fail("Exception expected");
		}
		catch (IndexOutOfBoundsException e) {
			//pass
		}
	}

	/** Test clearing empty list */
	@Test
	public void clearEmpty()
	{
		try {
			slist2.clear();
			assertEquals(0, slist2.size());
			slist2.get(0);
			fail("Exception expected");
		}
		catch (IndexOutOfBoundsException e) {
			//pass
		}
	}

	/** Test head and tail nodes */
	@Test
	public void headTail()
	{
		assertEquals("Check pointer", slist2.tail, 
				slist2.head.getNext());
		assertEquals("Check null", null, slist2.head.getElement());
		assertEquals("Check null", null, slist2.tail.getElement());
	}

	/** Test nelems initially 0 */
	@Test
	public void checkNelem()
	{
		assertEquals("Check nelem=0", 0, slist2.size());
	}

	/** Test isEmpty on non-empty list */
	@Test
	public void checkNonEmptyEmpty()
	{
		assertFalse("Check not empty", slist3.isEmpty());
	}

	/** Test isEmpty on 1 element list */
	@Test
	public void checkOneEmpty()
	{
		slist2.add("Hello");
		assertFalse("Check not empty", slist2.isEmpty());
	}

	/** Test isEmpty on empty list */
	@Test
	public void checkEmptyEmpty()
	{
		assertTrue("Check empty", slist2.isEmpty());
	}

	/** Test removing int in Integer List */
	@Test
	public void testRemoveInt()
	{
		try {
			one.add(0);
			one.remove(1);
			one.get(1);
			fail("Exception expected");
		}
		catch (IndexOutOfBoundsException e) {
			//pass
		}
	}

	/** Test remove the first int in Integer List */
	@Test
	public void testRemoveFirstInt()
	{
		empty.add(1);
		empty.add(2);
		empty.add(3);
		empty.remove(0);
		assertEquals("Check first value", (int)2, (int)empty.get(0));
	}

	/** Test remove last int in Integer List */
	@Test
	public void testRemoveLastInt()
	{
		empty.add(1);
		empty.add(2);
		empty.add(3);
		empty.remove(2);
		try {
			empty.get(2);
			fail("Exception expected");
		}
		catch (IndexOutOfBoundsException e) {
			//pass
		}
	}

	/** Test removing when list is empty */
	@Test
	public void testRemoveEmpty()
	{
		try {
			empty.remove(0);
			fail("Exception expected");
		}
		catch (IndexOutOfBoundsException e) {
			//pass
		}
	}

	/** Test removing greater than size */
	@Test
	public void testRemoveGreater()
	{
		try {
			slist3.remove(3);
			fail("Exception expected");
		}
		catch (IndexOutOfBoundsException e) {
			//pass
		}
	}

	/** Test removing index -1 */
	@Test
	public void testRemoveNegative()
	{
		try {
			slist3.remove(-1);
			fail("Exception expected");
		}
		catch (IndexOutOfBoundsException e) {
			//pass
		}
	}

	/** Test setting an int to another int in Integer MLL */
	@Test
	public void testSetValidInt()
	{
		several.set(1, 3);
		assertEquals("Check index 1 data", (int)3, (int)several.get(1));
	}

	/** Test set the first int to another int in Integer MLL */
	@Test
	public void testSetValidFirstInt()
	{
		several.set(0, 4);
		assertEquals("Check index 0 data", (int)4, (int)several.get(0));
	}

	/** Test set the last int to another int in Integer MLL */
	@Test
	public void testSetValidLastInt()
	{
		one.add(6);
		one.set(1, 8);
		assertEquals("Check index 1", (int)8, (int)one.get(1));
	}

	/** Test setting index 0 in empty list */
	@Test
	public void testEmptySet()
	{
		try {
			empty.set(0, 5);
			fail("Exception expected");
		}
		catch (IndexOutOfBoundsException e) {
			//pass
		}
	}

	/** Test setting index greater than size - 1 */
	@Test
	public void testSetGreater()
	{
		try {
			slist3.set(3, "Hello");
			fail("Exception expected");
		}
		catch (IndexOutOfBoundsException e) {
			//pass
		}
	}

	/** Test setting index less than 0 */
	@Test
	public void testSetNegative()
	{
		try {
			slist3.set(-1, "Bye");
			fail("Exception expected");
		}
		catch (IndexOutOfBoundsException e) {
			//pass/
		}
	}

	/** Test setting to null */
	@Test
	public void testSetNull()
	{
		try {
			one.add(4);
			one.set(1, null);
			fail("Exception expected");
		}
		catch (NullPointerException e) {
			//pass
		}
	}

	/** Test calling size on non-empty list */
	@Test
	public void testNonEmptySize()
	{
		assertEquals("Check size", 3, slist3.size());
	}

	/** Test calling size on one-element list */
	@Test
	public void testOneSize()
	{
		assertEquals("Check size", 1, one.size());
	}

	/** Test calling size on empty list */
	@Test
	public void testEmptySize()
	{
		assertEquals("Check size", 0, empty.size());
	}

	/** Test if first node of the lists are correct */	
	@Test	
	public void testGetFirst()	
	{	
		assertEquals("Check 0",new Integer(0),one.get(0)) ;	
		assertEquals("Check 0",new Integer(1),several.get(0)) ;	
	}	

	/** Test if size of lists are correct */	
	@Test	
	public void testListSize()	
	{	
		assertEquals("Check Empty Size",0,empty.size()) ;	
		assertEquals("Check One Size",1,one.size()) ;	
		assertEquals("Check Several Size",DIM,several.size()) ;	
	}	

	/** Test setting a specific entry */	
	@Test	
	public void testSet()	
	{	
		slist.set(1,"Final");	
		assertEquals("Setting specific value", "Final",slist.get(1));	
	}	

	/** Test isEmpty */	
	@Test	
	public void testEmpty()	
	{	
		assertTrue("empty is empty",empty.isEmpty()) ;	
		assertTrue("one is not empty",!one.isEmpty()) ;	
		assertTrue("several is not empty",!several.isEmpty()) ;	
	}	

	/** Test out of bounds exception on get */	
	@Test	
	public void testGetException()	
	{	
		try 	
		{	
			empty.get(0);	
			// This is how you can test when an exception is supposed 	
			// to be thrown	
			fail("Should have generated an exception");  	
		}	
		catch(IndexOutOfBoundsException e)	
		{	
			//  normal	
		}	
	}	

	/** Test iterator on empty list and several list */	
	@Test	
	public void testIterator()	
	{	
		int counter = 0 ;	
		ListIterator<Integer> iter;	
		for (iter = empty.listIterator() ; iter.hasNext(); )	
		{	
			fail("Iterating empty list and found element") ;	
		}	
		counter = 0 ;	
		for (iter = several.listIterator() ; iter.hasNext(); iter.next())	
			counter++;	
		assertEquals("Iterator several count", counter, DIM);	
	}	
	
	/** Test listiterator adding to the middle of nonempty list */
	@Test
	public void testIteratorAddMiddle() {
		ListIterator<Integer> iter = several.listIterator();
		iter.next();
		iter.next();
		iter.add(42);
		assertEquals((int)42, (int)iter.previous());
		iter.add(420);
		assertEquals((int)420, (int)iter.previous());
	}

	/** Test listiterator adding on first index of nonempty list */
	@Test
	public void testIteratorAddFirst() {
		ListIterator<Integer> iter = several.listIterator();
		iter.add(42);
		assertEquals((int)42, (int)iter.previous());
	}

	/** Test listiterator adding on last index of nonempty list */
	@Test
	public void testIteratorAddLast() {
		ListIterator<Integer> iter = one.listIterator();
		iter.next();
		iter.add(42);
		assertEquals((int)42, (int)iter.previous());
	}

	/** Test listiterator adding to empty list */
	@Test
	public void testIteratorAddToEmpty() {
		ListIterator<Integer> iter = empty.listIterator();
		iter.add(42);
		assertEquals((int)42, (int)iter.previous());
	}

	/** Test listiterator adding a null object */
	@Test
	public void testIteratorAddNull() {
		ListIterator<Integer> iter = one.listIterator();
		iter.next();
		try {
			iter.add(null);
			fail("Exception expected");
		}
		catch (NullPointerException e) {
			//pass
		}
	}

	/** Test listiterator constructor on nonempty List */
	@Test
	public void testIteratorConstructNonEmpty() {
		ListIterator<Integer> iter = one.listIterator();
		iter.next();
		try {
			iter.next();
			fail("Exception expected");
		}
		catch (NoSuchElementException e) {
			//pass
		}
	}

	/** Test listiterator constructor on empty list */
	@Test
	public void testIteratorConstructEmpty() {
		ListIterator<Integer> iter = empty.listIterator();
		try {
			iter.next();
			fail("Exception expected");
		}
		catch (NoSuchElementException e) {
			//pass
		}
	}

	/** Test listiterator hasnext method while it goes through the empty	
	 * and one list	
	 */	
	@Test 	
	public void testIteratorHasNext() {	

		ListIterator<Integer> iter = empty.listIterator();	
		ListIterator<Integer> iter1 = one.listIterator();	
		ListIterator<Integer> iter2 = several.listIterator();

		assertTrue(!iter.hasNext());	
		assertTrue(iter1.hasNext());			
		
		assertTrue(iter2.hasNext());
		iter2.next();
		assertTrue(iter2.hasNext());
		iter2.next();
		assertTrue(iter2.hasNext());
		iter2.next();
		assertTrue(iter2.hasNext());
	}

	/** Test listiterator hasPrev method */
	@Test
	public void testIteratorHasPrev() {

		ListIterator<Integer> iter = empty.listIterator();	
		ListIterator<Integer> iter2 = several.listIterator();

		assertTrue(!iter.hasPrevious());
		assertTrue(!iter2.hasPrevious());
		iter2.next();
		assertTrue(iter2.hasPrevious());
		iter2.next();
		assertTrue(iter2.hasPrevious());
		iter2.next();
		assertTrue(iter2.hasPrevious());

	}

	/** Test listiterator next method */	
	@Test	
	public void testIteratorNext() {	

		iterTest = several.listIterator();	

		assertEquals(new Integer(1),iterTest.next());	
		assertEquals(new Integer(2),iterTest.next());	
		assertEquals(new Integer(3),iterTest.next());	
		assertEquals(new Integer(4),iterTest.next());	
		assertEquals(new Integer(5),iterTest.next());					try {
			iterTest.next();
			fail("Exception expected");
		}
		catch (NoSuchElementException e) {
			//pass
		}

		iterTest = empty.listIterator();
		try {
			iterTest.next();
			fail("Exception expected");
		}
		catch (NoSuchElementException e) {
			//pass
		}
	}	
	
	/** Test nextIndex method of list iterator */	
	@Test	
	public void testIteratorNextIndex() {	
		iterTest = several.listIterator();	

		//Test the nextIndex method at the start of and end 	
		//of the list as well as middle of the list	
		assertEquals(0, iterTest.nextIndex());	

		iterTest.next();	
		iterTest.next();	
		iterTest.next();	

		assertEquals(3, iterTest.nextIndex());			

		iterTest.next();	
		iterTest.next();	

		assertEquals(5, iterTest.nextIndex());

		iterTest = empty.listIterator();

		assertEquals(0, iterTest.nextIndex());
	}	

	/** Test listiterator previous method */
	@Test
	public void testIteratorPrevious() {
		iterTest = several.listIterator();

		iterTest.next();
		iterTest.next();
		iterTest.next();
		assertEquals(new Integer(3), iterTest.previous());
		assertEquals(new Integer(2), iterTest.previous());
		assertEquals(new Integer(1), iterTest.previous());

		try {
			iterTest.previous();
			fail("Exception expected");
		}
		catch (NoSuchElementException e) {
			//pass
		}

		iterTest = empty.listIterator();

		try {
			iterTest.previous();
			fail("Exception expected");
		}
		catch (NoSuchElementException e) {
			//pass
		}
	}

	/** Test previousIndex method of listiterator */
	@Test
	public void testIteratorPreviousIndex() {
		//test for nonempty list
		iterTest = several.listIterator();
		assertEquals(-1, iterTest.previousIndex());
		iterTest.next();
		assertEquals(0, iterTest.previousIndex());
		iterTest.next();
		assertEquals(1, iterTest.previousIndex());

		//Test for empty list
		iterTest = empty.listIterator();
		assertEquals(-1, iterTest.previousIndex());
	}

	/** Test the remove method of list iterator */	
	@Test 	
	public void testIteratorRemove() {	
		iterTest = several.listIterator(); 	

		//Test whether removes method work after next method		
		iterTest.next();	
		iterTest.next();	
		iterTest.remove();	

		assertEquals(new Integer(1), iterTest.previous());	

		//Test whether remove method work after previous method	
		iterTest.next();	
		iterTest.next();	
		iterTest.previous();	
		iterTest.remove();	

		assertEquals(new Integer(4), iterTest.next());	

		try {
			iterTest.remove();
			iterTest.remove();
			fail("Exception expected");
		}
		catch (IllegalStateException e) {
			//pass
		}

		try {
			iterTest = empty.listIterator();
			iterTest.remove();
			fail("Exception expected");
		}
		catch (IllegalStateException e) {
			//pass
		}

	}
	
	/** Test listiterator set method */
	@Test
	public void testIteratorSet() {
		iterTest = several.listIterator();
		iterTest.next();
		iterTest.set(7);
		assertEquals((int)7, (int)iterTest.previous());
		iterTest.set(8);
		assertEquals((int)8, (int)iterTest.next());

		//set on empty list
		iterTest = empty.listIterator();
		try {
			iterTest.set(3);
			fail("Exception expected");
		}
		catch (IllegalStateException e) {
			//pass
		}

		try {
			iterTest = several.listIterator();
			iterTest.next();
			iterTest.next();
			iterTest.remove();
			iterTest.set(2);
			fail("Exception expected");
		}
		catch (IllegalStateException e) {
			//pass
		}

		try {
			iterTest = several.listIterator();
			iterTest.next();
			iterTest.set(null);
			fail("Exception expected");
		}
		catch (NullPointerException e) {
			//pass
		}
	}	
	
		

}
