package view;

import javax.swing.JDialog;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Salas extends JDialog {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salas dialog = new Salas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public Salas() {
		setResizable(false);
		setTitle("Salas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Salas.class.getResource("/img/logo.png")));
		setBounds(new Rectangle(0, 0, 450, 300));
		getContentPane().setLayout(null);
		
	}

}
