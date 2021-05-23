import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextArea;

public class GuiInsertTask extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTaskName;
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
		//Set frame to close window rather than application
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Set dimensions for frame
		setBounds(100, 100, 450, 725);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Create field for user to enter task name
		textFieldTaskName = new JTextField();
		textFieldTaskName.setBounds(191, 70, 221, 20);
		contentPane.add(textFieldTaskName);
		textFieldTaskName.setColumns(10);
		
		//Set text to tell user to enter task name
		JLabel lblTaskName = new JLabel("Task Name: *");
		lblTaskName.setBounds(90, 70, 76, 14);
		contentPane.add(lblTaskName);
		
		//Set text to tell user to enter task description
		JLabel lblDescription = new JLabel("Description: *");
		lblDescription.setBounds(90, 101, 76, 14);
		contentPane.add(lblDescription);
		
		//Create field for user to enter task description
		JTextArea textAreaDescription = new JTextArea();
		textAreaDescription.setBounds(191, 96, 221, 51);
		//Add line wrapping after words in text field.
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setWrapStyleWord(true);
		contentPane.add(textAreaDescription);
		
		//Set text to tell user to select task type
		JLabel lblTaskType = new JLabel("Type: *");
		lblTaskType.setBounds(90, 155, 76, 14);
		contentPane.add(lblTaskType);
		
		//Set title to insert task form
		JLabel lblInsertTask = new JLabel("Insert Task");
		lblInsertTask.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInsertTask.setBounds(87, 20, 202, 34);
		contentPane.add(lblInsertTask);
		
		//Create three dropdown menus for task types
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
		
		//Populate task type comboboxes
		for(int i=0; i<dbClass.getAllSkills().size(); i++) {
			String currentSkill = dbClass.getAllSkills().get(i);
			comboBoxTaskTypeOne.addItem(currentSkill);
			comboBoxTaskTypeTwo.addItem(currentSkill);
			comboBoxTaskTypeThree.addItem(currentSkill);
			
		}
		
		//Set label asking user to enter how often task is repeated
		JLabel lblFrequency = new JLabel("Days Until Repeat:");
		lblFrequency.setBounds(48, 283, 118, 14);
		contentPane.add(lblFrequency);
		
		//Dropdown input allowing user to select how often task is repeated
		JComboBox<String> comboBoxFrequency = new JComboBox<String>();
		comboBoxFrequency.setModel(new DefaultComboBoxModel<String>(new String[] {"One-off", "1", "7", "14", "30", "182", "365"}));
		comboBoxFrequency.setBounds(191, 279, 96, 22);
		contentPane.add(comboBoxFrequency);
		
		//Date model used for start and due dates within the application
		UtilDateModel modelStartDate = new UtilDateModel();
		
		Properties pStart = new Properties();
		pStart.put("text.today", "Today");
		pStart.put("text.month", "Month");
		pStart.put("text.year", "Year");
		JDatePanelImpl datePanelStart = new JDatePanelImpl(modelStartDate, pStart);
	    JDatePickerImpl datePickerStart = new JDatePickerImpl(datePanelStart, new DateLabelFormatter());
	    datePickerStart.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    });
	    
	    //Set location of the start date selection
	    datePickerStart.setBounds(191, 340, 125, 20);
		contentPane.add(datePickerStart);
		
		JLabel lblStartDate = new JLabel("Start Date: *");
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
	    		
	    	}
	    });
	    //Set location of the due date selection
	    datePickerDue.setBounds(191, 380, 125, 20);
		contentPane.add(datePickerDue);
		
		//Set label telling user to enter due date
		JLabel lblDueDate = new JLabel("Due Date: *");
		lblDueDate.setBounds(90, 380, 76, 14);
		contentPane.add(lblDueDate);
		
		//Set label telling user to choose importance level of task
		JLabel lblImportance = new JLabel("Importance:");
		lblImportance.setBounds(90, 428, 76, 14);
		contentPane.add(lblImportance);
		
		//Dropdown box allowing user to choose importance level of task
		JComboBox<String> comboBoxImportance = new JComboBox<String>();
		comboBoxImportance.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3"}));
		comboBoxImportance.setBounds(191, 424, 40, 22);
		contentPane.add(comboBoxImportance);
		
		//Set label telling user to choose if task needs signing off
		JLabel lblNeedsSigning = new JLabel("Needs Signing:");
		lblNeedsSigning.setBounds(90, 470, 135, 14);
		contentPane.add(lblNeedsSigning);
		
		//Checkbox allowing user to choose if task needs signing off
		JCheckBox chckbxNeedsSigning = new JCheckBox("");
		chckbxNeedsSigning.setBounds(233, 467, 97, 23);
		contentPane.add(chckbxNeedsSigning);
		
		//Set label telling user to choose if task needs peer checking
		JLabel lblNeedsPeerChecking = new JLabel("Needs Peer Checking:");
		lblNeedsPeerChecking.setBounds(89, 500, 142, 14);
		contentPane.add(lblNeedsPeerChecking);
		
		//Checkbox allowing user to choose if task needs peer checking
		JCheckBox chckbxNeedsPeerChecking = new JCheckBox("");
		chckbxNeedsPeerChecking.setBounds(233, 500, 97, 23);
		contentPane.add(chckbxNeedsPeerChecking);
		
		//Set label asking user for any extra considerations that the task may require
		JLabel lblExtraConsiderations = new JLabel("Extra Considerations:");
		lblExtraConsiderations.setBounds(86, 536, 139, 14);
		contentPane.add(lblExtraConsiderations);
		
		//Create text field for user to enter extra considerations for task
		JTextArea textAreaExtraConsiderations = new JTextArea();
		textAreaExtraConsiderations.setBounds(226, 525, 198, 78);
		//Add line wrapping after words in text field.
		textAreaExtraConsiderations.setLineWrap(true);
		textAreaExtraConsiderations.setWrapStyleWord(true);
		contentPane.add(textAreaExtraConsiderations);
		
		//Set label telling user creation was successful. Visible only on successful addition
		JLabel lblSuccessPrompt = new JLabel("Task created. Restart GUI to view changes");
		lblSuccessPrompt.setForeground(Color.RED);
		lblSuccessPrompt.setBounds(22, 621, 294, 41);
		contentPane.add(lblSuccessPrompt);
		lblSuccessPrompt.setVisible(false);
		
		//Set label telling user creation was unsuccessful. Visible only if an error occurs during addition.
		JLabel lblErrorMessage = new JLabel("Error");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(22, 626, 272, 31);
		contentPane.add(lblErrorMessage);
		lblErrorMessage.setVisible(false);
		
		//Button that user clicks to insert task
		JButton btnInsertTask = new JButton("Insert Task");
		btnInsertTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				//Variable retrieval
				String taskName = textFieldTaskName.getText();
				String description = textAreaDescription.getText();
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
				String extraConsiderations = textAreaExtraConsiderations.getText();
				ArrayList<String> storedTasks = new ArrayList<String>();
				for(int i=0; i<dbClass.getAllTasks().size(); i++) {
					storedTasks.add(dbClass.getAllTasks().get(i).getTitle());
				}
				//Convert string frequency to int to allow for insertion to database
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

				//Convert string importance level to int to allow for insertion to database
				switch(importance) {
				case "1":
					importanceNumeric = 1;
					break;
				case "2":
					importanceNumeric = 2;
					break;
				case "3":
					importanceNumeric = 3;
					break;
				}
				//Set to true if an error arises, preventing task insertion
				boolean insertTaskError = false;
				
				//Set max size for extra considerations field
				if(extraConsiderations.length() > 75) {
					lblErrorMessage.setText("Considerations cannot be over 75 characters");
					insertTaskError = true;
				}
				//Force due date to be chosen
				if(dueDate.equals("")) {
					lblErrorMessage.setText("Due date cannot be empty");
					insertTaskError = true;
				}
				//Force start date to be chosen
				if(startDate.equals("")) {
					lblErrorMessage.setText("Start date cannot be empty");
					insertTaskError = true;
				}
				//Force at least one task type to be chosen
				if(taskTypeOne.isEmpty()) {
					lblErrorMessage.setText("First task type must be selected");
					insertTaskError = true;
				}
				//Prevent duplicate tasks 
				if(taskTypeOne.equals(taskTypeTwo) || taskTypeOne.equals(taskTypeThree)) {
					lblErrorMessage.setText("Cannot have duplicate tasks");
					insertTaskError = true;
				}
				if(taskTypeTwo.equals(taskTypeThree) && !taskTypeTwo.equals("")) {
					lblErrorMessage.setText("Cannot have duplicate tasks");
					insertTaskError = true;	
				}
				//Force description field to be completed
				if(description.isBlank()) {
					lblErrorMessage.setText("Description cannot be empty");
					insertTaskError = true;
				} else if(description.length() > 75) {
					lblErrorMessage.setText("Description cannot be over 75 characters");
					insertTaskError = true;
				} else if(description.length() < 10) {
					lblErrorMessage.setText("Description cannot be under 10 characters");
					insertTaskError = true;
				}
				//Force task name field to be completed
				if(taskName.isBlank()) {
					lblErrorMessage.setText("Task name cannot be empty");
					insertTaskError = true;
				} else if(storedTasks.contains(taskName)) {
					lblErrorMessage.setText("Task already exists");
					insertTaskError = true;	
				} else if(taskName.length() > 30) {
					lblErrorMessage.setText("Task name cannot be over 30 characters");
					insertTaskError = true;	
				} else if(taskName.length() < 7 && taskName.length() != 0) {
					lblErrorMessage.setText("Task name cannot be under 7 characters");
					insertTaskError = true;
				}
				
				//If no error discovered insert task
				if(insertTaskError == false) {
					//Hide any previous error messages
					lblErrorMessage.setVisible(false);
					
					//Create and Insert task
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
					newTask.setExtraConsiderations(extraConsiderations);
					
					dbClass.addCaretakerTask(newTask);
					lblSuccessPrompt.setVisible(true);
				} else {
					//If any previous error has been discovered, set and display error message.
					lblErrorMessage.setVisible(true);
				}
			}
		});
		btnInsertTask.setBounds(294, 623, 118, 36);
		contentPane.add(btnInsertTask);
		
		
		
		
		
		
		
		
		
		

	}
}
