import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GuiLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiLogin frame = new GuiLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CAPYTEC");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(165, 11, 239, 59);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setToolTipText("Please enter username here");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(187, 107, 225, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setBounds(187, 182, 225, 37);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(26, 107, 110, 37);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(26, 177, 151, 47);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(165, 256, 110, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setToolTipText("");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblLogin.setBounds(175, 46, 239, 59);
		contentPane.add(lblLogin);
		
		JLabel lblLogin_1 = new JLabel("");
		lblLogin_1.setToolTipText("");
		lblLogin_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblLogin_1.setBounds(67, 40, 239, 59);
		contentPane.add(lblLogin_1);
	}
}
