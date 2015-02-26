package ClassesBin;

import java.sql.Date;

import Abstratas.Movimento;


public class Venda extends Movimento{
	private String cliente;
	private float desconto;
	

	public Venda(Date data, float custo, String cliente, float desconto) {
		super(data, custo);
		this.cliente = cliente;
		this.desconto = desconto;
	}

	public Venda(int id, Date data, float custo, String cliente, float desconto) {
		super(id, data, custo);
		this.cliente = cliente;
		this.desconto = desconto;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public float getDesconto() {
		return desconto;
	}

	public void setDesconto(float desconto) {
		this.desconto = desconto;
	}
	
	
	
	

}
