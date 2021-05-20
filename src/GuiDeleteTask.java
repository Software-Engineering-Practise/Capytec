import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class GuiDeleteTask extends JFrame {

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
					GuiDeleteTask frame = new GuiDeleteTask();
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
	public GuiDeleteTask() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel lblDeleteTask = new JLabel("Delete Task");
		lblDeleteTask.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDeleteTask.setBounds(87, 20, 202, 34);
		contentPane.add(lblDeleteTask);
		
		JLabel lblErrorMessage = new JLabel("Error Message");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(34, 202, 137, 14);
		lblErrorMessage.setVisible(false);
		contentPane.add(lblErrorMessage);
		
		JButton btnDeleteTask = new JButton("Delete Task");
		btnDeleteTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String inputID = textFieldID.getText();
				boolean isFound = false;
				
				for(int i=0; i<dbClass.getAllTasks().size(); i++) {
					//Caretaker currentCaretaker = dbClass.getAllCaretakers().get(i);
					Task currentTask = dbClass.getAllTasks().get(i);
					String retrievedID = Integer.toString(currentTask.getID());
					if(inputID.equals(retrievedID)) {
						System.out.println("ID found for task: " + currentTask.getTitle());
						isFound = true;
						lblErrorMessage.setVisible(false);
						int confirmationResult = JOptionPane.showConfirmDialog(contentPane, "Are you sure you would like to delete '" + currentTask.getTitle() + "'","Confirm Deletion",JOptionPane.YES_NO_OPTION);
						if(JOptionPane.YES_OPTION == confirmationResult) {
							//Delete Task
							System.out.println("Delete");
						} else {
							System.out.println("Do not delete");
						}				
						

					}
				}
				if(!isFound) {
					lblErrorMessage.setText("Invalid ID");
					lblErrorMessage.setVisible(true);
				} 
			}
		});
		btnDeleteTask.setBounds(304, 211, 118, 36);
		contentPane.add(btnDeleteTask);
		
		

	}
}
