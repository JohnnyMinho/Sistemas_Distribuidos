import java.io.DataOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

    public Reserva addReserva(String clientInput, HashMap<String, Carro> carros, HashMap<Integer, Reserva> Reservas, String user,ArrayList<Viagem> viagens) throws ParseException {
        String[] tokens = clientInput.split(",");
        int i;
        if (carros.containsKey(tokens[0])) { //Se a matrícula do carro não existir exigimos que este carro seja adicionado ao hashmap de veiculos disponiveis
            if (carros.get(tokens[0]).getCondutor().equals(user)) { //Se o utilizador não
                if(!viagens.contains(new Viagem(tokens[2]))){ //Se ainda não existe este tipo de viagem, adicionamos ao arraylist de viagens
                    viagens.add(new Viagem(tokens[2]));
                }
                for (i = 0; i < Reservas.size(); i++) {
                    if (Reservas.get(i).getMatricula().equals(tokens[0])) {
                        if (Reservas.get(i).getDATA_FIM().compareTo(new SimpleDateFormat("dd/MM/yyyy").parse(tokens[1])) == 0) {
                            System.out.println("A RESERVA JÁ ESTÁ MARCADA PARA O MESMO DIA");
                            return new Reserva(tokens[0], carros.get(tokens[0]).getCondutor(), carros.get(tokens[0]).getLugares(), 0, null, tokens[2]);
                        }
                    }
                }
                return new Reserva(tokens[0], carros.get(tokens[0]).getCondutor(), carros.get(tokens[0]).getLugares(), 0, tokens[1],tokens[2]); //O utilizador vai ter de enviar uma matrícula, O seu nome de utilizador e a data limite para entrarem pessoas
            } else {
                System.out.println("NÃO É O DONO DA VIATURA");
                return new Reserva(null, carros.get(tokens[0]).getCondutor(), carros.get(tokens[0]).getLugares(), 0, tokens[1],tokens[2]);
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

    public Carro addCar(String clientInput,String User) {
        String[] tokens = clientInput.split(",");
        try {
            return new Carro(tokens[0], Integer.parseInt(tokens[1]),User);
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

    public Reserva ocuparaluguer(String User, HashMap<Integer,Reserva> Reservas,int escolha) {
        //carros com lugares disponiveis
        //lugareslivres= this.lugares - this.condutor - this.passageiros
        //apresentar o numero da reserva associado a esses carros
        //cliente escolhe uma reserva, pelo codigo?, faz sentido se um amigo disser q vai na reserva com o codigo tal
        if(escolha <=Reservas.size() && (escolha > -1)) {
            Reserva reserva = Reservas.get(escolha);
            if (!Arrays.asList(reserva.getPassageiros()).contains(User)) {
                if (!reserva.isEncerrada()) {
                    if (!reserva.isFull()) {
                        reserva.setPassageiros(User);
                    } else {
                        System.out.println("CARRO CHEIO");
                        return null;
                    }
                } else {
                    System.out.println("ESTA RESERVA ESTÁ ENCERRADA");
                    return null;
                }
            } else {
                return null;
            }
            return reserva;
        }else{
            System.out.println("ESCOLHA INVÁLIDA");
            return null;
        }
    }

    public boolean availablereservations(HashMap<Integer, Reserva> reservas, DataOutputStream out) {
        try {
            int i = 0;
            boolean sucess = false;
            for (i = 0; i != reservas.size(); i++) {
                sucess = true;
                reservas.get(i).serialize(out);
                if(!(i+1 == reservas.size())) {
                    out.writeUTF("NO");
                    out.flush();
                }
            }
            try {
                out.writeUTF("FIM");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sucess;
        } catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }


    public void FecharReserva(String Data, HashMap<Integer,Reserva> Reservas) {
        //colocar lugares disponiveis a 0?
        //se acrescentar essa variavel fica bem mais facil
        try {
        for(int i = 0; i != Reservas.size();i++){
                if(Reservas.get(i).getDATA_FIM().compareTo( new SimpleDateFormat("dd/MM/yyyy").parse(Data)) ==0){
                    Reservas.get(i).setEncerrada(true);
                }
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
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