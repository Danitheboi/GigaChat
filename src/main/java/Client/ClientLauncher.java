package Client;

import Controller.ClientWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientLauncher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/chatapp/gigachat/ClientWindow.fxml"));
        ClientWindowController controller = new ClientWindowController();
        primaryStage.setTitle("Gigachat");
        primaryStage.setScene(new Scene(root,450,550));
        controller.initialize("");


        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Login Window");
        stage.initOwner(primaryStage.getScene().getWindow());
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/com/chatapp/gigachat/LoginWindow.fxml"))));
        stage.centerOnScreen();
        stage.show();

    }
}