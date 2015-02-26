package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassesBin.Cliente;
import Conexao.Conexao;
import Interfaces.CRUD;

public class ClienteDAO implements CRUD {

	private Connection con = Conexao.getConnection();

	@Override
	public void criar(Object ob) {
		Cliente cliente = (Cliente) ob;

		// montando o sql
		String sql = "INSERT INTO cliente (nome, telefone, endereco, numero,"
				+ " bairro, cidade) " + "values ( ?, ?, ?, ?, ?, ?)";
		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			String nome = cliente.getNome();
			preparador.setString(1, nome);

			String telefone = cliente.getTelefone();
			preparador.setString(2, telefone);

			String end = cliente.getEndereco();
			preparador.setString(3, end);

			String numero = cliente.getNumero();
			preparador.setString(4, numero);

			String bairro = cliente.getBairro();
			preparador.setString(5, bairro);

			String cidade = cliente.getCidade();
			preparador.setString(6, cidade);

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out.println("erro na classe DAO em Criar - cliente");

		}

	}

	@Override
	public List<Object> buscar() {
		

		// montando o sql
		String sql = "SELECT * FROM cliente ORDER BY nome";

		List<Object> lista = new ArrayList<Object>();

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();

			// impressão de visualização- resgate dos valores contidos no banco
			while (resultado.next()) {
				Integer id = resultado.getInt("id");
				String nome = resultado.getString("nome");
				String telefone = resultado.getString("telefone");
				String endereco = resultado.getString("endereco");
				String numero = resultado.getString("numero");
				String bairro = resultado.getString("bairro");
				String cidade = resultado.getString("cidade");

				Cliente novoCliente = new Cliente(id, telefone, endereco, numero, bairro, cidade, nome);
				lista.add(novoCliente);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out.println("Ocorreu um erro na classe dao buscar cliente");

		}
		return lista;
	}

	@Override
	public void atualizar(Object ob) {
		Cliente cliente = (Cliente) ob;
		String sql = "UPDATE cliente SET nome=?, telefone=?, endereco=?, numero=?, bairro=?, cidade=? WHERE id=?";

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, cliente.getNome());
			
			preparador.setString(2, cliente.getTelefone());
			
			preparador.setString(3, cliente.getEndereco());
			
			preparador.setString(4, cliente.getNumero());

			preparador.setString(5, cliente.getBairro());
			
			preparador.setString(6, cliente.getCidade());

			preparador.setInt(7, cliente.getId());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out.println("Erro na classe DAO Atualizar - cliente");

		}

	}

	@Override
	public void deletar(Object ob) {
		Cliente cliente = (Cliente) ob;
		// montando o sql
		String sql = "DELETE FROM cliente WHERE id=?";

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setInt(1, cliente.getId());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe DAO deletar - cliente");

		}

	}

	public void salvar(Object ob) {
		Cliente cliente = (Cliente) ob;
		if (cliente.getId() != 0) {
			criar(cliente);
		} else {
			atualizar(cliente);
		}
	}

	public Cliente buscarPorId(Integer id) {
		// montando o sql
		String sql = "SELECT * FROM cliente WHERE id=?";

		Cliente cliente = null;

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {

				String nome = resultado.getString("nome");
				String telefone = resultado.getString("telefone");
				String endereco = resultado.getString("endereco");
				String numero = resultado.getString("numero");
				String bairro = resultado.getString("bairro");
				String cidade = resultado.getString("cidade");

				cliente = new Cliente(id, telefone, endereco, numero, bairro, cidade, nome);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe DAO buscarPorId - cliente");
		}
		return cliente;
	}

	@SuppressWarnings("null")
	public List<Cliente> buscarPorNome(String nome) {
		// montando o sql
		String sql = "SELECT * FROM cliente WHERE nome %like% ?";

		List<Cliente> lista = null;

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, nome);

			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {

				int id = resultado.getInt("id");
				String telefone = resultado.getString("telefone");
				String endereco = resultado.getString("endereco");
				String numero = resultado.getString("numero");
				String bairro = resultado.getString("bairro");
				String cidade = resultado.getString("cidade");

				Cliente cliente = new Cliente(id, nome, telefone, endereco,
						numero, bairro, cidade);
				lista.add(cliente);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe dao busca por nome cliente");

		}
		return lista;
	}

}