import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			
			ResultSet resultList = database.RunSQLQuery(sqlString);
			if(resultList != null) {
				
				while(resultList.next()) {
					
					Caretaker newCaretaker = new Caretaker();
					newCaretaker.setID(resultList.getInt(1));
					newCaretaker.setFirstName(resultList.getString(2));
					newCaretaker.setLastName(resultList.getString(3));
					newCaretaker.setJobTitle(resultList.getString(4));
					
					caretakers.add(newCaretaker);
				}
				
				//Query Skills for each Care taker with corresponding ID
				sqlString = "SELECT user_id, skill_name " + 
						"FROM skill JOIN user_skill ON skill.skill_id = user_skill.skill " + 
						"JOIN user ON user_skill.user = user.user_id WHERE job_type = 3;";
				
				resultList = database.RunSQLQuery(sqlString);
				
				
				while(resultList.next()) {//Loop query result rows
					for(int i = 0 ; i < caretakers.size() ; i++) {//Loop gathered care takers from before for ID comparison 
						if(caretakers.get(i).getID() == resultList.getInt(1)) {//If the current care taker matches the ID of the query result row then add the skill of that row to the care taker skill ArrayList
							caretakers.get(i).getSkills().add(resultList.getString(2));
						}
					}
				}
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
		
			String sqlString = new String("SELECT task_id, task_title, task_desc, need_signing, need_peer_check, date_created, date_due, date_updated, date_completed, priority, days_till_repeat, "
					+ "a.first_name || ' ' || a.last_name AS CreatorName, "
					+ "b.first_name || ' ' || b.last_name AS CompletionistName, "
					+ "c.first_name || ' ' || c.last_name AS SignedBy, "
					+ "d.first_name || ' ' || d.last_name AS PeerChecker "
					+ "FROM task AS t LEFT JOIN user AS a ON t.created_by = a.user_id  LEFT JOIN user AS b ON t.completed_by = b.user_id LEFT JOIN user AS c ON t.signed_by = c.user_id LEFT JOIN user AS d ON t.peer_checked_by = d.user_id;");
													
			ResultSet taskResultSet = database.RunSQLQuery(sqlString);
			
			
			while(taskResultSet.next()){
				CaretakerTask newTask = new CaretakerTask();
				newTask.setID(taskResultSet.getInt(1));
				newTask.setTitle(taskResultSet.getString(2));
				newTask.setDesc(taskResultSet.getString(3));
				newTask.setPriority(taskResultSet.getInt(10));
				newTask.setDaysUntilRepeat(taskResultSet.getInt(11));
				newTask.setAuthor(taskResultSet.getString(12));
				newTask.setCompletionist(taskResultSet.getString(13));
				newTask.setSignee(taskResultSet.getString(14));
				newTask.setPeerChecker(taskResultSet.getString(15));
				
				//Get database integer flag and set to java boolean in Task
				if (taskResultSet.getInt(4) == 1) newTask.setNeedsSigning(true);
				if (taskResultSet.getInt(5) == 1) newTask.setNeedsPeerChecking(true);
				
				newTask.setDateCreated(taskResultSet.getString(6));
				newTask.setDateDue(taskResultSet.getString(7));
				newTask.setDateUpdated(taskResultSet.getString(8));
				newTask.setDateCompleted(taskResultSet.getString(9));
				
				tasks.add(newTask);
			} 
			
			sqlString = "SELECT task_id, skill_name " + 
					"FROM skill JOIN task_skill ON skill.skill_id = task_skill.skill " + 
					"JOIN task ON task_skill.task = task.task_id;";
			
			taskResultSet = database.RunSQLQuery(sqlString);
			
			while(taskResultSet.next()) {//Loop query result rows
				for(int i = 0 ; i < tasks.size() ; i++) {//Loop gathered tasks from before for ID comparison 
					if(tasks.get(i).getID() == taskResultSet.getInt(1)) {//If the current task matches the ID of the query result row then add the skill of that row to the care taker skill ArrayList
						tasks.get(i).getRecSkills().add(taskResultSet.getString(2));
					}
				}
			}
			
			sqlString = "SELECT * FROM team;";
			taskResultSet = database.RunSQLQuery(sqlString);
			
			while(taskResultSet.next()) {
				for(int i = 0 ; i < tasks.size() ; i++ ) {
					if(tasks.get(i).getID() == taskResultSet.getInt(1)) {
						tasks.get(i).getTeamMembers().add(taskResultSet.getInt(2));
					}
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tasks;
	}
	
	public ArrayList<String> getAllSkills() {
		
		ArrayList<String> skills = new ArrayList<String>();
		
		try {
			
			String sql = "SELECT skill_name FROM skill;";
			
			ResultSet skillResultSet = database.RunSQLQuery(sql);
			
			while(skillResultSet.next()) {
				
				skills.add(skillResultSet.getString(1));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return skills;
	}
	
	//INSERT FUNCTIONS
	public void addCaretaker(Caretaker caretaker) {
		
		String fname = caretaker.getFirstName();
		String lname = caretaker.getLastName();
		
		String sql = new String("INSERT INTO user (first_name, last_name, job_type) VALUES ('"+fname+"', '"+lname+"', 3);");
		
		boolean success = database.RunSQL(sql);
		
		if(!success) {
			System.out.println("Failed to run query: "+sql);
		}
		
	}
	
	//DELETE FUNCTIONS
	public void DeleteUser(String userFName, String userLName) {
		
		String sqlString = new String("DELETE FROM user WHERE first_name = "+userFName+" AND last_name = "+userLName+";");
		
		boolean success = database.RunSQL(sqlString);
		
		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
	}
}