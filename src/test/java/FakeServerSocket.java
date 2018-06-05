import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class FakeServerSocket extends ServerSocket {
    private LinkedList<Socket> fakeClientSockets;

    FakeServerSocket(LinkedList<Socket> fakeClientSockets) throws IOException {
        this.fakeClientSockets = fakeClientSockets;
    }

    public Socket accept() {
        return fakeClientSockets.pop();
    }
}
