import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;

public class EchoServer {
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            int portNumber = 8000;
            ServerSocket serverSocket = new ServerSocket(portNumber);
            EchoServer echoServer = new EchoServer(serverSocket);
            echoServer.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    EchoServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start() {
        try {
            System.out.println("Started server");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted connection from client");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
