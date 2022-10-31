package FlavioBasilio.Locadora;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public abstract class Locadora {

	ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	ArrayList<Aluguel> alugueis = new ArrayList<Aluguel>();

	public abstract void inserir(Veiculo v) throws Exception;
	
	public abstract void inserir(Cliente c)  throws ClienteJaCadastrado, ClienteNaoCadastrado, SQLException;
	
	public abstract Cliente pesquisarCliente(int cpf) throws ClienteNaoCadastrado, SQLException;
	
	// Retorna as motos com cilindrada maior ou igual a pesquisada.
    public abstract ArrayList<Veiculo> pesquisarMoto(int cilindrada) throws SQLException;
	// tipo de carro
	// 1 (passeio), 2 (SUV), 3 (pickup)
    public abstract ArrayList<Veiculo> pesquisarCarro(int tipoCarro) throws SQLException;
	// Retorna os caminhões com capacidade de carga maior ou igual a pesquisada.
    public abstract ArrayList<Veiculo> pesquisarCaminhao(int carga) throws SQLException;
	// Retorna os ônibus com capacidade de passageiros maior ou igual a pesquisada.
    public abstract ArrayList<Veiculo> pesquisarOnibus(int passageiros) throws SQLException;
    
    //Seguro Moto = (valor do bem * 11%)/365
    //Seguro Carro = (valor do bem * 3%)/365
    //Seguro Caminhão = (valor do bem * 8%)/365
    //Seguro Ônibus = (valor do bem * 20%)/365
    //Aluguel = (valor da diária + seguro) * quantidade de dias
    public abstract double calcularAluguel(String placa, int dias) throws VeiculoNaoCadastrado, SQLException, VeiculoJaCadastrado ;
    
    // Retorna falso se veiculo não existir ou se estiver alugado. 
    // Deve registrar que o veículo está alugado.
    public abstract void registrarAluguel(String placa, Date data, int dias, int cpf) throws ClienteNaoCadastrado, VeiculoAlugado, VeiculoNaoCadastrado, VeiculoJaCadastrado, SQLException;
    
    // Retorna falso se veiculo não existir ou se não estiver alugado.  
    // Deve registrar a devolução do veiculo e permitir nova locação. 
    public abstract void registrarDevolucao(String placa, Date data, int cpf) throws VeiculoJaCadastrado, VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado, ClienteJaCadastrado, VeiculoNaoAlugado, SQLException;
    
	// Tipo de veiculo a ser usado:
	// 0 (todos), 1 (moto), 2 (carro), 3 (caminhão), 4 (ônibus)
    // Não pode deixar ter taxa de depreciação negativa. Ela sempre será positiva e será subtraído do valor do veiculo.
    public abstract void depreciarVeiculos(int tipo, double taxaDepreciacao) throws SQLException;
    
	// Tipo de veiculo a ser usado:
	// 0 (todos), 1 (moto), 2 (carro), 3 (caminhão), 4 (ônibus)
    // Não pode deixar ter taxa de aumento negativa. Ela sempre será positiva e será acrescida ao valor da diária.
    public abstract void aumentarDiaria(int tipo, double taxaAumento) throws SQLException;
    
    // Retorna o valor total de faturamento para um tipo de veículo, durante um período. 
    // Os alugueis devem começar e terminar dentro do período.
    public abstract double faturamentoTotal(int tipo, Date inicio, Date fim) throws SQLException, VeiculoJaCadastrado, VeiculoNaoCadastrado;
    
    // Retorna a quantidade total de diárias de aluguel para um tipo de veículo, durante um período.
    // Os alugueis devem começar e terminar dentro do período.
    public abstract int quantidadeTotalDeDiarias(int tipo, Date inicio, Date fim) throws SQLException, VeiculoNaoCadastrado;
}
