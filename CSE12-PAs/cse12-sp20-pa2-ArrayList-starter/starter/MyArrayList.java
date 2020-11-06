//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

/**
 * This file contains the MyArrayList class. By using it, we can easily store
 * elements in a dynamically changing array without the user having to trouble 
 * with modifying the array behind the scenes.
 */

/**
 * This creates an ArrayList you can manipulate by adding, removing, setting,
 * etc. data stores our elements at a given capacity and size describes how
 * many elements we have.
 */
class MyArrayList<E> implements MyList<E> {

	Object[] data;
	int size;
	private static final int DEFAULT = 10;
	private static final int DOUBLING = 2;

	public MyArrayList() {
		data = new Object[DEFAULT];
	}

	public MyArrayList(int initialCapacity) {
		if(initialCapacity < 0) {
			throw new IllegalArgumentException("Invalid capacity!");
		}
		data = new Object[initialCapacity];
	}

	public MyArrayList(E[] arr) {
		if(arr == null) {
			data = new Object[DEFAULT];
		}
		else {
			size = arr.length;
			data = new Object[size];

			//copy over arr's elements
			for(int i = 0; i < size; i++)
			{
				data[i] = arr[i];
			}
		}
	}

	/**
	 * Change capacity size.
	 *
	 * @param newCapacity The new capacity size
	 */
	public void changeCapacity(int newCapacity) {
		Object[] temp = new Object[getCapacity()];

		//store current elements
		for(int i = 0; i < data.length; i++)
		{
			temp[i] = data[i];
		}
		data = new Object[newCapacity];
		//copy back current elements
		for(int i = 0; i < temp.length; i++)
		{
			data[i] = temp[i];
		}
	}

	/**
	 * Check if current capacity is enough for the required capacity. If
	 * not, change the current capacity accordingly.
	 *
	 * @param requiredCapacity The desired capacity
	 */
	public void checkCapacity(int requiredCapacity) {
		if(getCapacity() == 0)
		{
			changeCapacity(DEFAULT);
		}
		if(getCapacity() < requiredCapacity)
		{
			if(getCapacity()*DOUBLING < requiredCapacity)
			{
				changeCapacity(requiredCapacity);
			}
			else
			{
				changeCapacity(getCapacity()*DOUBLING);
			}
		}
	}

	/**
	 * Return the current capacity.
	 *
	 * @return The current capacity
	 */
	public int getCapacity() {
		return data.length;
	}

	/**
	 * Insert an element at the given index. Throw IndexOutOfBoundsException
	 * if index is invalid.
	 *
	 * @param index The index to be inserted at
	 * @param element The element to insert at the index
	 */
	public void insert(int index, E element) {
		if(index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException("Invalid index!");
		}
		checkCapacity(size+1);
		int count = 0;
		Object[] temp = new Object[size-index];
		//store elements after index
		for(int i = index; i < size; i++)
		{
			temp[count] = data[i];
			count++;
		}
		data[index] = element;
		size++;
		count = 0;
		//put elements shifted in their correct spots
		for(int i = index+1; i < size; i++)
		{
			data[i] = temp[count];
			count++;
		}
	}

	/**
	 * Insert element at the end of the array.
	 *
	 * @param element Element to be inserted
	 */
	public void append(E element) {
		if(size == 0)
		{
			insert(0, element);
		}
		else
		{
			insert(size, element);
		}
	}

	/**
	 * Insert element at the beginning of the array.
	 *
	 * @param element Element to be inserted
	 */
	public void prepend(E element) {
		insert(0, element);
	}

	/**
	 * Return the element at a given index. Throw an 
	 * IndexOutOfBoundsException if index is invalid.
	 *
	 * @param index The index of the element
	 * @return The element at the given inde
	 */
	@SuppressWarnings("unchecked")
	public E get(int index) {
		if(index < 0 || index > size-1)
		{
			throw new IndexOutOfBoundsException("Invalid index!");
		}
		return (E)data[index];
	}

	/**
	 * Set the element at the given index to a different element. Throws an 
	 * IndexOutOfBoundsException if index is invalid.
	 *
	 * @param index The index of the element to be changed
	 * @param element The element to be set at the given index
	 * @return The element that was removed
	 */
	@SuppressWarnings("unchecked")
	public E set(int index, E element) {
		if(index < 0 || index > size-1)
		{
			throw new IndexOutOfBoundsException("Invalid index!");
		}
		Object previous = data[index];
		data[index] = element;
		return (E)previous;
	}

	/**
	 * Removed the element at a given index. Throws an 
	 * IndexOutOfBoundsException if the index is invalid.
	 *
	 * @param index The index of the element to be removed
	 * @return The element removed
	 */
	@SuppressWarnings("unchecked")
	public E remove(int index) {
		if(index < 0 || index > size-1)
		{
			throw new IndexOutOfBoundsException("Invalid index!");
		}
		Object[] temp = new Object[size-index];
		int count = 0;
		//store elements after index
		for(int i = index+1; i < size; i++)
		{
			temp[count] = data[i];
		}
		Object removed = data[index];
		size--;
		count = 0;
		//put elements shifted in their correct place
		for(int i = index; i < size; i++)
		{
			data[i] = temp[count];
		}
		return (E)removed;
	}

	/**
	 * Return the size of the current ArrayList.
	 *
	 * @return The size of the current ArrayList
	 */
	public int size() {
		return size;
	}

	/** Trim the capacity to the current size of the ArrayList.
	 */
	public void trimToSize() {
		Object[] temp = new Object[size];
		//store current elements
		for(int i = 0; i < size; i++)
		{
			temp[i] = data[i];
		}
		data = new Object[size];
		//put elements back in new array
		for(int i = 0; i < size; i++)
		{
			data[i] = temp[i];
		}
	}

}
