package ClassesBin;

import java.sql.Date;

public class Despesa {
	private int id;
	private String descricao;
	private Date data;
	private float valor;
	private String classificacao;
	
	
	
	public Despesa(String descricao, 
			String classificacao) {
		super();
		this.descricao = descricao;
		this.classificacao = classificacao;
		this.data = null;
	}
	public Despesa(int id, String descricao, Date data, float valor,
			String classificacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
		this.classificacao = classificacao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	
	
	

}
