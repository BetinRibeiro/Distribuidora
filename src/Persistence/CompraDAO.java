package Persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import ClassesBin.Compra;
import Conexao.Conexao;
import Interfaces.CRUD;

public class CompraDAO implements CRUD {

	private Connection con = Conexao.getConnection();

	@Override
	public void criar(Object ob) {
		Compra compra = (Compra) ob;

		// montando o sql
		String sql = "INSERT INTO compra (data, valor, fornecedor, imposto) values ( ?, ?, ?, ?)";
		// contruindo o PreparedStatement com o sql
		try {
			
			//ta dando erro nessa merda provavelmente é a data verificar isso!!

			PreparedStatement preparador = con.prepareStatement(sql);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 

			Date data = Date.valueOf(format.format(compra.getData()));
			preparador.setDate(1, data);

			float valor = compra.getCusto();
			preparador.setFloat(2, valor);

			String fornecedor = compra.getFornecedor();
			preparador.setString(3, fornecedor);
			
			float imposto = compra.getImposto();
			preparador.setFloat(4, imposto);

			
			preparador.execute();
			preparador.close();
			

		} catch (SQLException e) {

			System.out.println("erro na classe DAO em Criar - compra");

		}

	}

	@Override
	public List<Object> buscar() {

		// montando o sql
		String sql = "SELECT * FROM compra ORDER BY id";

		List<Object> lista = new ArrayList<Object>();

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();

			// impressão de visualização- resgate dos valores contidos no banco
			while (resultado.next()) {
				Integer id = resultado.getInt("id");
				Date data = resultado.getDate("data");
				float valor = resultado.getFloat("valor");
				String fornecedor = resultado.getString("fornecedor");
				float imposto = resultado.getFloat("imposto");

				Compra compra = new Compra(id, data, valor, fornecedor,imposto);
				lista.add(compra);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out.println("Ocorreu um erro na classe dao buscar compra");

		}
		return lista;
	}

	@Override
	public void atualizar(Object ob) {
		Compra compra = (Compra) ob;
		String sql = "UPDATE item SET data=?, valor=?, fornecedor=?, imposto=? WHERE id=?";

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setDate(1, compra.getData());
			preparador.setFloat(2, compra.getCusto());
			preparador.setString(3, compra.getFornecedor());
			preparador.setFloat(4, compra.getImposto());
			preparador.setFloat(5, compra.getId());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out.println("Erro na classe DAO Atualizar - compra");

		}

	}

	@Override
	public void deletar(Object ob) {
		Compra compra = (Compra) ob;
		// montando o sql
		String sql = "DELETE FROM compra WHERE id=?";

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setInt(1, compra.getId());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe DAO deletar - compra");

		}

	}

	public Compra buscarPorId(Integer id) {
		// montando o sql
		String sql = "SELECT * FROM compra WHERE id=?";

		Compra compra = null;
		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {

				Date data = resultado.getDate("data");
				float valor = resultado.getFloat("valor");
				String fornecedor = resultado.getString("fornecedor");
				float imposto = resultado.getFloat("imposto");

				compra = new Compra(id, data, valor, fornecedor, imposto);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe DAO buscarPorId - compra");
		}
		return compra;
	}
	
	public Compra ultimoId() {
		String sql = "SELECT MAX(id) FROM compra";
		Compra compra= null;
		try {

			PreparedStatement preparador = con.prepareStatement(sql);
			
			
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				int id =resultado.getInt(1);

				compra = buscarPorId(id);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe DAO buscarMaiorID - compra");
		}
		
		return compra;
		
		
	}

}
