package PauloAugustoMartins.PetShop;

import java.util.ArrayList;
import java.util.Date;

public interface PetShop {
	public void cadastroTipoDeServico(int codigo, String descricao, double valor, boolean adulto) throws Exception;
    public void cadastroCliente(int cpf, String nome) throws Exception;
    public void cadastroAnimal(int cpf, Animal a) throws Exception;
    public double executarServico(int codigoServico, int idAnimal, int quantidade) throws Exception;
    public ArrayList<Servico> relatorioDeServicos(int cpf, Date periodoInicial, Date periodoFinal) throws Exception;
}
