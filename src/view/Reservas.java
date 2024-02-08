package view;

import javax.swing.JDialog;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Reservas extends JDialog {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservas dialog = new Reservas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public Reservas () {
		setResizable(false);
		setTitle("Reservas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/img/logo.png")));
		setBounds(new Rectangle(0, 0, 450, 300));
		getContentPane().setLayout(null);
		
	}

}
