package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.DAO;
import net.proteanit.sql.DbUtils;

public class Salas extends JDialog {
	private JTextField inputOcup;
	public JButton imgCreate;
	public JButton imgUpdate;
	public JButton imgDelete;

	public Salas() {
		setTitle("Salas");
		setResizable(false);
		setBounds(new Rectangle(300, 100, 614, 403));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		JLabel tipoSala = new JLabel("Categoria:");
		tipoSala.setBounds(10, 58, 60, 14);
		getContentPane().add(tipoSala);

		JLabel codSala = new JLabel("Código:");
		codSala.setBounds(24, 220, 46, 14);
		getContentPane().add(codSala);

		JLabel andarSala = new JLabel("Sala:");
		andarSala.setBounds(299, 220, 46, 14);
		getContentPane().add(andarSala);

		JLabel ocupSala = new JLabel("Ocupação máxima:");
		ocupSala.setBounds(244, 268, 101, 14);
		getContentPane().add(ocupSala);

		JLabel numSala = new JLabel("Número:");
		numSala.setBounds(24, 268, 46, 14);
		getContentPane().add(numSala);

		inputOcup = new JTextField();
		inputOcup.setColumns(10);
		inputOcup.setBounds(355, 262, 108, 20);
		getContentPane().add(inputOcup);

		imgCreate = new JButton("");
		imgCreate.setBackground(new Color(240, 240, 240));
		imgCreate.setBorderPainted(false);
		imgCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCreate.setIcon(new ImageIcon(Salas.class.getResource("/img/create.png")));
		imgCreate.setBounds(304, 290, 65, 54);
		getContentPane().add(imgCreate);

		imgCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarSala();
			}
		});

		imgUpdate = new JButton("");
		imgUpdate.setBackground(new Color(240, 240, 240));
		imgUpdate.setBorderPainted(false);
		imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUpdate.setIcon(new ImageIcon(Salas.class.getResource("/img/update.png")));
		imgUpdate.setBounds(398, 290, 65, 54);
		getContentPane().add(imgUpdate);
		imgUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarSala();
			}
		});

		imgDelete = new JButton("");
		imgDelete.setBackground(new Color(240, 240, 240));
		imgDelete.setBorderPainted(false);
		imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgDelete.setIcon(new ImageIcon(Salas.class.getResource("/img/delete.png")));
		imgDelete.setBounds(488, 290, 65, 54);
		getContentPane().add(imgDelete);
		imgDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarSala();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 71, 479, 93);
		getContentPane().add(scrollPane);

		tblSalas = new JTable();
		scrollPane.setViewportView(tblSalas);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.setBackground(new Color(240, 240, 240));
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBuscarSala();
			}
		});
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setIcon(new ImageIcon(Salas.class.getResource("/img/search.png")));
		btnPesquisar.setBounds(284, 175, 32, 20);
		getContentPane().add(btnPesquisar);

		inputCategoria = new JComboBox();
		inputCategoria.setModel(new DefaultComboBoxModel(new String[] { "", "Sala de Reunião", "Sala de Conferência",
				"Espaço de Eventos", "Escritório privado" }));
		inputCategoria.setBounds(74, 50, 479, 22);
		getContentPane().add(inputCategoria);
		inputCategoria.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				BuscarSalaNaTabela();
			}
		});

		inputCod = new JComboBox();
		inputCod.setModel(new DefaultComboBoxModel(new String[] { "", "REU", "CONF", "EVENT", "PRIV" }));
		inputCod.setBounds(74, 216, 200, 22);
		getContentPane().add(inputCod);

		inputAndar = new JComboBox();
		inputAndar.setModel(new DefaultComboBoxModel(
				new String[] { "", "Subsolo", "Térreo", "1° Andar", "2° Andar", "3° Andar", "" }));
		inputAndar.setBounds(355, 216, 200, 22);
		getContentPane().add(inputAndar);

		inputNum = new JTextField();
		inputNum.setBounds(74, 265, 101, 20);
		getContentPane().add(inputNum);
		inputNum.setColumns(10);

		tblSalas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
			}
		});

	}

	// Criar um objeto da classe DAO para estabelecer conexão com banco
	DAO dao = new DAO();
	private JTable tblSalas;
	private JComboBox inputCategoria;
	private JComboBox inputCod;
	private JComboBox inputAndar;
	private JTextField inputNum;

	private void adicionarSala() {
		String create = "insert into salas (andarSala, numeroSala, tipoSala, codigoSala, ocupacaoSala) values (?, ?, ?, ?, ?);";

		if (inputCategoria.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Categoria da sala obrigatória!");
			inputCategoria.requestFocus();
		}

		else if (inputCod.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Código da sala obrigatório!");
			inputCod.requestFocus();

		} else if (inputAndar.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Andar da sala obrigatória!");
			inputAndar.requestFocus();

		} else if (inputNum.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Número da sala obrigatório!");
			inputNum.requestFocus();

		}

		else if (inputOcup.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ocupação máxima obrigatória!");
			inputOcup.requestFocus();
		}

		else {
			try {
				// Estabelecer a conexão
				Connection conexaoBanco = dao.conectar();

				// Preparar a execusão do script SQL
				PreparedStatement executarSQL = conexaoBanco.prepareStatement(create);

				// Substituir os pontos de interrogação pelo conteúdo das caixas de texto
				// (inputs)
				executarSQL.setString(1, inputAndar.getSelectedItem().toString());
				executarSQL.setString(2, inputNum.getText());
				executarSQL.setString(3, inputCategoria.getSelectedItem().toString());

				executarSQL.setString(4, inputCod.getSelectedItem().toString());

				executarSQL.setString(5, inputOcup.getText());

				// Executar os comandos SQL e inserir o funcionário no banco de dados
				executarSQL.executeUpdate();

				conexaoBanco.close();

				JOptionPane.showMessageDialog(null, "Sala cadastrada com sucesso!");

				limparCampos();

			}

			catch (SQLIntegrityConstraintViolationException error) {
				JOptionPane.showMessageDialog(null, "Sala já cadastrada.");
			}

			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	public void limparCampos() {
		inputCategoria.setSelectedItem("");
		inputCod.setSelectedItem("");
		inputAndar.setSelectedItem("");
		inputNum.setText(null);
		inputOcup.setText(null);

		// Posicionar o cursor de volta no campo Nome
		inputCategoria.requestFocus();

	}

	private void setarCaixasTexto() {
		// Criar uma variável para receber a linha da tabela
		int setarLinha = tblSalas.getSelectedRow();

		inputCategoria.setSelectedItem(tblSalas.getModel().getValueAt(setarLinha, 0).toString());
		inputNum.setText(tblSalas.getModel().getValueAt(setarLinha, 2).toString());

	}

	// Criar método para buscar funcionário pelo botão Pesquisar
	private void btnBuscarSala() {
		String readBtn = "select * from salas where numeroSala = ?;";

		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readBtn);

			executarSQL.setString(1, inputNum.getText());

			// Executar o comando SQL e exibir o resultado no formulário funcionário (todos
			// os seus dados)
			ResultSet resultadoExecucao = executarSQL.executeQuery();

			if (resultadoExecucao.next()) {
				// Preencher os campos do formulário

				inputAndar.setSelectedItem(resultadoExecucao.getString(2));
				inputCod.setSelectedItem(resultadoExecucao.getString(5));
				inputOcup.setText(resultadoExecucao.getString(6));

			}

		}

		catch (Exception e) {
			System.out.println(e);
		}

	}
			
			
		
	private void BuscarSalaNaTabela() {
		String readBtn = "select tipoSala as Categoria, andarSala as Andar, numeroSala as Número from salas where tipoSala = ?;";

		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execução do comando SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readBtn);

			// Substituir
			executarSQL.setString(1, inputCategoria.getSelectedItem().toString());

			// Executar o comando SQL e exibir o resultado no formulário funcionário (todos
			// os seus dados)
			ResultSet resultadoExecucao = executarSQL.executeQuery();

			tblSalas.setModel(DbUtils.resultSetToTableModel(resultadoExecucao));

			conexaoBanco.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}

	}

	private void atualizarSala() {
		String updateBtn = "update sala set tipoSala = ?, codigoSala = ?, andarSala = ?, ocupacaoSala = ?  where numeroSala = ?;";

		if (inputCategoria.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Categoria da sala obrigatória!");
			inputCategoria.requestFocus();
		}

		else if (inputCod.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Código da sala obrigatório!");
			inputCod.requestFocus();

		} else if (inputAndar.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Andar da sala obrigatória!");
			inputAndar.requestFocus();

		} else if (inputNum.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Número da sala obrigatório!");
			inputNum.requestFocus();

		}

		else if (inputOcup.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ocupação máxima obrigatória!");
			inputOcup.requestFocus();
		}

		else {

			try {
				// Estabelecer a conexão
				Connection conexaoBanco = dao.conectar();

				// Preparar a execução do comando SQL
				PreparedStatement executarSQL = conexaoBanco.prepareStatement(updateBtn);

				// Substituir
				executarSQL.setString(1, inputCategoria.getSelectedItem().toString());
				executarSQL.setString(2, inputCod.getSelectedItem().toString());
				executarSQL.setString(3, inputAndar.getSelectedItem().toString());

				executarSQL.setString(4, inputOcup.getText());
				executarSQL.setString(5, inputNum.getText());

				executarSQL.executeUpdate();

				conexaoBanco.close();

				JOptionPane.showMessageDialog(null, "Sala editada com sucesso!");

				limparCampos();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void deletarSala() {
		String updateBtn = "delete from salas where numeroSala = ?;";

		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execução do comando SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(updateBtn);

			// Substituir

			executarSQL.setString(1, inputNum.getText());

			executarSQL.executeUpdate();

			conexaoBanco.close();

			JOptionPane.showMessageDialog(null, "Sala excluída com sucesso!");

			limparCampos();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salas dialog = new Salas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
