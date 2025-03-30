import java.util.ArrayList;
import java.util.Scanner;

public class ApplicantDisplay implements UserDisplay {
    Scanner scan = new Scanner(System.in);
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
			System.out.println("|(8) Reply to an Enquiry                |");
			System.out.println("|(9) To test approve Withdrawal         |");
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
				if ((!applicant.getApplication().getAppliedStatus().equals("Unsuccessful"))) { //cant they still request withdrawal and submit again if unsuccessful?
					System.out.println("Application options: ");
					System.out.println("(1) Request Withdrawal");
				} else if(applicant.getApplication().getAppliedStatus().equals("Successful")) {
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
			
		case 8:
			//will be moved to manager or officer display
			ArrayList<Enquiry> unansweredEnquiries = EnquiryList.getUnansweredEnquiries();
			
			if (!unansweredEnquiries.isEmpty()) {
				EnquiryList.showUnansweredEnquiries();
				System.out.println("Which enquiry would you like to reply to? Input ID: ");
				
		        	int enquiryNum = scan.nextInt();
		        	scan.nextLine(); //needs this after every scanInt
		        	Enquiry unansweredEnquiry = EnquiryList.selectEnquiry(enquiryNum); //the array starts from 0
					
				System.out.println("Write your reply:");
			        String reply = scan.nextLine();
		        
		        	applicant.replyEnquiry(unansweredEnquiry, reply);
			}
			else {
	    			System.out.println("There are no enquiries to reply.");
	    	}
			
			break;
		case 9:
			//below is the procedure for Officers and Managers
			//will show list of applications first aka like projects order
			//will then ask for the application you wish to view
			//show the details of application chosen
			
			//tests the approve and withdrawal functions
			applicant.approveWithdrawal(applicant.getApplication());
			break;

		case 0:
			running = false;
			break;
	}
	
	} while (running);

    }

}
