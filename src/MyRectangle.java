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


import java.util.UUID;

// -------------------------------------------------------------------------
/**
 * Create a custom rectangle class
 *
 * @author Ahmed (AhmedAredah)
 * @version Aug 28, 2022
 */
public class MyRectangle implements Comparable<MyRectangle> 
{
    private int xCoord;
    private int yCoord;
    private int width;
    private int height;
    private UUID uniqueKey;  // this is the only way to differentiate between 
                             // two instances which have the same properties

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new Rectangle1 object.
     * 
     * @param x    : the top left corner x coordinate
     * @param y    : the top left corner y coordinate
     * @param w    : width of the rectangle
     * @param h    : height of the rectangle
     */
    private MyRectangle(int x, int y, int w, int h) {
        // assign all values to the instance
        this.setX(x);
        this.setY(y);
        this.setW(w);
        this.setH(h);
        // set a unique ID for comparison later.
        // the only way to override the equals method
        uniqueKey = UUID.randomUUID();
    }

    /**
     * create a new Rectangle1 object.
     * 
     * @param x        : the top left corner x coordinate
     * @param y        : the top left corner y coordinate
     * @param w        : width of the rectangle
     * @param h        : height of the rectangle
     * @return new rectangle if inputs are valid, otherwise null
     */
    public static MyRectangle createNew(int x, int y, int w, int h)
    {

        return new MyRectangle(x, y, w, h);
    }

    // ----------------------------------------------------------
    /**
     * get the x coordinate of the top left point
     * 
     * @return (int) x value
     */
    public int getX() {
        return this.xCoord;
    }

    // ----------------------------------------------------------
    /**
     * set the y coordinate of the top left point
     * 
     * @param x is the x value
     */
    public void setX(int x) {
        this.xCoord = x;
    }

    // ----------------------------------------------------------
    /**
     * get the y coordinate of the top left point
     * 
     * @return (int) y
     */
    public int getY() {
        return this.yCoord;
    }

    // ----------------------------------------------------------
    /**
     * set the y coordinate of the top left point
     * 
     * @param y is the y value
     */
    public void setY(int y) {
        this.yCoord = y;
    }

    // ----------------------------------------------------------
    /**
     * get the width of the instance of type rectangle
     * 
     * @return (int) width
     */
    public int getW() {
        return this.width;
    }

    // ----------------------------------------------------------
    /**
     * set the width of the instance of type rectangle
     * 
     * @param w is the width value
     */
    public void setW(int w) {
        this.width = w;
    }

    // ----------------------------------------------------------
    /**
     * get the height of the instance of type rectangle
     * 
     * @return (int) height
     */
    public int getH() {
        return this.height;
    }

    // ----------------------------------------------------------
    /**
     * set the height of the instance of type rectangle
     * 
     * @param h is the height value
     */
    public void setH(int h) {
        this.height = h;
    }

    // ----------------------------------------------------------
    /**
     * get the x coordinate of the bottom right point
     * rectangle
     * 
     * @return (int) x
     */
    public int getFarX() {
        return this.xCoord + this.width;
    }

    // ----------------------------------------------------------
    /**
     * get the y coordinate of the bottom right point
     * rectangle
     * 
     * @return (int) y
     */
    public int getFarY() {
        return this.yCoord + this.height;
    }

    // ----------------------------------------------------------
    /**
     * get the area of the instance of type rectangle
     * 
     * @return (int) area
     */
    public int getArea() {
        return this.width * this.height;
    }



    // ----------------------------------------------------------
    /**
     * check if another rectangle intersect with this rectangle.
     * 
     * @param other : the other rectangle
     * @return (boolean) isIntersected
     */
    public boolean doInterset(MyRectangle other) {
        return !(this.getX() >= other.getFarX() || 
                this.getFarX() <= other.getX() || 
                this.getY() >= other.getFarY() ||
                this.getFarY() <= other.getY());
    }

    // ----------------------------------------------------------
    /**
     * check if another rectangle has the same dimensions as this rectangle.
     * 
     * @param other : the other rectangle
     * @return true if both rectangles have the same dimensions
     */
    protected boolean hasSameProperties(MyRectangle other) {
        // if they have the same x,y,w, and h then they are the same
        return (this.getX() == other.getX() && 
                this.getY() == other.getY() && 
                this.getW() == other.getW() && 
                this.getH() == other.getH());

    }

    // ----------------------------------------------------------
    /**
     * compares another rectangle to this rectangle.
     *
     * @param other : the other rectangle
     * @return o if equal, -1 if far, 1 if intersects,
     *         and 0 if has the same properties.
     */
    @Override
    public int compareTo(MyRectangle other) {
        final int far = -1;
        final int hasSameProp = 0;
        final int intersect = 1;
        
        if (other == null) {
            throw new RuntimeException("NullPointerException");
        }
        // if they have the same properties, return true
        if (this.hasSameProperties(other)) {
            return hasSameProp;
        }
        // if they intersect each other
        if (this.doInterset(other)) {
            return intersect;
        }
        // if they are far from each other
        return far;
    }

    // ----------------------------------------------------------
    /**
     * check if two rectangles are the same
     * 
     * @param obj of type rectangle
     * @return true if both rectangles have the pointer
     */
    @Override
    public boolean equals(Object obj) {
        // if null
        if (obj == null) {
            return false;
        }
        // if the same instance
        if (this == obj) {
            return true;
        }
        // cast it and compare UUID to UUID
        // we can have multiple rectangle with the same values
        // that's why we cannot compare them by their properties
        // neither by their hash values.
        MyRectangle other = (MyRectangle) obj;
        return (this.uniqueKey.compareTo(other.uniqueKey) == 0);
    }


    
    /**
     * gives the rectangle info in text
     * @return rectangle info 
     */
    @Override
    public String toString() {
        return String.format("%d, %d, %d, %d", 
                this.xCoord, this.yCoord, this.width, this.height);
    }

}
