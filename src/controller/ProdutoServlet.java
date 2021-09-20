package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Produto;
import persistence.ProdutoDao;
import persistence.IProdutoDao;

@WebServlet("/produto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProdutoDao cDao;
	
	
	public ProdutoServlet() {
		try {
			cDao = new ProdutoDao();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("button");
		String saida = "";
		String erro = "";
//		System.out.println(cmd);
		List<Produto> listaProdutos = new ArrayList<Produto>();
		Produto produto = validaCampos(request, cmd);
		try {
			if (cmd.contains("Cadastrar")) {
				if (produto != null) {
					saida = cDao.insertProduto(produto);
					produto = new Produto();
				}
			}
			if (cmd.contains("Atualizar")) {
				if (produto != null) {
					saida = cDao.updateProduto(produto);
					produto = new Produto();
				}
			}
			if (cmd.contains("Excluir")) {
				if (produto != null) {
					saida = cDao.deleteProduto(produto);
					produto = new Produto();
				}
			}
			if (cmd.contains("Buscar")) {
				if (produto != null) {
					produto = cDao.selectProduto(produto);
				}
			}
			if (cmd.contains("Listar")) {
				listaProdutos = cDao.selectProdutos();
			}
		} catch (SQLException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("produto", produto);
			request.setAttribute("listaProdutos", listaProdutos);
			RequestDispatcher rd = request.getRequestDispatcher("produto.jsp");
			rd.forward(request, response);
		}
	}

	private Produto validaCampos(HttpServletRequest request, String cmd) {
		Produto produto = new Produto();
		if (cmd.contains("Cadastrar") || cmd.contains("Atualizar")) {
			if (!request.getParameter("id").trim().isEmpty() &&
					!request.getParameter("nome").trim().isEmpty() &&
					!request.getParameter("valor").trim().isEmpty()) {
				produto.setId(Integer.parseInt(request.getParameter("id").trim()));
				produto.setNome(request.getParameter("nome").trim());
				produto.setValor(Float.parseFloat(request.getParameter("valor").trim()));
			}
		}
		if (cmd.contains("Excluir") || cmd.contains("Buscar")) {
			if (!request.getParameter("id").trim().isEmpty()) {
				produto.setId(Integer.parseInt(request.getParameter("id").trim()));
			}
		}
		return produto;
	}

}
