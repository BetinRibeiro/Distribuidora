package ClassesBin;

import Abstratas.Instancia;

public class ItemVenda extends Instancia {
	private int idVenda;
	private float valor;
	
	
	public ItemVenda(int idProduto, float quantidade,
			float custoUnitario, int idVenda, float valor) {
		super(idProduto, quantidade, custoUnitario);
		this.idVenda = idVenda;
		this.valor = valor;
	}
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
	

}
