import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Viagem {

    public String Tipo;

    public String getTipo(){
        return this.Tipo;
    }

    public void setTipo(String novo_tipo){
        Tipo = novo_tipo;
    }

    public Viagem(String tipo){
        this.Tipo = tipo;
    }

    public void serialize (DataOutputStream out) throws IOException {
        out.writeUTF(this.Tipo);
        out.flush();
    }

    public static Viagem deserialize (DataInputStream in) throws IOException {
        String arrived_tipo = in.readUTF();

        return new Viagem(arrived_tipo);
    }

   /* public String toString () {
        StringBuilder builder = new StringBuilder();
        builder.append(this.Destino).append(";");
        builder.append(this.Origem).append(";");
        builder.append(this.Data).append(";");
        builder.append("}");
        return builder.toString();
    }*/
}
