import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        for (int i = 1; i <= 10; i++) {
            map.put("key" + i, i);
        }
        System.out.println("_______________");
        map.printArray();
        System.out.println("_______________");
        System.out.println("size = " + map.size());
        System.out.println("_______________");
        System.out.println("get(key10) = " + map.get("key10"));
        System.out.println("_______________");
        map.remove("key1");
        map.remove("key3");
        map.remove("key5");
        map.printArray();
        System.out.println("_______________");
        System.out.println("size = " + map.size());
        map.remove("key5");
        System.out.println("_______________");
        for (int i = 1; i <= 5; i++) {
            map.put("key" + i, 1);
        }
        map.printArray();
        System.out.println("_______________");
        System.out.println("size = " + map.size());

    }
}
