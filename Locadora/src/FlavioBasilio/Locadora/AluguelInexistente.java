package FlavioBasilio.Locadora;

public class AluguelInexistente extends Exception {
	public AluguelInexistente(String placa){
        super("Aluguel não encontrado. Placa: "+ placa + ".");
    }
}
