import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Operations_Logic implements Operations{

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

    public Reserva addReserva(String clientInput, HashMap<String,Carro> carros)
    {
        String[] tokens = clientInput.split(",");

        if(carros.containsKey(tokens[0])){
            return new Reserva(tokens[0],carros.get(tokens[0]).getCondutor(),carros.get(tokens[0]).getLugares(),0,tokens[1]); //O utilizador vai ter de enviar uma matrícula, O seu nome de utilizador e a data limite para entrarem pessoas
        }else{
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
    public Carro addCar(String clientInput)
    {
        String[] tokens = clientInput.split(",");
    try {
        return new Carro(tokens[0], Integer.parseInt(tokens[1]), tokens[2], Integer.parseInt(tokens[3]));
    }catch(ArrayIndexOutOfBoundsException e){
        return null;
    }
    }

    public void Lista_Passageiros(){
        boolean sucess =false;
        Scanner numero = new Scanner(System.in);
        for(int i = 0; sucess; i++)
            if(Reservas.containsKey(i)) { //imprime o id das reservas para o utilizador escolher
                System.out.print("id da reserva: ");
                System.out.print(i);
                System.out.print(" com matricula: ");
                System.out.println(Reservas.get(i).Matricula);
            }
        int idmatricula = numero.nextInt();
        System.out.println(Reservas.get(idmatricula).Passageiros);
    }
   /* public void ocuparaluguer(Reserva novareserva, Client getidclient){
        //carros com lugares disponiveis
        //lugareslivres= this.lugares - this.condutor - this.passageiros
        //apresentar o numero da reserva associado a esses carros
        //cliente escolhe uma reserva, pelo codigo?, faz sentido se um amigo disser q vai na reserva com o codigo tal

        this.lista.put(nova.codigoreserva(),nova.clone());//insere nessa reserva

    }

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
    }*/
}
