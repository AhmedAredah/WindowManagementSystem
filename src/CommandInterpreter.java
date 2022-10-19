import java.util.Arrays;
/**
 * interpret the commands
 * 
 * @author Ahmed (AhmedAredah)
 * @version Aug 28, 2022
 */
public class CommandInterpreter {

    private SkipList<String, CustomRectangle> database;

    /**
     * initialize an interpreter
     */
    public CommandInterpreter() {
        database = new SkipList<String, CustomRectangle>();
    }

    /**
     * Execute line of commands
     * 
     * @param lineSplits : an array of strings
     * 
     */
    public void interpretLine(String[] lineSplits) {
        //declare variables
        String command = "";
        
//        System.out.println("----"+Arrays.toString(lineSplits));
        command = lineSplits[0].toLowerCase();
        switch (command) {
            //-----------------------------------------------------
            // process the case of insertion into the database
            case "insert": 
                // call the insert new function
                insertCase(lineSplits);
                break;
            
            //-----------------------------------------------------
            // process the case of printing all rectangles in the database
            case "dump":
                dumpCase();
                break;
    
            //-----------------------------------------------------
            // process the case of search a rectangle by its name
            case "search":
                searchCase(lineSplits);
                break;
    
            //-----------------------------------------------------
            // process the case of removing rectangles from
            // the database by a common name
            case "remove":
                removeCase(lineSplits);
                break;
    
            //-----------------------------------------------------
            // find intersections of a region
            case "regionsearch":
                regionSearchCase(lineSplits);
                break;
    
            //-----------------------------------------------------
            // print all the intersections pairs, duplicates are fine (as per 
            // the instructions)
            case "intersections":
                intersectedCase();
                break;
    
            //-----------------------------------------------------
            // if it reached here, that means the command was either
            // misspelled or a new command that has not been implemented
            default:
                System.out.println(String.format(
                        "Command %s is not implemented! check spelling!",
                        command));
                break;
        }
    }

    /**
     * get the database
     * 
     * @return database of type SkipList
     */
    public SkipList<String, CustomRectangle> getDatabase() {
        return database;
    }
    
    private int countNewLines(String s)
    {
        return (s.split("\\n", -1).length - 1);
    }
    
    /**
     * interprets an insert command
     * @param lineSplits is the commandLine array
     * @return true if insertion is a success, false otherwise
     */
    protected boolean insertCase(String[] lineSplits)
    {
        //declare variables
        String output = "";
        int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;
        String n = "";
        KVPair<String, CustomRectangle> dic;
        CustomRectangle rec;
        
        if (lineSplits.length != 6)
        {
            System.out.println(
                    String.format(
                         "Rectangle rejected: (%s)",
                         Arrays.toString(lineSplits)).
                        replaceFirst("\\[", "").
                        replaceFirst("\\]", "").
                        replaceFirst("insert, ", ""));
            return false;
        }
        
        try
        {
            n = lineSplits[1];
            x = Integer.parseInt(lineSplits[2]);
            y = Integer.parseInt(lineSplits[3]);
            w = Integer.parseInt(lineSplits[4]);
            h = Integer.parseInt(lineSplits[5]);
        }
        catch (Exception e)
        {
            System.out.println(
                    String.format(
                         "Rectangle rejected: (%s, %s, %s, %s, %s)",
                         lineSplits[1], lineSplits[2], lineSplits[3],
                         lineSplits[4], lineSplits[5]));
            return false;
        }
        
        // check if the rectangle has valid parameters
        if (verifyRecParam(n, x, y, w, h))
        {
            // create a new rectangle with the passed arguments
            rec = CustomRectangle.createNew(x, y, w, h);

            // create a new KVPair to hold the same rectangles
            dic = new KVPair<String, CustomRectangle>(n, rec);
            // insert the dictionary to the database
            database.insert(dic);
            output = "Rectangle inserted: ";
            output += String.format("(%s, %s, ", n, x);
            output += String.format("%s, %s, %s)", y, w, h);
            System.out.println(output);

        }
        else
        {
            // if it is not a region and user defined a name
            System.out.println(
                    String.format(
                         "Rectangle rejected: (%s, %d, %d, %d, %d)",
                         n, x, y, w, h));
        }
        return true;
    }
    
