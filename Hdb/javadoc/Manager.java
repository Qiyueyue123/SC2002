/**
 * Entity.<p>
 * This {@code Manager} class represents a Manager in the system who can create and manage projects,
 * approve or reject officer registrations and applicant applications,
 * handle enquiries, generate reports, and control project visibility.
 * 
 * Inherits common user attributes from the {@link User} class
 */
public class Manager extends User {
    
    /**
     * Constructs a new Manager with the specified personal details.
     *
     * @param nric     the National Registration Identity Card number that uniquely identifies this manager
     * @param name     the full name of the manager
     * @param password the password for authentication
     * @param age      the age of the manager in years
     * @param married  the marital status of the manager (true if married, false otherwise)
     */
    public Manager(String nric, String name, String password, int age, boolean married) {
        super(nric, name, password, age, married);
    }
	/**
	 * Launches the manager's interface display
	 * Delegates to {@link ManagerDisplay#showDisplay()}
	 */
	public void display(){
		ManagerDisplay display = new ManagerDisplay(this);
		display.showDisplay();
}
}
	



	