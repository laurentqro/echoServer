import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FakeServerSocket extends ServerSocket {
    private Socket fakeClientSocket;

    FakeServerSocket(Socket fakeClientSocket) throws IOException {
        this.fakeClientSocket = fakeClientSocket;
    }

    public Socket accept() {
        return fakeClientSocket;
    }
}