    /**
     * dump the database to the console
     * @return true if dumping is a success
     */
    protected boolean dumpCase()
    {
        String output = "";
        
        // print heading line
        System.out.println("SkipList dump:");
        
        output = database.dump();
        System.out.print(output);
        
        System.out.println("SkipList size is: " +
                database.size());
        return true;
    }
    
    /**
     * searches the database by name
     * @param lineSplits is the command line array
     * @return true if an entry is found, false otherwise
     */
    protected boolean searchCase(String[] lineSplits)
    {
        String output = "";
        String n = "";
        
        if (lineSplits.length == 2)
        {
            n = lineSplits[1];

            // print the rectangle is not found if it is not in the 
            // database
            output = database.findElements(n);
            if (countNewLines(output) == 0)
            {
                System.out.println("Rectangle not found: (" + n + ")");
                return false;
            }
            
            else
            {
                System.out.print(output);
                return true;
            }
        }
        else
        {
            System.out.println(
                    String.format(
                         "Rectangle not found: (%s)",
                         Arrays.toString(lineSplits)).
                        replaceFirst("\\[", "").
                        replaceFirst("\\]", "").
                        replaceFirst("search, ", "")); 
            return false;
        }
    }
    
    /**
     * remove an entry from the database
     * @param lineSplits is the commandLine array
     * @return true if an entry is remove, false otherwise
     */
    protected boolean removeCase(String[] lineSplits)
    {
        String output = "";
        String n = "";
        int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;
        KVPair<String, CustomRectangle> dic;
        CustomRectangle rec;
        
        // remove by name
        if (lineSplits.length == 2)
        {
            n = lineSplits[1];
            // return true if deleted
            output = database.deleteByKey(n);
            // if it is not in the database, 
            // show a message that the rectangle
            // is not found
            
            if (countNewLines(output) == 0)
            {
                System.out.println("Rectangle not removed: (" + n 
                        + ")");
                return false;
            }
            // if it's deleted, show a message that
            // the rectangle is deleted
            else
            {

                System.out.print(output);
                return true;
            }

        }
        // remove a rectangle by its dimensions
        else if (lineSplits.length == 5)
        {
            try
            {
                x = Integer.parseInt(lineSplits[1]);
                y = Integer.parseInt(lineSplits[2]);
                w = Integer.parseInt(lineSplits[3]);
                h = Integer.parseInt(lineSplits[4]);
            }
            catch (Exception e)
            {
                System.out.println(
                        String.format(
                          "Rectangle not removed: (%s, %s, %s, %s)",
                          lineSplits[1], lineSplits[2], 
                          lineSplits[3], lineSplits[4]));
                return false;
            }
            
            if (verifyRecParam(x, y, w, h, false))
            {
                rec = CustomRectangle.createNew(x, y, w, h);
                
                // create a new KVPair to hold the same rectangles
                dic = new KVPair<String, CustomRectangle>(null, rec);
                // return true if deleted
                output = database.deleteByValue(dic); 

                // if it is not in the database, show a message that 
                // the rectangle
                // is not found
                if (countNewLines(output)  == 0)
                {
                    System.out.println(
                            String.format("Rectangle not removed: "
                                    + "(%s, %s, %s, %s)", x, y, w, h));
                    return false;
                }
                // if it's deleted, show a message that the rectangle 
                // is deleted
                else
                {
                    System.out.print(output);
                    return true;
                }
            }
            else
            {
                // if it is not a region and user defined a name
                System.out.println(
                        String.format(
                             "Rectangle rejected: (%d, %d, %d, %d)",
                             x, y, w, h));
                return false;
            }
        }
        else
        {
            System.out.println(
                    String.format(
                         "Rectangle not removed: (%s)",
                         Arrays.toString(lineSplits).
                         replaceFirst("\\[", "").
                         replaceFirst("\\]", "").
                         replaceFirst("remove, ", "").
                         replaceFirst(",", "")));
            return false;
        }
    }
    
