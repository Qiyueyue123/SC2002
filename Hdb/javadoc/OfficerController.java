import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Controller
 * OfficerController manages officer-related actions such as registering as Officer-in-Charge,
 * viewing assigned projects, checking registration status, replying to enquiries, booking flats,
 * and generating receipts.
 */
public class OfficerController {
    /**
     * The officer associated with this controller.
     */
    private Officer officer;

    /**
     * Scanner for reading user input.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs an OfficerController for the specified officer.
     *
     * @param officer the officer associated with this controller
     */
    public OfficerController(Officer officer) {
        this.officer = officer;
    }

    /**
     * Allows the officer to register as Officer-in-Charge (OIC) for an available project.
     * Checks for available projects, registration status, and project dates before submitting a registration.
     */
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

        if (RegistrationRepository.hasRegistration(officer, selected)) {
            System.out.println("You have already registered for this project");
            return;
        }
        if (ApplicationRepository.getApplicationByNRICAndProject(officer.getNRIC(), selected) != null) {
            System.out.println("You cannot register for a project you have applied for");
        }
        LocalDate currentDate = LocalDate.now();
        LocalDate projectOpeningDate = LocalDate.parse(selected.getOpeningDate());
        LocalDate projectClosingDate = LocalDate.parse(selected.getClosingDate());

        if (currentDate.isBefore(projectOpeningDate) || currentDate.isAfter(projectClosingDate)) {
            System.out.println("Registration is not allowed at this time. Registration is allowed only between "
                    + projectOpeningDate + " and " + projectClosingDate + ".");
            return;
        }
        Registration reg = new Registration(officer, selected);
        RegistrationRepository.addRegistration(reg);
        System.out.println("Registration submitted and pending manager approval.");
    }

    /**
     * Displays the project currently assigned to the officer, if any.
     */
    public void viewAssignedProject() {
        if (officer.getAssignedProject() != null) {
            System.out.println("Assigned Project: ");
            ProjectDisplay.printProj(officer.getAssignedProject());
        } else {
            System.out.println("No assigned project.");
        }
    }

    /**
     * Displays the registration status for all projects the officer has registered for.
     */
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

    /**
     * Sets the response for a given enquiry.
     *
     * @param enquiry  the enquiry to respond to
     * @param response the response text
     */
    public void replyToEnquiry(Enquiry enquiry, String response) {
        enquiry.setResponse(response);
    }

    /**
     * Books a flat for an applicant in the officer's assigned project.
     * Checks project assignment, application status, and flat availability before booking.
     */
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

        if (choice == 1) {
            flatType = "2-Room";
            if (assignedProject.getNum2Rooms() > 0) {
                assignedProject.minusNum2Rooms();
            } else {
                System.out.println("No more " + flatType + " flats available");
                return;
            }
        } else {
            flatType = "3-room";
            if (assignedProject.getNum3Rooms() > 0) {
                assignedProject.minusNum3Rooms();
            } else {
                System.out.println("No more " + flatType + " flats available");
                return;
            }
        }

        application.setAppliedStatus("booked");
        application.setFlatType(choice);

        System.out.println("Flat booked successfully for applicant: " + application.getApplicant().getName());
    }

    /**
     * Generates and displays a receipt for applicants who have booked a flat in the officer's assigned project.
     * Allows the officer to select an applicant and prints their booking and personal details.
     */
    public void generateReceipt() {
        Project assignedProject = officer.getAssignedProject();
        ArrayList<Application> bookedApplications = new ArrayList<>();
        for (Application a : ApplicationRepository.getAllApplications()) {
            if ((a.getProject() == assignedProject) && (a.getAppliedStatus().equals("booked"))) {
                bookedApplications.add(a);
            }
        }
        if (bookedApplications.isEmpty()) {
            System.out.println("No booked flats for your assigned project. ");
            System.out.println();
            return;
        }
        int i = 1;
        int choice = 0;
        System.out.println("Select Applicant to generate reciept for: ");
        for (Application a : bookedApplications) {
            System.out.println(i + ". " + a.getApplicant().getName() + ", " + a.getApplicant().getNRIC() + ", " + a.getFlatType());
            i++;
        }
        System.out.println("0. Return to homepage");
        if (choice == 0) {
            return;
        }
        choice = scanner.nextInt();
        scanner.nextLine();
        Application a = bookedApplications.get(choice - 1);

        String marriedStatus = a.getApplicant().isMarried() ? "Married" : "Single";
        System.out.println("============ Receipt ============");
        System.out.println("Applicant Details:");
        System.out.println("Applicant: " + a.getApplicant().getName());
        System.out.println("NRIC: " + a.getApplicant().getNRIC());
        System.out.println("Age: " + a.getApplicant().getAge());
        System.out.println("Marital Status: " + marriedStatus);
        System.out.println("------------------------------");
        System.out.println("Booked Project Details:");
        System.out.println("Name: " + a.getProject().getName());
        System.out.println("Neighborhood: " + a.getProject().getNeighborhood());
        System.out.println("Flat Type: " + a.getFlatType() + " room");
        System.out.println();
    }
}
