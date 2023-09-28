package Server;

import Client.ClientHandler;
import entitet.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer {
    public static final int PORT = 12345;
    public static final CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();
    public static final Map<String, User> users = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started on port: " + PORT + "...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected...");

            ClientHandler handler = new ClientHandler(clientSocket);

            new Thread(handler).start();
        }
    }
}
