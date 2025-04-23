import java.util.List;
import java.util.Scanner;

/**
 * Boundary. <p>
 * This {@code OfficerDisplay} class handles the display and menu interactions for an Officer user.
 * It acts as the user interface for officers to perform actions such as registering
 * as Officer-in-Charge (OIC), viewing project assignments, replying to enquiries,
 * booking flats, and generating receipts.
 * <p>
 * It also allows officers to continue as an Applicant if applicable.
 * Implements {@link UserDisplay}.
 */
public class OfficerDisplay implements UserDisplay {
    /**
     * The officer currently using the display.
     */
    private Officer officer;
    /**
     * The controller that handles officer-related actions.
     */
    private OfficerController controller;
     /**
     * Scanner for reading user input from the console.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs an OfficerDisplay with a specific Officer.
     * Initializes the corresponding OfficerController for handling logic.
     *
     * @param officer the officer using this display interface
     */
    public OfficerDisplay(Officer officer) {
        this.officer = officer;
        this.controller = new OfficerController(officer);
    }

    /**
     * Shows the display and handles user input for officer functionalities.
     * Offers the user a menu to either continue as an Applicant or Officer.
     * Officer functions include project registration, viewing assigned projects,
     * replying to enquiries, booking flats, generating receipts, and changing passwords.
     */
    @Override
    public void showDisplay() {
        boolean running = true;

        System.out.println("Continue as?:");
        System.out.println("1. Applicant");
        System.out.println("2. Officer");
        int choice = scanner.nextInt();
        if (choice == 1) {
            ApplicantDisplay appDp = new ApplicantDisplay((Applicant) officer);
            appDp.showDisplay();
        } else if(choice==2){
            while (running) {
            System.out.println("========== Officer Menu ==========");
            System.out.println("(1) Register as Officer-in-Charge");
            System.out.println("(2) View Assigned Project");
            System.out.println("(3) View Registration Status");
            System.out.println("(4) View Project Enquiries");
            System.out.println("(5) Reply to Enquiry");
            System.out.println("(6) Book flat for Applicant");
            System.out.println("(7) Generate Receipt");
            System.out.println("(8) Change Password");
            System.out.println("(0) Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

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
                    EnquiryController.showProjectEnquiries(officer.getAssignedProject());
                    break;
                case 5:
                    List<Enquiry> enquiries = EnquiryRepository.getProjectEnquiries(officer.getAssignedProject());
                    if(enquiries.isEmpty()){
                        System.out.println("There is no enquiries for this project");
                    }
                    else{
                        for (int i = 0; i < enquiries.size(); i++) {
                            System.out.println((i + 1) + ". ");
                            enquiries.get(i).print();
                        }
                        System.out.print("Select enquiry to reply to: ");
                        int idx = scanner.nextInt(); scanner.nextLine();
                        if (idx >= 1 && idx <= enquiries.size()) {
                            Enquiry e = enquiries.get(idx - 1);
                            System.out.print("Enter your response: ");
                            String response = scanner.nextLine();
                            controller.replyToEnquiry(e, response);
                            System.out.println("Response submitted.");
                        }
                        else{
                            System.out.println("Invalid selection.");
                        }
                    }
                    break;
                case 6:
                    List<Application> approvedApps = controller.getApprovedApplications();
                    int i = 1;
                    if(approvedApps.isEmpty()){
                        System.out.println("Currently no applications awaiting booking.");
                    }
                    else{
                        System.out.println("Approved applications awaiting for booking:");
                    for(Application a: approvedApps){
                        System.out.println("Application " + i + ":");
                        System.out.println("Applicant Name: " + a.getApplicant().getName());
                        System.out.println("Applicant NRIC: " + a.getApplicant().getNRIC());
                        System.out.println("Project Name: " + a.getProject().getName());
                        System.out.println("Applied flat type: " + a.getFlatType() + "-Room");
                        System.out.println("");
                        i++;
                    }
                    controller.bookFlat();
                    }
                    break;
                case 7:
                    controller.generateReceipt();
                    break;
                case 8:
                    changeUserPassword(scanner,officer);
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
