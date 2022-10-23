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

import java.util.LinkedList;

/**
 * Create a world class
 *
 * @author Ahmed (AhmedAredah)
 * @version Oct 11, 2022
 */
public class World {
    private BST<KVPair<String, MyRectangle>> database;

    /**
     * create a new instance of the world class
     */
    World() {
        database = new BST<KVPair<String, MyRectangle>>();
    }

    /**
     * insert a rectangle into the database
     * 
     * @param name is the rectangle name
     * @param r    is the rectangle
     */
    public void insert(String name, MyRectangle r) {
        KVPair<String, MyRectangle> entry = new KVPair<String, MyRectangle>(
                name, r);
        database.insert(entry);
    }

    /**
     * insert a KVPair to the BST database
     * 
     * @param entry is the KVPair of types string and rectangle to be saved
     * @return true if inserted, false if a duplicate
     */
    public boolean insert(KVPair<String, MyRectangle> entry) {
        // check if value is already in the database
        // did not implement it inside BST because I want BST to be generic 
        // and insert all values, even duplicates
        for (BSTIterator<KVPair<String, MyRectangle>> i = this.database
                .iterator(); i.hasNext();) {
            KVPair<String, MyRectangle> temp = i.next().value();
            if (temp.value().compareTo(entry.value()) == 0 
                    && temp.key().compareTo(entry.key()) == 0) {
                return false;
            }
        }
        // insert in database
        database.insert(entry);
        return true;
    }

    /**
     * get the database
     * 
     * @return database of type SkipList
     */
    public BST<KVPair<String, MyRectangle>> getDatabase() {
        return database;
    }

    /**
     * print the entire database to the console
     */
    public void printData() {
        this.database.print();
    }

    /**
     * find the first node that has the passed value
     * 
     * @param cR is the lookup rectangle
     * @return the first node that has a rectangle equals to the cR, null
     *         otherwise
     */
    public Node<KVPair<String, MyRectangle>> findByValue(MyRectangle cR) {
        // iterate through all values
        for (BSTIterator<KVPair<String, MyRectangle>> i = this.database
                .iterator(); i.hasNext();) {
            Node<KVPair<String, MyRectangle>> currentNode = i.next();
            // if value is found, return the whole node
            if (currentNode.value().compareValues(cR) == 0) {
                return currentNode;
            }
        }
        return null;
    }

    /**
     * delete the first node that has the passed value
     * 
     * @param cR is the lookup KVPair with the rectangle
     * @return the first node value as a string that has a rectangle equals to
     *         the cR, null otherwise
     */
    public String deleteByValue(KVPair<String, MyRectangle> cR) {
        Node<KVPair<String, MyRectangle>> currentNode;
        currentNode = findByValue(cR.value());  // get node that has the value
        if (currentNode == null) {
            return null;
        }
        // remove the node
        return "Rectangle removed: ("
                + database.removeNode(currentNode).toString() + ")\n";
    }

    /**
     * delete the first node that has the passed value
     * 
     * @param entry is a KVPair<String, MyRectangle> that has string of the
     *              rectangle you want to delete
     * @return result as string
     */
    protected String delete(KVPair<String, MyRectangle> entry) {
        KVPair<String, MyRectangle> current = null;
        current = database.remove(entry);
        if (current == null) {
            return null;
        }
        return "Rectangle removed: (" + current.toString() + ")\n";
    }

    /**
     * find the intersections in the database with respect to a passed rectangle
     * 
     * @param it of type KVPair<String, MyRectanlge> to find all intersections
     *           with respect to
     * @return a string of all intersecting rectangles
     */
    protected String findIntersections(KVPair<String, MyRectangle> it) {
        String intersections = "";  // placeholder for intersections
        for (BSTIterator<KVPair<String, MyRectangle>> i = this.database
                .iterator(); i.hasNext();) {
            Node<KVPair<String, MyRectangle>> temp = i.next();
            // if intersects with it rectangle
            if (temp.value().compareValues(it) >= 0) {
                intersections += "(" + temp.value().toString() + ")\n";
            }
        }
        return intersections;
    }

    /**
     * find intersecting pairs in the database
     * 
     * @return a string of all intersections
     */
    protected String findIntersectingPairs() {
        // create a placeholder for all intersections pairs
        LinkedList<LinkedList<KVPair<String, MyRectangle>>> intersections;
        intersections = new 
                LinkedList<LinkedList<KVPair<String, MyRectangle>>>();
        // iterate on all values (1st iterator)
        for (BSTIterator<KVPair<String, MyRectangle>> i = this.database
                .iterator(); i.hasNext();) {
            Node<KVPair<String, MyRectangle>> obj1 = i.next();
            // 2nd iterator
            for (BSTIterator<KVPair<String, MyRectangle>> ii = this.database
                    .iterator(); ii.hasNext();) {
                Node<KVPair<String, MyRectangle>> obj2 = ii.next();
                // if obj1 intersects obj2, check if they are already in the 
                // intersections variable, if not, add them to intersections,
                // else, ignore them
                if (obj1.value().compareValues(obj2.value()) >= 0
                        && !obj1.value().equals(obj2.value())) {
                    if (existsIn(intersections, obj1.value(), obj2.value())) {
                        continue;
                    }
                    else {
                        LinkedList<KVPair<String, MyRectangle>> temp;
                        temp = new LinkedList<KVPair<String, MyRectangle>>();
                        temp.add(obj1.value());
                        temp.add(obj2.value());
                        intersections.add(temp);
                    }
                }
            }
        }
        // covert to string
        return toString(intersections);
    }
    
    // convert the list of intersections to a string
    private String toString(
            LinkedList<LinkedList<KVPair<String, MyRectangle>>> l) {
        String intersections = "";
        for (int i = 0; i < l.size(); i++) {
            intersections += "(" + l.get(i).get(0).toString() 
                    + " | " + l.get(i).get(1).toString() + ")\n";
        }
        return intersections;
    }
    
    // check if a KVPair is inside the intersections list
    private boolean existsIn(
            LinkedList<LinkedList<KVPair<String, MyRectangle>>> l,
            KVPair<String, MyRectangle> dic1, 
            KVPair<String, MyRectangle> dic2) {
        for (int i = 0; i < l.size(); i++) {
            if ( ( l.get(i).get(0).equals(dic1) &&
                    l.get(i).get(1).equals(dic2) ) || 
                    (l.get(i).get(1).equals(dic1) &&
                    l.get(i).get(0).equals(dic2) ) ) {
                
                return true;
            }
        }
        return false;
    }

    /**
     * gets a string of a rectangle by name
     * 
     * @param rectangleName the passed rectangle name for searching
     * @return string of the rectangle value
     */
    protected String findElements(String rectangleName) {
        String foundR = "";
        KVPair<String, MyRectangle> searchRec 
                    = new KVPair<String, MyRectangle>(rectangleName, null);
        for (BSTIterator<KVPair<String, MyRectangle>> i = this.database
                .iterator(); i.hasNext();) {
            Node<KVPair<String, MyRectangle>> currentNode = i.next();
            if (currentNode.value().compareTo(searchRec) == 0) {
                foundR += ("Rectangles found: (" 
                        + currentNode.value().toString() + ")\n");
            }
        }
        return foundR;

    }

}
