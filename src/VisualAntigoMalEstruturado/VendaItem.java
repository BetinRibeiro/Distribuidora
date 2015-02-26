package VisualAntigoMalEstruturado;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JScrollPane;

import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Component;

import javax.swing.border.EtchedBorder;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;

import ClassesBin.Cliente;
import ClassesBin.Item;
import Controller.ModelParaVenda;
import Persistence.ClienteDAO;
import Persistence.ItemDAO;

import java.awt.event.KeyEvent;

import javax.swing.ListSelectionModel;

//para deixar o codigo mais inchuto implementei um actionListener para que todos os eventos possam ser tratados 
//apartir do metodo que é reescrevido, deixando as ações separadas dos atributos e caracteristicas dos proprios componentes visuais 
@SuppressWarnings("serial")
public class VendaItem extends JFrame implements ActionListener, KeyListener {

	int id;

	// classe especial para popular a jtable carrinho de compra criei essa
	// classe para poder manupular e alterar
	// alguns metodos que antes não poderiam ser alterados usando a
	// defaulttablemodel, como muitos fazem
	ModelParaVenda modelo = new ModelParaVenda();

	// variavel simploria que armazena o total da venda para poder exibir na
	// tela principal de forma instantania
	// sem a necessidade de refazer calculos sempre que um produto é inserido no
	// carrinho
	float totalDaVenda = 0;

	// classe Leitor de teclas feito para poder reconhecer teclas atalhos
	// criadas para uma parte mais avançada do sistema

	// Classes DAO para fazer interação direto com o modelo que é o proprio
	// banco de dados,
	// coloquei o nome banco como prefixo para que ao programar tenhamos ciencia
	// que todos os parametros
	// passados e resgatados estão sendo diretamente do proprio banco de dados
	ClienteDAO bancoCliente = new ClienteDAO();
	ItemDAO bancoItens = new ItemDAO();

	// Listas
	// Lista que simplesmente armazena todos os clientes para carregar o box de
	// clientes.
	List<Object> listaClientes;
	// essa lista de itens é extremamente importante pois quando colocamos um
	// item na tabela carrinho de compra
	// temos que tirar desta lista o mesmo produto para que jamais haja
	// duplicidade do mesmo produto,
	// e assim quando queremos alterar o texto forçamos o usuario do sistema a
	// alterar a quantidade e nunca
	// inserir o mesmo produto no carrinho novamente, isso ajudará
	// posteriormente na implementação do fluxo de caixa
	// e controle de estoque, com os relatorios de entradas e saidas de
	// produtos.
	List<Object> listaItens;
	// Essa lista esta sendo mantida para poder guardar os itens que estão na
	// Jtable carrinho de compra
	// ela é importante para que eu haja necessidade de criar no banco alguma
	// coisa do tipo
	// pois ela é alocada de forma dinamica, jamais teremos a necessidade de
	// arquivamento dos produtos nela contido.
	List<Item> listaItensCarrinho = new ArrayList<Item>();

	// botões
	private JButton btnIncluirProduto;
	private JButton btnIncluirProduto_1;
	@SuppressWarnings("unused")
	private JButton btnFinalizarVenda;
	private JButton btnCancelarVenda;
	private JButton btnFinalizarVenda1;
	private JButton btnVoltar;
	private JButton btnClienteDesconhecido;
	private JButton btnCarregar;
	private JButton btnCadastrar;
	private JButton btnCriar;
	private JButton btnSalvar;
	private JButton btnAlterar;
	private JButton btnHistorico;
	private JButton btnVender;

	// caixas de texto
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtTelefone;
	private JTextField txtBairro;
	private JTextField txtNome;
	private JTextField txtTotalVenda;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField txtNumeroVenda;
	private JTextField txtCidade;

	// box's
	@SuppressWarnings("rawtypes")
	private JComboBox boxClientes;
	@SuppressWarnings("rawtypes")
	private JComboBox boxItens;

	// area de texto
	private JTextArea txtAreaCupom;

	// tabelas e Scrol
	private JScrollPane rolagem;
	private JTable tableCarrinhoVenda;

	// Paineis, criei assim simplesmente para organizar mais o codigo, mas não
	// existe a necessidade de um painel
	// ser uma variavel global, pelomenos não nesse extato nivel do sistema
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public VendaItem() {

		// caracteristicas da janela,
		// janela maximizada
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1373, 740);

		// instanciando
		contentPane = new JPanel();
		// definindo borda
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// colocando no conteiner
		setContentPane(contentPane);
		// definindo o layout
		contentPane.setLayout(null);

