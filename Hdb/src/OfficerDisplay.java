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
                    controller.bookFlat();
                    break;
                case 6:
                    controller.generateReceipt();
                    break;
                case 7:
                    changeUserPassword(scanner,officer);
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    /*private Officer officer;
    private ApplicantDisplay appDp;
    Scanner sc = new Scanner(System.in);
    
    OfficerDisplay(Officer officer) {
		this.officer = officer;
	}

    public void showDisplay() {
        System.out.println("Continue as?:");
        System.out.println("1. Applicant");
        System.out.println("2. Officer");
        int choice = sc.nextInt();
        if(choice==1){
            appDp = new ApplicantDisplay((Applicant)officer);
            appDp.showDisplay();
        }
        else if(choice==2){
            boolean running = true;
            do {
                System.out.println("========== Officer User Menu ==========");
                System.out.println("|Below are the actions you can take:    |");
                System.out.println("|(1) Register for a Project             |");
                System.out.println("|(2) View Registration Status           |");
                System.out.println("|(3) View Project details               |");
                System.out.println("|(4) View Project Enquiries             |");
                System.out.println("|(5) Reply Project Enquiries            |");
                System.out.println("|(6) Book Flat for Applicant            |");
                System.out.println("|(7) Generate Receipt                   |");
                System.out.println("|(0) Exit                               |");

                int choice2 = sc.nextInt();
                switch(choice2){
                    case 1:
                        officer.registerAsOIC();
                        break;
                    case 2:
                        officer.viewRegistrationStatus();
                        break;
                    case 3: 
                        officer.viewHandledProject();
                        break;
                    case 4:
                        officer.viewProjectEnquiry();
                        break;
                    case 5:
                        officer.replyEnquiry();
                        break;
                    case 6: 
                        officer.bookFlat();
                    case 7:
                        //add in generate receipt logic
                    case 0: 
                    running = false;
                }
                
            } while (running);
            
        }
    }*/
}
}
