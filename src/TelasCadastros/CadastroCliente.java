package TelasCadastros;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ClassesBin.Cliente;
import EstruturaVisual.Cadastro;
import Persistence.ClienteDAO;

import java.awt.Font;
import java.util.List;

@SuppressWarnings("serial")
public class CadastroCliente extends Cadastro{

	@SuppressWarnings("unused")
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtTelefone;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private JPanel panel;
	private JLabel lblCliente;
	private JLabel lblDescricao;
	private JLabel lblPreo;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblTelefone;
	private JLabel lblNumero;
	
	
	ClienteDAO bancoClientes = new ClienteDAO();
	private Cliente clienteCarregado = null;

	

	/**
	 * Create the frame.
	 */
	public CadastroCliente() {
		
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 105, 420, 175);
		painelPrincipal.add(panel);
		panel.setLayout(null);
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblCliente.setBounds(10, 10, 50, 20);
		panel.add(lblCliente);
		
		lblDescricao = new JLabel("Nome");
		lblDescricao.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblDescricao.setBounds(10, 40, 50, 20);
		panel.add(lblDescricao);
		
		lblPreo = new JLabel("Endereco");
		lblPreo.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblPreo.setBounds(10, 70, 50, 20);
		panel.add(lblPreo);
		
		lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblBairro.setBounds(10, 100, 50, 20);
		panel.add(lblBairro);
		
		lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblCidade.setBounds(10, 130, 50, 20);
		panel.add(lblCidade);
		
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblTelefone.setBounds(208, 130, 50, 20);
		panel.add(lblTelefone);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtNome.setBounds(66, 40, 344, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtEndereco.setBounds(76, 70, 334, 20);
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		txtBairro = new JTextField();
		txtBairro.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtBairro.setBounds(68, 100, 132, 20);
		panel.add(txtBairro);
		txtBairro.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(276, 130, 132, 20);
		panel.add(txtTelefone);
		
		txtCidade = new JTextField();
		txtCidade.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtCidade.setColumns(10);
		txtCidade.setBounds(66, 130, 132, 20);
		panel.add(txtCidade);
		
		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtNumero.setColumns(10);
		txtNumero.setBounds(276, 100, 132, 20);
		panel.add(txtNumero);
		
		lblNumero = new JLabel("Numero");
		lblNumero.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblNumero.setBounds(208, 100, 50, 20);
		panel.add(lblNumero);
		atualizarBox();
		
	}



	@Override
	public void campoTextoEnable(Boolean b) {
		txtBairro.setEnabled(b);
		txtCidade.setEnabled(b);
		txtEndereco.setEnabled(b);
		txtNome.setEnabled(b);
		txtNumero.setEnabled(b);
		txtTelefone.setEnabled(b);
		
	}



	



	@Override
	public void atualizarBox() {
		box.removeAllItems();
		
		List<Object> listaNew = bancoClientes.buscar();
		

		for (int i = 0; i < listaNew.size(); i++) {
			Cliente cliente = (Cliente) listaNew.get(i);
			box.addItem(cliente.getNome());

		}
		lista = listaNew;
		
	}



	@Override
	public void atualizaObjeto() {
		String nome=(String.valueOf(txtNome.getText()));
		String bairro=(txtBairro.getText());
		String cidade=(txtCidade.getText());
		String numero =(txtNumero.getText());
		String  telefone=(txtTelefone.getText());
		String endereco=(txtEndereco.getText());
		int id = clienteCarregado.getId();
		clienteCarregado = new Cliente(id, telefone, endereco, numero, bairro, cidade, nome);

		bancoClientes.atualizar(clienteCarregado);
		
	}



	@Override
	public void carregarDadosCampoTexto() {
		int index = box.getSelectedIndex();
		clienteCarregado = (Cliente) CadastroCliente.this.lista
				.get(index);
		lista.remove(index);
		
		txtNome.setText(clienteCarregado.getNome());
		txtEndereco.setText(clienteCarregado.getEndereco());
		txtNumero.setText(String.valueOf(clienteCarregado.getNome()));
		txtTelefone.setText(String.valueOf(clienteCarregado.getTelefone()));
		txtBairro.setText(clienteCarregado.getBairro());
		txtCidade.setText(clienteCarregado.getCidade());
		
	}



	@Override
	public void excluirItemSelecionado() {
		int index = box.getSelectedIndex();
		Cliente cliente = (Cliente) CadastroCliente.this.lista
				.get(index);
		int result = JOptionPane.showConfirmDialog(
				null,
				"Deseja Realmente Excluir o Cadastro do cliente "
						+ cliente.getNome() + "?");
		switch (result) {
		case 0:
			bancoClientes.deletar(cliente);
			break;

		default:
			break;
		}
		
	}



	@Override
	public void criaObjeto() {
		String nome = txtNome.getText();
		String endereco = txtEndereco.getText();
		String numero = txtNumero.getText();
		String telefone = txtTelefone.getText();
		String bairro = txtBairro.getText();
		String cidade = txtCidade.getText();

		Cliente cliente = new Cliente(telefone, endereco, numero, bairro, cidade, nome);
		bancoClientes.criar(cliente);
		
	}



	@Override
	public void limpaTexto() {
		txtNome.setText("");
		txtEndereco.setText("");
		txtNumero.setText("");
		txtTelefone.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		
	}



	



	
}
