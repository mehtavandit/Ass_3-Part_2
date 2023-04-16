import java.util.ArrayList;

class ElasticERL {
    long threshold;
    long newKey = 00000000;
    long elements_inserted = 0;
    long creationOrder = 0;
    ExpandableArray exArray = null;
    DoubleAVL doubleAVL = null;

    /**
     * @param threshold
     * Sets the maximum size of the data structure.
     */
    public void SetEINThreshold (long threshold) {
        this.threshold = threshold;
        if(threshold<=1000)
            exArray = new ExpandableArray((int) threshold);
        else
            doubleAVL = new DoubleAVL();
    }

    /**
     * Generates a 8 digit EIN key.
     * @return
     */
    public long generate() {

        return newKey++;
    }

    /**
     * return all keys in sorted order.
     */
    public void allKeys() {
         if(threshold<=1000)
            exArray.getSortedKeys();
         else
             doubleAVL.preorder();
    }

    /**
     * @param key
     * @param value
     * Inserts the key passed as an argument and a value.
     */
    // Add with generated key
    public void add(long key, String value) {
        if(elements_inserted<threshold){
            if(threshold<=1000)
                exArray.insert(key, value);
            else
                doubleAVL.insert(key, value);
            elements_inserted++;
        }
        else{
            System.out.println("You cannot enter "+(elements_inserted+1)+" element");
            elements_inserted++;
        }

    }

    /**
     * @param value
     * Generates the key using generate() method, and inserts the key and a value.
     */
    // Add with user-specified key
    public void add(String value) {
        if(elements_inserted<threshold){
            if(threshold<=1000)
                exArray.insert(generate(), value);
            else
                doubleAVL.insert(generate(), value);
            elements_inserted++;
        }
        else{
            System.out.println("You cannot enter "+(elements_inserted+1)+" element");
            elements_inserted++;
        }
    }

    /**
     * @param key
     * Deletes the key, and the value associated with it.
     */
    public void remove(long key) {
        if(threshold<=1000)
            exArray.delete(key);
        else
            doubleAVL.delete(key);
    }

    /**
     * @param key
     * Gives the values associated with a particular key.
     */
    public void getValues(long key) {
         if(threshold<=1000)
            exArray.getValue(key);
         else
             doubleAVL.get_value(key);
    }

    /**
     * @param key
     * Gives the next key present in chronological order.
     */
    public void nextKey(long key) {
        if(threshold<=1000)
           exArray.successor(key);
        else
            doubleAVL.successor(key);
    }

    /**
     * @param key
     * Gives the previous key present in chronological order.
     */
    public void prevKey(long key) {
        if(threshold<=1000)
            exArray.predecessor(key);
        else
             doubleAVL.predecessor(key);
    }

    /**
     * @param key1
     * @param key2
     * Gives the number of keys present between key1 and key2.
     */
    public void rangeKey(long key1, long key2) {
        if(threshold<=1000)
            exArray.range(key1, key2);
        else
            doubleAVL.range(key1, key2);
    }
}

public class EHITS {
    public static void main(String[] args) {
        ElasticERL a = new ElasticERL();
        a.SetEINThreshold(50000);
       Long[] data= Utils.getArrayFromFile("C:\\Users\\HP\\Desktop\\PPS\\Assignment 3 Part 2\\Ass_3-Part_2\\EHITS_test_files\\EHITS_test_file3.txt");
       for(int i=0; i<50000; i++){ //output must show that you cannot enter more elements
           a.add(data[i], "a");
       }
       a.allKeys();
       a.add("brand");//Will give error, as the threshold value is reached
       a.remove(0);
       a.getValues(84629786);
       a.prevKey(70147980);
       a.nextKey(70147980);
       a.rangeKey(99851347,99876866);

    }
}
