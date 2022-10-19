import student.TestCase;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Runs the testing scripts
 * @author Ahmed (AhmedAredah)
 * @version Aug 28, 2022
 */
public class RectangleTest extends TestCase
{
    //~ Fields ................................................................
    private SkipList<String, CustomRectangle> database;
    
    private CustomRectangle rec1;
    private CustomRectangle rec2;
    private CustomRectangle rec2C;
    private CustomRectangle rec3;
    private CustomRectangle rec4;
    private CustomRectangle rec5;
    private CustomRectangle rec6;
    private CustomRectangle rec7;
    private CustomRectangle rec8;
    private CustomRectangle rec9;
    private CustomRectangle rec10;
    private CustomRectangle reg1;
    private CustomRectangle reg2;
    private CustomRectangle reg3;
    
    private KVPair<String, CustomRectangle> kvp1;
    private KVPair<String, CustomRectangle> kvp2;
    private KVPair<String, CustomRectangle> kvp3;
    private KVPair<String, CustomRectangle> kvp4;
    private KVPair<String, CustomRectangle> kvp5;
    private KVPair<String, CustomRectangle> kvp6;
    private KVPair<String, CustomRectangle> kvp7;
    private KVPair<String, CustomRectangle> kvp8;
    private KVPair<String, CustomRectangle> kvp9;
    private KVPair<String, CustomRectangle> kvp10;
    private KVPair<String, CustomRectangle> kvpR1;
    private KVPair<String, CustomRectangle> kvpR2;
    private KVPair<String, CustomRectangle> kvpR3;
    
    /**
     * Initialize the test
     */
    public void setUp() 
    {
        database = new SkipList<String, CustomRectangle>();
    }
    
