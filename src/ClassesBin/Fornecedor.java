package ClassesBin;

import Abstratas.Pessoa;

public class Fornecedor extends Pessoa {
	private String cnpj;
	private String razaoSocial;
	
	public Fornecedor(int id, String telefone, String endereco, String numero,
			String bairro, String cidade, String cnpj, String razaoSocial) {
		super(id, telefone, endereco, numero, bairro, cidade);
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
	}
	public Fornecedor(String telefone, String endereco, String numero,
			String bairro, String cidade, String cnpj, String razaoSocial) {
		super(telefone, endereco, numero, bairro, cidade);
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

}
