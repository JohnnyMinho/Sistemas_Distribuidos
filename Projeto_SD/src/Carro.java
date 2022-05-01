import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class Carro {

    //Matricula
    //Lugares
    //Condutor - Talvez
    //Passageiros - Talvez

    //Serialize
    //Desirialize

    private String matricula;
    private int lugares;
    private String condutor;
    private int passageiros;
    private ReentrantLock l;

    public Carro(){
        this.matricula = "AA-00-ED";
        this.lugares = 0;
        this.condutor = "Antonio";
        this.passageiros = 0;
    }

    public Carro (String matricula, int lugares, String condutor, int passageiros){
        this.matricula = matricula;
        this.lugares = lugares;
        this.condutor = condutor;
        this.passageiros = passageiros;
        this.l = new ReentrantLock();
    }

    public String getMatricula() {
        return this.matricula;
    }
    public int getLugares() {
        return this.lugares;
    }
    public String getCondutor(){
        return this.condutor;
    }
    public int getPassageiros() {
        return this.passageiros;
    }

    public void serialize(DataOutputStream out) throws IOException {
        out.writeUTF(matricula);
        out.writeInt(lugares);
        int size = this.lugares;
        out.writeInt(size);
        out.flush();
    }

    public static Carro deserialize (DataInputStream in) throws IOException{
        String matricula = in.readUTF();
        int lugares = in.readInt();
        String condutor = in.readUTF();
        int passageiros = in.readInt();
        int size = in.readInt();

        return new Carro(matricula, lugares, condutor, passageiros);
    }




}