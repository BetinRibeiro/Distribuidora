package VisualAntigoMalEstruturado;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JButton btOk;
	private JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Distribuidora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 246, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblLogin.setBounds(20, 35, 46, 20);
		contentPane.add(lblLogin);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblSenha.setBounds(20, 60, 46, 20);
		contentPane.add(lblSenha);

		txtLogin = new JTextField();
		txtLogin.setText("admin");
		txtLogin.setBounds(85, 35, 125, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setText("admin");
		txtSenha.setToolTipText("admin\t");
		txtSenha.setBounds(85, 60, 125, 20);
		contentPane.add(txtSenha);
		txtSenha.setColumns(10);

		btOk = new JButton("Ok");
		btOk.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btOk.setActionCommand("ok");
		btOk.setBounds(20, 85, 89, 23);
		contentPane.add(btOk);

		
		
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		// nomeando a ação para que o actionperformed possa reconhecelo e
		// direciona-lo para e a ação correta
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.setBounds(121, 85, 89, 23);
		contentPane.add(btnCancelar);

		JLabel lblRibeiroDistribuidora = new JLabel("Ribeiro Distribuidora");
		lblRibeiroDistribuidora.setHorizontalAlignment(SwingConstants.CENTER);
		lblRibeiroDistribuidora.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblRibeiroDistribuidora.setBounds(20, 10, 190, 20);
		contentPane.add(lblRibeiroDistribuidora);
		btOk.grabFocus();  
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comandoAcao = e.getActionCommand();

		if (comandoAcao.equalsIgnoreCase("ok")) {
			VendaItem frente = new VendaItem();
			frente.setVisible(true);
			dispose();

		}
		if (comandoAcao.equalsIgnoreCase("cancelar")) {
			System.exit(0);

		}

	}
}
