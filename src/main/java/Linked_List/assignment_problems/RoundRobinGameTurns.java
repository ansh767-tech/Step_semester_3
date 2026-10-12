package Linked_List.assignment_problems;

/**
 * Problem 5: Round-Robin Game Turns
 * 
 * Circular Singly Linked List with a tail reference representing players in a circle.
 * 
 * Time Complexity:
 * - addLast(String name): O(1) using tail reference (head is tail.next)
 * - printTurns(int turns): O(turns) time
 * 
 * Additional Space Complexity: O(1) auxiliary space
 * 
 * Note: Does not use java.util.LinkedList.
 */
public class RoundRobinGameTurns {

    public static class Node {
        public String name;
        public Node next;

        public Node(String name) {
            this.name = name;
            this.next = null;
        }
    }

    public static class CircularLinkedList {
        private Node tail;
        private int count;

        public CircularLinkedList() {
            this.tail = null;
            this.count = 0;
        }

        /**
         * Adds a player to the end of the circular list.
         * In a circular list with tail reference, tail.next is the head.
         * Time Complexity: O(1)
         */
        public void addLast(String name) {
            Node newNode = new Node(name);
            if (tail == null) {
                tail = newNode;
                tail.next = tail; // points to itself
            } else {
                newNode.next = tail.next; // new node points to head
                tail.next = newNode;      // old tail points to new node
                tail = newNode;           // tail becomes new node
            }
            count++;
        }

        /**
         * Prints the player name for each turn up to the specified number of turns.
         * Time Complexity: O(turns)
         */
        public void printTurns(int turns) {
            if (tail == null || turns <= 0) {
                return;
            }

            Node curr = tail.next; // Head is tail.next
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < turns; i++) {
                sb.append(curr.name);
                if (i < turns - 1) {
                    sb.append(" ");
                }
                curr = curr.next;
            }
            System.out.println(sb.toString());
        }

        public int getPlayerCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        // players = Asha, Ravi, Neha; turns = 7
        CircularLinkedList game = new CircularLinkedList();
        game.addLast("Asha");
        game.addLast("Ravi");
        game.addLast("Neha");

        // Expected Output: Asha Ravi Neha Asha Ravi Neha Asha
        game.printTurns(7);
    }
}
