import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    static String User = "Cliente";
    static String password = "0000";
    //boolean shutdown = false;

    public Client(){

    }

    public static void askauth(DataOutputStream clientoserver){
        System.out.println("HELLO");
        try {
            send_auth(clientoserver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void send_auth(DataOutputStream clienttoserver) throws IOException {
        System.out.println("HELLO2");
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
                    default:
                        break;
                }
        }
        System.out.println("DIED");
        socket.shutdownInput();

        socket.close();
    }

}

class Menu implements Runnable{

    DataOutputStream menutoserver;
    Scanner input = new Scanner(System.in);
    volatile boolean send_new_command = true;
    boolean exit = false;
    int tipo = 0;

    public Menu(DataOutputStream outfrommenu){
        this.menutoserver = outfrommenu;
    }

    public void setSend_new_command(boolean yesorno){
        System.out.println("ENTREI");
        this.send_new_command = yesorno;
        System.out.println(this.send_new_command);
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

    public int getTipo(){
        return this.tipo;
    }

    public void datamissing() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(in);
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
           // System.out.println(getStatus());
            if(getStatus()){
                System.out.println("NEW COMMAND");
                setSend_new_command(false);
                Client.askauth(menutoserver);
                while(getTipo() == 0) {
                    System.out.println("DEBUG STAGE 0");
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("******************************************");
            System.out.println("* 1-> Criar uma Reserva                  *");
            System.out.println("* 2-> Reservar um lugar                  *");
            System.out.println("* 3-> Lista de passageiros (data&viatura)*");
            if (getTipo() == 2) {
                System.out.println("* 4->Encerrar reservas para uma data     *");
                System.out.println("* 5->Sair                                *");
                System.out.println("******************************************");
            } else if (getTipo() == 1) {
                System.out.println("* 4->Sair                                *");
                System.out.println("******************************************");
            }
            String comando = " ";
            try {
                comando = in.readLine();
                menutoserver.writeUTF(comando);
                menutoserver.flush();
                //in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (comando) {
                case "1":
                    try {
                        BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
                        String input_s = "";
                        System.out.println(in2);
                        //in2.readLine();
                        while (input_s == "") {
                            input_s = in2.readLine();
                        }
                        System.out.println(input_s);
                        System.out.println("HELLO");
                        menutoserver.writeUTF(input_s);
                        menutoserver.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    if (getTipo() == 1) {
                        exit = true;
                        try {
                            menutoserver.writeUTF("EXIT");
                            menutoserver.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Opção desconhecida, selecione uma opção do menu");
                    break;
            }
            type(0);
        }
        }
        System.out.println("lol1");
    }
}
