import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GuiInsertUser extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFirstName;
	private JTextField textFieldSurname;
	CapyTecDB dbClass = new CapyTecDB();
	private JTextField textFieldUsername;
	private JTextField textconfirmPwdField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordFieldIn;
	private JPasswordField passwordFieldConfirm;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiInsertUser frame = new GuiInsertUser();
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
	public GuiInsertUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 449, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(191, 70, 96, 20);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(191, 101, 96, 20);
		contentPane.add(textFieldSurname);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(191, 132, 96, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(90, 70, 76, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(90, 101, 76, 14);
		contentPane.add(lblSurname);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(90, 231, 76, 14);
		contentPane.add(lblPosition);
		
		JLabel lblInsertUser = new JLabel("Insert User");
		lblInsertUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInsertUser.setBounds(87, 20, 202, 34);
		contentPane.add(lblInsertUser);
		
		JComboBox comboBoxPosition = new JComboBox();
		comboBoxPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBoxPosition.setModel(new DefaultComboBoxModel(new String[] {"Caretaker", "Manager"}));
		comboBoxPosition.setBounds(191, 227, 96, 22);
		contentPane.add(comboBoxPosition);
		
		JComboBox<String> comboBoxSkill1 = new JComboBox();
		comboBoxSkill1.setBounds(191, 260, 96, 24);
		contentPane.add(comboBoxSkill1);
		
		JComboBox<String> comboBoxSkill2 = new JComboBox();
		comboBoxSkill2.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		comboBoxSkill2.setBounds(191, 295, 96, 24);
		contentPane.add(comboBoxSkill2);
		
		JComboBox<String> comboBoxSkill3 = new JComboBox();
		comboBoxSkill3.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		comboBoxSkill3.setBounds(191, 330, 96, 24);
		contentPane.add(comboBoxSkill3);
		
		JLabel lblUsrBox = new JLabel("");
		lblUsrBox.setForeground(new Color(204, 51, 0));
		lblUsrBox.setBounds(297, 135, 126, 14);
		contentPane.add(lblUsrBox);
		
		JLabel lblPwdBox = new JLabel("");
		lblPwdBox.setForeground(new Color(204, 51, 0));
		lblPwdBox.setBounds(297, 166, 126, 14);
		contentPane.add(lblPwdBox);
		
		JLabel lblFName = new JLabel("");
		lblFName.setForeground(new Color(204, 51, 0));
		lblFName.setBounds(297, 73, 126, 14);
		contentPane.add(lblFName);
		
		JLabel lblSName = new JLabel("");
		lblSName.setForeground(new Color(204, 51, 0));
		lblSName.setBounds(297, 104, 126, 14);
		contentPane.add(lblSName);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(new Color(204, 51, 0));
		lblNewLabel_1.setBounds(297, 197, 126, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblSkill1 = new JLabel("Skill 1:");
		lblSkill1.setBounds(90, 265, 76, 14);
		contentPane.add(lblSkill1);
		
		JLabel lblSkill2 = new JLabel("Skill 2:");
		lblSkill2.setBounds(90, 300, 76, 14);
		contentPane.add(lblSkill2);
		
		JLabel lblSkill3 = new JLabel("Skill 3:");
		lblSkill3.setBounds(90, 335, 76, 14);
		contentPane.add(lblSkill3);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(90, 135, 76, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(90, 166, 60, 14);
		contentPane.add(lblPassword);
		
		passwordFieldIn = new JPasswordField();
		passwordFieldIn.setBounds(191, 163, 96, 20);
		contentPane.add(passwordFieldIn);
		
		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setBounds(191, 194, 96, 20);
		contentPane.add(passwordFieldConfirm);
		
		JLabel lblNewLabel = new JLabel("Confirm Password:");
		lblNewLabel.setBounds(80, 197, 149, 14);
		contentPane.add(lblNewLabel);
		
		
		//Populate user skill comboboxes if caretaker is selected
		for(int i=0; i<dbClass.getAllSkills().size(); i++) {
			String currentSkill = dbClass.getAllSkills().get(i);
			comboBoxSkill1.addItem(currentSkill);
			comboBoxSkill2.addItem(currentSkill);
			comboBoxSkill3.addItem(currentSkill);
		}
		
		
		JButton btnInsertUser = new JButton("Insert User");
		btnInsertUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String firstName = textFieldFirstName.getText();
				String surname = textFieldSurname.getText();
				String username = textFieldUsername.getText();
				char [] password = passwordFieldIn.getPassword();
				char [] conPassword = passwordFieldConfirm.getPassword();
				String position = comboBoxPosition.getSelectedItem().toString();
				
				if(firstName.isEmpty()) {
					lblFName.setText("Missing First Name");
				} else {
					lblFName.setText("");
				}
				if(surname.isEmpty()) {
					lblSName.setText("Missing Surname!");
				} else {
					lblSName.setText("");
				}
				if(username.isEmpty()) {
					lblUsrBox.setText("Missing Username!");
				} else {
					lblUsrBox.setText("");
				}
				if(password.length == 0) {
					lblPwdBox.setText("Missing password!");
				} else {
					lblPwdBox.setText("");
				}
				if(conPassword.length == 0) {
					lblNewLabel_1.setText("Confirm password!");
				} else {
					lblNewLabel_1.setText("");
				}
				
				
				if(!firstName.isEmpty() && !surname.isEmpty() && password.length > 0 && conPassword.length > 0 && !username.isEmpty() && conPassword.length == password.length) {
					boolean pwdMatch = false;
					int i;
					for( i = 0 ; i < password.length ; i++) {
						if(password[i] != conPassword[i]) {
							break;
						} 
					}
					if (i == password.length) {
						pwdMatch = true;
					}
					
					if(pwdMatch && position == "Manager") {
						
						Manager newManager = new Manager();
						
						newManager.setFirstName(firstName);
						newManager.setLastName(surname);
						
					}
					if(pwdMatch && position == "Caretaker") {
						
						Caretaker caretaker = new Caretaker();
						
						
					}
					
				} else {
					
				}
				
			}
		});
		btnInsertUser.setBounds(305, 388, 118, 36);
		contentPane.add(btnInsertUser);
		
	}
}
