package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDao implements IClienteDao {

	private Connection c;
	
	public ClienteDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	@Override
	public String insertCliente(Cliente cli) throws SQLException {
		String saida = insUpdDel(cli, "I");
		return saida;
	}

	@Override
	public String updateCliente(Cliente cli) throws SQLException {
		String saida = insUpdDel(cli, "U");
		return saida;
	}

	@Override
	public String deleteCliente(Cliente cli) throws SQLException {
		String saida = insUpdDel(cli, "D");
		return saida;
	}

	@Override
	public Cliente selectCliente(Cliente cli) throws SQLException {
		String sql = "SELECT id, nome, telefone FROM cliente WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, cli.getId());
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			cli.setNome(rs.getString("nome"));
			cli.setTelefone(rs.getString("telefone"));
		} else {
			cli = new Cliente();
		}
		rs.close();
		ps.close();
		
		return cli;
	}

	@Override
	public List<Cliente> selectClientes() throws SQLException {
		List<Cliente> listaCli = new ArrayList<Cliente>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id, nome, ");
		sql.append("'('+SUBSTRING(telefone,1,2)+')'+SUBSTRING(telefone,3,5)+'-' ");
		sql.append("+SUBSTRING(telefone,8,4) AS telefone ");
		sql.append("FROM cliente");
		
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Cliente cli = new Cliente();
			cli.setId(rs.getInt("id"));
			cli.setNome(rs.getString("nome"));
			cli.setTelefone(rs.getString("telefone"));
			
			listaCli.add(cli);
		}
		rs.close();
		ps.close();
		
		return listaCli;
	}

	private String insUpdDel(Cliente cli, String cod) throws SQLException {
		String sql = "{CALL sp_iud_cliente (?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, cod);
		cs.setInt(2, cli.getId());
		cs.setString(3, cli.getNome());
		cs.setString(4, cli.getTelefone());
		cs.registerOutParameter(5, Types.VARCHAR);
		
		cs.execute();
		String saida = cs.getString(5);
		cs.close();
		
		return saida;
	}
}
