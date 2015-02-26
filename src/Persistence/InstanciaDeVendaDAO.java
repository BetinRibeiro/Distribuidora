package Persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import Conexao.Conexao;
import Interfaces.CRUD;
import ClassesBin.ItemVenda;

public class InstanciaDeVendaDAO implements CRUD {

	private Connection con = Conexao.getConnection();

	@Override
	public void criar(Object ob) {
		ItemVenda instancia = (ItemVenda) ob;

		// montando o sql
		String sql = "INSERT INTO lista_de_venda (id_venda, id_produto, quantidade, valor, custo) "
				+ "values ( ?, ?, ?, ?,?)";
		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			int idVenda = instancia.getIdVenda();
			preparador.setInt(1, idVenda);
			System.out.println(idVenda);

			int idProduto = instancia.getIdProduto();
			preparador.setInt(2, idProduto);
			System.out.println(idProduto);

			float quantidade = instancia.getQuantidade();
			preparador.setFloat(3, quantidade);
			System.out.println(quantidade);
			
			float valor = instancia.getValor();
			preparador.setFloat(3, valor);
			System.out.println(valor);

			float custoUnitario = instancia.getCustoUnitario();
			preparador.setFloat(5, custoUnitario);
			System.out.println(custoUnitario);

			System.out.println(preparador);
			preparador.execute();
			System.out.println("já executou");
			preparador.close();
			System.out.println("já feixou conexao");

		} catch (SQLException e) {

			System.out
					.println("erro na classe DAO em Criar - Lista de listaVenda");

		}

	}

	@Override
	public List<Object> buscar() {
		// montando o sql
		String sql = "SELECT * FROM lista_de_venda ORDER BY id";

		List<Object> lista = new ArrayList<Object>();

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();

			// impressão de visualização- resgate dos valores contidos no banco
			while (resultado.next()) {
				Integer idVenda = resultado.getInt("id_venda");
				Integer idProduto = resultado.getInt("id_produto");
				float quantidade = resultado.getFloat("quantidade");
				float custoUnitario = resultado.getFloat("custo_unitario");
				float valor = resultado.getFloat("valor");

				ItemVenda instancia = new ItemVenda(idProduto, quantidade,
						custoUnitario, idVenda, valor);
				lista.add(instancia);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe dao buscar lista listaVenda");

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

	public List<Object> buscarVenda(int id) {
		// montando o sql
		String sql = "SELECT * FROM lista_de_venda WHERE id_venda=? ";

		List<Object> lista = new ArrayList<Object>();

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();

			// impressão de visualização- resgate dos valores contidos no banco
			while (resultado.next()) {
				Integer idVenda = resultado.getInt("id_venda");
				Integer idProduto = resultado.getInt("id_produto");
				float quantidade = resultado.getFloat("quantidade");
				float custoUnitario = resultado.getFloat("custo_unitario");
				float valor = resultado.getFloat("valor");

				ItemVenda instancia = new ItemVenda(idProduto, quantidade,
						custoUnitario, idVenda, valor);
				lista.add(instancia);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe dao buscar lista listaVenda");

		}
		return lista;
	}

	public List<Object> buscaVenda(int id) {
		// montando o sql
				String sql = "SELECT * FROM lista_de_venda WHERE id_venda=? ";

				List<Object> lista = new ArrayList<Object>();

				// contruindo o PreparedStatement com o sql
				try {

					PreparedStatement preparador = con.prepareStatement(sql);
					preparador.setInt(1, id);

					ResultSet resultado = preparador.executeQuery();

					// impressão de visualização- resgate dos valores contidos no banco
					while (resultado.next()) {
						Integer idVenda = resultado.getInt("id_venda");
						Integer idProduto = resultado.getInt("id_produto");
						float quantidade = resultado.getFloat("quantidade");
						float custoUnitario = resultado.getFloat("custo_unitario");
						float valor = resultado.getFloat("valor");

						ItemVenda instancia = new ItemVenda(idProduto, quantidade,
								custoUnitario, idVenda, valor);
						lista.add(instancia);
					}

					preparador.close();

				} catch (SQLException e) {

					System.out
							.println("Ocorreu um erro na classe dao buscar lista listaVenda");

				}
				return lista;
	}

}