    /**
     * Run the test on new instances
     */
    public void testSeparateEntities() 
            throws FileNotFoundException
    {
        CommandInterpreter ci = new CommandInterpreter();
        assertTrue(ci.verifyRecParam("A", 0, 0, 1, 1));
        rec1 = CustomRectangle.createNew(0, 0, 1, 1);
        assertNotNull(rec1);
        
        // testing rectangle main parameters
        assertFalse(ci.verifyRecParam("Ah%s", 0, 0, 1, 1));
        assertTrue(ci.verifyRecParam("Am33d", 0, 0, 1, 1));
        assertFalse(ci.verifyRecParam("Adsfd", -1, 3, 4, 6));
        assertFalse(ci.verifyRecParam("Afds", 3, -1, 4, 6));
        assertFalse(ci.verifyRecParam("Afdsf", 3, 4, -1, 6));
        assertFalse(ci.verifyRecParam("Axzcx", 3, 4, 5, -4));
        assertFalse(ci.verifyRecParam("A_d", 3, 4, -1, 6));
        assertFalse(ci.verifyRecParam("A45", 3, 4, -1, 6));
        assertFalse(ci.verifyRecParam("A45", 3, 4, 1200, 5));
        assertFalse(ci.verifyRecParam("A45", 3, 4, 4, 1200));
        assertFalse(ci.verifyRecParam("A", 0, 0, 1, 0));
        assertFalse(ci.verifyRecParam("A", 0, 0, 0, 1));
        assertFalse(ci.verifyRecParam("A", 200, 30, 1400, 1));
        assertFalse(ci.verifyRecParam("A", 200, 30, 140, 1400));
        assertFalse(ci.verifyRecParam("A", 200, 30, 1400, 1));
        assertFalse(ci.verifyRecParam("A", 200, 30, 140, 1400));
        assertFalse(ci.verifyRecParam("0A", 0, 0, 0, 1));
        assertFalse(ci.verifyRecParam("_A", 0, 0, 0, 1));
        assertFalse(ci.verifyRecParam("0fdfd", 400, 300, 30, 70));
        assertFalse(ci.verifyRecParam("fdfd", 400, 300, 800, 600));
        assertFalse(ci.verifyRecParam("fdfd", 400, 300, 600, 800));
        
        assertTrue(ci.verifyRecParam("A", 0, 0, 1, 1));
        assertTrue(ci.verifyRecParam("A", 0, 0, 1, 1));

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

        assertFalse(ci.verifyRecParam(0, 0, 1, 0, true));
        assertFalse(ci.verifyRecParam(0, 0, 0, 1, true));
        assertFalse(ci.verifyRecParam(0, 0, 0, 1, true));
        assertFalse(ci.verifyRecParam(0, 0, 1, 0, true));
        assertFalse(ci.verifyRecParam(0, 0, 0, 1, true));
        assertFalse(ci.verifyRecParam(200, 30, 1400, 1, false));
        assertFalse(ci.verifyRecParam(200, 30, 140, 1400, false));
        assertFalse(ci.verifyRecParam(200, 30, 1400, 1, false));
        assertFalse(ci.verifyRecParam(200, 30, 140, 1400, false));
        assertFalse(ci.verifyRecParam(200, 30, 1400, 1, false));
        assertFalse(ci.verifyRecParam(200, 30, 140, 1400, false));
        
        assertTrue(ci.verifyRecParam(0, 0, 1, 1, true));
        assertTrue(ci.verifyRecParam(-1, 0, 1, 1, true));
        assertTrue(ci.verifyRecParam(0, -1, 1, 1, true));
        assertTrue(ci.verifyRecParam(0, 0, 1, 1, true));
        assertTrue(ci.verifyRecParam(200, 30, 1400, 1, true));
        assertTrue(ci.verifyRecParam(200, 30, 140, 1400, true));

        assertFalse(ci.regionSearchCase(
                        new String[] {"search", "%qq", "1", "2", "3"}));
        
        rec1 = CustomRectangle.createNew(100, 10, 13, 14);
        rec2 = CustomRectangle.createNew(100, 0, 1, 1);
        assertFalse(rec1.hasSameProperties(rec2));
        assertFalse(rec2.hasSameProperties(rec1));
        
        rec1 = CustomRectangle.createNew(1000, 10, 13, 14);
        rec2 = CustomRectangle.createNew(100, 0, 1, 1);
        assertFalse(rec1.hasSameProperties(rec2));
        assertFalse(rec2.hasSameProperties(rec1));
        
        rec1 = CustomRectangle.createNew(1000, 0, 1, 1);
        rec2 = CustomRectangle.createNew(100, 0, 1, 1);
        assertFalse(rec1.doInterset(rec2));
        assertFalse(rec2.doInterset(rec1));
        
        rec1 = CustomRectangle.createNew(100, 4, 1, 1);
        rec2 = CustomRectangle.createNew(100, 4, 1, 1);
        assertTrue(rec1.hasSameProperties(rec2));
        assertTrue(rec2.hasSameProperties(rec1));
        
        rec1 = CustomRectangle.createNew(100, 4, 5, 1);
        rec2 = CustomRectangle.createNew(100, 4, 4, 1);
        assertFalse(rec1.hasSameProperties(rec2));
        assertFalse(rec2.hasSameProperties(rec1));
        rec1 = CustomRectangle.createNew(10, 4, 5, 1);
        rec2 = CustomRectangle.createNew(100, 4, 4, 1);
        assertFalse(rec1.hasSameProperties(rec2));
        assertFalse(rec2.hasSameProperties(rec1));
        assertFalse(rec2.equals(rec1));
        assertTrue(rec2.equals(rec2));
        
        
        rec1 = CustomRectangle.createNew(0, 0, 1, 1);
        assertNotNull(rec1);
        assertEquals(rec1.getX(), 0);
        assertEquals(rec1.getY(), 0);
        assertEquals(rec1.getFarX(), 1);
        assertEquals(rec1.getFarY(), 1);
        assertEquals(rec1.getH(), 1);
        assertEquals(rec1.getW(), 1);
        assertEquals(rec1.getArea(), 1);
        assertEquals(rec1.toString(), "0, 0, 1, 1");

        
        rec2 = CustomRectangle.createNew(5, 10, 1, 1);
        assertFalse(rec1.doInterset(rec2));
        
        rec2C = CustomRectangle.createNew(5, 10, 2, 4);
        assertEquals(rec2C.getX(), 5);
        assertEquals(rec2C.getY(), 10);
        assertEquals(rec2C.getFarX(), 7);
        assertEquals(rec2C.getFarY(), 14);
        assertEquals(rec2C.getH(), 4);
        assertEquals(rec2C.getW(), 2);
        assertEquals(rec2C.getArea(), 8);
        assertEquals(rec2C.toString(), "5, 10, 2, 4");
        
        assertFalse(ci.verifyRecParam(5, 10, 0, 0, false));
        rec3 = null;


        rec4 = CustomRectangle.createNew(5, 10, 1, 1);
        rec5 = CustomRectangle.createNew(5, 10, 5, 5);
        assertTrue(rec4.doInterset(rec5));
        assertTrue(rec5.doInterset(rec4));
        
        rec6 = CustomRectangle.createNew(15, 100, 5, 5);
        assertFalse(rec5.doInterset(rec6));
        
        rec7 = CustomRectangle.createNew(500, 300, 5, 5);
        assertFalse(rec7.doInterset(rec6));
        assertFalse(rec6.doInterset(rec7));
        
        rec8 = CustomRectangle.createNew(500, 300, 5, 5);
        assertEquals(rec4.compareTo(rec6), -1);
        assertEquals(rec7.compareTo(rec8), 0);
        assertEquals(rec7.compareTo(rec7), 0);
        assertEquals(rec4.compareTo(rec5), 1);
        
        rec9 = CustomRectangle.createNew(500, 300, 5, 5);
        assertEquals(rec8.compareTo(rec9), 0);
        try
        {
            rec8.compareTo(null);
        }
        catch (NullPointerException e)
        {
            assertNull(e.getMessage());
        }
        
                
        rec10 = CustomRectangle.createNew(500, 1000, 5, 5);
        assertFalse(rec9.doInterset(rec10));
        assertFalse(rec10.doInterset(rec9));
        
        reg1 = CustomRectangle.createNew(-10, -10, 500, 500);
        reg2 = CustomRectangle.createNew(-10, -10, 3000, 3000);
        reg3 = CustomRectangle.createNew(-10, -10, -5, -5);
        assertTrue(reg1.doInterset(rec2));
        assertTrue(reg2.doInterset(rec1));
        assertNotNull(reg3);     
        assertEquals(reg2.toString(), "-10, -10, 3000, 3000");
        
        
        //......................................
        //testKVPair
        
        kvp1 = new KVPair<String, CustomRectangle>("A", rec1);
        assertEquals("A", kvp1.key());
        assertEquals(rec1, kvp1.value());
        assertEquals("A, 0, 0, 1, 1", kvp1.toString());
        assertEquals(kvp1.compareTo("A"), 0);
              
        kvp2 = new KVPair<String, CustomRectangle>(null, rec2);
        assertFalse(database.insert(kvp2));
        kvp2 = new KVPair<String, CustomRectangle>("A", rec2);
        
        
        kvp3 = new KVPair<String, CustomRectangle>(null, rec3);
        assertFalse(database.insert(kvp3));
        
        kvp3 = new KVPair<String, CustomRectangle>("A", rec3);
        assertFalse(database.insert(kvp3));
        
        kvp4 = new KVPair<String, CustomRectangle>("B", rec4);
        assertEquals(kvp4.toString(), "B, 5, 10, 1, 1");
        kvp5 = new KVPair<String, CustomRectangle>("B", rec5);
        kvp6 = new KVPair<String, CustomRectangle>("C", rec6);
        kvp7 = new KVPair<String, CustomRectangle>("C", rec7);
        kvp8 = new KVPair<String, CustomRectangle>("C", rec8);
        kvp9 = new KVPair<String, CustomRectangle>("D", rec9);
        kvp10 = new KVPair<String, CustomRectangle>("D", rec10);
        
        assertEquals(kvp4.compareValues(kvp6), -1);
        assertEquals(kvp7.compareValues(kvp8), 0);
        assertEquals(kvp7.compareValues(kvp7), 0);
        assertEquals(kvp4.compareValues(kvp5), 1);
        assertEquals(kvp8.compareValues(kvp9), 0);
        
        kvpR1 = new KVPair<String, CustomRectangle>(null, reg1);
        assertEquals(kvpR1.toString(), "-10, -10, 500, 500");
        kvpR2 = new KVPair<String, CustomRectangle>(null, reg2);
        kvpR3 = new KVPair<String, CustomRectangle>(null, reg3);
           
           
           
           //......................................
           //test database
        
        assertEquals(database.size(), 0);
        
        assertTrue(database.insert(kvp1));
        database.dump();
          
        systemOut().clearHistory();
        assertFalse(database.insert(kvp3));
        systemOut().clearHistory();
        
        assertTrue(database.insert(kvp2));
        assertTrue(database.insert(kvp4));
        assertTrue(database.insert(kvp5));
        assertEquals(database.deleteByKey("B"), 
                "Rectangle removed: (B, 5, 10, 5, 5)\r\n");
//        assertEquals("(B, 5, 10, 1, 1)", database.deleteByKey("B"));
//        database.insert(kvpR1);
//        assertFalse(database.insert(kvpR2));
//        database.insert(kvpR3);
        systemOut().clearHistory();
        database.dump();
        assertNotNull(systemOut().getHistory());
        systemOut().clearHistory();
        
        assertTrue(database.insert(kvp6));
        assertEquals(database.size(), 4);
        assertTrue(database.insert(kvp7));
        assertTrue(database.insert(kvp8));
        assertTrue(database.insert(kvp9));
        assertTrue(database.insert(kvp10));
        assertEquals(database.deleteByValue(kvp10), 
                "Rectangle removed: (D, 500, 1000, 5, 5)\r\n");
        
        assertEquals(database.deleteByValue(null), "");
        assertEquals(database.findElements(""), "");
        assertEquals(database.findElements(kvp6.key().toString()), 
                "(C, 500, 300, 5, 5)\r\n"
                + "(C, 500, 300, 5, 5)\r\n"
                + "(C, 15, 100, 5, 5)\r\n");
        database.findIntersections(null);
        database.findIntersections(kvp8);
        database.findIntersectingPairs();
        
        assertFalse(database.insert(null));
        kvp1 = new KVPair<String, CustomRectangle>(null, rec1);
        assertFalse(database.insert(kvp1));
        kvp1 = new KVPair<String, CustomRectangle>("A", null);
        assertFalse(database.insert(kvp1));
        
    }

