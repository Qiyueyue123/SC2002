import java.util.List;
import java.util.Scanner;

public class ManagerController {
    private final Manager manager;
    private final Scanner scanner = new Scanner(System.in);

    public ManagerController(Manager manager) {
        this.manager = manager;
    }

    public void viewOwnProjects() {
        List<Project> projects = ProjectController.getUserProjects(manager);
        if (projects.isEmpty()) {
            System.out.println("You have no projects assigned.");
            return;
        }
        ProjectDisplay.showUserProjects(manager);
    }

    public void viewPendingApplications() {
        ApplicationController.showPendingApplicationsForManager(manager);
    }

    public void viewWithdrawalRequests(Manager manager) {
        ApplicationController.showWithdrawalRequested(manager);
    }

    public void viewOfficerRegistrations() {
        List<Project> projects = ProjectController.getUserProjects(manager);
        for (Project project : projects) {
            RegistrationController.showPendingRegistrations(project);
        }
    }

    public void approveOrRejectOfficerRegistration() {
        List<Project> projects = ProjectController.getUserProjects(manager);
        for (Project project : projects) {
            List<Registration> pending = RegistrationRepository.getPendingRegistrations(project);
            for (Registration reg : pending) {
                System.out.println("Officer: " + reg.getOfficer().getName() + ", Project: " + project.getName());
                System.out.print("Approve (1) / Reject (2): ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    RegistrationController.approveRegistration(reg);
                } else {
                    RegistrationController.rejectRegistration(reg);
                }
            }
        }
    }

    public void approveOrRejectWithdrawalRequests() {
        List<Application> requested = ApplicationRepository.getWithdrawalRequested(manager);
        for (Application app : requested) {
            if (app.getProject().getManager().equals(manager)) {
                System.out.println("Withdrawal request from: " + app.getApplicant().getName());
                System.out.print("Approve (1) / Reject (2): ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    app.setWithdrawalStatus("Success");
                } else {
                    app.setWithdrawalStatus("Failed");
                }
            }
        }
    }
}
