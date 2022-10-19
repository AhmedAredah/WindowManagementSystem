import java.lang.reflect.Array;
import student.TestableRandom;

// -------------------------------------------------------------------------
/**
 * Create a skipList of KVPair instances
 *
 * @author Ahmed (AhmedAredah)
 * @version Aug 28, 2022
 * 
 * @param <K>
 * @param <E>
 */
public class SkipList<K extends Comparable<K>, E extends Comparable<E>> {
    // define skipNode
    // -------------------------------------------------------------------------
    /**
     * Create a new instance of a skip list node
     * 
     * @param <K>
     * @param <E>
     *
     * @author Ahmed
     * @version Aug 28, 2022
     */
    @SuppressWarnings("hiding")
    public class SkipNode<K extends Comparable<K>, E extends Comparable<E>> {
        // ~ Fields ...................................................
        private KVPair<K, E> entry; // the node
        private SkipNode<K, E>[] forward; // list of references

        // ~ Constructors .............................................
        // ----------------------------------------------------------
        /**
         * Create a new SkipNode object.
         * 
         * @param key is the Key value of the KVPair
         * @param elem is the Stored Value of the KVPair
         * @param level is the level of insertion
         */
        @SuppressWarnings({ "unchecked" })
        public SkipNode(K key, E elem, int level) {
            // instantiate a new KVPair with the provided key and element
            this.entry = new KVPair<K, E>(key, elem);
            // create an array of length (level+1) for forward array of pointers
            this.forward = new SkipNode[level + 1];
            // setting forward array values to null
            for (int i = 0; i < level; i++)
                forward[i] = null;
        }

        // ----------------------------------------------------------
        /**
         * Create a new SkipNode object.
         * 
         * @param it is the KVPair object
         * @param level is the level of insertion
         */
        @SuppressWarnings({ "unchecked" })
        public SkipNode(KVPair<K, E> it, int level) {
            // save the it entry
            this.entry = it;
            // create a forward array of length (level + 1)
            this.forward = new SkipNode[level + 1];
            // set all forward values to null
            for (int i = 0; i <= level; i++)
                forward[i] = null;
        }

        // ~Public Methods ....................................................
        // ----------------------------------------------------------
        /**
         * get the value of type KVPair
         * 
         * @return the stored value of type KVPair
         */
        public KVPair<K, E> element() {
            // return back the entry value
            return entry;
        }

        // ----------------------------------------------------------
        /**
         * print the node values
         * @return the node value
         */
        public String dump() {
            // check if the current node has both key and value
            if (this.entry != null)
                return String.format("Node has depth %o, Value (%s)",
                                this.forward.length, this.entry.toString());
            return null;
        }

        // ----------------------------------------------------------
        /**
         * refresh the nodes connections after removal at a specific level
         * 
         * @param theLevel of nodes refreshment
         */
        void refresh(int theLevel) {
            SkipNode<K, E> current = this;
            while (current != null && current.forward[theLevel] != null) {
                // get the next null pointer and assign it the next of next
                if (current.forward[theLevel].entry == null) {
                    current.forward[theLevel] = current.forward[theLevel].
                            forward[theLevel];
                    return;
                }
                // get the next node
                current = current.forward[theLevel];
            }
        }

        /**
         * gets a string of the node
         * @return a node equivalent value
         */
        @Override
        public String toString() {
            if (entry != null)
                return entry.toString();
            return "";
        }

    }

    // ~ Fields ................................................................

    private SkipNode<K, E> head;
    private int level; // saves the level of the list
    private int size; // saves the size of the list
    private student.TestableRandom rnd; // Random number

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new SkipList object.
     */
    public SkipList() {
        // instantiate an empty node and empty variables
        head = new SkipNode<K, E>(new KVPair<K, E>(null, null), 0);
        level = -1;
        this.size = 0;
        rnd = new TestableRandom(); // generate a random integer
    }

    // ~ Methods ........................................................

    /**
     * Pick a level using a geometric distribution
     * 
     * @return random integer
     */
    private int randomLevel() {
        int lev;
        for (lev = 0; Math.abs(rnd.nextInt()) % 2 == 0; lev++) {
        }
        return lev;
    }

