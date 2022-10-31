package FlavioBasilio.Locadora;

public class ClienteJaCadastrado extends Exception {
	public ClienteJaCadastrado(int cpf){
        super("Cliente já cadastrado. CPF: "+ cpf + ".");
    }
}