    /**
     * tests the interpreter class
     */
    public void testCommandInterpreter()
    {
        CommandInterpreter commandInt = new CommandInterpreter();
        String[] arr;
        
        systemOut().clearHistory();
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "SkipList dump:\r\n"
                + "Node has depth 1, Value (null)\r\n"
                + "SkipList size is: 0\r\n");
        
        systemOut().clearHistory();
        arr = new String[] {"intersections"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), "Intersection pairs:\r\n");
        
        systemOut().clearHistory();
        arr = new String[] {"remove", "A"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "Rectangle not removed: (A)\r\n");
        
        systemOut().clearHistory();
        arr = new String[] {"remove", "4", "5", "-1", "5"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "Rectangle rejected: (4, 5, -1, 5)\r\n");
                
        //missing value
        systemOut().clearHistory();
        arr = new String[] {"insert", "recA", "1", "1", "0"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (recA, 1, 1, 0)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", ".q", "1", "1", "0"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (.q, 1, 1, 0)\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "1q", "1", "1", "0"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (1q, 1, 1, 0)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "q$a", "1", "1", "0"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (q$a, 1, 1, 0)\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "", "1", "1", "0"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (, 1, 1, 0)\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "q_a.", "1", "1", "0"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (q_a., 1, 1, 0)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "100", "100", "1200", "1200"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (100, 100, 1200, 1200)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "120", "100", "1200", "1300"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (120, 100, 1200, 1300)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        //strings values
        systemOut().clearHistory();
        arr = new String[] {"insert", "recA", "ss", "1", "0"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (recA, ss, 1, 0)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        //strings values
        systemOut().clearHistory();
        arr = new String[] {"insert", "recA", "ss", "1", "1", "1"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (recA, ss, 1, 1, 1)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        //normal insertion values
        systemOut().clearHistory();
        arr = new String[] {"insert", "recA", "1", "1", "0", "0"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (recA, 1, 1, 0, 0)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "A", "0", "0", "1", "1"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle inserted: (A, 0, 0, 1, 1)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "A", "5", "10", "1", "1"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle inserted: (A, 5, 10, 1, 1)\n",
                systemOut().getHistory());

        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "A", "5", "10", "2", "4"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle inserted: (A, 5, 10, 2, 4)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "A", "5", "10", "0", "0"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (A, 5, 10, 0, 0)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "B", "5", "10", "1", "1"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle inserted: (B, 5, 10, 1, 1)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "B", "5", "10", "5", "5"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle inserted: (B, 5, 10, 5, 5)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "C", "15", "100", "5", "5"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle inserted: (C, 15, 100, 5, 5)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "C", "500", "300", "5", "5"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle inserted: (C, 500, 300, 5, 5)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "C", "500", "300", "5", "5"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle inserted: (C, 500, 300, 5, 5)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "D", "500", "300", "5", "5"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle inserted: (D, 500, 300, 5, 5)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "D", "500", "1000", "5", "5"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle inserted: (D, 500, 1000, 5, 5)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "C", "-10", "-10", "500", "500"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (C, -10, -10, 500, 500)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);

        systemOut().clearHistory();
        arr = new String[] {"insert", "j", "-10", "-10", "3000", "3000"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (j, -10, -10, 3000, 3000)\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"search", "dd", "-10", "3000", "3000"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle not found: (dd, -10, 3000, 3000)\r\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "qwewe2", "-10", "-10", "-5", "-5"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (qwewe2, -10, -10, -5, -5)\n",
                systemOut().getHistory());
        
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        
        systemOut().clearHistory();
        arr = new String[] {"insert", "r", "-10", "-10", "-5", "-5"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (r, -10, -10, -5, -5)\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"dump"};
        commandInt.interpretLine(arr);
        assertNotNull(systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"search", "A"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), 
                "(A, 5, 10, 2, 4)\r\n"
                + "(A, 5, 10, 1, 1)\r\n"
                + "(A, 0, 0, 1, 1)\r\n");
        
        systemOut().clearHistory();
        arr = new String[] {"search", "A", "B", "c"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), 
                "Rectangle not found: (A, B, c)\n");
        
        systemOut().clearHistory();
        arr = new String[] {"search", "A", "B"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), 
                "Rectangle not found: (A, B)\n");
        
        systemOut().clearHistory();
        arr = new String[] {"search", "Z"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle not found: (Z)\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"remove", "Z"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle not removed: (Z)\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"remove", "Z", "A"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle not removed: (Z A)\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"remove", "A"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), 
                "Rectangle removed: (A, 5, 10, 2, 4)\r\n");
        
        systemOut().clearHistory();
        arr = new String[] {"remove", "ss", "10", "1", "1"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle not removed: (ss, 10, 1, 1)\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"remove", "10", "10", "-1", "0"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle rejected: (10, 10, -1, 0)\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"remove", "5", "10", "1", "1"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle removed: (A, 5, 10, 1, 1)\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"remove", "2", "4", "2", "3"};
        commandInt.interpretLine(arr);
        assertEquals("Rectangle not removed: (2, 4, 2, 3)\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"regionsearch", "-5", "10000", "2000"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), 
                "Rectangle rejected: (-5, 10000, 2000)\r\n");
        
        systemOut().clearHistory();
        arr = new String[] {"regionsearch", "-10", "-5", "10000", "2000"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), 
                "Rectangles intersecting region (-10, -5, 10000, 2000):\r\n"
                + "(A, 0, 0, 1, 1)\r\n"
                + "(B, 5, 10, 5, 5)\r\n"
                + "(B, 5, 10, 1, 1)\r\n"
                + "(C, 500, 300, 5, 5)\r\n"
                + "(C, 500, 300, 5, 5)\r\n"
                + "(C, 15, 100, 5, 5)\r\n"
                + "(D, 500, 1000, 5, 5)\r\n"
                + "(D, 500, 300, 5, 5)\r\n");
        
        systemOut().clearHistory();
        arr = new String[] {"regionsearch", "-10", "-5", "-10", "-10"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), 
                "Rectangle rejected: (-10, -5, -10, -10)\r\n");
        
        systemOut().clearHistory();
        arr = new String[] {"regionsearch", "-10", "-5", "0", "0"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), 
                "Rectangle rejected: (-10, -5, 0, 0)\r\n");
        
        systemOut().clearHistory();
        arr = new String[] {"intersections"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(),
                "Intersections pairs:\r\n"
                + "(B, 5, 10, 5, 5 | B, 5, 10, 1, 1)\r\n"
                + "(B, 5, 10, 1, 1 | B, 5, 10, 5, 5)\r\n"
                + "(C, 500, 300, 5, 5 | C, 500, 300, 5, 5)\r\n"
                + "(C, 500, 300, 5, 5 | D, 500, 300, 5, 5)\r\n"
                + "(C, 500, 300, 5, 5 | C, 500, 300, 5, 5)\r\n"
                + "(C, 500, 300, 5, 5 | D, 500, 300, 5, 5)\r\n"
                + "(D, 500, 300, 5, 5 | C, 500, 300, 5, 5)\r\n"
                + "(D, 500, 300, 5, 5 | C, 500, 300, 5, 5)\r\n");
                
        systemOut().clearHistory();
        arr = new String[] {"brbrbr"};
        commandInt.interpretLine(arr);
        assertEquals("Command brbrbr is not implemented! check spelling!\n",
                systemOut().getHistory());
        
        systemOut().clearHistory();
        arr = new String[] {"regionsearch", "-900", "5", "5000", "200"};
        commandInt.interpretLine(arr);
        assertEquals(systemOut().getHistory(), 
                "Rectangles intersecting region (-900, 5, 5000, 200):\r\n"
                + "(B, 5, 10, 5, 5)\r\n"
                + "(B, 5, 10, 1, 1)\r\n"
                + "(C, 15, 100, 5, 5)\r\n");        
        
        Rectangle1 recClass = new Rectangle1();
        try 
        {
            
            Path currentRelativePath = Paths.get("");
            String p;
            p = currentRelativePath.toAbsolutePath().toString() + 
                    "\\SkipListSampleInput.txt";
            
            recClass.runScanner(p);
            assertFalse(recClass.runScanner("empty.txt"));
        }
        catch (FileNotFoundException e)
        {
            assertTrue(e.getMessage().
                      equalsIgnoreCase("The file empty.txt does not exist!"));
        }
        
        rec1 = CustomRectangle.createNew(0, 0, 5, 4);
        assertEquals(rec1.getArea(), 20);
        assertFalse(rec1.equals(null));
        
        rec2 = CustomRectangle.createNew(0, 0, 5, 4);
        assertEquals(rec1.compareTo(rec2), 0);
        
        rec3 = CustomRectangle.createNew(0, 0, 5, 5);
        assertEquals(rec1.compareTo(rec3), 1);
        
        rec4 = CustomRectangle.createNew(0, 0, 5, -1);
        assertNotNull(rec4);
        
        rec5 = CustomRectangle.createNew(0, 0, 5, 2);
        assertNotNull(rec5);
        
        rec6 = CustomRectangle.createNew(0, 0, 5, 2);
        assertNotNull(rec6);
        
        assertFalse(commandInt.verifyRecParam(500, 700, 1500, 100, false));
        rec7 = CustomRectangle.createNew(500, 700, 1500, 100);
        assertNotNull(rec7);
        
        assertFalse(commandInt.verifyRecParam(500, 700, 1500, 100, false));
        rec8 = CustomRectangle.createNew(500, 700, 1500, 100);
        assertEquals("500, 700, 1500, 100", rec8.toString());
        
        assertFalse(commandInt.verifyRecParam(500, 700, -10, 100, false));
        rec9 = CustomRectangle.createNew(500, 700, -10, 100);
        assertNotNull(rec9);
        
        rec4 = CustomRectangle.createNew(5, 10, 1, 1);
        kvp2 = new KVPair<String, CustomRectangle>("B", rec2);
        kvp4 = new KVPair<String, CustomRectangle>("B", rec4);
        
        assertEquals(0, kvp2.compareTo("B"));
        assertEquals(0, kvp2.compareTo(kvp4));
        assertEquals(0, kvp2.compareTo(kvp4.key()));
        
        kvp5 = new KVPair<String, CustomRectangle>("B", null);
        assertEquals((String)null, kvp5.toString());
        
        KVPair<String, CustomRectangle> kvpt = null;
        assertEquals(kvp5.compareTo(kvpt), -5);
        
        assertEquals(kvp2.compareTo(kvp1), -5);
        
        String test = null;
        assertEquals(kvp2.compareTo(test), -5);

        // test SkipList node
        
        SkipList.SkipNode n1;
        n1 = new SkipList<String, CustomRectangle>()
                .new SkipNode<String, CustomRectangle>(null, null, 1);
        
        assertNull(n1.toString());
        assertEquals(n1.dump(), "Node has depth 2, Value (null)");
        
        n1 = new SkipList<String, CustomRectangle>()
                .new SkipNode<String, CustomRectangle>(null, 1);
        assertEquals(n1.toString(), "");
        assertNull(n1.dump());

    }
    
  
}
