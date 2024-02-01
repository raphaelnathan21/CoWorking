package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Sobre extends JDialog {
	
	public Sobre() {
		setTitle("Sobre");
		setBounds(new Rectangle(0, 0, 526, 355));
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("Sobre o software");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 16));
		titulo.setBounds(0, 60, 510, 14);
		getContentPane().add(titulo);
		
		JLabel descricao1 = new JLabel("O software CoWorking trata-se de um protótipo cujo objetivo \r\n");
		descricao1.setHorizontalAlignment(SwingConstants.CENTER);
		descricao1.setFont(new Font("Tw Cen MT", Font.PLAIN, 12));
		descricao1.setBounds(0, 96, 510, 37);
		getContentPane().add(descricao1);
		
		JLabel descricao2 = new JLabel("é possibilitar o gerenciamento de reserva de salas em um espaço colaborativo.\r\n");
		descricao2.setHorizontalAlignment(SwingConstants.CENTER);
		descricao2.setFont(new Font("Tw Cen MT", Font.PLAIN, 12));
		descricao2.setBounds(0, 122, 510, 37);
		getContentPane().add(descricao2);
		
		JLabel versao = new JLabel("Versão: 1.0.0\r\n");
		versao.setHorizontalAlignment(SwingConstants.CENTER);
		versao.setFont(new Font("Tw Cen MT", Font.PLAIN, 12));
		versao.setBounds(0, 245, 510, 14);
		getContentPane().add(versao);
		
		JLabel atualizacao = new JLabel("Última atualização: 31/01/2024");
		atualizacao.setHorizontalAlignment(SwingConstants.CENTER);
		atualizacao.setFont(new Font("Tw Cen MT", Font.PLAIN, 12));
		atualizacao.setBounds(0, 270, 510, 14);
		getContentPane().add(atualizacao);
		
		JLabel imgMIT = new JLabel("");
		imgMIT.setIcon(new ImageIcon(Sobre.class.getResource("/img/mitLicense.png")));
		imgMIT.setBounds(452, 257, 48, 48);
		getContentPane().add(imgMIT);
	}

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
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
