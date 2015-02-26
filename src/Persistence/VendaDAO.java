package Persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



import ClassesBin.Venda;
import Conexao.Conexao;
import Interfaces.CRUD;

public class VendaDAO implements CRUD {

	private Connection con = Conexao.getConnection();

	@Override
	public void criar(Object ob) {
		Venda venda = (Venda) ob;

		// montando o sql
		String sql = "INSERT INTO venda (data, valor, cliente, desconto) values ( ?, ?, ?, ?)";
		// contruindo o PreparedStatement com o sql
		try {
			

			PreparedStatement preparador = con.prepareStatement(sql);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 

			Date data = Date.valueOf(format.format(venda.getData()));
			preparador.setDate(1, data);

			float valor = venda.getCusto();
			preparador.setFloat(2, valor);

			String cliente = venda.getCliente();
			preparador.setString(3, cliente);
			
			float desconto = venda.getDesconto();
			preparador.setFloat(4, desconto);

			
			preparador.execute();
			preparador.close();
			

		} catch (SQLException e) {

			System.out.println("erro na classe DAO em Criar - venda ");

		}

	}

	@Override
	public List<Object> buscar() {

		// montando o sql
		String sql = "SELECT * FROM venda ORDER BY id";

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
				String cliente = resultado.getString("cliente");
				float desconto = resultado.getFloat("desconto");

				Venda venda = new Venda(id, data, valor, cliente,desconto);
				lista.add(venda);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out.println("Ocorreu um erro na classe dao buscar venda");

		}
		return lista;
	}

	@Override
	public void atualizar(Object ob) {
		Venda venda = (Venda) ob;
		String sql = "UPDATE item SET data=?, valor=?, cliente=?, desconto=? WHERE id=?";

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setDate(1, venda.getData());
			preparador.setFloat(2, venda.getCusto());
			preparador.setString(3, venda.getCliente());
			preparador.setFloat(4, venda.getDesconto());
			preparador.setFloat(5, venda.getId());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out.println("Erro na classe DAO Atualizar - venda");

		}

	}

	@Override
	public void deletar(Object ob) {
		Venda venda = (Venda) ob;
		// montando o sql
		String sql = "DELETE FROM venda WHERE id=?";

		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setInt(1, venda.getId());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe DAO deletar - venda");

		}

	}

	public Venda buscarPorId(Integer id) {
		// montando o sql
		String sql = "SELECT * FROM venda WHERE id=?";

		Venda venda = null;
		// contruindo o PreparedStatement com o sql
		try {

			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {

				Date data = resultado.getDate("data");
				float valor = resultado.getFloat("valor");
				String cliente = resultado.getString("cliente");
				float desconto = resultado.getFloat("desconto");

				venda = new Venda(id, data, valor, cliente, desconto);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe DAO buscarPorId - venda");
		}
		return venda;
	}
	
	public Venda ultimoId() {
		String sql = "SELECT MAX(id) FROM venda";
		Venda venda= null;
		try {

			PreparedStatement preparador = con.prepareStatement(sql);
			
			
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				int id =resultado.getInt(1);

				venda = buscarPorId(id);
			}

			preparador.close();

		} catch (SQLException e) {

			System.out
					.println("Ocorreu um erro na classe DAO buscarMaiorID - venda");
		}
		
		return venda;
		
		
	}

}
