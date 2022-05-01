import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Viagem {

    public String Origem;
    public String Destino;
    public String Data;

    public String getOrigem() {
        return Origem;
    }

    public void setOrigem(String origem) {
        Origem = origem;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String destino) {
        Destino = destino;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public Viagem(String origem_nova, String Destino_nova, String Data_nova){
        this.Origem = origem_nova;
        this.Destino = Destino_nova;
        this.Data = Data_nova;
    }

    public void serialize (DataOutputStream out) throws IOException {
        out.writeUTF(this.Origem);
        out.writeUTF(this.Destino);
        out.writeUTF(this.Data);
        out.flush();
    }

    public static Viagem deserialize (DataInputStream in) throws IOException {
        String arrived_destiny = in.readUTF();
        String arrived_origin = in.readUTF();
        String arrived_data = in.readUTF();
        return new Viagem(arrived_destiny,arrived_origin,arrived_data);
    }

    public String toString () {
        StringBuilder builder = new StringBuilder();
        builder.append(this.Destino).append(";");
        builder.append(this.Origem).append(";");
        builder.append(this.Data).append(";");
        builder.append("}");
        return builder.toString();
    }
}
