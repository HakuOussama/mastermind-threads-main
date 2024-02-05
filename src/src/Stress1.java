import java.io.IOException;

public class Stress1 {

    int connexions ;

    Stress1(int connexions){
        this.connexions=connexions;
    }

    public void run() throws IOException {
        for (int i = 0; i < connexions; i++) {
            new IdleClient().run();
        }



    }
}
