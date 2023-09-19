package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    public PrintWriter output;  // Gjort offentlig for simplicitetens skyld, men du kan også bruge en getter.
    private Scanner input;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        ChatServer.clients.add(this);  // Tilføj denne klienthandler til listen over klienter
    }

    @Override
    public void run() {
        try {
            input = new Scanner(clientSocket.getInputStream());
            output = new PrintWriter(clientSocket.getOutputStream(), true);

            while (input.hasNextLine()) {
                String message = input.nextLine();
                System.out.println("Received: " + message);

                // Send beskeden til alle tilsluttede klienter
                for (ClientHandler client : ChatServer.clients) {
                    client.output.println(message);
                }
            }

            clientSocket.close();
            ChatServer.clients.remove(this);  // Fjern denne klienthandler fra listen, når den er færdig
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
