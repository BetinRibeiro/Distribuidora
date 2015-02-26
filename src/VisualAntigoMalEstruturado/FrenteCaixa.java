package VisualAntigoMalEstruturado;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrenteCaixa extends JFrame {

	private JPanel contentPane;
	private JTable table;

	

	/**
	 * Create the frame.
	 */
	public FrenteCaixa() {
		setTitle("RIBEIRO DISTRIBUIDORA");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1373, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 1342, 92);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 650, 1342, 45);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 107, 565, 540);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblPesquisarCliente = new JLabel("Pesquisar Cliente");
		lblPesquisarCliente.setBounds(10, 50, 98, 25);
		panel_2.add(lblPesquisarCliente);
		
		JButton btnVincularCliente = new JButton("Vincular Cliente");
		btnVincularCliente.setBounds(10, 15, 130, 25);
		panel_2.add(btnVincularCliente);
		
		JButton btnInventario = new JButton("Inventario");
		btnInventario.setBounds(415, 16, 130, 25);
		panel_2.add(btnInventario);
		
		JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
		btnCadastrarCliente.setBounds(145, 15, 130, 25);
		panel_2.add(btnCadastrarCliente);
		
		JComboBox boxClientes = new JComboBox();
		boxClientes.setBounds(134, 50, 421, 25);
		panel_2.add(boxClientes);
		
		JButton btnAlterarCliente = new JButton("Alterar Cliente");
		btnAlterarCliente.setVisible(false);
		btnAlterarCliente.setBounds(145, 15, 130, 25);
		panel_2.add(btnAlterarCliente);
		
		JButton btnCancelarVenda = new JButton("Cancelar Venda");
		btnCancelarVenda.setBounds(280, 15, 130, 25);
		panel_2.add(btnCancelarVenda);
		
		JLabel lblPesquisarProduto = new JLabel("Pesquisar Produto");
		lblPesquisarProduto.setBounds(10, 85, 108, 25);
		panel_2.add(lblPesquisarProduto);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(134, 85, 421, 25);
		panel_2.add(comboBox);
		
		JButton btnConsultarHistorico = new JButton("Consultar Historico");
		btnConsultarHistorico.setBounds(145, 120, 130, 23);
		panel_2.add(btnConsultarHistorico);
		
		JButton btnIncluirProduto = new JButton("Incluir Produto");
		btnIncluirProduto.setBounds(10, 120, 130, 25);
		panel_2.add(btnIncluirProduto);
		
		JButton btnAlterarQuantidade = new JButton("Alterar Quantidade");
		btnAlterarQuantidade.setBounds(10, 160, 130, 25);
		panel_2.add(btnAlterarQuantidade);
		
		JButton btnRetirarProduto = new JButton("Retirar Produto");
		btnRetirarProduto.setBounds(10, 200, 130, 25);
		panel_2.add(btnRetirarProduto);
		
		JButton btnClientePadrao = new JButton("Cliente padr\u00E3o");
		btnClientePadrao.setVisible(false);
		btnClientePadrao.setBounds(10, 15, 130, 25);
		panel_2.add(btnClientePadrao);
		
		JButton btnConfirmarCliente = new JButton("Confirmar Cliente");
		btnConfirmarCliente.setVisible(false);
		btnConfirmarCliente.setBounds(415, 15, 130, 25);
		panel_2.add(btnConfirmarCliente);
		
		JButton btnFinalizarVenda = new JButton("Finalizar Venda");
		btnFinalizarVenda.setBounds(145, 200, 130, 25);
		panel_2.add(btnFinalizarVenda);
		
		JButton btnDarDesconto = new JButton("Dar Desconto");
		btnDarDesconto.setBounds(145, 160, 130, 25);
		panel_2.add(btnDarDesconto);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 238, 545, 291);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144)));
		scrollPane.setBounds(0, 0, 545, 291);
		panel_4.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(585, 114, 396, 525);
		contentPane.add(panel_3);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(991, 114, 361, 525);
		contentPane.add(panel_5);
	}
}
