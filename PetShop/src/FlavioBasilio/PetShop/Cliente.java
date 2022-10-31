package PauloAugustoMartins.PetShop;

public class Cliente {
    long cpf;
    String nome;
    
    public Cliente(long cpfCliente, String nomeCliente) {
        cpf = cpfCliente;
        nome = nomeCliente;
    }

    public long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }
}
