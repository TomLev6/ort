import java.lang.Math;

public class stam2 extends Thread
{
    public static int sum;
    public void run()
    {
        int min = 1;
        int max = 10;
        double a = Math.random() * (max - min + 1) + min;
        sum+=(int)a;

    }
    public static void main(String[] args) throws InterruptedException {
        sum = 0;
        stam2 s = new stam2();
        stam2 s1 = new stam2();
        stam2 s2 = new stam2();
        stam2 s3 = new stam2();
        stam2 s4 = new stam2();
        s.start();
        s1.start();
        s2.start();
        s3.start();
        s4.start();
        s.join();
        s1.join();
        s2.join();
        s3.join();
        s4.join();
        System.out.println(sum);
        /*
         sum = 0;
        stam2 s = new stam2();
        stam2 s1 = new stam2();
        stam2 s2 = new stam2();
        stam2 s3 = new stam2();
        stam2 s4 = new stam2();
        List<stam2> list = new ArrayList<>();
        list.add(s);
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        for (stam2 st: list)
        {
            st.start();
        }
        for (stam2 st: list)
        {
            st.join();
        }
        System.out.println(sum);
        *\
    }
}
