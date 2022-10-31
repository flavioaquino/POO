package FlavioBasilio.Locadora;

public class Moto extends Veiculo{
    int cilin;

    public Moto(String mar, String mode, int ano_fab, double v_bem, double v_dia, String pla, int c){
        super(mar, mode, ano_fab, v_bem, v_dia, pla, c);
        cilin = c;
    }

    public int getTipo(){
        return cilin;
    }

    public int getType(){
        return 1;
    }

    public double getPrice(){
        double preco = v_dia + (((v_bem*11)/100)/365);
        return preco;
    }
}
