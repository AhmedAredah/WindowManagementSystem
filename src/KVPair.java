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
        if (it == null) {
            return -5; //invalid input
        }
        if (it.key() == null) {
            return -5;
        }
        return theKey.compareTo(it.key());
    }

    /**
     * Compare against a key
     * @param it : the other key value
     * @return -5 if null and 0 if equal
     */
    public int compareTo(K it)
    {
        if (it == null) {
            return -5; //invalid input
        }
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
     * Compares the values of two KVPairs
     * @param it : the other KVPair for comparison
     * @return 0 if equal
     */
    public int compareValues(E it)
    {
        return theVal.compareTo(it);
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
        if (theVal == null) {
            return (String)theVal;
        }
        if (theKey == null) {
            return theVal.toString();
        }
        return theKey.toString() + ", " + theVal.toString();
    }
    
    /**
     * compare this KVPaie value to another value
     * @param it is the other value for comparison
     * @return true if they are equal, false otherwise
     */
    public boolean equalValues(KVPair<K, E> it) {
        return this.value().equals(it.value());
    }
    
    /**
     * compare this KVPair to another
     * @param other is the other KVPaie
     * @return true if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        // if null
        if (other == null) {
            return false;
        }
        // if the same instance
        if (this == other) {
            return true;
        }
        @SuppressWarnings("unchecked")
        KVPair<K, E> temp = (KVPair<K, E>) other;
        return (this.value().equals(temp.value()) && 
                this.key().equals(temp.key()));
    }
    
}
