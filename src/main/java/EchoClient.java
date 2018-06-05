import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executor;

public class EchoClient {
    private ConsoleIO consoleIO;
    private PrintWriter out;
    private Socket socket;
    private Executor executor;
    private String name;

    public EchoClient(String name, ConsoleIO consoleIO, Socket socket, Executor executor) throws IOException {
        this.consoleIO = consoleIO;
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.executor = executor;
    }

    public void start() {
        try {
            executor.execute(new ReceivedMessagesListener(socket, consoleIO));

            String userInput;
            while ((userInput = consoleIO.readLine()) != null) {
                out.println(userInput);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public String getName() {
        return name;
    }
}
