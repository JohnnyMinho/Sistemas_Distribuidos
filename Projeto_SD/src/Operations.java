import java.io.DataOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public interface Operations {

    public Reserva addReserva(String clientInput, HashMap<String,Carro> carros, HashMap<Integer,Reserva> Reservas, String user, ArrayList<Viagem> viagens,ArrayList<Date> Datas) throws ParseException;

    public Carro addCar(String clientInput,String User);

    public Reserva ocuparaluguer(String User, HashMap<Integer,Reserva> Reservas,int escolha) ;

    public boolean  availablereservations(HashMap<Integer,Reserva> reservas, DataOutputStream out);

    public void FecharReserva(String Data, ArrayList<Date> Datas);

    public void Passageiros_viagem(DataOutputStream out, HashMap<Integer,Reserva> Reservas, String Viagem, ArrayList<Viagem> viagens);
    //Operações Lógicas
}
