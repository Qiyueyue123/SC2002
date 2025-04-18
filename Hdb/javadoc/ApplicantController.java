import java.util.ArrayList;

/**
 * Controller. <p>
 * This {@code ApplicantController} class manages actions performed by an {@link Applicant}.
 * Provides methods for viewing, creating, editing, and deleting enquiries,
 * viewing available projects, applying to projects, viewing applied projects,
 * and requesting application withdrawal.
 */
public class ApplicantController extends UserController{
    /**
     * The applicant associated with this controller.
     */
    private final Applicant applicant;

    /**
     * Constructs an ApplicantController for the specified applicant.
     *
     * @param applicant the applicant using this controller
     */
    public ApplicantController(Applicant applicant) {
        this.applicant = applicant;
    }

    /**
     * Displays all enquiries made by the applicant.
     */
    public void viewEnquiries() {
        EnquiryController.showUserEnquiries(applicant);
    }

    /**
     * Creates a new enquiry for the specified project with the given message.
     *
     * @param project the project to enquire about
     * @param msg     the enquiry message
     */
    public void createEnquiry(Project project, String msg) {
        Enquiry e = new Enquiry(applicant, project);
        e.setMessage(msg);
        EnquiryRepository.addEnquiry(e);
    }

    /**
     * Edits the message of an existing enquiry.
     *
     * @param enquiry the enquiry to edit
     * @param msg     the new message
     */
    public void editEnquiry(Enquiry enquiry, String msg) {
        enquiry.setMessage(msg);
    }

    /**
     * Deletes an enquiry from the system.
     *
     * @param enquiry the enquiry to delete
     */
    public void deleteEnquiry(Enquiry enquiry) {
        EnquiryRepository.getAllEnquiries().remove(enquiry);
    }

    /**
     * Returns a list of projects visible and available for the applicant to apply for.
     * Projects are filtered based on applicant's age, marital status, project visibility,
     * flat availability, and whether the applicant is already an officer in charge.
     *
     * @return a list of visible {@link Project} objects, or {@code null} if no projects are available
     */
    public ArrayList<Project> viewProjects() {
        if ((!applicant.isMarried() && applicant.getAge() < 35) || applicant.getAge() < 21) {
            System.out.println("No projects available");
            return null;
        }

        ArrayList<Project> all = ProjectRepository.getAllProjects();
        ArrayList<Project> visible = new ArrayList<>();
        String officerList;
        int i = 0;
        for (Project p : all) {
            officerList = p.getOfficerName();
            if (p.getVisibility() && (p.getNum2Rooms() > 0 || p.getNum3Rooms() > 0) && !(officerList.contains(applicant.getName()))) {
                if (applicant.isMarried() || (!applicant.isMarried() && p.getNum2Rooms() > 0)) {
                    visible.add(p);
                    i++;
                    System.out.println("Project " + i + ":");
                    ProjectDisplay.printProj(p);
                    System.out.println("");
                }
            }
        }
        return visible;
    }

    /**
     * Allows the applicant to apply for a project with a selected flat type.
     * Prevents applying if the applicant already has an application or is an officer registered for the project.
     *
     * @param project        the project to apply for
     * @param flatTypeChoice the flat type selected (e.g., 2 or 3 rooms)
     */
    public void applyProject(Project project, int flatTypeChoice) {
        if (applicant.getApplication() != null) {
            System.out.println("Cannot apply for a second project.");
            return;
        }
        // If applicant is also an officer, check registration
        if (applicant instanceof Officer) {
            Officer o = (Officer) applicant;
            if (RegistrationRepository.hasRegistration(o, project)) {
                System.out.println("You cannot apply for a project you are registered for.");
                return;
            }
        }

        Application app = new Application(applicant, project);
        app.setFlatType(flatTypeChoice);
        applicant.setApplication(app);
        ApplicationRepository.addApplication(app);
        project.addPerson(applicant);

        System.out.println("Successfully applied for " + project.getName());
        app.print();
    }

    /**
     * Displays the applicant's currently applied project and application details.
     * Prints a message if no application is found.
     */
    public void viewAppliedProject() {
        if (ApplicationController.getApplicationByNRIC(applicant.getNRIC()) == null) {
            System.out.println("No applied projects found");
            return;
        }
        ApplicationController.getApplicationByNRIC(applicant.getNRIC()).print();
    }

    /**
     * Requests withdrawal of the applicant's current application if not already requested.
     * Prints appropriate messages based on the request status.
     */
    public void withdrawApplication() {
        Application app = applicant.getApplication();
        if (app != null && !app.getWithdrawalRequest()) {
            app.requestWithdrawal();
            System.out.println("Withdrawal requested.");
        } else {
            System.out.println("Already requested or no application found.");
        }
    }
}
