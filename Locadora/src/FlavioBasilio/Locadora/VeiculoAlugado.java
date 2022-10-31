package FlavioBasilio.Locadora;

public class VeiculoAlugado extends Exception {
    public VeiculoAlugado(String placa){
        super("Veiculo Já alugado. Placa: " + placa + ".");
    }
}
