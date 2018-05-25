import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Socket;

import static org.junit.Assert.*;

public class EchoClientTest {
    @Test
    public void sentMessageIsEchoedBack() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("hello, world!".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Socket fakeClientSocket = new FakeClientSocket(inputStream, outputStream);

        ByteArrayInputStream fakeConsoleIn = new ByteArrayInputStream("hello, world!".getBytes());
        ByteArrayOutputStream fakeConsoleOut = new ByteArrayOutputStream();
        ConsoleIO consoleIO = new ConsoleIO(fakeConsoleIn, fakeConsoleOut);

        EchoClient echoClient = new EchoClient(consoleIO, fakeClientSocket);

        echoClient.start();

        assertEquals("echo: hello, world!", fakeConsoleOut.toString().trim());
        assertEquals("hello, world!", outputStream.toString().trim());
    }
}