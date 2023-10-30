import java.util.*;

public class Main {
    public static void main(String[] args) {

        Map<String, Car> map = new HashMap<>();

        Car c1 = new Car("BMW",2018,"338-89-921");
        Car c2 = new Car("PORSCHE",2009,"48-049-45");
        Car c3 = new Car("KIA",2013,"51-079-40");

        ArrayList<Car> arr = new ArrayList<>();
        arr.add(c1);
        arr.add(c2);
        arr.add(c3);


        map.put("Tom",c1);
        map.put("or",c2);
        map.put("danny",c3);

        for (String s: map.keySet()){
            System.out.println(s);
        }
        for (Car c: map.values()){
            System.out.println(c);
        }
        for (Map.Entry<String, Car> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " drives " + entry.getValue().getBrand());
        }
        System.out.println(arr);
        System.out.println("\n");
        Collections.sort(arr);
        System.out.println(arr);

    }
}
