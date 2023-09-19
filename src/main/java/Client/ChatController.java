package Client;

import Server.ChatServer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatController {
    @FXML
    private TextArea chatArea;

    @FXML
    private TextField inputField;

    private PrintWriter output;

    public void initialize() {
        try {
            Socket socket = new Socket("localhost", ChatServer.PORT);
            output = new PrintWriter(socket.getOutputStream(), true);

            new Thread(() -> {
                try {
                    Scanner input = new Scanner(socket.getInputStream());
                    while (input.hasNextLine()) {
                        String message = input.nextLine();
                        Platform.runLater(() -> chatArea.appendText(message + "\n"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sendMessage() {
        String message = inputField.getText();
        output.println(message);
        inputField.clear();
    }
}
