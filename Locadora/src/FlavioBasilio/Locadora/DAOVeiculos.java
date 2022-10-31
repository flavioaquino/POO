package FlavioBasilio.Locadora;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOVeiculos {

    public void inserir(Veiculo v) throws Exception {
		Connection con = Conexao.getConexao();
		Statement st = con.createStatement();
	    String insert = "insert into veiculos (marca, modelo, ano_fab, v_bem, v_dia, placa, categoria, classe) values ("+"'"+ v.getMarca()+"', "+"'" + v.getModelo()+"' ,"+"'"+v.getAnoDeFabricacao()+"', " +"'"+ v.getValorAvaliado()+"', " + "'" +v.getValorDiaria()+"', " +"'"+v.getPlaca()+"', " +"'"+v.getTipo()+"', " + "'"+ v.getClass().getSimpleName() +"'"+");";
	    System.out.println(insert);
	    st.executeUpdate(insert);
        st.close();
        return;
	}

	public Veiculo pesquisar(String placa) throws Exception {
		Connection con = null;
		Statement st = null;
        Veiculo v = null;

        try {
            con = Conexao.getConexao();
            st = con.createStatement();

            String sql = "select * from veiculos where placa = '" + placa + "';";
            System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                String classe = rs.getString("classe");
	        	String marca = rs.getString("marca");
	        	String modelo = rs.getString("modelo");
	        	int i = rs.getInt("categoria");
	        	int ano_fab = rs.getInt("ano_fab");
	        	double v_dia = rs.getDouble("v_dia");
	        	double v_bem = rs.getDouble("v_bem");
                if(classe.equals("Carro")){
                    v = new Carro(marca, modelo, ano_fab, v_bem, v_dia, placa, i);
                }else if(classe.equals("Moto")){
                    v = new Moto(marca, modelo, ano_fab, v_bem, v_dia, placa, i);
                }else if(classe.equals("Caminhao")){
                    v = new Caminhao(marca, modelo, ano_fab, v_bem, v_dia, placa, i);
                }else if(classe.equals("Onibus")){
                    v = new Onibus(marca, modelo, ano_fab, v_bem, v_dia, placa, i);
                }
	        }else{
	    		throw new Exception("Veiculo inexistente");
	        }
        	st.close();
        	return v;
        } catch (Exception e) {
            e.printStackTrace();
    		throw new Exception("Veiculo inexistente.");
        }
	}

	public void alterarValDiaria(String placa, int val){
		Connection con = null;
		Statement st = null;
		try {
			con = Conexao.getConexao();
		    st = con.createStatement();
			String insert = "update veiculos set v_dia = '"+ val +"' where placa = '"+ placa +"';";
			System.out.println(insert);
			st.executeUpdate(insert);
        	st.close();
        	return;
		} catch (Exception e) {
			e.printStackTrace();
		  }
	}

	public void removerVeiculo(String placa){
		Connection con = null;
		Statement st = null;
		try {
			con = Conexao.getConexao();
		    st = con.createStatement();
			String insert = "delete from veiculos where placa = '"+ placa +"';";
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
	        String insert = "delete from veiculos;";
	        System.out.println(insert);
	        st.executeUpdate(insert);
        	st.close();
        	return;
		  } catch (Exception e) {
			e.printStackTrace();
		  }
	}
}
