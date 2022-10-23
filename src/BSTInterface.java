
/**
 * define the BST interface
 * @author Ahmed (AhmedAredah)
 * @version oct 15, 2022
 * @param <T> is the type
 */
public interface BSTInterface<T extends Comparable<T>>
                    extends Iterable<Node<T>> {
    /**
     * clears the tree
     */
    public void clear();  // clears the tree

    /**
     * get the number of nodes in the tree
     * @return the number of nodes in the tree
     */
    public int size();

    /**
     * check if the tree is empty 
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() ;   // check if the tree is empty 
    
    /**
     * insert the given value in the BST
     * @param value is the passed value
     */
    public void insert(T value) ;  

    /**
     * removes the given value from the BST
     * @param value is the passed value
     * @return the node saved value
     */
    public T remove(T value);
      
    /**
     * removes the given node from the BST
     * @param nodeToRemove the passed node
     * @return the node saved value
     */
    public T removeNode(Node<T> nodeToRemove);


   
    /**
     * searches for a given value in the tree
     * @param value the passed value
     * @return the node if found, null if not found
     */
    public Node<T> find(T value);

    /**
     * print the entirety of the BST (inorder traversal) 
     */
    public void print() ;
}
