package FlavioBasilio.Locadora;

import java.util.Date;

public class Aluguel {
    int cpf, dias;
    double preco;
    String placa;
    Date data;
    int tipo;

    ArrayAlugueis arrayAluguel = new ArrayAlugueis();

    public Aluguel(int i, double p, String string, Date d, int di, int tip) {
        cpf = i;
        placa = string;
        data = d;
        preco = p;
        dias = di;
        tipo = tip;
    }
    
    public int getCpf(){
        return cpf;
    }

    public String getPlaca(){
        return placa;
    }

    public Date getData(){
        return data;
    }

    public double getPreco(){
        return preco;
    }

    public int getDias(){
        return dias;
    }

    public int getTipo(){
        return tipo;
    }
}
