package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.Compra;
import model.Produto;
import model.ProdutoComprado;
import persistence.ClienteDao;
import persistence.CompraDao;
import persistence.IClienteDao;
import persistence.ICompraDao;
import persistence.IProdutoDao;
import persistence.ProdutoDao;

@WebServlet("/compra")
public class CompraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IClienteDao cDao;
	private IProdutoDao pDao;
	private ICompraDao comDao;
       
    public CompraServlet() {
    	try {
			cDao = new ClienteDao();
	    	pDao = new ProdutoDao();
	    	comDao = new CompraDao();
    	} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		List<Produto> listaProdutos = new ArrayList<Produto>();
		String erro = "";
		try {
			listaClientes = listaClientes();
			listaProdutos = listaProdutos();
		} catch (SQLException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("erro", erro);
			request.setAttribute("listaClientes", listaClientes);
			request.setAttribute("listaProdutos", listaProdutos);
			RequestDispatcher rd = request.getRequestDispatcher("compra.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("button");
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		List<Compra> listaCompras = new ArrayList<Compra>();
		List<Produto> listaProdutos = new ArrayList<Produto>();
		Compra com = validaCampos(request, cmd);
		String erro = "";
		String saida = "";
		try {
			if (cmd.contains("Cadastrar")) {
				if (com != null) {
					saida = comDao.insertCompra(com);
					com = new Compra();
				}
			}
			if (cmd.contains("Buscar")) {
				if (com != null) {
					listaCompras = comDao.selectCompra(com);
				}
			}
			listaClientes = listaClientes();
			listaProdutos = listaProdutos();
		} catch (SQLException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			request.setAttribute("listaClientes", listaClientes);
			request.setAttribute("listaProdutos", listaProdutos);
			request.setAttribute("listaCompras", listaCompras);
			RequestDispatcher rd = request.getRequestDispatcher("compra.jsp");
			rd.forward(request, response);
		}
	}

	private List<Cliente> listaClientes() throws SQLException {
		return cDao.selectClientes();
	}

	private List<Produto> listaProdutos() throws SQLException {
		return pDao.selectProdutos();
	}

	private Compra validaCampos(HttpServletRequest request, String cmd) {
		Compra com = new Compra();
		if (cmd.contains("Cadastrar")) {
			if (!request.getParameter("codigo").trim().isEmpty() &&
					!request.getParameter("qtd").trim().isEmpty() &&
					!request.getParameter("cliente").trim().equals("0") &&
					!request.getParameter("produto").trim().equals("0")) {
				Cliente cli = new Cliente();
				cli.setId(Integer.parseInt(request.getParameter("cliente")));
				
				Produto p = new Produto();
				p.setId(Integer.parseInt(request.getParameter("produto")));
				
				ProdutoComprado pc = new ProdutoComprado();
				pc.setProduto(p);
				pc.setQtd(Integer.parseInt(request.getParameter("qtd")));

				com.setCodigo(Integer.parseInt(request.getParameter("codigo")));
				com.setData(LocalDate.now().toString());
				com.setCliente(cli);
				com.setProdutoComprado(pc);
				
			}
		}
		if (cmd.contains("Buscar")) {
			if (!request.getParameter("codigo").trim().isEmpty()) {
				com.setCodigo(Integer.parseInt(request.getParameter("codigo").trim()));
			}
		}
		return com;
	}
	


}
