package FlavioBasilio.Locadora;

import java.util.ArrayList;

public class ArrayClientes{

    static ArrayList<Cliente> array = new ArrayList<Cliente>();

    public boolean adicionar(Cliente c) {
		Cliente outra = pesquisarCliente(c.getCpf());
		if (outra != null) {
			return false;
		} else {
			array.add(c);
		  	return true;
		}
	}

    public Cliente pesquisarCliente(int cpf){
		for (Cliente c: array) {
			if (c.getCpf() == cpf) {
				return c;
			}		
		}
		return null;
	}
}
