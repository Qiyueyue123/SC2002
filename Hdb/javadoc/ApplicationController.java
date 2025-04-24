import java.util.ArrayList;
import java.util.List;

/**
 * Controller.<p>
 * This {@code ApplicationController} class provides static utility methods for displaying and retrieving
 * applications from the {@link ApplicationRepository}.
 */
public class ApplicationController {

    /**
     * Displays all applications in the system.
     * If there are no applications, prints a message indicating so.
     */
    public static void showAllApplications() {
        List<Application> all = ApplicationRepository.getAllApplications();
        if (all.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        for (Application app : all) {
            app.print();
            System.out.println();
        }
    }

    /**
     * Displays all applications with withdrawal requests for a specific manager.
     * If there are no such applications, prints a message indicating so.
     *
     * @param manager the {@link Manager} whose projects' withdrawal requests to display
     */
    public static void showWithdrawalRequested(Manager manager) {
        List<Application> requested = ApplicationRepository.getWithdrawalRequested(manager);

        if (requested.isEmpty()) {
            System.out.println("No applications requesting withdrawal.");
        } else {
            System.out.println("Applications requesting withdrawal:");
            for (Application app : requested) {
                System.out.println("NRIC: " + app.getApplicant().getNRIC() + ", Project: " + app.getProject().getName());
            }
        }
    }
  /**
     * Returns an application by the applicant's NRIC.
     *
     * @param nric the NRIC of the applicant
     * @return the {@link Application} with the matching NRIC, or {@code null} if not found
     */
    public static Application getApplicationByNRIC(String nric) {
        return ApplicationRepository.selectApplication(nric);
    }

    /**
     * Creates and adds a new application for the specified applicant and project.
     *
     * @param applicant the {@link Applicant} submitting the application
     * @param proj the {@link Project} being applied for
     * @param flatTypeChoice the type of flat chosen
     * @return the newly created {@link Application}
     */
    public static Application addApplication(Applicant applicant, Project proj,int flatTypeChoice){
         Application app = new Application(applicant, proj);
         app.setFlatType(flatTypeChoice);
         applicant.setApplication(app);
         ApplicationRepository.addApplication(app);
         return app;
     }

    /**
     * Returns all pending applications for a specific manager.
     *
     * @param manager the {@link Manager} to filter applications by
     * @return list of pending {@link Application} objects
     */
    public static List<Application> getManagerPendingApplications(Manager manager){
        return ApplicationRepository.getPendingApplicationsForManager(manager);
    }

    /**
     * Returns all applications in the system.
     *
     * @return list of all {@link Application} objects
     */
    public static List<Application> getAllApplications(){
        return ApplicationRepository.getAllApplications();
    }
    
     /**
     * Returns an application by applicant NRIC and project.
     *
     * @param nric the NRIC of the applicant
     * @param project the {@link Project} applied to
     * @return the matching {@link Application}, or {@code null} if not found
     */
    public static Application getApplicationByNRICAndProject(String nric, Project project) {
        List<Application> applications = getAllApplications();
        for (Application app : applications) {
            if (app.getApplicant().getNRIC().equalsIgnoreCase(nric) &&
                app.getProject().equals(project)) {
                return app;
            }
        }
        return null;
    }
    
     /**
     * Returns all approved applications for an officer's assigned project.
     *
     * @param officer the {@link Officer} whose project to check
     * @return list of approved {@link Application} objects
     */
    public static ArrayList<Application> getApprovedApplications(Officer officer){
        return new ArrayList<>(ApplicationRepository.getAllApplications().stream()
                .filter(a -> a.getProject() == officer.getAssignedProject() && "Successful".equalsIgnoreCase(a.getAppliedStatus())).toList());
    }
}
