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

import model.Cliente;
import persistence.ClienteDao;
import persistence.IClienteDao;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IClienteDao cDao;
	
	
	public ClienteServlet() {
		try {
			cDao = new ClienteDao();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("button");
		String saida = "";
		String erro = "";
//		System.out.println(cmd);
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		Cliente cli = validaCampos(request, cmd);
		try {
			if (cmd.contains("Cadastrar")) {
				if (cli != null) {
					saida = cDao.insertCliente(cli);
					cli = new Cliente();
				}
			}
			if (cmd.contains("Atualizar")) {
				if (cli != null) {
					saida = cDao.updateCliente(cli);
					cli = new Cliente();
				}
			}
			if (cmd.contains("Excluir")) {
				if (cli != null) {
					saida = cDao.deleteCliente(cli);
					cli = new Cliente();
				}
			}
			if (cmd.contains("Buscar")) {
				if (cli != null) {
					cli = cDao.selectCliente(cli);
				}
			}
			if (cmd.contains("Listar")) {
				listaClientes = cDao.selectClientes();
			}
		} catch (SQLException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("cliente", cli);
			request.setAttribute("listaClientes", listaClientes);
			RequestDispatcher rd = request.getRequestDispatcher("cliente.jsp");
			rd.forward(request, response);
		}
	}

	private Cliente validaCampos(HttpServletRequest request, String cmd) {
		Cliente cli = new Cliente();
		if (cmd.contains("Cadastrar") || cmd.contains("Atualizar")) {
			if (!request.getParameter("id").trim().isEmpty() &&
					!request.getParameter("nome").trim().isEmpty() &&
					!request.getParameter("telefone").trim().isEmpty()) {
				cli.setId(Integer.parseInt(request.getParameter("id").trim()));
				cli.setNome(request.getParameter("nome").trim());
				cli.setTelefone(request.getParameter("telefone").trim());
			}
		}
		if (cmd.contains("Excluir") || cmd.contains("Buscar")) {
			if (!request.getParameter("id").trim().isEmpty()) {
				cli.setId(Integer.parseInt(request.getParameter("id").trim()));
			}
		}
		return cli;
	}

}
