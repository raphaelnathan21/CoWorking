package view;

import javax.swing.JDialog;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class Funcionarios extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField inputNome;
	private JTextField inputLogin;
	private JTextField inputEmail;
	private JTextField inputPerfil;
	private JPasswordField inputSenha;
	private JLabel imgCreate;
	private JLabel imgUpdate;
	private JLabel imgDelete;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funcionarios dialog = new Funcionarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public Funcionarios() {
		setResizable(false);
		setTitle("Funcionarios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Funcionarios.class.getResource("/img/logo.png")));
		setBounds(new Rectangle(0, 0, 450, 300));
		getContentPane().setLayout(null);
		
		JLabel nomeFunc = new JLabel("Nome:");
		nomeFunc.setBounds(10, 36, 46, 14);
		getContentPane().add(nomeFunc);
		
		JLabel loginFunc = new JLabel("Login:");
		loginFunc.setBounds(10, 100, 46, 14);
		getContentPane().add(loginFunc);
		
		JLabel senhaFunc = new JLabel("Senha:");
		senhaFunc.setBounds(240, 100, 46, 14);
		getContentPane().add(senhaFunc);
		
		JLabel emailFunc = new JLabel("E-mail:");
		emailFunc.setBounds(240, 180, 46, 14);
		getContentPane().add(emailFunc);
		
		JLabel perfilFunc = new JLabel("Perfil:");
		perfilFunc.setBounds(10, 180, 46, 14);
		getContentPane().add(perfilFunc);
		
		inputNome = new JTextField();
		inputNome.setBounds(66, 33, 358, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);
		
		inputLogin = new JTextField();
		inputLogin.setBounds(66, 97, 154, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(286, 177, 138, 20);
		getContentPane().add(inputEmail);
		inputEmail.setColumns(10);
		
		inputPerfil = new JTextField();
		inputPerfil.setBounds(66, 177, 154, 20);
		getContentPane().add(inputPerfil);
		inputPerfil.setColumns(10);
		
		inputSenha = new JPasswordField();
		inputSenha.setBounds(286, 97, 138, 20);
		getContentPane().add(inputSenha);
		
		imgCreate = new JLabel("");
		imgCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));
		imgCreate.setBounds(240, 246, 63, 53);
		getContentPane().add(imgCreate);
		
		imgUpdate = new JLabel("");
		imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
		imgUpdate.setBounds(300, 246, 63, 53);
		getContentPane().add(imgUpdate);
		
		imgDelete = new JLabel("");
		imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
		imgDelete.setBounds(361, 246, 63, 53);
		getContentPane().add(imgDelete);
		
	}
}
