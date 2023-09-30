package Controller;

import Server.ChatServer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientWindowController {
    @FXML
    private TextArea chatArea;

    @FXML
    private TextField inputField;

    private PrintWriter output;
    private String clientName = "";

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientNameExternally(String clientName) {
        this.clientName = clientName;
        System.out.println("Client name set externally in ClientWindowController: " + clientName);
    }

    public void connectToServer() {
        try {
            Socket socket = new Socket("localhost", ChatServer.PORT);
            output = new PrintWriter(socket.getOutputStream(), true);

            new Thread(() -> {
                try {
                    Scanner input = new Scanner(socket.getInputStream());
                    while (input.hasNextLine()) {
                        String message = input.nextLine();
                        System.out.println("Received from server: " + message);

                        if (chatArea != null) {
                            Platform.runLater(() -> chatArea.appendText(message + "\n"));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Platform.runLater(() -> chatArea.appendText("Fejl ved modtagelse af beskeder fra serveren.\n"));
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
            if (chatArea != null) {
                Platform.runLater(() -> chatArea.appendText("Kunne ikke forbinde til serveren.\n"));
            }
        }
    }


    @FXML
    public void sendMessage() {
        String message = inputField.getText();
        String formattedMessage = clientName + ": " + message;
        chatArea.appendText(formattedMessage + "\n");
        inputField.clear();

        if (output != null) {
            output.println(formattedMessage);
        } else {
            System.err.println("Output is not initialized!");
        }
    }

    public void shutdown() {

    }
}
