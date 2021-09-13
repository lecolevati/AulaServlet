package model;

public class Compra {

	private int codigo;
	private String data;
	private Cliente cliente;
	private ProdutoComprado produtoComprado;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ProdutoComprado getProdutoComprado() {
		return produtoComprado;
	}
	public void setProdutoComprado(ProdutoComprado produtoComprado) {
		this.produtoComprado = produtoComprado;
	}

	@Override
	public String toString() {
		return "Compra [codigo=" + codigo + ", data=" + data + ", cliente=" + cliente + ", produtoComprado="
				+ produtoComprado + "]";
	}
	
}
