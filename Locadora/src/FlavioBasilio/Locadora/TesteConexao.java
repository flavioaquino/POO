package FlavioBasilio.Locadora;

import org.junit.Test;

import static org.junit.Assert.*;

public class TesteConexao {
    @Test
	public void testeConectar() throws Exception {
        
        DAOVeiculos locadora = new DAOVeiculos();
        locadora.removerTudo();
		Veiculo carro1 = new Carro("Ford", "F-1000", 1980, 10000, 50, "LVF-1002", 3);
        locadora.inserir(carro1);

		Veiculo recuperado = locadora.pesquisar("LVF-1002");
		assertEquals("Ford", recuperado.getMarca());
		assertEquals("F-1000", recuperado.getModelo());
		assertEquals(1980, recuperado.getAnoDeFabricacao());
		assertEquals(10000, recuperado.getValorAvaliado(), 0.0001);
		assertEquals(50, recuperado.getValorDiaria(), 0.001);
		assertEquals(3, recuperado.getTipo());

		Veiculo moto1 = new Moto("yamaha", "titan", 2012, 10000, 50, "LVF-1001", 3);
		locadora.inserir(moto1);
		Veiculo recuperado2 = locadora.pesquisar("LVF-1001");
		assertEquals("yamaha", recuperado2.getMarca());
		assertEquals("titan", recuperado2.getModelo());
		assertEquals(2012, recuperado2.getAnoDeFabricacao());
		assertEquals(10000, recuperado2.getValorAvaliado(), 0.0001);
		assertEquals(50, recuperado2.getValorDiaria(), 0.001);
		assertEquals(3, recuperado2.getTipo());

		locadora.removerVeiculo("LVF-1001");
    }

	@Test
	public void testeAlugueis() throws Exception {
		DAOVeiculos locadora = new DAOVeiculos();
        locadora.removerTudo();
		Veiculo carro1 = new Carro("Ford", "F-1000", 1980, 10000, 50, "LVF-1000", 3);
        locadora.inserir(carro1);
		
		DAOClientes cliente1 = new DAOClientes();
        cliente1.removerTudo();
		Cliente cliente = new Cliente(123, "Joao");
		cliente1.cadastrarCliente(cliente);
		
		DAOAluguel aluguel1 = new DAOAluguel();
        aluguel1.removerTudo();
		aluguel1.registrarAluguel(carro1, cliente, 4, carro1.getValorDiaria());

		Veiculo recuperado = locadora.pesquisar("LVF-1000");
		assertEquals("Ford", recuperado.getMarca());
		assertEquals("F-1000", recuperado.getModelo());
		assertEquals(1980, recuperado.getAnoDeFabricacao());
		assertEquals(10000, recuperado.getValorAvaliado(), 0.0001);
		assertEquals(50, recuperado.getValorDiaria(), 0.001);
		assertEquals(3, recuperado.getTipo());

		Aluguel recup = aluguel1.pesquisarCpf(123);
		assertEquals("LVF-1000", recup.getPlaca());
		assertEquals(123, recup.getCpf());
		assertEquals(4, recup.getDias());
		assertEquals("2022-10-04", recup.getData().toString());
	}
}
