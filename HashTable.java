//import java.util.Objects;
//
//public class HashTable {
//	class LinkedList
//	{
//		class LinkedListNode {
//			private long key;
//      private long creationOrder;
//			private LinkedListNode next;
//
//			LinkedListNode(long key, long creationOrder, LinkedListNode next) {
//				this.key = key;
//				this.creationOrder = creationOrder;
//				this.next = next;
//			}
//		}
//
//		private LinkedListNode head, tail;
//		private int size;
//
//		LinkedList() {
//			this.head = null;
//			this.tail = null;
//		}
//
//		void AddToStart(long key, long creationOrder) {
//			head = new LinkedListNode(key, creationOrder, head);
//			size++;
//
//			if (tail == null) tail = head;
//		}
//
//
//
//		void AddAtEnd(long key, long creationOrder) {
//			LinkedListNode temp = tail;
//			tail = new LinkedListNode(key, creationOrder, null);
//			temp.next = tail;
//			size++;
//
//			if (head == null) head = tail;
//		}
//
//		void displayListContents() {
//			if (head == null)
//				System.out.println("List is empty" );
//
//			else {
//				LinkedListNode temp = head;
//        System.out.print("Keys: ");
//				while (true) {
//					System.out.print(temp.key);
//
//					if (temp.next != null) {
//						System.out.print(", ");
//						temp = temp.next;
//					} else {
//						System.out.println();
//						break;
//					}
//				}
//			}
//		}
//
//		int size() {
//			return size;
//		}
//
//		boolean contains(long targetKey) {
//			LinkedListNode temp = head;
//			while (temp != null) {
//				if (Objects.equals(temp.key, targetKey))
//					return true;
//
//				temp = temp.next;
//			}
//			return false;
//		}
//	}
//
//	private LinkedList[] HashArr = new LinkedList[1000];
//
//	HashTable() {
//		for (int i = 0; i < HashArr.length; i++) {
//			HashArr[i] = new LinkedList();
//		}
//	}
//
//	private int computeHashValue(long key) {
//    String keyString = key+"";
//		long hashCode = 0;
//		int z = 3;
//
//		for (int i = 0; i < Math.min(12, keyString.length()); i++)
//			hashCode += ((int) keyString.charAt(i)) * Math.pow(z, i);
//
//		return (int) hashCode % HashArr.length;
//	}
//
//	public void insertKey(long key, long creationOrder) {
//		int hashValue = computeHashValue(key);
//
//		if (!HashArr[hashValue].contains(key)) {
//			HashArr[hashValue].AddToStart(key, creationOrder);
//		} else {
//			System.out.println("Key already exists; no addition is made");
//		}
//	}
//
//	public long delete(long key) {
//		int hashValue = computeHashValue(key);
//
//		LinkedList.LinkedListNode temp = HashArr[hashValue].head;
//		while (temp.next!=null && temp.next.key != key)
//			temp = temp.next;
//
//		if (temp.next.key == key) {
//			long deletedCreationOrder = temp.next.creationOrder;
//			temp.next = temp.next.next;
//			return deletedCreationOrder;
//		} else {
//			System.out.println("Key not found");
//			return -1;
//		}
//	}
//
//	public long search(long key) {
//		int hashValue = computeHashValue(key);
//
//		LinkedList.LinkedListNode temp = HashArr[hashValue].head;
//		while (temp.next!=null && temp.key != key)
//			temp = temp.next;
//
//		if (temp.key == key) {
//			return temp.creationOrder;
//		} else {
//			System.out.println("Key not found");
//			return -1;
//		}
//	}
//
//	public void showTableContents() {
//		for (int i = 0; i < HashArr.length; i++) {
//      if (HashArr[i].head != null) {
//        System.out.println("index "+i+" has");
//        HashArr[i].displayListContents();
//        if (HashArr[i].size() <= 1) {
//          System.out.println("0 collisions\n");
//        } else {
//          System.out.println(HashArr[i].size()-1+" collisions\n");
//        }
//      }
//
//		}
//	}
//}
