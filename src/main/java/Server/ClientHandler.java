package Client;

import Controller.ClientWindowController;
import Server.ChatServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private PrintWriter out;
    private Socket clientSocket;
    public PrintWriter output;
    private Scanner input;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        ChatServer.clients.add(this);
        System.out.println("ClientHandler created for client: " + clientSocket.getInetAddress());
    }

    @Override
    public void run() {
        try {
            input = new Scanner(clientSocket.getInputStream());
            output = new PrintWriter(clientSocket.getOutputStream(), true);

            while (input.hasNextLine()) {
                String message = input.nextLine();
                System.out.println("Received: " + message);


                for (ClientHandler client : ChatServer.clients) {
                    if(client != this) {
                        client.output.println(message);
                    }
                }
            }

            clientSocket.close();
            ChatServer.clients.remove(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}