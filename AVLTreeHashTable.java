public class AVLTreeHashTable {
    static long creationOrder = 0;

    HashTable hTable;
    AVLTree creationOrder_tree;
    AVLTree key_tree;

    AVLTreeHashTable() {
//      hTable = new HashTable();
      creationOrder_tree = new AVLTree();
      key_tree = new AVLTree();
    }

    public void insert(long key, String value){
        creationOrder_tree.insert(creationOrder, key, value);
        key_tree.insert(key, creationOrder, value );
        creationOrder++;

    }

    public void delete(long key){
        long deleteCreationOrder = key_tree.delete(key);
        creationOrder_tree.delete(deleteCreationOrder);

    }

    public void get_value(long key){
        AVLTree.BinaryNode node = key_tree.search(key);
        if(node != null){
            System.out.println(node.value);
        }
        else{
            System.out.println("No such key found");
        }

    }

    public void preorder(){
        creationOrder_tree.preorder(creationOrder_tree.root);
        System.out.println("\n\n\n");
        key_tree.preorder(key_tree.root);
    }

    public void allKeys(){
        key_tree.inorder(key_tree.root);
    }

    public static void main(String[] args) {
        AVLTreeHashTable tree = new AVLTreeHashTable();
        tree.insert(110, "a");
        tree.insert(111, "b");
        tree.insert(109, "c");
        tree.insert(112, "d");
        tree.insert(113, "e");

        tree.allKeys();


    }
}
