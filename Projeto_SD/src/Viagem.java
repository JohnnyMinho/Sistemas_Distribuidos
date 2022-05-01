import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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

    public void serialize (DataOutputStream out) throws IOException {
        out.write(Byte.parseByte(String.valueOf(this.getOrigem().length())));
        out.flush();
        out.write(Byte.parseByte(this.getOrigem()));
        out.flush();
        out.write(Byte.parseByte(String.valueOf(this.getDestino().length())));
        out.flush();
        out.write(Byte.parseByte(this.getDestino()));
        out.flush();
        
    }

    // @TODO
    public static Viagem deserialize (DataInputStream in) throws IOException {
        String name =  in.readUTF();
        int age = in.readInt();
        long phoneNumber = in.readLong();
        String company = null;
        if(in.readBoolean()){
            in.readBoolean();
            company =  in.readUTF();
        }
        int size =  in.readInt();;
        ArrayList<String> emails = new ArrayList();
        for(int i = 0; i<size;i++){
            emails.add(in.readUTF());
        }

        return new Contact(name,age,phoneNumber,company,emails);
    }

    public String toString () {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name).append(";");
        builder.append(this.age).append(";");
        builder.append(this.phoneNumber).append(";");
        builder.append(this.company).append(";");
        builder.append(this.emails.toString());
        builder.append("}");
        return builder.toString();
    }
}
