import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    static ArrayList<Socket> clients = new ArrayList<>();

    public static void main(String[] args) {
        ServerSocket server_socket;
        final int PORT = 9080;
        try {
            server_socket = new ServerSocket(PORT);
            System.out.println("Server started. Waiting for client...");
            while (true) {
                Socket client_socket = server_socket.accept();
                if (!clients.contains(client_socket)) {
                    clients.add(client_socket);
                }
                System.out.println("Client connected: " + client_socket.getInetAddress());
                HandleClient client = new HandleClient(client_socket);
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    class HandleClient  extends  Thread
    {
        private final Socket client_socket;

        public HandleClient(Socket client)
        {
            this.client_socket = client;
        }

        @Override
        public void run() {
            while (true)
            {
                String client_msg = getString();
                for (Socket c : Main.clients) {
                    PrintWriter writerC = null;
                    try {
                        writerC = new PrintWriter(c.getOutputStream(), true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    writerC.println("Server echo: " + client_msg);
                }

            }
        }
        private String getString() {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String client_msg = null;
            try {
                client_msg = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return client_msg;
        }
    }


