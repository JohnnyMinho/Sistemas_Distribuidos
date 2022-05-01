import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Operations_Logic {

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

    public void add(Reserva novareserva, Client getidclient)
    {
        //foi feito pedido de reserva
        //lista de carros com ninguem --> array<carros> --> getlugaresdisponiveis=5
        //OUUUUUUUU
        //carro tem int reserva-->   array<carros> procura por reserva=null ou valor predefinido
        //primeiro carro q encontrar -->getmatricula

        
        
        
        
        // Hasmap <codigoreserva, matricula>
        //o carro tem associado 5 gajos

        //this.lista = new HashMap<Integer,Cliente>();
        //this.listacarro = new HashMap<Integer,Carro>();?????
        
        
        this.lista.put(nova.codigoreserva(),nova.clone()); //cliente fica associado a uma reserva
         
        






    }

    public void ocuparaluguer(Reserva novareserva, Client getidclient){  

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
    }
}
