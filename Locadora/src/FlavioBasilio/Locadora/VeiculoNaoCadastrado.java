package FlavioBasilio.Locadora;

public class VeiculoNaoCadastrado extends Exception {
	public VeiculoNaoCadastrado(String placa){
        super("Veiculo não encontrado. Placa: "+ placa + ".");
    }
}
