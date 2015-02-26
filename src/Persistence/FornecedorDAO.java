package Persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassesBin.Compra;
import ClassesBin.Fornecedor;
import Conexao.Conexao;
import Interfaces.CRUD;

public class FornecedorDAO implements CRUD{

	private Connection con = Conexao.getConnection();
	
	@Override
	public void criar(Object ob) {
		Fornecedor fornecedor = (Fornecedor) ob;

		// montando o sql
		String sql = "INSERT INTO fornecedor (razao_social, cnpj, telefone, endereco,"
				+ " numero, bairro, cidade) " + "values ( ?, ?, ?, ?, ?, ?, ?)";
		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			String razao = fornecedor.getRazaoSocial();
			preparador.setString(1, razao);

			String cnpj = fornecedor.getCnpj();
			preparador.setString(2, cnpj);

			String telefone = fornecedor.getTelefone();
			preparador.setString(3, telefone);

			String endereco = fornecedor.getEndereco();
			preparador.setString(4, endereco);

			String numero = fornecedor.getNumero();
			preparador.setString(5, numero);

			String bairro = fornecedor.getBairro();
			preparador.setString(6, bairro);
			
			String cidade = fornecedor.getCidade();
			preparador.setString(7, cidade);

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out.println("erro na classe DAO em Criar - fornecedor");

		}
		
	}

	@Override
	public List<Object> buscar() {


		// montando o sql
		String sql = "SELECT * FROM fornecedor ORDER BY razao_social";

		List<Object> lista = new ArrayList<Object>();

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();

			// impressão de visualização- resgate dos valores contidos no banco
			while (resultado.next()) {
				Integer id = resultado.getInt("id");
				String cnpj = resultado.getString("cnpj");
				String razaoSocial = resultado.getString("razao_social");
				String telefone = resultado.getString("telefone");
				String endereco = resultado.getString("endereco");
				String numero = resultado.getString("numero");
				String bairro = resultado.getString("bairro");
				String cidade = resultado.getString("cidade");

				Fornecedor novoCliente = new Fornecedor(id, telefone, endereco, numero, bairro, cidade, cnpj, razaoSocial);
				lista.add(novoCliente);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out.println("Ocorreu um erro na classe dao buscar fornecedor");

		}
		return lista;
	}

	@Override
	public void atualizar(Object ob) {
		Fornecedor fornecedor = (Fornecedor) ob;
		String sql = "UPDATE fornecedor SET razao_social=?, cnpj=?, telefone=?, endereco=?, numero=?, bairro=?, cidade=? WHERE id=?";

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, fornecedor.getRazaoSocial());
			
			preparador.setString(2, fornecedor.getCnpj());
			
			preparador.setString(3, fornecedor.getTelefone());
			
			preparador.setString(4, fornecedor.getEndereco());
			
			preparador.setString(5, fornecedor.getNumero());

			preparador.setString(6, fornecedor.getBairro());
			
			preparador.setString(7, fornecedor.getCidade());

			preparador.setInt(8, fornecedor.getId());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out.println("Erro na classe DAO Atualizar - fornecedor");

		}
		
	}

	@Override
	public void deletar(Object ob) {
		Fornecedor fornecedor = (Fornecedor) ob;
		// montando o sql
		String sql = "DELETE FROM fornecedor WHERE id=?";

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setInt(1, fornecedor.getId());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe DAO deletar - fornecedor");

		}
		
	}

	public Fornecedor buscarPorId(int id) {
		// montando o sql
		String sql = "SELECT * FROM fornecedor WHERE id=?";

		Fornecedor fornecedor = null;
		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {

				String  telefone = resultado.getString("telefone");
				String  endereco = resultado.getString("endereco");
				String  numero = resultado.getString("numero");
				String  bairro = resultado.getString("bairro");
				String  cidade = resultado.getString("cidade");
				String  cnpj = resultado.getString("cnpj");
				String  razaoSocial = resultado.getString("razao_social");
				

				fornecedor = new Fornecedor(id, telefone, endereco, numero, bairro, cidade, cnpj, razaoSocial);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe DAO buscarPorId - compra");
		}
		return fornecedor;
	}

}
