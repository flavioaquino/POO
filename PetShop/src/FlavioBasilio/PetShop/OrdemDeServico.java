package PauloAugustoMartins.PetShop;

import java.util.Date;

public class OrdemDeServico {
    TipoDeServico serv;
    Animal anml;
    Date data;
    int q;
    
    public OrdemDeServico(TipoDeServico servico, Animal animal, Date dataRegistro, int quantidade) {
        serv = servico;
        anml = animal;
        data = dataRegistro;
        q = quantidade;
    }

    public TipoDeServico getServico() {
        return serv;
    }

    public Animal getAnimal() {
        return anml;
    }

    public Date getDataRegistro(){
        return data;
    } 

    public int getQuantidade(){
        return q;
    }
}
