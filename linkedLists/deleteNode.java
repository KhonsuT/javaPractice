package linkedLists;
public class deleteNode {
    public static void main(String[] args) {
        char[] input_list = {'a','b','c','d','e','f'};
        Node<Character> head = null;
        Node<Character> current = new Node<>(input_list[0]);
        head = current;
        for (int i = 1; i<input_list.length; i++) {
            Node<Character> next_node = new Node<>(input_list[i]);
            current.next = next_node;
            current = next_node;
        }
        printList(head);
        head = deletNodeAgent(head,'b');
        printList(head);

        head = deletNodeAgent(head,'c');
        printList(head);

    }

    public static Node<Character> deletNodeAgent(Node<Character> headNode, char target) { 
        if (headNode.data.equals(target) && headNode != null) {
            headNode = headNode.next;
        }
        Node<Character> curNode = headNode;
        while (curNode.next!=null && curNode != null) {
            if (curNode.next.data.equals(target)) {
                curNode.next = curNode.next.next;
            }
            curNode = curNode.next;
        }
        return headNode;
    }

    public static void printList(Node<Character> input_node) {
        Node<Character> current = input_node;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
