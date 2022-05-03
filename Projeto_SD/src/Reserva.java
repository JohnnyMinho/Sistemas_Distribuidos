import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {

    public String Matricula;
    public String Condutor;
    int MAX_LUGARES;
    String[] Passageiros;
    Date DATA_FIM;
    boolean full = false;
    boolean encerrada = false;

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

    public String[] getPassageiros() {

        return Passageiros;
    }

    public void setPassageiros(String passageiros){
        for(int i = 1; i<MAX_LUGARES; i++){
            if(Passageiros[i] != null){
                Passageiros[i] = passageiros;
                if(i == MAX_LUGARES-1){
                    setFull(true);
                    setEncerrada(true);
                }
            }
        }
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public boolean isEncerrada() {
        return encerrada;
    }

    public void setEncerrada(boolean encerrada) {
        this.encerrada = encerrada;
    }

    public Date getDATA_FIM() {
        return DATA_FIM;
    }

    public void setDATA_FIM(String DATA_FIM) throws ParseException {
        this.DATA_FIM = new SimpleDateFormat("dd/MM/yyyy").parse(DATA_FIM);
    }

    public Reserva(String matricula_nova, String condutor_novo, int maxlugares_novos, int passageiros_novos, String datafim_nova) throws ParseException {
        this.Matricula = matricula_nova;
        this.Condutor = condutor_novo;
        this.MAX_LUGARES = maxlugares_novos;
        this.Passageiros = new String[maxlugares_novos-1];
        if(datafim_nova != null) {
            this.DATA_FIM = new SimpleDateFormat("dd/MM/yyyy").parse(datafim_nova);
        }else{
            this.DATA_FIM = null;
        }
    }

    public void serialize(DataOutputStream out) throws IOException{
        out.writeUTF(this.Matricula);
        out.writeUTF(this.Condutor);
        out.writeInt(this.MAX_LUGARES);
        for(int i = 0; i<Passageiros.length;i++) {
            out.writeUTF(this.Passageiros[i]);
        }
        out.writeUTF(String.valueOf(this.DATA_FIM));
    }

    public static Reserva deserialize(DataInputStream in) throws IOException, ParseException {
        String mat = in.readUTF();
        String cond = in.readUTF();
        int mlugar = in.readInt();
        int pass = in.readInt();
        String dataf = in.readUTF();
       // Date datafim = new SimpleDateFormat("dd/MM/yyyy").parse(dataf);
        return new Reserva(mat, cond, mlugar, pass, dataf);
    }

}
