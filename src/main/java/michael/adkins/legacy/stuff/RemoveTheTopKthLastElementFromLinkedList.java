package michael.adkins.legacy.stuff;

public class RemoveTheTopKthLastElementFromLinkedList {
    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public String toString() {
            StringBuilder buf = new StringBuilder();
            buf.append(value);
            if(next != null) {
                buf.append(next.toString());
            }
            return buf.toString();
        }
    }

    public static Node removeKthFromLinkedList(Node head, int k) {
        Node slow = head;
        Node fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        if(fast == null) {
            return head.next;
        }
        Node prev = null;
        while(fast != null) {
            prev = slow;
            fast = fast.next;
            slow = slow.next;
        }
        prev.next = slow.next;
        return head;
    }
    public static void main(String[] args) {
        Node node = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5)))));
        System.out.println(node);
        node = removeKthFromLinkedList(node, 1);
        System.out.println(node);
    }
}
