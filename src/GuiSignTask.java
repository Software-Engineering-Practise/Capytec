import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GuiSignTask extends JFrame {

	private JPanel contentPane;
	CapyTecDB dbClass = new CapyTecDB();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiSignTask frame = new GuiSignTask(1);
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
	public GuiSignTask(int passedLoggedIn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSignTaskTitle = new JLabel("Sign Task");
		lblSignTaskTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignTaskTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSignTaskTitle.setBounds(86, 11, 260, 42);
		contentPane.add(lblSignTaskTitle);
		
		JLabel lblSelectedTaskLabel = new JLabel("Task ID");
		lblSelectedTaskLabel.setBounds(114, 64, 46, 14);
		contentPane.add(lblSelectedTaskLabel);
		
		int userLoggedIn = passedLoggedIn;
		
		JComboBox dropdownTaskID = new JComboBox();
		
		dropdownTaskID.setModel(new DefaultComboBoxModel());
		dropdownTaskID.addItem("Select a Task ID");
		for (int i = 0 ; i < dbClass.getAllTasks().size() ; i++) {
			CaretakerTask currentTask = dbClass.getAllTasks().get(i);
			//if (currentTask.isNeedsSigning() && !currentTask.getTeamMembers().contains(userLoggedIn))
			if (currentTask.isNeedsSigning() && (currentTask.getSignee() == null))
			{
				dropdownTaskID.addItem(currentTask.getID());
			}
		}
		dropdownTaskID.setBounds(208, 60, 138, 22);
		contentPane.add(dropdownTaskID);
		
		JButton btnSignTask = new JButton("Set Task as Signed");
		btnSignTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Button pressed. Task " + (dropdownTaskID.getSelectedItem()) + ".");
				
				for (int i = 0 ; i < dbClass.getAllTasks().size() ; i++)
				{
					CaretakerTask currentTask = dbClass.getAllTasks().get(i);
					if (currentTask.getID() == (int) dropdownTaskID.getSelectedItem())
					{
						CaretakerTask taskToUpdate = currentTask;
						taskToUpdate.setSigneeID(userLoggedIn);
						String signeeName = "";
						int signeeID = 0;
						for (int j = 0 ; j < dbClass.getAllManagers().size() ; j++)
						{
							Manager currentManager = dbClass.getAllManagers().get(j);
							if (currentManager.getID() == userLoggedIn)
							{
								signeeName = currentManager.getFullName();
								signeeID = currentManager.getID();
							}
						}
						
						if (signeeName == "")
						{
							System.out.println("An error occured, invalid signee name. Please try again");
						}
						else
						{
							taskToUpdate.setSignee(signeeName);
							taskToUpdate.setSigneeID(signeeID);
							System.out.println("New signee is " + signeeName);
							taskToUpdate.setDateCompleted("");
							dbClass.updateCaretakerTask(taskToUpdate);
						}
					}
				}
			}
		});
		btnSignTask.setEnabled(false);
		btnSignTask.setBounds(96, 170, 250, 42);
		contentPane.add(btnSignTask);
		
		dropdownTaskID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isManager = false;
				for (int i = 0 ; i < dbClass.getAllManagers().size() ; i++)
				{
					Manager currentManager = dbClass.getAllManagers().get(i);
					if (currentManager.getID() == userLoggedIn)
						isManager = true;
				}
				if (!isManager)
					{
						btnSignTask.setEnabled(false);
						btnSignTask.setText("You do not have permission to sign off");
					}
				else if (dropdownTaskID.getSelectedIndex() != 0)
					btnSignTask.setEnabled(true);
				else
					btnSignTask.setEnabled(false);
			}
		});
		
	}

}
