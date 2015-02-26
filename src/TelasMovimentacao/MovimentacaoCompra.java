package TelasMovimentacao;

import java.awt.Font;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import ClassesBin.Fornecedor;
import ClassesBin.Item;
import ClassesBin.ItemCompra;
import Controller.ModelParaCompra;
import EstruturaVisual.Movimentacao;
import Persistence.CompraDAO;
import Persistence.FornecedorDAO;
import Persistence.InstanciaDeCompraDAO;
import Persistence.ItemDAO;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class MovimentacaoCompra extends Movimentacao {

	// para poder fazer alterações no banco
	private CompraDAO bancoCompra = new CompraDAO();
	// essa classe é o bin de compra !!
	private ClassesBin.Compra compraCarregada = null;
	private InstanciaDeCompraDAO bancoItensCompra = new InstanciaDeCompraDAO();
	private ModelParaCompra modelCompra = new ModelParaCompra();
	private ItemDAO bancoItens = new ItemDAO();
	private FornecedorDAO bancoFornecedor = new FornecedorDAO();
	private JTable tableProdutos;
	private float totalDaCompra;
	private List<Object> listaFornecedores;
	private List<Object> listaProdutos;
	protected List<Item> listaItensCompra = new ArrayList<Item>();
	protected SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

	public MovimentacaoCompra() {
		txtImpostoDesconto.addKeyListener(new KeyAdapter() {
			@Override
			// esse codigo troca a virgula pelo ponto para que não haja erro na
			// hora dos calculos matematicos e o armazenamento no banco
			public void keyReleased(KeyEvent e) {
				// esse evento não deixa o usuario digitar letras, ao digitar o
				// sistema apaga automaticamente
				if ((e.getKeyCode() < 48 || e.getKeyCode() > 57)
						&& (e.getKeyCode() < 96 || e.getKeyCode() > 105)
						&& (e.getKeyCode() != 8 && e.getKeyCode() != 46))
					// && (e.getKeyCode() !=110 && e.getKeyCode() != 10)
					if (txtImpostoDesconto.getText().length() > 0)
						txtImpostoDesconto
								.setText(txtImpostoDesconto.getText()
										.substring(
												0,
												txtImpostoDesconto.getText()
														.length() - 1));

				if (e.getKeyCode() == 44)
					txtImpostoDesconto.setText(txtImpostoDesconto.getText()
							+ ".");
				if (e.getKeyCode() == 110)
					txtImpostoDesconto.setText(txtImpostoDesconto.getText()
							+ ".");

			}
		});
		btnPosterior.setEnabled(false);
		txtData.setHorizontalAlignment(SwingConstants.CENTER);
		txtData.setFont(new Font("Roboto Cn", Font.PLAIN, 18));
		lblTituloDoPainel.setText("Compra");
		lblPessoa.setText("Fornecedor");
		lblValorAdicional.setText("Imposto");
		setTitle("Compra de Produtos");

		btnFinalizarMovimento.setText("Comprar");

		carregaExibicao(0);// passo zero como parametro por que aqui devemos
							// passar o id
		// mas quando se passa zero ele retorna o ultimo id colocado

		tableProdutos = new JTable(modelCompra);
		tableProdutos.setFont(new Font("Roboto Cn", Font.PLAIN, 12));
		tableProdutos.setBounds(0, 0, 1, 1);
		scrollPane.setColumnHeaderView(tableProdutos);
		scrollPane.setViewportView(tableProdutos);
		tableProdutos.setVisible(true);
		tableProdutos.setEnabled(true);
		tableProdutos.getColumn("Descrição").setPreferredWidth(205);
		tableProdutos.getTableHeader().setReorderingAllowed(false);

		carregaBoxProdutos();

		carregaBoxFornecedores();
	}

	// função ok
	private void carregaBoxFornecedores() {
		// remove todos os sandos do box pessoal
		boxPessoa.removeAllItems();

		List<Object> listaNew = bancoFornecedor.buscar();
		// popula o box pessoa apartir de uma busca no banco
		// isso futuramente pode dar problema no desenpenho do sistema
		for (int i = 0; i < listaNew.size(); i++) {
			Fornecedor fornecedor = (Fornecedor) listaNew.get(i);
			boxPessoa.addItem(fornecedor.getRazaoSocial());

		}
		listaFornecedores = listaNew;

	}

	// função ok
	private void carregaBoxProdutos() {
		// remove os dados do box de produto
		boxProdutos.removeAllItems();

		List<Object> listaNew = bancoItens.buscar();
		// popula o box de produtos apartir de uma pesquisa no banco de dados
		for (int i = 0; i < listaNew.size(); i++) {
			Item item = (Item) listaNew.get(i);
			boxProdutos.addItem(item.getDescricao());

		}
		listaProdutos = listaNew;

	}

	// função ok
	public void campoTextoBotaoEnable(Boolean b) {
		//ativa ou desativa os botões apartir do parametro passado
		txtImpostoDesconto.setEnabled(b);
		boxPessoa.setEnabled(b);
		boxProdutos.setEnabled(b);
		btnAlterarMovimento.setEnabled(b);
		btnAlterarQuantidade.setEnabled(b);
		btnAnterior.setEnabled(b);
		btnCancelar.setEnabled(b);
		btnFinalizarInsersão.setEnabled(b);
		btnFinalizarMovimento.setEnabled(b);
		btnInserir.setEnabled(b);
		btnNovoMovimento.setEnabled(b);
		btnPosterior.setEnabled(b);
		btnRemoverProduto.setEnabled(b);
		btnRetornarSairPagina.setEnabled(b);
	}

	public void criaObjeto() {
		try {

			JOptionPane.showMessageDialog(null,
					"A compra ficará cadastrada com data de hoje! ");
			Date data = new java.sql.Date(new java.util.Date().getTime());

			ClassesBin.Compra compra = new ClassesBin.Compra(data,
					Float.parseFloat(txtTotalMovimento.getText()),
					bancoFornecedor.buscarPorId(boxPessoa.getSelectedIndex())
							.getRazaoSocial(),
					Float.parseFloat(txtImpostoDesconto.getText()));
			bancoCompra.criar(compra);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Verifique a integridade dos dados fornecidos!!");
		}
	}

	// função ok
	public void limpaTexto() {
		txtData.setText("");
		txtId.setText("");
		txtImpostoDesconto.setText("");
		txtTotalMovimento.setText("");
		modelCompra.removeTudo();

	}

	// tenho que trabalhar melhor essa função
	//se o parametro for um id valido ele carrega o objeto
	//caso contrario ele simplesmente busca o ultimo elemento 
	//e apresenta, por que o carregamento padrão é o ultimo
	public void carregaExibicao(int e) {

		// dependendo do parametro ele decide se ele busca o umtimo elemento
		// introduzido
		// ou se ele busca um id normalmente;
		if (e == 0) {
			btnAnterior.setEnabled(true);
			btnPosterior.setEnabled(true);
			e = bancoCompra.ultimoId().getId();
			compraCarregada = bancoCompra.buscarPorId(e);
			idMovimentoAtual = e;

		}

		if (e >= bancoCompra.ultimoId().getId()) {
			btnPosterior.setEnabled(false);
			e = bancoCompra.ultimoId().getId();

		}

		else {

			while (bancoCompra.buscarPorId(e) == null) {
				if (sentido == -1) {
					e = e - 1;

					if (e <= 0) {
						btnAnterior.setEnabled(false);
						break;

					}

				}
				if (sentido == 1) {
					e = e + 1;
					if (e >= bancoCompra.ultimoId().getId()) {
						JOptionPane.showMessageDialog(null,
								"Não existe compra Posteriores!!");
						e = bancoCompra.ultimoId().getId();
						idMovimentoAtual = e;

					}

				}

			}
		}
		compraCarregada = bancoCompra.buscarPorId(e);

		List<Object> lista = bancoItensCompra.buscarCompra(e);

		modelCompra.removeTudo();

		for (int i = 0; i < lista.size(); i++) {

			ItemCompra produto = (ItemCompra) lista.get(i);

			int id = produto.getIdProduto();
			String descricao = bancoItens.buscarPorId(id).getDescricao();
			float quantidade = produto.getQuantidade();
			System.out.println("Quantidade: " + quantidade);

			float preco = produto.getCustoUnitario();
			System.out.println(preco);

			Item produtoReal = new Item(id, descricao, quantidade, preco);
			modelCompra.addRow(produtoReal);

		}

		if (bancoCompra.buscarPorId(e) != null) {
			txtId.setText(String.valueOf(compraCarregada.getId()));
			txtData.setText(String.valueOf(dt.format(bancoCompra.buscarPorId(e)
					.getData())));
			txtImpostoDesconto.setText(String.valueOf(bancoCompra
					.buscarPorId(e).getImposto()));
			txtTotalMovimento.setText(String.valueOf(bancoCompra.buscarPorId(e)
					.getCusto()));

		}

	}

	// função ok
	@Override
	public void carregaItenAnterrior() {
		idMovimentoAtual = idMovimentoAtual - 1;
		carregaExibicao(idMovimentoAtual);

	}

	// função ok
	@Override
	public void carregaItemPosterior() {
		idMovimentoAtual = idMovimentoAtual + 1;
		carregaExibicao(idMovimentoAtual);
		btnAnterior.setEnabled(true);
		btnFinalizarMovimento.setText("Salvar");

	}

	// função ok
	@Override
	public void liberaAlteracoes() {
		
		
		campoTextoBotaoEnable(false);
		btnInserir.setEnabled(true);
		btnAlterarQuantidade.setEnabled(true);
		btnRemoverProduto.setEnabled(true);
		btnCancelar.setEnabled(true);
		boxProdutos.setEnabled(true);
		

		btnAlterarQuantidade.setEnabled(false);
		btnRemoverProduto.setEnabled(false);
		btnFinalizarMovimento.setEnabled(false);
		modelCompra.removeTudo();
		totalDaCompra=0;
		txtTotalMovimento.setText("");

	}

	@Override
	public void liberaNovoCadastro() {
		limpaTexto();
		int proximoID = bancoCompra.ultimoId().getId() + 1;
		txtId.setText(String.valueOf(proximoID));

		txtData.setText(String.valueOf(dt.format(new java.sql.Date(
				new java.util.Date().getTime()))));
		liberaAlteracoes();
		listaItensCompra.clear();
		boxPessoa.setEnabled(false);
		btnAlterarQuantidade.setEnabled(false);
		btnRemoverProduto.setEnabled(false);
		btnFinalizarMovimento.setText("Comprar");
		btnFinalizarMovimento.setEnabled(false);

	}

	@Override
	public void voltaParaPaginaAnterior() {
		dispose();

	}

	@Override
	public void inserirProdutoNaLista() {
		try {
			int index = boxProdutos.getSelectedIndex();

			Item item = (Item) listaProdutos.get(index);
			
			//troca a virgula pelo ponto
			float quantidade = Float.parseFloat((JOptionPane
					.showInputDialog("Quantidade").replace(',', '.')));
			//troca a virgula pelo ponto
			float custo = Float.parseFloat(JOptionPane.showInputDialog("Custo")
					.replace(',', '.'));
			//troca a virgula pelo ponto
			float preco = Float.parseFloat(JOptionPane.showInputDialog(
					"Preço de venda").replace(',', '.'));
			if (preco < custo)
				JOptionPane
						.showMessageDialog(null,
								"O preço de Venda é menor que o custo, você terá prejuiso!!");
			String descricao = item.getDescricao();
			int id = item.getId();
			String fornecedor = item.getFornecedor();
			String local = item.getLocal();
			float estoqueMin = item.getEstoqueMin();
			float desconto = (float) 0.05;
			Item itemCriado = new Item(id, descricao, fornecedor, local,
					estoqueMin, quantidade, preco, custo, desconto);

			modelCompra.addRow(itemCriado);
			listaItensCompra.add(itemCriado);

			// atualizaTabelaProdutos(e);

			totalDaCompra = (quantidade * custo) + totalDaCompra;

			txtTotalMovimento.setText(String.valueOf(totalDaCompra));

			listaProdutos.remove(listaProdutos.get(index));
			boxProdutos.removeItemAt(index);
			btnFinalizarInsersão.setEnabled(true);
			btnAlterarQuantidade.setEnabled(true);
			btnRemoverProduto.setEnabled(true);
			btnFinalizarMovimento.setEnabled(true);
			System.out.println(listaItensCompra.size());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Verifique a integridade dos dados fornecidos!!");
		}
		System.out.println("inseriu inserindo: tamanho da lista é "+listaItensCompra.size());
	}

	@Override
	public void alterarQuantidadeProdutoLista() {
		try {
			
			
			float total = (float) tableProdutos.getValueAt(
					tableProdutos.getSelectedRow(), 3);
			totalDaCompra = totalDaCompra - total;
			txtTotalMovimento.setText(String.valueOf(totalDaCompra));
			int id = modelCompra.removeRow(tableProdutos.getSelectedRow());
			
			for (int i = 0; i < listaItensCompra.size(); i++) {
				if (listaItensCompra.get(i).getId() == id) {
					
					
					listaItensCompra.remove(i);
					
					System.out.println("Removeu alterando: tamanho da lista é "+listaItensCompra.size());

				}
			}
			for (int i = 0; i < listaItensCompra.size(); i++) {
				System.out.println("item da lista numero "+i+"-"+listaItensCompra.get(i).getDescricao());
			}
			
			Item item = bancoItens.buscarPorId(id);

			//listaItensCompra.add(item);
			atualizaBoxProdutos();

			int index = boxProdutos.getSelectedIndex();

			float quantidade = Float.parseFloat((JOptionPane
					.showInputDialog("Quantidade").replace(',', '.')));

			float custo = Float.parseFloat(JOptionPane.showInputDialog("Custo")
					.replace(',', '.'));
			float preco = Float.parseFloat(JOptionPane.showInputDialog(
					"Preço de venda").replace(',', '.'));
			if (preco < custo)
				JOptionPane
						.showMessageDialog(null,
								"O preço de Venda é menor que o custo, você terá prejuiso!!");
			String descricao = item.getDescricao();

			String fornecedor = item.getFornecedor();

			String local = item.getLocal();

			float estoqueMin = item.getEstoqueMin();

			float desconto = (float) 0.05;

			Item itemCriado = new Item(id, descricao, fornecedor, local,
					estoqueMin, quantidade,  preco,custo, desconto);

			listaItensCompra.add(itemCriado);
			modelCompra.addRow(itemCriado);
			// atualizaTabelaProdutos(e);

			totalDaCompra = (quantidade * custo) + totalDaCompra;

			txtTotalMovimento.setText(String.valueOf(totalDaCompra));

			listaProdutos.remove(listaProdutos.get(index));
			boxProdutos.removeItemAt(index);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Verifique a integridade dos dados fornecidos!!");
		}
		System.out.println("inseriu alterando: tamanho da lista é "+listaItensCompra.size());

	}

	private void atualizaBoxProdutos() {
		try {

			boxProdutos.removeAllItems();
			List<Object> listaNova = new ArrayList<Object>();
			List<Object> listaNew = bancoItens.buscar();

			for (int i = 0; i < listaNew.size(); i++) {
				for (int j = 0; j < listaProdutos.size(); j++) {
					if (listaProdutos != null) {
						Item item = (Item) listaProdutos.get(j);
						Item itemBanco = (Item) listaNew.get(i);
						if (itemBanco.getId() == item.getId()) {
							listaNova.add(itemBanco);
							boxProdutos.addItem(item.getDescricao());
							j = listaProdutos.size();

						}
					}

				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Verifique a integridade dos dados fornecidos!!");
		}

	}

	@Override
	public void removeProdutoDaLista() {
		try {

			float total = (float) tableProdutos.getValueAt(
					tableProdutos.getSelectedRow(), 3);
			totalDaCompra = totalDaCompra - total;
			txtTotalMovimento.setText(String.valueOf(totalDaCompra));
			int id = modelCompra.removeRow(tableProdutos.getSelectedRow());

			Item item = bancoItens.buscarPorId(id);

			listaProdutos.add(item);
			atualizaBoxProdutos();

			for (int i = 0; i < listaItensCompra.size(); i++) {
				System.out.println(listaItensCompra.get(i).getDescricao());
				if (listaItensCompra.get(i).getId() == item.getId()) {
					listaItensCompra.remove(i);

				}
			}
			int tamanhoLista = modelCompra.getRowCount();
			if (tamanhoLista <= 0) {
				listaItensCompra.clear();
				btnFinalizarInsersão.setEnabled(false);
				btnAlterarQuantidade.setEnabled(false);
				btnRemoverProduto.setEnabled(false);
				btnFinalizarMovimento.setEnabled(false);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Selecione primeiramente o item antes de retirar!!");
		}

		System.out.println("Removel : tamanho da lista é "+listaItensCompra.size());
	}

	@Override
	public void finalizaInsercaoDeProdutos() {
		campoTextoBotaoEnable(false);
		txtImpostoDesconto.setEnabled(true);
		btnCancelar.setEnabled(true);
		btnFinalizarMovimento.setEnabled(true);
		boxPessoa.setEnabled(true);

	}

	@Override
	public void cancelaMovimentacaoAtual() {
		campoTextoBotaoEnable(false);
		limpaTexto();
		carregaExibicao(0);
		btnAlterarMovimento.setEnabled(true);
		btnNovoMovimento.setEnabled(true);
		btnRetornarSairPagina.setEnabled(true);
		carregaBoxProdutos();

	}

	@Override
	public void finalizarMovimento() {
		Fornecedor forn = null;
		for (int i = 0; i < listaFornecedores.size(); i++) {
			Fornecedor fornecedor = (Fornecedor) listaFornecedores.get(i);
			System.out.println(boxPessoa.getSelectedItem());
			if (fornecedor.getRazaoSocial() == boxPessoa.getSelectedItem()) {
				forn = fornecedor;

			}

		}

		JOptionPane.showMessageDialog(null,
				"A compra ficará cadastrada com data de hoje! ");
		@SuppressWarnings("unused")
		Date data = new java.sql.Date(new java.util.Date().getTime());
		bancoCompra.criar(new ClassesBin.Compra(new java.sql.Date(
				new java.util.Date().getTime()), Float
				.parseFloat(txtTotalMovimento.getText()),
				forn.getRazaoSocial(), Float.valueOf(txtImpostoDesconto
						.getText())));

		for (int i = 0; i < listaItensCompra.size(); i++) {
			int idCompra = Integer.parseInt(txtId.getText());
			int idProduto = listaItensCompra.get(i).getId();
			float quantidadeDeCompra = listaItensCompra.get(i).getQuantidade();
			float custoUnitarioDaCompra = listaItensCompra.get(i).getCusto();
			float novoValorVenda = listaItensCompra.get(i).getPreco();

			ClassesBin.ItemCompra instancia = new ClassesBin.ItemCompra(
					idProduto, quantidadeDeCompra, custoUnitarioDaCompra,
					idCompra);
			bancoItensCompra.criar(instancia);

			Item item = bancoItens.buscarPorId(idProduto);

			// Calculando o novo custo
			float quantidadeAntiga = item.getQuantidade();
			float custoTotalAntigo = item.getCusto() * quantidadeAntiga;

			float CustoTotalCompra = quantidadeDeCompra * custoUnitarioDaCompra;

			float novaQuantidade = quantidadeAntiga + quantidadeDeCompra;

			float novoCustoUnitario = (custoTotalAntigo + CustoTotalCompra)
					/ novaQuantidade;

			item.setCusto(novoCustoUnitario);
			item.setQuantidade(novaQuantidade);
			item.setFornecedor(forn.getRazaoSocial());
			item.setPreco(novoValorVenda);
			bancoItens.atualizar(item);

			cancelaMovimentacaoAtual();

		}
	}

}
