public class AVLTreeHashTable {
    static int creationOrder = 0;  

    HashTable hTable;
    AVLTree tree;

    AVLTreeHashTable() {
      hTable = new HashTable();
      tree = new AVLTree();
    }
}