		// essa janela principal dentro dela temos todos os outros componentes
		// do sistema
		JPanel painelPrincipal = new JPanel();
		// definição da sua cor
		painelPrincipal.setBackground(new Color(245, 245, 245));
		// definição do tipo e da cor da borda
		painelPrincipal.setBorder(new EtchedBorder(EtchedBorder.RAISED,
				new Color(105, 105, 105), new Color(210, 180, 140)));
		// definindo o tamanho e localização do painel
		painelPrincipal.setBounds(10, 11, 1347, 691);
		// colocando a janela principal dentro do contentPane
		contentPane.add(painelPrincipal);
		// definindo o tipo de layout que o painel carregará - nesse cado o
		// absolut
		painelPrincipal.setLayout(null);

		// janela de cupom aqui ficará todo conteudo do cupom na area de texto
		// que estara dentro dela
		JPanel painelDeCupom = new JPanel();
		// tamanho do painel
		painelDeCupom.setBounds(951, 6, 389, 634);
		// adicionando o painel dentro do painel principal
		painelPrincipal.add(painelDeCupom);
		// definindo a cor da borda
		painelDeCupom.setBorder(new EtchedBorder(EtchedBorder.RAISED,
				new Color(105, 105, 105), new Color(222, 184, 135)));
		// definindo o tipo de layout que o painel carregará - nesse cado o
		// absolut
		painelDeCupom.setLayout(null);

		// area de texto que será inserido os dados da compra
		txtAreaCupom = new JTextArea(10, 11);
		// cor da area
		txtAreaCupom.setBackground(SystemColor.info);
		// Define a política de quebra de linha da área de texto
		txtAreaCupom.setLineWrap(true);
		// Define o número de colunas para este TextArea
		txtAreaCupom.setColumns(72);
		// Define o número de linhas para este TextArea
		txtAreaCupom.setRows(3);
		// Define o estilo de acondicionamento utilizado se a área de texto está
		// envolvendo linhas.
		txtAreaCupom.setWrapStyleWord(true);
		// Define a posição do cursor de inserção de texto
		txtAreaCupom.setCaretPosition(txtAreaCupom.getDocument().getLength());
		// Cria um JScrollPane que exibe o conteúdo do componente especificado

		rolagem = new JScrollPane(txtAreaCupom);
		// Define a imagem do cursor para o cursor especificado
		rolagem.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		// tamanho
		rolagem.setBounds(5, 5, 377, 622);
		// adiciona a rolagem ao painel de cupom
		painelDeCupom.add(rolagem);

		// instanciando painel
		JPanel panelPesquisaClientes = new JPanel();
		// define tamanho
		panelPesquisaClientes.setBounds(5, 89, 345, 72);
		// adiciona ao conteiner
		painelPrincipal.add(panelPesquisaClientes);
		// definição de borda
		panelPesquisaClientes.setBorder(new EtchedBorder(EtchedBorder.RAISED,
				new Color(105, 105, 105), new Color(222, 184, 135)));
		// definição do tipo de layout
		panelPesquisaClientes.setLayout(null);

		// instancia label
		JLabel lblCliente = new JLabel("Cliente ");
		// define tamanho
		lblCliente.setBounds(10, 11, 46, 14);
		// adiciona ao container
		panelPesquisaClientes.add(lblCliente);

		// /instancia box
		boxClientes = new JComboBox();
		// define tamanho
		boxClientes.setBounds(56, 5, 284, 28);
		// adiciona ao conteiner
		panelPesquisaClientes.add(boxClientes);

		// instanciando o botão cadastrar
		btnCadastrar = new JButton("Cadastrar");
		// Adiciona um ActionListener ao botão
		btnCadastrar.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btnCadastrar.setActionCommand("cadastrar");
		// tamanho
		btnCadastrar.setBounds(5, 39, 116, 28);
		panelPesquisaClientes.add(btnCadastrar);

		// instanciando o botão carregar
		btnCarregar = new JButton("Carregar");
		// Adiciona um ActionListener ao botão
		btnCarregar.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btnCarregar.setActionCommand("carregar");
		// define tamanho
		btnCarregar.setBounds(126, 39, 104, 28);
		// adiciona ao conteiner
		panelPesquisaClientes.add(btnCarregar);

		// instancia
		btnClienteDesconhecido = new JButton("Desconhecido");
		// define borda
		btnClienteDesconhecido.setBorder(new LineBorder(
				new Color(222, 184, 135), 2));
		// define tamanho
		btnClienteDesconhecido.setBounds(235, 39, 105, 28);
		// adiciona ao conteiner
		panelPesquisaClientes.add(btnClienteDesconhecido);

		// /instancia
		btnVoltar = new JButton("Voltar");
		// adiciona um ActionListener
		btnVoltar.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btnVoltar.setActionCommand("voltar");

