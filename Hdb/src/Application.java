public class Application {
    private final Project project;
	private final Applicant applicant;
	private String appliedStatus;
	private int flatType;
	private boolean withdrawalRequest; //if a request for withdrawal has been made
	private String withdrawalStatus;  //for status set to either pending, success, failed

    Application(Applicant applicant, Project project) {
		this.project = project;
		this.applicant = applicant;
		this.appliedStatus = "Pending";
		this.flatType = -1;
		this.withdrawalRequest = false;
		this.withdrawalStatus = "Pending";
	}
    
	public Project getProject() {
		return project;
	}
	
	public Applicant getApplicant() {
		return applicant;
	}
	
    //could change this to specific approve, or reject
	public void setAppliedStatus(String status) {
		this.appliedStatus = status;
	}
	
	public String getAppliedStatus() {
		return appliedStatus;
	}
	
	public void setFlatType(int r) {
        flatType = r;
        System.out.println(flatType + " room selected.");
    }
	
	public int getFlatType() {
		return flatType;
	}
	
	public void requestWithdrawal() {
		this.withdrawalRequest = true;
		this.withdrawalStatus = "Pending";
	}
	
	public boolean getWithdrawalRequest() {
		return withdrawalRequest;
	}
	
	public void approveWithdrawal() {
		this.withdrawalStatus = "Success";
	}
	
	public void rejectWithdrawal() {
		this.withdrawalRequest = false;
		this.withdrawalStatus = "Failed";
	}
	
	public void resetWithdrawalStatus() {
		this.withdrawalStatus = "Pending";
	}
	
	public String getWithdrawalStatus() {
		return withdrawalStatus;
	}
	
	//========== BTO Application ==========
	//Application Status: Pending
	//-------------------------------------
	//Applicant: Joe Shmoe
	//NRIC: SXXXXXXXE
	//Age: 32
	//Marital Status: Married
	//
	//Project: GrovesProject
	//Flat-type: 2-room
	//Number of units available: 3
	//-------------------------------------
	//Withdrawal requested: false
	//Withdrawal status: Failed
	
	public void print() {
		int numUnits = 0;
		String marriedStatus = "NIL";
		
		if (flatType == 2) {
			numUnits = project.getNum2Rooms();
		}
		else if (flatType == 3) {
			numUnits = project.getNum3Rooms();
		}
		
		if (applicant.isMarried()) {
			marriedStatus = "Married";
		}
		else {
			marriedStatus = "Single";
		}
		
		System.out.println();
		System.out.println("========== BTO Application ==========");
		System.out.println("Application Status: " + appliedStatus);
		System.out.println("-------------------------------------");
		System.out.println("Applicant: " + applicant.getName());
		System.out.println("NRIC: " + applicant.getNRIC());
		System.out.println("Age: " + applicant.getAge());
		System.out.println("Marital Status: " + marriedStatus);
		System.out.println();
		System.out.println("Project: " + project.getName());
		System.out.println("Flat-type: " + flatType + " room");
		System.out.println("Number of units available: " + numUnits);
		System.out.println("-------------------------------------");
		System.out.println("Withdrawal requested: " + withdrawalRequest);
		System.out.println("Withdrawal status: " + withdrawalStatus);
		
	}
}
