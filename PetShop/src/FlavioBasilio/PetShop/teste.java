package PauloAugustoMartins.PetShop;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class teste {
    @Test
    public void testarCadastrarCliente() throws Exception{
        MeuPetShop ps = new MeuPetShop();
        ps.removerTudo();

        ps.cadastroCliente(1, "Joao");
        ps.cadastroCliente(2, "Marcos");
        ps.cadastroCliente(3, "Vilela");
        ps.cadastroCliente(4, "Kaua");
        ps.cadastroCliente(123, "Paulo");

        Cliente c1 = ps.retornarCliente(1);
        Cliente c2 = ps.retornarCliente(2);
        Cliente c3 = ps.retornarCliente(3);
        Cliente c4 = ps.retornarCliente(4);
        Cliente c5 = ps.retornarCliente(123);

        assertEquals(c1.nome, "Joao");
        assertEquals(c1.cpf, 1);
        assertEquals(c2.nome, "Marcos");
        assertEquals(c2.cpf, 2);
        assertEquals(c3.nome, "Vilela");
        assertEquals(c3.cpf, 3);
        assertEquals(c4.nome, "Kaua");
        assertEquals(c4.cpf, 4);
        assertEquals(c5.nome, "Paulo");
        assertEquals(c5.cpf, 123);
    }

    @Test
    public void testarCadastroTipoDeServico() throws Exception {
        MeuPetShop ps = new MeuPetShop();
        ps.removerTudo();

        ps.cadastroCliente(1, "Joao");
        ps.cadastroTipoDeServico(1, "Tosa", 100, false);
        Cliente Joao = ps.retornarCliente(1);
        
        assertEquals(Joao.cpf, 1);
        assertEquals(Joao.nome, "Joao");
    }

    @Test
    public void testarCadastroAnimal() throws Exception {
        MeuPetShop ps = new MeuPetShop();
        ps.removerTudo();

        Date hoje = new Date();
        String Date = "20/10/2022";  
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(Date);  

        ps.cadastroCliente(1, "Joao");
        Animal xanim = new Gato(12, ps.retornarCliente(1), hoje);
        ps.cadastroAnimal(1, xanim);

        Animal recuperado = ps.pesquisarAnimalId(12);
        Cliente rerecuperado = recuperado.getDono();
        assertEquals(12, recuperado.getCod());
        assertEquals(rerecuperado.nome, "Joao");
        assertEquals(rerecuperado.cpf, 1);
        assertEquals(date, recuperado.getNascimento());
    }

    @Test
    public void executarServico() throws Exception{
        MeuPetShop ps = new MeuPetShop();
        ps.removerTudo();

        String Date = "20/10/2022";  
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(Date);  
        ps.cadastroCliente(123, "Joao");
        Cliente c1 = ps.retornarCliente(123);
        Animal xane = new Gato(123, c1, date);
        ps.cadastroAnimal(123, xane);

        ps.cadastroTipoDeServico(1, "Tosa", 100, false);

        TipoDeServico tds = ps.retornarTipoServico(1);
        assertEquals(1, tds.codigo);
        assertEquals(false, tds.dispA);
        assertEquals(100, tds.valor, 0.001);

        ps.executarServico(1, 123, 5);
        ArrayList<Servico> arrayOrdens = ps.relatorioDeServicos(123, date, date);
        for(Servico s : arrayOrdens){
            double valor = s.valor;
            Animal a = s.animal;
            assertEquals(123, a.cod);
            assertEquals(500, valor, 0.0001);
        }
    }
}
