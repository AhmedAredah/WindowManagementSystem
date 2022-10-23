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


import java.util.Iterator;
import java.util.LinkedList;

/**
 * Create a Binary Search Tree (BST)
 *
 * @author Ahmed (AhmedAredah)
 * @version Oct 11, 2022
 * @param <T> is the BST type
 */
public class BSTIterator<T extends Comparable<T>>
                        implements Iterator<Node<T>> {
    
    private LinkedList<Node<T>> stackLinkedList; // a placeholder for nodes
    
    private void inorderTraversal(Node<T> theRoot) {
        if (theRoot == null) {
            return;
        }
        // push all values in in-order sequence
        inorderTraversal(theRoot.left());
        stackLinkedList.push(theRoot);
        inorderTraversal(theRoot.right());
    }
    
    /**
     * construct In-Order iterator 
     * 
     * @param theRoot is the tree root
     */
    BSTIterator(Node<T> theRoot) {
        stackLinkedList = new LinkedList<Node<T>>();
        // populate/retrieve the values
        inorderTraversal(theRoot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        // check if stack is empty
        return !stackLinkedList.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node<T> next() {
        // remove last item added
        return stackLinkedList.removeLast();
    }

}
