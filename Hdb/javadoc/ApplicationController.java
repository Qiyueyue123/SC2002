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
}
