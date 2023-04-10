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

    ExpandableArray () {
        arr = new Node[10];
    }

    ExpandableArray (int initialSize) {
        arr = new Node[initialSize];
    }

    public void insert(long key, String value) {
        if (top == arr.length)
            expand();
        
        if (getIndex(key) == -1) {
            arr[top] = new Node(key, value);
            top++;
        } else {
            System.out.println("Key already exists");
        }
    }

    public void search(long key) {
        for (int i=0; i<top; i++) {
            if (arr[i].key == key) {
                System.out.println(arr[i].value);
                return;
            }
        }

        System.out.println("Not found");
    }

    private int getIndex(long key) {
        for (int i=0; i<top; i++) {
            if (arr[i].key == key) {
                return i;
            }
        }

        return -1;
    }

    public long predecessor(long key) {
        int index=getIndex(key);

        if (index!=-1 && arr[index].key == key && index-1>=0) {
            return arr[index-1].key;
        } else return -1;
    }

    public long successor(long key) {
        int index=getIndex(key);

        if (index!=-1 && arr[index].key == key && index+1<top) {
            return arr[index+1].key;
        } else return -1;
    }

    public int size() {
        return top;
    }

    private void expand() {
        Node[] temp = new Node[Math.min(1000, 2*arr.length)];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        arr = temp;
    }

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

    long[] sortKeys(long keys[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sortKeys(keys, l, m);
            sortKeys(keys, m + 1, r);
            merge(keys, l, m, r);
        }

        return keys;
    }

    public void displaySortedKeys() {
        long[] keys = new long[top];
        for (int i = 0; i < top; i++) {
            keys[i] = arr[i].key;
        }

        long[] sortedKeys = sortKeys(keys, 0, keys.length-1);
        for (int i = 0; i < sortedKeys.length; i++) {
            System.out.print(sortedKeys[i]+" ");
            search(sortedKeys[i]);
        }
    }

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

    public int range(long key1, long key2) {
        int counter = 0;
        
        for (int i = 0; i < top; i++) {
            if (arr[i].key > key1 && arr[i].key < key2)
                counter++;
        }

        return counter;
    }
}
