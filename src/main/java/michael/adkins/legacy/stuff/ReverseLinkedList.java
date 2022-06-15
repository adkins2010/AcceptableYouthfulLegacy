package michael.adkins.legacy.stuff;

public class ReverseLinkedList {
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
    public static Node reverseLinkedList(Node node) {
        Node curr = node;
        Node prev = null;

        while(curr != null) {
            Node tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }
    public static void main(String[] args) {
        Node n = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5)))));
        System.out.println(reverseLinkedList(n));
    }
}
