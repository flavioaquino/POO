package FlavioBasilio.Locadora;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;

public class DAOAluguel{
    
    public void registrarAluguel(Veiculo v, Cliente c, int dias, double valor) throws VeiculoJaCadastrado, Exception {
		LocalDateTime date = LocalDateTime.now();
		Connection con = Conexao.getConexao();
		Statement st = con.createStatement();
	    String insert = "insert into aluguel (placa, cpf, q_dias, valor_aluguel, data, tipo) values ("+"'"+ v.getPlaca()+"', "+"'" + c.getCpf()+"' ,"+"'" + dias+"' ,"+"'" + valor+"' ,"+"'" + date+"' ,"+"'"+v.getTipo()+"'"+");";
	    System.out.println(insert);
	    st.executeUpdate(insert);
        st.close();
        return;
	}

    public Aluguel pesquisarPlaca(String placa) throws VeiculoNaoCadastrado {
		Connection con = null;
		Statement st = null;
        Aluguel al = null;

        try {
            con = Conexao.getConexao();
            st = con.createStatement();

            String sql = "select * from aluguel where placa = '" + placa + "';";
            System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                int cpf = rs.getInt("cpf");
	        	double v_bem = rs.getDouble("valor_aluguel");
	        	int dias = rs.getInt("q_dias");
	        	Date data = rs.getDate("data");
				int i = rs.getInt("tipo");
            
                al = new Aluguel(cpf, v_bem, placa, data, dias, i);
	        }else{
	    		throw new AluguelInexistente("Aluguel Inexistente");
	        }
        	st.close();
        	return al;
        } catch (Exception e) {
            e.printStackTrace();
    		throw new VeiculoNaoCadastrado("Aluguel inexistente.");
        }
	}

    public Aluguel pesquisarCpf(int cpf) throws VeiculoNaoCadastrado {
		Connection con = null;
		Statement st = null;
        Aluguel al = null;

        try {
            con = Conexao.getConexao();
            st = con.createStatement();

            String sql = "select * from aluguel where cpf = '" + cpf + "';";
            System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                String placa = rs.getString("placa");
	        	double v_bem = rs.getDouble("valor_aluguel");
	        	int dias = rs.getInt("q_dias");
	        	Date data = rs.getDate("data");
				int i = rs.getInt("tipo");
            
                al = new Aluguel(cpf, v_bem, placa, data, dias, i);
	        }else{
	    		throw new AluguelInexistente("Aluguel Inexistente");
	        }
        	st.close();
        	return al;
        } catch (Exception e) {
            e.printStackTrace();
    		throw new VeiculoNaoCadastrado("Aluguel inexistente.");
        }
	}

	public void devolucao(String p) throws VeiculoNaoCadastrado {
		Connection con = Conexao.getConexao();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String comando = "delete from aluguel where placa = " + p;
			System.out.println(comando);
			stmt.executeUpdate(comando);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void removerTudo() {
		Connection con = Conexao.getConexao();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String comando = "delete from aluguel";
			System.out.println(comando);
			stmt.executeUpdate(comando);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

    public void alterarDias(String placa, int val){
		Connection con = null;
		Statement st = null;
		try {
			con = Conexao.getConexao();
		    st = con.createStatement();
			String insert = "update aluguel set q_dias = '"+ val +"' where placa = '"+ placa +"';";
			System.out.println(insert);
			st.executeUpdate(insert);
        	st.close();
        	return;
		} catch (Exception e) {
			e.printStackTrace();
		  }
	}
}
