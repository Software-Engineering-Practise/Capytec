
public abstract class User{
	
	private int id;
	private String userName;
	private String firstName;
	private String lastName;
	private String jobTitle;
	
	public User() {
		
		setUserName("USERNAME NOT SET!");
		setFirstName("FIRSTNAME NOT SET!");
		setLastName("LASTNAME NOT SET!");
		
	}
	
	//ENCAPSULATION METHODS
	public int getID() {
		return id;
	}
	
	public void setID(int newID) {
		this.id = newID;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return getFirstName() + " " + getLastName();	
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
}