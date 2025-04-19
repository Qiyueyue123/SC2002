//reply enquiry will be moved when officer and manager class is settled

/**
 * Entity. <p>
 * This {@code Applicant} class represents an applicant in the system who can apply for projects.
 * Inherits common user attributes from the {@link User} class and maintains a reference to the applicant's current application.
 */
public class Applicant extends User {
    /**
     * The current application associated with this applicant, or {@code null} if none.
     */
    private Application application;

    /**
     * Constructs a new Applicant with the specified personal details.
     *
     * @param nric     the National Registration Identity Card number of the applicant
     * @param name     the full name of the applicant
     * @param password the password for authentication
     * @param age      the age of the applicant
     * @param married  the marital status of the applicant (true if married, false otherwise)
     */
    public Applicant(String nric, String name, String password, int age, boolean married) {
        super(nric, name, password, age, married);
        this.application = null;
    }

    /**
     * Returns the current application associated with this applicant.
     *
     * @return the {@link Application} object, or {@code null} if none
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Sets the current application for this applicant.
     *
     * @param app the {@link Application} to associate with this applicant
     */
    public void setApplication(Application app) {
        this.application = app;
    }
	/**
	 * Launches the applicant's interface display
	 * Delegates to {@link ApplicantDisplay#showDisplay()}
	 */
	public void display() {
		ApplicantDisplay display = new ApplicantDisplay(this);
		display.showDisplay();
	}
}

