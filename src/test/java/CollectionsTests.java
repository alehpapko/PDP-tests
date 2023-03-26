import models.Person;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Comparator.reverseOrder;
import static org.junit.jupiter.api.Assertions.*;


public class CollectionsTests {

    @Test
    public void listTest() {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            arrayList.add(i);
        }

        arrayList.sort(reverseOrder());
        assertEquals(10, arrayList.get(0));

        arrayList.set(5, 1111);
        assertEquals(1111, arrayList.get(5));
        assertTrue(arrayList.contains(1111));

        arrayList.clear();
        arrayList.add(1717);
        assertEquals(1717, arrayList.get(0));
        assertEquals(1, arrayList.size());



        List<Person> personList = new LinkedList<>();
        personList.add(new Person("Zed", 19));
        personList.add(new Person("Bob", 29));
        personList.add(new Person("John", 25));
        personList.add(new Person("James", 34));
        System.out.println("Default list: " + personList);

        // sort by age
        personList.sort(Comparator.comparingInt(Person::getAge));
        System.out.println("Sorted by age: " + personList);

        // sort by name
        personList.sort(Comparator.comparing(Person::getName));
        System.out.println("Sorted by name: " + personList);

        // remove if start j
        personList.removeIf(person -> person.getName().startsWith("J"));
        System.out.println("Removed starts 'J': " + personList);
    }

    @Test
    public void mapTest() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Bob");
        map.put(2, "Zed");
        map.put(3, "Bob");
        // rewrite if present
        map.put(3, "QWERTY");
        System.out.println(map);

        // not rewrite if present
        map.putIfAbsent(3, "Bob");
        System.out.println(map);

        System.out.println("entrySet: " + map.entrySet());
        System.out.println("keySet: " + map.keySet());
        System.out.println("values: " + map.values());
    }

    @Test
    public void setTest() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 25; i++) {
            list.add(1);
        }
        System.out.println("List: " + list);

        Set<Integer> set = new HashSet<>(list);
        System.out.println("Set from list: " + set);

        /*
          HashSet use the `equals()` & `hashCode()` implementations
          of the Custom classes to check for duplicates and ignore them
        */
    }

    @Test
    public void queueTest() {
        // FIFO
        Queue<Person> queue = new LinkedList<>();
        queue.add(new Person("Zed", 19));
        queue.add(new Person("Bob", 29));
        queue.add(new Person("John", 25));
        queue.add(new Person("James", 34));

        System.out.println("Peeked Element: " + queue.peek());
        System.out.println(queue);

        System.out.println("Polled Element: " + queue.poll());
        System.out.println(queue);


        // LIFO
        Stack<Person> stack = new Stack<>();
        stack.add(new Person("Zed", 19));
        stack.add(new Person("Bob", 29));
        stack.add(new Person("John", 25));
        stack.add(new Person("James", 34));

        System.out.println("Peeked Element: " + stack.peek());
        System.out.println(stack);

        System.out.println("Polled Element: " + stack.pop());
        System.out.println(stack);
    }
}

