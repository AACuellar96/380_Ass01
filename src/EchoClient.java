
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br.readLine());
            BufferedReader brIS = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            System.out.print("Client>");
            String inp;
            while((inp=brIS.readLine())!=null){
                if(inp.equalsIgnoreCase("exit"))
                    break;
                out.println(inp);
                System.out.println("Server>"+br.readLine());
                System.out.print("Client>");
            }
            is.close();
            isr.close();
            br.close();
            brIS.close();
            socket.close();
        }
    }
}
