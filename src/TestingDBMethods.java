import java.util.ArrayList;

public class TestingDBMethods {

	public static void main(String[] args) {
		
		System.out.println("TEST METHOD(S) STARTING!!!");
		
		//testGetAllCaretakers();
		//testGetAllTasks();
		//testGetAllCompletedTasks();
		//testInsertTask();
		//testUpdateCaretaker();
		//testUpdateTask();
		testMultiSkillTask();
		
	}
	
	public static void testGetAllCaretakers() {
		
		System.out.println("");
		System.out.println("######################TEST 1: Retrieve all caretaker Users from system database######################");
		System.out.println("");
		
		//Start instance of database class, constructor will start a connection to database
		CapyTecDB DBTest = new CapyTecDB();
		
		//Retrieve all care taker users from database via specified method
		ArrayList<Caretaker> caretakers = DBTest.getAllCaretakers();
		
		System.out.println("Number of Caretakers: " + caretakers.size());
		
		for(int i = 0 ; i < caretakers.size() ; i++ ) {//loop though all care takers and print out their details
			
			System.out.println("");
			System.out.println("##############Caretaker ID: " + caretakers.get(i).getID());
			System.out.println("Full Name: " + caretakers.get(i).getFullName());
			System.out.println("Job Title: " + caretakers.get(i).getJobTitle());
			for(int j = 0; j < caretakers.get(i).getSkills().size() ; j++) {
				System.out.println("Skill: " + caretakers.get(i).getSkills().get(j));
			}
		}
	}
	
	public static void testGetAllTasks() {
		
		System.out.println("");
		System.out.println("######################TEST 2: Retrieve all tasks from the system database######################");
		System.out.println("");
		
		//Start instance of database class, constructor will start a connection to database
		CapyTecDB DBTest = new CapyTecDB();
		
		//Retrieve all tasks from database
		ArrayList<CaretakerTask> allTasks = DBTest.getAllTasks();
		
		System.out.println("Number of tasks: "+ allTasks.size());
		
		for(int i = 0; i < allTasks.size() ; i++) {//loop though all tasks and print out details
			
			System.out.println("");
			System.out.println("##############Task ID: " + allTasks.get(i).getID());
			System.out.println("Title: " + allTasks.get(i).getTitle());
			System.out.println("Task Description: " + allTasks.get(i).getDesc());
			System.out.println("Extra Considerations: " + allTasks.get(i).getExtraConsiderations());
			System.out.println("Needs Signing is: " + allTasks.get(i).isNeedsSigning());
			System.out.println("Needs Peer Check is: " + allTasks.get(i).isNeedsPeerChecking());
			System.out.println("Date Created: " + allTasks.get(i).getDateCreated());
			System.out.println("Date Due: " + allTasks.get(i).getDateDue());
			System.out.println("Date Updated: " + allTasks.get(i).getDateUpdated());
			System.out.println("Date Completed: " + allTasks.get(i).getDateCompleted());
			System.out.println("Task Priority: " + allTasks.get(i).getPriority());
			System.out.println("Task made by: " + allTasks.get(i).getAuthor());
			System.out.println("Task completed by: " + allTasks.get(i).getCompletionist());
			System.out.println("Task peer checked by: " + allTasks.get(i).getPeerChecker());
			System.out.println("Task signed by: " + allTasks.get(i).getSignee());
			System.out.println("Task to be repeated every " + allTasks.get(i).getDaysUntilRepeat() + " days.");
			System.out.println("=====RECOMMENDED SKILLS=====");
			for(int j = 0 ; j < allTasks.get(i).getRecSkills().size() ; j++) {
				System.out.println("Reccomended Skill: " + allTasks.get(i).getRecSkills().get(j));
			}
			System.out.println("=====TEAM MEMBERS=====");
			for(int j = 0 ; j < allTasks.get(i).getTeamMembers().size() ; j++) {
				System.out.println("Member ID: " + allTasks.get(i).getTeamMembers().get(j));
			}
		}
	}
	
