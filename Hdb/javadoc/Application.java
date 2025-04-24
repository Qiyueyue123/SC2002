/**
 * Entity.<p>
 * Represents an application made by an applicant for a specific project.
 * Tracks the application status, flat type selected, and withdrawal requests.
 */
public class Application {
    /**
     * The project for which the application is made.
     */
    private final Project project;

    /**
     * The applicant who made the application.
     */
    private final Applicant applicant;

    /**
     * The current status of the application (e.g., Pending, Successful, Unsuccessful).
     */
    private String appliedStatus;

    /**
     * The flat type selected by the applicant (e.g., 2 for 2-room, 3 for 3-room).
     * Defaults to -1 if not set.
     */
    private int flatType;

    /**
     * Indicates whether a withdrawal request has been made.
     */
    private boolean withdrawalRequest;

    /**
     * The status of the withdrawal request (e.g., Pending, Success, Failed).
     */
    private String withdrawalStatus;

    /**
     * Constructs a new application for the specified applicant and project.
     * Initializes the application status to "Pending", flat type to -1,
     * and withdrawal request, withdrawal status to false and "Pending".
     *
     * @param applicant the applicant submitting the application
     * @param project   the project applied to
     */
    Application(Applicant applicant, Project project) {
        this.project = project;
        this.applicant = applicant;
        this.appliedStatus = "Pending";
        this.flatType = -1;
        this.withdrawalRequest = false;
        this.withdrawalStatus = "Pending";
    }

    /**
     * Returns the project associated with this application.
     *
     * @return the {@link Project} applied to
     */
    public Project getProject() {
        return project;
    }

    /**
     * Returns the applicant who made this application.
     *
     * @return the {@link Applicant} who applied
     */
    public Applicant getApplicant() {
        return applicant;
    }

    /**
     * Sets the status of this application.
     * Typical statuses include "Pending", "Successful", or "Unsuccessful".
     *
     * @param status the new application status
     */
    public void setAppliedStatus(String status) {
        this.appliedStatus = status;
    }

    /**
     * Returns the current status of this application.
     *
     * @return the application status
     */
    public String getAppliedStatus() {
        return appliedStatus;
    }

    /**
     * Sets the flat type selected for this application.
     *
     * @param r the flat type (e.g., 2 for 2-room, 3 for 3-room)
     */
    public void setFlatType(int r) {
        flatType = r;
    }

    /**
     * Returns the flat type selected in this application.
     *
     * @return the flat type as an integer
     */
    public int getFlatType() {
        return flatType;
    }

    /**
     * Marks this application as having a withdrawal request.
     * Sets the withdrawal status to "Pending".
     */
    public void requestWithdrawal() {
        this.withdrawalRequest = true;
        this.withdrawalStatus = "Pending";
    }

    /**
     * Returns whether a withdrawal request has been made.
     *
     * @return {@code true} if withdrawal requested, {@code false} otherwise
     */
    public boolean getWithdrawalRequest() {
        return withdrawalRequest;
    }

    /**
     * Approves the withdrawal request, setting the withdrawal status to "Success".
     */
    public void approveWithdrawal() {
        this.withdrawalStatus = "Success";
    }

    /**
     * Rejects the withdrawal request, resetting the withdrawal request flag and setting status to "Failed".
     */
    public void rejectWithdrawal() {
        this.withdrawalRequest = false;
        this.withdrawalStatus = "Failed";
    }

    /**
     * Resets the withdrawal status to "Pending".
     */
    public void resetWithdrawalStatus() {
        this.withdrawalStatus = "Pending";
    }

    /**
     * Returns the current withdrawal status.
     *
     * @return the withdrawal status string
     */
    public String getWithdrawalStatus() {
        return withdrawalStatus;
    }

    /**
     * Sets the withdrawal status.
     *
     * @param withdrawalStatus the new withdrawal status string
     */
    public void setWithdrawalStatus(String withdrawalStatus) {
        this.withdrawalStatus = withdrawalStatus;
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
    /**
     * Prints detailed information about the application to the console.
     * Includes applicant details, project details, flat type, price, availability,
     * and withdrawal request status.
     */

    public void print() {
		int numUnits = 0;
		int price = 0;
		String marriedStatus = "NIL";
		
		if (flatType == 2) {
			numUnits = project.getNum2Rooms();
			price = project.getPrice2Room();
		}
		else if (flatType == 3) {
			numUnits = project.getNum3Rooms();
			price = project.getPrice3Room();
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
		System.out.println("Flat-type: " + flatType + "-Room");
		System.out.println("Price: $" + price);
		System.out.println("Number of units available: " + numUnits);
		System.out.println("-------------------------------------");
		System.out.println("Withdrawal Requested: " + withdrawalRequest);
		System.out.println("Withdrawal Status: " + withdrawalStatus);
		
	}
}
