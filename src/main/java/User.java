import java.io.*;
import java.net.Socket;

public class User {
    private PrintWriter out;

    public User(Socket socket) throws IOException {
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }
}
