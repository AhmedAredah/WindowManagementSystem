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


import java.util.Arrays;
/**
 * interpret the commands
 * 
 * @author Ahmed (AhmedAredah)
 * @version Aug 28, 2022
 */
public class CommandInterpreter {

    private World world;

    /**
     * initialize an interpreter
     */
    public CommandInterpreter() {
        world = new World();
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
        boolean result;
        KVPair<String, MyRectangle> dic;
        MyRectangle rec;
        // if number of passed values is not right
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
        // try to cast variables
        try
        {
            n = lineSplits[1];
            x = Integer.parseInt(lineSplits[2]);
            y = Integer.parseInt(lineSplits[3]);
            w = Integer.parseInt(lineSplits[4]);
            h = Integer.parseInt(lineSplits[5]);
        }
        // if could not cast variables
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
            rec = MyRectangle.createNew(x, y, w, h);

            // create a new KVPair to hold the same rectangles
            dic = new KVPair<String, MyRectangle>(n, rec);
            // insert the dictionary to the database
            result = world.insert(dic);
            if (result) {
                output = "Rectangle accepted: ";
                output += String.format("(%s, %s, %s, %s, %s)", n, x, y, w, h);
                System.out.println(output);
            }
            else {
                // if it is not a region and user defined a name
                System.out.println(
                        String.format(
                             "Rectangle rejected: (%s, %d, %d, %d, %d)",
                             n, x, y, w, h));
            }

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
        // print the database        
        world.printData();
        

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
            output = world.findElements(n);
            if (countNewLines(output) == 0)
            {
                System.out.println("Rectangle not found: " + n + "");
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
                         "Rectangle not found: %s",
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
        // remove by name
        if (lineSplits.length == 2)
        {
            return removeByName(lineSplits);
        }
        // remove a rectangle by its dimensions
        else if (lineSplits.length == 5)
        {
            return removeByDim(lineSplits);
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
     * remove a rectangle by its name from database
     * @param lineSplits is the arguments 
     * @return true if removed, false otherwise
     */
    private boolean removeByName(String[] lineSplits) {
        String output;
        String n = "";
        
        n = lineSplits[1];
        // return true if deleted
        output = world.delete(new KVPair<String, MyRectangle>(n, null));
        // if it is not in the database, 
        // do not show a message that the rectangle
        // is not found
        
        if (output == null)
        {
            System.out.println("Rectangle rejected: (" + n + ")");
            return false;
        }
        // if it's deleted, do not show a message that
        // the rectangle is deleted
        else
        {
            return true;
        }
    }
    
    /**
     * remove a rectangle by its dimensions from database
     * @param lineSplits is the arguments 
     * @return true if removed, false otherwise
     */
    private boolean removeByDim(String[] lineSplits) {
        String output;
        int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;
        KVPair<String, MyRectangle> dic;
        MyRectangle rec;
        // try to cast passed values
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
                      lineSplits[1], lineSplits[2], 
                      lineSplits[3], lineSplits[4]));
            return false;
        }
        // verify if the passed values are correct
        if (verifyRecParam(x, y, w, h, false))
        {
            rec = MyRectangle.createNew(x, y, w, h); // create new rectangle
            
            // create a new KVPair to hold the same rectangles
            dic = new KVPair<String, MyRectangle>(null, rec);
            // return true if deleted
            output = world.deleteByValue(dic); 

            // if it is not in the database, show a message that 
            // the rectangle is not found
            if (output == null)
            {
                System.out.println(
                        String.format("Rectangle rejected: "
                                + "(%s, %s, %s, %s)", x, y, w, h));
                return false;
            }
            // if it's deleted, don't show a message that the rectangle 
            // is deleted
            else
            {
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
        KVPair<String, MyRectangle> dic;
        MyRectangle rec;
        
        if (lineSplits.length != 5)
        {
            // if the number of passed values is not right
            // do not show any warning
            return false;
        }
        // try to cast passed values
        try
        {
            x = Integer.parseInt(lineSplits[1]);
            y = Integer.parseInt(lineSplits[2]);
            w = Integer.parseInt(lineSplits[3]);
            h = Integer.parseInt(lineSplits[4]);
        }
        catch (Exception e)
        {
            // do not show any warning (Required)
            return false;
        }
        // check if passed values are right
        if (verifyRecParam(x, y, w, h, true))
        {
            rec = MyRectangle.createNew(x, y, w, h); // create new rectangle

            dic = new KVPair<String, MyRectangle>(null, rec); // new KVPair
            output = world.findIntersections(dic);  // get intersections in BST
            if (countNewLines(output) > 0) {
                System.out.println("Rectangles intersecting region (" + 
                        rec.toString() + "):");
                System.out.print(output);
                return true;
            }
            else {
                return false;
            }
        }
        else
        {
            return false; // if passed values are not correct
        }
    }
    
    /**
     * get all the intersecting pairs (duplicates are allowed)
     * @return true if intersections found
     */
    protected boolean intersectedCase()
    {
        String output = "";
        output = world.findIntersectingPairs();
        if (countNewLines(output) < 1)
        {
            System.out.println("Intersection pairs:");
        }
        else
        {
            System.out.println("Intersection pairs:");
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
        if (isRegion) {
            return !(w <= 0 || h <= 0);
        }
        else {
            return !(x < 0 || y < 0 || w <= 0 || 
                    h <= 0 ||
                    x + w > 1024 ||
                    y + h > 1024);
        }
    }

}
