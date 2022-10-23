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
 * test the commandInterpreter class
 * 
 * @author Ahmed (AhmedAredah)
 * @version Oct 15, 2022
 * 
 */
public class CommandInterpreterTest extends TestCase {
    private CommandInterpreter ci;

    /**
     * initialize the command interpreter
     */
    public void setUp() {
        ci = new CommandInterpreter();
    }

    private int[][] paraD = { { 0, 0, 1, 1 }, { -1, 3, 4, 6 }, { 3, -1, 4, 6 },
                              { 3, 4, -1, 6 } };
    private String[] paraN = { "A", "Ah%s", "Am33d", "Adsfd", "Afds", "A_d",
                               "A45", "0A", "_A" };

    private boolean verify(String n, int[] d) {
        return ci.verifyRecParam(n, d[0], d[1], d[2], d[3]);
    }

    /**
     * test command interpreter
     */
    @Test
    public void testVerify0() {
        assertTrue(verify(paraN[0], paraD[0]));
        assertTrue(verify(paraN[2], paraD[0]));
    }

    /**
     * test command interpreter
     */
    @Test
    public void testVerify1() {
        assertFalse(verify(paraN[1], paraD[0]));
        assertFalse(verify(paraN[3], paraD[1]));
        assertFalse(verify(paraN[3], paraD[2]));
        assertFalse(verify(paraN[3], paraD[3]));
        assertFalse(verify(paraN[6], paraD[1]));
        assertFalse(verify(paraN[7], paraD[0]));
    }

    /**
     * test command interpreter
     */
    @Test
    public void testVerify2() {
        assertFalse(ci.verifyRecParam(400, 300, 30, -1, false));
        assertTrue(ci.verifyRecParam(400, 300, 30, 70, false));
        assertFalse(ci.verifyRecParam(400, 300, 800, 600, false));
        assertFalse(ci.verifyRecParam(400, 300, 600, 800, false));
        assertFalse(ci.verifyRecParam(-1, 0, 1, 1, false));
        assertFalse(ci.verifyRecParam(0, -1, 1, 1, false));
        assertFalse(ci.verifyRecParam(0, 0, 2000, 4, false));
        assertFalse(ci.verifyRecParam(0, 0, 45, 3000, false));
        assertFalse(ci.verifyRecParam(40, 1000, 2000, 4, false));
        assertFalse(ci.verifyRecParam(1000, 40, 45, 3000, false));
    }