	public static void testGetAllCompletedTasks() {
		
		System.out.println("");
		System.out.println("######################TEST 3: Get all completed task records######################");
		System.out.println("");
		
		//Start instance of database class, constructor will start a connection to database
		CapyTecDB DBTest = new CapyTecDB();
		
		ArrayList<CompletedTask> allTasks = DBTest.getAllCompletedTasks();
		
		for(int i = 0; i < allTasks.size() ; i++) {
			
			System.out.println("");
			System.out.println("##############Record ID: " + allTasks.get(i).getId());
			System.out.println("Completed Task ID: " + allTasks.get(i).getTaskID());
			System.out.println("Caretaker that completed the task ID: " + allTasks.get(i).getUserID());
			System.out.println("Task's completion date: " + allTasks.get(i).getDateCompleted());
			
		}
		
	}
	
	public static void testInsertTask() {
		
		System.out.println("");
		System.out.println("######################TEST 4: Insert a task into the system database then delete it######################");
		System.out.println("INSERTING NEW TASK!!!");
		
		//Start instance of database class, constructor will start a connection to database
		CapyTecDB DBTest = new CapyTecDB();
		
		CaretakerTask testTask = new CaretakerTask();
		
		ArrayList<CaretakerTask> allTasks = DBTest.getAllTasks();
		
		for(int i = 0; i < allTasks.size() ; i++) {//Display all the tasks in the database
			
			System.out.println("");
			System.out.println("##############Task ID: " + allTasks.get(i).getID());
			System.out.println("Task Title: " + allTasks.get(i).getTitle());
			System.out.println("Task Description: " + allTasks.get(i).getDesc());
			System.out.println("Needs Signing is: " + allTasks.get(i).isNeedsSigning());
			System.out.println("Needs Peer Check is: " + allTasks.get(i).isNeedsPeerChecking());
			System.out.println("Date Created: " + allTasks.get(i).getDateCreated());
			System.out.println("Date Due: " + allTasks.get(i).getDateDue());
			System.out.println("Date Updated: " + allTasks.get(i).getDateUpdated());
			System.out.println("Date Completed: " + allTasks.get(i).getDateCompleted());
			System.out.println("Task Priority: " + allTasks.get(i).getPriority());
			System.out.println("Task made by: " + allTasks.get(i).getAuthor());
			System.out.println("Task completed by: " + allTasks.get(i).getCompletionist());
			System.out.println("Task peer checked by: " + allTasks.get(i).getPeerChecker());
			System.out.println("Task signed by: " + allTasks.get(i).getSignee());
			System.out.println("Task to be repeated every " + allTasks.get(i).getDaysUntilRepeat() + " days.");
			for(int j = 0 ; j < allTasks.get(i).getRecSkills().size() ; j++) {
				System.out.println("Reccomended Skill: " + allTasks.get(i).getRecSkills().get(j));
			}
			System.out.println("=====TEAM MEMBERS=====");
			for(int j = 0 ; j < allTasks.get(i).getTeamMembers().size() ; j++) {
				System.out.println("Member ID: " + allTasks.get(i).getTeamMembers().get(j));
			}
		}
		
		//set task variables
		testTask.setTitle("Title for insert");
		testTask.setDesc("Desc for insert");
		testTask.setDateCreated("2021-3-13 12:00:00");
		testTask.setDateDue("2420-2-11 9:00:00");
		testTask.setAuthorID(1);
		testTask.setPriority(2);
		testTask.getRecSkills().add(DBTest.getAllSkills().get(0));
		testTask.getRecSkills().add(DBTest.getAllSkills().get(4));
		testTask.getRecSkills().add(DBTest.getAllSkills().get(2));
		testTask.getTeamMembers().add(DBTest.getAllCaretakers().get(0).getID());
		testTask.getTeamMembers().add(DBTest.getAllCaretakers().get(1).getID());
		
		System.out.println("");
		
		//insert the task to the database
		DBTest.addCaretakerTask(testTask);
		
		allTasks = DBTest.getAllTasks();
		
		int id = 0;
		
		for(int i = 0; i < allTasks.size() ; i++) {//Display all the tasks in the database
			
			System.out.println("");
			System.out.println("##############Task ID: " + allTasks.get(i).getID());
			System.out.println("Task Title: " + allTasks.get(i).getTitle());
			System.out.println("Task Description: " + allTasks.get(i).getDesc());
			System.out.println("Needs Signing is: " + allTasks.get(i).isNeedsSigning());
			System.out.println("Needs Peer Check is: " + allTasks.get(i).isNeedsPeerChecking());
			System.out.println("Date Created: " + allTasks.get(i).getDateCreated());
			System.out.println("Date Due: " + allTasks.get(i).getDateDue());
			System.out.println("Date Updated: " + allTasks.get(i).getDateUpdated());
			System.out.println("Date Completed: " + allTasks.get(i).getDateCompleted());
			System.out.println("Task Priority: " + allTasks.get(i).getPriority());
			System.out.println("Task made by: " + allTasks.get(i).getAuthor());
			System.out.println("Task completed by: " + allTasks.get(i).getCompletionist());
			System.out.println("Task peer checked by: " + allTasks.get(i).getPeerChecker());
			System.out.println("Task signed by: " + allTasks.get(i).getSignee());
			System.out.println("Task to be repeated every " + allTasks.get(i).getDaysUntilRepeat() + " days.");
			for(int j = 0 ; j < allTasks.get(i).getRecSkills().size() ; j++) {
				System.out.println("Reccomended Skill: " + allTasks.get(i).getRecSkills().get(j));
			}
			System.out.println("=====TEAM MEMBERS=====");
			for(int j = 0 ; j < allTasks.get(i).getTeamMembers().size() ; j++) {
				System.out.println("Member ID: " + allTasks.get(i).getTeamMembers().get(j));
			}
			if(allTasks.get(i).getDateCreated().equals(allTasks.get(i).getDateCreated())) {
				id = allTasks.get(i).getID();
			}
		}
		
		//Delete the task that was just added 
		DBTest.deleteCaretakerTask(id);
		
		allTasks = DBTest.getAllTasks();//update allTasks variable to current state of the database
		
		for(int i = 0; i < allTasks.size() ; i++) {//Display all the tasks in the database
			
			System.out.println("");
			System.out.println("##############Task ID: " + allTasks.get(i).getID());
			System.out.println("Task Title: " + allTasks.get(i).getTitle());
			System.out.println("Task Description: " + allTasks.get(i).getDesc());
			System.out.println("Needs Signing is: " + allTasks.get(i).isNeedsSigning());
			System.out.println("Needs Peer Check is: " + allTasks.get(i).isNeedsPeerChecking());
			System.out.println("Date Created: " + allTasks.get(i).getDateCreated());
			System.out.println("Date Due: " + allTasks.get(i).getDateDue());
			System.out.println("Date Updated: " + allTasks.get(i).getDateUpdated());
			System.out.println("Date Completed: " + allTasks.get(i).getDateCompleted());
			System.out.println("Task Priority: " + allTasks.get(i).getPriority());
			System.out.println("Task made by: " + allTasks.get(i).getAuthor());
			System.out.println("Task completed by: " + allTasks.get(i).getCompletionist());
			System.out.println("Task peer checked by: " + allTasks.get(i).getPeerChecker());
			System.out.println("Task signed by: " + allTasks.get(i).getSignee());
			System.out.println("Task to be repeated every " + allTasks.get(i).getDaysUntilRepeat() + " days.");
			for(int j = 0 ; j < allTasks.get(i).getRecSkills().size() ; j++) {
				System.out.println("Reccomended Skill: " + allTasks.get(i).getRecSkills().get(j));
			}
			System.out.println("=====TEAM MEMBERS=====");
			for(int j = 0 ; j < allTasks.get(i).getTeamMembers().size() ; j++) {
				System.out.println("Member ID: " + allTasks.get(i).getTeamMembers().get(j));
			}
		}
	}
	
