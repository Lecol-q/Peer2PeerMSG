import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server extends Thread{
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for client...");
            
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");
            boolean connected = true;

            // receives a message sent from peer
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // sends message to the peer
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
        

            String user;

            while(connected){
                Scanner input = new Scanner(System.in);
                user = input.nextLine();

                // in.readLine() is object that is reading from the other side, so in this case reading from client side
                String message = in.readLine();
                if(message != null){
                    System.out.println("Client: " + message);
                }
                out.println("Server: " + user);
            }

            // recieving thread


            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}