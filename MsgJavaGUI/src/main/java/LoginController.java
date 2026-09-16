import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    String name;
    String IP;
    int port;
    Peer peer = new Peer(name, IP, port);

    String message;

    @FXML 
    TextField nameField, ipField, portField;

    public void Create(ActionEvent e){
        peer.name = getName(e);
        peer.IP = getIP(e);
        peer.port = getPort(e);
        System.out.println("Create pressed");
        System.out.println(name + " " + IP + " " + port);
        peer.create();
        peer.start();
        try { 
            toChat(e);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public void Join(ActionEvent e){
        peer.name = getName(e);
        peer.IP = getIP(e);
        peer.port = getPort(e);
        System.out.println("Join pressed");
        System.out.println(name + " " + IP + " " + port);
        peer.joinPeer();
        peer.start();
        try {
            toChat(e);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public void toChat(ActionEvent event) throws IOException{
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        Parent root = loginLoader.load();
        ChatController chatController = loginLoader.getController();
        chatController.receivePeer(peer);
        peer.receiveChatController(chatController);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // getters
    public String getName(ActionEvent e){
        name = nameField.getText();
        return name;
    }

    public String getIP(ActionEvent e){
        IP = ipField.getText();
        return IP;
    }

    public int getPort(ActionEvent e){
        port = Integer.parseInt(portField.getText());
        return port;
    }
}
