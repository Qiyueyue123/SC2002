import java.util.List;
import java.util.Scanner;

/**
 * Boundary.
 * Provides a console-based user interface for officers to interact with the system.
 * Allows officers to perform various actions such as registering as Officer-in-Charge,
 * viewing assigned projects, replying to enquiries, booking flats, and more.
 * Also allows switching to applicant mode if the officer is also an applicant.
 */
public class OfficerDisplay implements UserDisplay {
    /**
     * The officer currently using the display.
     */
    private Officer officer;

    /**
     * The controller handling officer-related actions.
     */
    private OfficerController controller;

    /**
     * Scanner for reading user input from the console.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs an OfficerDisplay for the specified officer.
     *
     * @param officer the officer using this display
     */
    public OfficerDisplay(Officer officer) {
        this.officer = officer;
        this.controller = new OfficerController(officer);
    }

    /**
     * Displays the main menu and handles user input for officer actions.
     * Allows the officer to switch to applicant mode or perform officer-specific actions.
     */
    @Override
    public void showDisplay() {
        boolean running = true;

        System.out.println("Continue as?:");
        System.out.println("1. Applicant");
        System.out.println("2. Officer");
        int choice = scanner.nextInt();
        if (choice == 1) {
            // Switch to applicant display
            ApplicantDisplay appDp = new ApplicantDisplay((Applicant) officer);
            appDp.showDisplay();
        } else if (choice == 2) {
            // Officer menu loop
            while (running) {
                System.out.println("========== Officer Menu ==========");
                System.out.println("(1) Register as Officer-in-Charge");
                System.out.println("(2) View Assigned Project");
                System.out.println("(3) View Registration Status");
                System.out.println("(4) Reply to Enquiry");
                System.out.println("(5) Book flat for Applicant");
                System.out.println("(6) Generate Receipt");
                System.out.println("(7) Change Password");
                System.out.println("(0) Exit");

                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        controller.registerAsOIC();
                        break;
                    case 2:
                        controller.viewAssignedProject();
                        break;
                    case 3:
                        controller.viewRegistrationStatus();
                        break;
                    case 4:
                        List<Enquiry> enquiries = EnquiryRepository.getAllEnquiries();
                        for (int i = 0; i < enquiries.size(); i++) {
                            System.out.println((i + 1) + ". ");
                            enquiries.get(i).print();
                        }
                        System.out.print("Select enquiry to reply to: ");
                        int idx = scanner.nextInt();
                        scanner.nextLine();
                        if (idx >= 1 && idx <= enquiries.size()) {
                            Enquiry e = enquiries.get(idx - 1);
                            System.out.print("Enter your response: ");
                            String response = scanner.nextLine();
                            controller.replyToEnquiry(e, response);
                            System.out.println("Response submitted.");
                        }
                        break;
                    case 5:
                        controller.bookFlat();
                        break;
                    case 6:
                        controller.generateReceipt();
                        break;
                    case 7:
                        // Calls the default method in UserDisplay interface
                        UserDisplay.super.changeUserPassword(scanner, officer);
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }
}