		// define a não visibilidade
		btnVoltar.setVisible(false);
		// define a borda
		btnVoltar.setBorder(new LineBorder(new Color(222, 184, 135), 2));
		// define tamanho
		btnVoltar.setBounds(235, 39, 105, 28);
		// adiciona ao conteiner
		panelPesquisaClientes.add(btnVoltar);

		// instancia
		JPanel painelCadastroCliente = new JPanel();
		// define tamanho
		painelCadastroCliente.setBounds(5, 165, 345, 226);
		// adiciona ao conteiner
		painelPrincipal.add(painelCadastroCliente);
		// define borda
		painelCadastroCliente.setBorder(new EtchedBorder(EtchedBorder.RAISED,
				new Color(105, 105, 105), new Color(222, 184, 135)));
		// define o layout
		painelCadastroCliente.setLayout(null);

		// instancia
		JLabel lblEndereoEntrega = new JLabel("Endere\u00E7o Entrega");
		// Define o alinhamento do conteúdo do rótulo ao longo do eixo X .
		lblEndereoEntrega.setHorizontalAlignment(SwingConstants.RIGHT);
		// define tamanh
		lblEndereoEntrega.setBounds(10, 40, 114, 14);
		// adiciona ao conteiner
		painelCadastroCliente.add(lblEndereoEntrega);

		// instancia
		txtEndereco = new JTextField();
		// define disponibilidade de acesso
		txtEndereco.setEnabled(false);
		// define tamanho
		txtEndereco.setBounds(125, 37, 210, 20);
		// adiciona ao conteiner
		painelCadastroCliente.add(txtEndereco);
		// define tamanho baseado em colunas
		txtEndereco.setColumns(10);

		// instancia
		JLabel lblNumero = new JLabel("Numero ");
		// define alinhamento horizontal
		lblNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		// define tamanho
		lblNumero.setBounds(62, 68, 64, 14);
		// adicona ao conteiner
		painelCadastroCliente.add(lblNumero);

		// intancia
		txtNumero = new JTextField();
		// define disponibilidade de acesso
		txtNumero.setEnabled(false);
		// define tamanho
		txtNumero.setBounds(130, 65, 46, 20);
		// adiciona ao conteiner
		painelCadastroCliente.add(txtNumero);
		// define tamanho
		txtNumero.setColumns(10);

		// instancia
		JLabel lblFone = new JLabel("Fone");
		// define tamanho
		lblFone.setBounds(199, 68, 46, 14);
		// adiciona ao conteiner
		painelCadastroCliente.add(lblFone);

		// instancia
		txtTelefone = new JTextField();
		// define disponibilidade de acesso
		txtTelefone.setEnabled(false);
		// define tamanho
		txtTelefone.setBounds(237, 65, 98, 20);
		// adiciona ao conteiner
		painelCadastroCliente.add(txtTelefone);
		// define tamanho
		txtTelefone.setColumns(10);

		// intancia
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		// define tamanho
		lblBairro.setBounds(62, 93, 64, 14);
		painelCadastroCliente.add(lblBairro);

		// intancia
		txtBairro = new JTextField();
		txtBairro.setEnabled(false);
		// define tamanho
		txtBairro.setBounds(130, 90, 205, 20);
		painelCadastroCliente.add(txtBairro);
		txtBairro.setColumns(10);

		// intancia
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		// define tamanho
		lblCidade.setBounds(62, 118, 64, 14);
		painelCadastroCliente.add(lblCidade);

		// intancia
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		// define tamanho
		lblNome.setBounds(10, 140, 64, 14);
		painelCadastroCliente.add(lblNome);

		// intancia
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		// define tamanho
		txtNome.setBounds(78, 137, 257, 20);
		painelCadastroCliente.add(txtNome);
		txtNome.setColumns(10);

		// intancia
		JLabel lblInformaesDoCliente = new JLabel(
				"Informa\u00E7\u00F5es do cliente");
		lblInformaesDoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		// define tamanho
		lblInformaesDoCliente.setBounds(0, 15, 345, 14);
		painelCadastroCliente.add(lblInformaesDoCliente);

		// intancia
		btnCriar = new JButton("Criar");
		btnCriar.setEnabled(false);
		btnCriar.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btnCriar.setActionCommand("criar");
		// define tamanho
		btnCriar.setBounds(5, 162, 116, 28);
		painelCadastroCliente.add(btnCriar);

