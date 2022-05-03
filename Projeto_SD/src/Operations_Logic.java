import java.io.DataOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Operations_Logic implements Operations {

    //Código Operações Lógica
    //Criar reserva - TODOS
    //Ocupar reserva - TODOS
    //Obter Lista de Passageiros de uma dada reserva - Todos
    //Fechar uma reserva para uma data. - Administrador


    //private HashMap<Integer, Client> lista;

    //ao cria um aluguer ele vai ver um carro disponivel, e ficas com um numero de uma reserva,
    //                                  ==matricula            ==reserva
    //depois de haver um carro ele coloca la um cliente, e dois podem juntar se mais
    // ==idcliente                ==idcliente

    public Reserva addReserva(String clientInput, HashMap<String, Carro> carros, HashMap<Integer, Reserva> Reservas, String user) throws ParseException {
        String[] tokens = clientInput.split(",");
        int i;
        if (carros.containsKey(tokens[0])) {
            if (carros.get(tokens[0]).getCondutor().equals(user)) {
                for (i = 0; i < Reservas.size(); i++) {
                    if (Reservas.get(i).getMatricula().equals(tokens[0])) {
                        if (Reservas.get(i).getDATA_FIM().compareTo(new SimpleDateFormat("dd/MM/yyyy").parse(tokens[1])) == 0) {
                            System.out.println("A RESERVA JÁ ESTÁ MARCADA PARA O MESMO DIA");
                            return new Reserva(tokens[0], carros.get(tokens[0]).getCondutor(), carros.get(tokens[0]).getLugares(), 0, null);
                        }
                    }
                }
                return new Reserva(tokens[0], carros.get(tokens[0]).getCondutor(), carros.get(tokens[0]).getLugares(), 0, tokens[1]); //O utilizador vai ter de enviar uma matrícula, O seu nome de utilizador e a data limite para entrarem pessoas
            } else {
                System.out.println("NÃO É O DONO DA VIATURA");
                return new Reserva(null, carros.get(tokens[0]).getCondutor(), carros.get(tokens[0]).getLugares(), 0, tokens[1]);
            }
        } else {
            return null;
        }
        //foi feito pedido de reserva
        //lista de carros com ninguem --> array<carros> --> getlugaresdisponiveis=5
        //OUUUUUUUU
        //carro tem int reserva-->   array<carros> procura por reserva=null ou valor predefinido
        //primeiro carro q encontrar -->getmatricula


        // Hasmap <codigoreserva, matricula>
        //o carro tem associado 5 gajos

        //this.lista = new HashMap<Integer,Cliente>();
        //this.listacarro = new HashMap<Integer,Carro>();?????

        // this.lista.put(nova.codigoreserva(),nova.clone()); //cliente fica associado a uma reserva

    }

    public Carro addCar(String clientInput) {
        String[] tokens = clientInput.split(",");
        try {
            return new Carro(tokens[0], Integer.parseInt(tokens[1]), tokens[2], Integer.parseInt(tokens[3]));
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

   /* public void Lista_Passageiros(){
        boolean sucess =false;

        for(int i = 0; sucess; i++)
            if(Reservas.containsKey(i)) { //imprime o id das reservas para o utilizador escolher
                System.out.print("id da reserva: ");
                System.out.print(i);
                System.out.print(" com matricula: ");
                System.out.println(Reservas.get(i).Matricula);
            }
        int idmatricula = numero.nextInt();
        System.out.println(Reservas.get(idmatricula).Passageiros);
    }*/

    public void ocuparaluguer(String User, Reserva reserva) {
        //carros com lugares disponiveis
        //lugareslivres= this.lugares - this.condutor - this.passageiros
        //apresentar o numero da reserva associado a esses carros
        //cliente escolhe uma reserva, pelo codigo?, faz sentido se um amigo disser q vai na reserva com o codigo tal
        if (!reserva.isEncerrada()) {
            if (!reserva.isFull()) {
                reserva.setPassageiros(User);
            }
        }
    }

    public void availablereservations(HashMap<Integer, Reserva> reservas, DataOutputStream out) {
        int i = 0;
        for (i = 0; reservas.containsKey(i); i++) {
            try {
                reservas.get(i).serialize(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out.writeUTF("FIM");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FecharReserva(Reserva reserva) {
        //colocar lugares disponiveis a 0?
        //se acrescentar essa variavel fica bem mais facil
        reserva.setEncerrada(true);
    }
}
    /* public void Lista_Passageiros(){
     boolean sucess =false;
     for(int i = 0; sucess; i++)
         if(Reservas.containsKey(i)) { //imprime o id das reservas para o utilizador escolher
             System.out.print("id da reserva: ");
             System.out.print(i);
             System.out.print(" com matricula: ");
             System.out.println(Reservas.get(i).Matricula);
         }
     int idmatricula = numero.nextInt();
     System.out.println(Reservas.get(idmatricula).Passageiros);
 }*/
    /*
    public List<Client> getclientsreserva()
    {
        ArrayList<Client> temp = new ArrayList<Client>();
        for(Integer k:this.lista.keySet())//percorre lista
            if(k==getcodigoreserva)// a procura do numeroreserva
                temp.add(k.value);//se for igual adiciona a um array
        return temp; //retorna array dos clients na reserva
    }


    public void Fecharreserva(getcodigoreserva){
        //colocar lugares disponiveis a 0?
        //se acrescentar essa variavel fica bem mais facil
    }
}
*/