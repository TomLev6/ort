package main;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        // add GamePanel to the window
        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack(); // window adjust his size to the game panel (gp)


        window.setLocationRelativeTo(null);// centered the screen
        window.setVisible(true);

        gp.launchGame();

    }
}
