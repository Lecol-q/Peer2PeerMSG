import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Peer extends Thread {
    // server fields
    ServerSocket serverSocket;
    Socket clientSocket;
    int port;
    boolean connected;
    String IP;

    // I/O
    Scanner input;
    BufferedReader in;
    PrintWriter out;

    // user data
    String name;
    int response;

    // constructor with name, and port
    public Peer(String name, String IP,int port) {
        this.name = name;
        this.IP = IP;
        this.port = port;
    }

    public void startService() {
        System.out.println("1. Create Connection");
        System.out.println("2. Join Connection");
        Scanner choice = new Scanner(System.in);

        response = choice.nextInt();

        if(response == 1){
            create();
        }
        if(response == 2){
            joinPeer();
        }

        try {
            // sends message to the peer
            out = new PrintWriter(clientSocket.getOutputStream());
            String userInput;
            input = new Scanner(System.in);

            // run method (thread)
            start();

            // endless user input
            while (connected) {
                userInput = input.nextLine();
                // this is literally being sent
                out.println(userInput);
                out.flush();
            }

            // end of connection
            out.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            connected = false;
        }
    }

    // method call is .start() thread
    @Override 
    public void run() {
        try {
            connected = true;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // endless message refresh
            while (connected) {
                // receives a message sent from peer
                // in.readLine() is object that is reading from the other side, so in this case
                String message = in.readLine();
                if(message != null){
                    System.out.println(message);
                }
                else
                    connected = false;
            }
            // end of connection
            in.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            connected = false;
        }
    }

    public void joinPeer(){
        try {
            clientSocket = new Socket(IP, port);
            System.out.println("Peer Connected!");
            connected = true;
        } catch (IOException e) {
            connected = false;
        }
    }

    public void create(){
        try {
            // listening
            serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for client...");
            // accepted
            clientSocket = serverSocket.accept();
            System.out.println("Peer Connected!");
            connected = true;
        } catch (IOException e) {
            connected = false;
        }
    }
}
