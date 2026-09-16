import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// mvn javafx:run
public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    // stage method
    @Override 
        public void start(Stage stage) throws Exception {
            try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);

            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
}
    