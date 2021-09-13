package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class ProdutoDao implements IProdutoDao {

	private Connection c;
	
	public ProdutoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public String insertProduto(Produto p) throws SQLException {
		String saida = insUpdDel(p, "I");
		return saida;
	}

	@Override
	public String updateProduto(Produto p) throws SQLException {
		String saida = insUpdDel(p, "U");
		return saida;
	}

	@Override
	public String deleteProduto(Produto p) throws SQLException {
		String saida = insUpdDel(p, "D");
		return saida;
	}

	@Override
	public Produto selectProduto(Produto p) throws SQLException {
		String sql = "SELECT id, nome, Valor FROM produto WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, p.getId());
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			p.setNome(rs.getString("nome"));
			p.setValor(rs.getFloat("valor"));
		} else {
			p = new Produto();
		}
		rs.close();
		ps.close();
		
		return p;
	}

	@Override
	public List<Produto> selectProdutos() throws SQLException {
		List<Produto> listap = new ArrayList<Produto>();
		String sql = "SELECT id, nome, valor FROM produto";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Produto p = new Produto();
			p.setId(rs.getInt("id"));
			p.setNome(rs.getString("nome"));
			p.setValor(rs.getFloat("valor"));
			
			listap.add(p);
		}
		rs.close();
		ps.close();
		
		return listap;
	}
	
	private String insUpdDel(Produto p, String cod) throws SQLException {
		String sql = "{CALL sp_iud_produto (?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, cod);
		cs.setInt(2, p.getId());
		cs.setString(3, p.getNome());
		cs.setFloat(4, p.getValor());
		cs.registerOutParameter(5, Types.VARCHAR);
		
		cs.execute();
		String saida = cs.getString(5);
		cs.close();
		
		return saida;
	}

}
