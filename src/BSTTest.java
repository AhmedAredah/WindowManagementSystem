//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.


import org.junit.Test;

import student.TestCase;


// -------------------------------------------------------------------------
/**
 * Test the BST functions
 *
 * @author CS Staff
 * @version August, 2018
 */
public class BSTTest extends TestCase {
    
    private BST<Integer> database;

    /**
     * setUp the condition.
     */
    public void setUp() {
        database = new BST<Integer>();
    }

    private BST<Integer> insert(BST<Integer> theDatabase, Integer[] in2) {
        for (int i = 0; i < in2.length; i++) {
            theDatabase.insert(in2[i]);
        }
        return theDatabase;
    }
    
    private Integer[][] in = {{2, 1, 3, 4}, {4, 1, 7, 2}};

    /**
     * check the basics.
     */
    @Test
    public void testBSTBasic() {
        BST<Integer> theTree = new BST<Integer>();
        assertNotNull(theTree);
    }
    
    
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTInsert0() {
        database = insert(database, in[0]);
        assertEquals(database.toString(), "Node has depth 1, Value (1)\n"
                + "Node has depth 0, Value (2)\n"
                + "Node has depth 1, Value (3)\n"
                + "Node has depth 2, Value (4)\n");
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTInsert1() {
        database = insert(database, in[1]);
        assertEquals(database.toString(), "Node has depth 1, Value (1)\n"
                + "Node has depth 2, Value (2)\n"
                + "Node has depth 0, Value (4)\n"
                + "Node has depth 1, Value (7)\n");
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTclear() {
        database = insert(database, in[1]);
        database.clear();
        assertEquals(database.toString(), "");
    }
    
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTsize() {
        database = insert(database, in[1]);
        assertEquals(database.size(), in[1].length);
    }
    
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTEmpty() {
        database = insert(database, in[1]);
        assertFalse(database.isEmpty());
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTEmpty1() {
        database = insert(database, in[1]);
        database.clear();
        assertTrue(database.isEmpty());
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTRemove1() {
        database = insert(database, in[1]);
        systemOut().clearHistory();
        database.print();
        assertEquals(systemOut().getHistory(), "BST dump:\n"
                + "Node has depth 1, Value (1)\n"
                + "Node has depth 2, Value (2)\n"
                + "Node has depth 0, Value (4)\n"
                + "Node has depth 1, Value (7)\n"
                + "BST size is: 4\n");
        database.remove(in[1][0]);
        systemOut().clearHistory();
        database.print();
        assertEquals(systemOut().getHistory(), "BST dump:\n"
                + "Node has depth 1, Value (1)\n"
                + "Node has depth 0, Value (2)\n"
                + "Node has depth 1, Value (7)\n"
                + "BST size is: 3\n");
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTRemove2() {
        database = insert(database, in[1]);
        database.remove(in[1][1]);
        assertEquals(database.toString(), "Node has depth 1, Value (2)\n"
                + "Node has depth 0, Value (4)\n"
                + "Node has depth 1, Value (7)\n");
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTRemove3() {
        database = insert(database, in[1]);
        database.remove(in[1][2]);
        assertEquals(database.toString(), "Node has depth 1, Value (1)\n"
                + "Node has depth 2, Value (2)\n"
                + "Node has depth 0, Value (4)\n");
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTPrint() {
        database = insert(database, in[1]);
        systemOut().clearHistory();
        database.print();
        assertEquals(systemOut().getHistory(), "BST dump:\n"
                + "Node has depth 1, Value (1)\n"
                + "Node has depth 2, Value (2)\n"
                + "Node has depth 0, Value (4)\n"
                + "Node has depth 1, Value (7)\n"
                + "BST size is: 4\n");
    }
    
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTPrint1() {
        database = insert(database, in[1]);
        database.clear();
        systemOut().clearHistory();
        database.print();
        assertEquals(systemOut().getHistory(), "BST dump:\n"
                + "Node has depth 0, Value (null)\n"
                + "BST size is: 0\n");
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTPrint2() {
        database = insert(database, in[1]);
        systemOut().clearHistory();
        for (BSTIterator<Integer> i = database
                .iterator(); i.hasNext();) {
            System.out.println(i.next().value().toString());
        }
        assertEquals(systemOut().getHistory(), "1\n"
                + "2\n"
                + "4\n"
                + "7\n");
    }
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTIterator0() {
        database = insert(database, in[1]);
        assertNotNull(database.iterator());
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTIterator1() {
        database = insert(database, in[1]);
        assertTrue(database.iterator().hasNext());
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTIterator2() {
        database = insert(database, in[1]);
        database.clear();
        assertFalse(database.iterator().hasNext());
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTIterator3() {
        database = insert(database, in[1]);
        assertEquals(database.iterator().next().value().compareTo(1), 0);
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTFind0() {
        database = insert(database, in[1]);
        assertEquals(database.find(in[1][0]).compareTo(in[1][0]), 0);
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTFind1() {
        database = insert(database, in[1]);
        assertEquals(database.find(in[1][1]).compareTo(in[1][1]), 0);
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTFind2() {
        database = insert(database, in[1]);
        assertEquals(database.find(in[1][2]).compareTo(in[1][2]), 0);
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTFind3() {
        database = insert(database, in[1]);
        assertEquals(database.find(in[1][3]).compareTo(in[1][3]), 0);
    }
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTFind4() {
        database = insert(database, in[1]);
        database.clear();
        assertNull(database.find(in[1][3]));
    } 
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTRemove0() {
        database = insert(database, in[1]);
        assertEquals(database.removeNode(database.iterator().next()).
                compareTo(1), 0);
    } 
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTNode0() {
        Node<Integer> n = new Node<Integer>(1);
        assertTrue(n.isLeaf());
    } 
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTNode1() {
        Node<Integer> n1 = new Node<Integer>(1);
        Node<Integer> n2 = new Node<Integer>(1);
        Node<Integer> n3 = new Node<Integer>(1);
        n1.setLeft(n2);
        n1.setRight(n3);
        assertFalse(n1.isLeaf());
    } 
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTNode2() {
        Node<Integer> n1 = new Node<Integer>(1);
        Node<Integer> n2 = new Node<Integer>(1);
        assertEquals(n1.compareTo(n2), 0);
    } 
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTNode3() {
        Node<Integer> n1 = new Node<Integer>(1);
        Node<Integer> n2 = new Node<Integer>(1);
        assertEquals(n1.compareTo(n2.value()), 0);
    } 
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTNode4() {
        Node<Integer> n1 = new Node<Integer>(1);
        Node<Integer> n2 = new Node<Integer>(1);
        assertTrue(n1.equals(n2.value()));
    } 
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTNode5() {
        Node<Integer> n1 = new Node<Integer>(1);
        Node<Integer> n2 = new Node<Integer>(1);
        assertTrue(n1.equals(n2));
    } 
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTNode6() {
        Node<Integer> n1 = new Node<Integer>(1);
        assertTrue(n1.equals(n1));
    } 
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTNode7() {
        Node<Integer> n1 = new Node<Integer>(1);
        assertFalse(n1.equals((Integer)null));
    } 
    
    /**
     * check the binary search tree operations 
     */
    @Test
    public void testBSTNode8() {
        Node<Integer> n1 = new Node<Integer>(1);
        assertTrue(n1.equals(n1.value()));
    } 
    

    
    
}
