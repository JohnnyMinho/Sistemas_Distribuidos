import java.io.DataOutputStream;
import java.text.ParseException;
import java.util.HashMap;

public interface Operations {

    public Reserva addReserva(String clientInput, HashMap<String,Carro> carros,HashMap<Integer,Reserva> Reservas,String user) throws ParseException;

    public Carro addCar(String clientInput);

    public void ocuparaluguer(String User,Reserva reserva);

    public void  availablereservations(HashMap<Integer,Reserva> reservas, DataOutputStream out);

    public void FecharReserva(Reserva reserva);
 //Operações Lógicas
}
