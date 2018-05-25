import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private List<Socket> clients;

    EchoServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.threadPool = Executors.newFixedThreadPool(2);
        this.clients = new ArrayList<>();
    }

    public void start() {
        System.out.println("Started server");
        System.out.println("Listening for clients ...");

        while(true) {
            try {
                Socket socket = serverSocket.accept();
                threadPool.execute(new EchoServerThread(this, socket));
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addClient(Socket socket) {
        clients.add(socket);
    }

    public void broadcastMessage(String message) {
        for(Socket client : clients) {
            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                out.println(message);
            } catch(IOException e) {
               System.out.println(e.getMessage());
            }
        }
    }
}
