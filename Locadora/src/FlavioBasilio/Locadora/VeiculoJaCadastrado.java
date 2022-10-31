package FlavioBasilio.Locadora;

public class VeiculoJaCadastrado extends Exception {
    public VeiculoJaCadastrado(String placa){
        super("Veiculo Já cadastrado. Placa: " + placa + ".");
    }
}
