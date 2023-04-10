public class EHITS {

    int size;
    long newEin = 12345678;
    AVL tree = null;

    public EHITS(){
        tree = new AVL();
    }
    public void SetEINThreshold (int size){
        this.size = size;
    }

    public long generate(){
        return newEin++;
    }

    public void add(long key, String value ){
        tree.insert(key, value);
    }

    public void display(){
        tree.preorder(tree.root);
    }

    public void remove(long key){
        tree.delete(key);
    }

    public void getValues(long key){
        tree.search(key);
    }

}
