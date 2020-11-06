//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

/**
 * This file contains the class MyBSTTester to test MyBST
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Our tester class to test MyBSTs
 */
public class MyBSTTester {

    
    MyBST<Integer, String> test1;

   
    @Before
    public void setup() {
        test1 = new MyBST<Integer, String>();
	test1.insert(15, "h");
	test1.insert(2, "e");
test1.insert(93, "a");
test1.insert(16, "d");
    }

    @Test
    public void testOrder() {
       ArrayList<MyBST.MyBSTNode<Integer, String>> list = new ArrayList<>();
       list = test1.inorder();
       assertEquals(4, list.size());
       assertEquals(4, test1.size());
       assertEquals(2, (int)list.get(0).getKey());
       assertEquals(15, (int)list.get(1).getKey());
       assertEquals(16, (int)list.get(2).getKey());
       assertEquals(93, (int)list.get(3).getKey());

    }

}
