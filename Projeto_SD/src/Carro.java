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
        out.write(Byte.parseByte(String.valueOf(matricula.length())));
        out.write(Byte.parseByte(matricula));
        out.write(Byte.parseByte(String.valueOf(lugares)));
        int size = this.lugares;
        out.write(size);
        out.flush();
    }

    public static Carro deserialize (DataInputStream in) throws IOException{
        String matricula = String.valueOf(in.readByte());
        int lugares = in.readByte();
        String condutor = String.valueOf(in.readByte());
        int passageiros = in.readByte();
        int size = in.readByte();
        
        return new Carro(matricula, lugares, condutor, passageiros);
    }




}