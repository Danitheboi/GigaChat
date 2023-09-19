package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer {
    public static final int PORT = 12345;

    // Trådsikker liste over alle klienthåndterere
    public static final CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected...");
            ClientHandler handler = new ClientHandler(clientSocket);
            new Thread(handler).start();
        }
    }
}
