import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Server {


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);

        while (true) {
            Socket socket = serverSocket.accept();
            Thread worker = new Thread(new ClientHandler());
            worker.start();
        }
    }

}

class PollManager{

    private HashMap<Integer,Reserva> Reservas;
    private HashMap<String,Carro> Carros;
    ReentrantLock l1 = new ReentrantLock();

    public PollManager(){
        this.Reservas = new HashMap<>();
        this.Carros = new HashMap<>();
    }
    public void updateReservations(Reserva reservation){
        l1.lock();
        try{
            if(Carros.containsKey(reservation.getID())) { //Temos de ver se a matrícula do Carro já existe basicamente um getMatricula
                System.out.println("A reserva já existe no sistema");
            }else{
                Carros.put(reservation.getID(),car);
                System.out.println("Reserva adicionada com sucesso");
            }
        }finally{
            l1.unlock();
        }
    }
    public void addReservation(Reserva reservation){
        l1.lock();
        try{
            if(Carros.containsKey(reservation.getID())) { //Temos de ver se a matrícula do Carro já existe basicamente um getMatricula
                System.out.println("A reserva já existe no sistema");
            }else{
                //Carros.put(reservation.getID(),car);
                System.out.println("A reserva não existe no sistema, porfavor crie uma nova");
            }
        }finally{
            l1.unlock();
        }
    }
    public void AddCars(Carro car){
        l1.lock();
        try{
            if(Carros.containsKey(car.getMatricula())) { //Temos de ver se a matrícula do Carro já existe basicamente um getMatricula
                System.out.println("A MATRICULA JÁ EXISTE NO SISTEMA");
            }else{
                Carros.put(car.getMatricula(),car);
                System.out.println("Carro adicionado com sucesso");
            }
        }finally{
            l1.unlock();
        }
    }
    public void updateCars(Carro car){
        l1.lock();
        try{
            if(Carros.containsKey(car.getMatricula())) { //Temos de ver se a matrícula do Carro já existe basicamente um getMatricula
                System.out.println("Informações atualizadas");
                Carros.put(car.getMatricula(),car);
            }else{
                System.out.println("Matrícula não existente, adicione o carro");
                //Carros.put(car.getMatricula(),car);
            }
        }finally{
            l1.unlock();
        }
    }
}

class ClientHandler implements Runnable{

    private Boolean end_connenction = false;
    private Socket socket;
    private PollManager pollManager;

    public ClientHandler(Socket newSocket, PollManager newPollManager){
        this.socket = newSocket;
        this.pollManager = newPollManager;
    }

    @Override
    public void run() {
        while(!end_connenction){

        }
    }
}