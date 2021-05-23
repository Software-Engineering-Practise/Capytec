import java.util.ArrayList;

public class TestingDBMethods {

	public static void main(String[] args) {
		
		System.out.println("TEST DB METHODS STARTING!!!");
		
		
		//testGetAllCaretakers();
		//testGetAllTasks();
		//testGetAllCompletedTasks();
		testInsertTask();
		//testUpdateCaretaker();
		//testUpdateTask();
		
	}
	
	public static void testGetAllCaretakers() {
		
		System.out.println("");
		System.out.println("######################TEST 1######################");
		System.out.println("GETTING ALL CARETAKERS!!!");
		
		CapyTecDB DBTest = new CapyTecDB();
		
		ArrayList<Caretaker> caretakers = new ArrayList<Caretaker>();
		caretakers = DBTest.getAllCaretakers();
		
		System.out.println("Number of Caretakers: " + caretakers.size());
		
		for(int i = 0 ; i < caretakers.size() ; i++ ) {
			
			System.out.println("Caretaker ID: " + caretakers.get(i).getID());
			System.out.println("Full Name: " + caretakers.get(i).getFullName());
			System.out.println("Job Title: " + caretakers.get(i).getJobTitle());
			
			for(int j = 0; j < caretakers.get(i).getSkills().size() ; j++) {
				System.out.println("Skill: " + caretakers.get(i).getSkills().get(j));
			}
		}
	}
	
