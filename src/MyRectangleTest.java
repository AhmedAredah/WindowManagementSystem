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
 * Test the CustomRectangle class
 *
 * @author Ahmed (AhmedAredah)
 * @version Oct 11, 2022
 */
public class MyRectangleTest extends TestCase {

    private MyRectangle rec1;
    private MyRectangle rec2;

    private int[][] goodRectangles = { { 100, 10, 13, 14 }, { 100, 0, 1, 1 },
        { 1000, 10, 13, 14 }, { 100, 0, 1, 1 }, { 0, 0, 1, 1 },
        { 5, 10, 1, 1 }, { 5, 10, 2, 4 }, { 5, 10, 1, 1 },
        { 5, 10, 5, 5 } };



    /**
     * create a new rectangle with passed variables
     * 
     * @param d is an array of rectangle variables
     * @return the created rectangle
     */
    private MyRectangle customRectangleCreate(int[] d) {
        return MyRectangle.createNew(d[0], d[1], d[2], d[3]);
    }
    
    /**
     * check two rectangles do not have the same properties
     */
    @Test
    public void tesCustomRectangleSameProperties0() {

        rec1 = customRectangleCreate(goodRectangles[0]);
        rec2 = customRectangleCreate(goodRectangles[1]);
        assertFalse(rec1.hasSameProperties(rec2));
        assertFalse(rec2.hasSameProperties(rec1));
    }

    /**
     * check two rectangles have the same properties
     */
    @Test
    public void testCustomRectangleSameProperties1() {

        rec1 = customRectangleCreate(goodRectangles[0]);
        rec2 = customRectangleCreate(goodRectangles[0]);
        assertTrue(rec1.hasSameProperties(rec2));
        assertTrue(rec2.hasSameProperties(rec1));
    }

    /**
     * test two rectangles do not intersect
     */
    @Test
    public void testCustomRectangleIntersect0() {

        rec1 = customRectangleCreate(goodRectangles[2]);
        rec2 = customRectangleCreate(goodRectangles[3]);
        assertFalse(rec1.doInterset(rec2));
        assertFalse(rec2.doInterset(rec1));
    }

    /**
     * test two rectangles do intersect
     */
    @Test
    public void testCustomRectangleIntersect1() {

        rec1 = customRectangleCreate(goodRectangles[5]);
        rec2 = customRectangleCreate(goodRectangles[6]);
        assertTrue(rec1.doInterset(rec2));
        assertTrue(rec2.doInterset(rec1));
    }

    /**
     * test two rectangles do intersect with different values
     */
    @Test
    public void testCustomRectangleIntersect2() {
        rec1 = customRectangleCreate(goodRectangles[7]);
        rec2 = customRectangleCreate(goodRectangles[8]);
        assertTrue(rec1.doInterset(rec2));
        assertTrue(rec2.doInterset(rec1));
    }

    /**
     * test two rectangles are equal
     */
    @Test
    public void testCustomRectangleEquals0() {
        rec1 = customRectangleCreate(goodRectangles[0]);
        rec2 = customRectangleCreate(goodRectangles[1]);
        assertFalse(rec2.equals(rec1));
        assertFalse(rec1.equals(rec2));
    }

    /**
     * test two rectangles are not equal
     */
    @Test
    public void testCustomRectangleEquals1() {
        rec1 = customRectangleCreate(goodRectangles[1]);
        assertTrue(rec1.equals(rec1));
    }

    /**
     * test one rectangle and a null are not equal
     */
    @Test
    public void testCustomRectangleEquals2() {
        rec1 = customRectangleCreate(goodRectangles[5]);
        assertFalse(rec1.equals(null));
    }

    /**
     * test two rectangles are not equal
     */
    @Test
    public void testCustomRectangleEquals3() {
        rec1 = customRectangleCreate(goodRectangles[5]);
        rec2 = customRectangleCreate(goodRectangles[5]);
        assertFalse(rec1.equals(rec2));
    }
    
    /**
     * check the rectangle is created (not null)
     */
    @Test
    public void testCustomRectangleCreate0() {
        MyRectangle rec = null;
        rec = customRectangleCreate(goodRectangles[0]);
        assertNotNull(rec);
    }

    /**
     * check properties of rectangle
     */
    @Test
    public void testCustomRectangleCreate1() {
        rec1 = customRectangleCreate(goodRectangles[4]);
        assertEquals(rec1.getX(), 0);
        assertEquals(rec1.getY(), 0);
        assertEquals(rec1.getFarX(), 1);
        assertEquals(rec1.getFarY(), 1);
        assertEquals(rec1.getH(), 1);
        assertEquals(rec1.getW(), 1);
        assertEquals(rec1.getArea(), 1);
        assertEquals(rec1.toString(), "0, 0, 1, 1");
    }

    /**
     * check properties of rectangle
     */
    @Test
    public void testCustomRectangleCreate2() {
        rec1 = customRectangleCreate(goodRectangles[6]);
        assertEquals(rec1.getX(), 5);
        assertEquals(rec1.getY(), 10);
        assertEquals(rec1.getFarX(), 7);
        assertEquals(rec1.getFarY(), 14);
        assertEquals(rec1.getH(), 4);
        assertEquals(rec1.getW(), 2);
        assertEquals(rec1.getArea(), 8);
        assertEquals(rec1.toString(), "5, 10, 2, 4");
    }

    /**
     * check compareTo method in rectangle
     */
    @Test
    public void testCustomRectangleCompareTo0() {
        rec1 = customRectangleCreate(goodRectangles[0]);
        rec2 = customRectangleCreate(goodRectangles[1]);
        assertEquals(rec1.compareTo(rec2), -1);
    }

    /**
     * check compareTo method in rectangle
     */
    @Test
    public void testCustomRectangleCompareTo1() {
        rec1 = customRectangleCreate(goodRectangles[7]);
        rec2 = customRectangleCreate(goodRectangles[7]);
        assertEquals(rec1.compareTo(rec2), 0);
    }

    /**
     * check compareTo method in rectangle
     */
    @Test
    public void testCustomRectangleCompareTo2() {
        rec1 = customRectangleCreate(goodRectangles[5]);
        rec2 = customRectangleCreate(goodRectangles[8]);
        assertEquals(rec1.compareTo(rec2), 1);
    }

    /**
     * check compareTo method in rectangle with throw error
     */
    @Test(expected = RuntimeException.class)
    public void testCustomRectangleCompareTo3() {
        rec1 = customRectangleCreate(goodRectangles[5]);
        rec1.compareTo(null);
        assertEquals(systemOut().getHistory(), "");
    }

}
