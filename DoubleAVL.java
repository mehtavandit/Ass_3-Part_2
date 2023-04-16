import java.security.spec.ECField;

public class DoubleAVL {
    static long creationOrder = 00000000;

//    HashTable hTable;
    AVLTree creationOrder_tree;
    AVLTree key_tree;

    /**
     * Intializes two objects of Class AVLTree
     */
    DoubleAVL() {
//      hTable = new HashTable();
      creationOrder_tree = new AVLTree();
      key_tree = new AVLTree();
    }

    /**
     * @param key
     * @param value
     * Inserts the key and value in both the trees.
     */
    public void insert(long key, String value){
        if((key_tree.search(key_tree.root, key)==null)){

            creationOrder_tree.insert(creationOrder, key, value);
            key_tree.insert(key, creationOrder, value );
            creationOrder = creationOrder + 1;
        }
        else{
//            System.out.println(key);
            System.out.println(key+" Key already exists");


        }


    }

    /**
     * @param key
     * Deletes the particular key and value from both the trees.
     */
    public void delete(long key){
        try{
            long x = key_tree.search(key_tree.root, key).storedData;
            key_tree.delete(key);
            creationOrder_tree.delete(x);
        }
        catch (Exception e){
            System.out.println("The key "+key+" is not present");
        }


    }

    /**
     * @param key
     * Prints the value associated with a given key.
     */
    public void get_value(long key){
        AVLTree.BinaryNode node = key_tree.search(key_tree.root,key);
        if(node != null){
            System.out.println(node.value);
        }
        else{

            System.out.println(key+" No such key found");
        }

    }

    /**
     * Prints the inorder traversal.
     */
    public void preorder(){
//        creationOrder_tree.inorder(creationOrder_tree.root);
//        System.out.println("\n");
        key_tree.inorder(key_tree.root);
        System.out.println();

    }

    /**
     * @param key
     * Gives the previous key present in chronological order.
     */
    public void predecessor(long key) {
        try{
            long x = key_tree.search(key_tree.root, key).storedData; //creation order;
            for (long i = x - 1; i > 0; i--) {
                AVLTree.BinaryNode y = creationOrder_tree.search(creationOrder_tree.root, i);
                if (y != null) {
                    System.out.println("The previous key is " + y.storedData);
                    return;
                }
            }
            System.out.println("No previous key found");
        }
        catch (Exception e){
            System.out.println("The key "+key+" is not present");
        }


    }

    /**
     * @param key1
     * @param key2
     * Gives the number of keys present between key1 and key2.
     */
    public void range(long key1, long key2){
        long counter = key_tree.range_keys(key_tree.root,key1, key2);
        if(counter == 0){
            System.out.println(counter);
        }
        else{
            System.out.println(counter-2);
        }

    }

    /**
     * @param key
     * Gives the next key present in chronological order.
     */
    public void successor(long key){
        try{
            long x = key_tree.search(key_tree.root, key).storedData; //creation order
            for(long i = x+1; i<creationOrder;i++){
                AVLTree.BinaryNode y = creationOrder_tree.search(creationOrder_tree.root, i);
                if(y!=null){
                    System.out.println("The next key is "+y.storedData);
                    return;
                }
            }
            System.out.println(key+ " No Next key found");
        }
        catch (Exception e){
            System.out.println("The key "+key+" is not present");
        }


    }
}

