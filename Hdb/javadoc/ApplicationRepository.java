import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity. <p>
 * This {@code ApplicationRepository} class is for managing applications within the system.
 * Provides static methods to add, retrieve, filter, and query applications.
 */
public class ApplicationRepository {
    /**
     * The static list that stores all applications.
     */
    private static List<Application> applications = new ArrayList<>();

    /**
     * Adds a new application to the repository.
     *
     * @param app the {@link Application} to add
     */
    public static void addApplication(Application app) {
        applications.add(app);
    }

    /**
     * Returns all applications stored in the repository.
     *
     * @return the list of all {@link Application} objects
     */
    public static List<Application> getAllApplications() {
        return applications;
    }

    /**
     * Replaces the current list of applications with the provided list.
     *
     * @param appl the new list of {@link Application} objects to set
     */
    public static void setApplications(ArrayList<Application> appl) {
        applications = appl;
    }

    /**
     * Removes a specific application from the repository.
     *
     * @param app the {@link Application} to remove
     */
    public static void deleteApplication(Application app){
        applications.remove(app);
    }

    /**
     * Finds an application by the applicant's NRIC.
     *
     * @param nric the NRIC of the applicant to find
     * @return the {@link Application} with the matching NRIC, or {@code null} if not found
     */
    public static Application selectApplication(String nric) {
        return applications.stream()
                .filter(app -> app.getApplicant().getNRIC().equalsIgnoreCase(nric))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns all applications with withdrawal requests for a specific manager.
     *
     * @param manager the {@link Manager} whose projects' withdrawal requests to retrieve
     * @return a list of applications with withdrawal requests for the manager's projects
     */
    public static List<Application> getWithdrawalRequested(Manager manager) {
        return applications.stream()
                .filter(a -> a.getWithdrawalRequest() && a.getProject().getManager().equals(manager))
                .collect(Collectors.toList());
    }

    /**
     * Returns all pending applications for projects managed by a specific manager.
     *
     * @param manager the {@link Manager} whose pending applications to retrieve
     * @return a list of pending applications for the manager's projects
     */
    public static List<Application> getPendingApplicationsForManager(Manager manager) {
        return applications.stream()
                .filter(app -> app.getProject().getManager().equals(manager))
                .filter(app -> app.getAppliedStatus().equalsIgnoreCase("Pending"))
                .collect(Collectors.toList());
    }

    /**
     * Removes all applications from the repository.
     */
    public static void clearAll() {
        applications.clear();
    }

    /**
     * Finds an application by the applicant's NRIC and project.
     *
     * @param nric the NRIC of the applicant
     * @param project the {@link Project} to find the application for
     * @return the {@link Application} with the matching NRIC and project, or {@code null} if not found
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
}
