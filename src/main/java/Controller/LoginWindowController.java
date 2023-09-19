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

    public void LoginBotton(ActionEvent actionEvent) throws IOException {
        if(!txtName.getText().isEmpty()&&txtName.getText().matches("[A-Åa-å0-9]")){
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/com/chatapp/gigachat/ClientWindow.fxml"));

            ClientWindowController controller = new ClientWindowController();
            controller.setClientName(txtName.getText());

            primaryStage.setScene(new Scene(root,450,550));
            primaryStage.setTitle(txtName.getText());

            primaryStage.show();

            txtName.clear();
        }

    }
}
