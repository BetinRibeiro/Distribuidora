package EstruturaVisual;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;

import Interfaces.ContratoCadastro;

@SuppressWarnings("serial")
public abstract class Cadastro extends JFrame implements ContratoCadastro,
		ActionListener {

	protected JPanel contentPane;
	protected JButton btVoltar;
	protected JButton btAlterar;
	protected JButton btSalvar;
	protected JButton btCriar;
	protected JButton btRetornar;
	protected JButton btDeletar;
	protected JButton btCarregar;
	protected JButton btCadastro;
	protected JComboBox<String> box;
	protected JLabel lblBusca;
	protected JPanel painelPrincipal;
	
	protected List<Object> lista = new ArrayList<Object>();

	/**
	 * Create the frame.
	 */
	public Cadastro() {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(null);
		painelPrincipal.setBorder(new LineBorder(new Color(0, 0, 0)));
		painelPrincipal.setBounds(10, 11, 443, 345);
		contentPane.add(painelPrincipal);

		lblBusca = new JLabel("Busca ");
		lblBusca.setFont(new Font("Roboto Cn", Font.PLAIN, 16));
		lblBusca.setBounds(25, 35, 46, 25);
		painelPrincipal.add(lblBusca);

		box = new JComboBox<String>();
		box.setFont(new Font("Roboto Cn", Font.PLAIN, 16));
		box.setBounds(80, 35, 340, 25);
		painelPrincipal.add(box);

		btCadastro = new JButton("Cadastrar");
		btCadastro.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btCadastro.setBounds(31, 70, 90, 25);
		painelPrincipal.add(btCadastro);
		btCadastro.addActionListener(this);

		btCarregar = new JButton("Carregar");
		btCarregar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btCarregar.setBounds(131, 70, 90, 25);
		painelPrincipal.add(btCarregar);
		btCarregar.addActionListener(this);

		btDeletar = new JButton("Deletar");
		btDeletar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btDeletar.setBounds(231, 70, 90, 25);
		painelPrincipal.add(btDeletar);
		btDeletar.addActionListener(this);

		btRetornar = new JButton("Retornar");
		btRetornar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btRetornar.setBounds(331, 70, 90, 25);
		painelPrincipal.add(btRetornar);
		btRetornar.addActionListener(this);

		btCriar = new JButton("Criar");
		btCriar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btCriar.setBounds(25, 294, 90, 25);
		painelPrincipal.add(btCriar);
		btCriar.addActionListener(this);
		btCriar.setEnabled(false);

		btSalvar = new JButton("Salvar");
		btSalvar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btSalvar.setBounds(125, 294, 90, 25);
		painelPrincipal.add(btSalvar);
		btSalvar.addActionListener(this);
		btSalvar.setEnabled(false);

		btAlterar = new JButton("Alterar");
		btAlterar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btAlterar.setBounds(225, 294, 90, 25);
		painelPrincipal.add(btAlterar);
		btAlterar.addActionListener(this);
		btAlterar.setEnabled(false);

		btVoltar = new JButton("Voltar");
		btVoltar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		btVoltar.setBounds(324, 294, 90, 25);
		painelPrincipal.add(btVoltar);
		btVoltar.addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comandoAcao = e.getActionCommand();

		switch (comandoAcao) {
		case "Cadastrar":
			bloquearBox();
			bloquearBotoes();
			campoTextoEnable(true);
			btCriar.setEnabled(true);
			btVoltar.setEnabled(true);
			break;

		case "Carregar":
			bloquearBox();
			bloquearBotoes();
			carregarDadosCampoTexto();
			btAlterar.setEnabled(true);
			btVoltar.setEnabled(true);
			campoTextoEnable(false);
			break;

		case "Deletar":
			excluirItemSelecionado();
			atualizarBox();
			break;

		case "Retornar":
			// Voltar a tela Anterior - que não fiz ainda por que não foi
			// estruturada
			dispose();
			break;

		case "Criar":
			bloquearBox();
			criaObjeto();// ao criar tambem adiciona na lista
			atualizarBox();
			bloquearBotoes();
			campoTextoEnable(false);
			btAlterar.setEnabled(true);
			btVoltar.setEnabled(true);
			atualizarBox();
			break;

		case "Salvar":
			bloquearBox();
			atualizaObjeto();// inclui tambem na lista de objetos
			atualizarBox();
			bloquearBotoes();
			campoTextoEnable(false);
			btAlterar.setEnabled(true);
			btVoltar.setEnabled(true);
			atualizarBox();
			break;
		case "Alterar":
			bloquearBox();
			campoTextoEnable(true);
			bloquearBotoes();
			btSalvar.setEnabled(true);
			btVoltar.setEnabled(true);
			break;
		case "Voltar":
			atualizarBox();
			desbloqueiaBox();
			desbloqueiaBotoes();
			campoTextoEnable(false);
			btCriar.setEnabled(false);
			btSalvar.setEnabled(false);
			btAlterar.setEnabled(false);
			btVoltar.setEnabled(false);
			limpaTexto();
			break;

		default:
			break;
		}

	}

	private void desbloqueiaBotoes() {
		btAlterar.setEnabled(true);
		btCadastro.setEnabled(true);
		btCarregar.setEnabled(true);
		btCriar.setEnabled(true);
		btDeletar.setEnabled(true);
		btRetornar.setEnabled(true);
		btSalvar.setEnabled(true);
		btVoltar.setEnabled(true);
		
	}

	private void desbloqueiaBox() {
		box.setEnabled(true);
		
	}

	private void bloquearBotoes() {
		btAlterar.setEnabled(false);
		btCadastro.setEnabled(false);
		btCarregar.setEnabled(false);
		btCriar.setEnabled(false);
		btDeletar.setEnabled(false);
		btRetornar.setEnabled(false);
		btSalvar.setEnabled(false);
		btVoltar.setEnabled(false);
		
	}

	private void bloquearBox() {
		box.setEnabled(false);
		
	}
}
