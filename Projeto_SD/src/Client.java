import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    static String User = "Cliente";
    static String password = "0000";
    //boolean shutdown = false;

    public Client(){

    }
   /* public boolean isShutdown() {
        return shutdown;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }*/

    public static void askauth(DataOutputStream clientoserver){
        try {
            send_auth(clientoserver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void send_auth(DataOutputStream clienttoserver) throws IOException {
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
    boolean exit = false;
    int tipo = 0;

    public Menu(DataOutputStream outfrommenu){
        this.menutoserver = outfrommenu;
    }

    public void type(int typereceived){
        System.out.println(typereceived);
        this.tipo = typereceived;
    }

    public int getTipo(){
        return this.tipo;
    }

    @Override
    public void run(){

        while(!exit){
            Client.askauth(menutoserver);
            while(getTipo() == 0) {
                System.out.println("DEBUG STAGE 1");
            }
            System.out.println("******************************************");
            System.out.println("* 1-> Criar uma Reserva                  *");
            System.out.println("* 2-> Reservar um lugar                  *");
            System.out.println("* 3-> Lista de passageiros (data&viatura)*");
            if(getTipo() == 2){
                System.out.println("* 4->Encerrar reservas para uma data     *");
                System.out.println("* 5->Sair                                *");
                System.out.println("******************************************");
            }else if(getTipo() == 1){
                System.out.println("* 4->Sair                                *");
                System.out.println("******************************************");
            }
            String comando = input.nextLine();
            switch(comando){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    if(getTipo() == 1){
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
        }
        System.out.println("lol1");
    }
}
