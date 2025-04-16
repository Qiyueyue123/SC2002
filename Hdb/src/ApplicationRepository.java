import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationRepository {
    private static List<Application> applications = new ArrayList<>();

    public static void addApplication(Application app) {
        applications.add(app);
    }

    public static List<Application> getAllApplications() {
        return applications;
    }

    public static void setApplications(ArrayList<Application> appl) {
        applications = appl;
    }

    public static void deleteApplication(Application app){
        applications.remove(app);
    }

    public static Application selectApplication(String nric) {
        return applications.stream()
                .filter(app -> app.getApplicant().getNRIC().equalsIgnoreCase(nric))
                .findFirst()
                .orElse(null);
    }

    public static List<Application> getWithdrawalRequested(Manager manager) {
        return applications.stream()
                .filter(a -> a.getWithdrawalRequest() && a.getProject().getManager().equals(manager))
                .collect(Collectors.toList());
    }

    public static List<Application> getPendingApplicationsForManager(Manager manager) {
        return applications.stream()
                .filter(app -> app.getProject().getManager().equals(manager))
                .filter(app -> app.getAppliedStatus().equalsIgnoreCase("Pending"))
                .collect(Collectors.toList());
    }

    public static boolean hasUserAppliedToProject(String nric, Project project) {
        List<Application> applications = getAllApplications();
        for (Application app : applications) {
            if (app.getApplicant().getNRIC().equalsIgnoreCase(nric)
                && app.getProject().equals(project)) {
                return true;
            }
        }
        return false;
    }

    public static void clearAll() {
        applications.clear();
    }

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
