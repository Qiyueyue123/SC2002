import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/**
 * Boundary. <p>
 * This {@code ApplicantDisplay} class provides a console-based user interface for applicants to interact with the system.
 * Allows applicants to view and apply for projects, manage their applications,
 * view and manage enquiries, change their password, and withdraw applications.
 */
public class ApplicantDisplay implements UserDisplay {

    /**
     * The applicant associated with this display.
     */
    private final Applicant applicant;

    /**
     * The controller handling applicant actions.
     */
    private final ApplicantController controller;

    /**
     * Scanner for reading user input from the console.
     */
    private Scanner scan = new Scanner(System.in);

    /**
     * Constructs an ApplicantDisplay for the specified applicant.
     *
     * @param applicant the applicant using this display
     */
    public ApplicantDisplay(Applicant applicant) {
        this.applicant = applicant;
        this.controller = new ApplicantController(applicant);
    }

    /**
     * Displays the main menu and handles user input for applicant actions.
     * Allows the applicant to view and apply for projects, manage their application,
     * view, create, edit, and delete enquiries, change password, and withdraw applications.
     */

    @Override
    public void showDisplay() {
        boolean running = true;
        while (running) {
            System.out.println("========== Applicant Menu ==========");
            System.out.println("(1) View Projects");
            System.out.println("(2) Apply for Project");
            System.out.println("(3) View Applied Project/View Withdrawal Outcome");
            System.out.println("(4) View Enquiries");
            System.out.println("(5) Create Enquiry");
            System.out.println("(6) Edit Enquiry");
            System.out.println("(7) Delete Enquiry");
			System.out.println("(8) Change Password");
			System.out.println("(9) Withdraw Application");
            System.out.println("(0) Exit");
            int choice = scan.nextInt(); scan.nextLine();

            switch (choice) {
                case 1 -> controller.viewProjects();
                case 2 -> {
                    ArrayList<Project> projects = controller.viewProjects();
                    if (projects != null && !projects.isEmpty()) {
                        System.out.print("Select project index: ");
                        int idx = scan.nextInt(); scan.nextLine();
						if(idx < 1 || idx > projects.size()){
							System.out.println("Invalid Index.");
						}else{
                        	Project selected = projects.get(idx - 1);

                       	 	int flatType = applicant.isMarried() ? askFlatType() : 2;
                        	controller.applyProject(selected, flatType);
						}
                    }
                }
                case 3 -> {
					controller.viewAppliedProject();
					if (!(applicant.getApplication()==null)) { //execute only if application exist				
			
						if(applicant.getApplication().getWithdrawalRequest()) { //if true, then a withdrawal was requested
							System.out.println("Check Withdrawal Status:");
							if (applicant.getApplication().getWithdrawalStatus().equals("Pending")) {
								System.out.println("Your withdrawal is still pending.");
								System.out.println();
							}
							else if (applicant.getApplication().getWithdrawalStatus().equals("Success")) {
								System.out.println("Your withdrawal has been approved!");
								ApplicationRepository.deleteApplication(applicant.getApplication());
								applicant.setApplication(null);
								System.out.println("You can make another application.");
								System.out.println();
								break;
							}
							else if (applicant.getApplication().getWithdrawalStatus().equals("Failed")) {
								System.out.println("Your withdrawal has been rejected!");
								applicant.getApplication().resetWithdrawalStatus();
								System.out.println("You can make another request.");
								System.out.println();
							}
						}
					}
				}
                case 4 -> controller.viewEnquiries();
                case 5 -> {
                    ArrayList<Project> projects = ProjectRepository.getAllProjects();
                    ProjectDisplay.showAllProjects();
                    System.out.print("Project number: ");
                    int projNo = scan.nextInt(); scan.nextLine();
					if(projNo < 1 || projNo > projects.size()){
						System.out.println("Invalid project number!");
					}else{
						System.out.print("Message: ");
                    	String msg = scan.nextLine();
                    	controller.createEnquiry(projects.get(projNo - 1), msg);
                        System.out.println("Enquiry successfully created");
					}
                }
                case 6 -> editOrDeleteEnquiry(true);
                case 7 -> editOrDeleteEnquiry(false);
				case 8 -> changeUserPassword(scan, applicant);
				case 9 -> {
					if(applicant.getApplication() == null){
						System.out.println("No application found.");
						return;
					}
					controller.viewAppliedProject();
					int withdraw;
					while(true){
						try{
							System.out.println("Are you sure you want to withdraw?(1 for Yes/ 2 for No):");
							withdraw = scan.nextInt(); scan.nextLine();
							break;
						}catch(InputMismatchException e){
							System.out.println("Invalid choice");
							scan.nextLine();
						};
					}
					if(withdraw == 1){
						controller.withdrawApplication();
					}
					else if(withdraw == 0){
						return;
					}
				}
                case 0 -> running = false;
            }
        }
    }
 	/**
     * Prompts the user to enter a flat type (2 or 3) and returns the selection.
     *
     * @return the selected flat type as an integer
     */
    private int askFlatType() {
        System.out.print("Enter flat type (2 or 3): ");
        return scan.nextInt();
    }
	 /**
     * Handles editing or deleting an enquiry for the applicant.
     * If {@code isEdit} is true, edits the selected enquiry; otherwise, deletes it.
     *
     * @param isEdit {@code true} to edit an enquiry, {@code false} to delete
     */
    private void editOrDeleteEnquiry(boolean isEdit) {
        EnquiryController.showUserEnquiries(applicant);
        List<Enquiry> enquiries = EnquiryRepository.getUserEnquiries(applicant);
        if (!enquiries.isEmpty()) {
            System.out.print("Select enquiry ID: ");
            int id = scan.nextInt(); scan.nextLine();
            Enquiry e = EnquiryController.getEnquiryById(id);
            if (e != null) {
                if (isEdit) {
                    System.out.print("New message: ");
                    String msg = scan.nextLine();
                    controller.editEnquiry(e, msg);
                    System.out.println("Enquiry succesfully edited");
                } else {
                    controller.deleteEnquiry(e);
                    System.out.println("Enquiry successfully deleted");
                }
            }
        }
	}
}
	