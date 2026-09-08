import java.util.Scanner;

public class Ben {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a name: ");
        String name = input.nextLine();
        System.out.println("Enter a port number (123345)");
        int port = input.nextInt();


        Peer peer2 = new Peer(name, port);

        peer2.startService();
    }
}
