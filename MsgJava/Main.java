import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a name: ");
        String name = input.nextLine();
        System.out.println("Enter a port number (12345)");
        int port = input.nextInt();


        Peer peer1 = new Peer(name, port);

        peer1.startService();
    }
}
    