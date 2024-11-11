package stacksAndQueues;
import linkedLists.Node;
public class animalShelter {
    Node<String> shelter = null;

    public static void main(String[] args) {
        animalShelter animalShelter =  new animalShelter();
        animalShelter.enqueue(new Node<String>("Dog"));
        animalShelter.enqueue(new Node<String>("Dog"));
        animalShelter.enqueue(new Node<String>("Cat"));
        animalShelter.enqueue(new Node<String>("Dog"));
        animalShelter.enqueue(new Node<String>("Cat"));
        animalShelter.enqueue(new Node<String>("Dog"));
        printList(animalShelter.getShelter());
        animalShelter.dequeueAll();
        printList(animalShelter.getShelter());
        animalShelter.dequeueCat();
        printList(animalShelter.getShelter());
        animalShelter.dequeueDog();
        printList(animalShelter.getShelter());
    }
    
    public Node<String> getShelter() {
        return shelter;
    }

    public static void printList(Node<String> input_node) {
        Node<String> current = input_node;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void enqueue(Node<String> newAnimal) {
        if (shelter ==  null) {
            shelter = newAnimal;
        }
        else {
            Node<String> walk = shelter;
            while (walk.next!=null) {
                walk = walk.next;
            }
            walk.next = newAnimal;
        }
        newAnimal.next = null;
    }

    public Node<String> dequeueAll() {
        if (shelter == null) return null;
        Node<String> animal = shelter;
        shelter = shelter.next;
        animal.next = null;
        return animal;
    }

    public Node<String> dequeueCat() {
        if (shelter == null) return null;
        Node<String> animal = shelter;
        while (animal.next!=null) {
            if (animal.next.data == "Cat") {
                Node<String> cat = animal.next;
                animal.next = animal.next.next;
                cat.next  = null;
                return cat;
            }
            animal = animal.next;
        }
        return null;
    }

    public Node<String> dequeueDog() {
        if (shelter == null) return null;
        Node<String> animal = shelter;
        while (animal.next!=null) {
            if (animal.data == "Dog") {
                Node<String> dog = animal.next;
                animal.next = animal.next.next;
                dog.next = null;
                return dog;
            }
            animal =  animal.next;
        }
        return null;
    }
}
