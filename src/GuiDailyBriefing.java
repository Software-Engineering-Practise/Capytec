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
					//Create daily briefing frame and make it visible
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
		//Set frame to close independently on exit rather than close whole application
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Set frame dimensions
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Create panel for title and add title label
		JPanel panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		JLabel lblDailyBriefing = new JLabel("Daily Briefing");
		panelTitle.add(lblDailyBriefing);
		
		//Textpane storing the text for the daily briefing
		JTextPane txtpnBriefingForIdnumber = new JTextPane();
		
		//Create caretaker object for currently logged in user
		Caretaker loggedInCaretaker = new Caretaker();
		
		//Check all caretakers to find logged in caretaker, and set loggedInCaretaker variable to match
		for(int i=0; i<dbClass.getAllCaretakers().size(); i++) {
			if(dbClass.getAllCaretakers().get(i).getID() == loggedInUser) {
				 loggedInCaretaker = dbClass.getAllCaretakers().get(i);
			}
		}
		
		//Set name and tasks to variables for current caretaker to be displayed in daily briefing output
		String currentName = loggedInCaretaker.getFullName();
		String currentTasks = "";
		//Append tasks assigned to current caretaker to the currentTasks string
		for(int i=0; i<dbClass.getAllTasks().size(); i++) {
			CaretakerTask currentTask = dbClass.getAllTasks().get(i);
			ArrayList<Integer> assignedMembers = currentTask.getTeamMembers(); 
			for(int x=0; x<assignedMembers.size();x++) {
				if(assignedMembers.get(x) == loggedInUser) {
					//Get information about current tasks for the logged in caretaker
					currentTasks += currentTask.getTitle() + "\nDescription: " + currentTask.getDesc() + "\nDue: " + currentTask.getDateDue() + "\r\n\n ";
				}
			}
		}

		//Output text to txtpnBriefingForIdnumber containing daily briefing information
		txtpnBriefingForIdnumber.setText("Briefing for " + currentName + ", ID: " + loggedInUser + "\r\n\n"
				+ "Tasks:\r\n"
				+ currentTasks);
			
		contentPane.add(txtpnBriefingForIdnumber, BorderLayout.CENTER);
	}

}
