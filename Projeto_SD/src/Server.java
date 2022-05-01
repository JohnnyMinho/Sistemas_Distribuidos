import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Server {


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);
        PollManager manager = new PollManager();

        while(true){
            Socket socket = serverSocket.accept();
            Thread worker = new Thread(new ClientHandler(socket,manager));
            worker.start();
        }
    }

}

class PollManager{


    private HashMap<Integer,Reserva> Reservas;
    private HashMap<String,Carro> Carros;
    private HashMap<Integer, Viagem> Viagens;
    ReentrantLock l1 = new ReentrantLock();

    public PollManager(){
        this.Reservas = new HashMap<>();
        this.Carros = new HashMap<>();
        this.Viagens = new HashMap<>();
    }

    public void updateReservations(Reserva reservation){
        boolean sucess = false;
        l1.lock();
        for(int i = 0; sucess; i++) {
            try {
                if (Reservas.containsKey(i)) { //Temos de ver se a matrícula do Carro já existe basicamente um getMatricula
                    System.out.println("A reserva já existe no sistema");
                } else {
                    Reservas.put(i, reservation);
                    System.out.println("Reserva adicionada com sucesso");
                }
            } finally {
                l1.unlock();
            }
        }
    }

    public void addReservation(Reserva reservation){
        boolean sucess = false;
        l1.lock();
        try{
            for(int i = 0; sucess; i++)
            if(Reservas.containsKey(i)) { //Temos de ver se a matrícula do Carro já existe basicamente um getMatricula
                System.out.println("A reserva já existe no sistema");
            }else{
                System.out.println("A reserva foi criada com sucesso");
                Reservas.put(i,reservation);
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
    public void AddTrip(Viagem Trip){
        boolean sucess = false;
        l1.lock();
        for(int i = 0; sucess; i++){
        try{
            if(Viagens.containsKey(i)){ //Temos de ver se a matrícula do Carro já existe basicamente um getMatricula
                System.out.println("A MATRICULA JÁ EXISTE NO SISTEMA");
            }else{
                Viagens.put(i,Trip);
                sucess = true;
                System.out.println("Carro adicionado com sucesso");
            }
        }finally{
            l1.unlock();
        }
        }
    }

    public void updateTrip(Viagem Trip){
        boolean sucess = false;
        l1.lock();
        for(int i = 0; sucess; i++) {
            try {
                if (Viagens.containsKey(i)){ //Temos de ver se a matrícula do Carro já existe basicamente um getMatricula
                    System.out.println("Informações atualizadas");
                    Viagens.put(i, Trip);
                } else {
                    System.out.println("Matrícula não existente, adicione o carro");
                    //Carros.put(car.getMatricula(),car);
                }
            } finally {
                l1.unlock();
            }
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