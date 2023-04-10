public class ExpandableArray {
    class Node {
        long key;
        String value;

        Node(long key, String value) {
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
        if (top+1 == arr.length)
            expand();
        arr[top] = new Node(key, value);
        top++;
    }

    public String search(long key) {
        for (int i=0; i<top; i++) {
            if (arr[i].key == key) {
                return arr[i].value;
            }
        }

        return "-1";
    }

    public int size() {
        return top;
    }

    private void expand() {
        Node[] temp = new Node[Math.min(1000, 2*arr.length)];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        arr = temp;
    }
}
