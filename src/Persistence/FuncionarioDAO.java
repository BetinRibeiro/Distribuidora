package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassesBin.Funcionario;
import Conexao.Conexao;
import Interfaces.CRUD;

public class FuncionarioDAO implements CRUD {

	private Connection con = Conexao.getConnection();
	
	@Override
	public void criar(Object ob) {
		Funcionario funcionario = (Funcionario) ob;

		// montando o sql
		String sql = "INSERT INTO funcionario (nome, cpf, telefone, endereco,"
				+ " numero, bairro, cidade) " + "values ( ?, ?, ?, ?, ?, ?, ?)";
		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			String razao = funcionario.getNome();
			preparador.setString(1, razao);

			String cnpj = funcionario.getCpf();
			preparador.setString(2, cnpj);

			String telefone = funcionario.getTelefone();
			preparador.setString(3, telefone);

			String endereco = funcionario.getEndereco();
			preparador.setString(4, endereco);

			String numero = funcionario.getNumero();
			preparador.setString(5, numero);

			String bairro = funcionario.getBairro();
			preparador.setString(6, bairro);
			
			String cidade = funcionario.getCidade();
			preparador.setString(7, cidade);

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out.println("erro na classe DAO em Criar - funcionario");

		}
		
	}

	@Override
	public List<Object> buscar() {


		// montando o sql
		String sql = "SELECT * FROM funcionario ORDER BY nome";

		List<Object> lista = new ArrayList<Object>();

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();

			// impressão de visualização- resgate dos valores contidos no banco
			while (resultado.next()) {
				Integer id = resultado.getInt("id");
				System.out.println("id "+id);
				String cpf = resultado.getString("cpf");
				System.out.println("cpf"+cpf);
				String nome = resultado.getString("nome");
				System.out.println("nome "+nome);
				String telefone = resultado.getString("telefone");
				System.out.println("telefone "+telefone);
				String endereco = resultado.getString("endereco");
				System.out.println("endereco "+endereco);
				String numero = resultado.getString("numero");
				System.out.println("numero "+numero);
				String bairro = resultado.getString("bairro");
				System.out.println("bairro "+bairro);
				String cidade = resultado.getString("cidade");
				System.out.println("cidade "+cidade);

				Funcionario novoFuncionario = new Funcionario(id, telefone, endereco, numero, bairro, cidade, nome, cpf);
				lista.add(novoFuncionario);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out.println("Ocorreu um erro na classe dao buscar funcionario");

		}
		return lista;
	}

	@Override
	public void atualizar(Object ob) {
		Funcionario funcionario = (Funcionario) ob;
		String sql = "UPDATE funcionario SET nome=?, cpf=?, telefone=?, endereco=?, numero=?, bairro=?, cidade=? WHERE id=?";

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, funcionario.getNome());
			
			preparador.setString(2, funcionario.getCpf());
			
			preparador.setString(3, funcionario.getTelefone());
			
			preparador.setString(4, funcionario.getEndereco());
			
			preparador.setString(5, funcionario.getNumero());

			preparador.setString(6, funcionario.getBairro());
			
			preparador.setString(7, funcionario.getCidade());

			preparador.setInt(8, funcionario.getId());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out.println("Erro na classe DAO Atualizar - funcionario");

		}
		
	}

	@Override
	public void deletar(Object ob) {
		Funcionario funcionario = (Funcionario) ob;
		// montando o sql
		String sql = "DELETE FROM funcionario WHERE id=?";

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setInt(1, funcionario.getId());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe DAO deletar - funcionario");

		}
		
	}


}
