import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {

    public class BinaryNode {
        String value;
        long baseData=-1;
        long storedData=-1;
        int height;
        BinaryNode left;
        BinaryNode right;

        BinaryNode() {
            this.height = 0;
        }

    }


    BinaryNode root;

    public AVLTree(){
        root = null;
    }

    //Preorder

    public void preorder(BinaryNode node)
    {
        if(node == null){
            return;
        }
        System.out.print(node.baseData+" ");
        preorder(node.left);
        preorder(node.right);
    }

    //Inorder

    public void inorder(BinaryNode node)
    {

        if(node == null){
            return;
        }
        inorder(node.left);
        System.out.print(node.baseData+" ");
        inorder(node.right);
    }
    public long range_keys(BinaryNode node, long key1,long key2){
        if(node == null){
            return 0;
        }
        if(node.baseData<=key2 && node.baseData>=key1){
            return 1 + range_keys(node.left, key1, key2)+range_keys(node.right, key1,key2);
        }
        else if(node.baseData<key1){
            return range_keys(node.right, key1, key2);
        }
        else{
            return range_keys(node.left, key1, key2);
        }
    }
    //Preorder

    public void postorder(BinaryNode node){
        if(node == null){
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value+" ");
    }

    public BinaryNode search(BinaryNode node, long baseData)
    {
        if(node == null){
            return null;
        }

        boolean isPresent = false;

        while(node!=null)
        {
            if(baseData<node.baseData){
                node = node.left;
            }
            else if(baseData>node.baseData){
                node = node.right;
            }else{
                return node;
            }
        }
        return null;
    }

    //get height
    public int getHeight(BinaryNode node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    // rotate right
    private BinaryNode rotateRight(BinaryNode disbalancednode){
        BinaryNode newRoot = disbalancednode.left;
        disbalancednode.left = disbalancednode.left.right;
        newRoot.right = disbalancednode;
        disbalancednode.height = 1 + Math.max(getHeight(disbalancednode.left), getHeight(disbalancednode.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    // rotate left
    private BinaryNode rotateLeft(BinaryNode disbalancednode){
        BinaryNode newRoot = disbalancednode.right;
        disbalancednode.right = disbalancednode.right.left;
        newRoot.left = disbalancednode;
        disbalancednode.height = 1 + Math.max(getHeight(disbalancednode.left), getHeight(disbalancednode.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    //getBalance
    public int getBalance(BinaryNode node){
        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    //insert method
    private BinaryNode insertNode(BinaryNode node,long baseData, long storedData, String value){
        if(node == null){
            BinaryNode newNode = new BinaryNode();
            newNode.baseData = baseData;
            newNode.storedData = storedData;
            newNode.value = value;
            newNode.height = 1;
            return newNode;
        }
        else if(baseData<node.baseData){
            node.left = insertNode(node.left, baseData, storedData, value);
        }
        else{
            node.right = insertNode(node.right, baseData, storedData, value);
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if(balance>1 && baseData < node.left.baseData){ //LL
            return rotateRight(node);
        }

        if(balance>1 && baseData>node.left.baseData){ //LR
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if(balance<-1 && baseData>node.right.baseData){ //RR
            return rotateLeft(node);
        }

        if(balance<-1 && baseData<node.right.baseData){ //RL
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void insert(long baseData, long storedData, String value){
        root = insertNode(root,baseData, storedData,value);
    }

    //Minimum node
    public static BinaryNode minimumNode(BinaryNode node){
        if(node.left==null){
            return node;
        }
        return minimumNode(node.left);
    }

    //Delete node
    public BinaryNode deleteNode(BinaryNode node, long baseData){

        if(node == null){
            System.out.println("Value not found in AVL Tree");
            return node;
        }
        if(baseData<node.baseData){
            node.left = deleteNode(node.left, baseData);
        }
        else if(baseData>node.baseData){
            node.right = deleteNode(node.right, baseData);
        }
        else{
            if(node.left!=null && node.right!=null){
                BinaryNode temp = node;
                BinaryNode minNodeForRight = minimumNode(temp.right);
                node.value = minNodeForRight.value;
                node.baseData = minNodeForRight.baseData;
                node.storedData = minNodeForRight.storedData;
                node.right = deleteNode(node.right, minNodeForRight.baseData);
            }
            else if(node.left!=null){
                node = node.left;
            }
            else if(node.right!=null){
                node = node.right;
            }else{
                node = null;
            }
        }
        int balance = getBalance(node);

        if(balance>1 && getBalance(node.left)>=0){ //LL
            return rotateRight(node);
        }

        if(balance>1 && getBalance(node.left)<0){ //LR
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if(balance<-1 && getBalance(node.right)<=0){ //RR
            return rotateLeft(node);
        }

        if(balance<-1 && getBalance(node.right)>0){ //RL
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;

    }

    public void delete(long baseData){
        root = deleteNode(root,baseData);
//        System.out.println(root.value+"z");
    }

//    void levelOrder()
//    {
//        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
//        queue.add(root);
//        while(!queue.isEmpty()){
//            BinaryNode presentNode = queue.remove();
//
//            if (presentNode.value == -1) {
//                System.out.println("");
//            } else {
//                System.out.print(presentNode.value+" ");
//            }
//
//
//            if (presentNode.right!=null || presentNode.left!=null) {
//                BinaryNode levelEnd = new BinaryNode();
//                levelEnd.value = -1;
//                queue.add(levelEnd);
//                if(presentNode.left!=null){
//                    queue.add(presentNode.left);
//                }
//                if(presentNode.right!=null){
//                    queue.add(presentNode.right);
//                }
//            }
//        }
//    }

//    public long delete__(BinaryNode node, long baseData){
//        long x = search(node, baseData);
//        return x;
//    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(110, 210, "a");
        tree.insert(111, 210, "b");
        tree.insert(112, 210, "c");
        tree.insert(113, 210, "d");

//        tree.inorder(tree.root);
//        System.out.println();
//        System.out.println(tree.search(tree.root, 110));
//
//        tree.delete(110);
//
//        tree.inorder(tree.root);
//        System.out.println(tree.delete__(tree.root, 110));

    }

}
