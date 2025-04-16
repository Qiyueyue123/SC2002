
/**
 * Represents an Officer in the system, who is also an Applicant.
 * An Officer may be assigned to a {@link Project} and can perform officer-specific actions
 * via the {@link OfficerController}.
 */
public class Officer extends Applicant {
    /**
     * The project currently assigned to this officer, or {@code null} if none.
     */
    private Project assignedProject;

    /**
     * Constructs an Officer with the specified details.
     *
     * @param nric     the NRIC of the officer
     * @param name     the name of the officer
     * @param password the officer's password
     * @param age      the officer's age
     * @param married  the officer's marital status
     */
    public Officer(String nric, String name, String password, int age, boolean married) {
        super(nric, name, password, age, married);
    }

    /**
     * Returns the project currently assigned to this officer.
     *
     * @return the assigned {@link Project}, or {@code null} if none
     */
    public Project getAssignedProject() {
        return assignedProject;
    }

    /**
     * Assigns a project to this officer.
     *
     * @param assignedProject the {@link Project} to assign
     */
    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
    }
}
