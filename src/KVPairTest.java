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
/**
 * test the KVPair class
 *
 * @author Ahmed (AhmedAredah)
 * @version Oct 11, 2022
 */
public class KVPairTest extends TestCase {
    
    /**
     * test
     */
    @Test
    public void test1() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = new KVPair<>("a", 1);
        assertEquals(a1.compareTo(a2), 0);
    }
    /**
     * test
     */
    @Test
    public void test2() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = new KVPair<>("a", 1);
        assertEquals(a1.compareTo(a2.key()), 0);
    }
    /**
     * test
     */
    @Test
    public void test3() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = new KVPair<>("b", 1);
        assertEquals(a1.compareTo(a2.key()), -1);
    }
    /**
     * test
     */
    @Test
    public void test4() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = new KVPair<>("b", 1);
        assertEquals(a1.compareValues(a2), 0);
    }
    /**
     * test
     */
    @Test
    public void test5() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = new KVPair<>("b", 1);
        assertEquals(a1.compareValues(a2.value()), 0);
    }
    /**
     * test
     */
    @Test
    public void test6() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        assertEquals(a1.toString(), "a, 1");
    }
    /**
     * test
     */
    @Test
    public void test7() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = new KVPair<>(null, null);
        assertEquals(a1.compareTo(a2), -5);
    }
    /**
     * test
     */
    @Test
    public void test8() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = new KVPair<>(null, null);
        assertEquals(a1.compareTo(a2.key()), -5);
    }
    
    /**
     * test
     */
    @Test
    public void test9() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        assertTrue(a1.equals(a1));
    }
    
    /**
     * test
     */
    @Test
    public void test10() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = new KVPair<>("a", 2);
        assertFalse(a1.equals(a2));
    }
    
    /**
     * test
     */
    @Test
    public void test11() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = new KVPair<>("b", 1);
        assertFalse(a1.equals(a2));
    }
    
    /**
     * test
     */
    @Test
    public void test12() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = null;
        assertFalse(a1.equals(a2));
    }
    
    /**
     * test
     */
    @Test
    public void test13() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = new KVPair<>("b", 5);
        assertFalse(a1.equals(a2));
    }
    /**
     * test
     */
    @Test
    public void test13e() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = new KVPair<>("a", 1);
        assertTrue(a1.equals(a2));
    }
    
    /**
     * test
     */
    @Test
    public void test14() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = new KVPair<>("b", 5);
        assertFalse(a1.equalValues(a2));
    }
    
    /**
     * test
     */
    @Test
    public void test15() {
        KVPair<String, Integer> a1 = new KVPair<>("a", null);
        assertNull(a1.toString());
    }
    
    /**
     * test
     */
    @Test
    public void test16() {
        KVPair<String, Integer> a1 = new KVPair<>(null, 1);
        assertEquals(a1.toString(), "1");
    }
    
    /**
     * test
     */
    @Test
    public void test17() {
        KVPair<String, Integer> a1 = new KVPair<>("a", 1);
        KVPair<String, Integer> a2 = null;
        assertEquals(a1.compareTo(a2), -5);
    }
}
