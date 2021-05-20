import java.util.ArrayList;

public abstract class Task {
	
	private int id;
	private String title;
	private String desc;
	private int priority;
	
	private String dateCreated;
	private String dateDue;
	private String dateCompleted;
	private String dateUpdated;
	
	private int daysUntilRepeat;
	
	private String author;
	private String completionist;
	private String signee;
	private String peerChecker;
	
	private int authorID;
	private int completionistID;
	private int signeeID;
	private int peerCheckerID;
	
	private boolean needsSigning;
	private boolean needsPeerChecking;
	
	private ArrayList<String> recSkills = new ArrayList<String>();
	private ArrayList<Integer> teamMembers = new ArrayList<Integer>(); 
	
	public Task() {
		
		setTitle("TITLE NOT SET!");
		setDesc("DESCRIPTION NOT SET!");
		
		setNeedsSigning(false);
		setNeedsPeerChecking(false);
		
	}


	public int getID() {
		return id;
	}


	public void setID(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}
	
	
	public void setDesc(String desc) {
		this.desc = desc; 
	}
	
	
	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public String getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}


	public String getDateDue() {
		return dateDue;
	}


	public void setDateDue(String dateDue) {
		this.dateDue = dateDue;
	}


	public String getDateCompleted() {
		return dateCompleted;
	}


	public void setDateCompleted(String dateCompleted) {
		this.dateCompleted = dateCompleted;
	}


	public String getDateUpdated() {
		return dateUpdated;
	}


	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
	
	public int getDaysUntilRepeat() {
		return daysUntilRepeat;
	}


	public void setDaysUntilRepeat(int daysUntilRepeat) {
		this.daysUntilRepeat = daysUntilRepeat;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getCompletionist() {
		return completionist;
	}


	public void setCompletionist(String completionist) {
		this.completionist = completionist;
	}


	public String getSignee() {
		return signee;
	}


	public void setSignee(String signee) {
		this.signee = signee;
	}


	public String getPeerChecker() {
		return peerChecker;
	}


	public void setPeerChecker(String peerChecker) {
		this.peerChecker = peerChecker;
	}


	public int getAuthorID() {
		return authorID;
	}


	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}


	public int getCompletionistID() {
		return completionistID;
	}


	public void setCompletionistID(int completionistID) {
		this.completionistID = completionistID;
	}


	public int getSigneeID() {
		return signeeID;
	}


	public void setSigneeID(int signeeID) {
		this.signeeID = signeeID;
	}


	public int getPeerCheckerID() {
		return peerCheckerID;
	}


	public void setPeerCheckerID(int peerCheckerID) {
		this.peerCheckerID = peerCheckerID;
	}


	public boolean isNeedsSigning() {
		return needsSigning;
	}

	public void setNeedsSigning(boolean needsSigning) {
		this.needsSigning = needsSigning;
	}

	public boolean isNeedsPeerChecking() {
		return needsPeerChecking;
	}

	public void setNeedsPeerChecking(boolean needsPeerChecking) {
		this.needsPeerChecking = needsPeerChecking;
	}

	public ArrayList<String> getRecSkills() {
		return recSkills;
	}

	public void setRecSkills(ArrayList<String> recSkills) {
		this.recSkills = recSkills;
	}

	public ArrayList<Integer> getTeamMembers() {
		return teamMembers;
	}


	public void setTeamMembers(ArrayList<Integer> teamMembers) {
		this.teamMembers = teamMembers;
	}

}
