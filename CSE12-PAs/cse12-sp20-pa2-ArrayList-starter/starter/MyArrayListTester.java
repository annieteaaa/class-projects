//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Arrays;


import org.junit.*;

public class MyArrayListTester {

  static final int DEFAULT_CAPACITY = 10;
  static final int MY_CAPACITY = 3;

  Object[] arr = new Object[10];
  Integer[] arrInts = {1,2,3};

  private MyArrayList list1, list2, list3, list4, list5;

  @Before
  public void setUp() throws Exception {
    list1 = new MyArrayList();
    list2 = new MyArrayList(DEFAULT_CAPACITY);
    list3 = new MyArrayList(MY_CAPACITY);
    list4 = new MyArrayList(arr);
    list5 = new MyArrayList<Integer>(arrInts);
  }

  @Test
  public void testNullArrCounstructor() {
    Integer[] nullarr = null;
    MyArrayList<Integer> testnull = new MyArrayList<Integer>(nullarr);
    assertEquals("Check default capacity", DEFAULT_CAPACITY, testnull.getCapacity());
  }

  @Test
  public void testInvalidConstructor() {
    try {
      MyArrayList<Integer> invalid = new MyArrayList<Integer>(-1);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Pass
    }
  }

  @Test
  public void testDefaultSize() {
    assertEquals("Check size for default constructor", 0, list1.size());
    assertEquals("Check size for constructor with given capacity of 10", 0, list2.size());
    assertEquals("Check size for constructor with given capacity of 3", 0, list3.size());
    assertEquals("Check size for constructor with given array", 10, list4.size());
    assertEquals("Check size for constructor with given int array", 3, list5.size());
  }
  
  @Test
  public void testInitialCapacity() {
    assertEquals("Check default capacity", DEFAULT_CAPACITY, list1.getCapacity());
    assertEquals("Check given capacity", MY_CAPACITY, list3.getCapacity());
  }

  @Test
  public void testAppend() {
    int[] nums = {2,4};
    list1.append(nums[0]);
    assertEquals("Check that append increments size", 1, list1.size());
    list1.append(nums[1]);
    assertEquals("Check that capacity is unchanged", DEFAULT_CAPACITY, list1.getCapacity());
  }

  @Test
  public void testDoubleCapacity() {
    MyArrayList<Integer> testDouble = new MyArrayList<Integer>(1);
    testDouble.checkCapacity(2);
    assertEquals("Check that capacity has been doubled", 2, testDouble.getCapacity());
  }

  @Test
  public void testDirectCapacity() {
    MyArrayList<Integer> testDirect = new MyArrayList<>(1);
    testDirect.checkCapacity(15);
    assertEquals("Check that capacity has been directly set", 15, testDirect.getCapacity());
  }

  @Test
  public void testNoChangeCapacity() {
    list1.checkCapacity(10);
    assertEquals("Check that capacity has not been changed", 10, list1.getCapacity());
  }

  @Test
  public void testValidInsert() {
    list2.insert(0, 2);
    assertEquals("Check that element has been inserted", 2, list2.get(0));
    assertEquals("Check that size has been changed", 1, list2.size());
    list2.insert(0, 3);
    assertEquals("Check that element has been inserted", 3, list2.get(0));
    assertEquals("Check that size has been changed", 2, list2.size());
    list2.insert(1, 4);
    assertEquals("Check that element has been inserted", 4, list2.get(1));
    assertEquals("Check that size has been changed", 3, list2.size());
  }

  @Test
  public void testNegativeInsert() {
    try {
      list2.insert(-1, 2);
      fail("Exception expected");
    }
    catch (IndexOutOfBoundsException e) {
      //pass
    }
  }

  @Test
  public void testGreaterInsert() {
    try {
      list2.insert(15, 2);
      fail("Exception expected");
    }
    catch (IndexOutOfBoundsException e) {
      //pass
    }
  }

  @Test
  public void testPrependRegular() {
    list1.append(2);
    list1.append(3);
    list1.append(4);
    list1.prepend(5);
    assertEquals("Check the first element is 5", 5, list1.get(0));
    list1.prepend(6);
    assertEquals("Check the first element is 6", 6, list1.get(0));
  }

  @Test
  public void testPrependEmpty() {
    MyArrayList<Integer> list6 = new MyArrayList<Integer>(10);
    list6.prepend(2);
    assertEquals("Check the first element is 2", 2, list6.get(0).intValue());
    MyArrayList<Integer> emptytest = new MyArrayList<Integer>(0);
    emptytest.prepend(3);
    assertEquals("Check the first element is 3", 3, emptytest.get(0).intValue());
  }

  @Test
  public void testValidGet() {
    list1.append(4);
    assertEquals("Check the 0th value is 4", 4, list1.get(0));
  }

  @Test
  public void testNegativeGet() {
    try {
    list1.append(4);
    list1.get(-1);
    fail("Exception expected");
    }
    catch (IndexOutOfBoundsException e) {
      //pass
    }
  }

  @Test
  public void testGetGreater() {
    try {
      list1.append(4);
      list1.get(1);
      fail("Exception expected");
    }
    catch (IndexOutOfBoundsException e) {
      //pass
    }
  }

  @Test
  public void testValidSet() {
    list1.append(2);
    list1.set(0, 2);
    assertEquals("Check the 0th element is 2", 2, list1.get(0));
    assertEquals("Check the set returns the right elmenet", 2, list1.set(0, 3));
    assertEquals("Check the 0th element is 3", 3, list1.get(0));
    assertEquals("Check size is the same", 1, list1.size());
  }

  @Test
  public void testNegativeSet() {
    try {
      list1.append(2);
      list1.set(-1, 3);
      fail("Exception expected");
    }
    catch (IndexOutOfBoundsException e) {
      //pass
    }
  }

  @Test
  public void testGreaterSet() {
    try {
      list1.append(2);
      list1.set(1, 3);
      fail("Exception expected");
    }
    catch (IndexOutOfBoundsException e) {
      //pass
    }
  }

  @Test
  public void testValidRemove() {
    list1.append(2);
    list1.append(3);
    assertEquals("Check return value is 2", 2, list1.remove(0));
    assertEquals("Check the value has been removed", 3, list1.get(0));
    assertEquals("Check size has been changed", 1, list1.size());
  }

  @Test
  public void testNegativeRemove() {
    try {
    list1.append(2);
    list1.remove(-1);
    fail("Exception expected");
    }
    catch (IndexOutOfBoundsException e) {
      //pass
    }
  }

  @Test
  public void testGreaterRemove() {
    try {
      list1.append(2);
      list1.remove(1);
      fail("Exception expected");
    }
    catch (IndexOutOfBoundsException e) {
      //pass
    }
  }

  @Test
  public void testTrim() {
    MyArrayList<Integer> trimthis = new MyArrayList<>(16);
    trimthis.append(3);
    trimthis.append(2);
    trimthis.trimToSize();
    assertEquals("Check the capacity is 2", 2, trimthis.getCapacity());
  }

  @Test
  public void testNoTrim() {
    MyArrayList<Integer> notrim = new MyArrayList<>(2);
    notrim.append(3);
    notrim.append(2);
    notrim.trimToSize();
    assertEquals("Check the capacity is 2", 2, notrim.getCapacity());
  }

}
