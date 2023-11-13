import java.lang.Math;
import java.util.Scanner;

public class stam extends Thread
{
    public void run()
    {
        boolean running = true;
        int min = 1;
        int max = 100;
        double a = Math.random() * (max - min + 1) + min;
        int guesses = 0;

        while (running)
        {
            System.out.println("Enter Your Guess: ");
            double num = new Scanner(System.in).nextInt();
                if (num == (int)a)
                {
                    System.out.println("Equals!");
                    running = false;
                }
                else if (num > (int)a)
                {
                    System.out.println("Smaller!");
                }
                else
                {
                    System.out.println("Greater!");
                }

            guesses++;
        }
        System.out.println("Your Guesses: " + guesses);
    }
    public static void main(String[] args)
    {
        stam s = new stam();
        s.start();

    }
}