		// intancia
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btnSalvar.setActionCommand("salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(5, 193, 116, 28);
		painelCadastroCliente.add(btnSalvar);

		// intancia
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btnAlterar.setActionCommand("alterar");

		btnAlterar.setEnabled(false);

		btnAlterar.setBounds(123, 162, 116, 28);
		painelCadastroCliente.add(btnAlterar);

		// intancia
		btnHistorico = new JButton("Historico");
		btnHistorico.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btnHistorico.setActionCommand("historico");

		btnHistorico.setEnabled(false);
		btnHistorico.setBounds(123, 193, 116, 28);
		painelCadastroCliente.add(btnHistorico);

		// intancia
		btnVender = new JButton("Vincular");
		btnVender.setEnabled(false);
		btnVender.setBorder(new LineBorder(new Color(222, 184, 135), 2));
		btnVender.setBounds(241, 162, 98, 59);
		painelCadastroCliente.add(btnVender);

		// intancia
		txtCidade = new JTextField();
		txtCidade.setEnabled(false);
		txtCidade.setBounds(130, 115, 205, 20);
		painelCadastroCliente.add(txtCidade);
		txtCidade.setColumns(10);

		// intancia
		JPanel painelPesquisaProdutos = new JPanel();
		painelPesquisaProdutos.setBounds(354, 89, 593, 72);
		painelPrincipal.add(painelPesquisaProdutos);
		painelPesquisaProdutos.setBorder(new EtchedBorder(EtchedBorder.RAISED,
				new Color(105, 105, 105), new Color(222, 184, 135)));
		painelPesquisaProdutos.setLayout(null);

		// intancia
		JLabel lblItem = new JLabel("Item");
		lblItem.setBounds(10, 11, 46, 14);
		painelPesquisaProdutos.add(lblItem);

		// intancia
		boxItens = new JComboBox();
		boxItens.setBounds(55, 3, 528, 30);
		painelPesquisaProdutos.add(boxItens);

		// intancia
		btnIncluirProduto = new JButton("Trocar Cliente");
		btnIncluirProduto
				.setBorder(new LineBorder(new Color(222, 184, 135), 2));
		btnIncluirProduto.setBounds(5, 39, 144, 28);
		painelPesquisaProdutos.add(btnIncluirProduto);

		// intancia
		btnIncluirProduto_1 = new JButton("Incluir Produto");
		btnIncluirProduto_1.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btnIncluirProduto_1.setActionCommand("incluirProduto");

		btnIncluirProduto_1.setBorder(new LineBorder(new Color(222, 184, 135),
				2));
		btnIncluirProduto_1.setBounds(150, 39, 144, 28);
		painelPesquisaProdutos.add(btnIncluirProduto_1);

		// intancia
		btnCancelarVenda = new JButton("Cancelar Venda");
		btnCancelarVenda.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btnCancelarVenda.setActionCommand("cancelarVenda");

		btnCancelarVenda.setBounds(295, 39, 144, 28);
		painelPesquisaProdutos.add(btnCancelarVenda);

		// intancia
		btnFinalizarVenda1 = new JButton("Finalizar Venda");
		btnFinalizarVenda1.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btnFinalizarVenda1.setActionCommand("finalizarVenda");

		btnFinalizarVenda1
				.setBorder(new LineBorder(new Color(222, 184, 135), 2));
		btnFinalizarVenda1.setBounds(440, 39, 144, 28);
		painelPesquisaProdutos.add(btnFinalizarVenda1);
		btnFinalizarVenda1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1");

		// intancia
		JPanel painelCarrinhoVenda = new JPanel();
		painelCarrinhoVenda.setBounds(354, 165, 593, 475);
		painelPrincipal.add(painelCarrinhoVenda);
		painelCarrinhoVenda.setBorder(new EtchedBorder(EtchedBorder.RAISED,
				new Color(105, 105, 105), new Color(222, 184, 135)));
		painelCarrinhoVenda.setLayout(null);
		rolagem.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		// intancia
		JScrollPane scrollTabela = new JScrollPane((Component) null);
		scrollTabela.setBounds(5, 61, 583, 139);
		painelCarrinhoVenda.add(scrollTabela);

		// intancia
		tableCarrinhoVenda = new JTable(modelo);
		tableCarrinhoVenda.setRowMargin(2);
		tableCarrinhoVenda.setRowHeight(20);
		tableCarrinhoVenda
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCarrinhoVenda.getColumnModel().getColumn(0).setPreferredWidth(300);
		tableCarrinhoVenda.getColumnModel().getColumn(1).setPreferredWidth(5);
		tableCarrinhoVenda.getColumnModel().getColumn(2).setPreferredWidth(5);
		tableCarrinhoVenda.getColumnModel().getColumn(3).setPreferredWidth(5);
		scrollTabela.setViewportView(tableCarrinhoVenda);
		tableCarrinhoVenda.getTableHeader().setReorderingAllowed(false);

		// intancia
		txtTotalVenda = new JTextField();
		txtTotalVenda.setEditable(false);
		txtTotalVenda.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalVenda.setText("0.00");
		txtTotalVenda.setFont(new Font("Virtual DJ", Font.PLAIN, 30));
		txtTotalVenda.setBounds(403, 5, 185, 52);
		painelCarrinhoVenda.add(txtTotalVenda);
		txtTotalVenda.setColumns(10);

		// intancia
		JLabel lblItensSelecionados = new JLabel("Itens Selecionados");
		lblItensSelecionados.setForeground(Color.GRAY);
		lblItensSelecionados.setFont(new Font("Roboto",
				Font.BOLD | Font.ITALIC, 22));
		lblItensSelecionados.setBounds(10, 12, 249, 33);
		painelCarrinhoVenda.add(lblItensSelecionados);

		// intancia
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(105,
				105, 105), new Color(222, 184, 135)));
		panel_9.setBounds(5, 206, 283, 263);
		painelCarrinhoVenda.add(panel_9);

		// intancia
		JButton btnRetirarProduto = new JButton("Retirar");
		btnRetirarProduto.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btnRetirarProduto.setActionCommand("retirar");

		btnRetirarProduto.setBounds(292, 206, 116, 28);
		painelCarrinhoVenda.add(btnRetirarProduto);

		// intancia
		JButton btnAlterarQuantidade = new JButton("Quantidade");
		btnAlterarQuantidade.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btnAlterarQuantidade.setActionCommand("quantidade");

		btnAlterarQuantidade.setBounds(292, 237, 116, 28);
		painelCarrinhoVenda.add(btnAlterarQuantidade);

		// intancia
		JButton btnDesconto = new JButton("Desconto");
		btnDesconto.setBounds(410, 206, 116, 28);
		painelCarrinhoVenda.add(btnDesconto);

		// intancia
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(105,
				105, 105), new Color(222, 184, 135)));
		panel_10.setBounds(292, 276, 296, 149);
		painelCarrinhoVenda.add(panel_10);
		panel_10.setLayout(null);

		// intancia
		textField_6 = new JTextField();
		textField_6.setBounds(189, 32, 86, 20);
		panel_10.add(textField_6);
		textField_6.setColumns(10);

		JLabel lblValorDaVenda = new JLabel("Valor da Venda");
		lblValorDaVenda.setBounds(53, 35, 132, 14);
		panel_10.add(lblValorDaVenda);
		lblValorDaVenda.setHorizontalAlignment(SwingConstants.RIGHT);

		// intancia
		textField_7 = new JTextField();
		textField_7.setBounds(189, 58, 86, 20);
		panel_10.add(textField_7);
		textField_7.setColumns(10);

		// intancia
		textField_8 = new JTextField();
		textField_8.setBounds(189, 83, 86, 20);
		panel_10.add(textField_8);
		textField_8.setColumns(10);

		// intancia
		JLabel lblDinheiroRecebido = new JLabel("Dinheiro Recebido");
		lblDinheiroRecebido.setBounds(53, 61, 132, 14);
		panel_10.add(lblDinheiroRecebido);
		lblDinheiroRecebido.setHorizontalAlignment(SwingConstants.RIGHT);

		// intancia
		JButton button = new JButton("Retirar");
		button.setBounds(41, 110, 116, 28);
		panel_10.add(button);

		// intancia
		JButton button_1 = new JButton("Desconto");
		button_1.setBounds(159, 110, 116, 28);
		panel_10.add(button_1);

		// intancia
		JLabel lblSimulaoDeTroco = new JLabel("Simula\u00E7\u00E3o de Troco");
		lblSimulaoDeTroco.setHorizontalAlignment(SwingConstants.CENTER);
		lblSimulaoDeTroco.setBounds(10, 11, 265, 14);
		panel_10.add(lblSimulaoDeTroco);

		// intancia
		JLabel lblTroco = new JLabel("Troco");
		lblTroco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTroco.setBounds(53, 86, 132, 14);
		panel_10.add(lblTroco);

		// intancia
		txtNumeroVenda = new JTextField();
		txtNumeroVenda.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumeroVenda.setText("N\u00BA-0015");
		txtNumeroVenda.setFont(new Font("Virtual DJ", Font.PLAIN, 30));
		txtNumeroVenda.setColumns(10);
		txtNumeroVenda.setBounds(214, 5, 185, 52);
		painelCarrinhoVenda.add(txtNumeroVenda);

		// intancia
		JButton btnEstoque = new JButton("Estoque");
		btnEstoque.setBounds(410, 237, 116, 28);
		painelCarrinhoVenda.add(btnEstoque);

		// intancia
		JPanel painelRodaPe = new JPanel();
		painelRodaPe.setBounds(5, 642, 1335, 43);
		painelPrincipal.add(painelRodaPe);
		painelRodaPe.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(
				105, 105, 105), new Color(222, 184, 135)));

		// intancia
		JPanel painelHistoricoCliente = new JPanel();
		painelHistoricoCliente.setBorder(new EtchedBorder(EtchedBorder.RAISED,
				new Color(105, 105, 105), new Color(222, 184, 135)));
		painelHistoricoCliente.setBounds(5, 394, 345, 246);
		painelPrincipal.add(painelHistoricoCliente);
		painelHistoricoCliente.setLayout(null);

		// intancia
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(5, 5, 335, 235);
		painelHistoricoCliente.add(scrollPane);
		scrollPane.setBackground(new Color(210, 180, 140));

		// intancia
		JTextArea txtrHistoricoCliente = new JTextArea();
		txtrHistoricoCliente.setEnabled(false);
		txtrHistoricoCliente.setBackground(new Color(255, 255, 255));
		txtrHistoricoCliente.setText("historico de Venda");
		scrollPane.setViewportView(txtrHistoricoCliente);

		// intancia
		JPanel panelLogoPropaganda = new JPanel();
		panelLogoPropaganda.setFont(new Font("Roboto", Font.PLAIN, 60));
		panelLogoPropaganda.setBorder(new EtchedBorder(EtchedBorder.RAISED,
				new Color(105, 105, 105), new Color(222, 184, 135)));
		panelLogoPropaganda.setBounds(5, 6, 942, 79);
		painelPrincipal.add(panelLogoPropaganda);
		panelLogoPropaganda.setLayout(null);

		ImageIcon img = new ImageIcon("../Distribuidora/src/Visual/2.jpg");

		// intancia
		JLabel lblDepositoRibeiro = new JLabel(img);
		lblDepositoRibeiro.setForeground(new Color(128, 128, 128));
		lblDepositoRibeiro.setBounds(0, 0, 942, 79);
		lblDepositoRibeiro.setFont(new Font("Roboto", Font.BOLD | Font.ITALIC,
				48));
		panelLogoPropaganda.add(lblDepositoRibeiro);

		// Funções inicializadas
		atualizaBoxCliente();
		carregaBoxProdutos();

		// leitor de teclas
		addKeyListener(this);
		// Se você não deixar a janela selecionável, ela não
		// fará os eventos relacionados a ela, ocorrerem
		setFocusable(true);
		setVisible(true);
		setVisible(true);
		requestFocusInWindow();
	}

	@SuppressWarnings("unused")
	private void atualizaTabelaProdutos(Item e) {

		if (listaItensCarrinho != null) {
			for (int i = 0; i < listaItensCarrinho.size(); i++) {
				if (e.getDescricao() == modelo.getValueAt(i, 0)) {
					modelo.removeRow(i);
					modelo.addRow(listaItensCarrinho.get(i));

				}
				modelo.addRow(listaItensCarrinho.get(i));

			}
		}

	}

	protected void bloqueiaTexto() {
		txtCidade.setEnabled(false);
		txtBairro.setEnabled(false);
		txtEndereco.setEnabled(false);
		txtNome.setEnabled(false);
		txtNumero.setEnabled(false);
		txtTelefone.setEnabled(false);

	}

	protected void desbloqueiaTexto() {
		txtCidade.setEnabled(true);
		txtBairro.setEnabled(true);
		txtEndereco.setEnabled(true);
		txtNome.setEnabled(true);
		txtNumero.setEnabled(true);
		txtTelefone.setEnabled(true);

	}

	@SuppressWarnings("unchecked")
	protected void atualizaBoxCliente() {

		boxClientes.removeAllItems();

		List<Object> listaNew = bancoCliente.buscar();

		for (int i = 0; i < listaNew.size(); i++) {
			Cliente cliente = (Cliente) listaNew.get(i);
			boxClientes.addItem(cliente.getNome());

		}
		listaClientes = listaNew;

	}

	@SuppressWarnings("unchecked")
	protected void carregaBoxProdutos() {

		boxItens.removeAllItems();

		List<Object> listaNew = bancoItens.buscar();

		for (int i = 0; i < listaNew.size(); i++) {
			Item item = (Item) listaNew.get(i);
			boxItens.addItem(item.getDescricao());

		}
		listaItens = listaNew;

	}

	@SuppressWarnings("unchecked")
	protected void atualizaBoxProdutos() {

		boxItens.removeAllItems();
		List<Object> listaNova = new ArrayList<Object>();
		List<Object> listaNew = bancoItens.buscar();

		for (int i = 0; i < listaNew.size(); i++) {
			for (int j = 0; j < listaItens.size(); j++) {
				if (listaItens != null) {
					Item item = (Item) listaItens.get(j);
					Item itemBanco = (Item) listaNew.get(i);
					if (itemBanco.getId() == item.getId()) {
						listaNova.add(itemBanco);
						boxItens.addItem(item.getDescricao());
						j = listaItens.size();

					}
				}

			}

		}
		listaItens = listaNova;

	}

	protected void limpaTexto() {
		txtBairro.setText("");
		txtCidade.setText("");
		txtEndereco.setText("");
		txtNome.setText("");
		txtNumero.setText("");
		txtTelefone.setText("");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comandoAcao = e.getActionCommand();

		if (comandoAcao.equals("carregar")) {

			int index = boxClientes.getSelectedIndex();
			Cliente cliente = (Cliente) VendaItem.this.listaClientes
					.get(index);
			txtCidade.setText(cliente.getCidade());
			txtBairro.setText(cliente.getBairro());
			txtEndereco.setText(cliente.getEndereco());
			txtNome.setText(cliente.getNome());
			txtNumero.setText(cliente.getNumero());
			txtTelefone.setText(cliente.getTelefone());

			boxClientes.setEnabled(false);
			btnCadastrar.setEnabled(false);
			btnCarregar.setEnabled(false);
			btnClienteDesconhecido.setEnabled(false);
			btnClienteDesconhecido.setVisible(false);
			btnVoltar.setVisible(true);
			btnCriar.setEnabled(false);
			btnAlterar.setEnabled(true);
			btnSalvar.setEnabled(false);
			btnHistorico.setEnabled(true);
			btnVender.setEnabled(true);
			bloqueiaTexto();

		}
		if (comandoAcao.equals("cadastrar")) {
			limpaTexto();
			boxClientes.setEnabled(false);
			btnCadastrar.setEnabled(false);
			btnCarregar.setEnabled(false);
			btnClienteDesconhecido.setEnabled(false);
			btnClienteDesconhecido.setVisible(false);
			btnVoltar.setVisible(true);
			btnCriar.setEnabled(true);
			btnAlterar.setEnabled(false);
			btnSalvar.setEnabled(false);
			btnHistorico.setEnabled(false);
			desbloqueiaTexto();

		}
		if (comandoAcao.equals("voltar")) {
			btnClienteDesconhecido.setEnabled(true);
			btnClienteDesconhecido.setVisible(true);
			btnVoltar.setVisible(false);
			boxClientes.setEnabled(true);
			btnCadastrar.setEnabled(true);
			btnCarregar.setEnabled(true);
			btnVender.setEnabled(false);
			btnCriar.setEnabled(false);
			btnAlterar.setEnabled(false);
			btnSalvar.setEnabled(false);
			btnHistorico.setEnabled(false);
			bloqueiaTexto();
			limpaTexto();

		}
		if (comandoAcao.equals("criar")) {
			// coletando informações das caixas de textos...
			// instanciando um cliente
			Cliente cliente = new Cliente(txtNome.getText(),
					txtTelefone.getText(), txtEndereco.getText(),
					txtNumero.getText(), txtBairro.getText(),
					txtCidade.getText());
			// criando o cliente no banco
			bancoCliente.criar(cliente);

			// limpa as caixas de texto
			bloqueiaTexto();
			atualizaBoxCliente();
			// botões que são desbloqueados e outros que são bloqueados
			boxClientes.setEnabled(true);
			btnCadastrar.setEnabled(true);
			btnCarregar.setEnabled(true);
			btnClienteDesconhecido.setEnabled(false);
			btnClienteDesconhecido.setVisible(false);
			btnVoltar.setVisible(true);
			btnVoltar.setEnabled(true);
			btnCriar.setEnabled(false);
			btnAlterar.setEnabled(true);
			btnSalvar.setEnabled(false);
			btnHistorico.setEnabled(true);
			boxClientes.setEnabled(false);
			listaItens.add(cliente);

		}
		if (comandoAcao.equals("salvar")) {
			// coletando informações das caixas de textos...
			// instanciando um cliente
			Cliente cliente = new Cliente(id, txtNome.getText(),
					txtTelefone.getText(), txtEndereco.getText(),
					txtNumero.getText(), txtBairro.getText(),
					txtCidade.getText());
			// criando o cliente no banco
			bancoCliente.atualizar(cliente);

			// limpa as caixas de texto
			bloqueiaTexto();

			// botões que são desbloqueados e outros que são bloqueados
			boxClientes.setEnabled(false);
			btnVender.setEnabled(true);
			btnCadastrar.setEnabled(true);
			btnCarregar.setEnabled(false);
			btnClienteDesconhecido.setEnabled(false);
			btnClienteDesconhecido.setVisible(false);
			btnVoltar.setVisible(true);
			btnVoltar.setEnabled(true);
			btnCriar.setEnabled(false);
			btnAlterar.setEnabled(true);
			btnSalvar.setEnabled(false);
			btnHistorico.setEnabled(true);
			atualizaBoxCliente();

		}
		if (comandoAcao.equals("alterar")) {
			Cliente cliente = (Cliente) VendaItem.this.listaClientes
					.get(boxClientes.getSelectedIndex());
			id = cliente.getId();
			desbloqueiaTexto();
			boxClientes.setEnabled(false);
			btnCadastrar.setEnabled(false);
			btnCarregar.setEnabled(false);
			btnClienteDesconhecido.setEnabled(false);
			btnClienteDesconhecido.setVisible(false);
			btnVoltar.setVisible(true);
			btnVoltar.setEnabled(true);
			btnCriar.setEnabled(false);
			btnAlterar.setEnabled(false);
			btnSalvar.setEnabled(true);
			btnHistorico.setEnabled(false);
			btnVender.setEnabled(false);

		}
		if (comandoAcao.equals("incluirProduto")) {

			int index = boxItens.getSelectedIndex();

			Item item = (Item) listaItens.get(index);
			float quantidade = Float.parseFloat(JOptionPane
					.showInputDialog("Produto "+item.getDescricao()+", Valor "+item.getPreco()+". Digite a quantidade : "));
			String descricao = item.getDescricao();
			float preco = (item.getPreco());
			int id = item.getId();

			Item itemCriado = new Item(id, descricao, quantidade, preco);

			listaItensCarrinho.add(itemCriado);
			modelo.addRow(itemCriado);
			// atualizaTabelaProdutos(e);

			totalDaVenda = (quantidade * preco) + totalDaVenda;

			txtTotalVenda.setText(String.valueOf(totalDaVenda));

			listaItens.remove(listaItens.get(index));
			boxItens.removeItemAt(index);

		}
		if (comandoAcao.equals("historico")) {

		}
		if (comandoAcao.equals("cancelarVenda")) {
			modelo.removeTudo();
			carregaBoxProdutos();
			totalDaVenda = 0;
			txtTotalVenda.setText("0.00");

		}
		if (comandoAcao.equals("finalizarVenda")) {

		}
		if (comandoAcao.equals("retirar")) {
			float total = (float) tableCarrinhoVenda.getValueAt(
					tableCarrinhoVenda.getSelectedRow(), 3);
			totalDaVenda = totalDaVenda - total;
			txtTotalVenda.setText(String.valueOf(totalDaVenda));
			int id = modelo.removeRow(tableCarrinhoVenda.getSelectedRow());

			Item item = bancoItens.buscarPorId(id);

			listaItens.add(item);
			atualizaBoxProdutos();

		}
		if (comandoAcao.equals("quantidade")) {

			float total = (float) tableCarrinhoVenda.getValueAt(
					tableCarrinhoVenda.getSelectedRow(), 3);
			totalDaVenda = totalDaVenda - total;
			txtTotalVenda.setText(String.valueOf(totalDaVenda));

			int index = tableCarrinhoVenda.getSelectedRow();
			int id = modelo.removeRow(index);
			Item item = bancoItens.buscarPorId(id);

			float quantidade = Float.parseFloat(JOptionPane
					.showInputDialog("Quantidade"));
			String descricao = item.getDescricao();
			float preco = (item.getPreco());

			Item itenNovo = new Item(id, descricao, quantidade, preco);

			listaItensCarrinho.add(itenNovo);
			modelo.addRow(itenNovo);
			// atualizaTabelaProdutos(e);

			totalDaVenda = (quantidade * preco) + totalDaVenda;

			txtTotalVenda.setText(String.valueOf(totalDaVenda));

		}
		if (comandoAcao.equals("")) {

		}
		requestFocusInWindow();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_F1):
			//CadastroItem cadastroItem = new CadastroItem();
			//cadastroItem.setVisible(true);
			//cadastroItem.setLocationRelativeTo(null);
			//cadastroItem.setResizable(false);
			dispose();
			break;

		case (KeyEvent.VK_F2):
			CompraItem compra = new CompraItem();
			compra.setVisible(true);
			compra.setLocationRelativeTo(null);
			compra.setResizable(false);
			dispose();
			break;

		case (KeyEvent.VK_F3):
			//CadastroCliente cadastroCliente = new CadastroCliente();
			//cadastroCliente.setVisible(true);
			//cadastroCliente.setLocationRelativeTo(null);
			//cadastroCliente.setResizable(false);
			dispose();
			break;
		}
		//Esse codigo abaixo fica impriminfo as teclas digitadas quando o foco esta nessa função!
		//System.out.println("Código da tecla: " + e.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
