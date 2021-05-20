import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class GuiDailyBriefing extends JFrame {

	private JPanel contentPane;
	CapyTecDB dbClass = new CapyTecDB();
	//FOR LOGIN PAGE
	int loggedInID = 1;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		txtpnBriefingForIdnumber.setText("Briefing for IDNUMBER + NAME\r\n"
				+ "Tasks:\r\n"
				+ "TaskOne, SKILLTYPE, DESRIPTION, DUE DATE\r\n"
				+ "TaskTwo, SKILLTYPE, DESRIPTION, DUE DATE");
			
		contentPane.add(txtpnBriefingForIdnumber, BorderLayout.CENTER);
	}

}
