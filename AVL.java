public class AVL {
    BinaryNode root;

    public AVL(){
        root = null;
    }

    //Preorder

    public void preorder(BinaryNode node)
    {
        if(node == null){
            return;
        }
        System.out.print(node.key+" ");
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
        System.out.print(node.value+" ");
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

    public boolean find(BinaryNode node, long key)
    {
        if(node == null){
            return false;
        }

        boolean isPresent = false;

        while(node!=null)
        {
            if(key<node.key){
                node = node.left;
            }
            else if(key>node.key){
                node = node.right;
            }else{
                isPresent = true;
                System.out.println(node.value);
                break;
            }
        }
        return isPresent;
    }
    public void search(long key){
        find(root, key);
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
    private BinaryNode insertNode(BinaryNode node, long key, String value){
        if(node == null){
            BinaryNode newNode = new BinaryNode();
            newNode.key = key;
            newNode.value = value;
            newNode.height = 1;
            return newNode;
        }
        else if(key<node.key){
            node.left = insertNode(node.left, key, value);
        }
        else{
            node.right = insertNode(node.right, key, value);
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if(balance>1 && key < node.left.key){ //LL
            return rotateRight(node);
        }

        if(balance>1 && key>node.left.key){ //LR
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if(balance<-1 && key>node.right.key){ //RR
            return rotateLeft(node);
        }

        if(balance<-1 && key<node.right.key){ //RL
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void insert(long key, String value){
        root = insertNode(root,key, value );
    }

    //Minimum node
    public static BinaryNode minimumNode(BinaryNode node){
        if(node.left!=null){
            return node;
        }
        return minimumNode(node.left);
    }

    //Delete node
    public BinaryNode deleteNode(BinaryNode node, long key){
        if(node == null){
            System.out.println("Value not found in AVL Tree");
            return node;
        }
        if(key<node.key){
            node.left = deleteNode(node.left, key);
        }
        else if(key>node.key){
            node.right = deleteNode(node.right, key);
        }
        else{
            if(node.left!=null && node.right!=null){
                BinaryNode temp = node;
                BinaryNode minNodeForRight = minimumNode(temp.right);
                node.value = minNodeForRight.value;
                node.right = deleteNode(node.right, minNodeForRight.key);
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

    public void delete(long key){
        root = deleteNode(root,key);
    }

}
