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
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JComboBox;
import java.awt.Color;
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
					//Create and make allocate task form frame visible
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
		//Set frame to close independently on exit rather than close whole program
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Set size of content pane
		setBounds(100, 100, 450, 725);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Placeholder value until login system is implemented
		int loggedInUser = 8;
		
		//Label to tell user they are selecting between task names in the adjacent dropdown
		JLabel lblTaskName = new JLabel("Task Name:");
		lblTaskName.setBounds(22, 70, 144, 14);
		contentPane.add(lblTaskName);
		
		//Label to tell user they are reading the task description in the adjacent textbox
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(22, 101, 144, 14);
		contentPane.add(lblDescription);
		
		//Subheading to tell user they are reading recommended skills
		JLabel lblTaskType = new JLabel("Recommended Skills:");
		lblTaskType.setBounds(22, 160, 144, 14);
		contentPane.add(lblTaskType);
		
		//Form title to tell user they are allocating a task
		JLabel lblAllocateTask = new JLabel("Allocate Task");
		lblAllocateTask.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAllocateTask.setBounds(87, 20, 202, 34);
		contentPane.add(lblAllocateTask);
		
		//Frame to tell user they are reading the frequency of the tasks repetition
		JLabel lblFrequency = new JLabel("Days Until Repeat");
		lblFrequency.setBounds(22, 283, 162, 14);
		contentPane.add(lblFrequency);
		
		//Create models to store the format for the start and due dates to be displayed
		UtilDateModel modelStartDate = new UtilDateModel();
		
		Properties pStart = new Properties();
		pStart.put("text.today", "Today");
		pStart.put("text.month", "Month");
		pStart.put("text.year", "Year");
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(22, 340, 144, 14);
		contentPane.add(lblStartDate);
		
		UtilDateModel modelDueDate = new UtilDateModel();
		Properties pDue = new Properties();
		pDue.put("text.today", "Today");
		pDue.put("text.month", "Month");
		pDue.put("text.year", "Year");
		
		//Label to tell user they are reading the selected tasks due date that is adjacent to the label
		JLabel lblDueDate = new JLabel("Due Date:");
		lblDueDate.setBounds(22, 380, 144, 14);
		contentPane.add(lblDueDate);
		
		//Label to tell user they are reading the selected tasks importance level that is adjacent to the label
		JLabel lblImportance = new JLabel("Importance:");
		lblImportance.setBounds(22, 428, 144, 14);
		contentPane.add(lblImportance);
		
		//Label to tell the user they are reading if the selected task needs signing off
		JLabel lblNeedsSigning = new JLabel("Needs Signing:");
		lblNeedsSigning.setBounds(22, 470, 162, 14);
		contentPane.add(lblNeedsSigning);
		
		//Label to tell the user they are reading if the selected task needs peer checkings
		JLabel lblNeedsPeerChecking = new JLabel("Needs Peer Checking:");
		lblNeedsPeerChecking.setBounds(22, 500, 162, 14);
		contentPane.add(lblNeedsPeerChecking);
		
		//Label to tell user allocation was a success. Only visible after successful allocation.
		JLabel lblSuccessPrompt = new JLabel("Task Allocated. Restart GUI to view changes");
		lblSuccessPrompt.setForeground(Color.RED);
		lblSuccessPrompt.setBounds(22, 606, 267, 69);
		contentPane.add(lblSuccessPrompt);
		lblSuccessPrompt.setVisible(false);
		
		//Label to tell user allocation was a failure. Only visible after task allocation fails
		JLabel lblErrorMessage = new JLabel("Task already assigned");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(22, 626, 180, 31);
		contentPane.add(lblErrorMessage);
		lblErrorMessage.setVisible(false);
		
		//Text box containing the description for the currently selected task
		JTextArea textAreaDescription = new JTextArea();
		textAreaDescription.setBounds(95, 96, 317, 53);
		//Add line wrapping after words in text field.
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setWrapStyleWord(true);
		//Made it so user cannot edit box
		textAreaDescription.setEditable(false);	
		contentPane.add(textAreaDescription);
		
		//Three labels containing the relevant task types for that particular tasks, presenting them to the user (Gardening, Plumbing etc)
		JLabel lblTaskTypeOne = new JLabel("Placeholder");
		lblTaskTypeOne.setBounds(22, 183, 96, 14);
		contentPane.add(lblTaskTypeOne);
		
		JLabel lblTaskTypeTwo = new JLabel("Placeholder");
		lblTaskTypeTwo.setBounds(22, 200, 98, 14);
		contentPane.add(lblTaskTypeTwo);
		
		JLabel lblTaskTypeThree = new JLabel("Placeholder");
		lblTaskTypeThree.setBounds(22, 219, 125, 14);
		contentPane.add(lblTaskTypeThree);
		
		//Label storing the frequency of the tasks repetition, or if it is a one-off task
		JLabel lblFrequencyValue = new JLabel("Frequency");
		lblFrequencyValue.setBounds(191, 283, 118, 14);
		contentPane.add(lblFrequencyValue);
		
		//Label storing the start value of the task
		JLabel lblStartDateValue = new JLabel("Start");
		lblStartDateValue.setBounds(191, 340, 187, 14);
		contentPane.add(lblStartDateValue);
		
		//Label storing the due date value of the task
		JLabel lblDueDateValue = new JLabel("Due");
		lblDueDateValue.setBounds(191, 380, 187, 14);
		contentPane.add(lblDueDateValue);
		
		//Label storing the importance rating for the task
		JLabel lblImportanceValue = new JLabel("New label");
		lblImportanceValue.setBounds(191, 428, 98, 14);
		contentPane.add(lblImportanceValue);
		
		//Label storing whether the task needs peer checking
		JLabel lblNeedsPeerCheckingValue = new JLabel("New label");
		lblNeedsPeerCheckingValue.setBounds(191, 500, 98, 14);
		contentPane.add(lblNeedsPeerCheckingValue);
		
		//Label storing whether the task needs signing off
		JLabel lblNeedsSigningValue = new JLabel("New label");
		lblNeedsSigningValue.setBounds(191, 470, 98, 14);
		contentPane.add(lblNeedsSigningValue);
		
		//Label telling user they are reading extra considerations for that particular task, holding any extra information surrounding that task
		JLabel lblExtraConsiderations = new JLabel("Extra Considerations:");
		lblExtraConsiderations.setBounds(22, 531, 150, 14);
		contentPane.add(lblExtraConsiderations);
		
		//Uneditable textbox containing the extra considerations for the task
		JTextArea textAreaExtraConsiderations = new JTextArea();
		textAreaExtraConsiderations.setBounds(191, 525, 172, 50);
		//Add line wrapping after words in text field.
		textAreaExtraConsiderations.setLineWrap(true);
		textAreaExtraConsiderations.setWrapStyleWord(true);
		//Make is so user cannot edit box
		textAreaExtraConsiderations.setEditable(false);	
		contentPane.add(textAreaExtraConsiderations);
		
		//Subheader and labels showing the skills of the currently logged in user
		JLabel lblUserSkills = new JLabel("Your Skills:");
		lblUserSkills.setBounds(157, 160, 83, 14);
		contentPane.add(lblUserSkills);
		
		JLabel lblUserSkillOne = new JLabel("Placeholder Skill One");
		lblUserSkillOne.setBounds(157, 183, 180, 14);
		contentPane.add(lblUserSkillOne);
		
		JLabel lblUserSkillTwo = new JLabel("Placeholder Skill Two");
		lblUserSkillTwo.setBounds(157, 200, 132, 14);
		contentPane.add(lblUserSkillTwo);
		
		JLabel lblUserSkillThree = new JLabel("Placeholder Skill Three");
		lblUserSkillThree.setBounds(157, 219, 132, 14);
		contentPane.add(lblUserSkillThree);
		
		
		
		
		//Dropdown for task names
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
				//Get details of each stored task
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
					
					//If task is the same as currently selected task by user, update GUI elements with details
					if(currentTask.getTitle().equals(comboBoxTaskName.getSelectedItem().toString())) {
						//Get same number of skills as stored in database. Prevents index out of bounds issues.
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
		
		//Create allocate task button
		JButton btnAllocateTask = new JButton("Allocate Task");
		btnAllocateTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSuccessPrompt.setVisible(false);
				lblErrorMessage.setVisible(false);
				//Loop through each stored task
				for(int i=0; i<dbClass.getAllTasks().size(); i++) {
					CaretakerTask currentTask = dbClass.getAllTasks().get(i);
					//If task matches currently selected task by user set new team member and run database update method
					if(currentTask.getTitle().equals(comboBoxTaskName.getSelectedItem().toString()) && currentTask.getTeamMembers().isEmpty()) {
						CaretakerTask updatedTask = currentTask;
						ArrayList<Integer> assignedCaretakers = new ArrayList<Integer>();
						//Template code for when login system is implemented
						assignedCaretakers.add(loggedInUser);
						updatedTask.setTeamMembers(assignedCaretakers);
						dbClass.updateCaretakerTask(updatedTask);
						lblErrorMessage.setVisible(false);
						lblSuccessPrompt.setVisible(true);
					} else if(currentTask.getTitle().equals(comboBoxTaskName.getSelectedItem().toString()) && !currentTask.getTeamMembers().isEmpty()){
						//Output error information
						lblSuccessPrompt.setVisible(false);
						lblErrorMessage.setVisible(true);

					}
				} 
				
				
				
			}
		});
		
		//Set dimensions for allocate task button
		btnAllocateTask.setBounds(294, 623, 118, 36);
		contentPane.add(btnAllocateTask);
		comboBoxTaskName.setBounds(97, 68, 266, 22);
		contentPane.add(comboBoxTaskName);
		
		
		
		
		
		
		
		
		
		
		
		

	}
}
