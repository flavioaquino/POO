package FlavioBasilio.Locadora;

public class Carro extends Veiculo {
    int cate;

    public Carro(String mar, String mode, int ano_fab, double v_bem, double v_dia, String pla, int i){
        super(mar, mode, ano_fab, v_bem, v_dia, pla, i);
        cate = i;
    }

    public int getTipo(){
        return cate;
    }

    public int getType(){
        return 2;
    }

    public double getPrice(){
        double preco = v_dia + (((v_bem*3)/100)/365);
        return preco;
    }
}
