import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		comboBoxTaskType.setModel(new DefaultComboBoxModel(new String[] {"SkillOne", "SkillTwo"}));
		comboBoxTaskType.setBounds(191, 151, 96, 22);
		contentPane.add(comboBoxTaskType);
		
		
		
		JLabel lblFrequency = new JLabel("Frequency:");
		lblFrequency.setBounds(90, 193, 76, 14);
		contentPane.add(lblFrequency);
		
		JComboBox comboBoxFrequency = new JComboBox();
		comboBoxFrequency.setModel(new DefaultComboBoxModel(new String[] {"One-off", "Daily", "Weekly", "Fortnightly", "Monthly", "Semi-Annually", "Annually"}));
		comboBoxFrequency.setBounds(191, 189, 96, 22);
		contentPane.add(comboBoxFrequency);
		
		UtilDateModel modelStartDate = new UtilDateModel();
		
		Properties pStart = new Properties();
		pStart.put("text.today", "Today");
		pStart.put("text.month", "Month");
		pStart.put("text.year", "Year");
		JDatePanelImpl datePanelStart = new JDatePanelImpl(modelStartDate, pStart);
	    JDatePickerImpl datePickerStart = new JDatePickerImpl(datePanelStart, new DateLabelFormatter());
	    datePickerStart.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("Start Date: " + datePickerStart.getJFormattedTextField().getText());
	    	}
	    });
	    
	    datePickerStart.setBounds(191, 230, 125, 20);
		contentPane.add(datePickerStart);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(90, 230, 76, 14);
		contentPane.add(lblStartDate);
		
		UtilDateModel modelDueDate = new UtilDateModel();
		Properties pDue = new Properties();
		pDue.put("text.today", "Today");
		pDue.put("text.month", "Month");
		pDue.put("text.year", "Year");
		JDatePanelImpl datePanelDue = new JDatePanelImpl(modelDueDate, pDue);
	    JDatePickerImpl datePickerDue = new JDatePickerImpl(datePanelDue, new DateLabelFormatter());
	    datePickerDue.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("End Date: " + datePickerDue.getJFormattedTextField().getText());
	    	}
	    });
	    datePickerDue.setBounds(191, 260, 125, 20);
		contentPane.add(datePickerDue);
		
		JLabel lblDueDate = new JLabel("Due Date:");
		lblDueDate.setBounds(90, 263, 76, 14);
		contentPane.add(lblDueDate);
		
		JLabel lblImportance = new JLabel("Importance:");
		lblImportance.setBounds(90, 303, 76, 14);
		contentPane.add(lblImportance);
		
		JComboBox comboBoxImportance = new JComboBox();
		comboBoxImportance.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		comboBoxImportance.setBounds(191, 299, 40, 22);
		contentPane.add(comboBoxImportance);
		
		JLabel lblErrorMessage = new JLabel("Error Message");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(90, 332, 180, 31);
		contentPane.add(lblErrorMessage);
		lblErrorMessage.setVisible(false);
		
		
		JButton btnInsertTask = new JButton("Insert Task");
		btnInsertTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				String taskName = textFieldTaskName.getText();
				String description = textFieldDescription.getText();
				String taskType = comboBoxTaskType.getSelectedItem().toString();
				boolean insertTaskError = false;
				
				
				if(datePickerDue.getJFormattedTextField().getText().equals("")) {
					//System.out.println("Due date cannot be empty");
					lblErrorMessage.setText("Due date cannot be empty");
					insertTaskError = true;
				}
				if(datePickerStart.getJFormattedTextField().getText().equals("")) {
					//System.out.println("Start date cannot be empty");
					lblErrorMessage.setText("Start date cannot be empty");
					insertTaskError = true;
				}
				if(taskType.isEmpty()) {
					//System.out.println("Task type cannot be empty");
					lblErrorMessage.setText("Task type cannot be empty");
					insertTaskError = true;
				}
				if(description.isBlank()) {
					//System.out.println("Description cannot be empty");
					lblErrorMessage.setText("Description cannot be empty");
					insertTaskError = true;
				}
				if(taskName.isBlank()) {
					//System.out.println("Task name cannot be empty");
					lblErrorMessage.setText("Task name cannot be empty");
					insertTaskError = true;
				}
				if(insertTaskError == false) {
					lblErrorMessage.setVisible(false);
					System.out.println("Insert");
				} else {
					lblErrorMessage.setVisible(true);
				}
			}
		});
		btnInsertTask.setBounds(306, 338, 118, 36);
		contentPane.add(btnInsertTask);
		

	}
}
