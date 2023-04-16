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

    /**
     * Initializes root to null.
     */
    public AVLTree(){
        root = null;
    }


    /**
     * @param node
     * Prints the inorder traversal.
     */
    public void inorder(BinaryNode node)
    {

        if(node == null){
            return;
        }
        inorder(node.left);
        System.out.print(node.baseData+" ");
        inorder(node.right);
    }

    /**
     * @param node
     * @param key1
     * @param key2
     * Gives the number of keys present between key1 and key2.
     * @return
     */
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

    /**
     * @param node
     * @param baseData
     * Searches the tree for the given baseData.
     * @return
     */
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

    /**
     * @param node
     * Gives the height of a node.
     * @return
     */
    //get height
    public int getHeight(BinaryNode node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    /**
     * @param disbalancednode
     * Performs right rotation, and increases the height of disbalancednode by 1.
     * @return
     */
    // rotate right
    private BinaryNode rotateRight(BinaryNode disbalancednode){
        BinaryNode newRoot = disbalancednode.left;
        disbalancednode.left = disbalancednode.left.right;
        newRoot.right = disbalancednode;
        disbalancednode.height = 1 + Math.max(getHeight(disbalancednode.left), getHeight(disbalancednode.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    /**
     * @param disbalancednode
     * Performs left rotation, and increases the height of disbalancednode by 1.
     * @return
     */
    // rotate left
    private BinaryNode rotateLeft(BinaryNode disbalancednode){
        BinaryNode newRoot = disbalancednode.right;
        disbalancednode.right = disbalancednode.right.left;
        newRoot.left = disbalancednode;
        disbalancednode.height = 1 + Math.max(getHeight(disbalancednode.left), getHeight(disbalancednode.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    /**
     * @param node
     * Gives the difference of height between left subtree, and right subtree.
     * @return
     */
    //getBalance
    public int getBalance(BinaryNode node){
        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * @param node
     * @param baseData
     * @param storedData
     * @param value
     * Inserts a key in a tree.
     * @return
     */
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

    /**
     * @param node
     * Finds the minimum node in a tree.
     * @return
     */
    //Minimum node
    public static BinaryNode minimumNode(BinaryNode node){
        if(node.left==null){
            return node;
        }
        return minimumNode(node.left);
    }

    /**
     * @param node
     * @param baseData
     * Delete a key from a tree.
     * @return
     */
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
    }
}
