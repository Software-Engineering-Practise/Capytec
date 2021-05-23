import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JComboBox;

//Main GUI Class
public class CapytecGui extends JFrame {
	
	private int loggedInId = 0;
	
	private JPanel contentPane;
	private JTable tableUserManagement;
	private static JTable tableTaskManagement;
	CapyTecDB dbClass = new CapyTecDB();
	private JTable tableTaskLogging;

	/**
	 * Launch the application.
	 */
	public static void main(boolean isManager) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					GuiLogin login = new GuiLogin();
					login.setVisible(true);
					
					CapytecGui frame = new CapytecGui(isManager);
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
	public CapytecGui(boolean isManager) {
		//Exit application when JFrame is closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set size of JFrame and other window settings. Display the frame.
		setBounds(100, 100, 1127, 984);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Create a multi-tab frame, with each tab to encompass a different feature within the application
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		if(isManager) {
			JPanel panelUserManagement = new JPanel();
			tabbedPane.addTab("User Management", null, panelUserManagement, null);
			panelUserManagement.setLayout(new BorderLayout(0, 0));
		
		
			//Add a JPanel which will store the buttons across the bottom of the application
			JPanel panelTaskBottomButtons = new JPanel();
			panelUserManagement.add(panelTaskBottomButtons, BorderLayout.SOUTH);
			
			JButton btnAddUser = new JButton("Add User");
			btnAddUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GuiInsertUser frameInsertUser = new GuiInsertUser();
					frameInsertUser.setVisible(true);
				}
			});
			panelTaskBottomButtons.add(btnAddUser);
			
			JButton btnUpdateUser = new JButton("Update User");
			btnUpdateUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GuiUpdateUser frameUpdateUser = new GuiUpdateUser();
					frameUpdateUser.setVisible(true);
				}
			});
			panelTaskBottomButtons.add(btnUpdateUser);
			
			JButton btnRemoveUser = new JButton("Remove User");
			btnRemoveUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GuiDeleteUser frameDeleteUser = new GuiDeleteUser();
					frameDeleteUser.setVisible(true);
				}
			});
			panelTaskBottomButtons.add(btnRemoveUser);
			
			JPanel panelUserTitle = new JPanel();
			panelUserManagement.add(panelUserTitle, BorderLayout.NORTH);
			
			//User management tab title
			JLabel lblUserManagement = new JLabel("User Management");
			lblUserManagement.setFont(new Font("Tahoma", Font.PLAIN, 20));
			panelUserTitle.add(lblUserManagement);
			
			//Scroll pane along with table displaying users within the application and their details
			JScrollPane scrollPaneUserManagement = new JScrollPane();
			panelUserManagement.add(scrollPaneUserManagement, BorderLayout.CENTER);
			tableUserManagement = new JTable();
			//Create table layout
			//System.out.println(dbClass.getAllCaretakers().get(1).getFullName());
			tableUserManagement.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"ID", "User", "Position", "Skills"
				}
			));
			//Set dimensions of table columns
			tableUserManagement.getColumnModel().getColumn(0).setPreferredWidth(121);
			tableUserManagement.getColumnModel().getColumn(1).setPreferredWidth(351);
			tableUserManagement.getColumnModel().getColumn(2).setPreferredWidth(197);
			tableUserManagement.getColumnModel().getColumn(3).setPreferredWidth(226);
			
			DefaultTableModel tableModelUserManagement = (DefaultTableModel)tableUserManagement.getModel();
			
			//Add rows for all caretakers and managers
			for(int i=0; i<dbClass.getAllCaretakers().size(); i++) {
				Caretaker currentCaretaker = dbClass.getAllCaretakers().get(i);
				tableModelUserManagement.addRow(new Object[] {currentCaretaker.getID(),currentCaretaker.getFullName(),currentCaretaker.getJobTitle(),"Skill"});
			}
			for(int i=0; i<dbClass.getAllManagers().size(); i++) {
				Manager currentManager = dbClass.getAllManagers().get(i);
				tableModelUserManagement.addRow(new Object[] {currentManager.getID(),currentManager.getFullName(),currentManager.getJobTitle(),"Skill"});
			}
			scrollPaneUserManagement.setViewportView(tableUserManagement);
		
			//Add task management as a tab to the tabbedPane
			JPanel panelTaskManagement = new JPanel();
			tabbedPane.addTab("Task Management", null, panelTaskManagement, null);
			panelTaskManagement.setLayout(new BorderLayout(0, 0));
			
			JPanel panelUserBottomButtons = new JPanel();
			panelTaskManagement.add(panelUserBottomButtons, BorderLayout.SOUTH);
			
			//Add task button which opens the insert task form on click
			JButton btnAddTask = new JButton("Add Task");
			btnAddTask.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GuiInsertTask frameInsertTask = new GuiInsertTask();
					frameInsertTask.setVisible(true);
				}
			});
			panelUserBottomButtons.add(btnAddTask);
			
			//Assign task button which opens the assign task form on click
			JButton btnAssignTask = new JButton("Assign Task");
			btnAssignTask.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GuiAllocateTask frameAllocateTask = new GuiAllocateTask();
					frameAllocateTask.setVisible(true);
				}
			});
			panelUserBottomButtons.add(btnAssignTask);
			
			//Remove task button which opens the remove task form on click
			JButton btnRemoveTask = new JButton("Remove Task");
			btnRemoveTask.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GuiDeleteTask frameDeleteTask = new GuiDeleteTask();
					frameDeleteTask.setVisible(true);
				}
			});
			panelUserBottomButtons.add(btnRemoveTask);
			
			//Daily briefing button which generates currently logged in user daily briefing on click
			JButton btnGetBriefing = new JButton("Daily Briefing");
			btnGetBriefing.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GuiDailyBriefing frameDailyBriefing = new GuiDailyBriefing();
					frameDailyBriefing.setVisible(true);
				}
			});
			panelUserBottomButtons.add(btnGetBriefing);
			
			JPanel panelTaskTitle = new JPanel();
			panelTaskManagement.add(panelTaskTitle, BorderLayout.NORTH);
			
			//Add task management title
			JLabel lblTaskManagement = new JLabel("Task Management");
			lblTaskManagement.setFont(new Font("Tahoma", Font.PLAIN, 20));
			panelTaskTitle.add(lblTaskManagement);
			
			//Add task management to scroll pane
			JScrollPane scrollPaneTaskManagement = new JScrollPane();
			panelTaskManagement.add(scrollPaneTaskManagement, BorderLayout.CENTER);
			
			//Create table structure for task management
			tableTaskManagement = new JTable() {
				//Make it so user cannot edit the table at runtime
				public boolean editCellAt(int row, int column, java.util.EventObject e) {
					return false;
				}
			};
			tableTaskManagement.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Task No.", "Task", "Assigned Caretaker", "Description", "Type", "Start Date", "Due Date", "Completion Date", "Completionist", "Importance", "Days Until Repeat"
				}
			));
			tableTaskManagement.getColumnModel().getColumn(1).setPreferredWidth(270);
			tableTaskManagement.getColumnModel().getColumn(2).setPreferredWidth(173);
			tableTaskManagement.getColumnModel().getColumn(3).setPreferredWidth(342);
			tableTaskManagement.getColumnModel().getColumn(4).setPreferredWidth(129);
			tableTaskManagement.getColumnModel().getColumn(5).setPreferredWidth(162);
			tableTaskManagement.getColumnModel().getColumn(6).setPreferredWidth(162);
			tableTaskManagement.getColumnModel().getColumn(7).setPreferredWidth(175);
			tableTaskManagement.getColumnModel().getColumn(8).setPreferredWidth(192);
			tableTaskManagement.getColumnModel().getColumn(9).setPreferredWidth(98);
			tableTaskManagement.getColumnModel().getColumn(10).setPreferredWidth(131);
			
			DefaultTableModel tableModelTaskManagement = (DefaultTableModel)tableTaskManagement.getModel();
			
			//Add row sorting for task management table
			tableTaskManagement.setAutoCreateRowSorter(true);
			
			//Populate task management table
			for(int i=0; i<dbClass.getAllTasks().size(); i++) {
				CaretakerTask currentItem = dbClass.getAllTasks().get(i);
				int repeat = currentItem.getDaysUntilRepeat();
				String isRepeated;
				String daysUntilRepeat;
				String skillsList = " ";
				String assignedCaretakers = " ";
				
				//Add assigned caretakers to task table
				for(int y=0; y<currentItem.getTeamMembers().size(); y++) {
					assignedCaretakers += currentItem.getTeamMembers().get(y) + " ";
				}
				
				//Add recommended skills to task table
				for(int x=0; x<currentItem.getRecSkills().size(); x++) {
					skillsList += currentItem.getRecSkills().get(x) + " ";
				}
				
				//Set whether a task is a one-off task, or how many days until it needs to be repeated
				if(repeat == 0) {
					isRepeated = "One-off";
					tableModelTaskManagement.addRow(new Object[] {currentItem.getID(),currentItem.getTitle(),assignedCaretakers,currentItem.getDesc(),skillsList,currentItem.getDateCreated(),currentItem.getDateDue(),currentItem.getDateCompleted(),currentItem.getCompletionist(),currentItem.getPriority(),"One-off"});
				} else {
					isRepeated = "Repeats";
					daysUntilRepeat = "" + repeat;
					tableModelTaskManagement.addRow(new Object[] {currentItem.getID(),currentItem.getTitle(),assignedCaretakers,currentItem.getDesc(),skillsList,currentItem.getDateCreated(),currentItem.getDateDue(),currentItem.getDateCompleted(),currentItem.getCompletionist(),currentItem.getPriority(),daysUntilRepeat});
				}
			}		
			
			scrollPaneTaskManagement.setViewportView(tableTaskManagement);
		}
		//Task Logging - Mission 9
		
		JPanel panelTaskLogging = new JPanel();
		tabbedPane.addTab("Task Logging", null, panelTaskLogging, null);
		panelTaskLogging.setLayout(new BorderLayout(0, 0));
		
		JPanel panelLoggingTitle = new JPanel();
		panelTaskLogging.add(panelLoggingTitle, BorderLayout.NORTH);
		
		JLabel lblTaskLogging = new JLabel("Task Logging");
		lblTaskLogging.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelLoggingTitle.add(lblTaskLogging);
		
		JPanel panelTaskLoggingButtons = new JPanel();
		panelTaskLogging.add(panelTaskLoggingButtons, BorderLayout.SOUTH);
		
		JButton btnSignTask = new JButton("Sign Task");
		btnSignTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GuiSignTask frameSignTask = new GuiSignTask();
				frameSignTask.setVisible(true);
			}
		});
		panelTaskLoggingButtons.add(btnSignTask);
		
		JButton btnSetCompleted = new JButton("Set Completed");
		btnSetCompleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GuiSetCompleted frameSetCompleted = new GuiSetCompleted();
				frameSetCompleted.setVisible(true);
			}
		});
		panelTaskLoggingButtons.add(btnSetCompleted);
		
		JButton btnCheckTask = new JButton("Check Task");
		btnCheckTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GuiCheckTask frameCheckTask = new GuiCheckTask();
				frameCheckTask.setVisible(true);
			}
		});
		panelTaskLoggingButtons.add(btnCheckTask);
		
		
		
		JScrollPane scrollPaneTaskLogging = new JScrollPane();
		panelTaskLogging.add(scrollPaneTaskLogging, BorderLayout.CENTER);
		
		JTable tableTaskLogging = new JTable() {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		
		tableTaskLogging.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Assigned Caretaker(s)", "Task ID", "Task", "Task Set", "Repeated Task?", "Repeat Period", "Completed Date", "Extra Requirements", "Checked By", "Signed By", "Priority"
			}
		));
		
		DefaultTableModel tableModelTaskLogging = (DefaultTableModel)tableTaskLogging.getModel();
		
		//Load all current tasks, one off tasks.
		for (int i = 0 ; i < dbClass.getAllTasks().size() ; i++)
		{
			CaretakerTask currentItem = dbClass.getAllTasks().get(i);
			
			
			int repeat = currentItem.getDaysUntilRepeat();
			String members;
			if (currentItem.getTeamMembers().isEmpty()) {
				members = "No Assigned Caretakers";
			}
			else {
				members = "" + currentItem.getTeamMembers();
			}
			String isRepeated;
			String daysRepeat;
			if (repeat == 0)
			{
				isRepeated = "Doesn't repeat";
				daysRepeat = "N/A";
			}
			else
			{
				isRepeated = "Yes";
				daysRepeat = "" + repeat;
			}
			String extraReqs;
			String signedBy;
			String checkedBy;
			if (currentItem.isNeedsSigning() && currentItem.isNeedsPeerChecking()) {
				extraReqs = "Needs Both Peer Checking and Signing";
				signedBy = currentItem.getSignee();
				checkedBy = currentItem.getPeerChecker(); }
			else if (currentItem.isNeedsPeerChecking()) {
				extraReqs = "Needs Peer Checking";
				signedBy = "N/A";
				checkedBy = currentItem.getPeerChecker(); }
			else if (currentItem.isNeedsSigning()) {
				extraReqs = "Needs Signing";
				signedBy = currentItem.getSignee();
				checkedBy = "N/A"; }
			else {
				extraReqs = "No Extra Requirements"; 
				signedBy = "N/A";
				checkedBy = "N/A"; }
			if ((currentItem.getDateCompleted() == null || currentItem.getDateCompleted().equals("")) && isRepeated != "Yes")
			{
				//System.out.println("Current item " + currentItem.getID() + ". Completed date: " + currentItem.getDateCompleted());
				tableModelTaskLogging.addRow(new Object[] {members, currentItem.getID(), currentItem.getTitle(), currentItem.getDateCreated(), isRepeated, daysRepeat, currentItem.getDateCompleted(), extraReqs, checkedBy, signedBy, currentItem.getPriority()});
			}
		}
		
		//Load all current tasks, repeated tasks.
				for (int i = 0 ; i < dbClass.getAllTasks().size() ; i++)
				{
					CaretakerTask currentItem = dbClass.getAllTasks().get(i);
					
					
					int repeat = currentItem.getDaysUntilRepeat();
					String members;
					if (currentItem.getTeamMembers().isEmpty()) {
						members = "No Assigned Caretakers";
					}
					else {
						members = "" + currentItem.getTeamMembers();
					}
					String isRepeated;
					String daysRepeat;
					if (repeat == 0)
					{
						isRepeated = "Doesn't repeat";
						daysRepeat = "N/A";
					}
					else
					{
						isRepeated = "Yes";
						daysRepeat = "" + repeat;
					}
					String extraReqs;
					String signedBy;
					String checkedBy;
					if (currentItem.isNeedsSigning() && currentItem.isNeedsPeerChecking()) {
						extraReqs = "Needs Both Peer Checking and Signing";
						signedBy = currentItem.getSignee();
						checkedBy = currentItem.getPeerChecker(); }
					else if (currentItem.isNeedsPeerChecking()) {
						extraReqs = "Needs Peer Checking";
						signedBy = "N/A";
						checkedBy = currentItem.getPeerChecker(); }
					else if (currentItem.isNeedsSigning()) {
						extraReqs = "Needs Signing";
						signedBy = currentItem.getSignee();
						checkedBy = "N/A"; }
					else {
						extraReqs = "No Extra Requirements"; 
						signedBy = "N/A";
						checkedBy = "N/A"; }
					if (isRepeated == "Yes")
						tableModelTaskLogging.addRow(new Object[] {members, currentItem.getID(), currentItem.getTitle(), currentItem.getDateCreated(), isRepeated, daysRepeat, currentItem.getDateCompleted(), extraReqs, checkedBy, signedBy, currentItem.getPriority()});
				}
		
		//Load all completed tasks
		for (int i = 0 ; i < dbClass.getAllTasks().size() ; i++)
		{
			CaretakerTask currentItem = dbClass.getAllTasks().get(i);
			
			
			int repeat = currentItem.getDaysUntilRepeat();
			String members;
			if (currentItem.getTeamMembers().isEmpty()) {
				members = "No Assigned Caretakers";
			}
			else {
				members = "" + currentItem.getTeamMembers();
			}
			String isRepeated;
			String daysRepeat;
			if (repeat == 0)
			{
				isRepeated = "Doesn't repeat";
				daysRepeat = "N/A";
			}
			else
			{
				isRepeated = "Yes";
				daysRepeat = "" + repeat;
			}
			String extraReqs;
			String signedBy;
			String checkedBy;
			if (currentItem.isNeedsSigning() && currentItem.isNeedsPeerChecking()) {
				extraReqs = "Needs Both Peer Checking and Signing";
				signedBy = currentItem.getSignee();
				checkedBy = currentItem.getPeerChecker(); }
			else if (currentItem.isNeedsPeerChecking()) {
				extraReqs = "Needs Peer Checking";
				signedBy = "N/A";
				checkedBy = currentItem.getPeerChecker(); }
			else if (currentItem.isNeedsSigning()) {
				extraReqs = "Needs Signing";
				signedBy = currentItem.getSignee();
				checkedBy = "N/A"; }
			else {
				extraReqs = "No Extra Requirements"; 
				signedBy = "N/A";
				checkedBy = "N/A"; }
			if (currentItem.getDateCompleted() != null && !currentItem.getDateCompleted().equals("") && isRepeated != "Yes")
			{
				String currentDate = currentItem.getDateCompleted();
				//System.out.println("Current item " + currentItem.getID() + ". Completed date: " + currentItem.getDateCompleted() + currentItem.getDateCompleted().compareTo(""));
				tableModelTaskLogging.addRow(new Object[] {members, currentItem.getID(), currentItem.getTitle(), currentItem.getDateCreated(), isRepeated, daysRepeat, currentItem.getDateCompleted(), extraReqs, checkedBy, signedBy, currentItem.getPriority()});
			}
		}
		scrollPaneTaskLogging.setViewportView(tableTaskLogging);
		
		//Reporting - Mission 10
		
		if(isManager) {
		
		JPanel panelReporting = new JPanel();
		tabbedPane.addTab("Reporting", null, panelReporting, null);
		panelReporting.setLayout(new BorderLayout(0, 0));
		
		JPanel panelReportingTitle = new JPanel();
		panelReporting.add(panelReportingTitle, BorderLayout.NORTH);
		
		JLabel lblReportingTitle = new JLabel("Reporting");
		lblReportingTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelReportingTitle.add(lblReportingTitle);
		
		JPanel panelReportingButtons = new JPanel();
		panelReporting.add(panelReportingButtons, BorderLayout.SOUTH);
		
		JPanel panelReportPresentation = new JPanel();
		panelReporting.add(panelReportPresentation, BorderLayout.CENTER);
		panelReportPresentation.setLayout(new BorderLayout(0, 0));
		
		JPanel panelReportSettings = new JPanel();
		panelReportPresentation.add(panelReportSettings, BorderLayout.NORTH);
		
		JLabel lblTaskDropdown = new JLabel("Selected Task:");
		lblTaskDropdown.setVisible(false);
		panelReportSettings.add(lblTaskDropdown);
		
		JLabel lblCaretakerDropdown = new JLabel("Selected Caretaker:");
		lblCaretakerDropdown.setVisible(false);
		panelReportSettings.add(lblCaretakerDropdown);
		
		JComboBox comboBoxSelectedCaretaker = new JComboBox();
		panelReportSettings.add(comboBoxSelectedCaretaker);
		comboBoxSelectedCaretaker.setVisible(false);
		
		comboBoxSelectedCaretaker.setModel(new DefaultComboBoxModel());
		comboBoxSelectedCaretaker.addItem("Select a Caretaker");
		
		ArrayList<Caretaker> allCaretakers = dbClass.getAllCaretakers();
		
		for (int i = 0 ; i < allCaretakers.size(); i++)
		{
			Caretaker currentCaretaker = allCaretakers.get(i);
			comboBoxSelectedCaretaker.addItem(currentCaretaker.getID());
		}
		
		
		JComboBox comboBoxSelectedTask = new JComboBox();
		panelReportSettings.add(comboBoxSelectedTask);
		comboBoxSelectedTask.setVisible(false);
		
		comboBoxSelectedTask.setModel(new DefaultComboBoxModel());
		comboBoxSelectedTask.addItem("Select a Task");;
		
		ArrayList<CaretakerTask> allTasks = dbClass.getAllTasks();
		
		for (int i = 0 ; i < allTasks.size(); i++)
		{
			CaretakerTask currentTask = allTasks.get(i);
			comboBoxSelectedTask.addItem(currentTask.getID());
		}
		
		//Define each report-type button.
		JButton btnCaretakerReports = new JButton("Caretaker Reports");
		panelReportingButtons.add(btnCaretakerReports);
		
		JButton btnCurrentTasksReports = new JButton("Current Tasks");
		panelReportingButtons.add(btnCurrentTasksReports);
		
		JButton btnHistoricTasks = new JButton("Historic Tasks");
		panelReportingButtons.add(btnHistoricTasks);
		
		//Define the text pane.
		JTextPane textPaneReport = new JTextPane();
		textPaneReport.setEditable(false);
		panelReportPresentation.add(textPaneReport, BorderLayout.CENTER);
		
		//Report generation button and panel.
		JPanel panelGenerateButton = new JPanel();
		panelReportPresentation.add(panelGenerateButton, BorderLayout.SOUTH);
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.setVisible(false);
		panelGenerateButton.add(btnGenerateReport);
	
		
		//Report type buttons
		btnHistoricTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Historic tasks report button");
				int mode = 2;
				System.out.println("Mode: " + mode);
				comboBoxSelectedCaretaker.setVisible(false);
				comboBoxSelectedTask.setVisible(true);
				btnGenerateReport.setVisible(false);
				lblCaretakerDropdown.setVisible(false);
				lblTaskDropdown.setVisible(true);
				comboBoxSelectedTask.setSelectedIndex(0);
				lblReportingTitle.setText("Reporting - Task History");
				textPaneReport.setText("");
			}
		});

		btnCurrentTasksReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Current tasks report button");
				int mode = 1;
				System.out.println("Mode: " + mode);
				comboBoxSelectedCaretaker.setVisible(false);
				comboBoxSelectedTask.setVisible(false);
				btnGenerateReport.setVisible(true);
				lblCaretakerDropdown.setVisible(false);
				lblTaskDropdown.setVisible(false);
				lblReportingTitle.setText("Reporting - Current Tasks");
				textPaneReport.setText("");
			}
		});
		
		btnCaretakerReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Caretaker report button");
				int mode = 0;
				System.out.println("Mode: " + mode);
				comboBoxSelectedCaretaker.setVisible(true);
				comboBoxSelectedTask.setVisible(false);
				btnGenerateReport.setVisible(false);
				lblCaretakerDropdown.setVisible(true);
				lblTaskDropdown.setVisible(false);
				comboBoxSelectedCaretaker.setSelectedIndex(0);
				lblReportingTitle.setText("Reporting - Caretaker History");
				textPaneReport.setText("");
			}
		});
		
		
		//Generate Report button
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Report text is reset.
				String reportText = "";
				int mode = 0;
				//Checks to see what report "mode" is selected.
				//If the caretaker dropdown box is visible, then it is specific caretaker reports. (mode 0)
				//If the task dropdown box is visible, then it is the historic task reports (mode 2)
				//Otherwise, it can only be the current tasks report (mode 1)
				if (lblCaretakerDropdown.isVisible())
					mode = 0;
				else if (lblTaskDropdown.isVisible())
					mode = 2;
				else
					mode = 1;
				
				//Depending on if it is mode 0, 1, or 2, the generate report button will do different things.
				switch (mode)
				{
				case 0:
					//Individual caretaker reports
					System.out.println("Caretaker Report");
					//Checks to see the currently assigned tasks, so has to loop through.
					reportText += "Caretaker Report \n";
					reportText += "Selected caretaker: " + comboBoxSelectedCaretaker.getSelectedItem() + "\n \n";
					reportText += "Currently assigned tasks: " + "\n";
					reportText += "One-off tasks: \n";
					String currentTaskEntry = "";
					for (int i = 0 ; i < dbClass.getAllTasks().size() ; i++)
					{
						currentTaskEntry = "";
						CaretakerTask currentTask = dbClass.getAllTasks().get(i);
						if (currentTask.getTeamMembers().contains(comboBoxSelectedCaretaker.getSelectedItem()) && (currentTask.getDateCompleted() == null || currentTask.getDateCompleted().equals("")))
						{
							currentTaskEntry = "Task: " + currentTask.getID() + "\n";
						}
						reportText += currentTaskEntry;
					}
					//Repeat tasks
					reportText += "\n" + "Repeating tasks: \n";
					for (int i = 0 ; i < dbClass.getAllTasks().size() ; i++)
					{
						currentTaskEntry = "";
						CaretakerTask currentTask = dbClass.getAllTasks().get(i);
						if (currentTask.getTeamMembers().contains(comboBoxSelectedCaretaker.getSelectedItem()) && (currentTask.getDaysUntilRepeat() != 0))
						{
							currentTaskEntry = "Task: " + currentTask.getID() + "\n";
						}
						reportText += currentTaskEntry;
					}
					
					//Task completion log
					//Will show every time a task has been completed, regardless of it if was a one off task, or a repeated task.
					reportText += "\n" + "Previously completed tasks: " + "\n";
					for (int i = 0 ; i < dbClass.getAllCompletedTasks().size() ; i++)
					{
						currentTaskEntry ="";
						CompletedTask currentTask = dbClass.getAllCompletedTasks().get(i);
						if (currentTask.getUserID() == (int) comboBoxSelectedCaretaker.getSelectedItem())
						{
							currentTaskEntry = "Task " + currentTask.getTaskID() + ", Completed on: " + currentTask.getDateCompleted() + "\n";
						}
						reportText += currentTaskEntry;
					}
					break;
				case 1:
					//Report system for current tasks.
					System.out.println("Current Tasks");
					String currentReportEntry = "";
					reportText += "Current Tasks: \n \n";
					reportText += "One off tasks: "  + "\n";
					//Loops through all tasks, checks to see if the current task is a one-off, which hasn't been completed.
					for (int i = 0 ; i < dbClass.getAllTasks().size() ; i++)
					{
						currentReportEntry = "";
						CaretakerTask currentTask = dbClass.getAllTasks().get(i);
						if (currentTask.getDateCompleted() == null && currentTask.getDaysUntilRepeat() == 0)
						{
							String teamMembers = new String();
							
							for (int j = 0 ; j < currentTask.getTeamMembers().size() ; j++)
							{
								Caretaker currentCaretaker  = dbClass.getAllCaretakers().get(j);
								teamMembers += currentCaretaker.getFullName() + "(ID: " + currentCaretaker.getID() + ")";
							}
							currentReportEntry = "Task: " + currentTask.getID() + ". This task is currently assigned to: " + teamMembers + ".\n" + "                This task is due: " + currentTask.getDateDue();
							if (false) // Date comparison
								currentReportEntry += ". This task is overdue. ";
							currentReportEntry += "\n";
						}
						reportText += currentReportEntry;
					}
					reportText += currentReportEntry = "\n" + "Repeated tasks: " + "\n";
					//Then loops through all tasks again, checking to see if it is a repeated task.
					for (int i = 0 ; i < dbClass.getAllTasks().size() ; i++)
					{
						currentReportEntry = "";
						CaretakerTask currentTask = dbClass.getAllTasks().get(i);
						if (currentTask.getDaysUntilRepeat() != 0)
						{
							String lastCompleted = new String();
							if (currentTask.getDateCompleted() == null || currentTask.getDateCompleted().equals(""))
							{
								lastCompleted = " - This task has not been completed before.";
							}
							else
								lastCompleted = " - This task was last completed: " + currentTask.getDateCompleted();
							currentReportEntry = "Task: " + currentTask.getID() + lastCompleted +"\n";
						}
						reportText += currentReportEntry;
					}
					//If it is a task that was a one-off, which has been completed, it won't show up here.
					break;
				case 2:
					//Goes through all completed tasks, checking if task ID is equal to the one selected.
					System.out.println("Historic Task Report");
					
					reportText += "History of task " + comboBoxSelectedTask.getSelectedItem() + "\n";
					reportText += "Task details: \n";
					
					CaretakerTask currentTask = dbClass.getAllTasks().get((int) comboBoxSelectedTask.getSelectedItem());
					
					reportText += "Task repeats once every " + currentTask.getDaysUntilRepeat() + " days. \n";
					//What if the task was a one-off?
					
					reportText += "\n" + "Completion history: \n";
					for (int i = 0 ; i < dbClass.getAllCompletedTasks().size() ; i++)
					{
						CompletedTask currentRepeatedTask = dbClass.getAllCompletedTasks().get(i);
						currentReportEntry = "";
						if (currentRepeatedTask.getTaskID() == (int) comboBoxSelectedTask.getSelectedItem())
						{
							currentReportEntry = "Task: " + currentRepeatedTask.getTaskID() + " was completed on: " + currentRepeatedTask.getDateCompleted() + "\n";
						}
						
						reportText += currentReportEntry;
					}
					break;
				default:
					System.out.println("Something went wrong, please try again.");
					reportText = "Something went wrong, please try again.";
					break;
				}
				
				System.out.println(reportText);
				//Report text is presented to the user
				textPaneReport.setText(reportText);
			}
		});
		
		//Drop down boxes event listeners for report selection.
		//Caretaker
		comboBoxSelectedCaretaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Is the caretaker chosen a valid option? Make the generate report button visible
				System.out.println("Interacted with caretaker");
				if (comboBoxSelectedCaretaker.getSelectedIndex() != 0)
					btnGenerateReport.setVisible(true);
			}
		});
		//Tasks
		comboBoxSelectedTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Is the task chosen a valid option? Make the generate report button visible
				System.out.println("Interacted with task");
				if (comboBoxSelectedTask.getSelectedIndex() != 0)
					btnGenerateReport.setVisible(true);
			}
		});
		}
	}

	public int getLoggedInId() {
		return loggedInId;
	}

	public void setLoggedInId(int loggedInId) {
		this.loggedInId = loggedInId;
	}
}
