public class ExpandableArray {
    class Node {
        long key;
        String value;

        Node (long key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] arr;
    int top = 0;

    /**
     * Initializes an array of size 10.
     */
    ExpandableArray () {
        arr = new Node[10];
    }

    /**
     * @param initialSize
     * Intializes an array of given size.
     */
    ExpandableArray (int initialSize) {
        arr = new Node[initialSize];
    }

    /**
     * @param key
     * @param value
     * Insert a key into the array.
     */
    public void insert(long key, String value) {
        if (top == arr.length)
            expand();

        if (getIndex(key) == -1) {
            arr[top] = new Node(key, value);
            top++;
        } else {
            System.out.println(key+" Key already exists");
        }
    }

    /**
     * @param key
     * Prints the value associated with a given key.
     */
    public void getValue(long key) {
        for (int i=0; i<top; i++) {
            if (arr[i].key == key) {
                System.out.print(arr[i].value);
                return;
            }
        }

        System.out.println("No such key exists, value not found");
    }

    /**
     * @param key
     * Gives the index (creation order) of the particular key.
     * @return
     */
    private int getIndex(long key) {
        for (int i=0; i<top; i++) {
            if (arr[i].key == key) {
                return i;
            }
        }

        return -1;
    }

    /**
     * @param key
     * Gives the previous key present in chronological order.
     */
    public void predecessor(long key) {
        int index=getIndex(key);

        if (index!=-1 && arr[index].key == key && index-1>=0) {
            System.out.println(arr[index-1].key);
        }
        else System.out.println("No such key exists");
    }

    /**
     * @param key
     * Gives the next key present in chronological order.
     */
    public void successor(long key) {
        int index=getIndex(key);

        if (index!=-1 && arr[index].key == key && index+1<top) {
            System.out.println(arr[index+1].key);
        }
        else System.out.println("No such key exists");
    }

    public int size() {
        return top;
    }

    /**
     * Creates an array of double the existing size, and copies all elements into it.
     */
    private void expand() {
        Node[] temp = new Node[Math.min(1000, 2*arr.length)];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        arr = temp;
    }

    /**
     * @param keys
     * @param l
     * @param m
     * @param r
     * Performs the merge sort.
     * @return
     */
    private long[] merge(long[] keys, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        long L[] = new long[n1];
        long R[] = new long[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = keys[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = keys[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                keys[k] = L[i];
                i++;
            }
            else {
                keys[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            keys[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            keys[k] = R[j];
            j++;
            k++;
        }
        return keys;
    }

    /**
     * @param keys
     * @param l
     * @param r
     * returns all keys in sorted order.
     * @return
     */
    private long[] sortKeys(long keys[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sortKeys(keys, l, m);
            sortKeys(keys, m + 1, r);
            merge(keys, l, m, r);
        }

        return keys;
    }

    /**
     * Prints all keys in sorted order.
     */
    public void getSortedKeys() {
        long[] keys = new long[top];
        for (int i = 0; i < top; i++) {
            keys[i] = arr[i].key;
        }

        keys = sortKeys(keys, 0, keys.length-1);
        for (int i = 0; i < keys.length; i++) {
            System.out.print(keys[i]+" ");
        }
        System.out.println("");
    }

    /**
     * @param key
     * Deletes the key, and the value associated with it.
     */
    public void delete(long key) {
        int index = getIndex(key);

        if (index != -1) {
            for (int i = index; i < top-1; i++) {
                arr[i] = arr[i+1];
            }
            top--;
        } else {
            System.out.println("No such key found");
        }
    }

    /**
     * @param key1
     * @param key2
     * Gives the number of keys present between key1 and key2.
     */
    public void range(long key1, long key2) {
        int counter = 0;

        for (int i = 0; i < top; i++) {
            if (arr[i].key > key1 && arr[i].key < key2)
                counter++;
        }

        System.out.println(counter);
    }
}
