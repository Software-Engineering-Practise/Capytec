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
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class GuiAllocateTask extends JFrame {

	private JPanel contentPane;
	CapyTecDB dbClass = new CapyTecDB();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiAllocateTask frame = new GuiAllocateTask();
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
	public GuiAllocateTask() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 725);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Placeholder value until login system is implemented
		int loggedInUser = 8;
		
		JLabel lblTaskName = new JLabel("Task Name:");
		lblTaskName.setBounds(22, 70, 144, 14);
		contentPane.add(lblTaskName);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(22, 101, 144, 14);
		contentPane.add(lblDescription);
		
		JLabel lblTaskType = new JLabel("Recommended Skills:");
		lblTaskType.setBounds(22, 160, 144, 14);
		contentPane.add(lblTaskType);
		
		JLabel lblAllocateTask = new JLabel("Allocate Task");
		lblAllocateTask.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAllocateTask.setBounds(87, 20, 202, 34);
		contentPane.add(lblAllocateTask);
		
		JLabel lblFrequency = new JLabel("Days Until Repeat");
		lblFrequency.setBounds(22, 283, 162, 14);
		contentPane.add(lblFrequency);
		
		UtilDateModel modelStartDate = new UtilDateModel();
		
		Properties pStart = new Properties();
		pStart.put("text.today", "Today");
		pStart.put("text.month", "Month");
		pStart.put("text.year", "Year");
		JDatePanelImpl datePanelStart = new JDatePanelImpl(modelStartDate, pStart);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(22, 340, 144, 14);
		contentPane.add(lblStartDate);
		
		UtilDateModel modelDueDate = new UtilDateModel();
		Properties pDue = new Properties();
		pDue.put("text.today", "Today");
		pDue.put("text.month", "Month");
		pDue.put("text.year", "Year");
		JDatePanelImpl datePanelDue = new JDatePanelImpl(modelDueDate, pDue);
		
		JLabel lblDueDate = new JLabel("Due Date:");
		lblDueDate.setBounds(22, 380, 144, 14);
		contentPane.add(lblDueDate);
		
		JLabel lblImportance = new JLabel("Importance:");
		lblImportance.setBounds(22, 428, 144, 14);
		contentPane.add(lblImportance);
		
		JLabel lblNeedsSigning = new JLabel("Needs Signing:");
		lblNeedsSigning.setBounds(22, 470, 162, 14);
		contentPane.add(lblNeedsSigning);
		
		JLabel lblNeedsPeerChecking = new JLabel("Needs Peer Checking:");
		lblNeedsPeerChecking.setBounds(22, 500, 162, 14);
		contentPane.add(lblNeedsPeerChecking);
		
		JLabel lblErrorMessage = new JLabel("Error Message");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(22, 626, 180, 31);
		contentPane.add(lblErrorMessage);
		lblErrorMessage.setVisible(false);
		
		JTextArea textAreaDescription = new JTextArea();
		textAreaDescription.setBounds(95, 96, 317, 53);
		//Add line wrapping after words in text field.
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setWrapStyleWord(true);
		//Made it so user cannot edit box
		textAreaDescription.setEditable(false);	
		contentPane.add(textAreaDescription);
		
		JLabel lblTaskTypeOne = new JLabel("Placeholder");
		lblTaskTypeOne.setBounds(22, 183, 96, 14);
		contentPane.add(lblTaskTypeOne);
		
		JLabel lblTaskTypeTwo = new JLabel("Placeholder");
		lblTaskTypeTwo.setBounds(22, 200, 98, 14);
		contentPane.add(lblTaskTypeTwo);
		
		JLabel lblTaskTypeThree = new JLabel("Placeholder");
		lblTaskTypeThree.setBounds(22, 219, 125, 14);
		contentPane.add(lblTaskTypeThree);
		
		JLabel lblFrequencyValue = new JLabel("New label");
		lblFrequencyValue.setBounds(191, 283, 118, 14);
		contentPane.add(lblFrequencyValue);
		
		JLabel lblStartDateValue = new JLabel("New label");
		lblStartDateValue.setBounds(191, 340, 187, 14);
		contentPane.add(lblStartDateValue);
		
		JLabel lblDueDateValue = new JLabel("New label");
		lblDueDateValue.setBounds(191, 380, 187, 14);
		contentPane.add(lblDueDateValue);
		
		JLabel lblImportanceValue = new JLabel("New label");
		lblImportanceValue.setBounds(191, 428, 98, 14);
		contentPane.add(lblImportanceValue);
		
		JLabel lblNeedsPeerCheckingValue = new JLabel("New label");
		lblNeedsPeerCheckingValue.setBounds(191, 500, 98, 14);
		contentPane.add(lblNeedsPeerCheckingValue);
		
		JLabel lblNeedsSigningValue = new JLabel("New label");
		lblNeedsSigningValue.setBounds(191, 470, 98, 14);
		contentPane.add(lblNeedsSigningValue);
		
		JLabel lblExtraConsiderations = new JLabel("Extra Considerations:");
		lblExtraConsiderations.setBounds(22, 531, 150, 14);
		contentPane.add(lblExtraConsiderations);
		
		JTextArea textAreaExtraConsiderations = new JTextArea();
		textAreaExtraConsiderations.setBounds(191, 525, 172, 50);
		//Add line wrapping after words in text field.
		textAreaExtraConsiderations.setLineWrap(true);
		textAreaExtraConsiderations.setWrapStyleWord(true);
		//Make is so user cannot edit box
		textAreaExtraConsiderations.setEditable(false);	
		contentPane.add(textAreaExtraConsiderations);
		
		JLabel lblUserSkills = new JLabel("Your Skills:");
		lblUserSkills.setBounds(157, 160, 83, 14);
		contentPane.add(lblUserSkills);
		
		JLabel lblUserSkillOne = new JLabel("New label");
		lblUserSkillOne.setBounds(157, 183, 76, 14);
		contentPane.add(lblUserSkillOne);
		
		JLabel lblUserSkillTwo = new JLabel("New label");
		lblUserSkillTwo.setBounds(157, 200, 76, 14);
		contentPane.add(lblUserSkillTwo);
		
		JLabel lblUserSkillThree = new JLabel("New label");
		lblUserSkillThree.setBounds(157, 219, 76, 14);
		contentPane.add(lblUserSkillThree);
		
		
		
		
		
		JComboBox<String> comboBoxTaskName = new JComboBox<String>();
		
		//Populate task name dropdown
		for(int i=0; i<dbClass.getAllTasks().size(); i++) {
			CaretakerTask currentTask = dbClass.getAllTasks().get(i);
			String currentTaskName = currentTask.getTitle();	
			comboBoxTaskName.addItem(currentTaskName);
		}
		
		//Populate initial values
		for(int i=0; i<dbClass.getAllTasks().size(); i++) {
			CaretakerTask currentTask = dbClass.getAllTasks().get(i);
			String currentTaskDesc = currentTask.getDesc();	
			String recSkillOne = "";
			String recSkillTwo = "";
			String recSkillThree = "";
			Integer daysUntilRepeat = currentTask.getDaysUntilRepeat();
			String startDate = currentTask.getDateCreated();
			String dueDate = currentTask.getDateDue();
			Integer importance = currentTask.getPriority();
			Boolean needsSigning = currentTask.isNeedsSigning();
			Boolean needsPeerChecking = currentTask.isNeedsPeerChecking();
			String extraConsiderations = currentTask.getExtraConsiderations();
			
			if(currentTask.getTitle().equals(comboBoxTaskName.getSelectedItem().toString())) {
				ArrayList<String> recSkills = currentTask.getRecSkills();
				int noOfRecSkills = recSkills.size();
				switch(noOfRecSkills) {
				case 1:
					recSkillOne = recSkills.get(0);
					break;
				case 2:
					recSkillOne = recSkills.get(0);
					recSkillTwo = recSkills.get(1);
					break;
				case 3:
					recSkillOne = recSkills.get(0);
					recSkillTwo = recSkills.get(1);
					recSkillThree = recSkills.get(2);
					break;
					
				}
				
				/* Template code for when login system implemented
				ArrayList<String> userSkills = loggedInUser.getSkills();
				int noOfUserSkills = userSkills.size();
				switch(noOfUserSkills) {
				case 1:
					userSkillOne = userSkills.get(0);
					break;
				case 2:
					userSkillOne = userSkills.get(0);
					userSkillTwo = userSkills.get(1);
					break;
				case 3:
					userSkillOne = userSkills.get(0);
					userSkillTwo = userSkills.get(1);
					userSkillThree = userSkills.get(2);
					break;
				}
				*/
				
				
			
				textAreaDescription.setText(currentTaskDesc);
				lblTaskTypeOne.setText(recSkillOne);
				lblTaskTypeTwo.setText(recSkillTwo);
				lblTaskTypeThree.setText(recSkillThree);
				// Template code for when login system implemented
				//lblUserSkillOne.setText(userSkillOne);
				//lblUserSkillTwo.setText(userSkillTwo);
				//lblUserSkillThree.setText(userSkillThree);
				lblFrequencyValue.setText(daysUntilRepeat.toString());
				lblStartDateValue.setText(startDate);
				lblDueDateValue.setText(dueDate);
				lblImportanceValue.setText(importance.toString());
				lblNeedsSigningValue.setText(needsSigning.toString());
				lblNeedsPeerCheckingValue.setText(needsPeerChecking.toString());
				textAreaExtraConsiderations.setText(extraConsiderations);
				
				
				
				
			}	
		}
		
		
		//Update values on change of task name dropdown
		comboBoxTaskName.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<dbClass.getAllTasks().size(); i++) {
					CaretakerTask currentTask = dbClass.getAllTasks().get(i);
					String currentTaskDesc = currentTask.getDesc();	
					String recSkillOne = "";
					String recSkillTwo = "";
					String recSkillThree = "";
					Integer daysUntilRepeat = currentTask.getDaysUntilRepeat();
					String startDate = currentTask.getDateCreated();
					String dueDate = currentTask.getDateDue();
					Integer importance = currentTask.getPriority();
					Boolean needsSigning = currentTask.isNeedsSigning();
					Boolean needsPeerChecking = currentTask.isNeedsPeerChecking();
					String extraConsiderations = currentTask.getExtraConsiderations();
					
					if(currentTask.getTitle().equals(comboBoxTaskName.getSelectedItem().toString())) {
						ArrayList<String> recSkills = currentTask.getRecSkills();
						int noOfRecSkills = recSkills.size();
						switch(noOfRecSkills) {
						case 1:
							recSkillOne = recSkills.get(0);
							break;
						case 2:
							recSkillOne = recSkills.get(0);
							recSkillTwo = recSkills.get(1);
							break;
						case 3:
							recSkillOne = recSkills.get(0);
							recSkillTwo = recSkills.get(1);
							recSkillThree = recSkills.get(2);
							break;
							
						}
						
						/* Template code for when login system implemented
						ArrayList<String> userSkills = loggedInUser.getSkills();
						int noOfUserSkills = userSkills.size();
						switch(noOfUserSkills) {
						case 1:
							userSkillOne = userSkills.get(0);
							break;
						case 2:
							userSkillOne = userSkills.get(0);
							userSkillTwo = userSkills.get(1);
							break;
						case 3:
							userSkillOne = userSkills.get(0);
							userSkillTwo = userSkills.get(1);
							userSkillThree = userSkills.get(2);
							break;
						}
						*/
						
						
						//Update GUI with task details
						textAreaDescription.setText(currentTaskDesc);
						lblTaskTypeOne.setText(recSkillOne);
						lblTaskTypeTwo.setText(recSkillTwo);
						lblTaskTypeThree.setText(recSkillThree);
						// Template code for when login system implemented
						//lblUserSkillOne.setText(userSkillOne);
						//lblUserSkillTwo.setText(userSkillTwo);
						//lblUserSkillThree.setText(userSkillThree);
						lblFrequencyValue.setText(daysUntilRepeat.toString());
						lblStartDateValue.setText(startDate);
						lblDueDateValue.setText(dueDate);
						lblImportanceValue.setText(importance.toString());
						lblNeedsSigningValue.setText(needsSigning.toString());
						lblNeedsPeerCheckingValue.setText(needsPeerChecking.toString());
						textAreaExtraConsiderations.setText(extraConsiderations);
						
						
						
						
						
						
					}	
				}
			}
		});
		
		
		JButton btnAllocateTask = new JButton("Allocate Task");
		btnAllocateTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<dbClass.getAllTasks().size(); i++) {
					CaretakerTask currentTask = dbClass.getAllTasks().get(i);
					//if(currentTask.getID() == 1);
					if(currentTask.getTitle().equals(comboBoxTaskName.getSelectedItem().toString())) {
						CaretakerTask updatedTask = currentTask;
						ArrayList<Integer> assignedCaretakers = new ArrayList<Integer>();
						//Template code for when login system is implemented
						assignedCaretakers.add(loggedInUser);
						updatedTask.setTeamMembers(assignedCaretakers);
						dbClass.updateCaretakerTask(updatedTask);
						CapytecGui.refreshTaskManagementGui();
					}
				}

				
				
				
			}
		});
		
		btnAllocateTask.setBounds(294, 623, 118, 36);
		contentPane.add(btnAllocateTask);
		
		
		/*
		comboBoxTaskName.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<dbClass.getAllTasks().size(); i++) {			
					CaretakerTask currentTask = dbClass.getAllTasks().get(i);
					if(currentTask.getTitle().equals(comboBoxTaskName.getSelectedItem().toString())) {
						String recSkillOne = "Placeholder";
						String recSkillTwo = ""; 
						String recSkillThree = "";
						ArrayList<String> recSkills = currentTask.getRecSkills();
						int noOfRecSkills = recSkills.size();
						switch(noOfRecSkills) {
						case 1:
							recSkillOne = recSkills.get(0);
							break;
						case 2:
							recSkillOne = recSkills.get(0);
							recSkillTwo = recSkills.get(1);
							break;
						case 3:
							recSkillOne = recSkills.get(0);
							recSkillTwo = recSkills.get(1);
							recSkillThree = recSkills.get(2);
							break;
							
					}
						
						
						
						
						
						
						
						
						String currentTaskName = currentTask.getTitle();
						String currentTaskDesc = currentTask.getDesc();
						comboBoxTaskName.addItem(currentTaskName);
						textAreaDescription.setText(currentTaskDesc);
						lblTaskTypeOne.setText(recSkillOne);
						lblTaskTypeTwo.setText(recSkillTwo);
						lblTaskTypeThree.setText(recSkillThree);
					}
					
					
					
					
				}
				
				
				//CaretakerTask currentTask = dbClass.getAllTasks().get(i);
			}
		});
		*/
		comboBoxTaskName.setBounds(97, 68, 266, 22);
		contentPane.add(comboBoxTaskName);
		
		
		
		
		
		
		
		
		
		
		

	}
}
