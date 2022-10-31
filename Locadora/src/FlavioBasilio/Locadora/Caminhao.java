package FlavioBasilio.Locadora;

public class Caminhao extends Veiculo{
    int carga;

    public Caminhao(String mar, String mode, int ano_fab, double v_b, double v_d, String pla, int i) {
        super(mar, mode, ano_fab, v_b, v_d, pla, i);
        carga = i;
    }

    public int getTipo(){
        return carga;
    }

    public int getType(){
        return 3;
    }

    public double getPrice(){
        double preco = v_dia + (((v_bem*8)/100)/365);
        return preco;
    }
}
