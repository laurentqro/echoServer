import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class EchoServerTest {
    private ByteArrayInputStream inputStream = new ByteArrayInputStream("hello, world!".getBytes());
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    public void echoes() throws IOException {
        FakeClientSocket fakeClientSocket = new FakeClientSocket(inputStream, outputStream);
        FakeServerSocket fakeServerSocket = new FakeServerSocket(fakeClientSocket);
        EchoServer echoServer = new EchoServer(fakeServerSocket);
        EchoServerThread echoServerThread = new EchoServerThread(echoServer, fakeClientSocket);

        echoServerThread.run();

        assertEquals("hello, world!", outputStream.toString().trim());
    }
}