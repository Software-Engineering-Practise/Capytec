import java.util.concurrent.TimeUnit;

public class CapytecMain {

	public static void main(String[] args) {
		
		GuiLogin loginGui = new GuiLogin();
		loginGui.setVisible(true);
		
		while(loginGui.isNotLoggedIn()) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("Not Logged In");
		}
		
		boolean isManager = loginGui.checkUser(loginGui.getLoggedInId());
		
		CapytecGui mainGui = new CapytecGui(isManager);
		mainGui.setLoggedInId(loginGui.getLoggedInId());
		mainGui.setVisible(true);
		
		
		if(isManager) {
			System.out.println("Manager Mode");
			mainGui.setVisible(true);
		}else {
			mainGui.setVisible(true);
			System.out.println("Caretaker Mode");
		}
		
	}

}
