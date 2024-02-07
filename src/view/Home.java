package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class Home extends JDialog {

	private static final long serialVersionUID = 1L;
	
public Home() {
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
	
	JButton btnRoom = new JButton("");
	btnRoom.setBorderPainted(false);
	btnRoom.setIcon(new ImageIcon(Home.class.getResource("/img/room.png")));
	btnRoom.setBounds(142, 78, 141, 99);
	getContentPane().add(btnRoom);
	
	JButton btnReserve = new JButton("");
	btnReserve.setBorderPainted(false);
	btnReserve.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	btnReserve.setIcon(new ImageIcon(Home.class.getResource("/img/reserve.png")));
	btnReserve.setBounds(293, 78, 141, 99);
	getContentPane().add(btnReserve);
		
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
