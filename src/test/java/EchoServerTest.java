import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class EchoServerTest {
    private ByteArrayInputStream inputStream = new ByteArrayInputStream("hello, world!".getBytes());
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private Executor executor = Executors.newSingleThreadExecutor();

    @Test
    public void echoes() throws IOException {
        FakeClientSocket fakeClientSocket = new FakeClientSocket(inputStream, outputStream);
        LinkedList<Socket> fakeClientSockets = new LinkedList<>(Arrays.asList(fakeClientSocket));
        FakeServerSocket fakeServerSocket = new FakeServerSocket(fakeClientSockets);
        Client client = new Client(fakeClientSocket);
        EchoServer echoServer = new EchoServer(fakeServerSocket, executor);
        EchoServerThread echoServerThread = new EchoServerThread(echoServer, client);

        echoServerThread.run();

        assertEquals("hello, world!", outputStream.toString().trim());
    }

    @Test
    public void broadcastsToAllClients() throws IOException {
        EchoClient client1 = createClient("client1", "hello from client 1");
        EchoClient client2 = createClient("client2", "hello from client 2");
        EchoClient client3 = createClient("client2", "hello from client 3");

        LinkedList<Socket> fakeClientSockets = new LinkedList<>(Arrays.asList(client1.getSocket(), client2.getSocket(), client3.getSocket()));
        FakeServerSocket fakeServerSocket = new FakeServerSocket(fakeClientSockets);
        EchoServer echoServer = new EchoServer(fakeServerSocket, new SynchronousExecutor());

        echoServer.listenForClients();
        echoServer.listenForClients();
        echoServer.listenForClients();

        assertEquals("hello from client 1\nhello from client 2\nhello from client 3", client1.getSocket().getOutputStream().toString().trim());
    }

    private EchoClient createClient(String name, String message) throws IOException {
        ByteArrayInputStream fakeConsoleIn = new ByteArrayInputStream(message.getBytes());
        ByteArrayOutputStream fakeConsoleOut = new ByteArrayOutputStream();
        ConsoleIO consoleIO = new ConsoleIO(fakeConsoleIn, fakeConsoleOut);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(message.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        FakeClientSocket fakeClientSocket = new FakeClientSocket(inputStream, outputStream);

        return new EchoClient(name, consoleIO, fakeClientSocket, executor);
    }
}