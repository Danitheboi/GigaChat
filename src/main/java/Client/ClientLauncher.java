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

        Parent loginRoot = FXMLLoader.load(getClass().getResource("/com/chatapp/gigachat/LoginWindow.fxml"));
        Stage loginStage = new Stage();

        loginStage.initModality(Modality.WINDOW_MODAL);
        loginStage.setTitle("Login Window");
        loginStage.initOwner(primaryStage.getScene().getWindow());
        loginStage.setScene(new Scene(loginRoot));
        loginStage.centerOnScreen();

        loginStage.show();

    }
}