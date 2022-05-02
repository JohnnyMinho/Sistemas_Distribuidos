import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Reserva {

    public String Matricula;
    public String Condutor;
    int MAX_LUGARES;
    int Passageiros;
    public String DATA_FIM;


    public String getMatricula() {

        return Matricula;
    }

    public void setMatricula(String matricula) {

        Matricula = matricula;
    }

    public String getCondutor() {

        return Condutor;
    }

    public void setCondutor(String condutor) {

        Condutor = condutor;
    }

    public int getMAX_LUGARES() {

        return MAX_LUGARES;
    }

    public void setMAX_LUGARES(int MAX_LUGARES) {

        this.MAX_LUGARES = MAX_LUGARES;
    }

    public int getPassageiros() {

        return Passageiros;
    }

    public void setPassageiros(int passageiros) {

        Passageiros = passageiros;
    }

    public String getDATA_FIM() {

        return DATA_FIM;
    }

    public void setDATA_FIM(String DATA_FIM) {

        this.DATA_FIM = DATA_FIM;
    }

    public Reserva(String matricula_nova, String condutor_novo, int maxlugares_novos, int passageiros_novos, String datafim_nova){
        this.Matricula = matricula_nova;
        this.Condutor = condutor_novo;
        this.MAX_LUGARES = maxlugares_novos;
        this.Passageiros = passageiros_novos;
        this.DATA_FIM = datafim_nova;
    }

    public void serialize(DataOutputStream out) throws IOException{
        out.writeUTF(this.Matricula);
        out.writeUTF(this.Condutor);
        out.writeInt(this.MAX_LUGARES);
        out.writeInt(this.Passageiros);
        out.writeUTF(this.DATA_FIM);
    }

    public static Reserva deserialize(DataInputStream in) throws IOException{
        String mat = in.readUTF();
        String cond = in.readUTF();
        int mlugar = in.readInt();
        int pass = in.readInt();
        String dataf = in.readUTF();
        return new Reserva(mat, cond, mlugar, pass, dataf);
    }

}
