package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.DAO;

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
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logar();
				
			}
		});

		JLabel tituloLogin = new JLabel("Acessar conta");
		tituloLogin.setBounds(170, 36, 86, 23);
		getContentPane().add(tituloLogin);

		imgDatabase = new JLabel("");
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

	private void logar() {
		String read = "select * from funcionario where login=? and senha=md5(?)";

		try {

			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execução do script SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(read);

			// Atribuir valores de login e senha
			// Substituir as interrogações pelo conteúdo da caixa de texto (input)
			executarSQL.setString(1, inputLogin.getText());
			executarSQL.setString(2, inputSenha.getText());

			// Executar os comandos SQL e de acordo com resultado liberar os recursos na
			// tela
			ResultSet resultadoExecucao = executarSQL.executeQuery();

			// Validação do funcionário (autenticação)
			// resultadoExecucao.next() significa que o login e a senha existem, ou seja,
			// correspondem

			if (resultadoExecucao.next()) {
				Home.main(null);
			}
			
			else {
				System.out.println("Login e/ou senha inválidos.");
			}

		}

		catch (Exception e) {
			System.out.println(e);
		}
	}

	private static final long serialVersionUID = 1L;
	private JTextField inputLogin;
	private JPasswordField inputSenha;
	private JLabel imgDatabase;

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
