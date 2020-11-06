//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class MyHashTableTester {

	private MyHashTable hashTable1;
	private MyHashTable hashTable2;
	private MyHashTable hashTable3;

	@Before
	public void setUp()
	{
		hashTable1 = new MyHashTable(1);
		hashTable2 = new MyHashTable(13);
		hashTable3 = new MyHashTable(5);
	}

	/** 
	 * Create MyHashTable with size 10
	 */
	@Test
	public void testConstructor()
	{
		MyHashTable tester = new MyHashTable(10);
	}

	/**
	 * Test Constructor instance variables
	 */
	@Test
	public void testInstanceConstruct()
	{
		try {
			MyHashTable tester = new MyHashTable(-10);
			fail("Exception expected");
		}
		catch (IllegalArgumentException e) {
			//pass
		}
		try {
			MyHashTable tester = new MyHashTable(10, null);
			fail("Exception expected");
		}
		catch (NullPointerException e) {
			//pass
		}
		MyHashTable tester = new MyHashTable(10, "fakeFile");
		assertEquals("Testing array capacity", 10, tester.array.length);
		assertEquals("Testing nelems value", 0, tester.nelems);
		assertEquals("Testing expand value", 0, tester.expand);
		assertEquals("Testing collision value", 0, tester.collision);
		assertEquals("Testing statsFileName value", "fakeFile", 
				tester.statsFileName);
		assertEquals("Testing printStats value", true, 
				tester.printStats);
		MyHashTable tester2 = new MyHashTable(11);
		assertEquals("Testing array capacity", 11, 
				tester2.array.length);
		assertEquals("Testing nelems value", 0, tester2.nelems);
		assertEquals("Testing expand value", 0, tester2.expand);
		assertEquals("Testing collision value", 0, tester2.collision);
		assertEquals("Testing statsFileName value", null, 
				tester2.statsFileName);
		assertEquals("Testing printStats value", false, 
				tester2.printStats);
	}

	/**
	 * Insert a String into the empty hash table
	 */
	@Test
	public void testInsert()
	{
		assertEquals("Checking insert", true, hashTable1.insert("abc"));
		assertEquals("Checking contains after insert", true,
		hashTable1.contains("abc"));
	}

	/**
	 * Testing the insert method
	 */
	@Test
	public void testMoreInsert()
	{
		hashTable2.insert("abcde");
		assertTrue(hashTable2.array[hashTable2.hashString("abcde")]
				.contains("abcde"));
		
		try {
			hashTable2.insert(null);
			fail("Exception expected");
		}
		catch (NullPointerException e) {
			//pass
		}

		assertFalse(hashTable2.insert("abcde"));
		assertTrue(hashTable2.insert("abc"));
		assertEquals("Check size", 2, hashTable2.getSize());
		
		hashTable3.insert("abc");
		hashTable3.insert("myg");
		hashTable3.insert("deh");

		assertEquals("Check collisions", 1, hashTable3.collision);

		//Check they're put in the right buckets
		assertTrue(hashTable3.array[2].contains("deh"));
		assertTrue(hashTable3.array[2].contains("abc"));

		hashTable3.insert("def");

		//check rehash has been called
		assertEquals("Check capacity", 11, hashTable3.array.length);

		hashTable3.insert("abcedfghijk");
		assertEquals("Check size", 5, hashTable3.getSize());
	}

	/**
	 * Try to insert a null value
	 */
	@Test
	public void testInsertNull()
	{
		try{
			hashTable1.insert(null);
			fail("Expected an NullPointerException to be thrown");
		} catch(NullPointerException e) {
			assertEquals(e.getClass().getName(), 
					"java.lang.NullPointerException");
		}
	}

	/**
	 * Delete a String from hash table with a single element
	 */
	@Test
	public void testDelete()
	{
		hashTable1.insert("abc");
		assertEquals("Checking delete", true, hashTable1.delete("abc"));
		assertEquals("Checking contains after delete", false,
		hashTable1.contains("abc"));
	}

	/**
	 * Test delete correctly without removing other Strings and test more 
	 * delete
	 */
	@Test
	public void testMoreDelete()
	{
		try {
			hashTable2.delete(null);
			fail("Exception expected");
		}
		catch (NullPointerException e) {
			//pass
		}
		hashTable2.insert("abcdef");
		assertTrue(hashTable2.delete("abcdef"));
		assertEquals("Checking size", 0, hashTable2.getSize());
		assertNull(hashTable2.array[hashTable2.hashString("abcdef")]);
		hashTable2.insert("abc");
		hashTable2.insert("abcde");
		assertTrue(hashTable2.delete("abc"));
		assertTrue(hashTable2.contains("abcde"));
		assertTrue(hashTable2.delete("abcde"));
		assertNull(hashTable2.array[1]);
	}

	/**
	 * Search for a String that is the only String in the hash table
	 */
	@Test
	public void testContains()
	{
		hashTable1.insert("abc");
		assertEquals("Checking contains after insert", true, 
				hashTable1.contains("abc"));
	}

	/**
	 * Test printing out hash table with a single element
	 */
	@Test
	public void testPrint()
	{
		hashTable3.insert("abc");
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		hashTable3.printTable();
		String expected = "0:\n1:\n2: abc\n3:\n4:".replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
		assertEquals(expected, os.toString());
	}

	/**
	 * Test contains method is correct and doesn't change hash table.
	 * Check contains works correctly
	 */
	@Test
	public void testMoreContains()
	{
		hashTable2.insert("abc");
		assertEquals("Checking contains after insert", true, 
				hashTable2.contains("abc"));
		assertEquals("Checking size", 1, hashTable2.getSize());
		assertEquals("Checking capacity", 13, hashTable2.array.length);
		assertNotNull(hashTable2.array[hashTable2.hashString("abc")]);
		try {
			hashTable2.contains(null);
			fail("Exception expected");
		}
		catch (NullPointerException e) {
			//pass
		}
		assertFalse(hashTable2.contains("abcdef"));
		assertTrue(hashTable2.contains("abc"));
	}

	/**
	 * Testing getSize method
	 */
	@Test
	public void testGetSize()
	{
		hashTable1.insert("abc");
		hashTable1.insert("pqr");
		hashTable1.insert("xyz");
		assertEquals("Checking getSize", Integer.valueOf(3),
		Integer.valueOf(hashTable1.getSize()));
		hashTable1.delete("abc");
		assertEquals("Checking getSize", Integer.valueOf(2),
		Integer.valueOf(hashTable1.getSize()));
		hashTable1.contains("pqr");
		assertEquals("Checking getSize", Integer.valueOf(2),
		Integer.valueOf(hashTable1.getSize()));
	}

	/**
	 * Testing rehash method
	 */
	@Test
	public void testRehash()
	{
		MyHashTable tester = new MyHashTable(10);
		tester.insert("abc");
		tester.insert("defg");
		tester.insert("hello");
		tester.insert("ab");
		tester.insert("bye");
		tester.insert("hwelfkw");
		tester.insert("hooglaboogla");
		assertEquals("Checking rehashed size", 23, tester.array.length);
		assertTrue(tester.contains("abc"));
		assertTrue(tester.contains("defg"));
		assertTrue(tester.contains("hello"));
		assertTrue(tester.array[tester.hashString("abc")]
				.contains("abc"));
	}

	/**
	 * Hash the String "CSE12 Rocks!"
	 */
	@Test
	public void testHash()
	{
		int bucket = (hashTable2.hashString("CSE12 Rocks!"));
		assertEquals("Checking hash value", 11,
				hashTable2.hashString("CSE12 Rocks!"));
		hashTable2.insert("CSE12 Rocks!");
		assertTrue(hashTable2.array[bucket].contains("CSE12 Rocks!"));
	}

	/**
	 * Test hashString against handwritten values
	 */
	@Test
	public void testHashString()
	{
		assertEquals("Checking value", 1, 
				hashTable2.hashString("abc"));
		assertEquals("Checking value", 4, 
				hashTable2.hashString("defgh"));
	}
}
