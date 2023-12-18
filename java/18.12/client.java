import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * This class implements java socket client
 * @author pankaj
 *
 */
public class client {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        final String serverAddress = "127.0.0.1";
        final int port = 9876;
        while (true) {
            //establish socket connection to server
            Socket my_socket = new Socket(serverAddress, port);
            try {

                //get the msg
                PrintWriter writer = new PrintWriter(my_socket.getOutputStream(), true);
                Scanner s = new Scanner(System.in);
                int move = s.nextInt();
                //send msg to server
                writer.println(move);

                // 1|2|3
                // 4|5|6
                // 7|8|9

                System.out.println("Sending " + move + " to the Server");

                //read the server response message
                BufferedReader reader = new BufferedReader(new InputStreamReader(my_socket.getInputStream()));
                String answer = reader.readLine();
                System.out.println(answer);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                my_socket.close();
            }
        }
    }
}