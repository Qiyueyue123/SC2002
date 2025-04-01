import java.util.ArrayList;
import java.util.Scanner;

public class ManagerDisplay implements UserDisplay{
    Scanner scan = new Scanner(System.in);
	private Manager manager;
	
	ManagerDisplay(Manager manager) {
		this.manager = manager;
	}
	@Override
	public void showDisplay() {
		boolean running = true;
		
		do {
			
			System.out.println("========== Manager User Menu ==========");
			System.out.println("|Below are the actions you can take:    |");
			System.out.println("|(1)                  |");
			System.out.println("|(2)                                    |");
			System.out.println("|(3) View all Enquiries                 |");
			System.out.println("|(4) View Project Enquiries             |");
			System.out.println("|(5) Reply to an Enquiry                |");
			System.out.println("|(7) Approve/Reject Withdrawal of App   |");
			System.out.println("|(0) Exit                               |");

		System.out.println();
		System.out.println("Please enter your choice: ");
		int choice = scan.nextInt();
		scan.nextLine();
			
		switch (choice) {
			case 1:
				
				break;
			case 2:
				
				break;
				
			case 3: 
				manager.viewAllEnquiry();
				break;
		
			case 4:
				//will need depend on the projects they manage
				ArrayList<Project> userProjs = ProjectList.getUserProjects(manager);
		    	
		    	if (!userProjs.isEmpty()) {
			    	ProjectList.showUserProjects(manager);
			    	
			    	System.out.println("Which project's enquiries would you like to view?");
			    	int projNo = scan.nextInt();
			    	scan.nextLine(); //needs this after every scanInt
			    	Project chosenProj = userProjs.get(projNo-1); 
			        
			    	manager.viewProjectEnquiry(chosenProj);
			    	}
		    	else {
		    		System.out.println("There are no projects available.");
		    	}
				
				
				break;
				
			
			case 5:
				//can only reply to enquiries to projects they manage
				userProjs = ProjectList.getUserProjects(manager);
		    	
		    	if (!userProjs.isEmpty()) {
			    	ProjectList.showUserProjects(manager);
			    	
			    	System.out.println("Which project's enquiries would you like to view?");
			    	int projNo = scan.nextInt();
			    	scan.nextLine(); //needs this after every scanInt
			    	Project chosenProj = userProjs.get(projNo-1); 
			   
			    	//show all enquiries anyways and select the one to reply
			    	ArrayList<Enquiry> unansweredEnquiries = EnquiryList.getProjEnquiries(chosenProj);
					if (!unansweredEnquiries.isEmpty()) {
						manager.viewProjectEnquiry(chosenProj);
						
						System.out.println("Which enquiry would you like to reply? Input ID:");
			        	int enquiryNum = scan.nextInt();
			        	scan.nextLine(); //needs this after every scanInt
			        	Enquiry unansweredEnquiry = EnquiryList.selectEnquiry(enquiryNum); 
						
						System.out.println("Write your reply:");
				        String reply = scan.nextLine();
				        
				        manager.replyEnquiry(unansweredEnquiry, reply);
					}
					else {
			    		System.out.println("There are no enquiries to reply.");
			    	}
			    	
			    	}
		    	else {
		    		System.out.println("There are no projects available.");
		    	}
				
				
				
				
				
				break;
				
			case 7:	//NEED TO ASK WHETHER THE APPLICATIONS SHOW ARE SPECIFIC TO THE PROJECT THE MANAGER MANAGES
				
				//below is the procedure for Managers
				//will show list of applications first aka like projects order
				//will then ask for the application you wish to view
				//show the details of application chosen
				
				//currently views all applications that ask for withdrawal
				ArrayList<Application> withdrawalRequested = ApplicationList.getWithdrawalRequested();
				if (!(withdrawalRequested.isEmpty())) { //only shows if it is requested
					boolean sevRunning = true;
					do {
						
						ApplicationList.showWithdrawalRequested(); //show summarised list of applications that req for withdrawal
						System.out.println("Type in the NRIC of the application you wish to view: ");
						System.out.println("(Input 0 to exit to main menu)");
						String nric = scan.nextLine(); //select application to view in detail
						
						if (!(nric.equals("0"))) { //selected an application
							Application selectedApplication = ApplicationList.selectApplication(nric);
							selectedApplication.print(); //show detail of selected application
							System.out.println();
							
							//choose to approve, reject application or its withdrawal or return to summarised list
							System.out.println("Select 0 to exit to application list.");
							System.out.println("Application options: ");
							System.out.println("(1) Approve withdrawal");
							System.out.println("(2) Reject withdrawal");
							System.out.println();
							System.out.println("Input choice:");
							int appChoice = scan.nextInt();
							scan.nextLine();
							
							if (appChoice == 1) {
								manager.approveWithdrawal(selectedApplication);
							}
							else if (appChoice == 2) {
								manager.rejectWithdrawal(selectedApplication);
							}
							else if (appChoice == 0) {
								continue;
							}
						}
						else { //quits to summarised list
							sevRunning = false;
							break;
						}
						
					} while (sevRunning);
					
				}
				else {
					System.out.println("There are no applications requesting for withdrawal.");
					System.out.println();
				}
				
				
				
				break;
				
			case 0:
				running = false;
				break;
		}
		
		} while (running);
		
	}

}