	public static void testUpdateCaretaker() {
		
		System.out.println("");
		System.out.println("######################TEST 5: Update a Caretaker on the system database then revert changes######################");
		System.out.println("");
		
		//Start instance of database class, constructor will start a connection to database
		CapyTecDB DBTest = new CapyTecDB();
		
		//get the first care taker the database method retrieves
		Caretaker caretaker = DBTest.getAllCaretakers().get(0);
		Caretaker orignialCaretaker = DBTest.getAllCaretakers().get(0);
		
		//display the values of the care taker before changes
		System.out.println("CARETAKER BEFORE CHANGES");
		System.out.println("Caretaker ID: " + caretaker.getID());
		System.out.println("Full Name: " + caretaker.getFullName());
		System.out.println("Job Title: " + caretaker.getJobTitle());
		for(int j = 0; j < caretaker.getSkills().size() ; j++) {
			System.out.println("Skill: " + caretaker.getSkills().get(j));
		}
		
		//change the variables of the care taker
		caretaker.setFirstName("Updated First Name");
		caretaker.setLastName("Updated Last Name");
		caretaker.getSkills().clear();
		caretaker.getSkills().add(DBTest.getAllSkills().get(0));
		caretaker.getSkills().add(DBTest.getAllSkills().get(1));
		
		//Update the care taker to the database
		DBTest.updateCaretaker(caretaker);
		
		//get the updated version of the care taker
		Caretaker updatedCaretaker = DBTest.getAllCaretakers().get(0);
		
		//display the updated care taker
		System.out.println("CARETAKER AFTER CHANGES");
		System.out.println("Caretaker ID: " + updatedCaretaker.getID());
		System.out.println("Full Name: " + updatedCaretaker.getFullName());
		System.out.println("Job Title: " + updatedCaretaker.getJobTitle());
		for(int j = 0; j < updatedCaretaker.getSkills().size() ; j++) {
			System.out.println("Skill: " + updatedCaretaker.getSkills().get(j));
		}
		
		//revert the changes of the database
		DBTest.updateCaretaker(orignialCaretaker);
		
		//get the updated version of the care taker
		updatedCaretaker = DBTest.getAllCaretakers().get(0);
		
		//display the updated care taker
		System.out.println("CARETAKER AFTER RETURNING TO ORIGINAL");
		System.out.println("Caretaker ID: " + updatedCaretaker.getID());
		System.out.println("Full Name: " + updatedCaretaker.getFullName());
		System.out.println("Job Title: " + updatedCaretaker.getJobTitle());
		for(int j = 0; j < updatedCaretaker.getSkills().size() ; j++) {
			System.out.println("Skill: " + updatedCaretaker.getSkills().get(j));
		}
	}
	
