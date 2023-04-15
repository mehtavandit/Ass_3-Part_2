import java.util.ArrayList;

class ElasticERL {
    long threshold;
    long newKey = 00000000;
    long elements_inserted = 0;
    long creationOrder = 0;
    ExpandableArray exArray = null;
    DoubleAVL doubleAVL = null;

    public void SetEINThreshold (long threshold) {
        this.threshold = threshold;
        if(threshold<=1000)
            exArray = new ExpandableArray((int) threshold);
        else
            doubleAVL = new DoubleAVL();
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

    public void nextKey(long key) {
        if(threshold<=1000)
           exArray.successor(key);
        else
            doubleAVL.successor(key);
    }

    public void prevKey(long key) {
        if(threshold<=1000)
            exArray.predecessor(key);
        else
             doubleAVL.predecessor(key);
    }

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
