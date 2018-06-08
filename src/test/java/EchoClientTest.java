import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class EchoClientTest {
    @Test
    public void messageIsSentToServer() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("hello, world!".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Socket fakeClientSocket = new FakeClientSocket(inputStream, outputStream);

        ByteArrayInputStream fakeConsoleIn = new ByteArrayInputStream("hello, world!".getBytes());
        ByteArrayOutputStream fakeConsoleOut = new ByteArrayOutputStream();
        ConsoleIO consoleIO = new ConsoleIO(fakeConsoleIn, fakeConsoleOut);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        EchoClient echoClient = new EchoClient("foobar", consoleIO, fakeClientSocket, executor);

        echoClient.start();

        assertEquals("hello, world!", outputStream.toString().trim());
    }

    @Test
    public void sentMessageIsSentBack() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("hello, world!".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Socket fakeClientSocket = new FakeClientSocket(inputStream, outputStream);

        ByteArrayInputStream fakeConsoleIn = new ByteArrayInputStream("hello, world!".getBytes());
        ByteArrayOutputStream fakeConsoleOut = new ByteArrayOutputStream();
        ConsoleIO consoleIO = new ConsoleIO(fakeConsoleIn, fakeConsoleOut);

        new ReceivedMessagesListener(fakeClientSocket, consoleIO).run();

        assertEquals("hello, world!", fakeConsoleOut.toString().trim());
    }
}