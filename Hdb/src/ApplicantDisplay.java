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
	System.out.println("|(0) Exit                               |");
	
	System.out.println("Please enter your choice: ");
	int choice = scan.nextInt();
	
	do {
	switch (choice) {
		case 1:
			applicant.viewProjects();
			break;

		case 2:
			applicant.applyProject();
			break;
		
		case 3:
			applicant.viewAppliedProject();
			System.out.println("Select 0 to return to homepage");
			if ((!applicant.application.getAppliedStatus().equals("Unapplied")) && (!applicant.application.getAppliedStatus().equals("Unsuccessful"))) {
				System.out.println("Application options: ");
				System.out.println("(1) Request Withdrawal");
			} else if(applicant.application.getAppliedStatus().equals("Successful")) {
				System.out.println("(2) Request to Book Flat");
			}
			//add logic for checking and switches to req withdraw, book 
			System.out.println();
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

		
	}
	
	System.out.println("Please enter your choice: ");
	choice = scan.nextInt();
	
	} while (choice != 0);
    }

}
