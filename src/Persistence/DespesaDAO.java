package Persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ClassesBin.Despesa;
import Conexao.Conexao;
import Interfaces.CRUD;

public class DespesaDAO implements CRUD {

	private Connection con = Conexao.getConnection();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


	@Override
	public void criar(Object ob) {
		
		
		Despesa despesa = (Despesa) ob;

		// montando o sql
		String sql = "INSERT INTO despesa (descricao, data, valor, classificacao) values ( ?, ?, ?, ?)";
		// contruindo o PreparedStatement com o sql
		try {

			// ta dando erro nessa merda provavelmente é a data verificar isso!!

			PreparedStatement preparador = con.prepareStatement(sql);

			String descricao = despesa.getDescricao();
			preparador.setString(1, descricao);
			System.out.println(descricao);
			System.out.println(format);

			Date data = Date.valueOf(format.format(despesa.getData()));
			preparador.setDate(2, data);
			System.out.println(data);
			
			float valor = despesa.getValor();
			preparador.setFloat(3, valor);
			System.out.println(valor);
			
			String classificacao = despesa.getClassificacao();
			preparador.setString(4, classificacao);
			System.out.println(classificacao);

			System.out.println(preparador);
			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out.println("erro na classe DAO em Criar - compra");

		}

	}

	@Override
	public List<Object> buscar() {

		// montando o sql
		String sql = "SELECT * FROM despesa ORDER BY data";

		List<Object> lista = new ArrayList<Object>();

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();

			// impressão de visualização- resgate dos valores contidos no banco
			while (resultado.next()) {
				Integer id = resultado.getInt("id");
				String descricao = resultado.getString("descricao");
				Date data = resultado.getDate("data");
				float valor = resultado.getFloat("valor");
				String classificacao = resultado.getString("classificacao");

				Despesa despesa = new Despesa(id, descricao, data, valor, classificacao);
				lista.add(despesa);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out.println("Ocorreu um erro na classe dao buscar compra");

		}
		return lista;
	}

	@Override
	public void atualizar(Object ob) {
		Despesa despesa = (Despesa) ob;
		String sql = "UPDATE despesa SET descricao=?, data=?, valor=?, classificacao=? WHERE id=?";

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, despesa.getDescricao());
			
			preparador.setDate(2, Date.valueOf(format.format(despesa.getData())));
			
			preparador.setFloat(3, despesa.getValor());
			
			preparador.setString(4, despesa.getClassificacao());


			preparador.setInt(5, despesa.getId());
			System.out.println(preparador);

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out.println("Erro na classe DAO Atualizar - despesa");

		}
	}

	@Override
	public void deletar(Object ob) {
		Despesa despesa = (Despesa) ob;
		// montando o sql
		String sql = "DELETE FROM despesa WHERE id=?";

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setInt(1, despesa.getId());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe DAO deletar - despesa");

		}

	}

	public Despesa buscarPorId(Integer id) {
		// não ha a necessidade de implementar isso no momento
		
		return null;
	}

}
