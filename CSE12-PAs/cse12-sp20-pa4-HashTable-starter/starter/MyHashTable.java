//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Create a HashTable which can be used to quickly insert, remove, and check if
 * elements exist. This HashTable takes String values. You can set it up with
 * an initial capacity.
 */
public class MyHashTable implements MyHashTableInterface {
	//Constant used to double the size and do addition
	final static int CONSTANT_TWO = 2;

	LinkedList<String>[] array;//Array that stores linkedlists
	int nelems;  //Number of element stored in the hash table
	int expand;  //Number of times that the table has been expanded
	int collision;  //Number of collisions since last expansion
	String statsFileName;     //FilePath for the file to write statistics
	//upon every rehash
	boolean printStats = false;   //Boolean to decide whether to write
	//stats to file or not after rehashing

	//Feel free to add more :)

	public MyHashTable(int size) {

		if(size < 0) {
			throw new IllegalArgumentException();
		}
		array = new LinkedList[size];
		printStats = false;
	}

	public MyHashTable(int size, String fileName){
		if(size < 0) {
			throw new IllegalArgumentException();
		}
		if(fileName == null) {
			throw new NullPointerException();
		}
		array = new LinkedList[size];
		printStats = true;
		statsFileName = fileName;
	}

	/**
	 * Insert a value into the table. Throw exception if value is null
	 * @param value The String in question
	 * @return true if successfully inserted
	 */
	@Override
	public boolean insert(String value) {
		if(value == null) {
			throw new NullPointerException();
		}
		if((double)(nelems+1)/array.length >= (double)2/3) {
			rehash();
		}
		if(contains(value)) {
			return false;
		}
		else {
			int bucket = (hashString(value));
			if(array[bucket] == null) {
				array[bucket] = new LinkedList<String>();
			}
			else {
				collision++;
			}
			nelems++;
			array[bucket].add(value);
			return true;
		}
	}

	/**
	 * Delete a value if it exists. Throw exception if value is null
	 * @param value The String in question
	 * @return true if successfully deleted
	 */
	@Override
	public boolean delete(String value) {

		if(value == null) {
			throw new NullPointerException();
		}
		if(!contains(value)) {
			return false;
		}
		int bucket = hashString(value);
		if(array[bucket].size() == 1) {
			array[bucket] = null;
			nelems--;
			return true;
		}
		else {
			if(array[bucket].remove(value)) {
				nelems--;
				return true;
			}
			else {
				return false;
			}
		}
	}

	/**
	 * Check if hash table contains a value. Throw exception if value is
	 * null
	 * @param value the String in question
	 * @return true if table contains the STring
	 */
	@Override
	public boolean contains(String value) {
		if(value == null) {
			throw new NullPointerException();
		}
		int bucket = hashString(value);
		if(array[bucket] == null) {
			return false;
		}
		else {
			return array[bucket].contains(value);
		}
	}

	/**
	 * Print the current hash table
	 */
	@Override
	public void printTable() {
		for(int i = 0; i < array.length; i++) {
			System.out.print(i + ":");
			if(!(array[i] == null)) {
				System.out.print(" ");
				String bucketlist = "";
				for(String value: array[i]) {
					bucketlist += value + ", ";
				}
				System.out.print(bucketlist.substring(0, 
							bucketlist.length()-2));
				if(i != array.length - 1) {
					System.out.println();
				}
			}
			else if(i != array.length - 1) {
				System.out.println();
			}
		}
	}

	/**
	 * Return number of elements
	 * @return Number of elements
	 */
	@Override
	public int getSize() {
		return nelems;
	}

	/**
	 * Resize the container to a prime number and rehash all elements
	 */
	@Override
	@SuppressWarnings( "unchecked" )
	public void rehash() {
		if(printStats == true) {
			printStatistics();
		}
		int newSize = primeGen();
		LinkedList<String>[] temp = new LinkedList[array.length];
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				temp[i] = new LinkedList<String>();
				for(String value: array[i]) {
					temp[i].add(value);
				}
			}
		}
		array = new LinkedList[newSize];
		for(int i = 0; i < temp.length; i++) {
			if(!(temp[i] == null)) {
				for(String value: temp[i]) {
					int bucket = hashString(value);
					if(array[bucket] == null) {
						array[bucket] = new 
							LinkedList<String>();
					}
					array[bucket].add(value);
				}
			}
		}
		expand++;
		collision = 0;
	}

	/**
	* Calculate the hash value of a given string
	* @param str the string value
	* @return the hash value
	*/
	public int hashString(String str){
		int h = 0;
		for(int i = 0; i < str.length(); i++) {
			int highorder = h & 0xf8000000;
			h = h << 5;
			h = h ^ (highorder >>> 27);
			h = h ^ (str.charAt(i));
		}
		return Math.abs(h)%array.length;
	}

	/**
	* Print statistics to the given file.
	* @return True if successfully printed statistics, false if the file
	*         could not be opened/created.
	*/
	@Override
	public boolean printStatistics(){
		PrintStream out;
		try {
			out = new PrintStream( new FileOutputStream( 
						this.statsFileName,
			true ) );
		} catch(FileNotFoundException e) {
			return false;
		}
		out.print(this.expand + " resizes, ");//Print resize times
		//Calculate the load factor
		double loadFactor = ( (double) nelems / array.length );
		DecimalFormat df = new DecimalFormat("#.##"); //Print the load 
			//factor
		out.print("load factor " + df.format( loadFactor ) + ", ");
		out.print(this.collision + " collisions, "); //Print collision 
			//times
		int length = 0;
		for(int i = 0; i < this.array.length; i++){
			if(this.array[i] != null && this.array[i].size() > 
					length)
			length = this.array[i].size();
		}
		//Print the length of the longest chain
		out.println(length + " longest chain");
		out.close();
		return true;
	}

	/**
	* Generate a prime number that is close to the double of current array
	* size
	* @return a prime number used as array size
	*/
	private int primeGen(){
		boolean isPrime = false;
		int num = array.length*CONSTANT_TWO;//Double the size

		/*
		* Generate next prime number that is greater than the double of
		* current array size
		*/
		while(!isPrime){
			num++;
			/*
			* Try divides the number with all numbers greater than 
			* two and
			* less than or equal to the square root of itself
			*/
			for(int divisor = CONSTANT_TWO; divisor <= 
					Math.sqrt(num);
			divisor++){
				if(num % divisor == 0)//The number is divisible
				break;//No need for further testing, break 
					//inner loop
				if(divisor == (int)Math.sqrt(num))//The number 
					//is indivisible
				isPrime = true;//Then it is a prime
			}
		}
		return num;
	}


}
