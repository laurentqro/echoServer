import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Socket;

public class FakeClientSocket extends Socket {
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;

    FakeClientSocket(ByteArrayInputStream inputStream, ByteArrayOutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    @Override
    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }
}
