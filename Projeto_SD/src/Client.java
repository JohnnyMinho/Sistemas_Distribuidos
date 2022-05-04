import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    static String User = "Catarina";
    static String password = "1111";
    //boolean shutdown = false;

    public Client(){

    }

    public static void askauth(DataOutputStream clientoserver){
        //System.out.println("HELLO");
        try {
            send_auth(clientoserver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void send_auth(DataOutputStream clienttoserver) throws IOException {
        //System.out.println("HELLO2");
        clienttoserver.writeUTF(User);
        clienttoserver.writeUTF(password);
        clienttoserver.flush();
    }

    public static void main (String[] args) throws IOException {

        Client cliente = new Client();
        Socket socket = new Socket("localhost", 9999);
        boolean shutdown = false;

        DataOutputStream servOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        DataInputStream servIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        Menu menu = new Menu(servOut);
        Thread menu_thread = new Thread(menu);
        menu_thread.start();

        while(!shutdown){
                switch (servIn.readInt()) {
                    case 1:
                        menu.type(1);
                        break;
                    case 2:
                        menu.type(2);
                        break;
                    case 3:
                        break;
                    case 4:
                        //System.out.println("Hello");
                        menu.printfromserver(servIn);
                        menu.setChoose_reservation(false);
                        break;
                    case 5:
                        break;
                    case 6:
                        shutdown = true;
                        break;
                    case 7:
                        //String missing = servIn.readUTF();
                        menu.datamissing();
                        break;
                    case 8:
                        System.out.println("debug");
                        menu.setSend_new_command(true);
                        break;
                    case 10:
                        menu.printerror_reserva();
                        menu.setChoose_trip(false);
                        break;
                    case 11:
                        menu.tripgoers(servIn);
                    default:
                        break;
                }
        }
        System.out.println("DIED");
        socket.close();
        System.exit(0);
    }

}

class Menu implements Runnable{

    DataOutputStream menutoserver;

    Scanner input = new Scanner(System.in);
    volatile boolean send_new_command = true;
    boolean exit = false;
    boolean choose_trip = false;
    boolean choose_reservation = false;
    int tipo = 0;

    public Menu(DataOutputStream outfrommenu){
        this.menutoserver = outfrommenu;
    }

    public void setSend_new_command(boolean yesorno){
        System.out.println("ENTREI");
        this.send_new_command = yesorno;
        System.out.println(this.send_new_command);
    }

    public void printerror_reserva(){
        System.out.println("ACONTECEU UM ERRO NA RESERVA");
    }

    public boolean getStatus(){
        //System.out.println(this.send_new_command);
        return this.send_new_command;
    }

    public void type(int typereceived){
        //System.out.println("Tipo:");
        //System.out.println(typereceived);
        this.tipo = typereceived;
    }

    public void printfromserver(DataInputStream in){
        try {
            int n = 0;
            boolean fim = false;
            while(!fim) {
                System.out.println("RESERVA NUMERO: " + n);
                System.out.println("CARRO: " + in.readUTF());
                System.out.println("CONDUTOR: " + in.readUTF());
                int max = in.readInt();
                for (int i = 0; i < max-1; i++) {
                    System.out.println("PASSAGEIRO: " + in.readUTF());
                }
                System.out.println("DATA LIMITE: " + in.readUTF());
                System.out.println("VIAGEM: " + in.readUTF());
                System.out.println("Encerrada a novos passageiros: " + in.readUTF());
                if(in.readUTF().equals("FIM")){
                    //System.out.println("FIM");
                    this.choose_reservation = true;
                    fim = true;
                }
                n++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void tripgoers(DataInputStream in){
        try {
            int n = 0;
            boolean fim = false;
            boolean stop = false;
            while(!fim) {
                System.out.println("Viagem TIPO: " + in.readUTF());
                System.out.println("No Carro: " + in.readUTF());
                System.out.println("Na Data: " + in.readUTF());
                    for (int i = 0; !stop; i++) {
                        String what = in.readUTF();
                        if(what.equals("NO")){
                            stop = true;
                        }else {
                            System.out.println("O PASSAGEIRO: " + what);
                        }
                    }
                if(in.readUTF().equals("FIM")){
                    //System.out.println("FIM");
                    this.choose_reservation = true;
                    fim = true;
                }
                n++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setChoose_reservation(boolean h){
        choose_reservation = h;
    }

    public boolean getChoose_Reservation(){
        return this.choose_reservation;
    }

    public void setChoose_trip(boolean h){
        choose_trip = h;
    }

    public boolean getChoose_trip(){
        return this.choose_trip;
    }

    public int getTipo(){
        return this.tipo;
    }

    public void datamissing() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("NOVO CARRO, ENVIE: MATRICULA,NUMERO DE LUGARES");
       /* byte[] whatsmissing_byte;
        whatsmissing_byte = whatsmissing.getBytes(StandardCharsets.UTF_8);
        System.out.println(whatsmissing_byte);*/
        String input_s = "";
        while(input_s == ""){
            input_s=in.readLine();
        }
        System.out.println(input_s);
        //input_s = in.readLine();
        menutoserver.writeUTF(input_s);
        menutoserver.flush();
    }

    @Override
    public void run(){

        while(!exit) {
           //System.out.println(getStatus());
            if(getStatus()){
                //System.out.println("NEW COMMAND");
                setSend_new_command(false);
                Client.askauth(menutoserver);
                while(getTipo() == 0) {
                   System.out.println("À ESPERA DE AUTENTICAÇÃO");
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("******************************************");
            System.out.println("* 1-> Criar uma Reserva                  *");
            System.out.println("* 2-> Reservar um lugar                  *");
            System.out.println("* 3-> Lista de passageiros (data&viatura)*");
            if (getTipo() == 2) {
                System.out.println("* 4->Sair                                *");
                System.out.println("* 5->Encerrar reservas para uma data     *");
                System.out.println("******************************************");
            }if (getTipo() == 1) {
                System.out.println("* 4->Sair                                *");
                System.out.println("******************************************");
            }
            String comando = "";
            try {
                while(comando == "") {
                    comando = in.readLine();
                }
                menutoserver.writeUTF(comando);
                menutoserver.flush();
                //in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (comando) {
                case "1":
                    try {
                        System.out.println("NOVA RESERVA: MATRÍCULA DA VIATURA A USAR, DATA (dd/MM/yyyy), TIPO DE VIAGEM");
                        BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
                        String input_s = "";
                        System.out.println(in2);
                        //in2.readLine();
                        while (input_s == "") {
                            input_s = in2.readLine();
                        }
                        System.out.println(input_s);
                        //System.out.println("HELLO");
                        menutoserver.writeUTF(input_s);
                        menutoserver.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    while(getChoose_Reservation()){
                        //

                        System.out.print("");
                    }
                    setChoose_reservation(true);
                    try {
                    int send = -2;
                    System.out.println("SE NAO QUISER UM LUGAR EM NENHUMA DAS RESERVADAS DIGITE -1");
                    while (send == -2) {
                        //System.out.println("waiting");
                        send = Integer.parseInt(in.readLine());
                        System.out.println(send);
                    }
                        menutoserver.writeInt(send);
                        menutoserver.flush();
                    } catch (IOException e) {
                    e.printStackTrace();
                    }
                    break;
                case "3":
                    try{
                        BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
                        String input_s = "";
                        System.out.println(in2);
                        //in2.readLine();
                        while (input_s == "") {
                            input_s = in2.readLine();
                        }
                        System.out.println(input_s);
                        menutoserver.writeUTF(input_s);
                        menutoserver.flush();
                        int send = -2;
                        /*while(getChoose_trip()){
                            System.out.println("CAN'T CHOOSE");
                        }
                        setChoose_trip(true);*/
                        /*System.out.println("SE NAO QUISER VER NENHUMA INFORMAÇÃO DIGITE -1");
                        while (send == -2) {
                            //System.out.println("waiting");
                            send = Integer.parseInt(in.readLine());
                            System.out.println(send);
                        }
                        menutoserver.writeInt(send);
                        menutoserver.flush();*/
                    } catch (IOException e){
                            e.printStackTrace();
                        }
                    break;
                case "4":
                        exit = true;
                    break;
                case "5":
                    try {
                        BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
                        String input_s = "A";
                        while (input_s == "A") {
                            input_s = in2.readLine();
                            System.out.println(input_s);

                        }
                        menutoserver.writeUTF(input_s);
                        menutoserver.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Opção desconhecida, selecione uma opção do menu");
                    break;
            }
            type(0);
        }
        }
        System.out.println("Cliente encerrou");
    }
}