	public static void testUpdateTask() {
		
		System.out.println("");
		System.out.println("######################TEST 6: Update a task on the system database then revert changes######################");
		System.out.println("");
		
		//Start instance of database class, constructor will start a connection to database
		CapyTecDB DBTest = new CapyTecDB();
		
		//get first task the database method gets
		CaretakerTask testTask = DBTest.getAllTasks().get(0);
		CaretakerTask originalTestTask = DBTest.getAllTasks().get(0);
		
		//display task details before changes
		System.out.println("TASK BEFORE CHANGES");
		System.out.println("Task ID: " + testTask.getID());
		System.out.println("Task Title: " + testTask.getTitle());
		System.out.println("Task Description: " + testTask.getDesc());
		System.out.println("Extra Considerations: " + testTask.getExtraConsiderations());
		System.out.println("Needs Signing is: " + testTask.isNeedsSigning());
		System.out.println("Needs Peer Check is: " + testTask.isNeedsPeerChecking());
		System.out.println("Date Created: " + testTask.getDateCreated());
		System.out.println("Date Due: " + testTask.getDateDue());
		System.out.println("Date Updated: " + testTask.getDateUpdated());
		System.out.println("Date Completed: " + testTask.getDateCompleted());
		System.out.println("Task Priority: " + testTask.getPriority());
		System.out.println("Task made by: " + testTask.getAuthor());
		System.out.println("Task completed by: " + testTask.getCompletionist());
		System.out.println("Task peer checked by: " + testTask.getPeerChecker());
		System.out.println("Task signed by: " + testTask.getSignee());
		System.out.println("Task to be repeated every " + testTask.getDaysUntilRepeat() + " days.");
		System.out.println("=====RECOMMENDED SKILLS=====");
		for(int j = 0 ; j < testTask.getRecSkills().size() ; j++) {
			System.out.println("Reccomended Skill: " + testTask.getRecSkills().get(j));
		}
		System.out.println("");
		System.out.println("=====TEAM MEMBERS=====");
		for(int j = 0 ; j < testTask.getTeamMembers().size() ; j++) {
			System.out.println("Member ID: " + testTask.getTeamMembers().get(j));
		}
		System.out.println("");
		//change the task variables
		testTask.setTitle("Updated Title");
		testTask.setDesc("Updated description");
		testTask.setExtraConsiderations("Updated considerations!");
		testTask.getRecSkills().clear();
		testTask.getRecSkills().add(DBTest.getAllSkills().get(1));
		testTask.getRecSkills().add(DBTest.getAllSkills().get(2));
		testTask.getTeamMembers().clear();
		testTask.getTeamMembers().add(DBTest.getAllCaretakers().get(0).getID());
		testTask.getTeamMembers().add(DBTest.getAllCaretakers().get(2).getID());
		
		DBTest.updateCaretakerTask(testTask);
		
		//get updated task
		testTask = DBTest.getAllTasks().get(0);
		
		//display task details after changes
		System.out.println("TASK AFTER CHANGES");
		System.out.println("Task ID: " + testTask.getID());
		System.out.println("Task Title: " + testTask.getTitle());
		System.out.println("Task Description: " + testTask.getDesc());
		System.out.println("Extra Considerations: " + testTask.getExtraConsiderations());
		System.out.println("Needs Signing is: " + testTask.isNeedsSigning());
		System.out.println("Needs Peer Check is: " + testTask.isNeedsPeerChecking());
		System.out.println("Date Created: " + testTask.getDateCreated());
		System.out.println("Date Due: " + testTask.getDateDue());
		System.out.println("Date Updated: " + testTask.getDateUpdated());
		System.out.println("Date Completed: " + testTask.getDateCompleted());
		System.out.println("Task Priority: " + testTask.getPriority());
		System.out.println("Task made by: " + testTask.getAuthor());
		System.out.println("Task completed by: " + testTask.getCompletionist());
		System.out.println("Task peer checked by: " + testTask.getPeerChecker());
		System.out.println("Task signed by: " + testTask.getSignee());
		System.out.println("Task to be repeated every " + testTask.getDaysUntilRepeat() + " days.");	
		System.out.println("=====RECOMMENDED SKILLS=====");
		for(int j = 0 ; j < testTask.getRecSkills().size() ; j++) {
			System.out.println("Reccomended Skill: " + testTask.getRecSkills().get(j));
		}
		System.out.println("");
		System.out.println("=====TEAM MEMBERS=====");
		for(int j = 0 ; j < testTask.getTeamMembers().size() ; j++) {
			System.out.println("Member ID: " + testTask.getTeamMembers().get(j));
		}
		System.out.println("");
		DBTest.updateCaretakerTask(originalTestTask);
		
		//get updated task
		testTask = DBTest.getAllTasks().get(0);
		
		//display task details after changes to original
		System.out.println("TASK AFTER CHANGES");
		System.out.println("Task ID: " + testTask.getID());
		System.out.println("Task Title: " + testTask.getTitle());
		System.out.println("Task Description: " + testTask.getDesc());
		System.out.println("Extra Considerations: " + testTask.getExtraConsiderations());
		System.out.println("Needs Signing is: " + testTask.isNeedsSigning());
		System.out.println("Needs Peer Check is: " + testTask.isNeedsPeerChecking());
		System.out.println("Date Created: " + testTask.getDateCreated());
		System.out.println("Date Due: " + testTask.getDateDue());
		System.out.println("Date Updated: " + testTask.getDateUpdated());
		System.out.println("Date Completed: " + testTask.getDateCompleted());
		System.out.println("Task Priority: " + testTask.getPriority());
		System.out.println("Task made by: " + testTask.getAuthor());
		System.out.println("Task completed by: " + testTask.getCompletionist());
		System.out.println("Task peer checked by: " + testTask.getPeerChecker());
		System.out.println("Task signed by: " + testTask.getSignee());
		System.out.println("Task to be repeated every " + testTask.getDaysUntilRepeat() + " days.");
		System.out.println("=====RECOMMENDED SKILLS=====");
		for(int j = 0 ; j < testTask.getRecSkills().size() ; j++) {
			System.out.println("Reccomended Skill: " + testTask.getRecSkills().get(j));
		}
		System.out.println("");
		System.out.println("=====TEAM MEMBERS=====");
		for(int j = 0 ; j < testTask.getTeamMembers().size() ; j++) {
			System.out.println("Member ID: " + testTask.getTeamMembers().get(j));
		}
		System.out.println("");
	}
	
