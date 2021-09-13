package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Compra;

public interface ICompraDao {

	public String insertCompra(Compra com) throws SQLException;
	public List<Compra> selectCompra(Compra com) throws SQLException;
	
}
