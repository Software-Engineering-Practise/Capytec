import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
				if (textFieldID.getText() != "") {
					int inputID = Integer.parseInt(textFieldID.getText());
				}
				int inputID = Integer.parseInt(textFieldID.getText());
				boolean isFound = false;
				
				CapyTecDB dbClass = new CapyTecDB();
				
				ArrayList<Caretaker> ctrs = dbClass.getAllCaretakers();
				ArrayList<Manager> mgrs = dbClass.getAllManagers();
				
				for(int i=0; i<ctrs.size(); i++) {
					if(inputID == ctrs.get(i).getID()) {
						//System.out.println("ID found for caretaker " + currentCaretaker.getFullName());
						dbClass.deleteCaretaker(inputID);
						lblNotify.setText("DELETED Caretaker: "+ inputID);
						isFound = true;
					}
				}
				for(int i=0; i<mgrs.size(); i++) {
					if(inputID == mgrs.get(i).getID()) {
						//System.out.println("ID found for manager " + currentManager.getFullName());
						dbClass.deleteManager(inputID);
						lblNotify.setText("DELETED Manager: "+ inputID);
						isFound = true;
					}
				}

				if(isFound) {
					
					dbClass.deleteUser(inputID);
					lblNotify.setText("DELETED User: "+ inputID);
				} else {
				if(!isFound) {
					lblNotify.setText("Invalid user ID, Check user table for ID!");
				}
			}
		});
		btnDeleteUser.setBounds(304, 211, 118, 36);
		contentPane.add(btnDeleteUser);
		
	}
}
