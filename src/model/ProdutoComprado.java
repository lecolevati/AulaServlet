package model;

public class ProdutoComprado {

	private Produto produto;
	private int qtd;
	private float valorTotal;
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "ProdutoComprado [produto=" + produto + ", qtd=" + qtd + ", valorTotal=" + valorTotal + "]";
	}
	
}
