import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class OfficerController {
    private Officer officer;
    private Scanner scanner = new Scanner(System.in);

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

        if(RegistrationRepository.hasRegistration(officer,selected)){
            System.out.println("You have already registered for this project");
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

    public void bookFlat() {
        Project assignedProject = officer.getAssignedProject();
        if (assignedProject == null) {
            System.out.println("You are not assigned to any project.");
            return;
        }

        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate projectOpeningDate = LocalDate.parse(assignedProject.getOpeningDate());
            LocalDate projectClosingDate = LocalDate.parse(assignedProject.getClosingDate());

            if (currentDate.isBefore(projectOpeningDate) || currentDate.isAfter(projectClosingDate)) {
                System.out.println("Booking is not allowed at this time. Booking is allowed only between " 
                + projectOpeningDate + " and " + projectClosingDate + ".");
                return;
            }
        } catch (DateTimeParseException e) {
                System.out.println("Error parsing project dates: " + e.getMessage());
                return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Applicant NRIC: ");
        String applicantNRIC = sc.nextLine().trim();
        Application application = ApplicationRepository.getApplicationByNRICAndProject(applicantNRIC, assignedProject);

        if (application == null) {
            System.out.println("No application found for this applicant in your project.");
            return;
        }

        if (!application.getAppliedStatus().equalsIgnoreCase("successful")) {
            System.out.println("Applicant's status is not successful. Cannot proceed with booking.");
            return;
        }

        System.out.println("Choose flat type to book: ");
        System.out.println("1. 2-Room");
        System.out.println("2. 3-Room");
        String flatType;
        int choice = sc.nextInt();
        sc.nextLine();

        if(choice == 1){
            flatType = "2-Room";
            if (assignedProject.getNum2Rooms() > 0){
                assignedProject.minusNum2Rooms();
            }
            else{
                System.out.println("No more " + flatType + " flats available");
                return;
            }
        }
        else{
            flatType = "3-room";
            if (assignedProject.getNum3Rooms() > 0){
                assignedProject.minusNum3Rooms();
            }
            else{
                System.out.println("No more " + flatType + " flats available");
                return;
            }
        }

        application.setAppliedStatus("booked");
        application.setFlatType(choice);

        System.out.println("Flat booked successfully for applicant: " + application.getApplicant().getName());
    }
}
