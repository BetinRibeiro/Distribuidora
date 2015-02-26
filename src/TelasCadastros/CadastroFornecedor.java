package TelasCadastros;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ClassesBin.Fornecedor;
import EstruturaVisual.Cadastro;
import Persistence.FornecedorDAO;

import java.awt.Font;
import java.util.List;

@SuppressWarnings("serial")
public class CadastroFornecedor extends Cadastro {

	@SuppressWarnings("unused")
	private JPanel contentPane;
	private JPanel panel;
	private JTextField txtRazao;
	private JTextField txtCnpj;
	private JTextField txtTelefone;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtNumero;
	private JTextField txtCidade;
	private FornecedorDAO bancoFornecedor = new FornecedorDAO();
	Fornecedor fornecedorCarregado= null;

	/**
	 * Create the frame.
	 */
	public CadastroFornecedor() {

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 105, 420, 175);
		painelPrincipal.add(panel);
		panel.setLayout(null);

		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblFornecedor.setBounds(10, 11, 67, 20);
		panel.add(lblFornecedor);

		JLabel lblRazoSocial = new JLabel("R. Social");
		lblRazoSocial.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblRazoSocial.setBounds(10, 40, 67, 20);
		panel.add(lblRazoSocial);

		JLabel lblCnpj = new JLabel("CNPj");
		lblCnpj.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblCnpj.setBounds(237, 40, 67, 20);
		panel.add(lblCnpj);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblTelefone.setBounds(10, 70, 67, 20);
		panel.add(lblTelefone);

		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblEndereo.setBounds(10, 100, 67, 20);
		panel.add(lblEndereo);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblBairro.setBounds(10, 130, 67, 20);
		panel.add(lblBairro);

		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblNumero.setBounds(297, 70, 55, 20);
		panel.add(lblNumero);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblCidade.setBounds(223, 130, 55, 20);
		panel.add(lblCidade);

		txtRazao = new JTextField();
		txtRazao.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtRazao.setBounds(87, 41, 140, 20);
		panel.add(txtRazao);
		txtRazao.setColumns(10);

		txtCnpj = new JTextField();
		txtCnpj.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(277, 40, 133, 20);
		panel.add(txtCnpj);

		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(87, 70, 200, 20);
		panel.add(txtTelefone);

		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(87, 100, 323, 20);
		panel.add(txtEndereco);

		txtBairro = new JTextField();
		txtBairro.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtBairro.setColumns(10);
		txtBairro.setBounds(87, 130, 126, 20);
		panel.add(txtBairro);

		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtNumero.setColumns(10);
		txtNumero.setBounds(355, 70, 55, 20);
		panel.add(txtNumero);

		txtCidade = new JTextField();
		txtCidade.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtCidade.setColumns(10);
		txtCidade.setBounds(277, 130, 133, 20);
		panel.add(txtCidade);
		atualizarBox();
	}

	@Override
	public void campoTextoEnable(Boolean b) {
		txtBairro.setEditable(b);
		txtCidade.setEditable(b);
		txtCnpj.setEditable(b);
		txtEndereco.setEditable(b);
		txtNumero.setEditable(b);
		txtRazao.setEditable(b);
		txtTelefone.setEditable(b);

	}

	@Override
	public void atualizarBox() {
		box.removeAllItems();

		List<Object> listaNew = bancoFornecedor.buscar();

		for (int i = 0; i < listaNew.size(); i++) {
			Fornecedor fornecedor = (Fornecedor) listaNew.get(i);
			box.addItem(fornecedor.getRazaoSocial());

		}
		lista = listaNew;

	}

	@Override
	public void atualizaObjeto() {
		String razaoSocial = txtRazao.getText();
		String cnpj=txtCnpj.getText();
		String bairro=(txtBairro.getText());
		String cidade=(txtCidade.getText());
		String numero =(txtNumero.getText());
		String  telefone=(txtTelefone.getText());
		String endereco=(txtEndereco.getText());
		int id = fornecedorCarregado.getId();
		fornecedorCarregado = new Fornecedor(id, telefone, endereco, numero, bairro, cidade, cnpj, razaoSocial);

		bancoFornecedor.atualizar(fornecedorCarregado);

	}

	@Override
	public void carregarDadosCampoTexto() {
		int index = box.getSelectedIndex();
		fornecedorCarregado = (Fornecedor) CadastroFornecedor.this.lista
				.get(index);
		lista.remove(index);
		
		txtRazao.setText(fornecedorCarregado.getRazaoSocial());
		txtCnpj.setText(fornecedorCarregado.getCnpj());
		txtBairro.setText(String.valueOf(fornecedorCarregado.getBairro()));
		txtCidade.setText(String.valueOf(fornecedorCarregado.getCidade()));
		txtBairro.setText(fornecedorCarregado.getBairro());
		txtNumero.setText(fornecedorCarregado.getNumero());
		txtTelefone.setText(fornecedorCarregado.getTelefone());
		txtEndereco.setText(fornecedorCarregado.getEndereco());
	}

	@Override
	public void excluirItemSelecionado() {
		int index = box.getSelectedIndex();
		Fornecedor fornecedor = (Fornecedor) CadastroFornecedor.this.lista
				.get(index);
		int result = JOptionPane.showConfirmDialog(
				null,
				"Deseja Realmente Excluir o Cadastro do Fornecedor "
						+ fornecedor.getRazaoSocial() + "?");
		switch (result) {
		case 0:
			bancoFornecedor.deletar(fornecedor);
			break;

		default:
			break;
		}
		

	}

	@Override
	public void criaObjeto() {
		String cnpj = txtCnpj.getText();
		String razaoSocial = txtRazao.getText();
		String endereco = txtEndereco.getText();
		String numero = txtNumero.getText();
		String telefone = txtTelefone.getText();
		String bairro = txtBairro.getText();
		String cidade = txtCidade.getText();

		Fornecedor fornecedor = new Fornecedor(telefone, endereco, numero, bairro, cidade, cnpj, razaoSocial);
		bancoFornecedor.criar(fornecedor);

	}

	@Override
	public void limpaTexto() {
		txtBairro.setText("");
		txtCidade.setText("");
		txtCnpj.setText("");
		txtEndereco.setText("");
		txtNumero.setText("");
		txtRazao.setText("");
		txtTelefone.setText("");

	}

}
