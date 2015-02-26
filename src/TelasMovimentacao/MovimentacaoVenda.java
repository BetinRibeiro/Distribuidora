package TelasMovimentacao;

import java.awt.Font;

//Essa Classe ainda tem que ser todo modificada!!
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import ClassesBin.Cliente;
import ClassesBin.Item;
import ClassesBin.ItemVenda;
import Controller.ModelParaVenda;
import EstruturaVisual.Movimentacao;
import Persistence.ClienteDAO;
import Persistence.VendaDAO;
import Persistence.InstanciaDeVendaDAO;
import Persistence.ItemDAO;

import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class MovimentacaoVenda extends Movimentacao {

	private VendaDAO bancoVenda = new VendaDAO();
	private ClassesBin.Venda vendaCarregada = null;
	private InstanciaDeVendaDAO bancoItensVenda = new InstanciaDeVendaDAO();
	private ModelParaVenda modelVenda = new ModelParaVenda();
	private ItemDAO bancoItens = new ItemDAO();
	private ClienteDAO bancoCliente = new ClienteDAO();
	private JTable tableProdutos;
	private float totalDaVenda;
	private List<Object> listaClientes;
	private List<Object> listaProdutos;
	protected List<Item> listaItensVenda = new ArrayList<Item>();
	protected SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

	public MovimentacaoVenda() {
		txtImpostoDesconto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//esse evento não deixa o usuario digitar letras, ao digitar o sistema apaga automaticamente
				if ((e.getKeyCode() < 48 || e.getKeyCode() > 57) 
						&& (e.getKeyCode() < 96 || e.getKeyCode() > 105) 
						&& (e.getKeyCode() !=8 && e.getKeyCode() != 46))
						//&& (e.getKeyCode() !=110 && e.getKeyCode() != 10)
					if (txtImpostoDesconto.getText().length() > 0)
						txtImpostoDesconto.setText(txtImpostoDesconto.getText()
							.substring( 0 , txtImpostoDesconto.getText().length()-1));
				
				if (e.getKeyCode()==44) txtImpostoDesconto.setText(txtImpostoDesconto.getText()+".");
				if (e.getKeyCode()==110) txtImpostoDesconto.setText(txtImpostoDesconto.getText()+".");
				
				
			}
		});
		btnPosterior.setEnabled(false);
		txtData.setHorizontalAlignment(SwingConstants.CENTER);
		txtData.setFont(new Font("Roboto Cn", Font.PLAIN, 18));
		lblTituloDoPainel.setText("Venda");
		lblPessoa.setText("Cliente");
		lblValorAdicional.setText("Desconto");
		setTitle("Venda de Produtos");

		btnFinalizarMovimento.setText("Vender");

		carregaExibicao(0);// passo zero como parametro por que aqui devemos
							// passar o id
		// mas quando se passa zero ele retorna o ultimo id colocado

		tableProdutos = new JTable(modelVenda);
		tableProdutos.setFont(new Font("Roboto Cn", Font.PLAIN, 12));
		tableProdutos.setBounds(0, 0, 1, 1);
		scrollPane.setColumnHeaderView(tableProdutos);
		scrollPane.setViewportView(tableProdutos);
		tableProdutos.setVisible(true);
		tableProdutos.setEnabled(true);
		tableProdutos.getColumn("Descrição").setPreferredWidth(205);
		tableProdutos.getTableHeader().setReorderingAllowed(false);

		carregaBoxProdutos();

		carregaBoxCliente();
	}

	// função ok
	private void carregaBoxCliente() {
		boxPessoa.removeAllItems();

		List<Object> listaNew = bancoCliente.buscar();

		for (int i = 0; i < listaNew.size(); i++) {
			Cliente cliente = (Cliente) listaNew.get(i);
			boxPessoa.addItem(cliente.getNome());

		}
		listaClientes = listaNew;

	}

	// função ok
	private void carregaBoxProdutos() {

		boxProdutos.removeAllItems();

		List<Object> listaNew = bancoItens.buscar();

		for (int i = 0; i < listaNew.size(); i++) {
			Item item = (Item) listaNew.get(i);
			boxProdutos.addItem(item.getDescricao());

		}
		listaProdutos = listaNew;

	}

	// função ok
	public void campoTextoBotaoEnable(Boolean b) {
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
				"A Venda ficará cadastrada com data de hoje! ");
		Date data = new java.sql.Date(new java.util.Date().getTime());

		ClassesBin.Venda venda = new ClassesBin.Venda(data,
				Float.parseFloat(txtTotalMovimento.getText()), bancoCliente
						.buscarPorId(boxPessoa.getSelectedIndex())
						.getNome(), Float.parseFloat(txtImpostoDesconto
						.getText()));
		bancoVenda.criar(venda);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Verifique a integridade dos dados fornecidos!!");
		}
	}

	// função ok
	public void limpaTexto() {
		txtData.setText("");
		txtId.setText("");
		txtImpostoDesconto.setText("");
		txtTotalMovimento.setText("");
		modelVenda.removeTudo();

	}

	// tenho que trabalhar melhor essa função
	public void carregaExibicao(int e) {

		// dependendo do parametro ele decide se ele busca o umtimo elemento
		// introduzido
		// ou se ele busca um id normalmente;
		if (e == 0) {
			btnAnterior.setEnabled(true);
			btnPosterior.setEnabled(true);
			e = bancoVenda.ultimoId().getId();
			vendaCarregada = bancoVenda.buscarPorId(e);
			idMovimentoAtual = e;

		}

		if (e >= bancoVenda.ultimoId().getId()) {
			btnPosterior.setEnabled(false);
			e = bancoVenda.ultimoId().getId();

		}

		else {

			while (bancoVenda.buscarPorId(e) == null) {
				if (sentido == -1) {
					e = e - 1;

					if (e <= 0) {
						btnAnterior.setEnabled(false);
						break;

					}

				}
				if (sentido == 1) {
					e = e + 1;
					if (e >= bancoVenda.ultimoId().getId()) {
						JOptionPane.showMessageDialog(null,
								"Não existe Venda Posteriores!!");
						e = bancoVenda.ultimoId().getId();
						idMovimentoAtual = e;

					}

				}

			}
		}
		vendaCarregada = bancoVenda.buscarPorId(e);

		List<Object> lista = bancoItensVenda.buscarVenda(e);

		modelVenda.removeTudo();

		for (int i = 0; i < lista.size(); i++) {

			ItemVenda produto = (ItemVenda) lista.get(i);

			int id = produto.getIdProduto();
			String descricao = bancoItens.buscarPorId(id).getDescricao();
			float quantidade = produto.getQuantidade();
			System.out.println("Quantidade: "+quantidade);

			float preco = produto.getCustoUnitario();
			System.out.println(preco);

			Item produtoReal = new Item(id, descricao, quantidade, preco);
			modelVenda.addRow(produtoReal);

		}

		if (bancoVenda.buscarPorId(e) != null) {
			txtId.setText(String.valueOf(vendaCarregada.getId()));
			txtData.setText(String
					.valueOf(dt.format(bancoVenda.buscarPorId(e).getData())));
			txtImpostoDesconto.setText(String.valueOf(bancoVenda
					.buscarPorId(e).getDesconto()));
			txtTotalMovimento.setText(String.valueOf(bancoVenda.buscarPorId(e)
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
		boxPessoa.setEnabled(true);
		boxProdutos.setEnabled(true);

		btnAlterarQuantidade.setEnabled(false);
		btnRemoverProduto.setEnabled(false);
		btnFinalizarMovimento.setEnabled(false);

	}

	@Override
	public void liberaNovoCadastro() {
		limpaTexto();
		int proximoID = bancoVenda.ultimoId().getId() + 1;
		txtId.setText(String.valueOf(proximoID));

		txtData.setText(String.valueOf(dt.format(new java.sql.Date(new java.util.Date()
				.getTime()))));
		liberaAlteracoes();
		listaItensVenda.clear();
		boxPessoa.setEnabled(false);
		btnAlterarQuantidade.setEnabled(false);
		btnRemoverProduto.setEnabled(false);
		btnFinalizarMovimento.setText("Vender");
		btnFinalizarMovimento.setEnabled(false);

	}

	@Override
	public void voltaParaPaginaAnterior() {
		dispose();

	}

	@Override
	public void inserirProdutoNaLista() {
		try{
		int index = boxProdutos.getSelectedIndex();

		Item item = (Item) listaProdutos.get(index);
		
		float quantidade = Float.parseFloat((JOptionPane
				.showInputDialog("Quantidade").replace(',', '.')));
		
		
		float custo = Float.parseFloat(JOptionPane.showInputDialog("Custo").replace(',', '.'));
		float preco = Float.parseFloat(JOptionPane
				.showInputDialog("Preço de venda").replace(',', '.'));
		if (preco < custo)
			JOptionPane
					.showMessageDialog(null,
							"O preço de Venda é menor que o custo, você terá prejuiso!!");
		String descricao = item.getDescricao();
		int id = item.getId();
		String local = item.getLocal();
		float estoqueMin = item.getEstoqueMin();
		float desconto = (float) 0.05;
		Item itemCriado = new Item(id, descricao, quantidade, preco);

		modelVenda.addRow(itemCriado);
		listaItensVenda.add(itemCriado);

		// atualizaTabelaProdutos(e);

		totalDaVenda = (quantidade * custo) + totalDaVenda;

		txtTotalMovimento.setText(String.valueOf(totalDaVenda));

		listaProdutos.remove(listaProdutos.get(index));
		boxProdutos.removeItemAt(index);
		btnFinalizarInsersão.setEnabled(true);
		btnAlterarQuantidade.setEnabled(true);
		btnRemoverProduto.setEnabled(true);
		btnFinalizarMovimento.setEnabled(true);
		System.out.println(listaItensVenda.size());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Verifique a integridade dos dados fornecidos!!");
		}
	}

	

	@Override
	public void alterarQuantidadeProdutoLista() {
		try{
		float total = (float) tableProdutos.getValueAt(
				tableProdutos.getSelectedRow(), 3);
		totalDaVenda = totalDaVenda - total;
		txtTotalMovimento.setText(String.valueOf(totalDaVenda));
		int id = modelVenda.removeRow(tableProdutos.getSelectedRow());

		Item item = bancoItens.buscarPorId(id);

		listaItensVenda.add(item);
		atualizaBoxProdutos();

		int index = boxProdutos.getSelectedIndex();

		float quantidade = Float.parseFloat(JOptionPane
				.showInputDialog("Quantidade"));
		float custo = Float.parseFloat(JOptionPane.showInputDialog("Custo"));
		float preco = Float.parseFloat(JOptionPane
				.showInputDialog("Preço de venda"));
		if (preco < custo)
			JOptionPane
					.showMessageDialog(null,
							"O preço de Venda é menor que o custo, você terá prejuiso!!");
		String descricao = item.getDescricao();


		String local = item.getLocal();

		float estoqueMin = item.getEstoqueMin();

		float desconto = (float) 0.05;

		Item itemCriado = new Item(id, descricao, cliente, local,
				estoqueMin, quantidade, custo, preco, desconto);

		listaItensVenda.add(itemCriado);
		modelVenda.addRow(itemCriado);
		// atualizaTabelaProdutos(e);

		totalDaVenda = (quantidade * custo) + totalDaVenda;

		txtTotalMovimento.setText(String.valueOf(totalDaVenda));

		listaProdutos.remove(listaProdutos.get(index));
		boxProdutos.removeItemAt(index);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Verifique a integridade dos dados fornecidos!!");
		}

	}

	private void atualizaBoxProdutos() {
		try{

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
			JOptionPane.showMessageDialog(null, "Verifique a integridade dos dados fornecidos!!");
		}

	}

	@Override
	public void removeProdutoDaLista() {
		try{

		float total = (float) tableProdutos.getValueAt(
				tableProdutos.getSelectedRow(), 3);
		totalDaVenda = totalDaVenda - total;
		txtTotalMovimento.setText(String.valueOf(totalDaVenda));
		int id = modelVenda.removeRow(tableProdutos.getSelectedRow());

		Item item = bancoItens.buscarPorId(id);

		listaProdutos.add(item);
		atualizaBoxProdutos();

		for (int i = 0; i < listaItensVenda.size(); i++) {
			if (listaItensVenda.get(i).getId() == item.getId()) {
				listaItensVenda.remove(i);

			}
		}
		int tamanhoLista = listaItensVenda.size();
		if (tamanhoLista <= 0) {
			btnFinalizarInsersão.setEnabled(false);
			btnAlterarQuantidade.setEnabled(false);
			btnRemoverProduto.setEnabled(false);
			btnFinalizarMovimento.setEnabled(false);
		}} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Selecione primeiramente o item antes de retirar!!");
		}
		
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
		Cliente clie = null;
		for (int i = 0; i < listaClientes.size(); i++) {
			Cliente cliente = (Cliente) listaClientes.get(i);
			System.out.println(boxPessoa.getSelectedItem());
			if (cliente.getNome() == boxPessoa.getSelectedItem()) {
				clie = cliente;

			}

		}

		JOptionPane.showMessageDialog(null,
				"A Venda ficará cadastrada com data de hoje! ");
		@SuppressWarnings("unused")
		Date data = new java.sql.Date(new java.util.Date().getTime());
		bancoVenda.criar(new ClassesBin.Venda(new java.sql.Date(
				new java.util.Date().getTime()), Float
				.parseFloat(txtTotalMovimento.getText()),
				clie.getNome(), Float.valueOf(txtImpostoDesconto
						.getText())));

		for (int i = 0; i < listaItensVenda.size(); i++) {
			int idVenda = Integer.parseInt(txtId.getText());
			int idProduto = listaItensVenda.get(i).getId();
			float quantidadeDeVenda = listaItensVenda.get(i).getQuantidade();
			float custoUnitarioDaVenda = listaItensVenda.get(i).getCusto();
			float novoValorVenda = listaItensVenda.get(i).getPreco();
			float preco = listaItensVenda.get(i).getPreco();

			ClassesBin.ItemVenda instancia = new ClassesBin.ItemVenda(
					idProduto, quantidadeDeVenda, custoUnitarioDaVenda,
					idVenda,preco);
			bancoItensVenda.criar(instancia);

			Item item = bancoItens.buscarPorId(idProduto);

			// Calculando o novo custo
			float quantidadeAntiga = item.getQuantidade();
			float custoTotalAntigo = item.getCusto() * quantidadeAntiga;

			float CustoTotalVenda = quantidadeDeVenda * custoUnitarioDaVenda;

			float novaQuantidade = quantidadeAntiga + quantidadeDeVenda;

			float novoCustoUnitario = (custoTotalAntigo + CustoTotalVenda)
					/ novaQuantidade;

			item.setCusto(novoCustoUnitario);
			item.setQuantidade(novaQuantidade);
			item.setPreco(novoValorVenda);
			bancoItens.atualizar(item);
			
			cancelaMovimentacaoAtual();

		}
	}

}
