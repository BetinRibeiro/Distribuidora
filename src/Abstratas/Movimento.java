package Abstratas;

import java.sql.Date;

public abstract class Movimento {
	protected int id;
	protected  Date data;
	protected  float custo;
	
	
	public Movimento(Date data, float custo) {
		super();
		this.data = data;
		this.custo = custo;
	}
	public Movimento(int id, Date data, float custo) {
		super();
		this.id = id;
		this.data = data;
		this.custo = custo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public float getCusto() {
		return custo;
	}
	public void setCusto(float custo) {
		this.custo = custo;
	}

	
	
}
