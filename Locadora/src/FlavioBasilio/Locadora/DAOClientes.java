package FlavioBasilio.Locadora;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOClientes {

    public void cadastrarCliente(Cliente c) throws Exception {
		Connection con = Conexao.getConexao();
		Statement st = con.createStatement();
	    String insert = "insert into clientes (cpf, nome) values ("+"'"+ c.getCpf()+"', "+"'" + c.getNome()+"'"+");";
	    System.out.println(insert);
	    st.executeUpdate(insert);
        st.close();
        return;
	}

    public Cliente pesquisar(int c) throws Exception {
		Connection con = null;
		Statement st = null;
        Cliente c1 = null;

        try {
            con = Conexao.getConexao();
            st = con.createStatement();

            String sql = "select * from clientes where cpf = '" + c + "';";
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

    public void removerCliente(int cpf){
		Connection con = null;
		Statement st = null;
		try {
			con = Conexao.getConexao();
		    st = con.createStatement();
			String insert = "delete from clientes where cpf = '"+ cpf +"';";
			System.out.println(insert);
			st.executeUpdate(insert);
        	st.close();
        	return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void removerTudo() {
		Connection con = null;
		Statement st = null;
		try {
			con = Conexao.getConexao();
		    st = con.createStatement();
	        String insert = "delete from clientes;";
	        System.out.println(insert);
	        st.executeUpdate(insert);
        	st.close();
        	return;
		  } catch (Exception e) {
			e.printStackTrace();
		  }
	}
}