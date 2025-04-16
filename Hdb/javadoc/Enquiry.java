/**
 * Entity. <p>
 * Represents an enquiry made by an applicant regarding a specific project.
 * Each enquiry has a unique ID, a message, an optional response, and tracks whether it has been answered.
 */
public class Enquiry {
    /**
     * Static counter to assign unique IDs to each enquiry instance.
     */
    private static int instanceCount = 1;

    /**
     * Unique identifier for this enquiry.
     */
    private int id;

    /**
     * The message content of the enquiry.
     */
    private String message;

    /**
     * The response to the enquiry, if any.
     */
    private String response;

    /**
     * The applicant who made the enquiry.
     */
    private Applicant user;

    /**
     * Flag indicating whether the enquiry has been answered.
     */
    private boolean answered;

    /**
     * The project to which this enquiry relates.
     */
    private Project project;

    /**
     * Constructs a new enquiry for the given applicant and project.
     * Automatically assigns a unique ID and initializes the enquiry as unanswered.
     *
     * @param user    the {@link Applicant} who made the enquiry
     * @param project the {@link Project} the enquiry is about
     */
    Enquiry(Applicant user, Project project) {
        this.id = instanceCount++;
        this.message = null;
        this.response = null;
        this.user = user;
        this.project = project;
        this.answered = false;
    }

    /**
     * Returns the unique ID of this enquiry.
     *
     * @return the enquiry ID
     */
    public int getID() {
        return id;
    }

    /**
     * Sets the message content of this enquiry.
     *
     * @param message the enquiry message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the message content of this enquiry.
     *
     * @return the enquiry message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the response to this enquiry.
     *
     * @return the response message, or {@code null} if unanswered
     */
    public String getResponse() {
        return response;
    }

    /**
     * Sets the response to this enquiry and marks it as answered.
     *
     * @param response the response message
     */
    public void setResponse(String response) {
        this.response = response;
        this.answered = true;
    }

    /**
     * Returns the applicant who made this enquiry.
     *
     * @return the {@link Applicant} user
     */
    public Applicant getUser() {
        return user;
    }

    /**
     * Returns the project associated with this enquiry.
     *
     * @return the {@link Project} related to the enquiry
     */
    public Project getProject() {
        return project;
    }

    /**
     * Returns whether this enquiry has been answered.
     *
     * @return {@code true} if answered, {@code false} otherwise
     */
    public boolean isAnswered() {
        return answered;
    }

    /**
     * Sets the answered status of this enquiry.
     *
     * @param answered {@code true} to mark as answered, {@code false} otherwise
     */
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    /**
     * Prints the enquiry details to the console.
     * Displays the enquiry ID, project name, applicant name, message, and response if answered.
     */
    public void print() {
        System.out.println("[ID " + id + "] " + project.getName() + " | " + user.getName() + ": " + message);
        if (this.isAnswered()) {
            System.out.println("| Response: " + response);
        }
    }
}
