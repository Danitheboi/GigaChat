package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class
LoginWindowController {
    @FXML
    public TextField txtName;

    public void initialize() {

    }
    @FXML
    private void LoginBotton(ActionEvent event) {
        String enteredName = txtName.getText();
        System.out.println("Entered name: " + enteredName);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/chatapp/gigachat/ClientWindow.fxml"));
            Parent root = loader.load();
            ClientWindowController clientController = loader.getController();

            clientController.setClientNameExternally(enteredName);
            clientController.connectToServer();

            Stage stage = new Stage();
            stage.setTitle("Chat Window");
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) txtName.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
