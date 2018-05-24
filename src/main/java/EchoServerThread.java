import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoServerThread extends Thread {
    private Socket socket;

    public EchoServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            System.out.println("Accepted client on thread: " + Thread.currentThread().getName());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
            socket.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
