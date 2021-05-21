import java.util.ArrayList;

public class TestingDBMethods {

	public static void main(String[] args) {
		
		System.out.println("TEST DB METHODS STARTING!!!");
		
		//test1();
		//test2();
		//test3();
		//test4();
		test5();
		//test6();
	}
	
	public static void test1() {
		
		System.out.println("");
		System.out.println("######################TEST 1######################");
		
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
	
	public static void test2() {
		
		System.out.println("");
		System.out.println("######################TEST 2######################");
		
		CapyTecDB DBTest = new CapyTecDB();
		
		ArrayList<Caretaker> caretakers = DBTest.getAllCaretakers();
		
		Caretaker updateCaretaker = caretakers.get(0);
		
		System.out.println("Caretaker to change:");
		System.out.println("Caretaker ID: " + updateCaretaker.getID());
		System.out.println("Full Name: " + updateCaretaker.getFullName());
		System.out.println("Job Title: " + updateCaretaker.getJobTitle());
		
		updateCaretaker.setLastName("Crook");
		
		//perform update here
	}
	
	public static void test3() {
		
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
	
	public static void test4() {
		
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
	
	public static void test5() {
		
		System.out.println("");
		System.out.println("######################TEST 5######################");
		System.out.println("INSERTING NEW TASK!!!");
		
		CaretakerTask testTask = new CaretakerTask();
		
		testTask.setTitle("Test Insert Title");
		testTask.setDesc("Test Insert Desc");
		testTask.setDateCreated("2021-3-13 12:00:00");
		testTask.setDateDue("2420-2-11 9:00:00");
		testTask.setAuthorID(1);
		testTask.setPriority(2);
		
		CapyTecDB DBTest = new CapyTecDB();
		
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
		
		
		DBTest.DeleteCaretakerTask(id);
		
	}
	
	public static void test6() {
		
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
		String oldSkill = caretaker.getSkills().get(1);
		String changeSkill = DBTest.getAllSkills().get(1);
		caretaker.getSkills().set(1, changeSkill);
		
		DBTest.updateCaretaker(caretaker);
		Caretaker updatedCaretaker = DBTest.getAllCaretakers().get(0);
		
		System.out.println("Caretaker ID: " + updatedCaretaker.getID());
		System.out.println("Full Name: " + updatedCaretaker.getFullName());
		System.out.println("Job Title: " + updatedCaretaker.getJobTitle());
		
		for(int j = 0; j < updatedCaretaker.getSkills().size() ; j++) {
			System.out.println("Skill: " + updatedCaretaker.getSkills().get(j));
		}
		
		caretaker.setFirstName("Dan");
		caretaker.getSkills().set(1, oldSkill);
		
		DBTest.updateCaretaker(caretaker);
		
		
		
	}
	
}
