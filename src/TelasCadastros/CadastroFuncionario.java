package TelasCadastros;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ClassesBin.Funcionario;
import EstruturaVisual.Cadastro;
import Persistence.FuncionarioDAO;

	@SuppressWarnings("serial")
	public class CadastroFuncionario extends Cadastro {

		@SuppressWarnings("unused")
		private JPanel contentPane;
		private JPanel panel;
		private JTextField txtNome;
		private JTextField txtCpf;
		private JTextField txtTelefone;
		private JTextField txtEndereco;
		private JTextField txtBairro;
		private JTextField txtNumero;
		private JTextField txtCidade;
		private FuncionarioDAO bancoFuncionario = new FuncionarioDAO();
		Funcionario funcionarioCarregado = null;

		
		/**
		 * Create the frame.
		 */
		public CadastroFuncionario() {
			
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(10, 105, 420, 175);
			painelPrincipal.add(panel);
			panel.setLayout(null);
			
			JLabel lblFuncionario = new JLabel("Funcionario");
			lblFuncionario.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
			lblFuncionario.setBounds(10, 11, 67, 20);
			panel.add(lblFuncionario);
			
			JLabel lblRazoSocial = new JLabel("Nome");
			lblRazoSocial.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
			lblRazoSocial.setBounds(10, 40, 67, 20);
			panel.add(lblRazoSocial);
			
			JLabel lblCnpj = new JLabel("CPF");
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
			
			txtNome = new JTextField();
			txtNome.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
			txtNome.setBounds(87, 41, 140, 20);
			panel.add(txtNome);
			txtNome.setColumns(10);
			
			txtCpf = new JTextField();
			txtCpf.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
			txtCpf.setColumns(10);
			txtCpf.setBounds(277, 40, 133, 20);
			panel.add(txtCpf);
			
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
		}


		@Override
		public void campoTextoEnable(Boolean b) {
			txtBairro.setEditable(b);
			txtCidade.setEditable(b);
			txtCpf.setEditable(b);
			txtEndereco.setEditable(b);
			txtNumero.setEditable(b);
			txtNome.setEditable(b);
			txtTelefone.setEditable(b);
			
		}


		@Override
		public void atualizarBox() {
			box.removeAllItems();

			List<Object> listaNew = bancoFuncionario.buscar();

			for (int i = 0; i < listaNew.size(); i++) {
				Funcionario funcionario = (Funcionario) listaNew.get(i);
				box.addItem(funcionario.getNome());

			}
			lista = listaNew;
			
		}


		@Override
		public void atualizaObjeto() {
			String nome = txtNome.getText();
			String cpf=txtCpf.getText();
			String bairro=(txtBairro.getText());
			String cidade=(txtCidade.getText());
			String numero =(txtNumero.getText());
			String  telefone=(txtTelefone.getText());
			String endereco=(txtEndereco.getText());
			int id = funcionarioCarregado.getId();
			funcionarioCarregado = new Funcionario(id, telefone, endereco, numero, bairro, cidade, nome, cpf);

			bancoFuncionario.atualizar(funcionarioCarregado);
			
		}


		@Override
		public void carregarDadosCampoTexto() {
			int index = box.getSelectedIndex();
			funcionarioCarregado = (Funcionario) CadastroFuncionario.this.lista
					.get(index);
			lista.remove(index);
			
			txtNome.setText(funcionarioCarregado.getNome());
			txtCpf.setText(funcionarioCarregado.getCpf());
			txtBairro.setText(String.valueOf(funcionarioCarregado.getBairro()));
			txtCidade.setText(String.valueOf(funcionarioCarregado.getCidade()));
			txtBairro.setText(funcionarioCarregado.getBairro());
			txtNumero.setText(funcionarioCarregado.getNumero());
			txtTelefone.setText(funcionarioCarregado.getTelefone());
			txtEndereco.setText(funcionarioCarregado.getEndereco());
			
		}


		@Override
		public void excluirItemSelecionado() {
			int index = box.getSelectedIndex();
			Funcionario funcionario = (Funcionario) CadastroFuncionario.this.lista
					.get(index);
			int result = JOptionPane.showConfirmDialog(
					null,
					"Deseja Realmente Excluir o Cadastro do funcionario "
							+ funcionario.getNome() + "?");
			switch (result) {
			case 0:
				bancoFuncionario.deletar(funcionario);
				break;

			default:
				break;
			}
			
		}


		@Override
		public void criaObjeto() {
			String cpf = txtCpf.getText();
			String nome = txtNome.getText();
			String endereco = txtEndereco.getText();
			String numero = txtNumero.getText();
			String telefone = txtTelefone.getText();
			String bairro = txtBairro.getText();
			String cidade = txtCidade.getText();

			Funcionario funcionario = new Funcionario(telefone, endereco, numero, bairro, cidade, nome, cpf);
			bancoFuncionario.criar(funcionario);

			
		}


		@Override
		public void limpaTexto() {
			txtBairro.setText("");
			txtCidade.setText("");
			txtCpf.setText("");
			txtEndereco.setText("");
			txtNumero.setText("");
			txtNome.setText("");
			txtTelefone.setText("");
			
		}


		


}
