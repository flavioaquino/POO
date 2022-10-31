package FlavioBasilio.Locadora;

public abstract class Veiculo{
    
    String marca, placa, modelo;
    int ano_f, tipo;
    double v_dia, v_bem;

    public Veiculo(String mar, String mode, int ano_fab, double v_b, double v_d, String pla, int i){
        marca = mar;
        placa = pla;
        modelo = mode;
        ano_f = ano_fab;
        v_dia = v_d;
        v_bem = v_b;
        tipo = i;
    }

    public String getMarca(){
        return marca;
    }

    public String getPlaca(){
        return placa;
    }

    public String getModelo(){
        return modelo;
    }

    public int getAnoDeFabricacao(){
        return ano_f;
    }

    public double getValorDiaria(){
        return v_dia;
    }

    public double getValorAvaliado(){
        return v_bem;
    }

    public abstract int getTipo();

    public abstract int getType();

    public abstract double getPrice();
}