import java.util.Arrays;

public class HashMep {

    private int size = 0;

    static class Element {
        String key;
        String value;
        Element next;

        Element(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Element[] elements;

    public HashMep() {
        elements = new Element[100];
    }

    public int getSize() {
        return size;
    }

    private int getIndex(String key) {
        return (key.hashCode() & 0x7fffffff) % elements.length;
    }

    public void put(String key, String value) {

        if (size >= elements.length) {
            resize();
        }
        int index = getIndex(key);
        if (elements[index] == null) {
            elements[index] = new Element(key, value);
            size++;
            return;
        }

        Element curr = elements[index];

        while (true) {

            // Key already exists -> update value
            if (curr.key.equals(key)) {
                curr.value = value;
                return;
            }

            if (curr.next == null)
                break;

            curr = curr.next;
        }

        // Collision -> append
        curr.next = new Element(key, value);
        size++;
    }

    public String get(String key) {

        int index = getIndex(key);

        Element curr = elements[index];

        while (curr != null) {

            if (curr.key.equals(key))
                return curr.value;

            curr = curr.next;
        }

        return null;
    }

    public void remove(String key) {

        int index = getIndex(key);

        Element curr = elements[index];
        Element prev = null;

        while (curr != null) {

            if (curr.key.equals(key)) {

                if (prev == null) {
                    elements[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }

                size--;
                return;
            }

            prev = curr;
            curr = curr.next;
        }
    }

    private void resize() {

        Element[] old = elements;

        elements = new Element[old.length * 2];

        size = 0;

        for (Element head : old) {

            while (head != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }

    public static void main(String[] args) {

        HashMep map = new HashMep();

        map.put("a", "Apple");
        map.put("b", "Ball");
        map.put("c", "Cat");

        System.out.println(map.get("a"));

        map.put("a", "Ant");

        System.out.println(map.get("a"));

        map.remove("a");

        System.out.println(map.get("a"));

        System.out.println("Size = " + map.getSize());
    }
}