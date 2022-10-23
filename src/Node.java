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
 * Create a BST Node
 *
 * @author Ahmed (AhmedAredah)
 * @version Oct 11, 2022
 * @param <T> is the node type
 */
public class Node<T extends Comparable<T>> 
                implements NodeInterface<T> {
    // ~ Fields ...................................................
    private T entry; // the node
    private Node<T> left; // list of references
    private Node<T> right;
    
    
    // ~ CONSTRUCTORS ...................................................
    
    /**
     * Create a new BST Node object.
     * 
     * @param it is the object
     */
    public Node(T it) {
        // save the it entry
        this.entry = it;
        // set right and left branches to null
        this.left = null;
        this.right = null;
    }
    
    
    // ~ OTHER METHODS ...................................................
    

    /**
     * @return the saved data of the node
     */
    public T value() { return entry; }
    
    /**
     * @param value is the new data to save in the node
     */
    public void setValue(T value) { this.entry = value; }

    /**
     * @param right sets the right node to the current node
     */
    public void setRight(Node<T> right) { this.right = right; }

    /**
     * @param left sets the left node to the current node
     */
    public void setLeft(Node<T> left) { this.left = left; }

    /**
     * @return the right node to the current node
     */
    public Node<T> right() { return right; }

    /**
     * @return the left node to the current node
     */
    public Node<T> left() { return left; }

    /**
     * check if the current node is a leaf node
     * @return true if a leaf node, false otherwise
     */
    public boolean isLeaf()
    {
        return (this.left == null && this.right == null);
    }

    /**
     * @param it is the other node to compare with
     * @return 0 if they are equal, 1 if greater, -1 if less
     */
    public int compareTo(Node<T> it) {
        return this.entry.compareTo(it.entry);
    }
    
    /**
     * @param it is the other value to compare with
     * @return 0 if they are equal, 1 if greater, -1 if less
     */
    public int compareTo(T it) {
        return this.entry.compareTo(it);
    }
    
    /**
     * @param it is the other node to compare with
     * @return true if equals, false otherwise
     */
    public boolean equals(T it) {
        return entry.equals(it);
    }

    /**
     * compares the current Node to another
     * 
     * @param it is the other value to compare with
     * @return true if equals, false otherwise
     */
    @SuppressWarnings({ "unchecked" })
    @Override
    public boolean equals(Object it) {
        // if null
        if (it == null) {
            return false;
        }
        // if the same instance
        if (this == it) {
            return true;
        }
        Node<T> temp = (Node<T>) it;
        return this.value().equals(temp.value());
    }
}
