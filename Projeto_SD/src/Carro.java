import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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

        public Carro (String matricula, int lugares, String condutor, int passageiros){
            this.matricula = matricula;
            this.lugares = lugares;
            this.condutor = condutor;
            this.passageiros = passageiros;
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
            String new_matricula = in.readUTF();
            int new_lugares = in.readInt();
            String new_condutor = in.readUTF();
            int new_passageiros = in.readInt();
            int size = in.readInt();

            return new Carro(new_matricula, new_lugares, new_condutor, new_passageiros);
        }
}
