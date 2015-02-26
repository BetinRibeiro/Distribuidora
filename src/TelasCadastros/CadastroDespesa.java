package TelasCadastros;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import ClassesBin.Despesa;
import EstruturaVisual.Cadastro;
import Persistence.DespesaDAO;

import java.awt.Font;
import java.sql.Date;
import java.util.List;

@SuppressWarnings("serial")
public class CadastroDespesa extends Cadastro {

	@SuppressWarnings("unused")
	private JPanel contentPane;
	private JPanel panel;
	private JTextField txtDescricao;
	private JComboBox<String> boxClasse;
	private DespesaDAO bancoDespesas = new DespesaDAO();
	Despesa despesaCarregado = null;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CadastroDespesa() {

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 105, 420, 175);
		painelPrincipal.add(panel);
		panel.setLayout(null);

		JLabel lblDespesa = new JLabel("Despesa");
		lblDespesa.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblDespesa.setBounds(10, 10, 46, 20);
		panel.add(lblDespesa);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o");
		lblDescricao.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblDescricao.setBounds(10, 40, 83, 20);
		panel.add(lblDescricao);

		JLabel lblClassificao = new JLabel("Classifica\u00E7\u00E3o");
		lblClassificao.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblClassificao.setBounds(10, 70, 83, 20);
		panel.add(lblClassificao);

		txtDescricao = new JTextField();
		txtDescricao.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtDescricao.setBounds(103, 40, 230, 20);
		panel.add(txtDescricao);
		txtDescricao.setColumns(10);

		String[] lista = { "Estocagem e transporte", "Perda de Estoques",
				"Administrativas Gerais", "Mao de obra", "Manutenção",
				"P&D, Design e Inovaçao", "Treinamento", "Depreciaçao",
				"Instalaçao", "Compra de Informaçao",
				"Responsabilidade Social", "Mix Promocional",
				"Despesas Financeiras", "Despesas diversas" };

		boxClasse = new JComboBox(lista);
		boxClasse.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		boxClasse.setBounds(103, 70, 230, 20);
		panel.add(boxClasse);
		atualizarBox();
	}

	@Override
	public void campoTextoEnable(Boolean b) {
		txtDescricao.setEditable(b);
		boxClasse.setEnabled(b);

	}

	@Override
	public void atualizarBox() {
		box.removeAllItems();
		System.out.println(box);

		List<Object> listaNew = bancoDespesas.buscar();

		for (int i = 0; i < listaNew.size(); i++) {
			Despesa despesa = (Despesa) listaNew.get(i);
			box.addItem(despesa.getDescricao());
			System.out.println(despesa.getDescricao());

		}
		lista = listaNew;

	}

	@Override
	public void atualizaObjeto() {
		String descricao = (String.valueOf(txtDescricao.getText()));
		String classificacao = (String) (boxClasse.getSelectedItem());
		Date data = despesaCarregado.getData();
		float valor = despesaCarregado.getValor();
		int id = despesaCarregado.getId();
		despesaCarregado = new Despesa(id, descricao, data, valor,
				classificacao);

		bancoDespesas.atualizar(despesaCarregado);

	}

	@Override
	public void carregarDadosCampoTexto() {
		int index = box.getSelectedIndex();
		despesaCarregado = (Despesa) CadastroDespesa.this.lista.get(index);
		System.out.println(despesaCarregado.getDescricao());
		System.out.println(despesaCarregado.getId());
		lista.remove(index);

		txtDescricao.setText(despesaCarregado.getDescricao());

	}

	@Override
	public void excluirItemSelecionado() {
		int index = box.getSelectedIndex();
		Despesa despesa = (Despesa) CadastroDespesa.this.lista.get(index);
		int result = JOptionPane.showConfirmDialog(
				null,
				"Deseja Realmente Excluir o Cadastro da despesa "
						+ despesa.getDescricao() + "?");
		switch (result) {
		case 0:
			bancoDespesas.deletar(despesa);
			break;

		default:
			break;
		}

	}

	@Override
	public void criaObjeto() {
		String descricao = txtDescricao.getText();
		String classificacao = (String) boxClasse.getSelectedItem();

		Despesa despesa = new Despesa(descricao, classificacao);
		despesa.setData(new Date(System.currentTimeMillis()));
		bancoDespesas.criar(despesa);

	}

	@Override
	public void limpaTexto() {
		txtDescricao.setText("");

	}

}
