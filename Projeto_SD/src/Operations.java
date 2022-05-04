import java.io.DataOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public interface Operations {

    public Reserva addReserva(String clientInput, HashMap<String,Carro> carros, HashMap<Integer,Reserva> Reservas, String user, ArrayList<Viagem> viagens) throws ParseException;

    public Carro addCar(String clientInput,String User);

    public Reserva ocuparaluguer(String User, HashMap<Integer,Reserva> Reservas,int escolha) ;

    public boolean  availablereservations(HashMap<Integer,Reserva> reservas, DataOutputStream out);

    public void FecharReserva(String Data, HashMap<Integer,Reserva> Reservas);
 //Operações Lógicas
}
