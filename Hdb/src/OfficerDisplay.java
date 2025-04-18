import java.util.List;
import java.util.Scanner;
public class OfficerDisplay implements UserDisplay{
    private Officer officer;
    private OfficerController controller;
    private Scanner scanner = new Scanner(System.in);

    public OfficerDisplay(Officer officer) {
        this.officer = officer;
        this.controller = new OfficerController(officer);
    }

    @Override
    public void showDisplay() {
        boolean running = true;

        System.out.println("Continue as?:");
        System.out.println("1. Applicant");
        System.out.println("2. Officer");
        int choice = scanner.nextInt();
        if(choice==1){
            ApplicantDisplay appDp = new ApplicantDisplay((Applicant)officer);
            appDp.showDisplay();
        }
        else if(choice==2){
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
                    List<Enquiry> enquiries = EnquiryRepository.getAllEnquiries();
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
                    break;
                case 5:
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
                    }
                    controller.bookFlat();
                    }
                    break;
                case 6:
                    controller.generateReceipt();
                    break;
                case 7:
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
