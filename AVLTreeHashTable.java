public class AVLTreeHashTable {
    static long creationOrder = 55;

//    HashTable hTable;
    AVLTree creationOrder_tree;
    AVLTree key_tree;

    AVLTreeHashTable() {
//      hTable = new HashTable();
      creationOrder_tree = new AVLTree();
      key_tree = new AVLTree();
    }

    public void insert(long key, String value){
        if((key_tree.search(key_tree.root, key)==null)){

            creationOrder_tree.insert(creationOrder, key, value);
            key_tree.insert(key, creationOrder, value );
            creationOrder = creationOrder + 1;
        }
        else{
//            System.out.println(key);
            System.out.println("Cannot enter duplicate keys");


        }


    }

    public void delete(long key){
        long x = key_tree.search(key_tree.root, key).storedData;
        System.out.println(x);
        key_tree.delete(key);
        creationOrder_tree.delete(x);

    }

    public void get_value(long key){
        AVLTree.BinaryNode node = key_tree.search(key_tree.root,key);
        if(node != null){
            System.out.println(node.value);
        }
        else{

            System.out.println("No such key found");
        }

    }

    public void preorder(){
        creationOrder_tree.inorder(creationOrder_tree.root);
        System.out.println("\n");
        key_tree.inorder(key_tree.root);

    }

    public void allKeys(){
        key_tree.inorder(key_tree.root);
        System.out.println();
        creationOrder_tree.inorder(creationOrder_tree.root);
    }

    public void print_keys(){
        creationOrder_tree.inorder(creationOrder_tree.root);
    }

    public void predecessor(long key){
        long x = key_tree.search(key_tree.root, key).storedData;
        x = x+1;
    }

    public void range(long key1, long key2){
        long counter = key_tree.range_keys(key_tree.root,key1, key2);
        System.out.println(counter-2);
    }

    public void successor(long key){
        long x = key_tree.search(key_tree.root, key).storedData; //creation order

    }



    public static void main(String[] args) {
        AVLTreeHashTable tree = new AVLTreeHashTable();
        tree.insert(110, "a");
        tree.insert(111, "b");
        tree.insert(109, "c");
        tree.insert(112, "d");
        tree.insert(113, "e");
        tree.insert(114, "f");
        tree.insert(115, "e");

//        tree.preorder();
//        System.out.println();
//
        tree.allKeys();
//        tree.delete(114);
//        System.out.println();
//        tree.allKeys();
//
//
//
    }
}
