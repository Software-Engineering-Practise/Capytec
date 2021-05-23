import java.util.concurrent.TimeUnit;

public class CapytecMain {

	public static void main(String[] args) {
		
		boolean useLogin = false;
		boolean noLoginManager = false;
		boolean isManager;
		
		if (useLogin) {
			
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
			
			isManager = loginGui.checkUser(loginGui.getLoggedInId());
			
			CapytecGui mainGui = new CapytecGui(isManager, true);
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
		else
		{
			isManager = noLoginManager;
			CapytecGui mainGui = new CapytecGui(isManager, useLogin);
			mainGui.setLoggedInId(5);
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

}
