
/**
 * define a node interface for BST's
 * 
 * @author Ahmed
 * @version oct 10, 2022
 *
 * @param <T> is any object
 */
public interface NodeInterface<T extends Comparable<T>> {

    /**
     * sets the value of the node
     * 
     * @param value is the passed value
     */
    public void setValue(T value);

    /**
     * sets the right node
     * 
     * @param right is the node link
     */
    public void setRight(Node<T> right);

    /**
     * sets the left node
     * 
     * @param left is the node link
     */
    public void setLeft(Node<T> left);

    /**
     * get the right node
     * 
     * @return right node
     */
    public Node<T> right();

    /**
     * get the left node
     * 
     * @return left node
     */
    public Node<T> left();

    /**
     * get the saved value of the node
     * 
     * @return the data of the node
     */
    public T value();

    /**
     * check if the node is a leaf node
     * 
     * @return true if a leaf node, false otherwise
     */
    public boolean isLeaf();


}
