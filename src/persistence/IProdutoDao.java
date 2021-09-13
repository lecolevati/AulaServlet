package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Produto;

public interface IProdutoDao {

	public String insertProduto (Produto p) throws SQLException;
	public String updateProduto (Produto p) throws SQLException;
 	public String deleteProduto (Produto p) throws SQLException;
	public Produto selectProduto(Produto p) throws SQLException;
	public List<Produto> selectProdutos() throws SQLException;
	
}