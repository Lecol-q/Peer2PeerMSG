import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChatController {
    private Stage stage;
    public Scene scene;
    private Parent root;

    Peer peer;
    String otherPeer;

    ChatController chatController;

    @FXML 
    TextField messageField;
    
    @FXML 
    VBox displayField;
    
    @FXML 
    Button send;

    String message;
    String messageRecieved;
    

    public String getMessage(){
        message = messageField.getText();
        return message;
    }

    public void sendMessageUI(ActionEvent e){
        peer.sendMessage(getMessage());
    }

    public void displayMessage(String sender,String messageRecieved){

        Platform.runLater(() -> {
            Text text = new Text();
            text.setStyle("-fx-color: white");
            text.setText(sender +  ": " + messageRecieved);
            displayField.getChildren().add(text);
        });
    }

    public void toLogin(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void receivePeer(Peer peer){
        this.peer = peer;
    }

    public void receiveOther(String otherPeer){
        this.otherPeer = otherPeer;
    }

}
