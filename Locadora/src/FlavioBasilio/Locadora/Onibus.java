package FlavioBasilio.Locadora;

public class Onibus extends Veiculo{
    int cap_p;

    public Onibus(String mar, String mode, int ano_fab, double v_bem, double v_dia, String pla, int cp){
        super(mar, mode, ano_fab, v_bem, v_dia, pla, cp);
        cap_p = cp;
    }

    public int getTipo(){
        return cap_p;
    }

    public int getType(){
        return 4;
    }

    public double getPrice(){
        double preco = v_dia + (((v_bem*20)/100)/365);
        return preco;
    }
}
