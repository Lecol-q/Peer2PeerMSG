import java.io.*;
import java.net.*;

// TEST CLASS
public class Client {
    public static void main(String[] args) {
        boolean connected = true;
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (connected) {
                String response = in.readLine();
                System.out.println("Server: " + response);
                
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
