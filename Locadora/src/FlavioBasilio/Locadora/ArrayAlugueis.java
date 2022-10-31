package FlavioBasilio.Locadora;

import java.util.ArrayList;

public class ArrayAlugueis{

    static ArrayList<Aluguel> array = new ArrayList<Aluguel>();

    public boolean adicionar(Aluguel a) {
		Aluguel outra = pesquisarAluguel(a.getCpf(), a.getPlaca());
		if (outra != null) {
			return false;
		} else {
			array.add(a);
		  	return true;
		}
	}

    public Aluguel pesquisarAluguel(int cpf, String placa){
		for (Aluguel a: array) {
			if (a.getCpf() == cpf) {
				for(Aluguel b: array){
					if(b.getPlaca() == placa){
						return b;
					}
				}
			}
		}
		return null;
	}
}
