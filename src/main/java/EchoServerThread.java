import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EchoServerThread extends Thread {
    private Client client;
    private EchoServer server;

    public EchoServerThread(EchoServer server, Client client) {
        this.client = client;
        this.server = server;
    }

    public void run() {
        System.out.println("Accepted client on thread: " + Thread.currentThread().getName());
        server.addClient(client);
        System.out.println("Number of clients connected: " + server.getClients().size());

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.socket.getInputStream()));

            String message;
            while ((message = in.readLine()) != null) {
                server.broadcastMessage(message);
            }
            client.socket.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
