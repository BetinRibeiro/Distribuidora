package EstruturaVisual;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import Interfaces.ContratoMovimentacao;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public abstract class Movimentacao extends JFrame implements
		 ActionListener, ContratoMovimentacao {

	protected int idMovimentoAtual;
	protected JPanel contentPane;
	protected JTextField txtId;
	protected JTextField txtImpostoDesconto;
	protected JTextField txtTotalMovimento;
	protected int sentido;
	protected JTextField txtData;
	protected JComboBox<String> boxPessoa;
	protected JButton btnCancelar;
	protected JButton btnFinalizarMovimento;
	protected JButton btnAnterior;
	protected JButton btnPosterior;
	protected JButton btnAlterarMovimento;
	protected JButton btnNovoMovimento;
	protected JButton btnRetornarSairPagina;
	protected JComboBox<String> boxProdutos;
	protected JButton btnInserir;
	protected JButton btnRemoverProduto;
	protected JButton btnAlterarQuantidade;
	protected JButton btnFinalizarInsersão;
	protected JLabel lblData;
	protected JLabel lblTituloDoPainel;
	protected JLabel lblPessoa;
	protected JLabel Total;
	protected JLabel lblValorAdicional;
	protected JLabel lblProduto;
	protected JScrollPane scrollPane;
	

	/**
	 * Create the frame.
	 */
	public Movimentacao() {
		setTitle("Titulo pagina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel painelDirecional = new JPanel();
		painelDirecional.setBounds(10, 10, 550, 108);
		painelDirecional.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(painelDirecional);
		painelDirecional.setLayout(null);

		btnAnterior = new JButton("");
		btnAnterior.setIcon(new ImageIcon(Movimentacao.class
				.getResource("/EstruturaVisual/atras.png")));
		btnAnterior.setBounds(10, 11, 110, 85);
		btnAnterior.setActionCommand("anterior");
		painelDirecional.add(btnAnterior);
		btnAnterior.addActionListener(this);

		btnPosterior = new JButton("");
		btnPosterior.setIcon(new ImageIcon(Movimentacao.class
				.getResource("/EstruturaVisual/frente.png")));
		btnPosterior.setBounds(429, 11, 110, 85);
		btnPosterior.setActionCommand("posterior");
		painelDirecional.add(btnPosterior);
		btnPosterior.addActionListener(this);

		btnAlterarMovimento = new JButton("Refazer");
		btnAlterarMovimento.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btnAlterarMovimento.setBounds(130, 71, 90, 25);
		btnAlterarMovimento.setActionCommand("refazer");
		painelDirecional.add(btnAlterarMovimento);
		btnAlterarMovimento.addActionListener(this);

		btnNovoMovimento = new JButton("Novo");
		btnNovoMovimento.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btnNovoMovimento.setBounds(228, 71, 90, 25);
		btnNovoMovimento.setActionCommand("novo");
		painelDirecional.add(btnNovoMovimento);
		btnNovoMovimento.addActionListener(this);

		btnRetornarSairPagina = new JButton("Voltar");
		btnRetornarSairPagina.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btnRetornarSairPagina.setBounds(329, 71, 90, 25);
		btnRetornarSairPagina.setActionCommand("voltarPag");
		painelDirecional.add(btnRetornarSairPagina);
		btnRetornarSairPagina.addActionListener(this);

		txtId = new JTextField();
		txtId.setBorder(null);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setFont(new Font("Roboto Cn", Font.PLAIN, 35));
		txtId.setEnabled(false);
		txtId.setBounds(133, 11, 284, 49);
		painelDirecional.add(txtId);
		txtId.setColumns(10);

		JPanel painelConteudo = new JPanel();
		painelConteudo.setBorder(new LineBorder(new Color(0, 0, 0)));
		painelConteudo.setBounds(10, 128, 550, 351);
		contentPane.add(painelConteudo);
		painelConteudo.setLayout(null);

		lblProduto = new JLabel("Item");
		lblProduto.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblProduto.setBounds(10, 35, 46, 25);
		painelConteudo.add(lblProduto);

		boxProdutos = new JComboBox<String>();
		boxProdutos.setEnabled(false);
		boxProdutos.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		boxProdutos.setBounds(66, 35, 334, 25);
		painelConteudo.add(boxProdutos);

		btnInserir = new JButton("Inserir ");
		btnInserir.setEnabled(false);
		btnInserir.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btnInserir.setBounds(10, 65, 125, 25);
		btnInserir.setActionCommand("inserir");
		btnInserir.addActionListener(this);
		
		painelConteudo.add(btnInserir);

		btnRemoverProduto = new JButton("Remover");
		btnRemoverProduto.setEnabled(false);
		btnRemoverProduto.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btnRemoverProduto.setBounds(280, 65, 125, 25);
		painelConteudo.add(btnRemoverProduto);
		btnRemoverProduto.setActionCommand("remover");
		btnRemoverProduto.addActionListener(this);

		btnAlterarQuantidade = new JButton("Alterar");
		btnAlterarQuantidade.setEnabled(false);
		btnAlterarQuantidade.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btnAlterarQuantidade.setBounds(145, 65, 125, 25);
		painelConteudo.add(btnAlterarQuantidade);
		btnAlterarQuantidade.setActionCommand("alterarQuant");
		btnAlterarQuantidade.addActionListener(this);

		btnFinalizarInsersão = new JButton("Concluir");
		btnFinalizarInsersão.setEnabled(false);
		btnFinalizarInsersão.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btnFinalizarInsersão.setBounds(415, 65, 125, 25);
		painelConteudo.add(btnFinalizarInsersão);
		btnFinalizarInsersão.setActionCommand("finalizarInserssão");
		btnFinalizarInsersão.addActionListener(this);

		scrollPane = new JScrollPane((Component) null);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 100, 530, 180);
		painelConteudo.add(scrollPane);
		

		

		lblValorAdicional = new JLabel("---");
		lblValorAdicional.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblValorAdicional.setBounds(10, 290, 114, 20);
		painelConteudo.add(lblValorAdicional);

		txtImpostoDesconto = new JTextField();
		txtImpostoDesconto.setText("0.00");
		txtImpostoDesconto.setEnabled(false);
		txtImpostoDesconto.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtImpostoDesconto.setColumns(10);
		txtImpostoDesconto.setBounds(111, 290, 67, 20);
		painelConteudo.add(txtImpostoDesconto);

		Total = new JLabel("Total");
		Total.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		Total.setBounds(230, 290, 110, 20);
		painelConteudo.add(Total);

		txtTotalMovimento = new JTextField();
		txtTotalMovimento.setText("0.00");
		txtTotalMovimento.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtTotalMovimento.setEnabled(false);
		txtTotalMovimento.setEditable(false);
		txtTotalMovimento.setColumns(10);
		txtTotalMovimento.setBorder(null);
		txtTotalMovimento.setBounds(332, 290, 73, 20);
		painelConteudo.add(txtTotalMovimento);

		lblPessoa = new JLabel("----");
		lblPessoa.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblPessoa.setBounds(10, 320, 90, 20);
		painelConteudo.add(lblPessoa);

		btnFinalizarMovimento = new JButton("----");
		btnFinalizarMovimento.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btnFinalizarMovimento.setEnabled(false);
		btnFinalizarMovimento.setBounds(415, 318, 125, 25);
		painelConteudo.add(btnFinalizarMovimento);
		btnFinalizarMovimento.setActionCommand("finalizarMovimentacao");
		btnFinalizarMovimento.addActionListener(this);
		

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(415, 290, 125, 25);
		painelConteudo.add(btnCancelar);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(this);

		boxPessoa = new JComboBox<String>();
		boxPessoa.setEnabled(false);
		boxPessoa.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		boxPessoa.setBounds(110, 320, 290, 20);
		painelConteudo.add(boxPessoa);

		lblTituloDoPainel = new JLabel("---");
		lblTituloDoPainel.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblTituloDoPainel.setBounds(10, 10, 166, 20);
		painelConteudo.add(lblTituloDoPainel);

		txtData = new JTextField();
		txtData.setBorder(null);
		txtData.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtData.setEnabled(false);
		txtData.setBounds(416, 10, 124, 50);
		painelConteudo.add(txtData);
		txtData.setColumns(10);

		lblData = new JLabel("Data");
		lblData.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblData.setBounds(353, 10, 46, 20);
		painelConteudo.add(lblData);

		JPanel painelRodaPe = new JPanel();
		painelRodaPe.setBorder(new LineBorder(new Color(0, 0, 0)));
		painelRodaPe.setBounds(10, 490, 550, 46);
		contentPane.add(painelRodaPe);
		painelRodaPe.setLayout(null);
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		String comandoAcao = e.getActionCommand();

		System.out.println(comandoAcao);
		switch (comandoAcao) {
		case "anterior":
			btnPosterior.setEnabled(true);
			sentido=-1;//identifica qual botao foi clicado
			carregaItenAnterrior();
			break;

		case "posterior":
			sentido=1;//identifica qual botao foi clicado
			carregaItemPosterior();
			break;
			
		case "refazer":
			JOptionPane.showMessageDialog(null, "Você terá que refazer toda a compra novamente. lembre-se que é só cancelar para desistir da operação.");
			liberaAlteracoes();
			break;
			
		case "novo":
			liberaNovoCadastro();
			break;
			
		case "voltarPag":
			voltaParaPaginaAnterior();
			break;
			
		case "inserir":
			inserirProdutoNaLista();
			break;
			
		case "alterarQuant":
			alterarQuantidadeProdutoLista();
			break;
			
		case "remover":
			removeProdutoDaLista();
			break;
			
		case "finalizarInserssão":
			finalizaInsercaoDeProdutos();
			break;
			
		case "cancelar":
			cancelaMovimentacaoAtual();
			break;
			
		
			
		case "finalizarMovimentacao":
			finalizarMovimento();
			break;
			
		

	}
}}
