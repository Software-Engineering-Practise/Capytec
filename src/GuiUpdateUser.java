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

public class GuiUpdateUser extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFirstName;
	private JTextField textFieldSurname;
	CapyTecDB dbClass = new CapyTecDB();
	private JTextField textFieldUserID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiUpdateUser frame = new GuiUpdateUser();
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
	public GuiUpdateUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(188, 102, 96, 20);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(188, 133, 96, 20);
		contentPane.add(textFieldSurname);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(87, 102, 76, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(87, 133, 76, 14);
		contentPane.add(lblSurname);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(87, 164, 76, 14);
		contentPane.add(lblPosition);
		
		JLabel lblUpdateUser = new JLabel("Update User");
		lblUpdateUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUpdateUser.setBounds(87, 20, 202, 34);
		contentPane.add(lblUpdateUser);
		
		JComboBox comboBoxPosition = new JComboBox();
		comboBoxPosition.setModel(new DefaultComboBoxModel(new String[] {"Caretaker", "Manager"}));
		comboBoxPosition.setBounds(188, 163, 96, 22);
		contentPane.add(comboBoxPosition);
		
		JButton btnInsertUser = new JButton("Update User");
		btnInsertUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String inputID = textFieldUserID.getText();
				boolean isFound = false;
				
				//ID Retrieval and checking
				for(int i=0; i<dbClass.getAllCaretakers().size(); i++) {
					Caretaker currentCaretaker = dbClass.getAllCaretakers().get(i);
					String retrievedID = Integer.toString(currentCaretaker.getID());
					if(inputID.equals(retrievedID)) {
						System.out.println("ID found for caretaker " + currentCaretaker.getFullName());
						isFound = true;
					}
				}
				for(int i=0; i<dbClass.getAllManagers().size(); i++) {
					Manager currentManager = dbClass.getAllManagers().get(i);
					String retrievedID = Integer.toString(currentManager.getID());
					if(inputID.equals(retrievedID)) {
						System.out.println("ID found for manager " + currentManager.getFullName());
						isFound = true;
					}
				}
				if(!isFound) {
					System.out.println("ID Invalid");
				} else {
					
					String firstName = textFieldFirstName.getText();
					String surname = textFieldSurname.getText();
					String position = comboBoxPosition.getSelectedItem().toString();
					if(firstName.isBlank() || surname.isBlank()) {
						System.out.println("Name cannot be empty");
					} else {
						System.out.println(firstName + " " + surname);
						if(position.equals("Caretaker")) {
							System.out.println("Is caretaker");
							Caretaker newCaretaker = new Caretaker();
							newCaretaker.setFirstName(firstName);
							newCaretaker.setLastName(surname);
							System.out.println("Update with: " + firstName + " " + surname + " " + position);
						} else {
							System.out.println("Is manager");
						}
					}	
				}
				
			}
		});
		btnInsertUser.setBounds(304, 211, 118, 36);
		contentPane.add(btnInsertUser);
		
		JLabel lblUserID = new JLabel("User ID:");
		lblUserID.setBounds(87, 65, 76, 14);
		contentPane.add(lblUserID);
		
		textFieldUserID = new JTextField();
		textFieldUserID.setColumns(10);
		textFieldUserID.setBounds(188, 65, 96, 20);
		contentPane.add(textFieldUserID);

	}
}
