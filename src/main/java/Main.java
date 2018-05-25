import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java EchoServer server <port number> or java EchoServer client <port number> <host name>");
            System.exit(1);
        }

        String mode = args[0];
        int portNumber = Integer.parseInt(args[1]);

        if (mode.equals("server")) {
            if (args.length != 2) {
                System.err.println("Usage: java EchoServer server <port number>");
                System.exit(1);
            }

            try {
                ServerSocket serverSocket = new ServerSocket(portNumber);
                new EchoServer(serverSocket).start();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        if (mode.equals("client")) {
            if (args.length != 3) {
                System.err.println("Usage: java EchoServer client <port number> <host name>");
                System.exit(1);
            }

            String hostName = args[2];

            try {
                ConsoleIO consoleIO = new ConsoleIO(System.in, System.out);
                Socket echoSocket = new Socket(hostName, portNumber);
                new EchoClient(consoleIO, echoSocket).start();
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
