import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javax.swing.JCheckBox;

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
		setBounds(100, 100, 450, 725);
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
		
		JComboBox<String> comboBoxTaskTypeOne = new JComboBox<String>();
		comboBoxTaskTypeOne.setModel(new DefaultComboBoxModel<String>(new String[] {}));
		comboBoxTaskTypeOne.setBounds(191, 151, 96, 22);
		contentPane.add(comboBoxTaskTypeOne);
		
		JComboBox<String> comboBoxTaskTypeTwo = new JComboBox<String>();
		comboBoxTaskTypeTwo.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		comboBoxTaskTypeTwo.setBounds(191, 184, 96, 22);
		contentPane.add(comboBoxTaskTypeTwo);
		
		JComboBox<String> comboBoxTaskTypeThree = new JComboBox<String>();
		comboBoxTaskTypeThree.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		comboBoxTaskTypeThree.setBounds(191, 217, 96, 22);
		contentPane.add(comboBoxTaskTypeThree);
		
		for(int i=0; i<dbClass.getAllSkills().size(); i++) {
			String currentSkill = dbClass.getAllSkills().get(i);
			comboBoxTaskTypeOne.addItem(currentSkill);
			comboBoxTaskTypeTwo.addItem(currentSkill);
			comboBoxTaskTypeThree.addItem(currentSkill);
			
		}
		
		JLabel lblFrequency = new JLabel("Frequency(Days Until Repeat):");
		lblFrequency.setBounds(90, 283, 76, 14);
		contentPane.add(lblFrequency);
		
		JComboBox comboBoxFrequency = new JComboBox();
		comboBoxFrequency.setModel(new DefaultComboBoxModel(new String[] {"One-off", "1", "7", "14", "30", "182", "365"}));
		comboBoxFrequency.setBounds(191, 279, 96, 22);
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
	    
	    datePickerStart.setBounds(191, 340, 125, 20);
		contentPane.add(datePickerStart);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(90, 340, 76, 14);
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
	    datePickerDue.setBounds(191, 380, 125, 20);
		contentPane.add(datePickerDue);
		
		JLabel lblDueDate = new JLabel("Due Date:");
		lblDueDate.setBounds(90, 380, 76, 14);
		contentPane.add(lblDueDate);
		
		JLabel lblImportance = new JLabel("Importance:");
		lblImportance.setBounds(90, 428, 76, 14);
		contentPane.add(lblImportance);
		
		JComboBox comboBoxImportance = new JComboBox();
		comboBoxImportance.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		comboBoxImportance.setBounds(191, 424, 40, 22);
		contentPane.add(comboBoxImportance);
		
		JLabel lblNeedsSigning = new JLabel("Needs Signing:");
		lblNeedsSigning.setBounds(90, 470, 135, 14);
		contentPane.add(lblNeedsSigning);
		
		JCheckBox chckbxNeedsSigning = new JCheckBox("");
		chckbxNeedsSigning.setBounds(233, 467, 97, 23);
		contentPane.add(chckbxNeedsSigning);
		
		JLabel lblNeedsPeerChecking = new JLabel("Needs Peer Checking:");
		lblNeedsPeerChecking.setBounds(89, 500, 142, 14);
		contentPane.add(lblNeedsPeerChecking);
		
		JCheckBox chckbxNeedsPeerChecking = new JCheckBox("");
		chckbxNeedsPeerChecking.setBounds(233, 500, 97, 23);
		contentPane.add(chckbxNeedsPeerChecking);
		
		JLabel lblErrorMessage = new JLabel("Error Message");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(22, 626, 180, 31);
		contentPane.add(lblErrorMessage);
		lblErrorMessage.setVisible(false);
		
		
		JButton btnInsertTask = new JButton("Insert Task");
		btnInsertTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				String taskName = textFieldTaskName.getText();
				String description = textFieldDescription.getText();
				int frequency;
				String taskTypeOne = comboBoxTaskTypeOne.getSelectedItem().toString();
				String taskTypeTwo = comboBoxTaskTypeTwo.getSelectedItem().toString();
				String taskTypeThree = comboBoxTaskTypeThree.getSelectedItem().toString();
				String startDate = datePickerStart.getJFormattedTextField().getText();
				String dueDate = datePickerDue.getJFormattedTextField().getText();
				String importance = comboBoxImportance.getSelectedItem().toString();
				int importanceNumeric = 0;
				boolean needsSigning = chckbxNeedsSigning.isSelected();
				boolean needsPeerChecking = chckbxNeedsPeerChecking.isSelected();
				
				switch(comboBoxFrequency.getSelectedItem().toString()){
				case "1":
					frequency = 1;
					break;
				case "7":
					frequency = 7;
					break;
				case "14":
					frequency = 14;
					break;
				case "30":
					frequency = 30;
					break;
				case "182":
					frequency = 182;
					break;
				case "365":
					frequency = 365;
					break;
				default:
					frequency = 0;
				}

				switch(importance) {
				case "1":
					importanceNumeric = 1;
				case "2":
					importanceNumeric = 2;
				case "3":
					importanceNumeric = 3;
				}
				boolean insertTaskError = false;
				
				if(dueDate.equals("")) {
					//System.out.println("Due date cannot be empty");
					lblErrorMessage.setText("Due date cannot be empty");
					insertTaskError = true;
				}
				if(startDate.equals("")) {
					//System.out.println("Start date cannot be empty");
					lblErrorMessage.setText("Start date cannot be empty");
					insertTaskError = true;
				}
				if(taskTypeOne.isEmpty()) {
					//System.out.println("Task type cannot be empty");
					lblErrorMessage.setText("First task type must be selected");
					insertTaskError = true;
				}
				if(taskTypeOne.equals(taskTypeTwo) || taskTypeOne.equals(taskTypeThree)) {
					lblErrorMessage.setText("Cannot have duplicate tasks");
					insertTaskError = true;
				}
				if(taskTypeTwo.equals(taskTypeThree) && !taskTypeTwo.equals("")) {
					lblErrorMessage.setText("Cannot have duplicate tasks");
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
					System.out.println("Taskname: " + taskName);
					System.out.println("Description: " + description);
					System.out.println("Task Type One: " + taskTypeOne);
					System.out.println("Task Type Two: " + taskTypeTwo);
					System.out.println("Task Type Three: " + taskTypeThree);
					System.out.println("Days Until Repeat: " + frequency);
					System.out.println("Start Date: " + startDate);
					System.out.println("Due Date: " + dueDate);
					System.out.println("Needs Signing " + needsSigning);
					System.out.println("Needs Peer Checking " + needsPeerChecking);
					
					lblErrorMessage.setVisible(false);
					
					//Insert task
					CaretakerTask newTask = new CaretakerTask();
					newTask.setTitle(taskName);
					newTask.setDesc(description);
					ArrayList<String> taskTypes = new ArrayList<String>();
					taskTypes.add(taskTypeOne);
					taskTypes.add(taskTypeTwo);
					taskTypes.add(taskTypeThree);
					newTask.setRecSkills(taskTypes);
					newTask.setDaysUntilRepeat(frequency);
					newTask.setDateCreated(startDate);
					newTask.setDateDue(dueDate);
					newTask.setPriority(importanceNumeric);
					newTask.setNeedsSigning(needsSigning);
					newTask.setNeedsPeerChecking(needsPeerChecking);
					
					//dbClass.addCaretakerTask(newTask);
					//System.out.println("Insert");
				} else {
					lblErrorMessage.setVisible(true);
				}
			}
		});
		btnInsertTask.setBounds(294, 623, 118, 36);
		contentPane.add(btnInsertTask);
		
		
		
		
		

	}
}
