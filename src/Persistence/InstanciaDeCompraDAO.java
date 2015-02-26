package Persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import Conexao.Conexao;
import Interfaces.CRUD;
import ClassesBin.ItemCompra;

public class InstanciaDeCompraDAO implements CRUD {

	private Connection con = Conexao.getConnection();

	@Override
	public void criar(Object ob) {
		ItemCompra instancia = (ItemCompra) ob;

		// montando o sql
		String sql = "INSERT INTO lista_de_compra (id_compra, id_produto, quantidade, custo_unitario) "
				+ "values ( ?, ?, ?, ?)";
		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			int idCompra = instancia.getIdCompra();
			preparador.setInt(1, idCompra);
			System.out.println(idCompra);

			int idProduto = instancia.getIdProduto();
			preparador.setInt(2, idProduto);
			System.out.println(idProduto);

			float quantidade = instancia.getQuantidade();
			preparador.setFloat(3, quantidade);
			System.out.println(quantidade);

			float custoUnitario = instancia.getCustoUnitario();
			preparador.setFloat(4, custoUnitario);
			System.out.println(custoUnitario);

			System.out.println(preparador);
			preparador.execute();
			System.out.println("já executou");
			preparador.close();
			System.out.println("já feixou conexao");

		} catch (SQLException e) {

			System.out
					.println("erro na classe DAO em Criar - Lista de listaCompra");

		}

	}

	@Override
	public List<Object> buscar() {
		// montando o sql
		String sql = "SELECT * FROM lista_de_compra ORDER BY id";

		List<Object> lista = new ArrayList<Object>();

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();

			// impressão de visualização- resgate dos valores contidos no banco
			while (resultado.next()) {
				Integer idCompra = resultado.getInt("id_compra");
				Integer idProduto = resultado.getInt("id_produto");
				float quantidade = resultado.getFloat("quantidade");
				float custoUnitario = resultado.getFloat("custo_unitario");

				ItemCompra instancia = new ItemCompra(idProduto, quantidade,
						custoUnitario, idCompra);
				lista.add(instancia);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe dao buscar lista listaCompra");

		}
		return lista;
	}

	@Override
	public void atualizar(Object ob) {
		// essa função não ira necessitar no momento

	}

	@Override
	public void deletar(Object ob) {
		// Essa função não ira utilizar no momento

	}

	public List<Object> buscarCompra(int id) {
		// montando o sql
		String sql = "SELECT * FROM lista_de_compra WHERE id_compra=? ";

		List<Object> lista = new ArrayList<Object>();

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();

			// impressão de visualização- resgate dos valores contidos no banco
			while (resultado.next()) {
				Integer idCompra = resultado.getInt("id_compra");
				Integer idProduto = resultado.getInt("id_produto");
				float quantidade = resultado.getFloat("quantidade");
				float custoUnitario = resultado.getFloat("custo_unitario");

				ItemCompra instancia = new ItemCompra(idProduto, quantidade,
						custoUnitario, idCompra);
				lista.add(instancia);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe dao buscar lista listaCompra");

		}
		return lista;
	}

}
