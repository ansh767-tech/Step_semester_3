package Linked_List.assignment_problems;

/**
 * Problem 3: Reverse the Queue
 * 
 * Reverses a singly linked list in-place without creating any new nodes.
 * 
 * Time Complexity: O(N) - visits each node once.
 * Additional Space Complexity: O(1) - only 3 pointer variables (prev, curr, next).
 * 
 * Note: Does not use java.util.LinkedList.
 */
public class ReverseTheQueue {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Reverses the singly linked list in place using three pointers.
     * 
     * @param head head of the original list
     * @return head of the reversed list
     */
    public static Node reverse(Node head) {
        Node prev = null;
        Node curr = head;
        Node next = null;

        while (curr != null) {
            next = curr.next; // store next node
            curr.next = prev; // reverse pointer
            prev = curr;      // advance prev
            curr = next;      // advance curr
        }

        return prev; // new head
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr != null) {
            sb.append(curr.data).append(" -> ");
            curr = curr.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    public static Node buildList(int[] values) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new Node(values[i]);
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        // Sample 1: queue = 1 -> 2 -> 3 -> 4
        Node sample1 = buildList(new int[]{1, 2, 3, 4});
        System.out.print("Sample 1 Before: ");
        printList(sample1);
        Node reversed1 = reverse(sample1);
        System.out.print("Sample 1 After:  ");
        printList(reversed1); // Expected: 4 -> 3 -> 2 -> 1 -> null

        System.out.println();

        // Sample 2: queue = 9
        Node sample2 = buildList(new int[]{9});
        System.out.print("Sample 2 Before: ");
        printList(sample2);
        Node reversed2 = reverse(sample2);
        System.out.print("Sample 2 After:  ");
        printList(reversed2); // Expected: 9 -> null
    }
}
