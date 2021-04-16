import java.util.Date;

public abstract class Task {
	
	private int id;
	private String title;
	private String desc;
	private int priority;
	
	private Date dateCreated;
	private Date dateDue;
	private Date dateCompleted;
	private Date dateUpdated;
	
	private String author;
	private String completionist;
	private String signee;
	private String peerChecker;
	
	private boolean needsChecking;//THIS ROW DOES NOT EXIST IN DB!!!
	private boolean needsSigning;
	private boolean needsPeerChecking;
	
	private User signedBy;
	private User setBy;
	
	
	public Task() {
		
		setTitle("TITLE NOT SET!");
		setDesc("DESCRIPTION NOT SET!");
		
		setNeedsChecking(false);
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


	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public Date getDateDue() {
		return dateDue;
	}


	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}


	public Date getDateCompleted() {
		return dateCompleted;
	}


	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}


	public Date getDateUpdated() {
		return dateUpdated;
	}


	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
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


	public boolean isNeedsChecking() {
		return needsChecking;
	}


	public void setNeedsChecking(boolean needsChecking) {
		this.needsChecking = needsChecking;
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


	public User getSignedBy() {
		return signedBy;
	}


	public void setSignedBy(User signedBy) {
		this.signedBy = signedBy;
	}


	public User getSetBy() {
		return setBy;
	}


	public void setSetBy(User setBy) {
		this.setBy = setBy;
	}

}
