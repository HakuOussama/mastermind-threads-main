import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SocketHandler implements Runnable{
    static  int i = 1;

    Socket socket ;
    synchronized private String read(InputStream ip) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead = ip.read(buffer);

        if (bytesRead == -1) {
            throw new IOException("End of stream reached");
        }

        return new String(buffer, 0, bytesRead).trim();
    }


    private  void send(OutputStream op, String saisie) throws IOException {
        byte msg[] = saisie.getBytes();
        op.write(msg);
    }
    private  String randomCombi() {
        char[] alphabets = {'B', 'G', 'O', 'R', 'W', 'Y'};
        int length = 4;

        StringBuilder randomString = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(alphabets.length);
            char randomLetter = alphabets[randomIndex];
            randomString.append(randomLetter);
        }

        String result = randomString.toString();
        return result;

    }


     public SocketHandler(Socket socket){
        this.socket=socket ;
    }


    @Override
    public void run() {
        try {

            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            String trueCombi = randomCombi();

            System.out.println(i+"Secret combination: " + trueCombi);
            i++;
            int attempts = 0;
            String msg = "Trouve La combi";
            send(outputStream, msg);
            while (true) {
                attempts++;
                String userGuess = read(inputStream);

                if (userGuess.equals(trueCombi)) {
                    send(outputStream,"Correct");
                    System.out.println("Correct combination found in " + attempts + " attempts.");
                    send(outputStream, "Correct combination found in " + attempts + " attempts.");
                    break;
                }

                int correctPosition = 0;
                int incorrectPosition = 0;

                for (int index = 0; index < 4; index++) {
                    char userGuessCharAtIndex = userGuess.charAt(index);

                    if (userGuessCharAtIndex == trueCombi.charAt(index)) {
                        correctPosition++;
                    } else if (trueCombi.indexOf(userGuessCharAtIndex) != -1) {
                        incorrectPosition++;
                    }
                }
                send(outputStream, correctPosition + "," + incorrectPosition);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