    /**
     * search intersecting entries with a region
     * @param lineSplits is the commandLine array
     * @return true if entries were found, false otherwise
     */
    protected boolean regionSearchCase(String[] lineSplits)
    {
        String output = "";
        int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;
        KVPair<String, CustomRectangle> dic;
        CustomRectangle rec;
        
        if (lineSplits.length != 5)
        {
            System.out.println(
                    String.format(
                         "Rectangle rejected: (%s)",
                         Arrays.toString(lineSplits)).
                        replaceFirst("\\[", "").
                        replaceFirst("\\]", "").
                        replaceFirst("regionsearch, ", ""));
            return false;
        }
        try
        {
            x = Integer.parseInt(lineSplits[1]);
            y = Integer.parseInt(lineSplits[2]);
            w = Integer.parseInt(lineSplits[3]);
            h = Integer.parseInt(lineSplits[4]);
        }
        catch (Exception e)
        {
            System.out.println(
                    String.format(
                         "Rectangle rejected: (%s, %s, %s, %s)",
                         lineSplits[1], lineSplits[2], lineSplits[3],
                         lineSplits[4]));
            return false;
        }
        if (verifyRecParam(x, y, w, h, true))
        {
            rec = CustomRectangle.createNew(x, y, w, h);

            dic = new KVPair<String, CustomRectangle>(null, rec);
            output = database.findIntersections(dic);
            System.out.println("Rectangles intersecting region (" + 
                    rec.toString() + "):");
            System.out.print(output);
            return true;
        }
        else
        {
            // if it is a region, then user doesn't care about its name
            System.out.println(
                    String.format(
                            "Rectangle rejected: (%d, %d, %d, %d)",
                            x, y, w, h));
            return false;
        }
    }
    
    /**
     * get all the intersecting pairs (duplicates are allowed)
     * @return true if intersections found
     */
    protected boolean intersectedCase()
    {
        String output = "";
        output = database.findIntersectingPairs();
        if (countNewLines(output) <= 1)
        {
            System.out.println("Intersection pairs:");
            System.out.print(output);
        }
        else
        {
            System.out.println("Intersections pairs:");
            System.out.print(output);
        }
        return true;
    }
    
    
    /**check if a string has only letters, numbers or underscore
     * 
     * @param s is the string the check is running against
     * @return true if it only have letters, numbers, or underscore, and
     * false otherwise
     */
    private static boolean validateString(String s)
    {
        //search string by regex
        return s.matches("^[a-zA-Z0-9_]*$");
    }
    
    
    // ----------------------------------------------------------
    /**
     * verify the input parameters
     * @param n is the rectangle name
     * @param x is the x-coordinate value of the top left point 
     * @param y is the y-coordinate value of the top left point 
     * @param w is the width value of the rectangle
     * @param h is the height value of the rectangle
     * @return (boolean) valid parameters
     */
    protected boolean verifyRecParam(String n, int x, int y, int w, int h) {

        return !((!Character.isLetter(n.charAt(0))) ||
                (!validateString(n)) || 
                x < 0 || y < 0 || w <= 0 || 
                h <= 0 || 
                x + w > 1024 ||
                y + h > 1024);
    }
    
    // ----------------------------------------------------------
    /**
     * verify the input parameters
     * @param x is the x-coordinate value of the top left point 
     * @param y is the y-coordinate value of the top left point 
     * @param w is the width value of the rectangle
     * @param h is the height value of the rectangle
     * @param isRegion true if the rectangle is considered a region, false if
     * considered a window
     * @return (boolean) valid parameters
     */
    protected boolean verifyRecParam(int x, 
            int y, int w, int h, boolean isRegion) {
        if (isRegion)
            return !(w <= 0 || h <= 0);
        else
            return !(x < 0 || y < 0 || w <= 0 || 
                    h <= 0 ||
                    x + w > 1024 ||
                    y + h > 1024);
    }
}
