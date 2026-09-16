import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Peer extends Thread {
    // server fields
    ServerSocket serverSocket;
    Socket clientSocket;
    int port;
    boolean connected;
    String IP;

    BufferedReader in;
    PrintWriter out;

    ChatController chatController;

    String name;

    String otherPeerName;

    public Peer(String name, String IP,int port) {
        this.name = name;
        this.IP = IP;
        this.port = port;
    }

    // think of you are sending the message
    public void sendMessage(String message){
        // out is sending message
            out.println(message);
            chatController.displayMessage(name ,message);
    }

    // method call is .start() thread
    /*  this method's purpose is to continually listen for messages
     think of it as, you are waiting for messages to come in. (Waiting for messages from other person) */
    @Override 
    public void run() {
        try {
            connected = true;

            // endless message refresh
            while (connected) {
                String messageReceived = in.readLine();
                if(messageReceived != null){
                    chatController.displayMessage(otherPeerName, messageReceived);
                    System.out.println(messageReceived);
                    
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

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Peer Connected!");
            out.println(name);
            otherPeerName = in.readLine();

            connected = true;
        } catch (IOException e) {
            connected = false;
        }
    }

    public void create(){
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for client...");

            // accepted
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // listening
            System.out.println("Peer Connected!");
            out.println(name);
            otherPeerName = in.readLine();

            connected = true;
        } catch (IOException e) {
            connected = false;
        }
    }

    public void receiveChatController(ChatController chatController){
        this.chatController = chatController;
    }
    
}
