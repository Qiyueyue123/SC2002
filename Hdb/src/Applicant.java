import java.util.ArrayList;
import java.util.Scanner;

//applicant can do evrything needed with enquiries
//reply enquiry will be moved when officer and manager class is settled
public class Applicant extends User{
	Scanner scan = new Scanner(System.in);
	
	public Applicant(String nric, String name, String password, int age, boolean married) {
		super(nric,name,password,age,married);
	}

	public void createApplication(Project chosenProj) {
		application = new Application(this, chosenProj);
		ApplicationList.addApplication(application);
	}

	public void viewEnquiry () { 
		EnquiryList.showUserEnquiries(this);
	}
	
    public void createEnquiry(Project chosenProj, String msg) {
	    Enquiry enquiry = new Enquiry(this, chosenProj);    
	    enquiry.setMessage(msg);
	    EnquiryList.addEnquiry(enquiry);
    	}
    	
    public void editEnquiry(Enquiry editedEnquiry, String msg) {
    	editedEnquiry.setMessage(msg);
    }
    
    public void deleteEnquiry(Enquiry delEnquiry) {
    	ArrayList<Enquiry> allEnquiries = EnquiryList.getAllEnquiries();
    	allEnquiries.remove(delEnquiry); 
    	delEnquiry = null;
    }
    
    

	//Projects methods here
	public ArrayList<Project> viewProjects() {
		//if single and below 35 or married and below 21, nth visible
 		if ((!married && this.age <35) || this.age < 21) {
 			System.out.println("No projects available");
 			System.out.println();
 			return null;
 		}
 		ArrayList<Project> allProjects = ProjectList.getAllProjects();
 		ArrayList<Project> applicantProjects = new ArrayList<Project>();
 		int i = 1;
 		for (Project p : allProjects) {
 			if (p.getvisibility() && ((p.getNum2Rooms() > 0) || p.getNum3Rooms() > 0)) {
 				//if married then anything can print
 				if (married) {
 					applicantProjects.add(p);
 					System.out.print("Project " + i + ": ");
 					p.print();
 					System.out.println();
 					i++;
 				} 
 				// if not married, then only if 2 room available
 				else if (p.getNum2Rooms() > 0) {
 					applicantProjects.add(p);
 					System.out.print("Project " + i + ": ");
 					p.print();
 					System.out.println();
 					i++;
 				}
 			}
 		} return applicantProjects;
	}

	public void applyProject() {
		if (!(application == null)) {
			System.out.println("Cannot Apply for a Second Project.");
			System.out.println();
			return;
		}
		ArrayList<Project> applicantProjects = viewProjects();
		if ((applicantProjects == null)) {
			return;
		} else{
				System.out.println("Which Project to apply for?");
				int projNo = scan.nextInt();
				scan.nextLine();
				Project chosenProj = applicantProjects.get(projNo-1); 		
				
				createApplication(chosenProj);
				
				if (!married) {
					application.setFlatType(2);
				} 
				
				else {
					if ((chosenProj.getNum2Rooms() > 0 ) && chosenProj.getNum3Rooms() > 0) { //if 2 and 3 room available
						System.out.println("Apply for 2 or 3 room flat?");
						int choice = scan.nextInt();
						scan.nextLine();
						if (choice == 2) {
							application.setFlatType(2);
						} else if (choice == 3) {
							application.setFlatType(3);
						}
					} else if (chosenProj.getNum2Rooms() > 0) { //if only 2 room available
						application.setFlatType(2);
					} else if (chosenProj.getNum3Rooms() > 0) { //if only 3 room available
						application.setFlatType(3);
					} 
				}
				chosenProj.addPerson(this);
				System.out.println("Successfully applied for " + chosenProj.getName());
				application.print();
				System.out.println();
			}
		}

	public void viewAppliedProject() {
		if (application == null) {
			System.out.println("No applied projects found");
			System.out.println();
			return;
		}
		application.print();
		System.out.println();
	}

	public void withdrawApplication() {
		if (!application.getWithdrawalRequest()) {
			application.requestWithdrawal();
			System.out.println("Withdrawal requested!");
			System.out.println();
		}
		else {
			System.out.println("Application has already been requested for withdrawal.");
			System.out.println();
		}		
	}

	public Application getApplication() {
		return application;
	}
	
	public void deleteApplication() {
			ArrayList<Application> applications = ApplicationList.getAllApplications();
			applications.remove(application);
			application = null;
	}

	// reply enquiry has 2 ways for it to go, one is the all projects enquiry that a manager can do, 
    // one is the specific proj enq for officers 
    // for now this coded so that all unanswered enquiries are shown and can be replied
	public void replyEnquiry(Enquiry unansweredEnquiry, String msg) {
		unansweredEnquiry.setResponse(msg);	
	}

	//to be moved to manager class
    //havent written reject withdrawal
    public void approveWithdrawal(Application application) {
		application.approveWithdrawal();
		System.out.println("Withdrawal has been approved.");
		System.out.println();
	}

}