/**
 * KVPair class definition
 * @author Ahmed (AhmedAredah)
 * @version Aug 28, 2022
 * @param <K>
 * @param <E>
 */
public class KVPair<K extends Comparable<K>, E extends Comparable<E>>
               implements Comparable<KVPair<K, E>> 
{
    private K theKey;
    private E theVal;

    /**
     * create an instance of KVPair
     * @param k is the key
     * @param v is the value
     */
    KVPair(K k, E v) 
    {
        theKey = k;
        theVal = v;
    }
      
    /**
     * Compare KVPairs
     * @param it : the other KVPair for comparison
     * @return 0 if equal
     */
    @Override
    public int compareTo(KVPair<K, E> it)
    {
        if (it == null) return -5; //invalid input
        return theKey.compareTo(it.key());
    }

    /**
     * Compare against a key
     * @param it : the other key value
     * @return -5 if null and 0 if equal
     */
    public int compareTo(K it)
    {
        if (it == null) return -5; //invalid input
        return theKey.compareTo(it);
    }

    /**
     * Compares the values of two KVPairs
     * @param it : the other KVPair for comparison
     * @return 0 if equal
     */
    public int compareValues(KVPair<K, E> it)
    {
        return theVal.compareTo(it.theVal);
    }

    /**
     * get the key value of the KVPair
     * @return theKey of type General
     */
    public K key()
    {
        return theKey;
    }
    
    /**
     * get the value of the KVPair
     * @return theVal of type General
     */
    public E value()
    {
        return theVal;
    }
  
    /**
     * print the value of the KVPair
     */
    @Override
    public String toString() 
    {
        if (theVal == null) return (String)theVal;
        if (theKey == null) return theVal.toString();
        return theKey.toString() + ", " + theVal.toString();
    }
}
