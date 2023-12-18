import java.io.*;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class implements java Socket server
 * @author pankaj
 *
 */
public class server {

    public static void main(String args[]) throws IOException, ClassNotFoundException{
        int port = 9876;
        ServerSocket server_socket = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true) {
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection

            Socket client_socket = server_socket.accept();
            System.out.println("opened a socket with.. " + client_socket.getInetAddress());
            try {

                
                //recieve
                BufferedReader reader = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
                String move = reader.readLine();
                System.out.println("Message Received: " + move); // 1-9 1|2|3

                //send
                PrintWriter writer =  new PrintWriter(client_socket.getOutputStream(), true);
                //write answer to client
                writer.println("server received: " + move);

                if (move.equalsIgnoreCase("exit")) {
                    reader.close();
                    writer.close();
                    client_socket.close();
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {

                client_socket.close();
            }
        }
        //server_socket.close();
    }
}