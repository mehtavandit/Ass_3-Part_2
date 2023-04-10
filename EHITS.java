class ElasticERL {
    long threshold;
    long newKey = 00000000;
    long creationOrder = 0;
    ExpandableArray exArray = null;
    AVLTreeHashTable doubleAVL = null;

    public void SetEINThreshold (long threshold) {
        this.threshold = threshold;
        if(threshold<=1000)
            exArray = new ExpandableArray((int) threshold);
        else
            doubleAVL = new AVLTreeHashTable();
    }

    public long generate() {
        return newKey++;
    }

    public long[] allKeys() {
        // if(threshold<=1000)
            return exArray.getSortedKeys();
        // else
            // doubleAVL.predecessor(key);
    }

    // Add with generated key
    public void add(long key, String value) {
        if(threshold<=1000)
            exArray.insert(key, value);
        else
            doubleAVL.insert(generate(), value);
    }

    // Add with user-specified key
    public void add(String value) {
        if(threshold<=1000)
            exArray.insert(generate(), value);
        else
            doubleAVL.insert(generate(), value);
    }

    public void remove(long key) {
        if(threshold<=1000)
            exArray.delete(key);
        else
            doubleAVL.delete(key);
    }

    public String getValues(long key) {
        // if(threshold<=1000)
            return exArray.getValue(key);
        // else
        //     return doubleAVL.get_value(key);
    }

    public long nextKey(long key) {
        // if(threshold<=1000)
            return exArray.successor(key);
        // else
            // doubleAVL.predecessor(key);
    }
    
    public long prevKey(long key) {
        // if(threshold<=1000)
            return exArray.predecessor(key);
        // else
            // doubleAVL.predecessor(key);
    }

    public long rangeKey(long key1, long key2) {
        // if(threshold<=1000)
            return exArray.range(key1, key2);
        // else
            // doubleAVL.predecessor(key);
    }
}

public class EHITS {
    public static void main(String[] args) {
        
    }
}
