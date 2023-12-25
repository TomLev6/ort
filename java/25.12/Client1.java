import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {

    public static void main(String[] args) {

        final String serverAddress = "127.0.0.1"; //"192.168.128.127";
        final int port = 9080;
        try
        {
            Socket my_socket = new Socket(serverAddress, port);

            Thread recvier = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            recv(my_socket);
                        } catch (IOException e) {
                            try {
                                throw new RuntimeException(e);
                            } catch (RuntimeException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
            });
            recvier.start();
            while (true){
                send(my_socket);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("exists..");
        }


    }
    public static void recv(Socket my_socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(my_socket.getInputStream()));
        String answer = reader.readLine();
        System.out.println(answer);

    }
    public static void send(Socket my_socket) throws IOException {
        PrintWriter writer = new PrintWriter(my_socket.getOutputStream(), true);
        Scanner s = new Scanner(System.in);
        String msg = s.next();
        writer.println(msg);
        System.out.println("Sending " + msg + " to the Server");
    }
}