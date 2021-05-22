import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class GuiDailyBriefing extends JFrame {

	private JPanel contentPane;
	CapyTecDB dbClass = new CapyTecDB();
	//PLACEHOLDER FOR LOGIN PAGE
	int loggedInUser = 8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiDailyBriefing frame = new GuiDailyBriefing();
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
	public GuiDailyBriefing() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblDailyBriefing = new JLabel("Daily Briefing");
		panel.add(lblDailyBriefing);
		
		JTextPane txtpnBriefingForIdnumber = new JTextPane();
		
		Caretaker loggedInCaretaker = new Caretaker();
		
		for(int i=0; i<dbClass.getAllCaretakers().size(); i++) {
			if(dbClass.getAllCaretakers().get(i).getID() == loggedInUser) {
				 loggedInCaretaker = dbClass.getAllCaretakers().get(i);
			}
		}
		
		String currentName = loggedInCaretaker.getFullName();
		String currentTasks = "";
		for(int i=0; i<dbClass.getAllTasks().size(); i++) {
			CaretakerTask currentTask = dbClass.getAllTasks().get(i);
			ArrayList<Integer> assignedMembers = currentTask.getTeamMembers(); 
			for(int x=0; x<assignedMembers.size();x++) {
				if(assignedMembers.get(x) == loggedInUser) {
					currentTasks += currentTask.getTitle() + "\nDue: " + currentTask.getDesc() + "\nDue: " + currentTask.getDateDue() + "\r\n\n ";
				}
			}
		}

		//txtpnBriefingForIdnumber.setText("Briefing for " + currentName + ", ID: " + loggedInUser + "\r\n"
		//		+ "Tasks:\r\n"
		//		+ "TaskOne, SKILLTYPE, DESRIPTION, DUE DATE\r\n"
		//		+ "TaskTwo, SKILLTYPE, DESRIPTION, DUE DATE");
		
		txtpnBriefingForIdnumber.setText("Briefing for " + currentName + ", ID: " + loggedInUser + "\r\n\n"
				+ "Tasks:\r\n"
				+ currentTasks);
			
		contentPane.add(txtpnBriefingForIdnumber, BorderLayout.CENTER);
	}

}
