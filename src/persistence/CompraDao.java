package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Compra;
import model.Produto;
import model.ProdutoComprado;

public class CompraDao implements ICompraDao {

	private Connection c;
	
	public CompraDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public String insertCompra(Compra com) throws SQLException {
		String sql = "INSERT INTO compra VALUES (?,?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, com.getCodigo());
		ps.setInt(2, com.getCliente().getId());
		ps.setInt(3, com.getProdutoComprado().getProduto().getId());
		ps.setInt(4, com.getProdutoComprado().getQtd());
		ps.setString(5, com.getData());
		
		ps.execute();
		ps.close();
		
		String saida = "Compra inserida com sucesso";
		return saida;
	}

	@Override
	public List<Compra> selectCompra(Compra com) throws SQLException {
		List<Compra> listaCompra = new ArrayList<Compra>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT com.codigo, cli.id AS idCliente, cli.nome AS nomeCliente, "); 
		sql.append("cli.telefone, p.id AS idProduto, p.nome AS nomeProduto, ");
		sql.append("p.valor, com.qtd, p.valor * com.qtd AS vl_total, "); 
		sql.append("CONVERT(CHAR(10), com.dt_compra, 103) AS dt_compra ");
		sql.append("FROM cliente cli, produto p, compra com ");
		sql.append("WHERE cli.id = com.idCliente ");
		sql.append("AND p.id = com.idProduto ");
		sql.append("AND com.codigo = ?");
		
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setInt(1, com.getCodigo());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Compra compra = new Compra();
			Cliente cli = new Cliente();
			ProdutoComprado pc = new ProdutoComprado();
			Produto p = new Produto();
			
			p.setId(rs.getInt("idProduto"));
			p.setNome(rs.getString("nomeProduto"));
			p.setValor(rs.getFloat("valor"));
			
			pc.setProduto(p);
			pc.setQtd(rs.getInt("qtd"));
			pc.setValorTotal(rs.getFloat("vl_total"));
			
			cli.setId(rs.getInt("idCliente"));
			cli.setNome(rs.getString("nomeCliente"));
			cli.setTelefone(rs.getString("telefone"));
			
			compra.setCodigo(rs.getInt("codigo"));
			compra.setData(rs.getString("dt_compra"));
			compra.setCliente(cli);
			compra.setProdutoComprado(pc);
			
			listaCompra.add(compra);
		}
		
		rs.close();
		ps.close();
		return listaCompra;
	}

}
