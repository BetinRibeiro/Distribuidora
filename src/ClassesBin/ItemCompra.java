package ClassesBin;

import Abstratas.Instancia;

public class ItemCompra extends Instancia{
	private int idCompra;
	
	
	public ItemCompra(int idProduto, float quantidade,
			float custoUnitario, int idCompra) {
		super(idProduto, quantidade, custoUnitario);
		this.idCompra = idCompra;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	
	

}
