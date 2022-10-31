package PauloAugustoMartins.PetShop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class MeuPetShop implements PetShop {

    @Override
    public void cadastroTipoDeServico(int codigo, String descricao, double valor, boolean adulto) throws TipoDeServicoJaCadastrado, SQLException {
        Connection con = Conexao.getConexao();
        Statement st = con.createStatement();
        String insert = "insert into tipodeservico (codigo, descricao, valor, adulto) values ("+"'"+codigo+"', "+"'"+descricao+"', "+"'"+valor+"', "+adulto+");";
        System.out.println(insert);
        st.executeUpdate(insert);
        st.close();
        
        return;
    }

    @Override
    public void cadastroCliente(int cpf, String nome) throws Exception {
        Connection con = Conexao.getConexao();
        Statement st = con.createStatement();
        boolean b;
        b = pesquisarCliente(cpf);
        if(b == true){
            String insert = "insert into cliente (cpf, nome) values ("+"'"+cpf+"', '"+nome+"');";
            System.out.println(insert);
            st.executeUpdate(insert);
            st.close();
        }else if(b==false){
            throw new CPFJaCadastrado("Cpf ja cadastrado");
        }  
        return;
    }

    @Override
    public void cadastroAnimal(int cpfCliente, Animal Animal) throws Exception {
        Connection con = Conexao.getConexao();
        Statement st = con.createStatement();
        boolean recovered = pesquisarAnimal(Animal);  
        java.sql.Date data = new java.sql.Date(Animal.getNascimento().getTime());
        if(recovered == true){
            String insert = "insert into animal (codigo, clientecpf, nascimento, raca) values ("+"'"+Animal.getCod()+"', '"+cpfCliente+"', '"+data+"', "+"'"+Animal.getClass().getSimpleName()+"');";
            System.out.println(insert);
            st.executeUpdate(insert);
            st.close();
        }else if(recovered == false){
            throw new AnimalJaCadastrado("Animal ja cadastrado");
        }  
        return;
    }

    @Override
    public double executarServico(int codigoServico, int idAnimal, int quantidade) throws Exception {
        Connection con = Conexao.getConexao();
        Statement st = con.createStatement();
        Date inicio = new Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(inicio);
        String insert = "insert into ordemdeservico (idservico, idanimal, data, quantidade) values ('"+codigoServico+"', '"+idAnimal+"', '"+date+"', "+"'"+quantidade+"');";
        System.out.println(insert);
        st.executeUpdate(insert);
        st.close();
        TipoDeServico tds = retornarTipoServico(codigoServico);
        double valor = tds.valor * quantidade;
        return valor;
    }

    @Override
    public ArrayList<Servico> relatorioDeServicos(int cpf, Date periodoInicial, Date periodoFinal) throws Exception {
        ArrayList<Servico> arrayOrdens = new ArrayList<Servico>();
        Connection con = Conexao.getConexao();
        Statement st = con.createStatement();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String dateini = sdf.format(periodoInicial);
        String dateend = sdf.format(periodoFinal);
        String insert = "select * from ordemdeservico where data >= '"+dateini+"' && data <= '"+dateend+"';";
        System.out.println(insert);
        ResultSet rst = st.executeQuery(insert);
        while (rst.next()) {
            int idservico = rst.getInt("idservico");
            int idanimal = rst.getInt("idanimal");
            Date data = rst.getDate("data");
            int quant = rst.getInt("quantidade");
            TipoDeServico servico = retornarTipoServico(idservico);
            double valservico = quant * servico.valor;
            Cliente c = retornarCliente(cpf);
            Animal a = pesquisarAnimalId(idanimal);
            Servico serv = new Servico(c, a, data, valservico);
            arrayOrdens.add(serv);
        }
        st.close();
        return arrayOrdens;
    }

    public void removerTudo() {
		Connection con = Conexao.getConexao();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String comando1 = "delete from animal;";
			String comando2 = "delete from cliente;";
			String comando3 = "delete from servico;";
			String comando4 = "delete from tipodeservico;";
			String comando5 = "delete from ordemdeservico;";
			System.out.println(comando1);
			System.out.println(comando2);
			System.out.println(comando3);
			System.out.println(comando4);
			System.out.println(comando5);
			stmt.executeUpdate(comando1);
			stmt.executeUpdate(comando2);
			stmt.executeUpdate(comando3);
			stmt.executeUpdate(comando4);
			stmt.executeUpdate(comando5);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

    public boolean pesquisarCliente(int cpf) throws Exception{
        Connection con = null;
		Statement st = null;
        
        con = Conexao.getConexao();
        st = con.createStatement();

        String sql = "select * from cliente where cpf = '" + cpf + "';";
        System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        if(rs != null){
            return true;
        }else{
            return false;
        }
    }

    public boolean pesquisarAnimal(Animal pet) throws SQLException{
        Connection con = null;
		Statement st = null;
        con = Conexao.getConexao();
        st = con.createStatement();
        String sql = "select * from animal where codigo = '" + pet.cod + "';";
        System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        if(rs.next()){
            return false;
        }
        return true;
    }

    public boolean pesquisarServico(int cod) throws SQLException{
        Connection con = null;
		Statement st = null;
        con = Conexao.getConexao();
        st = con.createStatement();
        String sql = "select * from servico where cpfcliente = '" + cod + "';";
        System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        if(rs.next()){
            return false;
        }
        return true;
    }

    public TipoDeServico retornarTipoServico(int IDServico) throws Exception{
        Connection con = null;
		Statement st = null;
        TipoDeServico c1 = null;

        try {
            con = Conexao.getConexao();
            st = con.createStatement();

            String sql = "select * from tipodeservico where codigo = '" + IDServico + "';";
            System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
	        	String desc = rs.getString("descricao");
	        	double val = rs.getDouble("valor");
                boolean bol = rs.getBoolean("adulto");
                c1 = new TipoDeServico(IDServico, desc, val, bol);
	        }else{
	    		throw new Exception("Tipo de Serviço inexistente");
	        }
        	st.close();
        	return c1;
        } catch (Exception e) {
            e.printStackTrace();
    		throw new Exception("Cliente inexistente.");
        }
    }

    public Cliente retornarCliente(int cpfCliente) throws Exception{
        Connection con = null;
		Statement st = null;
        Cliente c1 = null;

        try {
            con = Conexao.getConexao();
            st = con.createStatement();

            String sql = "select * from cliente where cpf = '" + cpfCliente + "';";
            System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                int cpf = rs.getInt("cpf");
	        	String nome = rs.getString("nome");
                c1 = new Cliente(cpf, nome);
	        }else{
	    		throw new Exception("Cliente inexistente");
	        }
        	st.close();
        	return c1;
        } catch (Exception e) {
            e.printStackTrace();
    		throw new Exception("Cliente inexistente.");
        }
    }

    public Animal pesquisarAnimalId(int IDAnimal) throws Exception{
        Connection con = null;
		Statement st = null;
        Cliente c1 = null;
        Animal anm = null;

        con = Conexao.getConexao();
        st = con.createStatement();

        String sql = "select * from animal where codigo = '" + IDAnimal + "';";
        System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        if(rs.next()){
            int cpf = rs.getInt("clientecpf");
            Date nascimento = rs.getDate("nascimento");
            java.util.Date utilDate = new java.util.Date(nascimento.getTime());
            String raca = rs.getString("raca");
            if(raca.equals("Gato")){
                c1 = retornarCliente(cpf);
                anm = new Gato(IDAnimal, c1, utilDate);
            }else if(raca.equals("Cachorro")){
                c1 = retornarCliente(cpf);
                anm = new Cachorro(IDAnimal, c1, utilDate);
            }
        }else{
            throw new AnimalInexistente("Animal Inexistente!");
        }
        st.close();
        return anm;
    }
}
