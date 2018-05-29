import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceivedMessagesListener extends Thread {
    private BufferedReader in;
    private ConsoleIO consoleIO;

    public ReceivedMessagesListener(Socket socket, ConsoleIO consoleIO) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.consoleIO = consoleIO;
    }

    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                consoleIO.println(message);
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
