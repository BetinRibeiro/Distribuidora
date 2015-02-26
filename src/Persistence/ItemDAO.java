package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassesBin.Item;
import Conexao.Conexao;
import Interfaces.CRUD;


public class ItemDAO implements CRUD{
	
	private Connection con = Conexao.getConnection();

	@Override
	public void criar(Object ob) {
		Item item = (Item) ob;
		
			//montando o sql 
			String sql = "INSERT INTO item (descricao, fornecedor, local, estoquemin,"
					+ " quantidade, custo, preco, desconto) "
					+ "values ( ?, ?, ?, ?, ?, ?, ?, ?)";
			//contruindo o PreparedStatement com o sql 
			try {
				
				PreparedStatement preparador = con.prepareStatement(sql);
				
				String descricao = item.getDescricao();
				preparador.setString(1, descricao);
				System.out.println(descricao);
				
				String fornecedor = item.getFornecedor();
				preparador.setString(2, fornecedor);
				System.out.println(fornecedor);
				
				String local = item.getLocal();
				preparador.setString(3, local);
				System.out.println(local);
				
				float estoquemin = item.getEstoqueMin();
				preparador.setFloat(4, estoquemin);
				System.out.println(estoquemin);
				
				float quantidade = item.getQuantidade();
				preparador.setFloat(5, quantidade);
				System.out.println(quantidade);
				
				
				float custo = item.getCusto();
				preparador.setFloat(6, custo);
				System.out.println(custo);
				
				float preco = item.getPreco();
				preparador.setFloat(7, preco);
				System.out.println(preco);
				
				float desconto = item.getDesconto();
				preparador.setFloat(8, desconto);
				System.out.println(desconto);
				
				
				preparador.execute();
				preparador.close();
				
			} catch (SQLException e) {
				
				System.out.println("erro na classe DAO em Criar - item");
				
			}
		
		
	}

	@Override
	public List<Object> buscar() {
		
			//montando o sql
			String sql = "SELECT * FROM item ORDER BY descricao";
					
			List<Object> lista = new  ArrayList<Object>();
					
			//contruindo o PreparedStatement com o sql 
			try {
				
				PreparedStatement preparador = con.prepareStatement(sql);
							
				ResultSet resultado = preparador.executeQuery();
				
				//impressão de visualização- resgate dos valores contidos no banco
				while(resultado.next()){
					Integer id = resultado.getInt("id");
					String descricao = resultado.getString("descricao");
					String fornecedor = resultado.getString("fornecedor");
					String local = resultado.getString("local");
					float estoquemin = resultado.getFloat("estoquemin");
					float quantidade = resultado.getFloat("quantidade");
					float custo = resultado.getFloat("custo");
					float preco = resultado.getFloat("preco");
					float desconto = resultado.getFloat("desconto");
					
					Item item = new Item(id, descricao, fornecedor, local, estoquemin, quantidade, custo, preco, desconto);
					lista.add(item);
				}
				
				preparador.close();
				
			} catch (SQLException e) {
				
				System.out.println("Ocorreu um erro na classe dao buscar item");
				
			}
			return lista;
	}

	@Override
	public void atualizar(Object ob) {
		Item item = (Item) ob;
			String sql = "UPDATE item SET descricao=?, fornecedor=?, local=?, estoquemin=?, quantidade=?, custo=?, preco=?, desconto=? WHERE id=?";
			
			//contruindo o PreparedStatement com o sql 
			try {
				
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, item.getDescricao());
				System.out.println(item.getDescricao());
				preparador.setString(2, item.getFornecedor());
				System.out.println(item.getFornecedor());
				preparador.setString(3, item.getLocal());
				System.out.println(item.getLocal());
				preparador.setFloat(4, item.getEstoqueMin());
				System.out.println(item.getEstoqueMin());
				preparador.setFloat(5, item.getQuantidade());
				System.out.println(item.getQuantidade());
				preparador.setFloat(6, item.getCusto());
				System.out.println(item.getCusto());
				preparador.setFloat(7, item.getPreco());
				System.out.println(item.getPreco());
				preparador.setFloat(8, item.getDesconto());
				System.out.println(item.getDesconto());
				preparador.setInt(9, item.getId());
				System.out.println(item.getId());
				preparador.execute();
				preparador.close();
				
			} catch (SQLException e) {
				
				System.out.println("Erro na classe DAO Atualizar - item");
				
			}
		
	}

	@Override
	public void deletar(Object ob) {
		Item item = (Item) ob;
			//montando o sql
			String sql = "DELETE FROM item WHERE id=?";
			
			//contruindo o PreparedStatement com o sql 
			try {
				
				PreparedStatement preparador = con.prepareStatement(sql);
				
				preparador.setInt(1, item.getId());
				
				preparador.execute();
				preparador.close();
				
			} catch (SQLException e) {
				
				System.out.println("Ocorreu um erro na classe DAO deletar - item");
				
			}
		
	}
	
		
	public void salvar(Object ob){
		Item item = (Item) ob;
		if(item.getId()!=0){
			criar(item);
		}else{
			atualizar(item);
		}
	}
	
	
	
	public Item buscarPorId(Integer id){
		//montando o sql
		String sql = "SELECT * FROM item WHERE id=?";
		
		Item item = null;		
		//contruindo o PreparedStatement com o sql 
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			
			ResultSet resultado = preparador.executeQuery();
			if(resultado.next()){
				
				String descricao = resultado.getString("descricao");
				String fornecedor = resultado.getString("fornecedor");
				String local = resultado.getString("local");
				float estoquemin = resultado.getFloat("estoquemin");
				float quantidade = resultado.getFloat("quantidade");
				float custo = resultado.getFloat("custo");
				float preco = resultado.getFloat("preco");
				float desconto = resultado.getFloat("desconto");
				
				item = new Item(id, descricao, fornecedor, local, estoquemin, quantidade, custo, preco, desconto);
			}
						
			preparador.close();
			
		} catch (SQLException e) {
			
			System.out.println("Ocorreu um erro na classe DAO buscarPorId - item");
		}
		return item;
	}
	
	@SuppressWarnings("null")
	public List<Item> buscarPorNome (String nome){
		//montando o sql
		String sql = "SELECT * FROM item WHERE nome %like% ?";
		
		List<Item> lista = null;
		
		//contruindo o PreparedStatement com o sql 
		try {
			
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, nome);
			
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				
				Integer id = resultado.getInt("id");
				String descricao = resultado.getString("descricao");
				String fornecedor = resultado.getString("fornecedor");
				String local = resultado.getString("local");
				float estoquemin = resultado.getFloat("estoquemin");
				float quantidade = resultado.getFloat("quantidade");
				float custo = resultado.getFloat("custo");
				float preco = resultado.getFloat("preco");
				float desconto = resultado.getFloat("desconto");
				
				Item item = new Item(id, descricao, fornecedor, local, estoquemin, quantidade, custo, preco, desconto);
				lista.add(item);
			}
						
			preparador.close();
			
		} catch (SQLException e) {
			
			System.out.println("Ocorreu um erro na classe dao busca por nome item");
			
		}
		return lista;
	}

	

	}