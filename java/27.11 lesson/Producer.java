import java.util.LinkedList;

public class Producer {
    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 2;

    public void produce() throws InterruptedException {
        Message m = new Message("hello");
        int value = 0;
        while (true) {
            synchronized (this) {
                while (list.size() == capacity)
                    wait();
                System.out.println("Producer produced-"
                        + value  + m.msg);
                list.add(value++);
                notify();
                Thread.sleep(1000);
            }
        }
    }
    public void consume() throws InterruptedException {
        Message m = new Message("hello");
        while (true) {
            synchronized (this) {
                while (list.isEmpty())
                    wait();
                int val = list.removeFirst();

                System.out.println("Consumer consumed-"
                        + val+ m.msg);
                notify();
                Thread.sleep(1000);
            }
        }
    }
}
