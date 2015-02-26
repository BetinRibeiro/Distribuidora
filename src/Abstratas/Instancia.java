package Abstratas;

public abstract class Instancia {
	protected int idProduto;
	protected float quantidade;
	protected float custoUnitario;
	
	
	public Instancia(int idProduto, float quantidade, float custoUnitario) {
		super();
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.custoUnitario = custoUnitario;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}
	public float getCustoUnitario() {
		return custoUnitario;
	}
	public void setCustoUnitario(float custoUnitario) {
		this.custoUnitario = custoUnitario;
	}

}
