import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class EchoServer {
    private ServerSocket serverSocket;
    private Executor executor;
    private List<Client> clients;

    EchoServer(ServerSocket serverSocket, Executor executor) {
        this.serverSocket = serverSocket;
        this.executor = executor;
        this.clients = new ArrayList<>();
    }

    public void start() {
        System.out.println("Started server");
        System.out.println("Listening for clients ...");

        while(true) {
            try {
                listenForClients();
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void listenForClients() throws IOException {
        System.out.println("Accepting ...");
        Socket socket = serverSocket.accept();
        Client client = new Client(socket);
        System.out.println("Accepted.");
        executor.execute(new EchoServerThread(this, client));
    }

    public List<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void broadcastMessage(String message) {
        for(Client client : clients) {
            try {
                PrintWriter out = new PrintWriter(client.socket.getOutputStream(), true);
                out.println(message);
            } catch(IOException e) {
               System.out.println(e.getMessage());
            }
        }
    }
}
