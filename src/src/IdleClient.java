import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class IdleClient {

    public static String read(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);
        return new String(buffer, 0, bytesRead).trim();
    }

    private static void send(OutputStream outputStream, String message) throws IOException {
        byte[] msg = message.getBytes();
        outputStream.write(msg);
    }

    private static String getUserInput() {
        System.out.print("Enter your guess...: "+"\n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    public  void run() throws IOException {
        Socket socket = new Socket();
        int port =  1234 ;//Integer.parseInt(args[0]) ;
        InetAddress localhost = InetAddress.getLocalHost();
        socket.connect(new InetSocketAddress(localhost, port));

        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        String response;

        //send(outputStream, "AAAA");
        //socket.close();
    }
}

