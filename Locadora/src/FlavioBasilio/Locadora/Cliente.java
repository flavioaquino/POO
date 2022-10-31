package FlavioBasilio.Locadora;

public class Cliente {
    int cpf;
    String nome;

    ArrayClientes arrayCliente = new ArrayClientes();

    public Cliente(int i, String string) {
        cpf = i;
        nome = string;
    }
    
    public int getCpf(){
        return cpf;
    }

    public String getNome(){
        return nome;
    }
}
