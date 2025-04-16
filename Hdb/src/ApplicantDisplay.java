import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ApplicantDisplay implements UserDisplay {
	private final Applicant applicant;
    private final ApplicantController controller;
    private Scanner scan = new Scanner(System.in);

    public ApplicantDisplay(Applicant applicant) {
        this.applicant = applicant;
        this.controller = new ApplicantController(applicant);
		//should officer controller implement  
    }

    @Override
    public void showDisplay() {
        boolean running = true;
        while (running) {
            System.out.println("========== Applicant Menu ==========");
            System.out.println("(1) View Projects");
            System.out.println("(2) Apply for Project");
            System.out.println("(3) View Applied Project");
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

    private int askFlatType() {
        System.out.print("Enter flat type (2 or 3): ");
        return scan.nextInt();
    }

    private void editOrDeleteEnquiry(boolean isEdit) {
        EnquiryController.showUserEnquiries(applicant);
        List<Enquiry> enquiries = EnquiryRepository.getUserEnquiries(applicant);
        if (!enquiries.isEmpty()) {
            System.out.print("Select enquiry ID: ");
            int id = scan.nextInt(); scan.nextLine();
            Enquiry e = EnquiryList.selectEnquiry(id);
            if (e != null) {
                if (isEdit) {
                    System.out.print("New message: ");
                    String msg = scan.nextLine();
                    controller.editEnquiry(e, msg);
                } else {
                    controller.deleteEnquiry(e);
                }
            }
        }
    }
	//Old code
    /*Scanner scan = new Scanner(System.in);
	private Applicant applicant;
	
	ApplicantDisplay(Applicant applicant) {
		this.applicant = applicant;
	}

    @Override
    public void showDisplay() {
		boolean running = true;

		do {
    
			System.out.println("========== Applicant User Menu ==========");
			System.out.println("|Below are the actions you can take:    |");
			System.out.println("|(1) View Projects List                 |");
			System.out.println("|(2) Apply For a Project                |");
			System.out.println("|(3) View Applied Project Status        |");
			System.out.println("|(4) View Enquiries                     |");
			System.out.println("|(5) Create an Enquiry                  |");
			System.out.println("|(6) Edit an Enquiry                    |");
			System.out.println("|(7) Delete an Enquiry                  |");
			System.out.println("|(0) Exit                               |");
			
	System.out.println();
	System.out.println("Please enter your choice: ");
	int choice = scan.nextInt();
	scan.nextLine();
	
	switch (choice) {
		case 1:
			applicant.viewProjects();
			break;

		case 2:
			applicant.applyProject();
			break;
		
		case 3:
			applicant.viewAppliedProject();
			
			if (!(applicant.getApplication()==null)) { //execute only if application exist				
			
				if(applicant.getApplication().getWithdrawalRequest()) { //if true, then a withdrawal was requested
					System.out.println("Check Withdrawal Status:");
					if (applicant.getApplication().getWithdrawalStatus().equals("Pending")) {
						System.out.println("Your withdrawal is still pending.");
						System.out.println();
					}
					else if (applicant.getApplication().getWithdrawalStatus().equals("Success")) {
						System.out.println("Your withdrawal has been approved!");
						applicant.deleteApplication();
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
				
				
				System.out.println("Select 0 to return to homepage");
				System.out.println("Application options: ");
				System.out.println("(1) Request Withdrawal");
				if(applicant.getApplication().getAppliedStatus().equals("Successful")) {
					System.out.println("(2) Request to Book Flat");
				} 
				
				boolean subRunning = true;
				
				do {
					System.out.println();
					System.out.println("Please enter your choice for the sub-menu: ");
					int subChoice = scan.nextInt();
					scan.nextLine();
					
					switch (subChoice) { //this will have 2 ensure that the other choices cannot be selected
					case 1:
						applicant.withdrawApplication();
						break;
					case 2:
						//book application
						break;
					case 0:
						subRunning = false;
						break;
					}
					
					
				} while (subRunning);
				
				System.out.println();
			}

			break;
		case 4:
			applicant.viewEnquiry();
			break;
			
		case 5:
			ArrayList<Project> userProjs = ProjectList.getAllProjects();
	    	
	    	if (!ProjectList.isEmpty()) {
		    	ProjectList.showAllProjects();
		    	
		    	System.out.println("Which project would you like to make an enquiry for?");
		    	int projNo = scan.nextInt();
		    	scan.nextLine(); //needs this after every scanInt
		    	Project chosenProj = userProjs.get(projNo-1); //selects the project, will be changed when ID is added to project
		    	
		    	System.out.println("Write your enquiry:");
		        String msg = scan.nextLine(); 
		        
		    	applicant.createEnquiry(chosenProj, msg);
		    	}
	    	else {
	    		System.out.println("There are no projects available for enquiry.");
	    	}
			
			break;
			
		case 6:
			EnquiryList.showUserEnquiries(applicant);
			ArrayList<Enquiry> userEnquiries = EnquiryList.getUserEnquiries(applicant);	
			
			if (!userEnquiries.isEmpty()) {
				
			    	System.out.println("Which enquiry would you like to edit? Input ID:");
			    	int enquiryNo = scan.nextInt();
			    	scan.nextLine(); //needs this after every scanInt
			    	Enquiry editedEnquiry = EnquiryList.selectEnquiry(enquiryNo); 
			    	
			    	if (editedEnquiry != null) {
				    	System.out.println("Write your enquiry:");
				        String msg = scan.nextLine(); 
						applicant.editEnquiry(editedEnquiry, msg);
			    	}
			}
			break;
		case 7:
			EnquiryList.showUserEnquiries(applicant);
			userEnquiries = EnquiryList.getUserEnquiries(applicant);	
	    	
			if (!userEnquiries.isEmpty()) {
			    System.out.println("Which enquiry would you like to delete? Input ID:");
			    int enquiryNo = scan.nextInt();
			    scan.nextLine(); //needs this after every scanInt
			    Enquiry delEnquiry = EnquiryList.selectEnquiry(enquiryNo);
		    	
			    if (delEnquiry != null) {
			    	applicant.deleteEnquiry(delEnquiry);
			    }
			}
			break;

		case 0:
			running = false;
			break;
	}
	
	} while (running);

    }*/

}
