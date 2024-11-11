package linkedLists;

import java.util.HashMap;

public class loopDetection {
    static HashMap<Integer, Node<Integer>> map = new HashMap<>();
    public static void main(String[] args) {
    Node<Integer> head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next = new Node(4);
    System.err.println(findLoop(head));
    }

    static boolean findLoop(Node<Integer> head) {
        while (head!=null) {
            if (map.containsKey(head.data)) {
                return true;
            }
            map.put(head.data,head);
            head = head.next;
        }
        return false;
    }
 


}
