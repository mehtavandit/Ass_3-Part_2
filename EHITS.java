public class EHITS {

    long size;
    long key = 00000000;
    long creationOrder = 0;
    ExpandableArray object_array = null;
    AVLTreeHashTable object1_AvlHash = null;
    public void SetEINThreshold (long Size) {
        this.size = Size;


        if(size<=1000){
            object_array = new ExpandableArray((int) size);
        }
        else{
            object1_AvlHash = new AVLTreeHashTable();
        }
    }

    public long generate(){
        return key++;
    }

    public void allKeys(){

    }

    public void add(long key, String value){
        if(size<=1000){
            object_array.insert(generate(), value);
        }
        else{
            object1_AvlHash.insert(generate(), value);
        }
    }

    public void remove(long key){
        if(size<=1000){
//            object_array.delete(key);
        }
        else{
            object1_AvlHash.delete(key);
        }
    }

    public void getValues(long key){
        if(size<=1000){
            object_array.search(key);
        }
        else{
            object1_AvlHash.get_value(key);
        }

    }

    public static void main(String[] args) {
        EHITS a = new EHITS();
        a.SetEINThreshold(50);
        a.add(12,"v");
    }


}
