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
	System.out.println("|(1) View Enquiries                     |");
	System.out.println("|(2) Create an Enquiry                  |");
	System.out.println("|(3) Edit an Enquiry                    |");
	System.out.println("|(4) Delete an Enquiry                  |");
	System.out.println("|(5) Reply to an Enquiry                |");
	System.out.println("|(0) Exit                               |");
	
	System.out.println("Please enter your choice: ");
	int choice = scan.nextInt();
	
	do {
	switch (choice) {
	
		case 1:
			applicant.viewEnquiry();
			break;
			
		case 2:
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
			
		case 3:
			EnquiryList.showUserEnquiries(applicant);
	    	
	    	System.out.println("Which enquiry would you like to edit? Input ID:");
	    	int enquiryNo = scan.nextInt();
	    	scan.nextLine(); //needs this after every scanInt
	    	Enquiry editedEnquiry = EnquiryList.selectEnquiry(enquiryNo); 
	    	
	    	System.out.println("Write your enquiry:");
	        String msg = scan.nextLine(); 
	        
			applicant.editEnquiry(editedEnquiry, msg);
			break;
			
		case 4:
			EnquiryList.showUserEnquiries(applicant);
	    	
	    	System.out.println("Which enquiry would you like to delete? Input ID:");
	    	enquiryNo = scan.nextInt();
	    	scan.nextLine(); //needs this after every scanInt
	    	Enquiry delEnquiry = EnquiryList.selectEnquiry(enquiryNo);
	    	
			applicant.deleteEnquiry(delEnquiry);
			break;
			
		case 5:
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
