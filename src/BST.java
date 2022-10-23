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
 * Create a Binary Search Tree (BST)
 *
 * @author Ahmed (AhmedAredah)
 * @version Oct 11, 2022
 * @param <T> is the BST type
 */
public class BST<T extends Comparable<T>> implements BSTInterface<T> {

    private Node<T> root = null; // the start point of the tree

    /**
     * insert a value to the tree
     * 
     * @param theRoot is the root of the tree
     * @param it   is the passed value
     * @return the new root tree
     */
    private Node<T> insertUtil(Node<T> theRoot, T it) {
        if (theRoot == null) { // if null, attach a new node
            return new Node<T>(it);
        }
        if (it.compareTo(theRoot.value()) < 0) {  // if less, go left
            theRoot.setLeft(insertUtil(theRoot.left(), it));
        }
        else { // if higher, go right
            theRoot.setRight(insertUtil(theRoot.right(), it));
        }
        return theRoot;  // return the tree
    }

    /**
     * insert a new node to the BST
     * 
     * @param it of type KVPair is the new node to be inserted
     */
    @Override
    public void insert(T it) {
        this.root = insertUtil(this.root, it);
    }
    
    /**
     * find the depth of a particular node
     * 
     * @param theRoot is the starting point of the BST
     * @param compareNode is the look-for node
     * @return the depth of that node
     */
    private int findDepth(Node<T> theRoot, Node<T> compareNode) {
        if (theRoot == null) {
            return 0;
        }
        if (theRoot == compareNode) { // if compareNode found, return 1
            return 1;
        }
        int left = findDepth(theRoot.left(), compareNode); // depth of left
        if (left != 0) { // if not found in left, return depth of root
            return left + 1;
        }
        int right = findDepth(theRoot.right(), compareNode); // depth of right
        if (right != 0) { // if not found in right, return depth of root
            return right + 1;
        }
        return 0;
    }

    // get the in-order format of the tree values
    private String dumpUtil(Node<T> theRoot) {
        String output = ""; // placeholder for output string
        if (theRoot == null) {
            return "";
        }
        output += dumpUtil(theRoot.left());
        output += "Node has depth "
                + (findDepth(this.root, theRoot) - 1) + ", Value ("
                + theRoot.value().toString() + ")\n";
        output += dumpUtil(theRoot.right());
        return output;
    }

    /**
     * pre-order traverse the tree and print its content
     */
    @Override
    public void print() {
        String output = "BST dump:\n";
        if (root == null) { // if the BST root is null
            output += "Node has depth 0, Value (null)\n";
        }
        else {
            output += dumpUtil(root);
        }
        
        output += "BST size is: " + size();
        System.out.println(output);
    }

    /**
     * convert the tree to a string in pre-order format
     */
    @Override
    public String toString() {
        return dumpUtil(root);
    }

    /**
     * Check if the tree is empty or not
     */
    @Override
    public boolean isEmpty() {
        return (this.root == null);
    }

    private Node<T> findUtile(Node<T> theRoot, T it) {
        if (theRoot == null) { // the root is null
            return null;
        }
        if (theRoot.equals(it)) { // the value it is found
            return theRoot;
        }
        // right is greater than root
        if (theRoot.compareTo(it) < 0) {
            return findUtile(theRoot.right(), it);
        }
        // left is smaller than or equal to root
        return findUtile(theRoot.left(), it);
    }

    /**
     * traversing the tree to find a value
     * 
     * @return the node that contains the value
     */
    @Override
    public Node<T> find(T value) {
        return findUtile(this.root, value);
    }

    /**
     * clear all the tree structure and content
     */
    @Override
    public void clear() {
        this.root = null; // reset the root to null
    }

    private int countNode(Node<T> theRoot) {
        if (theRoot == null) {
            return 0;
        }
        return 1 + countNode(theRoot.left()) + countNode(theRoot.right());
    }

    /**
     * return the number of nodes in the tree
     * 
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return countNode(this.root);
    }

    private Node<T> removeUtile(Node<T> theRoot, T value, Node<T> oldValue) {
        if (theRoot != null) {
            T rootData = theRoot.value();
            int comp = value.compareTo(rootData);  // comparison

            if (comp == 0) {  // if node that has the value is found
                oldValue.setValue(rootData);   // set the old value to return
                theRoot = removeNodeFromTree(theRoot);  // replace node
            } 
            else if (comp < 0) {  // if less, go left
                Node<T> leftChild = theRoot.left();  // get left
                theRoot.setLeft(removeUtile(leftChild, value, oldValue));
            } 
            else {
                Node<T> rightChild = theRoot.right();  // get right
                theRoot.setRight(removeUtile(rightChild, value, oldValue));
            }
        }
        return theRoot; // return new after deletion
    }

    /**
     * remove a tree node that contains a value from the tree
     */
    @Override
    public T remove(T value) {
        Node<T> oldValue = new Node<T>(null); // initialize a temporary dummy
        this.root = removeUtile(this.root, value, oldValue); // remove the node
        return oldValue.value();  // return the old value
    }

    private Node<T> removeNodeFromTree(Node<T> nodeToRemove) {
        // Case 1: a node has two children
        if (nodeToRemove.left() != null && nodeToRemove.right() != null) {
            Node<T> leftSub = nodeToRemove.left(); // get left sub tree
            Node<T> largestNode = findLargest(leftSub); // find largest in left

            // replace value in root
            nodeToRemove.setValue(largestNode.value());

            // remove node that was used before
            nodeToRemove.setLeft(removeLargest(leftSub));
        }
        // Case 2: a node has only right children
        else if (nodeToRemove.right() != null) {
            nodeToRemove = nodeToRemove.right();
        }
        // Case 3: a node has only left children
        else {
            nodeToRemove = nodeToRemove.left();
        }

        return nodeToRemove;
    }
    
    // find a node passed by reference and delete it
    private Node<T> remove(Node<T> theRoot, Node<T> nodeToRemove) {
        if (theRoot == null) {  // if root is null, return null
            return null;
        }
        if (theRoot == nodeToRemove) { // if the nodeToRemove is found
            return removeNodeFromTree(nodeToRemove); // remove and reset BST
        }
        theRoot.setLeft(remove(theRoot.left(), nodeToRemove)); // get left
        theRoot.setRight(remove(theRoot.right(), nodeToRemove)); // get right
        return theRoot; // return changed BST
    }
    
    
    /**
     * remove node from a tree
     * 
     * @param nodeToRemove is the node that should be deleted
     */
    @Override
    public T removeNode(Node<T> nodeToRemove) {
        T oldValue = nodeToRemove.value(); // old value of node before delete
        this.root = remove(this.root, nodeToRemove); // remove node by ref
        return oldValue; // return old value
    }

    private Node<T> findLargest(Node<T> theRoot) {
        if (theRoot.right() != null) {
            theRoot = findLargest(theRoot.right());
        }
        return theRoot;
    }

    private Node<T> removeLargest(Node<T> theRoot) {
        if (theRoot.right() != null) {  // if there is right
            Node<T> rightChild = theRoot.right();
            Node<T> anotherRoot = removeLargest(rightChild);
            theRoot.setRight(anotherRoot);
        } 
        else {  // left
            theRoot = theRoot.left();
        }
        return theRoot;
    }

    @Override
    public BSTIterator<T> iterator() {
        return new BSTIterator<T>(this.root);
    }
}
