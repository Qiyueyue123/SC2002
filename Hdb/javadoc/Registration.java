/**
 * Entity. <p>
 * Represents a registration record linking an {@link Officer} to a {@link Project}.
 * <p>
 * A registration has a status indicating whether it's "Pending", "Approved", or "Rejected".
 */
public class Registration {
    private Officer officer;
    private Project project;
    private String status;

    /**
     * Constructs a new registration with the specified officer and project.
     * <p>
     * The status is set to "Pending" by default.
     *
     * @param o The officer registering.
     * @param p The project being registered to.
     */
    public Registration(Officer o, Project p) {
        officer = o;
        project = p;
        status = "Pending";
    }

    /**
     * Prints the registration details to the console.
     */
    public void print() {
        System.out.println();
        System.out.println("Project Name: " + project.getName());
        System.out.println("Officer Name: " + officer.getName());
        System.out.println("Registration Status: " + status);
        System.out.println();
    }

    /**
     * Gets the project associated with this registration.
     *
     * @return The registered project.
     */
    public Project getProject() {
        return this.project;
    }

    /**
     * Gets the officer associated with this registration.
     *
     * @return The registered officer.
     */
    public Officer getOfficer() {
        return this.officer;
    }

    /**
     * Gets the current status of this registration.
     *
     * @return The registration status ("Pending", "Approved", or "Rejected").
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of this registration.
     *
     * @param stat The new status ("Pending", "Approved", or "Rejected").
     */
    public void setStatus(String stat) {
        this.status = stat;
    }
}
