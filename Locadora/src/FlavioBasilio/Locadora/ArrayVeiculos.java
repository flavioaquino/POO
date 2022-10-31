package FlavioBasilio.Locadora;

import java.util.ArrayList;

public class ArrayVeiculos{

    static ArrayList<Veiculo> array = new ArrayList<Veiculo>();

	String placa;

    public boolean adicionar(Veiculo v) {
		Veiculo outra = pesquisar(v.getPlaca());
		placa = v.getPlaca();
		if (outra != null) {
			return false;
		} else {
			array.add(v);
		  	return true;
		}
	}

	public String getArray(){
		return placa;
	}

    public static Veiculo pesquisar(String plc){
		for (Veiculo v: array) {
			if (v.getPlaca() == plc) {
				return v;
			}		
		}
		return null;
	}
}
