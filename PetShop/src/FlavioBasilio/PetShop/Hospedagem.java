package PauloAugustoMartins.PetShop;

import java.util.Date;

public class Hospedagem extends TipoDeServico{
    Date ini;
    int id;
    long cpf;
    int qdias;
    
    public Hospedagem(int cod, String desc, double val, boolean adult, long cpfCliente, int idAnimal, Date dataInicio, int quantidadeDias) {
        super(cod, desc, val, adult);
        adult = true;
        cpf = cpfCliente;
        id = idAnimal;
        ini = dataInicio;
        qdias = quantidadeDias;
    }
    
    public Date getDate(){
        return ini;
    }

    public int getId(){
        return id;
    }

    public long getCpf(){
        return cpf;
    }

    public int getQDias(){
        return qdias;
    }
}
