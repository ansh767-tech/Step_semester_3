package Linked_List.assignment_problems;

/**
 * Problem 2: Cancelled Orders Cleanup
 * 
 * Removes every node with a given code from a singly linked list.
 * 
 * Time Complexity: O(N) - single pass through the linked list of length N.
 * Additional Space Complexity: O(1) - modifies links in place without extra allocations.
 * 
 * Note: Does not use java.util.LinkedList.
 */
public class CancelledOrdersCleanup {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Removes all nodes with value equal to code.
     * 
     * @param head head of the singly linked list
     * @param code value to remove
     * @return updated head of the singly linked list
     */
    public static Node removeAll(Node head, int code) {
        // Step 1: Remove matching nodes from the front by advancing head
        while (head != null && head.data == code) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        // Step 2: Walk with a previous reference and skip matching nodes
        Node prev = head;
        Node curr = head.next;

        while (curr != null) {
            if (curr.data == code) {
                prev.next = curr.next; // skip current node
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        return head;
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
        // Sample 1: orders = 5 -> 3 -> 5 -> 8 -> 5, code = 5
        Node sample1 = buildList(new int[]{5, 3, 5, 8, 5});
        System.out.print("Sample 1 Before: ");
        printList(sample1);
        Node res1 = removeAll(sample1, 5);
        System.out.print("Sample 1 After:  ");
        printList(res1); // Expected: 3 -> 8 -> null

        System.out.println();

        // Sample 2: orders = 5 -> 5 -> 5, code = 5
        Node sample2 = buildList(new int[]{5, 5, 5});
        System.out.print("Sample 2 Before: ");
        printList(sample2);
        Node res2 = removeAll(sample2, 5);
        System.out.print("Sample 2 After:  ");
        printList(res2); // Expected: null
    }
}