    /**
     * test command interpreter
     */
    @Test
    public void testVerify3() {
        assertFalse(ci.verifyRecParam(0, 0, 1, 0, true));
        assertFalse(ci.verifyRecParam(0, 0, 0, 1, true));
        assertFalse(ci.verifyRecParam(0, 0, 0, 1, true));
        assertFalse(ci.verifyRecParam(0, 0, 1, 0, true));
        assertFalse(ci.verifyRecParam(0, 0, 0, 1, true));

        assertTrue(ci.verifyRecParam(0, 0, 1, 1, true));
        assertTrue(ci.verifyRecParam(-1, 0, 1, 1, true));
        assertTrue(ci.verifyRecParam(0, -1, 1, 1, true));
        assertTrue(ci.verifyRecParam(0, 0, 1, 1, true));
        assertTrue(ci.verifyRecParam(200, 30, 1400, 1, true));
        assertTrue(ci.verifyRecParam(200, 30, 140, 1400, true));
    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter0() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;

        systemOut().clearHistory();
        arr = new String[] { "dump" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), "BST dump:\r\n"
                + "Node has depth 0, Value (null)\r\n" + "BST size is: 0\r\n");
    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter1() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "intersections" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), "Intersection pairs:\r\n");
    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter2() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "remove", "A" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "Rectangle rejected: (A)\r\n");
    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter3() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "remove", "4", "5", "-1", "5" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "Rectangle rejected: (4, 5, -1, 5)\r\n");

    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter4() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        // missing value
        systemOut().clearHistory();
        arr = new String[] { "insert", "recA", "1", "1", "0" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (recA, 1, 1, 0)\n",
                systemOut().getHistory());
    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter5() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "recA", "1", "1", "0" };
        commandInt.interpretLine(arr);
        arr = new String[] { "dump" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (recA, 1, 1, 0)\r\n" + "BST dump:\r\n"
                + "Node has depth 0, Value (null)\r\n" + "BST size is: 0\r\n",
                systemOut().getHistory());

    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter6() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;

        arr = new String[] { "insert", "recA", "0", "1", "0" };
        commandInt.interpretLine(arr);
        arr = new String[] { "dump" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (recA, 0, 1, 0)\r\n" + "BST dump:\r\n"
                + "Node has depth 0, Value (null)\r\n" + "BST size is: 0\r\n",
                systemOut().getHistory());
    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter7() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;

        systemOut().clearHistory();
        arr = new String[] { "insert", ".q", "1", "1", "0" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (.q, 1, 1, 0)\n",
                systemOut().getHistory());

    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter8() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "1q", "1", "1", "0" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (1q, 1, 1, 0)\n",
                systemOut().getHistory());
    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter9() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;

        systemOut().clearHistory();
        arr = new String[] { "insert", "q$a", "1", "1", "0" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (q$a, 1, 1, 0)\n",
                systemOut().getHistory());

    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter10() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "", "1", "1", "0" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (, 1, 1, 0)\n",
                systemOut().getHistory());
    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter11() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "q_a.", "1", "1", "0" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (q_a., 1, 1, 0)\n",
                systemOut().getHistory());
    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter12() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;

        systemOut().clearHistory();
        arr = new String[] { "insert", "100", "100", "1200", "1200" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (100, 100, 1200, 1200)\n",
                systemOut().getHistory());
    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter13() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;

        systemOut().clearHistory();
        arr = new String[] { "insert", "120", "100", "1200", "1300" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (120, 100, 1200, 1300)\n",
                systemOut().getHistory());

    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter14() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        // strings values
        systemOut().clearHistory();
        arr = new String[] { "insert", "recA", "ss", "1", "0" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (recA, ss, 1, 0)\n",
                systemOut().getHistory());
    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter15() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;

        // strings values
        systemOut().clearHistory();
        arr = new String[] { "insert", "recA", "ss", "1", "1", "1" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (recA, ss, 1, 1, 1)\n",
                systemOut().getHistory());
    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter16() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "recA", "1", "1", "0", "0" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (recA, 1, 1, 0, 0)\n",
                systemOut().getHistory());
    }

    /**
     * tests the interpreter class
     */
    @Test
    public void testCommandInterpreter17() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "regionsearch", "dd", " ", "3000", "3000" };
        commandInt.interpretLine(arr);
        assertEquals("",
                systemOut().getHistory());
    }

    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter18() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;

        systemOut().clearHistory();
        arr = new String[] { "insert", "A", "0", "0", "1", "1" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle accepted: (A, 0, 0, 1, 1)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter19() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;

        systemOut().clearHistory();
        arr = new String[] { "insert", "A", "5", "10", "1", "1" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle accepted: (A, 5, 10, 1, 1)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter20() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "A", "5", "10", "2", "4" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle accepted: (A, 5, 10, 2, 4)\n",
                systemOut().getHistory());
    }
    
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter21() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "A", "5", "10", "0", "0" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (A, 5, 10, 0, 0)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter22() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "B", "5", "10", "1", "1" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle accepted: (B, 5, 10, 1, 1)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter23() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "B", "5", "10", "5", "5" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle accepted: (B, 5, 10, 5, 5)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter24() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "C", "15", "100", "5", "5" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle accepted: (C, 15, 100, 5, 5)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter25() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "C", "500", "300", "5", "5" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle accepted: (C, 500, 300, 5, 5)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter26() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        arr = new String[] { "insert", "C", "500", "300", "5", "5" };
        commandInt.interpretLine(arr);
        systemOut().clearHistory();
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (C, 500, 300, 5, 5)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter27() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "D", "500", "300", "5", "5" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle accepted: (D, 500, 300, 5, 5)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter28() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "D", "500", "1000", "5", "5" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle accepted: (D, 500, 1000, 5, 5)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter29() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "C", "-10", "-10", "500", "500" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (C, -10, -10, 500, 500)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter30() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "j", "-10", "-10", "3000", "3000" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (j, -10, -10, 3000, 3000)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter31() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "search", "dd", "-10", "3000", "3000" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle not found: dd, -10, 3000, 3000\r\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter32() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "qwewe2", "-10", "-10", "-5", "-5" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (qwewe2, -10, -10, -5, -5)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter33() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "insert", "r", "-10", "-10", "-5", "-5" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (r, -10, -10, -5, -5)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter34() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "dump" };
        commandInt.interpretLine(arr);
        assertNotNull(systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter35() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        
        arr = new String[] { "insert", "A", "5", "10", "2", "4" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "A", "5", "10", "1", "1" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "A", "0", "0", "1", "1" };
        commandInt.interpretLine(arr);
        systemOut().clearHistory();
        arr = new String[] { "search", "A" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), 
                "Rectangles found: (A, 5, 10, 2, 4)\r\n"
                + "Rectangles found: (A, 5, 10, 1, 1)\r\n"
                + "Rectangles found: (A, 0, 0, 1, 1)\r\n");
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter36() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "search", "A", "B", "c" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "Rectangle not found: A, B, c\n");
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter37() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "search", "A", "B" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), "Rectangle not found: A, B\n");
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter38() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "search", "Z" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle not found: Z\n", systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter39() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "remove", "Z" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (Z)\n", systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter40() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "remove", "Z", "A" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle not removed: (Z A)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter41() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        arr = new String[] { "insert", "A", "5", "10", "2", "4" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "A", "5", "10", "1", "1" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "A", "0", "0", "1", "1" };
        commandInt.interpretLine(arr);
        systemOut().clearHistory();
        arr = new String[] { "dump" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), "BST dump:\r\n"
                + "Node has depth 0, Value (A, 5, 10, 2, 4)\r\n"
                + "Node has depth 1, Value (A, 5, 10, 1, 1)\r\n"
                + "Node has depth 2, Value (A, 0, 0, 1, 1)\r\n"
                + "BST size is: 3\r\n");
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter42() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        arr = new String[] { "insert", "A", "5", "10", "2", "4" };
        commandInt.interpretLine(arr);
        systemOut().clearHistory();
        arr = new String[] { "remove", "A" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), "");
        // "Rectangle removed: (A, 0, 0, 1, 1)\r\n"
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter43() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "remove", "ss", "10", "1", "1" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (ss, 10, 1, 1)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter44() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "remove", "10", "10", "-1", "0" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (10, 10, -1, 0)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter45() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        
        arr = new String[] { "insert", "A", "5", "10", "1", "1" };
        commandInt.interpretLine(arr);
        systemOut().clearHistory();
        arr = new String[] { "remove", "5", "10", "1", "1" };
        commandInt.interpretLine(arr);
        assertEquals("", systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter46() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "remove", "2", "4", "2", "3" };
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (2, 4, 2, 3)\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter47() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "regionsearch", "-5", "10000", "2000" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "");
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter48() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        arr = new String[] { "insert", "A", "5", "10", "2", "4" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "A", "5", "10", "1", "1" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "A", "0", "0", "1", "1" };
        commandInt.interpretLine(arr);
        systemOut().clearHistory();
        arr = new String[] { "regionsearch", "-10", "-5", "10000", "2000" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "Rectangles intersecting region (-10, -5, 10000, 2000):\r\n"
                + "(A, 5, 10, 2, 4)\r\n"
                + "(A, 5, 10, 1, 1)\r\n"
                + "(A, 0, 0, 1, 1)\r\n");
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter49() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "regionsearch", "-10", "-5", "-10", "-10" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "");
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter50() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "regionsearch", "-10", "-5", "0", "0" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), "");
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter51() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        arr = new String[] { "insert", "A", "5", "10", "2", "4" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "B", "5", "10", "1", "1" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "C", "0", "0", "1", "1" };
        commandInt.interpretLine(arr);
        systemOut().clearHistory();
        arr = new String[] { "intersections" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "Intersection pairs:\r\n"
                + "(A, 5, 10, 2, 4 | B, 5, 10, 1, 1)\r\n");
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter52() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        systemOut().clearHistory();
        arr = new String[] { "brbrbr" };
        commandInt.interpretLine(arr);
        assertEquals("Command brbrbr is not implemented! check spelling!\n",
                systemOut().getHistory());
    }
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter53() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        arr = new String[] { "insert", "A", "5", "10", "2", "4" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "B", "5", "10", "1", "1" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "C", "0", "0", "1", "1" };
        systemOut().clearHistory();
        arr = new String[] { "regionsearch", "-900", "5", "5000", "200" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "Rectangles intersecting region (-900, 5, 5000, 200):\r\n"
                + "(A, 5, 10, 2, 4)\r\n"
                + "(B, 5, 10, 1, 1)\r\n");
        
        systemOut().clearHistory();
        arr = new String[] { "regionsearch", "-900", "5", "1", "1" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "");
        
    }
    
    /**
     * simulate long and large operations
     */
    @Test
    public void testCommandInterpreter54() {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        arr = new String[] { "insert", "A", "5", "10", "2", "4" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "B", "5", "10", "1", "1" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "C", "0", "0", "1", "1" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "B", "0", "0", "1", "1" };
        commandInt.interpretLine(arr);
        arr = new String[] { "insert", "C", "5", "10", "1", "1" };
        commandInt.interpretLine(arr);
        systemOut().clearHistory();
        arr = new String[] { "dump" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "BST dump:\r\n"
                + "Node has depth 0, Value (A, 5, 10, 2, 4)\r\n"
                + "Node has depth 1, Value (B, 5, 10, 1, 1)\r\n"
                + "Node has depth 3, Value (B, 0, 0, 1, 1)\r\n"
                + "Node has depth 2, Value (C, 0, 0, 1, 1)\r\n"
                + "Node has depth 3, Value (C, 5, 10, 1, 1)\r\n"
                + "BST size is: 5\r\n");
        systemOut().clearHistory();
        arr = new String[] { "remove", "5", "10", "2", "4" };
        commandInt.interpretLine(arr);
        systemOut().clearHistory();
        arr = new String[] { "dump" };
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "BST dump:\r\n"
                + "Node has depth 0, Value (B, 5, 10, 1, 1)\r\n"
                + "Node has depth 2, Value (B, 0, 0, 1, 1)\r\n"
                + "Node has depth 1, Value (C, 0, 0, 1, 1)\r\n"
                + "Node has depth 2, Value (C, 5, 10, 1, 1)\r\n"
                + "BST size is: 4\r\n");
    }
    
    

}
