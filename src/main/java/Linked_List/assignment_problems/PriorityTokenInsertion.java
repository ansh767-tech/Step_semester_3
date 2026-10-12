package Linked_List.assignment_problems;

/**
 * Problem 1: Priority Token Insertion
 * 
 * Singly Linked List implementation supporting:
 * - addFirst(int token): O(1) time
 * - addLast(int token): O(1) time with tail pointer (or O(N) traversing from head)
 * - insertAt(int index, int token): O(N) time (0-based)
 * Space Complexity: O(1) auxiliary memory
 * 
 * Note: Does not use java.util.LinkedList.
 */
public class PriorityTokenInsertion {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class SinglyLinkedList {
        private Node head;
        private Node tail;
        private int size;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        /**
         * Adds token at the front (priority).
         * Time Complexity: O(1)
         */
        public void addFirst(int token) {
            Node newNode = new Node(token);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
            size++;
        }

        /**
         * Adds token at the end (normal).
         * Time Complexity: O(1) using tail reference (O(N) without tail)
         */
        public void addLast(int token) {
            Node newNode = new Node(token);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }

        /**
         * Inserts token at a specific 0-based index.
         * Time Complexity: O(N)
         */
        public void insertAt(int index, int token) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
            }

            if (index == 0) {
                addFirst(token);
                return;
            }
            if (index == size) {
                addLast(token);
                return;
            }

            // Walk to the node just before index
            Node curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }

            Node newNode = new Node(token);
            newNode.next = curr.next;
            curr.next = newNode;
            size++;
        }

        public int size() {
            return size;
        }

        public void printList() {
            Node curr = head;
            StringBuilder sb = new StringBuilder();
            while (curr != null) {
                sb.append(curr.data).append(" -> ");
                curr = curr.next;
            }
            sb.append("null");
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        // start = 101 -> 102 -> 103
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        System.out.print("Initial list: ");
        list.printList();

        // Operations: addFirst(100), addLast(104), insertAt(2, 150)
        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        System.out.print("Final list:   ");
        list.printList();
        // Expected Output: 100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null
    }
}
