import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //HashMap Reservas

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);


        while (true) {
            Socket socket = serverSocket.accept();
            Thread worker = new Thread(new ClientHandler());
            worker.start();
        }
    }

}

class ClientHandler implements Runnable{

    Boolean end_connenction = false;


    @Override
    public void run() {
        while(!end_connenction){
            //MENU -> Thread Própria
            /*
                */
            //
        }
    }
}