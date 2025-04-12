import java.util.List;
import java.util.Scanner;

public class OfficerController {
    private final Officer officer;
    private final Scanner scanner = new Scanner(System.in);

    public OfficerController(Officer officer) {
        this.officer = officer;
    }

    public void registerAsOIC() {
        List<Project> availableProjects = ProjectRepository.getAllProjects().stream()
                .filter(p -> p.getAvailOfficerSlots() > 0)
                .toList();

        if (availableProjects.isEmpty()) {
            System.out.println("No available projects for officer registration.");
            return;
        }

        System.out.println("Available Projects:");
        for (int i = 0; i < availableProjects.size(); i++) {
            System.out.println((i + 1) + ". " + availableProjects.get(i).getName());
        }

        System.out.print("Choose project to request OIC role: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > availableProjects.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Project selected = availableProjects.get(choice - 1);

        if (RegistrationRepository.hasPendingRegistration(officer, selected)) {
            System.out.println("You already have a pending registration for this project.");
            return;
        }

        Registration reg = new Registration(officer, selected);
        RegistrationRepository.addRegistration(reg);
        System.out.println("Registration submitted and pending manager approval.");
    }

    public void viewAssignedProject() {
        if (officer.getAssignedProject() != null) {
            System.out.println("Assigned Project: ");
            ProjectDisplay.printProj(officer.getAssignedProject());
        } else {
            System.out.println("No assigned project.");
        }
    }

    public void viewRegistrationStatus() {
        List<Registration> all = RegistrationRepository.getAllRegistrations();
        boolean found = false;
        for (Registration r : all) {
            if (r.getOfficer().equals(officer)) {
                System.out.println("Project: " + r.getProject().getName() + " | Status: " + r.getStatus());
                found = true;
            }
        }
        if (!found) System.out.println("No registration found.");
    }

    public void replyToEnquiry(Enquiry enquiry, String response) {
        enquiry.setResponse(response);
    }
}
