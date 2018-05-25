import java.io.*;
import java.net.Socket;

public class EchoClient {
    private ConsoleIO consoleIO;
    private PrintWriter out;
    private BufferedReader in;

    public EchoClient(ConsoleIO consoleIO, Socket echoSocket) throws IOException {
        this.consoleIO = consoleIO;
        this.out = new PrintWriter(echoSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
    }

    public void start() {
        try {
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
