package ClassesBin;

import java.sql.Date;

import Abstratas.Movimento;


public class Compra extends Movimento{
	private String fornecedor;
	private float imposto;
	

	public Compra(Date data, float custo, String fornecedor, float imposto) {
		super(data, custo);
		this.fornecedor = fornecedor;
		this.imposto = imposto;
	}

	public Compra(int id, Date data, float custo, String fornecedor, float imposto) {
		super(id, data, custo);
		this.fornecedor = fornecedor;
		this.imposto = imposto;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public float getImposto() {
		return imposto;
	}

	public void setImposto(float imposto) {
		this.imposto = imposto;
	}
	
	
	
	

}
