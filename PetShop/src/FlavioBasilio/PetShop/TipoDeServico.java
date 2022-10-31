package PauloAugustoMartins.PetShop;

public class TipoDeServico {
    int codigo;
    String dsc;
    double valor;
    Boolean dispA;
    
    public TipoDeServico(int cod, String desc, double val, boolean adult) {
        codigo = cod;
        dsc = desc;
        valor = val;
        dispA = adult;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDsc() {
        return dsc;
    }

    public double getValor() {
        return valor;
    }

    public boolean getAdult() {
        return dispA;
    }
}
