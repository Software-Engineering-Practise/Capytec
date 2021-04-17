import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;

public class CapyTecDB{
	
	private DBConnection database;
	
	public CapyTecDB() {
		database = new DBConnection();
		database.Connect("CapyTec.db"); // This is bad. Put in new function
	}
	
	//SELECT FUNCTIONS
	public ArrayList<Caretaker> getAllCaretakers(){
		
		ArrayList<Caretaker> caretakers = new ArrayList<Caretaker>();
		
		try {
			String sqlString = new String("SELECT user_id, first_name, last_name, job_title FROM user JOIN job ON user.job_type = job.job_id WHERE job_title = 'Caretaker';");
			
			ResultSet caretakerList = database.RunSQLQuery(sqlString);
	
			
			while(caretakerList.next()) {
				
				Caretaker newCaretaker = new Caretaker();
				newCaretaker.setID(caretakerList.getInt(1));
				newCaretaker.setFirstName(caretakerList.getString(2));
				newCaretaker.setLastName(caretakerList.getString(3));
				newCaretaker.setJobTitle(caretakerList.getString(4));
				
				caretakers.add(newCaretaker);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
		return caretakers;
	}
	
	public ArrayList<Manager> getAllManagers(){
		
		ArrayList<Manager> managers = new ArrayList<Manager>();
		
		try {
			String sqlString = new String("SELECT user_id, first_name, last_name, job_title FROM user JOIN job ON user.job_type = job.job_id WHERE job_title = 'Manager';");
			
			ResultSet managerList = database.RunSQLQuery(sqlString);
	
			
			while(managerList.next()) {
				
				Manager newManager = new Manager();
				newManager.setID(managerList.getInt(1));
				newManager.setFirstName(managerList.getString(2));
				newManager.setLastName(managerList.getString(3));
				newManager.setJobTitle(managerList.getString(4));
				
				managers.add(newManager);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
		return managers;
	}
	
	
	public ArrayList<CaretakerTask> GetAllTasks(){
		
		ArrayList<CaretakerTask> tasks = new ArrayList<CaretakerTask>();
		
		try {
		
			String sqlString = new String("SELECT task_id, task_title, task_desc, need_signing, need_peer_check, date_created, date_due, date_updated, date_completed, priority, "
					+ "a.first_name || ' ' || a.last_name AS CreatorName, "
					+ "b.first_name || ' ' || b.last_name AS CompletionistName, "
					+ "c.first_name || ' ' || c.last_name AS SignedBy, "
					+ "d.first_name || ' ' || d.last_name AS PeerChecker "
					+ "FROM task AS t JOIN user AS a ON t.created_by = a.user_id JOIN user AS b ON t.completed_by = b.user_id JOIN user AS c ON t.signed_by = c.user_id JOIN user AS d ON t.peer_checked_by = d.user_id;");
													
			ResultSet taskList = database.RunSQLQuery(sqlString);
			
				
			while(taskList.next()){
				CaretakerTask newTask = new CaretakerTask();
				newTask.setID(taskList.getInt(1));
				newTask.setTitle(taskList.getString(2));
				newTask.setDesc(taskList.getString(3));
				newTask.setPriority(taskList.getInt(10));
				newTask.setAuthor(taskList.getString(11));
				newTask.setCompletionist(taskList.getString(12));
				newTask.setSignee(taskList.getString(13));
				newTask.setPeerChecker(taskList.getString(14));
				
				//Get database integer flag and set to java boolean in Task
				if (taskList.getInt(4) == 1) newTask.setNeedsSigning(true);
				//System.out.println(newTask.isNeedsSigning());
				
				if (taskList.getInt(5) == 1) newTask.setNeedsPeerChecking(true);
				
				//Object to format database TEXT dates to java Date
				SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(dateFormatter.parse("2001-03-13 12:20:42"));
				
				//Get database date in String then parse to Date 
				newTask.setDateCreated(dateFormatter.parse(taskList.getString(6)));
				newTask.setDateDue(dateFormatter.parse(taskList.getString(7)));
				newTask.setDateUpdated(dateFormatter.parse(taskList.getString(8)));
				newTask.setDateCompleted(dateFormatter.parse(taskList.getString(9)));
				
				
				tasks.add(newTask);
			} 
		
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		return tasks;
	}
	
	
	//INSERT FUNCTIONS
	
	//UPDATE FUNCTIONS
	
	//DELETE FUNCTIONS
	public void DeleteUser(String userFName, String userLName) {
		
		String sqlString = new String("DELETE FROM user WHERE first_name = "+userFName+" AND last_name = "+userLName+";");
		
		boolean success = database.RunSQL(sqlString);
		
		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
	}
}