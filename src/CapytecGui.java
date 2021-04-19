import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Font;

public class CapytecGui extends JFrame {

	private JPanel contentPane;
	private JTable tableUserManagement;
	private JTable tableTaskManagement;
	CapyTecDB dbClass = new CapyTecDB();

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
				"User", "Position", "Talents"
			}
		));
		
		DefaultTableModel tableModel = (DefaultTableModel)tableUserManagement.getModel();
		
		for(int i=0; i<dbClass.getAllCaretakers().size(); i++) {
			tableModel.addRow(new Object[] {dbClass.getAllCaretakers().get(i).getFullName(),dbClass.getAllCaretakers().get(i).getJobTitle(),"Talent"});
		}
		for(int i=0; i<dbClass.getAllManagers().size(); i++) {
			tableModel.addRow(new Object[] {dbClass.getAllManagers().get(i).getFullName(),dbClass.getAllManagers().get(i).getJobTitle(),"Talent"});
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
				"Task No.", "Task", "Assigned Member", "Type", "Duration", "Importance", "Frequency"
			}
		));
		tableTaskManagement.getColumnModel().getColumn(1).setPreferredWidth(270);
		tableTaskManagement.getColumnModel().getColumn(2).setPreferredWidth(173);
		tableTaskManagement.getColumnModel().getColumn(3).setPreferredWidth(124);
		tableTaskManagement.getColumnModel().getColumn(4).setPreferredWidth(120);
		tableTaskManagement.getColumnModel().getColumn(5).setPreferredWidth(118);
		tableTaskManagement.getColumnModel().getColumn(6).setPreferredWidth(117);
		scrollPaneTaskManagement.setViewportView(tableTaskManagement);
	}

}
