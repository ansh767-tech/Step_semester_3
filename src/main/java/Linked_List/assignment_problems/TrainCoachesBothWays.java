package Linked_List.assignment_problems;

/**
 * Problem 4: Train Coaches Both Ways
 * 
 * Doubly Linked List with head and tail references representing train coaches.
 * Supports:
 * - addLast(String name): O(1) time
 * - remove(String name): O(N) time to find coach, O(1) time to unlink once found
 * - printForward(): O(N) time
 * - printBackward(): O(N) time
 * 
 * Additional Space Complexity: O(1) auxiliary memory
 */
public class TrainCoachesBothWays {

    public static class Node {
        public String name;
        public Node prev;
        public Node next;

        public Node(String name) {
            this.name = name;
            this.prev = null;
            this.next = null;
        }
    }

    public static class TrainDoublyLinkedList {
        private Node head;
        private Node tail;
        private int size;

        public TrainDoublyLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        /**
         * Appends coach to the tail of the train.
         * Time Complexity: O(1)
         */
        public void addLast(String name) {
            Node newNode = new Node(name);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            size++;
        }

        /**
         * Detaches a coach by name.
         * Time Complexity: O(N) to locate the coach, O(1) to unlink once found.
         * 
         * @param name name of coach to remove
         * @return true if coach was found and removed, false otherwise
         */
        public boolean remove(String name) {
            Node curr = head;
            while (curr != null) {
                if (curr.name.equals(name)) {
                    // Fix previous neighbor or head
                    if (curr == head) {
                        head = curr.next;
                        if (head != null) {
                            head.prev = null;
                        } else {
                            tail = null; // List became empty
                        }
                    } else {
                        curr.prev.next = curr.next;
                    }

                    // Fix next neighbor or tail
                    if (curr == tail) {
                        tail = curr.prev;
                        if (tail != null) {
                            tail.next = null;
                        } else {
                            head = null; // List became empty
                        }
                    } else {
                        curr.next.prev = curr.prev;
                    }

                    size--;
                    return true;
                }
                curr = curr.next;
            }
            return false;
        }

        /**
         * Prints the train coaches from engine end (head) to guard end (tail).
         */
        public void printForward() {
            StringBuilder sb = new StringBuilder("Forward: ");
            Node curr = head;
            while (curr != null) {
                sb.append(curr.name);
                if (curr.next != null) {
                    sb.append(" <-> ");
                }
                curr = curr.next;
            }
            System.out.println(sb.toString());
        }

        /**
         * Prints the train coaches from guard end (tail) to engine end (head).
         */
        public void printBackward() {
            StringBuilder sb = new StringBuilder("Backward: ");
            Node curr = tail;
            while (curr != null) {
                sb.append(curr.name);
                if (curr.prev != null) {
                    sb.append(" <-> ");
                }
                curr = curr.prev;
            }
            System.out.println(sb.toString());
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        // coaches = Engine, A1, B1, B2, Guard; remove B1
        TrainDoublyLinkedList train = new TrainDoublyLinkedList();
        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        train.remove("B1");

        // Expected Output:
        // Forward: Engine <-> A1 <-> B2 <-> Guard
        // Backward: Guard <-> B2 <-> A1 <-> Engine
        train.printForward();
        train.printBackward();
    }
}
