import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CapyTecDB{
	
	private DBConnection database;
	
	public CapyTecDB() {
		connect();
	}
	
	private void connect() {
		database = new DBConnection();
		database.Connect("CapyTec.db"); 
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
	
	public ArrayList<CaretakerTask> getAllTasks(){
		
		ArrayList<CaretakerTask> tasks = new ArrayList<CaretakerTask>();
		
		try {
		
			String sqlString = new String("SELECT task_id, task_title, task_desc, need_signing, need_peer_check, date_created, date_due, date_updated, date_completed, priority, days_till_repeat, created_by, completed_by, signed_by, peer_checked_by, extra_considerations, "
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
				newTask.setAuthorID(taskResultSet.getInt(12));
				newTask.setCompletionistID(taskResultSet.getInt(13));
				newTask.setSigneeID(taskResultSet.getInt(14));
				newTask.setPeerCheckerID(taskResultSet.getInt(15));
				newTask.setExtraConsiderations(taskResultSet.getString(16));
				newTask.setAuthor(taskResultSet.getString(17));
				newTask.setCompletionist(taskResultSet.getString(18));
				newTask.setSignee(taskResultSet.getString(19));
				newTask.setPeerChecker(taskResultSet.getString(20));
				
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
	
	public ArrayList<CompletedTask> getAllCompletedTasks() {
		
		ArrayList<CompletedTask> compTasks = new ArrayList<CompletedTask>();
		
		try {
			String sql = "SELECT compTask_id, task, user, date FROM completed_task;";
			
			ResultSet completedTasksResultSet = database.RunSQLQuery(sql);
			
			while(completedTasksResultSet.next()) {
					
				CompletedTask newCompTask = new CompletedTask();
				
				newCompTask.setId(completedTasksResultSet.getInt(1));
				newCompTask.setTaskID(completedTasksResultSet.getInt(2));
				newCompTask.setUserID(completedTasksResultSet.getInt(3));
				newCompTask.setDateCompleted(completedTasksResultSet.getString(4));
				compTasks.add(newCompTask);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compTasks;
	}
	//INSERT FUNCTIONS
	public void addCaretaker(Caretaker caretaker) {
			
		String fname = caretaker.getFirstName();
		fname = fname.replaceAll("[^\\x00-\\x7F]", "");
		fname = fname.replaceAll("[';']", "");
		
		String lname = caretaker.getLastName();
		lname = lname.replaceAll("[^\\x00-\\x7F]", "");
		lname = lname.replaceAll("[';']", "");
		
		String sql = new String("INSERT INTO user (first_name, last_name, job_type) VALUES ("+fname+", "+lname+", 3);");
		
		boolean success = database.RunSQL(sql);
		
		if(!success) {
			System.out.println("Failed to run query: "+sql);
		}
		
		sql = "SELECT skill_id, skill_name FROM skill;";
		
		if(caretaker.getSkills().size() != 0) {
			try {
				ResultSet sqlResult = database.RunSQLQuery(sql);
				
				ArrayList<Integer> skills = new ArrayList<Integer>();
				
				while(sqlResult.next()) {
					int id = 1;
					for(int i = 0 ; i < caretaker.getSkills().size() ; i++) {
						if(caretaker.getSkills().get(i) == sqlResult.getString(2)) {
							skills.add(id);
						}
					}
					id++;
				}
				
				sql = "INSERT INTO user_skill (user, skill) VALUES";
				
				for(int i = 0 ; i < skills.size() ; i++) {
					sql = sql + " (" + caretaker.getID() + ", " + skills.get(i) + ")";
					if(i < skills.size()) sql = sql + ",";
				}
				
				success = database.RunSQL(sql);
				
				if(!success) {
					System.out.println("Failed to run query: "+sql);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addCaretakerTask(CaretakerTask caretakerTask) {
		
		int dbTaskID;
		
		String title = caretakerTask.getTitle();
		title = title.replaceAll("[^\\x00-\\x7F]", "");
		title = title.replaceAll("[';']", "");
		
		String desc = caretakerTask.getDesc();
		desc = desc.replaceAll("[^\\x00-\\x7F]", "");
		desc = desc.replaceAll("[';']", "");
		
		String exConsider = caretakerTask.getExtraConsiderations();
		exConsider = exConsider.replaceAll("[^\\x00-\\x7F]", "");
		exConsider = exConsider.replaceAll("[';']", "");
		
		String dateCreated = caretakerTask.getDateCreated();
		dateCreated = dateCreated.replaceAll("[^\\x00-\\x7F]", "");
		dateCreated = dateCreated.replaceAll("[';']", "");
		
		String dateDue = caretakerTask.getDateDue();
		dateCreated = dateCreated.replaceAll("[^\\x00-\\x7F]", "");
		dateCreated = dateCreated.replaceAll("[';']", "");
		
		int priority = caretakerTask.getPriority();
		int daysUntilRepeat = caretakerTask.getDaysUntilRepeat();
		int authorID = caretakerTask.getAuthorID();
		int needsSigning = caretakerTask.isNeedsSigning() ? 1 : 0;
		int needsPeerChecking = caretakerTask.isNeedsPeerChecking() ? 1 : 0;
		
		
		String sql = "INSERT INTO task (task_title, task_desc, extra_considerations, need_signing, need_peer_check, date_created, date_due, priority, created_by, days_till_repeat) "
				+ "VALUES ('"+title+"', '"+desc+"', '"+exConsider+"', "+needsSigning+", "+needsPeerChecking+", '"+dateCreated+"', '"+dateDue+"', "+priority+", "+authorID+", "+daysUntilRepeat+");";
		
		boolean success = database.RunSQL(sql);
		
		
		if(!success) {
			System.out.println("Failed to run query: "+sql);
		}
		
		sql = "SELECT skill_id, skill_name FROM skill;";
		
		if(caretakerTask.getRecSkills().size() != 0) {
			try {
				ResultSet sqlResult = database.RunSQLQuery(sql);
				
				sql = "SELECT last_insert_rowid();";
				
				dbTaskID = database.RunSQLQuery(sql).getInt(1);
				
				ArrayList<Integer> skills = new ArrayList<Integer>();
				
				while(sqlResult.next()) {
					int id = 1;
					for(int i = 0 ; i < caretakerTask.getRecSkills().size() ; i++) {
						if(caretakerTask.getRecSkills().get(i).contentEquals(sqlResult.getString(2))) {
							skills.add(id);
						}
					}
					id++;
				}
				
				for(int i = 0 ; i < skills.size() ; i++) {
					sql = "INSERT INTO task_skill (task, skill) VALUES ("+dbTaskID+", "+skills.get(i)+");";
					
					success = database.RunSQL(sql);
					
					if(!success) {
						System.out.println("Failed to run query: "+sql);
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public void addCompletedTask(CompletedTask completedTask) {
		
		int taskID = completedTask.getTaskID();
		int userID = completedTask.getUserID();
		String dateCompleted = completedTask.getDateCompleted();
		
		String sql = "INSERT INTO completed_task (task, user, date) VALUES ("+taskID+", "+userID+", '"+dateCompleted+"');";
		
		boolean success = database.RunSQL(sql);
		
		if(!success) {
			System.out.println("Failed to run query: "+sql);
		}
	}
	//UPDATE FUNCITONS
	
	public void updateCaretaker(Caretaker caretaker) {
		
		int id = caretaker.getID();
		String fname = caretaker.getFirstName();
		String lname = caretaker.getLastName();
		
		String sql = "UPDATE user SET first_name = '"+fname+"', last_name = '"+lname+"' WHERE user_id = "+id+";";
		
		boolean success = database.RunSQL(sql);
		
		if(!success) {
			System.out.println("Failed to run query: "+sql);
		}
		
		//Delete older caretaker skills from db
		sql = "DELETE FROM user_skill WHERE user = "+id+";";
		
		success = database.RunSQL(sql);
		if(!success) {
			System.out.println("Failed to run query: "+sql);
		}
		
		if(caretaker.getSkills().size() != 0) {
			try {
				sql = "SELECT skill_id, skill_name FROM skill;";
				
				ResultSet sqlResult = database.RunSQLQuery(sql);
				
				ArrayList<Integer> newerSkills = new ArrayList<Integer>();
				
				while(sqlResult.next()) {
					int skillID = sqlResult.getInt(1);
					for(int i = 0 ; i < caretaker.getSkills().size() ; i++) {
						if(caretaker.getSkills().get(i).equals(sqlResult.getString(2))) {
							System.out.println("got skill : " + skillID);
							newerSkills.add(skillID);
						}
					}
				}
				//loop though and add skills to caretaker
				for (int i = 0 ; i < newerSkills.size(); i++) {
					sql = "INSERT INTO user_skill (user, skill) VALUES ("+id+", "+newerSkills.get(i)+");";
					success = database.RunSQL(sql);
					if(!success) {
						System.out.println("Failed to run query: "+sql);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateCaretakerTask(CaretakerTask caretakerTask) {
		
		if(caretakerTask.getID() != 0) {
			
			int id = caretakerTask.getID();
			
			String title = caretakerTask.getTitle();
			title = title.replaceAll("[^\\x00-\\x7F]", "");
			title = title.replaceAll("[';']", "");
			
			String desc = caretakerTask.getDesc();
			desc = desc.replaceAll("[^\\x00-\\x7F]", "");
			desc = desc.replaceAll("[';']", "");
			
			String exConsider = caretakerTask.getExtraConsiderations();
			exConsider = exConsider.replaceAll("[^\\x00-\\x7F]", "");
			exConsider = exConsider.replaceAll("[';']", "");
			
			String dateCreated = caretakerTask.getDateCreated();
			dateCreated = dateCreated.replaceAll("[^\\x00-\\x7F]", "");
			dateCreated = dateCreated.replaceAll("[';']", "");
			
			String dateDue = caretakerTask.getDateDue();
			dateDue = dateDue.replaceAll("[^\\x00-\\x7F]", "");
			dateDue = dateDue.replaceAll("[';']", "");
			
			String dateUpdated = caretakerTask.getDateUpdated();
			dateUpdated = dateUpdated.replaceAll("[^\\x00-\\x7F]", "");
			dateUpdated = dateUpdated.replaceAll("[';']", "");
			
			String dateCompleted = caretakerTask.getDateCompleted();
			dateCompleted = dateCompleted.replaceAll("[^\\x00-\\x7F]", "");
			dateCompleted = dateCompleted.replaceAll("[';']", "");
			
			int priority = caretakerTask.getPriority();
			int daysUntilRepeat = caretakerTask.getDaysUntilRepeat();
			int authorID = caretakerTask.getAuthorID();
			int completionistID = caretakerTask.getCompletionistID();
			int signeeID = caretakerTask.getSigneeID();
			int peerCheckerID = caretakerTask.getPeerCheckerID();
			//convert boolean to int 1 true/0 false
			int needsSigning = caretakerTask.isNeedsSigning() ? 1 : 0;
			int needsPeerChecking = caretakerTask.isNeedsPeerChecking() ? 1 : 0;
			
			String sql = "SELECT task_id FROM task WHERE task_id = "+id+";";
			
			ResultSet sqlResult = database.RunSQLQuery(sql);
			try {
				sql = "UPDATE task SET task_title = '"+title+"', task_desc = '"+desc+"', extra_considerations = '"+exConsider+"', need_signing = "+needsSigning+", need_peer_check = "+needsPeerChecking+", "  
						+" date_created = '"+dateCreated+"', date_due = '"+dateDue+"', date_updated = '"+dateUpdated+"', date_completed = '"+dateCompleted+"', priority = "+priority+", created_by = "+authorID+", days_till_repeat = "+daysUntilRepeat+", "
						+" completed_by ="+completionistID+", signed_by ="+signeeID+", peer_checked_by = "+peerCheckerID
						+" WHERE task_id = "+id+";";
				
				boolean success = database.RunSQL(sql);
				if(!success) {
					System.out.println("Failed to run query: "+sql);
				}
				
				//Delete old version of skills
				sql = "DELETE FROM task_skill WHERE task = "+id+";";
				
				success = database.RunSQL(sql);
				if(!success) {
					System.out.println("Failed to run query: "+sql);
				}
				
				if(caretakerTask.getRecSkills().size() != 0) {
					
					sql = "SELECT skill_id, skill_name FROM skill;";
					
					sqlResult = database.RunSQLQuery(sql);
					
					ArrayList<Integer> newerSkills = new ArrayList<Integer>();
					
					while(sqlResult.next()) {
						int skillID = sqlResult.getInt(1);
						for(int i = 0 ; i < caretakerTask.getRecSkills().size() ; i++) {
							if(caretakerTask.getRecSkills().get(i).equals(sqlResult.getString(2))) {
								newerSkills.add(skillID);
								break;
							}
						}
					}
					
					for (int i = 0 ; i < newerSkills.size(); i++) {
						sql = "INSERT INTO task_skill (task, skill) VALUES ("+id+", "+newerSkills.get(i)+");";
						success = database.RunSQL(sql);
						if(!success) {
							System.out.println("Failed to run query: "+sql);
						}
					}
				}
				
				//remove old version of team members from db
				sql = "DELETE FROM team WHERE task = "+id+";";
				
				success = database.RunSQL(sql);
				
				if(!success) {
					System.out.println("Failed to run query: "+sql);
				}
				
				if(caretakerTask.getTeamMembers().size() != 0) {
					
					sql = "SELECT user_id FROM user;";
					
					sqlResult = database.RunSQLQuery(sql);
					
					ArrayList<Integer> newerMembers= new ArrayList<Integer>();
					
					
					while(sqlResult.next()) {
						int memberID = sqlResult.getInt(1);
						for(int i = 0 ; i < caretakerTask.getTeamMembers().size() ; i++) {
							if(caretakerTask.getTeamMembers().get(i).equals(sqlResult.getInt(1))) {
								newerMembers.add(memberID);
								break;
							}
						}
					}
					//Add team members to db for task
					for (int i = 0 ; i < newerMembers.size(); i++) {
						sql = "INSERT INTO team (task, member) VALUES ("+id+", "+newerMembers.get(i)+");";
						success = database.RunSQL(sql);
						if(!success) {
							System.out.println("Failed to run query: "+sql);
						}
					}
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} else {
		}
	}
	
	//DELETE FUNCTIONS
	public void DeleteUser(int userID) {
		
		String sql = "DELETE FROM user WHERE user_id = "+userID+";";
		
		boolean success = database.RunSQL(sql);
		
		if(!success) {
			System.out.println("Failed to run query: "+sql);
		}
		
		sql = "DELETE FROM user_skill WHERE user = "+userID+";";
		
		success = database.RunSQL(sql);
		
		if(!success) {
			System.out.println("Failed to run query: "+sql);
		}
	}
	
	public void DeleteCaretakerTask(int taskID) {
		
		String sql = new String("DELETE FROM task WHERE task_id = "+taskID+";");
		
		boolean success = database.RunSQL(sql);
		
		if(!success) {
			System.out.println("Failed to run query: "+sql);
		}
		
		sql = "DELETE FROM task_skill WHERE task = "+taskID+";";
		
		success = database.RunSQL(sql);
		
		if(!success) {
			System.out.println("Failed to run query: "+sql);
		}
		
	}
	
	//LOGIN TABLEMETHODS
	
	public String getHash(String username) {
		
		String pwdHash = null;
		
		username = username.replaceAll("[^\\x00-\\x7F]", "");
		username = username.replaceAll("[';']", "");
		
		String sql = "SELECT login_username, login_pass FROM login WHERE login_username = '"+username+"';";
		
		ResultSet hashResultSet = database.RunSQLQuery(sql);
		
		try {
			if(hashResultSet.next()) {
				pwdHash = hashResultSet.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pwdHash;
	}
	
	public int getAccId(String username) {
		
		int id = 0;
		
		if(username != null) {
			
			username = username.replaceAll("[^\\x00-\\x7F]", "");
			username = username.replaceAll("[';']", "");
			
			String sql = "SELECT login_user FROM login WHERE login_username = '"+username+"';";
			
			ResultSet userIdResult = database.RunSQLQuery(sql);
			
			try {
				id = userIdResult.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
}