package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JDialog {
	
	public JLabel txtUsuarioLogado;
	public JPanel panelUsuario;
	public JLabel txtData;

	private static final long serialVersionUID = 1L;

	public Home() {
		
		addWindowListener (new WindowAdapter() {
			
			public void windowActivated(WindowEvent e) {
				Date dataSistema = new Date();
				DateFormat formatadorData = DateFormat.getDateInstance(DateFormat.FULL);
				txtData.setText(formatadorData.format(dataSistema));
			}
			
		});
		
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBounds(new Rectangle(0, 0, 460, 300));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/img/logo.png")));
		setResizable(false);
		setTitle("Home");
		getContentPane().setLayout(null);

		JButton btnUser = new JButton("");
		btnUser.setBorderPainted(false);
		btnUser.setIcon(new ImageIcon(Home.class.getResource("/img/user.png")));
		btnUser.setBounds(10, 78, 122, 99);
		getContentPane().add(btnUser);
		btnUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Funcionarios funcionarios = new Funcionarios();
				funcionarios.setVisible(true);

			}
		});

		JButton btnRoom = new JButton("");
		btnRoom.setBorderPainted(false);
		btnRoom.setIcon(new ImageIcon(Home.class.getResource("/img/room.png")));
		btnRoom.setBounds(142, 78, 141, 99);
		getContentPane().add(btnRoom);
		btnRoom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Salas salas = new Salas();
				salas.setVisible(true);

			}
		});

		JButton btnReserve = new JButton("");
		btnReserve.setBorderPainted(false);
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReserve.setIcon(new ImageIcon(Home.class.getResource("/img/reserve.png")));
		btnReserve.setBounds(293, 78, 141, 99);
		getContentPane().add(btnReserve);
		
		 panelUsuario = new JPanel();
		panelUsuario.setBounds(0, 213, 444, 48);
		getContentPane().add(panelUsuario);
		 panelUsuario.setLayout(null);
		
		 txtUsuarioLogado = new JLabel("");
		 txtUsuarioLogado.setBounds(10, 11, 191, 22);
		panelUsuario.add(txtUsuarioLogado);
		
		 txtData = new JLabel("");
		txtData.setBounds(313, 11, 121, 14);
		panelUsuario.add(txtData);
		
		btnReserve.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Reservas reservas = new Reservas();
				reservas.setVisible(true);

			}
		});

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home dialog = new Home();
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
