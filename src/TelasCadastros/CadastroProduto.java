package TelasCadastros;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ClassesBin.Item;
import EstruturaVisual.Cadastro;
import Persistence.ItemDAO;

import java.awt.Font;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class CadastroProduto extends Cadastro {

	@SuppressWarnings("unused")
	private JPanel contentPane;
	private JPanel panel;
	private JTextField txtDescricao;
	private JTextField txtFornecedor;
	private JTextField txtPreco;
	private JTextField txtEst;
	private JTextField txtArmazena;
	private JLabel lblProduto;
	private JLabel lblDescrio;
	private JLabel lblFornecedor;
	private JLabel lblPreo;
	private JLabel lblEstoqueMin;
	private JLabel lblLocalArmazenamento;
	private ItemDAO bancoProduto = new ItemDAO();
	private Item produtoCarregado = null;


	/**
	 * Create the frame.
	 */
	public CadastroProduto() {
		
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 105, 420, 175);
		painelPrincipal.add(panel);
		panel.setLayout(null);
		
		lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblProduto.setBounds(10, 10, 128, 20);
		panel.add(lblProduto);
		
		 lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblDescrio.setBounds(10, 40, 128, 20);
		panel.add(lblDescrio);
		
		 lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblFornecedor.setBounds(10, 70, 128, 20);
		panel.add(lblFornecedor);
		
		 lblPreo = new JLabel("Pre\u00E7o");
		lblPreo.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblPreo.setBounds(10, 100, 128, 20);
		panel.add(lblPreo);
		
		 lblEstoqueMin = new JLabel("Estoque Min");
		lblEstoqueMin.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblEstoqueMin.setBounds(180, 100, 128, 20);
		panel.add(lblEstoqueMin);
		
		 lblLocalArmazenamento = new JLabel("Local Armazenamento");
		lblLocalArmazenamento.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblLocalArmazenamento.setBounds(10, 130, 128, 20);
		panel.add(lblLocalArmazenamento);
		
		txtDescricao = new JTextField();
		txtDescricao.setEnabled(false);
		txtDescricao.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtDescricao.setBounds(84, 40, 315, 20);
		panel.add(txtDescricao);
		txtDescricao.setColumns(10);
		
		txtFornecedor = new JTextField();
		txtFornecedor.setEnabled(false);
		txtFornecedor.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtFornecedor.setBounds(84, 70, 315, 20);
		panel.add(txtFornecedor);
		txtFornecedor.setColumns(10);
		
		txtPreco = new JTextField();
		txtPreco.setEnabled(false);
		txtPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//esse evento não deixa o usuario digitar letras, ao digitar o sistema apaga automaticamente
				if ((e.getKeyCode() < 48 || e.getKeyCode() > 57) 
						&& (e.getKeyCode() < 96 || e.getKeyCode() > 105) 
						&& (e.getKeyCode() !=8 && e.getKeyCode() != 46))
						//&& (e.getKeyCode() !=110 && e.getKeyCode() != 10)
					if (txtPreco.getText().length() > 0)
					txtPreco.setText(txtPreco.getText()
							.substring( 0 , txtPreco.getText().length()-1));
				
				if (e.getKeyCode()==44) txtPreco.setText(txtPreco.getText()+".");
				if (e.getKeyCode()==110) txtPreco.setText(txtPreco.getText()+".");
				
			}
		});
		txtPreco.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtPreco.setBounds(84, 100, 86, 20);
		panel.add(txtPreco);
		txtPreco.setColumns(10);
		
		txtEst = new JTextField();
		txtEst.setEnabled(false);
		txtEst.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//esse evento não deixa o usuario digitar letras, ao digitar o sistema apaga automaticamente
				if ((e.getKeyCode() < 48 || e.getKeyCode() > 57) 
						&& (e.getKeyCode() < 96 || e.getKeyCode() > 105) 
						&& (e.getKeyCode() !=8 && e.getKeyCode() != 46))
						//&& (e.getKeyCode() !=110 && e.getKeyCode() != 10)
					if (txtEst.getText().length() > 0)
						txtEst.setText(txtEst.getText()
							.substring( 0 , txtEst.getText().length()-1));
				
				if (e.getKeyCode()==44) txtEst.setText(txtEst.getText()+".");
				if (e.getKeyCode()==110) txtEst.setText(txtEst.getText()+".");
				
			}
		});
		txtEst.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtEst.setBounds(254, 100, 100, 20);
		panel.add(txtEst);
		txtEst.setColumns(10);
		
		txtArmazena = new JTextField();
		txtArmazena.setEnabled(false);
		txtArmazena.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		txtArmazena.setBounds(140, 130, 86, 20);
		panel.add(txtArmazena);
		txtArmazena.setColumns(10);
		atualizarBox();
	}


	@Override
	public void campoTextoEnable(Boolean b) {
		txtArmazena.setEnabled(b);
		txtDescricao.setEnabled(b);
		txtEst.setEnabled(b);
		txtFornecedor.setEnabled(b);
		txtPreco.setEnabled(b);
		
	}


	@Override
	public void atualizarBox() {
		box.removeAllItems();

		List<Object> listaNew = bancoProduto.buscar();

		for (int i = 0; i < listaNew.size(); i++) {
			Item item = (Item) listaNew.get(i);
			box.addItem(item.getDescricao());

		}
		lista = listaNew;
		
	}


	@Override
	public void atualizaObjeto() {
		String descricao = txtDescricao.getText();
		float quantidade =produtoCarregado.getQuantidade();
		String local=(txtArmazena.getText());
		
		String fornecedor = txtFornecedor.getText();
		float estoqueMin =Float.valueOf(txtEst.getText());
		float custo=produtoCarregado.getCusto();
		
		float preco =Float.valueOf(txtPreco.getText());
		float desconto=produtoCarregado.getDesconto();
		
		
		int id = produtoCarregado.getId();
		produtoCarregado = new Item(id, descricao, fornecedor, local, estoqueMin, quantidade, custo, preco, desconto);

		bancoProduto.atualizar(produtoCarregado);
		
		
	}


	@Override
	public void carregarDadosCampoTexto() {
		int index = box.getSelectedIndex();
		produtoCarregado = (Item) CadastroProduto.this.lista
				.get(index);
		lista.remove(index);
		
		txtArmazena.setText(produtoCarregado.getLocal());
		txtDescricao.setText(produtoCarregado.getDescricao());
		txtEst.setText(String.valueOf(produtoCarregado.getEstoqueMin()));
		txtFornecedor.setText(produtoCarregado.getFornecedor());
		txtPreco.setText(String.valueOf(produtoCarregado.getPreco()));
		
	}


	@Override
	public void excluirItemSelecionado() {
		int index = box.getSelectedIndex();
		Item produto = (Item) CadastroProduto.this.lista
				.get(index);
		int result = JOptionPane.showConfirmDialog(
				null,
				"Deseja Realmente Excluir o Cadastro do Produto "
						+ produto.getDescricao() + "?");
		switch (result) {
		case 0:
			bancoProduto.deletar(produto);
			break;

		default:
			break;
		}
		
	}


	@Override
	public void criaObjeto() {
		String descricao = txtDescricao.getText();
		String local=(txtArmazena.getText());
		
		String fornecedor = txtFornecedor.getText();
		float estoqueMin =Float.valueOf(txtEst.getText());
		
		float preco =Float.valueOf(txtPreco.getText());
		
		

		Item produto = new Item(descricao, fornecedor, local, estoqueMin, preco);
		bancoProduto.criar(produto);

		
	}


	@Override
	public void limpaTexto() {
		txtArmazena.setText("");
		txtDescricao.setText("");
		txtEst.setText("");
		txtFornecedor.setText("");
		txtPreco.setText("");
		
	}


	
}
