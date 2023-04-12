public class AVLTree {

    public class BinaryNode {
    String value;
    long baseData;
    long storedData;
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

    public void preorder(BinaryNode node)  //tree1.add(value, data1, data2)
    {
        if(node == null){
            return;
        }
        System.out.print(node.baseData +" ");
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

    //Preorder

    public void postorder(BinaryNode node){
        if(node == null){
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value+" ");
    }

    public BinaryNode find(BinaryNode node, long baseData)
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
    public BinaryNode search(long baseData){
        return find(root, baseData);
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
    private BinaryNode insertNode(BinaryNode node,long creationOrder, long key, String value){
        if(node == null){
            BinaryNode newNode = new BinaryNode();
            newNode.storedData = key;
            newNode.baseData = creationOrder;
            newNode.value = value;
            newNode.height = 1;
            return newNode;
        }
        else if(creationOrder<node.baseData){
            node.left = insertNode(node.left,creationOrder, key, value);
        }
        else{
            node.right = insertNode(node.right,creationOrder, key, value);
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if(balance>1 && creationOrder < node.left.baseData){ //LL
            return rotateRight(node);
        }

        if(balance>1 && creationOrder>node.left.baseData){ //LR
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if(balance<-1 && creationOrder>node.right.baseData){ //RR
            return rotateLeft(node);
        }

        if(balance<-1 && creationOrder<node.right.baseData){ //RL
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
}

    public void insert(long creationOrder, long key, String value){
        root = insertNode(root,creationOrder, key, value );
    }

    //Minimum node
    public static BinaryNode minimumNode(BinaryNode node){
        if(node.left!=null){
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

    public long delete(long baseData){
        return deleteNode(root,baseData).baseData;
    }

}
