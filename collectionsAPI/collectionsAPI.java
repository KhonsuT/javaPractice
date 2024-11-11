package collectionsAPI;
import java.util.*;
import java.util.stream.Collectors;
public class collectionsAPI {
    public static void main(String[] args) {
        // priority Queues
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // inverse a priority queue; default pq is min-heap smallest element first
        pq.offer(5);
        pq.offer(100);
        System.out.println(pq.poll());
        System.out.println(pq.stream());

        // Stacks
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(3);
        stack.push(101);
        stack.stream().forEach(System.out::println);
        stack.pop();
        stack.stream().forEach(System.out::println);

        //Filter

        List<Integer> filter_list = Arrays.asList(3,4,2,5,2,2,2,5,6,9,2);
        System.out.println(filter_list);
        List<Integer> filteredList = filter_list.stream().filter(num -> num>2).collect(Collectors.toList());
        List<String> stringlist = filter_list.stream().map(Object::toString).collect(Collectors.toList());
        System.out.println(filteredList);
        System.out.println(stringlist);

        //Map

        List<String> guests = Arrays.asList("Derek","Daphne","Khonsu","Robert");
        List<Integer> guests_length = guests.stream().map(String::length).collect(Collectors.toList());
        List<String> guests_updated = guests.stream().map(guest->guest+="C").collect(Collectors.toList());
        System.out.println(guests_length);
        System.out.println(guests_updated);


        //method ref
        // Class::staticMethod
        List<Integer> method_ref = Arrays.asList(3,4,2,5,2,2,2,5,6,9,2);
        List<String> method_ref_string = method_ref.stream().map(i -> Integer.toString(i)).collect(Collectors.toList());
        System.out.println(method_ref.get(0).getClass().getName());
        System.out.println(method_ref_string.get(0).getClass().getName());

        //Set
        Set<Integer> set = new HashSet<>();

        set.add(1);
        set.add(1);
        set.add(10);
        System.out.println(set);

        //Map
        Map<String,Integer> map = new HashMap<>();
        map.put("Derek",25);
        map.put("Daphne", 22);
        System.out.println(map.containsKey("Daphne"));
        System.out.println(map);

        //min,max,frq
        List<String> list = Arrays.asList("Java","Python","C++");
        String min = Collections.min(list);
        String max = Collections.max(list);
        int frequency = Collections.frequency(list, "Java");
        System.out.println("Min:"+min+"Max:"+max+"Frq:"+frequency);
    }   
}