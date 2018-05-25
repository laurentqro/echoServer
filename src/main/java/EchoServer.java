import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    EchoServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.threadPool = Executors.newFixedThreadPool(2);
    }

    public void start() {
        System.out.println("Started server");
        System.out.println("Listening for clients ...");

        while(true) {
            try {
                Socket socket = serverSocket.accept();
                threadPool.execute(new EchoServerThread(socket));
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
