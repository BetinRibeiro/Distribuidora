package Abstratas;

public abstract class Pessoa {
	protected int id;
	protected String telefone;
	protected String endereco;
	protected String numero;
	protected String bairro;
	protected String cidade;
	
	
	public Pessoa( String telefone, String endereco, String numero,
			String bairro, String cidade) {
		super();
		this.telefone = telefone;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
	}
	public Pessoa(int id,  String telefone, String endereco,
			String numero, String bairro, String cidade) {
		super();
		this.id = id;
		this.telefone = telefone;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
