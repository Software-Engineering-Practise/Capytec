import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class CapytecGui extends JFrame {

	private JPanel contentPane;
	private JTable tableUserManagement;
	private JTable tableTaskManagement;
	CapyTecDB dbClass = new CapyTecDB();
	private JTable tableTaskLogging;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CapytecGui frame = new CapytecGui();
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
	public CapytecGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1127, 984);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelUserManagement = new JPanel();
		tabbedPane.addTab("User Management", null, panelUserManagement, null);
		panelUserManagement.setLayout(new BorderLayout(0, 0));
		
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
		
		JButton btnEditUser = new JButton("Edit User");
		panelTaskBottomButtons.add(btnEditUser);
		
		JButton btnRemoveUser = new JButton("Remove User");
		panelTaskBottomButtons.add(btnRemoveUser);
		
		JPanel panelUserTitle = new JPanel();
		panelUserManagement.add(panelUserTitle, BorderLayout.NORTH);
		
		JLabel lblUserManagement = new JLabel("User Management");
		lblUserManagement.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelUserTitle.add(lblUserManagement);
		
		JScrollPane scrollPaneUserManagement = new JScrollPane();
		panelUserManagement.add(scrollPaneUserManagement, BorderLayout.CENTER);
		
		tableUserManagement = new JTable();
		
		System.out.println(dbClass.getAllCaretakers().get(1).getFullName());
		tableUserManagement.setModel(new DefaultTableModel(
			new Object[][] {
				{},
			},
			new String[] {
				"User", "Position", "Skills"
			}
		));
		
		DefaultTableModel tableModelUserManagement = (DefaultTableModel)tableUserManagement.getModel();
		
		for(int i=0; i<dbClass.getAllCaretakers().size(); i++) {
			tableModelUserManagement.addRow(new Object[] {dbClass.getAllCaretakers().get(i).getFullName(),dbClass.getAllCaretakers().get(i).getJobTitle(),"Skill"});
		}
		for(int i=0; i<dbClass.getAllManagers().size(); i++) {
			tableModelUserManagement.addRow(new Object[] {dbClass.getAllManagers().get(i).getFullName(),dbClass.getAllManagers().get(i).getJobTitle(),"Skill"});
		}
		
		tableUserManagement.getColumnModel().getColumn(0).setPreferredWidth(351);
		tableUserManagement.getColumnModel().getColumn(1).setPreferredWidth(197);
		scrollPaneUserManagement.setViewportView(tableUserManagement);
		
		
		
		
		
		
		JPanel panelTaskManagement = new JPanel();
		tabbedPane.addTab("Task Management", null, panelTaskManagement, null);
		panelTaskManagement.setLayout(new BorderLayout(0, 0));
		
		JPanel panelUserBottomButtons = new JPanel();
		panelTaskManagement.add(panelUserBottomButtons, BorderLayout.SOUTH);
		
		JButton btnAddTask = new JButton("Add Task");
		panelUserBottomButtons.add(btnAddTask);
		
		JButton btnAssignTask = new JButton("Assign Task");
		panelUserBottomButtons.add(btnAssignTask);
		
		JButton btnRemoveTask = new JButton("Remove Task");
		panelUserBottomButtons.add(btnRemoveTask);
		
		JPanel panelTaskTitle = new JPanel();
		panelTaskManagement.add(panelTaskTitle, BorderLayout.NORTH);
		
		JLabel lblTaskManagement = new JLabel("Task Management");
		lblTaskManagement.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTaskTitle.add(lblTaskManagement);
		
		JScrollPane scrollPaneTaskManagement = new JScrollPane();
		panelTaskManagement.add(scrollPaneTaskManagement, BorderLayout.CENTER);
		
		tableTaskManagement = new JTable();
		tableTaskManagement.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Task No.", "Task", "Assigned Member", "Description", "Type", "Start Date", "Due Date", "Completion Date", "Completionist", "Importance", "Frequency"
			}
		));
		tableTaskManagement.getColumnModel().getColumn(1).setPreferredWidth(270);
		tableTaskManagement.getColumnModel().getColumn(2).setPreferredWidth(173);
		tableTaskManagement.getColumnModel().getColumn(3).setPreferredWidth(342);
		tableTaskManagement.getColumnModel().getColumn(4).setPreferredWidth(124);
		tableTaskManagement.getColumnModel().getColumn(5).setPreferredWidth(132);
		tableTaskManagement.getColumnModel().getColumn(6).setPreferredWidth(120);
		tableTaskManagement.getColumnModel().getColumn(7).setPreferredWidth(143);
		tableTaskManagement.getColumnModel().getColumn(8).setPreferredWidth(130);
		tableTaskManagement.getColumnModel().getColumn(9).setPreferredWidth(118);
		tableTaskManagement.getColumnModel().getColumn(10).setPreferredWidth(117);
		
		DefaultTableModel tableModelTaskManagement = (DefaultTableModel)tableTaskManagement.getModel();
		
		for(int i=0; i<dbClass.getAllCaretakers().size(); i++) {
			CaretakerTask currentItem = dbClass.GetAllTasks().get(i); 
			tableModelTaskManagement.addRow(new Object[] {currentItem.getID(),currentItem.getTitle(),"Assigned Member",currentItem.getDesc(),"Type",currentItem.getDateCreated(),currentItem.getDateDue(),currentItem.getDateCompleted(),currentItem.getCompletionist(),currentItem.getPriority(),"Repeat/Oneoff"});
		}
		scrollPaneTaskManagement.setViewportView(tableTaskManagement);
		
		JPanel panelTaskLogging = new JPanel();
		tabbedPane.addTab("Task Logging", null, panelTaskLogging, null);
		panelTaskLogging.setLayout(new BorderLayout(0, 0));
		
		JPanel panelLoggingTitle = new JPanel();
		panelTaskLogging.add(panelLoggingTitle, BorderLayout.NORTH);
		
		JLabel lblTaskLogging = new JLabel("Task Logging");
		panelLoggingTitle.add(lblTaskLogging);
		
		JPanel panelTaskLoggingButtons = new JPanel();
		panelTaskLogging.add(panelTaskLoggingButtons, BorderLayout.SOUTH);
		
		JScrollPane scrollPaneTaskLogging = new JScrollPane();
		panelTaskLogging.add(scrollPaneTaskLogging, BorderLayout.CENTER);
		
		tableTaskLogging = new JTable();
		tableTaskLogging.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"User", "Task ID", "Task",  "Task Set", "Repeated Task?", "Repeat Period", "Completed Date"
			}
		));
		
		DefaultTableModel tableModelTaskLogging = (DefaultTableModel)tableTaskLogging.getModel();
		
		for (int i = 0 ; i < dbClass.GetAllTasks().size() ; i++)
		{
			CaretakerTask currentItem = dbClass.GetAllTasks().get(i);
			int repeat = currentItem.getDaysUntilRepeat();
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
			if (currentItem.getDateCompleted() == null || isRepeated == "Yes")
				tableModelTaskLogging.addRow(new Object[] {currentItem.getID(), currentItem.getID(), currentItem.getTitle(), currentItem.getDateCreated(), isRepeated, daysRepeat, currentItem.getDateCompleted()});
		}
		
		scrollPaneTaskLogging.setViewportView(tableTaskLogging);
		
		JPanel panelReporting = new JPanel();
		tabbedPane.addTab("Reporting", null, panelReporting, null);
		
		
	}

}
