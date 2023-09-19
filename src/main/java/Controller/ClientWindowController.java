package Controller;

import Server.ChatServer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientWindowController {
    @FXML
    private TextArea chatArea;

    @FXML
    private TextField inputField;

    private PrintWriter output;

    private String clientName = "Client";

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }



    public void initialize() {

        try {
            Socket socket = new Socket("localhost", ChatServer.PORT);
            output = new PrintWriter(socket.getOutputStream(), true);

            new Thread(() -> {
                try {
                    Scanner input = new Scanner(socket.getInputStream());
                    while (input.hasNextLine()) {
                        String message = input.nextLine();
                        Platform.runLater(() -> chatArea.appendText(clientName + ": " + message + "\n"));
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

    public void shutdown() {

    }
}
