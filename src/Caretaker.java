import java.util.ArrayList;

public final class Caretaker extends User{
	
	private ArrayList<String> skills = new ArrayList<String>();
	
	public Caretaker() {
		
	}
	
	public ArrayList<String> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<String> skills) {
		this.skills = skills;
	}
}
