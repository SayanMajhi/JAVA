import java.util.HashMap;
public class HashMep {
    static class Element {
        String key;
        String value;
        Element(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    Element[] elements;
    HashMep() {
        elements = new Element[100];
    }
public void put(String key, String value) {
        Element element = new Element(key, value);
        int hash = key.hashCode()%elements.length;
        elements[hash] = element;
}
public String get(String key) {
    Element element = elements[key.hashCode()%elements.length];
    if (element.key.equals(key)) {
        return element.value;
    }else{
        return null;
    }
}
public static void main(String[] args) {
        HashMep hm = new HashMep();
        hm.put("a", "a");
        hm.put("b", "b");
        hm.put("c", "c");
        hm.put("d", "d");

        System.out.println(hm.get("a"));
}

}
