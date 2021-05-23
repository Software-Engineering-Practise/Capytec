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
import javax.swing.SwingConstants;
import java.awt.Color;

public class GuiDeleteUser extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	CapyTecDB dbClass = new CapyTecDB();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiDeleteUser frame = new GuiDeleteUser();
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
	public GuiDeleteUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(191, 70, 96, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(90, 70, 76, 14);
		contentPane.add(lblID);
		
		JLabel lblNotify = new JLabel("");
		lblNotify.setForeground(new Color(204, 51, 0));
		lblNotify.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotify.setBounds(0, 132, 434, 14);
		contentPane.add(lblNotify);
		
		JLabel lblDeleteUser = new JLabel("Delete User");
		lblDeleteUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDeleteUser.setBounds(87, 20, 202, 34);
		contentPane.add(lblDeleteUser);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String inputID = textFieldID.getText();
				boolean isFound = false;
				
				for(int i=0; i<dbClass.getAllCaretakers().size(); i++) {
					Caretaker currentCaretaker = dbClass.getAllCaretakers().get(i);
					String retrievedID = Integer.toString(currentCaretaker.getID());
					if(inputID.equals(retrievedID)) {
						//System.out.println("ID found for caretaker " + currentCaretaker.getFullName());
						isFound = true;
					}
				}
				for(int i=0; i<dbClass.getAllManagers().size(); i++) {
					Manager currentManager = dbClass.getAllManagers().get(i);
					String retrievedID = Integer.toString(currentManager.getID());
					if(inputID.equals(retrievedID)) {
						//System.out.println("ID found for manager " + currentManager.getFullName());
						isFound = true;
					}
				}
				if(!isFound) {
					lblNotify.setText("Invalid user ID, Check user table for ID!");
					//System.out.println("ID Invalid");
				}		
			}
		});
		btnDeleteUser.setBounds(304, 211, 118, 36);
		contentPane.add(btnDeleteUser);
		


	}
}
