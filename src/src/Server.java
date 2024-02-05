import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {



    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        int i = 1;
        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("connected . . ."+i);
            i++;
            SocketHandler sh = new SocketHandler(socket);
            new Thread(sh).start();

        }























    }
}
