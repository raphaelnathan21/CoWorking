package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class Sobre extends JDialog {
	public Sobre() {
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("Sobre o software");
		titulo.setBounds(0, 0, 160, 261);
		titulo.setBackground(new Color(255, 255, 255));
		titulo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		getContentPane().add(titulo);
	}

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
