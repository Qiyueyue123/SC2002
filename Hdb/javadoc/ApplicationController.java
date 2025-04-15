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

    public static void showPendingApplicationsForManager(Manager manager) {
        List<Application> pending = ApplicationRepository.getPendingApplicationsForManager(manager);

        if (pending.isEmpty()) {
            System.out.println("No pending applications for your projects.");
        } else {
            System.out.println("Pending Applications:");
            for (Application app : pending) {
                System.out.println("Applicant: " + app.getApplicant().getName() + ", Project: " + app.getProject().getName());
            }
        }
    }

    public static Application getApplicationByNRIC(String nric) {
        return ApplicationRepository.selectApplication(nric);
    }
}
