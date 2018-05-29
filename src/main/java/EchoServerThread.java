import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EchoServerThread extends Thread {
    private Socket socket;
    private EchoServer server;

    public EchoServerThread(EchoServer server, Socket socket) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            System.out.println("Accepted client on thread: " + Thread.currentThread().getName());
            server.addClient(socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;
            while ((message = in.readLine()) != null) {
                server.broadcastMessage(message);
            }
            socket.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
