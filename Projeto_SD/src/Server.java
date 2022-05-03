import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Server {


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);
        PollManager manager = new PollManager();
        /*Para facilitar as conexões o server vai ter as mensagens que envia para o cliente com tags
         * TAGS->
         * 1 -> Autenticacão Válida Cliente
         * 2 -> Autenticação Válida Admin
         * 3 -> Autenticação Inválida
         * 4 -> Informações (Para alugar uma reserva mostra por exemplo
         * 5 -> Resultados de uma ação (Ex: Reserva criada com sucesso / Lugar na reserva reservado , etc...)
         * 6 -> Utilizador Saiu
         * 9 -> Encerrar uma reserva para x data */
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

    public HashMap<Integer,Reserva> getReservas(){
        return this.Reservas;
    }
    public HashMap<String,Carro> getCarros(){
        return this.Carros;
    }
    public HashMap<Integer,Viagem> getViagens(){
        return this.Viagens;
    }

    public void updateReservations(Reserva reservation){
        l1.lock();
        try {
            boolean sucess = false;
            for(int i = 0; !sucess; i++) {


                if (Reservas.containsKey(i)) { //Temos de ver se a matrícula do Carro já existe basicamente um getMatricula
                    System.out.println("A reserva já existe no sistema");
                    sucess = true;
                } else {
                    Reservas.put(i, reservation);
                    System.out.println("Reserva adicionada com sucesso");
                }
            } }finally {
                l1.unlock();
            }
        }


    public void addReservation(Reserva reservation){
        l1.lock();
        try{
            System.out.println("HELLO");
            boolean sucess = false;
            for(int i = 0; !sucess; i++) {
                System.out.println(i);
                if (Reservas.containsKey(i)) { //Temos de ver se a matrícula do Carro já existe basicamente um getMatricula
                    System.out.println("A reserva já existe no sistema");
                } else {
                    System.out.println("A reserva foi criada com sucesso");
                    Reservas.put(i, reservation);
                    sucess = true;
                }
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
            }
        }finally{
            l1.unlock();
        }
    }

    public void AddTrip(Viagem Trip) {
        l1.lock();
        boolean sucess = false;
        try {
            for (int i = 0; !sucess; i++) {

                if (Viagens.containsKey(i)) { //Temos de ver se a matrícula do Carro já existe basicamente um getMatricula
                    System.out.println("A MATRICULA JÁ EXISTE NO SISTEMA");
                } else {
                    Viagens.put(i, Trip);
                    sucess = true;
                    System.out.println("Carro adicionado com sucesso");
                }
            }}finally{
                l1.unlock();
            }
    }

    public void updateTrip(Viagem Trip){

        l1.lock();
        try {
            boolean sucess = false;
        for(int i = 0; !sucess; i++) {

                if (Viagens.containsKey(i)){ //Temos de ver se a matrícula do Carro já existe basicamente um getMatricula
                    System.out.println("Informações atualizadas");
                    Viagens.put(i, Trip);
                } else {
                    System.out.println("Matrícula não existente, adicione o carro");
                    //Carros.put(car.getMatricula(),car);
                }
            }} finally {
                l1.unlock();
            }
        }
    }


class ClientHandler implements Runnable{

    private Operations_Logic operações = new Operations_Logic();
    private Boolean end_connenction = false;
    private Socket socket;
    private PollManager pollManager;
    private boolean authenticated = false;
    private String user;


    public ClientHandler(Socket newSocket, PollManager newPollManager){
        this.socket = newSocket;
        this.pollManager = newPollManager;
    }

    @Override
    public void run() {
        try {
            DataInputStream inclient = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream outclient = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            while(!end_connenction){
                if(!authenticated){
                    System.out.println("AWAITING");
                    user = inclient.readUTF();
                    String password = inclient.readUTF();
                    System.out.println(user);
                    System.out.println(password);
                    if(password.equals("0000")){
                        System.out.println(password);
                        authenticated = true;
                        outclient.writeInt(1);
                        outclient.flush();
                    }else if(password.equals("1111")){
                        System.out.println(password);
                        authenticated = true;
                        outclient.writeInt(2);
                        outclient.flush();
                    }
                }
                if(authenticated){
                    System.out.println("AUTENTICADO");
                    String menuoption = inclient.readUTF();
                    System.out.println(menuoption);
                    switch(menuoption){
                        case "EXIT":
                            outclient.writeInt(6);
                            outclient.flush();
                            end_connenction = true;
                            authenticated = false;
                            break;
                        case "1":
                            String reservation_data;
                            Reserva new_reservation = null;
                            reservation_data = inclient.readUTF();
                            new_reservation = operações.addReserva(reservation_data, pollManager.getCarros(),pollManager.getReservas(),user);
                            if(new_reservation != null) {
                                if(new_reservation.getDATA_FIM() == null || new_reservation.getMatricula() == null){
                                    outclient.writeInt(8);
                                    outclient.flush();
                                    }else{
                                        System.out.println("HELLO3");
                                        pollManager.addReservation(new_reservation);
                                        outclient.writeInt(8);
                                        outclient.flush();
                                    }
                                }
                                else {
                                    System.out.println("NULL");
                                    outclient.writeInt(7);
                                    outclient.flush();
                                    String car_data = inclient.readUTF();
                                    System.out.println(car_data);
                                    Carro novo_carro = operações.addCar(car_data);
                                if(novo_carro != null) {
                                    pollManager.AddCars(novo_carro);
                                    outclient.writeInt(8);
                                    outclient.flush();
                                }else{
                                    outclient.writeInt(6);
                                    outclient.flush();
                                }
                            }
                            authenticated = false;
                            break;
                        case "2":
                            outclient.writeInt(4);
                            outclient.flush();
                        default:
                            authenticated = false;
                            break;
                    }
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}