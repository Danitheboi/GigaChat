package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginWindowController {
    @FXML
    public TextField txtName;

    public void initialize(){

    }
    @FXML
    private void LoginBotton(ActionEvent event) {
        String enteredName = txtName.getText();
        System.out.println("Entered name: " + enteredName);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/chatapp/gigachat/ClientWindow.fxml"));
            Parent root = loader.load();
            ClientWindowController clientController = loader.getController();

            // Brug den nye metode til at indstille brugernavnet
            clientController.setClientNameExternally(enteredName);

            // Opret et nyt vindue for din chatklient
            Stage chatStage = new Stage();
            chatStage.setTitle("Chat Client - " + enteredName);
            chatStage.setScene(new Scene(root, 450, 550));
            chatStage.show();

            // Luk loginvinduet
            ((Stage) txtName.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
