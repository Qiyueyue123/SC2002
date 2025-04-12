import java.util.Scanner;

public class ManagerDisplay implements UserDisplay {
    private final ManagerController controller;
    private final Scanner scanner;

    public ManagerDisplay(Manager manager, ReportRepository repository) {
        this.scanner = new Scanner(System.in);
        // Pass the repository instance to the controller.
        this.controller = new ManagerController(manager, repository);
    }

    @Override
    public void showDisplay() {
        boolean running = true;
        do {
            System.out.println("=========== Manager User Menu ===========");
            System.out.println("| (1) Create a project                    |");
            System.out.println("| (2) Edit a project                      |");
            System.out.println("| (3) Delete a project                    |");
            System.out.println("| (4) View all my projects                |");
            System.out.println("| (5) Approve/Reject Officer Registration |");
            System.out.println("| (6) Approve/Reject Applicant Application|");
            System.out.println("| (7) View all Enquiries                  |");
            System.out.println("| (8) View Project Enquiries              |");
            System.out.println("| (9) Reply to an Enquiry                 |");
            System.out.println("| (10) Approve/Reject Withdrawal of App   |");
            System.out.println("| (11) Generate Report                    |");
            System.out.println("| (0) Exit                                |");
            System.out.println("==========================================");
            System.out.print("Please enter your choice: ");
            
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    System.out.print("Enter Project Name: ");
                    String projName = scanner.nextLine();
                    System.out.print("Enter Neighborhood: ");
                    String neighborhood = scanner.nextLine();
                    System.out.print("Enter Visibility (true/false): ");
                    boolean visibility = Boolean.parseBoolean(scanner.nextLine());
                    System.out.print("Enter number of 2-Room units: ");
                    int num2Rooms = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter number of 3-Room units: ");
                    int num3Rooms = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Application Opening Date (YYYY-MM-DD): ");
                    String openingDate = scanner.nextLine();
                    System.out.print("Enter Application Closing Date (YYYY-MM-DD): ");
                    String closingDate = scanner.nextLine();
                    System.out.print("Enter available HDB Officer Slots: ");
                    int availableOfficerSlots = Integer.parseInt(scanner.nextLine());
                    controller.createProject(projName, neighborhood, visibility, num2Rooms, num3Rooms, openingDate, closingDate, availableOfficerSlots);
                    break;
                case 2:
                    controller.editProject();
                    break;
                case 3:
                    controller.deleteProject();
                    break;
                case 4:
                    controller.viewProjects();
                    break;
                case 5:
                    controller.approveOrRejectOfficerRegistration();
                    break;
                case 6:
                    controller.approveOrRejectApplicantApplication();
                    break;
                case 7:
                    controller.viewAllEnquiries();
                    break;
                case 8:
                    controller.viewProjectEnquiries();
                    break;
                case 9:
                    controller.replyToEnquiry();
                    break;
                case 10:
                    controller.approveOrRejectWithdrawal();
                    break;
                case 11:
                    controller.generateReport();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (running);
    }
}
