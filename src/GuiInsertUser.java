import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GuiInsertUser extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFirstName;
	private JTextField textFieldSurname;
	CapyTecDB dbClass = new CapyTecDB();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		
		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setBounds(90, 70, 76, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(90, 101, 76, 14);
		contentPane.add(lblSurname);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(90, 132, 76, 14);
		contentPane.add(lblPosition);
		
		JLabel lblInsertUser = new JLabel("Insert User");
		lblInsertUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInsertUser.setBounds(87, 20, 202, 34);
		contentPane.add(lblInsertUser);
		
		JComboBox comboBoxPosition = new JComboBox();
		comboBoxPosition.setModel(new DefaultComboBoxModel(new String[] {"Caretaker", "Manager"}));
		comboBoxPosition.setBounds(191, 131, 96, 22);
		contentPane.add(comboBoxPosition);
		
		JButton btnInsertUser = new JButton("Insert User");
		btnInsertUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String firstName = textFieldFirstName.getText();
				String surname = textFieldSurname.getText();
				String position = comboBoxPosition.getSelectedItem().toString();
//				String talentOne = comboBoxTalentsOne.getSelectedItem().toString();
//				String talentTwo = comboBoxTalentsTwo.getSelectedItem().toString();
				if(firstName.isBlank() || surname.isBlank()) {
					System.out.println("Name cannot be empty");
				} else {
					System.out.println(firstName + " " + surname);
					if(position.equals("Caretaker")) {
						System.out.println("Is caretaker");
						
					} else {
						System.out.println("Is manager");
					}
				}
				
			}
		});
		btnInsertUser.setBounds(304, 211, 118, 36);
		contentPane.add(btnInsertUser);

	}
}
