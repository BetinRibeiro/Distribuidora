package ClassesBin;

import Abstratas.Pessoa;

public class Cliente extends Pessoa{
	private String nome;
	
	
	
	public Cliente(int id, String telefone, String endereco, String numero,
			String bairro, String cidade, String nome) {
		super(id, telefone, endereco, numero, bairro, cidade);
		this.setNome(nome);
	}

	public Cliente(String telefone, String endereco, String numero,
			String bairro, String cidade, String nome) {
		super(telefone, endereco, numero, bairro, cidade);
		this.setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
