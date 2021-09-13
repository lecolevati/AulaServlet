package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Cliente;

public interface IClienteDao {

	public String insertCliente (Cliente cli) throws SQLException;
	public String updateCliente (Cliente cli) throws SQLException;
 	public String deleteCliente (Cliente cli) throws SQLException;
	public Cliente selectCliente(Cliente cli) throws SQLException;
	public List<Cliente> selectClientes() throws SQLException;
	
}
