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

    public void allKeys() {
         if(threshold<=1000)
            exArray.getSortedKeys();
         else
             doubleAVL.preorder();
    }

    // Add with generated key
    public void add(long key, String value) {
        if(threshold<=1000)
            exArray.insert(key, value);
        else
            doubleAVL.insert(key, value);
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

    public void getValues(long key) {
         if(threshold<=1000)
            exArray.getValue(key);
         else
             doubleAVL.get_value(key);
    }

//    public long nextKey(long key) {
        // if(threshold<=1000)
//            return exArray.successor(key);
        // else
//             doubleAVL.succesor(key);
//    }

    public long prevKey(long key) {
        // if(threshold<=1000)
            return exArray.predecessor(key);
        // else
            // doubleAVL.predecessor(key);
    }

    public void rangeKey(long key1, long key2) {
        // if(threshold<=1000)
//            return exArray.range(key1, key2);
//         else
             doubleAVL.range(key1, key2);
    }
}

public class EHITS {
    public static void main(String[] args) {
        ElasticERL a = new ElasticERL();
        a.SetEINThreshold(1500);
        a.add("a");
//        a.add(10, "z");
        a.add("b");
        a.add("c");
        a.add("d");
        a.add("e");
        a.add("f");
        a.add("g");
        a.add("h");
        a.add("i");
        a.add("i");
        a.add("j");
        a.add("k");
        a.add("l");

        a.allKeys();
        System.out.println();
//        System.out.println();
//        a.getValues(22);
//        a.remove(7);
//        a.allKeys();
        a.rangeKey(1,10);



    }
}
