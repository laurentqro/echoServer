import java.io.*;

public class ConsoleIO {
    private BufferedReader inputStream;
    private PrintStream printStream;

    ConsoleIO(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new BufferedReader(new InputStreamReader(inputStream));
        this.printStream = new PrintStream(outputStream);
    }

    public String readLine() throws IOException {
        return inputStream.readLine();
    }

    public void println(String message) {
        printStream.println(message);
    }
}
