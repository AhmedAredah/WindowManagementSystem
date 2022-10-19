import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

/**
 * runs the command reader
 * 
 * @author Ahmed (AhmedAredah)
 * @version Aug 28, 2022
 */

public class Rectangle1 {
    /**
     * 
     * @param fileName is the file location that have the commands
     * @return true if executed successfully, false otherwise
     * @throws FileNotFoundException
     */
    protected static boolean readLines(String fileName)
            throws FileNotFoundException {
        String line;
        CommandInterpreter cmdInt = new CommandInterpreter();

        String[] lineSplits;

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                // retrieve the next line from the file
                line = scanner.nextLine();
                // remove all tabs if exist
                line = line.trim().replaceAll("\\t+", " ");
                // remove all the extra spaces if exit
                line = line.trim().replaceAll("\\s+", " ");
                if (line.length() == 0)
                    continue;
                // split the line into arguments
                lineSplits = line.split(" ");
                // interpret the commands
                cmdInt.interpretLine(lineSplits);
            }
            scanner.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * runs the scanner on the file
     * 
     * @param file is the file location that have the commands
     * @return true if executed successfully, false otherwise
     * @throws FileNotFoundException
     */
    protected boolean runScanner(String file) throws FileNotFoundException {
        return readLines(file);
    }

    /**
     * The entry point of the program
     * 
     * @param args : an array of strings
     * @throws FileNotFoundException : if the file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = args[0]; // file name is passed here

        readLines(fileName);

    }
}
