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

    public Carro (String m, int l, String c, int p){
        this.matricula = m;
        this.lugares = l;
        this.condutor = c;
        this.passageiros = p;
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

    public void serialize(DataOutputStream) throws IOException{

    }



}