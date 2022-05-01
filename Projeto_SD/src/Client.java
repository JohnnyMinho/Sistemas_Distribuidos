import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    String User = "Cliente";
    int password = 0000;
    boolean shutdown = false;

    public Client(){

    }
    public boolean isShutdown() {
        return shutdown;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    public static void main (String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);

        DataOutputStream servOut = new DataOutputStream(socket.getOutputStream());
        DataInputStream servIn = new DataInputStream(socket.getInputStream());

        while(){
        }

        socket.shutdownInput();

        socket.close();
    }

}

class

