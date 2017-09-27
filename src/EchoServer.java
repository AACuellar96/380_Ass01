
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    String address = socket.getInetAddress().getHostAddress();
                    System.out.printf("Client connected: %s%n", address);
                    OutputStream os = socket.getOutputStream();
                    PrintStream out = new PrintStream(os, true, "UTF-8");
                    out.printf("Hi %s, thanks for connecting!%n", address);
                    BufferedReader in = new BufferedReader(new InputStreamReader( socket.getInputStream()));
                    String input;
                    while((input=in.readLine())!=null){
                        if(input.equalsIgnoreCase(("exit")))
                            break;
                    }
                    System.out.printf("Client disconected: %s%n", address);
                    out.close();
                    in.close();
                    socket.close();
                }
            }
        }
    }
}