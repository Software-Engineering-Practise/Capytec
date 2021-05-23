import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class GuiSetCompleted extends JFrame {

	private JPanel contentPane;
	CapyTecDB dbClass = new CapyTecDB();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiSetCompleted frame = new GuiSetCompleted();
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
	public GuiSetCompleted() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCompleteTaskTitle = new JLabel("Set Task as Complete");
		lblCompleteTaskTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompleteTaskTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCompleteTaskTitle.setBounds(86, 11, 260, 42);
		contentPane.add(lblCompleteTaskTitle);;
		
		JLabel lblSelectedTask = new JLabel("Task ID");
		lblSelectedTask.setBounds(114, 64, 46, 14);
		contentPane.add(lblSelectedTask);
		
		int userLoggedIn = 8;
		
		JComboBox dropdownTaskID = new JComboBox();
		dropdownTaskID.setModel(new DefaultComboBoxModel());
		dropdownTaskID.addItem("Select a Task ID");
		for (int i = 0 ; i < dbClass.getAllTasks().size() ; i++) {
			CaretakerTask currentTask = dbClass.getAllTasks().get(i);
			if (currentTask.getTeamMembers().contains(userLoggedIn) && (currentTask.getDaysUntilRepeat() != 0 || currentTask.getDateCompleted() == null || currentTask.getDateCompleted().equals("")))
			{
				dropdownTaskID.addItem(currentTask.getID());
			}
		}
		dropdownTaskID.setBounds(208, 60, 138, 22);
		contentPane.add(dropdownTaskID);
		
		JButton btnCompleteTask = new JButton("Select a Task");
		btnCompleteTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button pressed. Task " + (dropdownTaskID.getSelectedItem()) + " selected");
				
				for (int i = 0 ; i < dbClass.getAllTasks().size() ; i++)
				{
					CaretakerTask currentTask = dbClass.getAllTasks().get(i);
					if (currentTask.getID() == (int) dropdownTaskID.getSelectedItem())
					{
						CaretakerTask completedTask = currentTask;
						CompletedTask newCompletion = new CompletedTask();
						newCompletion.setTaskID(completedTask.getID());
						newCompletion.setUserID(userLoggedIn);
						
						
						
						String taskDate = "";
						//YYYY-MM-DD hh:mm:ss
						
						DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();
							
						taskDate += now.getYear();
						taskDate += ("-" + now.getMonthValue());
						taskDate += ("-" + now.getDayOfMonth());
						taskDate += (" " + now.getHour());
						taskDate += (":" + now.getMinute());
						taskDate += (":" + now.getSecond());
							
						newCompletion.setDateCompleted(taskDate);
						System.out.println("Date is: " + taskDate);
						
						Caretaker currentUserC;
						Manager currentUserM;
						
						//Checks to see who the currently logged in user is, in order to get their name.
						//As user could be either a regular caretaker or a manager, it has to loop through both
						//If it finds the correct ID, it then sets the completionist and completionist ID
						completedTask.setDateCompleted(taskDate);
						for (int j = 0 ; j < dbClass.getAllCaretakers().size() ; j++)
						{
							Caretaker currentCaretaker = dbClass.getAllCaretakers().get(j);
							if (currentCaretaker.getID() == userLoggedIn)
							{
								currentUserC = currentCaretaker;
								completedTask.setCompletionist(currentUserC.getFullName());
								completedTask.setCompletionistID(currentUserC.getID());
							}
						}
						
						for (int j = 0 ; j < dbClass.getAllManagers().size() ; j++)
						{
							Manager currentManager = dbClass.getAllManagers().get(j);
							if (currentManager.getID() == userLoggedIn)
							{
								currentUserM = currentManager;
								completedTask.setCompletionist(currentUserM.getFullName());
								completedTask.setCompletionistID(currentUserM.getID());
							}
						}
						
						//If it's a task that repeats, the task must be set to be re-signed or re-checked, if it needs checking or signing
						//Checks to see if task repeats, then checks to see if it needs peer checking and signing
						//If it needs either, the person who has checked or signed is reset
						if (completedTask.getDaysUntilRepeat() != 0)
						{
							if (completedTask.isNeedsPeerChecking())
							{
								completedTask.setPeerChecker(null);
								completedTask.setPeerCheckerID(0);
							}
							if (completedTask.isNeedsSigning())
							{
								completedTask.setSignee(null);
								completedTask.setSigneeID(0);
							}
						}
						
						//Adds an entry to the completed task database
						dbClass.addCompletedTask(newCompletion);
						//Updates task with new details
						dbClass.updateCaretakerTask(completedTask);
						
						System.out.println("Task " + completedTask.getID() + ". Set as completed on date: " + completedTask.getDateCompleted());
					}
				}				
			}
		});
		btnCompleteTask.setEnabled(false);
		btnCompleteTask.setBounds(96, 170, 250, 42);
		contentPane.add(btnCompleteTask);
		
		
		dropdownTaskID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Button can only be accessed if a valid task has been selected.
				//The first item (index 0) in the dropdown is not a valid task
				if (dropdownTaskID.getSelectedIndex() != 0)
				{
					CaretakerTask currentTask = dbClass.getAllTasks().get((int)dropdownTaskID.getSelectedItem()-1);
					//If the task needs to be peer checked, and hasn't been, or needs to be signed, and hasn't been, the button is disabled.
					//Otherwise, if the user logged in is part of the task selected, the button can be selected
					//However if this is not the case, the button is disabled.
					if (currentTask.isNeedsPeerChecking() && currentTask.getPeerChecker() == null)
					{
						btnCompleteTask.setEnabled(false);
						btnCompleteTask.setText("This task has not been checked");
					}
					else if (currentTask.isNeedsSigning() && currentTask.getSignee() == null)
					{
						btnCompleteTask.setEnabled(false);
						btnCompleteTask.setText("This task has not been signed off");
					}
					else if (currentTask.getTeamMembers().contains(userLoggedIn))
					{
						btnCompleteTask.setEnabled(true);
						btnCompleteTask.setText("Set Task " + currentTask.getID() + " as Complete");
					}
					else
					{
						btnCompleteTask.setEnabled(false);
						btnCompleteTask.setText("This is not your task.");
					}
				}
				else
				{
					btnCompleteTask.setEnabled(false);
					btnCompleteTask.setText("Please Select a Task");
				}
			}
		});
		
		
		
	}

}
