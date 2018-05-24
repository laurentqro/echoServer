import java.io.*;
import java.net.Socket;

public class EchoClient {
    private ConsoleIO consoleIO;
    private Socket echoSocket;

    public static void main(String[] args) {
    }

    public EchoClient(ConsoleIO consoleIO, Socket echoSocket) {
        this.consoleIO = consoleIO;
        this.echoSocket = echoSocket;
    }

    public void start() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);

            String userInput;

            while ((userInput = consoleIO.readLine()) != null) {
                out.println(userInput);
                consoleIO.println("echo: " + in.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
