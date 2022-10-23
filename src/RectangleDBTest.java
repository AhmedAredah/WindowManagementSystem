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
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class RectangleDBTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing Here
    }

    
    /**
     * Get code coverage of the class declaration.

     */
    @Test
    public void testRInit2()
    {
        RectangleDB manager = new RectangleDB();
        assertNotNull(manager);
        systemOut().clearHistory();
        RectangleDB.readLines("c://Aa.txt");
        assertNotNull(systemOut().getHistory());
    }
    
    /**
     * Get code coverage of the class declaration.
     */
    @Test
    public void testRInit3()
    {
        RectangleDB manager = new RectangleDB();
        systemOut().clearHistory();
        manager.runScanner("c://Aa.txt");
        assertNotNull(systemOut().getHistory());
    }
}