    /**
     * Insert a KVPair into the skipList
     * 
     * @param it the new KVPair to be added to the list
     * @return true if insertion succeeded, false otherwise
     */
    public boolean insert(KVPair<K, E> it) {
        if (it == null || it.key() == null || it.value() == null)
            return false;

        int newLevel = randomLevel(); // get a random level
        Comparable<K> k = it.key(); // get the key value
        if (level < newLevel)
            adjustHead(newLevel);
        @SuppressWarnings("unchecked") // Generic array allocation
        SkipList<K, E>.SkipNode<K, E>[] update 
            = (SkipNode[]) Array.newInstance(
                    SkipList.SkipNode.class, level + 1);
        SkipList<K, E>.SkipNode<K, E> x = head; // Start at header node
        for (int i = level; i >= 0; i--) // Find insert position
        {
            while ((x.forward[i] != null) && x.forward[i].element() != null
                    && (k.compareTo(x.forward[i].element().key()) > 0))
                x = x.forward[i]; // assign next node for all levels
            update[i] = x; // Track end at level i
        }
        x = new SkipNode<K, E>(it, newLevel); // Instantiate a new skipNode
        
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who y points to
        }
        this.size++; // Increment list size
        return true;
    }
    
    // adjust the head after insertion
    private void adjustHead(int newLevel) {
        SkipNode<K, E> crnt = head;
        head = new SkipNode<K, E>(null, null, newLevel);
        for (int i = 0; i <= level; i++)
            head.forward[i] = crnt.forward[i];
        level = newLevel;
    }

    // ----------------------------------------------------------
    /**
     * Search the SkipList by the entry value Return the (first) matching 
     * element by value if one exists, null otherwise
     * 
     * @param it the KVPair entry
     * @return the SkipNode where the value is found
     */
    private SkipList<K, E>.SkipNode<K, E> searchByValue(KVPair<K, E> it) {
        if (it == null)
            return null;
        SkipList<K, E>.SkipNode<K, E> x = head.forward[0]; // Dummy header node
        while (x != null && x.element() != null) {
            if (it.compareValues(x.element()) == 0 || it.equals(x.element()))
                return x;
            x = x.forward[0];
        }
        return null;
    }

    // ----------------------------------------------------------
    /**
     * Search the skiplist by the entry key Return the (first) matching matching
     * element if one exists, null otherwise
     * 
     * @param key is for searching
     * @return the node
     */
    private SkipList<K, E>.SkipNode<K, E> search(Comparable<K> key) {
        SkipList<K, E>.SkipNode<K, E> x = head; // Dummy header node
        for (int i = level; i >= 0; i--) { // For each level...
            while ((x.forward[i] != null) && (x.forward[i].element() != null)
                    && (key.compareTo(x.forward[i].element().key()) > 0))
                x = x.forward[i];
        } // Go one last step
        x = x.forward[0]; // Move to actual value, if it exists
        if ((x != null) && (x.element() != null) && 
                (key.compareTo(x.element().key()) == 0))
            return x;
        return null;
    }

    // ----------------------------------------------------------
    /**
     * get the value from the skiplist using key value
     * 
     * @param key is for searching
     * @return a string of all values with the same key
     */
    public String findElements(K key)
    {
        String result = "";
        SkipList<K, E>.SkipNode<K, E> current = head.forward[0]; // Dummy h
        while (current != null) {
            KVPair<K, E> obj = current.entry;
            if (obj != null) {
                if (obj.compareTo(key) == 0) // if they have the same key
                {
                    result += "(" + obj.toString() + ")\n";
                }
            }
            current = current.forward[0];
        }
        return result;
    }

    // ----------------------------------------------------------
    /**
     * delete an entry by its key
     * 
     * @param key is the key value for searching
     * @return the deleted values
     */
    public String deleteByKey(Comparable<K> key) 
    {
        String result = "";
        SkipList<K, E>.SkipNode<K, E> searchedNode;

        // find the entry first
        searchedNode = search(key);
        // if the entry is not found, return false
        if (searchedNode == null)
            return result;
        
        result += "Rectangle removed: (" + searchedNode.entry.toString()
                + ")\n";
        // override the data to null
        searchedNode.entry = null;
        for (int i = 0; i < level; i++)
            head.refresh(i);
        this.size--;
        return result;
    }

    // ----------------------------------------------------------
    /**
     * delete an entry by its value
     * 
     * @param it is the KVPair entry
     * @return the deleted values
     */
    public String deleteByValue(KVPair<K, E> it) 
    {
        String result = "";
        SkipList<K, E>.SkipNode<K, E> searchedNode;
        // find the entry first
        searchedNode = searchByValue(it);
        // if the entry is not found, return false
        if (searchedNode == null)
            return result;
        result += "Rectangle removed: (" + searchedNode.entry.toString() + 
                ")\n";
        // override the data to null
        searchedNode.entry = null;
        for (int i = 0; i < level; i++)
            head.refresh(i);
        this.size--;
        return result;
    }

    // ----------------------------------------------------------
    /**
     * gets a string of the entire skipList
     * @return a list of the SkipList entries
     */
    public String dump() {
        String nodeStr;
        String result = "";

        result += String.format("Node has depth %o, Value (%s)\n", 
                this.head.forward.length,
                (String) this.head.entry.value());

        SkipList<K, E>.SkipNode<K, E> current = head.forward[0];
        while (current != null) {
            nodeStr = current.dump();
            if (nodeStr != null)
                result += nodeStr + "\n";
            current = current.forward[0];
        }
        return result;
    }
    
    /**
     * get the size of the SkipList
     * @return size
     */
    public int size()
    {
        return this.size;
    }

    // ----------------------------------------------------------
    /**
     * find intersections for a given KVPair
     * 
     * @param it is the KVPair entry
     * @return a list of all intersections
     */
    public String findIntersections(KVPair<K, E> it) {
        String intersections = "";
        if (it != null) 
        {
            SkipList<K, E>.SkipNode<K, E> current = head.forward[0]; // Dummy h
            // iterate till the end
            while (current != null) {
                KVPair<K, E> obj = current.entry;
                if (obj != null) {
                    if (it.compareValues(obj) >= 0) // if they equal/intersect
                        intersections += "(" + obj.toString() + ")\n";
                }
                current = current.forward[0];
            }

        }
        return intersections;
    }

    // ----------------------------------------------------------
    /**
     * Find all intersecting pairs.
     * 
     * @return a list of all intersections
     */
    public String findIntersectingPairs() 
    {
        String intersections = "";
        SkipList<K, E>.SkipNode<K, E> current1 = head.forward[0]; // Dummy h
        SkipList<K, E>.SkipNode<K, E> current2;
        while (current1 != null) {
            current2 = head.forward[0];
            while (current2 != null) {
                KVPair<K, E> obj1 = current1.entry;
                KVPair<K, E> obj2 = current2.entry;
                if (obj1 != null && obj2 != null) {
                    if (! obj1.equals(obj2))
                        // if they equal/intersect
                        if (obj1.compareValues(obj2) >= 0)
                            intersections += "(" + obj1.toString() + " | " + 
                                    obj2.toString() + ")\n";
                }
                current2 = current2.forward[0];
            }
            current1 = current1.forward[0];

        }
        return intersections;
    }

}
