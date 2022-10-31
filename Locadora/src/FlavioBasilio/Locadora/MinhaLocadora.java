package FlavioBasilio.Locadora;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MinhaLocadora extends Locadora {

    ArrayList<Aluguel> alugueis = new ArrayList<Aluguel>();
    ArrayList<Veiculo> alugados = new ArrayList<Veiculo>();
    // Retorna as motos com cilindrada maior ou igual a pesquisada.
    @Override
    public ArrayList<Veiculo> pesquisarMoto(int cilindrada) throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt;
        stmt = con.createStatement();
        String sql = "select * from veiculos where classe = 'Moto';";
        ResultSet rst;
        rst = stmt.executeQuery(sql);
        ArrayList<Veiculo> motos = new ArrayList<Veiculo>();
        while (rst.next()) {
            String placa = rst.getString("placa");
            String marca = rst.getString("marca");
            String modelo = rst.getString("modelo");
            int categoria = rst.getInt("categoria");
            int anofab = rst.getInt("ano_fab");
            double vdia = rst.getDouble("v_dia");
            double vbem = rst.getDouble("v_bem");
            Veiculo vei = new Moto(marca, modelo, anofab, vbem, vdia, placa, categoria);
            if(categoria == cilindrada){
                motos.add(vei);
            }
        }
        return motos;
    }
    // tipo de carro
    // 1 (passeio), 2 (SUV), 3 (pickup)
    @Override
    public ArrayList<Veiculo> pesquisarCarro(int tipoCarro) throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt;
        stmt = con.createStatement();
        String sql = "select * from veiculos where classe = 'Carro';";
        ResultSet rst;
        rst = stmt.executeQuery(sql);
        ArrayList<Veiculo> carros = new ArrayList<Veiculo>();
        while (rst.next()) {
            String placa = rst.getString("placa");
            String marca = rst.getString("marca");
            String modelo = rst.getString("modelo");
            int categoria = rst.getInt("categoria");
            int anofab = rst.getInt("ano_fab");
            double vdia = rst.getDouble("v_dia");
            double vbem = rst.getDouble("v_bem");
            Veiculo vei = new Carro(marca, modelo, anofab, vbem, vdia, placa, categoria);
            if(categoria == tipoCarro){
                carros.add(vei);
            }
        }
        return carros;
    }
    // Retorna os caminhões com capacidade de carga maior ou igual a pesquisada.
    @Override
    public ArrayList<Veiculo> pesquisarCaminhao(int carga) throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt;
        stmt = con.createStatement();
        String sql = "select * from veiculos where classe = 'Caminhao';";
        ResultSet rst;
        rst = stmt.executeQuery(sql);
        ArrayList<Veiculo> caminhoes = new ArrayList<Veiculo>();
        while (rst.next()) {
            String placa = rst.getString("placa");
            String marca = rst.getString("marca");
            String modelo = rst.getString("modelo");
            int categoria = rst.getInt("categoria");
            int anofab = rst.getInt("ano_fab");
            double vdia = rst.getDouble("v_dia");
            double vbem = rst.getDouble("v_bem");
            Veiculo vei = new Caminhao(marca, modelo, anofab, vbem, vdia, placa, categoria);
            if(categoria >= carga){
                caminhoes.add(vei);
            }
        }
        return caminhoes;
    }
    // Retorna os ônibus com capacidade de passageiros maior ou igual a pesquisada.
    @Override
    public ArrayList<Veiculo> pesquisarOnibus(int passageiros) throws SQLException{
        Connection con = Conexao.getConexao();
        Statement stmt;
        stmt = con.createStatement();
        String sql = "select * from veiculos where classe = 'Onibus';";
        ResultSet rst;
        rst = stmt.executeQuery(sql);
        ArrayList<Veiculo> onibus = new ArrayList<Veiculo>();
        while (rst.next()) {
            String placa = rst.getString("placa");
            String marca = rst.getString("marca");
            String modelo = rst.getString("modelo");
            int categoria = rst.getInt("categoria");
            int anofab = rst.getInt("ano_fab");
            double vdia = rst.getDouble("v_dia");
            double vbem = rst.getDouble("v_bem");
            Veiculo vei = new Onibus(marca, modelo, anofab, vbem, vdia, placa, categoria);
            if(categoria >= passageiros){
                onibus.add(vei);
            }
        }
        return onibus;
    }

    public void inserir(Veiculo v) throws  VeiculoJaCadastrado, VeiculoNaoCadastrado, SQLException  {
		Connection con = Conexao.getConexao();
		Statement st = con.createStatement();
	    String insert = "insert into veiculos (marca, modelo, ano_fab, v_bem, v_dia, placa, categoria, classe) values ("+"'"+ v.getMarca()+"', "+"'" + v.getModelo()+"' ,"+"'"+v.getAnoDeFabricacao()+"', " +"'"+ v.getValorAvaliado()+"', " + "'" +v.getValorDiaria()+"', " +"'"+v.getPlaca()+"', " +"'"+v.getTipo()+"', " + "'"+ v.getClass().getSimpleName() +"'"+");";
	    System.out.println(insert);
	    st.executeUpdate(insert);
        st.close();
        return;
	}

    public void removerTudo() {
		Connection con = Conexao.getConexao();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String comando1 = "delete from veiculos;";
			String comando2 = "delete from aluguel;";
			String comando3 = "delete from clientes;";
			String comando4 = "delete from devolucao;";
			System.out.println(comando1);
			System.out.println(comando2);
			System.out.println(comando3);
			System.out.println(comando4);
			stmt.executeUpdate(comando1);
			stmt.executeUpdate(comando2);
			stmt.executeUpdate(comando3);
			stmt.executeUpdate(comando4);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

    public void inserir(Cliente c) throws ClienteJaCadastrado, ClienteNaoCadastrado, SQLException {
        Connection con = Conexao.getConexao();
		Statement st = con.createStatement();
	    String insert = "insert into clientes (cpf, nome) values ("+"'"+ c.getCpf()+"', "+"'" + c.getNome()+"'"+");";
	    System.out.println(insert);
	    st.executeUpdate(insert);
        st.close();
        return;
    }

    public Cliente pesquisarCliente(int c) throws ClienteNaoCadastrado, SQLException{
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
	    		throw new ClienteNaoCadastrado(c);
	        }
        	st.close();
        	return c1;
        } catch (ClienteNaoCadastrado e) {
            e.printStackTrace();
    		throw new ClienteNaoCadastrado(c);
        }
    }

    public Veiculo pesquisar(String placa) throws VeiculoNaoCadastrado, SQLException {
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
	    		throw new VeiculoNaoCadastrado("Veiculo inexistente");
	        }
        	st.close();
        	return v;
        } catch (Exception e) {
            e.printStackTrace();
    		throw new VeiculoNaoCadastrado("Veiculo inexistente.");
        }
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


    @Override
    public double calcularAluguel(String placa, int dias) throws VeiculoNaoCadastrado, SQLException, VeiculoJaCadastrado {
        Veiculo x = pesquisar(placa);
        double aluguel;
        if (x.getClass() == Carro.class){
            aluguel = ((((x.v_bem*3)/100)/365) + x.v_dia)*dias;
        }else if(x.getClass() == Moto.class){
            aluguel = ((((x.v_bem*11)/100)/365) + x.v_dia)*dias;
        }else if(x.getClass() == Caminhao.class){
            aluguel = ((((x.v_bem*8)/100)/365) + x.v_dia)*dias;
        }else{
            aluguel = ((((x.v_bem*20)/100)/365) + x.v_dia)*dias;
        }
        return aluguel;
    }

    @Override
    public void registrarAluguel(String placa, Date data, int dias, int cpf) throws ClienteNaoCadastrado, VeiculoAlugado, VeiculoNaoCadastrado, VeiculoJaCadastrado, SQLException{
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(data);
		Connection con = Conexao.getConexao();
		Statement st = con.createStatement();
        Veiculo v = null;
        Cliente c = null;
        c = pesquisarCliente(cpf);
        v = pesquisar(placa);
        try {
            String insert = "insert into aluguel (placa, cpf, q_dias, valor_aluguel, data, tipo) values ("+"'"+ placa+"', "+"'" + c.cpf+"' ,"+"'" + dias+"' ,"+"'" + v.v_dia+"' ,"+"'" + date+"' ,"+"'"+v.getTipo()+"'"+");";
            System.out.println(insert);
            st.executeUpdate(insert);
            st.close();
        } catch (SQLException e) {
            throw new VeiculoAlugado(placa);
        }
	    
        return;
    }
    @Override
    public void registrarDevolucao(String placa, Date data, int cpf) throws VeiculoJaCadastrado, VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado, VeiculoNaoAlugado, SQLException {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Aluguel al = pesquisarPlaca(placa);
        Date datainicial = al.getData();
        String date = sdf.format(data);
        String dateini = sdf.format(datainicial);
		Connection con = Conexao.getConexao();
		Statement st = con.createStatement();
        try {
            Veiculo v = pesquisar(placa);
        } catch (SQLException e) {
            throw new VeiculoNaoCadastrado(placa);
        }
        try {
            Cliente c = pesquisarCliente(cpf);
        } catch (SQLException e) {
            throw new ClienteNaoCadastrado(cpf);
        }

        try {
            String insert = "insert into devolucao (placa, data, datafim, cpf) values ('"+ placa+"', "+"'" + dateini+"' ,"+"'" + date+"' ,"+"'"+cpf+"');";
            System.out.println(insert);
            st.executeUpdate(insert);
        } catch (SQLException e) {
            throw new VeiculoAlugado(placa);
        }

        st.close();
        return;
    }

    @Override
    public void depreciarVeiculos(int tipo, double taxaDepreciacao) throws SQLException{
        Connection con = Conexao.getConexao();
		Statement st = con.createStatement();
        if(tipo == 1){
            String insert = "update veiculos set v_bem = v_bem - (v_bem * '"+taxaDepreciacao+"') where classe = 'Moto'";
            st.executeUpdate(insert);
            System.out.println(insert);
        }else if(tipo == 2){
            String insert = "update veiculos set v_bem = v_bem - (v_bem * '"+taxaDepreciacao+"') where classe = 'Carro'";
            st.executeUpdate(insert);
            System.out.println(insert);
        }else if(tipo == 3){
            String insert = "update veiculos set v_bem = v_bem - (v_bem * '"+taxaDepreciacao+"') where classe = 'Caminhao'";
            st.executeUpdate(insert);
            System.out.println(insert);
        }else if(tipo == 4){
            String insert = "update veiculos set v_bem = v_bem - (v_bem * '"+taxaDepreciacao+"') where classe = 'Onibus'";
            st.executeUpdate(insert);
            System.out.println(insert);
        }else if(tipo == 0){
            String insert = "update veiculos set v_bem = v_bem - (v_bem * '"+taxaDepreciacao+"')";
            st.executeUpdate(insert);
            System.out.println(insert);
        }
        return;
    }

    @Override
    public void aumentarDiaria(int tipo, double taxaAumento) throws SQLException{
        Connection con = Conexao.getConexao();
		Statement st = con.createStatement();
        if(tipo == 1){
            String insert = "update veiculos set v_dia = v_dia + (v_dia * '"+taxaAumento+"') where classe = 'Moto'";
            st.executeUpdate(insert);
            System.out.println(insert);
        }else if(tipo == 2){
            String insert = "update veiculos set v_dia = v_dia + (v_dia * '"+taxaAumento+"') where classe = 'Carro'";
            st.executeUpdate(insert);
            System.out.println(insert);
        }else if(tipo == 3){
            String insert = "update veiculos set v_dia = v_dia + (v_dia * '"+taxaAumento+"') where classe = 'Caminhao'";
            st.executeUpdate(insert);
            System.out.println(insert);
        }else if(tipo == 4){
            String insert = "update veiculos set v_dia = v_dia + (v_dia * '"+taxaAumento+"') where classe = 'Onibus'";
            st.executeUpdate(insert);
            System.out.println(insert);
        }else if(tipo == 0){
            String insert = "update veiculos set v_dia = v_dia + (v_dia * '"+taxaAumento+"')";
            st.executeUpdate(insert);
            System.out.println(insert);
        }
        return;
    }

    
    Date dat;
    @Override
    public double faturamentoTotal(int tipo, Date inicio, Date fim) throws SQLException, VeiculoJaCadastrado, VeiculoNaoCadastrado{
        // Retorna o valor total de faturamento para um tipo de veículo, durante um período. 
        // Os alugueis devem começar e terminar dentro do período.
        Connection con = Conexao.getConexao();
		Statement st = null;
        Veiculo v = null;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String dateini = sdf.format(inicio);
        String dateend = sdf.format(fim);
        st = con.createStatement();
        String insert = "select * from devolucao where data >= '"+dateini+"' && data <= '"+dateend+"';";
	    ResultSet rst = st.executeQuery(insert);
        System.out.println(insert);
        double fatu = 0;
        while (rst.next()) {
            String placa = rst.getString("placa");
            v = pesquisar(placa);
            Aluguel al = pesquisarPlaca(placa);
            int dias = al.getDias();
            if(tipo == 1){
                if(v.getClass() == Moto.class){
                    double vdia = v.getValorDiaria();
                    double vava = v.getValorAvaliado();
                    double valuguel = (vdia + (((vava * 11)/100)/365))* dias;
                    fatu = fatu + valuguel;
                }
            }else if(tipo == 2){
                if(v.getClass() == Carro.class){
                    double vdia = v.getValorDiaria();
                    double vava = v.getValorAvaliado();
                    double valuguel = (vdia + (((vava * 3)/100)/365))* dias;
                    fatu = fatu + valuguel;
                }
            }else if(tipo == 3){
                if(v.getClass() == Caminhao.class){
                    double vdia = v.getValorDiaria();
                    double vava = v.getValorAvaliado();
                    double valuguel = (vdia + (((vava * 8)/100)/365))* dias;
                    fatu = fatu + valuguel;
                }
            }else if(tipo == 4){
                if(v.getClass() == Onibus.class){
                    double vdia = v.getValorDiaria();
                    double vava = v.getValorAvaliado();
                    double valuguel = (vdia + (((vava * 20)/100)/365))* dias;
                    fatu = fatu + valuguel;
                }
            }else if(tipo == 0){
                if(v.getClass() == Moto.class){
                    double vdia = v.getValorDiaria();
                    double vava = v.getValorAvaliado();
                    double valuguel = (vdia + (((vava * 11)/100)/365))* dias;
                    fatu = fatu + valuguel;
                }else if(v.getClass() == Carro.class){
                    double vdia = v.getValorDiaria();
                    double vava = v.getValorAvaliado();
                    double valuguel = (vdia + (((vava * 3)/100)/365))* dias;
                    fatu = fatu + valuguel;
                }else if(v.getClass() == Caminhao.class){
                    double vdia = v.getValorDiaria();
                    double vava = v.getValorAvaliado();
                    double valuguel = (vdia + (((vava * 8)/100)/365))* dias;
                    fatu = fatu + valuguel;
                }else if(v.getClass() == Onibus.class){
                    double vdia = v.getValorDiaria();
                    double vava = v.getValorAvaliado();
                    double valuguel = (vdia + (((vava * 20)/100)/365))* dias;
                    fatu = fatu + valuguel;
                }
            }
        }
        return fatu;
    }

    @Override
    public int quantidadeTotalDeDiarias(int tipo, Date inicio, Date fim) throws SQLException, VeiculoNaoCadastrado {
        int qtotal = 0;
        Connection con = Conexao.getConexao();
		Statement st = null;
        Veiculo v = null;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String dateini = sdf.format(inicio);
        String dateend = sdf.format(fim);
        st = con.createStatement();
        String insert = "select * from devolucao where data = '"+dateini+"' && datafim = '"+dateend+"';";
	    ResultSet rst = st.executeQuery(insert);
        System.out.println(insert);
        while (rst.next()) {
            String placa = rst.getString("placa");
            v = pesquisar(placa);
            Aluguel al = pesquisarPlaca(placa);
            int dias = al.getDias();
            if(tipo == 1){
                if(v.getClass() == Moto.class){
                    qtotal = qtotal + dias;
                }
            }else if(tipo == 2){
                if(v.getClass() == Carro.class){
                    qtotal = qtotal + dias;
                }
            }else if(tipo == 3){
                if(v.getClass() == Caminhao.class){
                    qtotal = qtotal + dias;
                }
            }else if(tipo == 4){
                if(v.getClass() == Onibus.class){
                    qtotal = qtotal + dias;
                }
            }else if(tipo == 0){
                if(v.getClass() == Moto.class){
                    qtotal = qtotal + dias;
                }else if(v.getClass() == Carro.class){
                    qtotal = qtotal + dias;
                }else if(v.getClass() == Caminhao.class){
                    qtotal = qtotal + dias;
                }else if(v.getClass() == Onibus.class){
                    qtotal = qtotal + dias;
                }
            }
        }
        return qtotal;
    }
}