	public static void testGetAllTasks() {
		
		System.out.println("");
		System.out.println("######################TEST 3######################");
		System.out.println("GETTING ALL TASKS!!!");
		
		CapyTecDB DBTest = new CapyTecDB();
		
		ArrayList<CaretakerTask> allTasks = new ArrayList<CaretakerTask>();
		
		allTasks = DBTest.getAllTasks();
		
		System.out.println("Got Tasks!");
		
		for(int i = 0; i < allTasks.size() ; i++) {
			
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
	
	public static void testGetAllCompletedTasks() {
		
		System.out.println("");
		System.out.println("######################TEST 4######################");
		System.out.println("GETTING ALL COMPLETED TASKS!!!");
		
		CapyTecDB DBTest = new CapyTecDB();
		
		ArrayList<CompletedTask> allTasks = DBTest.getAllCompletedTasks();
		
		
		for(int i = 0; i < allTasks.size() ; i++) {
			
			System.out.println("");
			System.out.println("##############Record ID: " + allTasks.get(i).getId());
			System.out.println("Task ID: " + allTasks.get(i).getTaskID());
			System.out.println("User ID: " + allTasks.get(i).getUserID());
			System.out.println("Date Completed: " + allTasks.get(i).getDateCompleted());
			
		}
		
	}
	
	public static void testInsertTask() {
		
		System.out.println("");
		System.out.println("######################TEST 5######################");
		System.out.println("INSERTING NEW TASK!!!");
		
		CapyTecDB DBTest = new CapyTecDB();
		
		CaretakerTask testTask = new CaretakerTask();
		
		testTask.setTitle("Test Insert Title");
		testTask.setDesc("Test Insert Desc");
		testTask.setDateCreated("2021-3-13 12:00:00");
		testTask.setDateDue("2420-2-11 9:00:00");
		testTask.setAuthorID(1);
		testTask.setPriority(2);
		testTask.getRecSkills().add(DBTest.getAllSkills().get(1));
		testTask.getRecSkills().add(DBTest.getAllSkills().get(2));
		testTask.getRecSkills().add(DBTest.getAllSkills().get(3));
		testTask.getTeamMembers().add(DBTest.getAllCaretakers().get(0).getID());
		
		System.out.println("Reccomended Skill: " + testTask.getRecSkills().get(0));
		
		DBTest.addCaretakerTask(testTask);
		
		ArrayList<CaretakerTask> allTasks = new ArrayList<CaretakerTask>();
		
		allTasks = DBTest.getAllTasks();
		
		System.out.println("Got Tasks!");
		
		int id = 0;
		
		for(int i = 0; i < allTasks.size() ; i++) {
			
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
				System.out.println("GOT ID FOR DELETE!");
				id = allTasks.get(i).getID();
			}
		}
		
		
		DBTest.deleteCaretakerTask(id);
		
	}
	
	public static void testUpdateCaretaker() {
		
		System.out.println("");
		System.out.println("######################TEST 6######################");
		System.out.println("UPDATING CARETAKER!!!");
		
		CapyTecDB DBTest = new CapyTecDB();
		
		Caretaker caretaker = DBTest.getAllCaretakers().get(0);
		
		System.out.println("Caretaker ID: " + caretaker.getID());
		System.out.println("Full Name: " + caretaker.getFullName());
		System.out.println("Job Title: " + caretaker.getJobTitle());
		
		for(int j = 0; j < caretaker.getSkills().size() ; j++) {
			System.out.println("Skill: " + caretaker.getSkills().get(j));
		}
		
		caretaker.setFirstName("Daniel");
		String changeSkill = DBTest.getAllSkills().get(4);
		System.out.println("changed skill to: " + DBTest.getAllSkills().get(4));
		
		caretaker.getSkills().set(1, changeSkill);
		
		DBTest.updateCaretaker(caretaker);
		
		Caretaker updatedCaretaker = DBTest.getAllCaretakers().get(0);
		
		System.out.println("Caretaker ID: " + updatedCaretaker.getID());
		System.out.println("Full Name: " + updatedCaretaker.getFullName());
		System.out.println("Job Title: " + updatedCaretaker.getJobTitle());
		
		//System.out.println(updatedCaretaker.getSkills().get(1));
		
		for(int j = 0; j < updatedCaretaker.getSkills().size() ; j++) {
			System.out.println("Skill: " + updatedCaretaker.getSkills().get(j));
		}
		
		caretaker.setFirstName("Dan");
		
		DBTest.updateCaretaker(caretaker);
		
	}
	
	public static void testUpdateTask() {
		
		System.out.println("");
		System.out.println("######################TEST 7######################");
		System.out.println("UPDATING NEW TASK!!!");
		
		CapyTecDB DBTest = new CapyTecDB();
		
		CaretakerTask testTask = DBTest.getAllTasks().get(0);
		
		testTask.setTitle("Task: Update Me");

		testTask.getRecSkills().add(DBTest.getAllSkills().get(1));
		testTask.getRecSkills().add(DBTest.getAllSkills().get(2));
		testTask.setDateCompleted(null);
		

		testTask.getTeamMembers().add(1);
		
		DBTest.updateCaretakerTask(testTask);
		
		testTask.setTitle("Task: Exist(Test)");
		testTask.setDesc("Test Description");
		testTask.setExtraConsiderations("Test Considerations");
		
		testTask.getRecSkills().clear();
		testTask.getRecSkills().size();
		testTask.getTeamMembers().clear();
		
		for(int j = 0 ; j < testTask.getTeamMembers().size() ; j++) {
			
			System.out.println("Member ID: " + testTask.getTeamMembers().get(j));
		}
		
		for(int j = 0 ; j < testTask.getRecSkills().size() ; j++) {
			System.out.println("Reccomended Skill: " + testTask.getRecSkills().get(j));
		}
		
		System.out.println("Skill Count: "+testTask.getRecSkills().size());
		
		DBTest.updateCaretakerTask(testTask);
		testTask = DBTest.getAllTasks().get(0);
		
		System.out.println("task is now: "+ testTask.getTitle());
		
		System.out.println("=====TEAM MEMBERS=====");
		System.out.println("Number Team members: "+ testTask.getTeamMembers().size());
		for(int j = 0 ; j < testTask.getTeamMembers().size() ; j++) {
			
			System.out.println("Member ID: " + testTask.getTeamMembers().get(j));
		}
		
		for(int j = 0 ; j < testTask.getRecSkills().size() ; j++) {
			System.out.println("Reccomended Skill: " + testTask.getRecSkills().get(j));
		}
		
	}
}
