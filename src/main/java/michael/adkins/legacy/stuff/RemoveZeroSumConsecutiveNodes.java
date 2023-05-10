package michael.adkins.legacy.stuff;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public class RemoveZeroSumConsecutiveNodes {
    static class Node {
        private final int value;
        private Node next;

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
                buf.append(next);
            }
            return buf.toString();
        }
    }


    public static Node removeZeroSumSublists(Node node) {
        LinkedHashMap<Integer, Node> sumToNodeDictionary = new LinkedHashMap<>();
        TreeMap<Integer, Node> treemap = new TreeMap<>();
        Integer sum = 0;
        Node dummy = new Node(sum);
        Node curr = dummy;
        while(curr != null) {
            sum += curr.value;
            if(sumToNodeDictionary.containsKey(sum)) {
                Node prev = sumToNodeDictionary.get(sum);
                prev.next = curr.next;
                while(sumToNodeDictionary.keySet().toArray()[sumToNodeDictionary.keySet().size()-1] != sum) {
                    sumToNodeDictionary.remove(sumToNodeDictionary.keySet().size()-1);
                }
            } else {
                sumToNodeDictionary.put(sum, curr);
            }
            curr = curr.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        
    }
}
