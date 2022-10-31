package FlavioBasilio.Locadora;

public class ClienteNaoCadastrado extends Exception {
	public ClienteNaoCadastrado(int cpf){
        super("Cliente já cadastrado. CPF: "+ cpf + ".");
    }
}
