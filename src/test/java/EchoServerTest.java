import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class EchoServerTest {
    private ByteArrayInputStream inputStream = new ByteArrayInputStream("hello, world!".getBytes());
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    public void echoes() {
        FakeClientSocket fakeClientSocket = new FakeClientSocket(inputStream, outputStream);
        EchoServerThread echoServerThread = new EchoServerThread(fakeClientSocket);

        echoServerThread.run();

        assertEquals("hello, world!", outputStream.toString().trim());
    }
}