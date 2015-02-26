package ClassesBin;

import Abstratas.Pessoa;

public class Funcionario extends Pessoa{
	private String nome;
	private String cpf;
	
	
	public Funcionario(int id, String telefone, String endereco, String numero,
			String bairro, String cidade, String nome, String cpf) {
		super(id, telefone, endereco, numero, bairro, cidade);
		this.nome = nome;
		this.cpf = cpf;
	}
	public Funcionario(String telefone, String endereco, String numero,
			String bairro, String cidade, String nome, String cpf) {
		super(telefone, endereco, numero, bairro, cidade);
		this.nome = nome;
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	

}
