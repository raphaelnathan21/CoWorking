package view;

import javax.swing.JDialog;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.DAO;

import javax.swing.JPasswordField;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class Login extends JDialog {
	public Login() {
		addWindowListener(new WindowAdapter() {

			public void windowActivated(WindowEvent e) {
				statusConexaoBanco();
			}

		});

		setTitle("Login");
		setResizable(false);
		setBounds(new Rectangle(0, 0, 451, 300));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		JLabel txtLogin = new JLabel("Login");
		txtLogin.setBounds(69, 111, 46, 14);
		getContentPane().add(txtLogin);

		JLabel txtSenha = new JLabel("Senha");
		txtSenha.setBounds(69, 136, 46, 14);
		getContentPane().add(txtSenha);

		inputLogin = new JTextField();
		inputLogin.setBounds(125, 108, 192, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);

		inputSenha = new JPasswordField();
		inputSenha.setBounds(125, 133, 192, 20);
		getContentPane().add(inputSenha);

		JButton btnLogin = new JButton("Entrar");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(167, 182, 89, 23);
		getContentPane().add(btnLogin);

		JLabel tituloLogin = new JLabel("Acessar conta");
		tituloLogin.setBounds(170, 36, 86, 23);
		getContentPane().add(tituloLogin);

		JLabel imgDatabase = new JLabel("");
		imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase.setBounds(10, 182, 59, 68);
		getContentPane().add(imgDatabase);
	}

	DAO dao = new DAO();

	private void statusConexaoBanco() {
		try {
			Connection conexaoBanco = dao.conectar();

	
			if (conexaoBanco == null) {
				// Escolher a imagem
				imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
			}

			else {
				// Trocar a imagem se houver conexão
				imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOn.png")));
			}
			conexaoBanco.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}

	private static final long serialVersionUID = 1L;
	private JTextField inputLogin;
	private JPasswordField inputSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login dialog = new Login();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
