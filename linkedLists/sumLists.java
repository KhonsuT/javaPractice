package linkedLists;

public class sumLists {
    public static void main(String[] args) {
        int[] input_list1 = {6,1,2,9};
        Node<Integer> input1 = null;
        Node<Integer> current1  = new Node<>(input_list1[0]);
        input1 = current1;
        for (int i = 1; i<input_list1.length; i++) {
            Node<Integer> next_node = new Node<>(input_list1[i]);
            current1.next = next_node;
            current1 = next_node;
        }

        int[] input_list2 = {3,2,9};
        Node<Integer> input2 = null;
        Node<Integer> current2 = new Node<>(input_list2[0]);
        input2 = current2;
        for (int i = 1; i<input_list2.length; i++) {
            Node<Integer> next_node = new Node<>(input_list2[i]);
            current2.next = next_node;
            current2 = next_node;
        }
        printList(input1);
        printList(input2);

        printList(reverseSumListCal(input1, input2,0));
        printList(recSum(input1, input2, 0));
    }

    static Node<Integer> reverseSumListCal(Node<Integer> input1, Node<Integer> input2, int carry) {
        if (carry==0 && input1==null && input2==null) {
            return null;
        }

        int val1 = (input1!=null)? input1.data:0;
        int val2 = (input2!=null)? input2.data:0;
        int sum = val1+val2+carry;

        Node<Integer> result = new Node<Integer>(sum%10);
        result.next = reverseSumListCal((input1!=null)? input1.next:null, (input2!=null)? input2.next:null, sum/10);

        return result;
    }

    static Node<Integer> sumListsCal(Node<Integer> input1, Node<Integer> input2) {
        Node<Integer> result = null;
        Node<Integer> res = new Node<Integer>(null);
        result = res;
        boolean carryOne = false;
        int sum;
        while (input1!=null || input2!=null || carryOne) {
            if (input1==null && input2!= null) {
                res.data = (carryOne)? input2.data + 1 : input2.data;
                Node<Integer> next_node = new Node<Integer>(null);
                res.next = next_node;
                res = res.next;
                input2 = input2.next;
            }
            else if (input2==null && input1!= null) {
                res.data = (carryOne)? input1.data + 1 : input1.data;
                Node<Integer> next_node = new Node<Integer>(null);
                res.next = next_node;
                res = res.next;
                input1 = input1.next;
            }
            else {
                if (carryOne) {
                    if (input1==null && input2 == null) {
                        res.data = 1;
                        Node<Integer> next_node = new Node<Integer>(null);
                        res.next = next_node;
                        res = res.next;
                        carryOne = false;
                        continue;
                    }
                    else {sum = input1.data+input2.data+1;}
                    carryOne = false;
                }
                else {
                    sum = input1.data+input2.data;
                }
                if (sum>=10) {
                    res.data = sum%10;
                    carryOne = true;
                }
                else {
                    res.data = sum;
                }
                Node<Integer> next_node = new Node<Integer>(null);
                res.next = next_node;
                res = res.next;
                input1 = input1.next;
                input2 = input2.next;
            }
        } 
        return result;
    }

    static Node<Integer> recSum(Node<Integer> input1, Node<Integer> input2, int carry) {
        if(input1==null && input2==null & carry==0) {
            return null;
        }

        int val1 = (input1!=null)? input1.data:0;
        int val2 = (input2!=null)? input2.data:0;
        int sum = val1+val2+carry;

        Node<Integer> result = new Node<Integer>(sum%10);
        result.next = recSum((input1!=null)?input1.next:null, (input2!=null)?input2.next:null, sum/10);
        return result;
    }

    public static void printList(Node<Integer> input_node) {
        Node<Integer> current = input_node;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