	public static void testMultiSkillTask() {
		
		CapyTecDB db = new CapyTecDB();
		
		CaretakerTask newTask = new CaretakerTask();
		
		newTask.setTitle("Title for insert");
		newTask.setDesc("Desc for insert");
		newTask.setDateCreated("2021-3-13 12:00:00");
		newTask.setDateDue("2420-2-11 9:00:00");
		newTask.setAuthorID(1);
		newTask.setPriority(2);
		newTask.getRecSkills().add(db.getAllSkills().get(0));
		newTask.getRecSkills().add(db.getAllSkills().get(4));
		newTask.getRecSkills().add(db.getAllSkills().get(2));
		newTask.getTeamMembers().add(db.getAllCaretakers().get(0).getID());
		//newTask.getTeamMembers().add(db.getAllCaretakers().get(1).getID());
		
		db.addCaretakerTask(newTask);
		
		newTask = db.getAllTasks().get(6);
		
		System.out.println("TASK AFTER CHANGES");
		System.out.println("Task ID: " + newTask.getID());
		System.out.println("Task Title: " + newTask.getTitle());
		System.out.println("Task Description: " + newTask.getDesc());
		System.out.println("Extra Considerations: " + newTask.getExtraConsiderations());
		System.out.println("Needs Signing is: " + newTask.isNeedsSigning());
		System.out.println("Needs Peer Check is: " + newTask.isNeedsPeerChecking());
		System.out.println("Date Created: " + newTask.getDateCreated());
		System.out.println("Date Due: " + newTask.getDateDue());
		System.out.println("Date Updated: " + newTask.getDateUpdated());
		System.out.println("Date Completed: " + newTask.getDateCompleted());
		System.out.println("Task Priority: " + newTask.getPriority());
		System.out.println("Task made by: " + newTask.getAuthor());
		System.out.println("Task completed by: " + newTask.getCompletionist());
		System.out.println("Task peer checked by: " + newTask.getPeerChecker());
		System.out.println("Task signed by: " + newTask.getSignee());
		System.out.println("Task to be repeated every " + newTask.getDaysUntilRepeat() + " days.");
		System.out.println("=====RECOMMENDED SKILLS=====");
		for(int i = 0 ; i < newTask.getRecSkills().size() ; i++) {
			System.out.println("Reccomended Skill: " + newTask.getRecSkills().get(i));
		}
		System.out.println("");
		System.out.println("=====TEAM MEMBERS=====");
		for(int i = 0 ; i < newTask.getTeamMembers().size() ; i++) {
			System.out.println("Member ID: " + newTask.getTeamMembers().get(i));
		}
		
		System.out.println("");
		
	}
	
}
