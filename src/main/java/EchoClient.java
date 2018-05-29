import java.io.*;
import java.net.Socket;

public class EchoClient {
    private ConsoleIO consoleIO;
    private PrintWriter out;
    private Socket socket;

    public EchoClient(ConsoleIO consoleIO, Socket socket) throws IOException {
        this.consoleIO = consoleIO;
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void start() {
        try {
            new ReceivedMessagesListener(socket, consoleIO).start();

            String userInput;
            while ((userInput = consoleIO.readLine()) != null) {
                out.println(userInput);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
