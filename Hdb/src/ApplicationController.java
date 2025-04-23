import java.util.ArrayList;
import java.util.List;

public class ApplicationController {

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
    public static Application getApplicationByNRIC(String nric) {
        return ApplicationRepository.selectApplication(nric);
    }

    public static Application addApplication(Applicant applicant, Project proj,int flatTypeChoice){
        Application app = new Application(applicant, proj);
        app.setFlatType(flatTypeChoice);
        applicant.setApplication(app);
        ApplicationRepository.addApplication(app);
        return app;
    }

    public static List<Application> getManagerPendingApplications(Manager manager){
        return ApplicationRepository.getPendingApplicationsForManager(manager);
    }

    public static List<Application> getAllApplications(){
        return ApplicationRepository.getAllApplications();
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

    public static ArrayList<Application> getApprovedApplications(Officer officer){
        return new ArrayList<>(ApplicationRepository.getAllApplications().stream()
                .filter(a -> a.getProject() == officer.getAssignedProject() && "Successful".equalsIgnoreCase(a.getAppliedStatus())).toList());
    }
}
