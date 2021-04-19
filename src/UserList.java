import java.util.ArrayList;
import java.util.Scanner;

public class UserList {
	CapyTecDB dbClass = new CapyTecDB();
	ArrayList<Caretaker> caretakerList = new ArrayList<Caretaker>();
	ArrayList<Manager> managerList = new ArrayList<Manager>();
	
	public void addCaretaker(){
		Caretaker newCaretaker = new Caretaker();
		Scanner sc = new Scanner(System.in);
		System.out.println("Input first name:");
		String firstname = sc.nextLine();
		System.out.println("Input surname:");
		String surname = sc.nextLine();
		System.out.println("Input talent:");
		String talent = sc.nextLine();
		System.out.println(firstname + " " + surname + " Caretaker " + talent);
		//Does not yet add caretaker object, just takes inputs
		sc.close();
	}

}
