package Cliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Cliente extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ClienteGUI.fxml"));
        primaryStage.setTitle("Lado del Cliente");
        primaryStage.setScene(new Scene(root, 488, 408));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
