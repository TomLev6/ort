import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, Car> map = new HashMap<>();

        Car c1 = new Car("BMW",2018,"338-89-921");
        Car c2 = new Car("PORSCHE",2009,"48-049-45");
        Car c3 = new Car("KIA",2013,"51-079-40");

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
    }
}
