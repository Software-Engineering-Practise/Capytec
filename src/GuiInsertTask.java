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

public class GuiInsertTask extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTaskName;
	private JTextField textFieldDescription;
	CapyTecDB dbClass = new CapyTecDB();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiInsertTask frame = new GuiInsertTask();
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
	public GuiInsertTask() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldTaskName = new JTextField();
		textFieldTaskName.setBounds(191, 70, 172, 20);
		contentPane.add(textFieldTaskName);
		textFieldTaskName.setColumns(10);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setColumns(10);
		textFieldDescription.setBounds(191, 101, 172, 39);
		contentPane.add(textFieldDescription);
		
		JLabel lblTaskName = new JLabel("Task Name:");
		lblTaskName.setBounds(90, 70, 76, 14);
		contentPane.add(lblTaskName);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(90, 101, 76, 14);
		contentPane.add(lblDescription);
		
		JLabel lblTaskType = new JLabel("Type:");
		lblTaskType.setBounds(90, 155, 76, 14);
		contentPane.add(lblTaskType);
		
		JLabel lblInsertTask = new JLabel("Insert Task");
		lblInsertTask.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInsertTask.setBounds(87, 20, 202, 34);
		contentPane.add(lblInsertTask);
		
		JComboBox comboBoxTaskType = new JComboBox();
		comboBoxTaskType.setModel(new DefaultComboBoxModel(new String[] {"Caretaker", "Manager"}));
		comboBoxTaskType.setBounds(191, 151, 96, 22);
		contentPane.add(comboBoxTaskType);
		
		JButton btnInsertTask = new JButton("Insert Task");
		btnInsertTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				String taskName = textFieldTaskName.getText();
				String description = textFieldDescription.getText();
				String taskType = comboBoxTaskType.getSelectedItem().toString();
				boolean insertTaskError = false;
				if(taskName.isBlank()) {
					System.out.println("Task name cannot be empty");
					insertTaskError = true;
				}
				if(description.isBlank()) {
					System.out.println("Description cannot be empty");
					insertTaskError = true;
				}
				if(taskType.isEmpty()) {
					System.out.println("Task type cannot be empty");
					insertTaskError = true;
				}
				if(insertTaskError == false) {
					System.out.println("Insert");
				}
				
			}
		});
		btnInsertTask.setBounds(306, 338, 118, 36);
		contentPane.add(btnInsertTask);

	}
}
