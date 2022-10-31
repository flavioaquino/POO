package PauloAugustoMartins.PetShop;

import java.util.Date;

public class Servico {
    Cliente cliente;
    Animal animal;
    Date data, dataF;
    Double valor;
    
    public Servico(Cliente Cpfcliente, Animal idAnimal, Date dataServico, double valorServico) {
        cliente = Cpfcliente;
        animal = idAnimal;
        data = dataServico;
        valor = valorServico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Date getData(){
        return data;
    }

    public double getValor(){
        return valor;
    }
}
