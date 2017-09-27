
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br.readLine());
            BufferedReader brIS = new BufferedReader(new InputStreamReader(System.in));
            String input;
            System.out.print("Client>");
            while(!(input= brIS.readLine()).equalsIgnoreCase("exit")){
                System.out.println(input);
                System.out.println("Server>" + br.readLine());
                System.out.print ("Client>");
            }
            System.out.print(input);
            is.close();
            isr.close();
            br.close();
            brIS.close();
            socket.close();
        }
    }
}
