import java.util.ArrayList;
import java.util.Scanner;

public class ManagerDisplay implements UserDisplay{
	private Manager manager;
    private ManagerController controller;
    private Scanner scanner = new Scanner(System.in);

    public ManagerDisplay(Manager manager) {
        this.manager = manager;
        this.controller = new ManagerController(manager);
    }

    @Override
    public void showDisplay() {
        boolean running = true;
        while (running) {
            System.out.println("========== Manager Menu ==========");
            System.out.println("(1) View My Projects");
            System.out.println("(2) View Pending Applications");
            System.out.println("(3) View Withdrawal Requests");
            System.out.println("(4) View Officer Registrations");
            System.out.println("(5) Approve/Reject Officer Registrations");
            System.out.println("(6) Approve/Reject Withdrawal Requests");
			System.out.println("(7) Change Password");
            System.out.println("(0) Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> controller.viewOwnProjects();
                case 2 -> controller.viewPendingApplications();
                case 3 -> controller.viewWithdrawalRequests(manager);
                case 4 -> controller.viewOfficerRegistrations();
                case 5 -> controller.approveOrRejectOfficerRegistration();
                case 6 -> controller.approveOrRejectWithdrawalRequests();
				case 7 -> changeUserPassword(scanner, manager);
                case 0 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }
	/*
    Scanner scan = new Scanner(System.in);
	private Manager manager;
	
	ManagerDisplay(Manager manager) {
		this.manager = manager;
	}
	@Override
	public void showDisplay() {
		boolean running = true;
		
		do {
			
			System.out.println("=========== Manager User Menu ===========");
			System.out.println("|Below are the actions you can take:     |");
			System.out.println("|(1) Create a project                    |");
			System.out.println("|(2) Edit a project                      |");
			System.out.println("|(3) Delete a Project                    |");
			System.out.println("|(4) View all my projects                |");
			System.out.println("|(5) Approve/Reject Officer Registration |");
			System.out.println("|(6) Approve/Reject Applicant Application|");
			System.out.println("|(7) View all Enquiries                  |");
			System.out.println("|(8) View Project Enquiries              |");
			System.out.println("|(9) Reply to an Enquiry                 |");
			System.out.println("|(10) Approve/Reject Withdrawal of App    |");
			System.out.println("|(11) Generate Report                    |");
			System.out.println("|(0) Exit                                |");
		
			

		System.out.println();
		System.out.println("Please enter your choice: ");
		int choice = scan.nextInt();
		scan.nextLine();
			

		// try to use interface for input , and then override them for each switch case , OCP
		switch (choice) {
			case 1: //getting user input for creating project
				System.out.println("Enter Project Name: ");
				String projName = scan.nextLine();

				System.out.println("Enter Neighborhood: ");
				String neighborhood = scan.nextLine();

				System.out.println("Enter Visibility (true/false): ");
				boolean visibility = Boolean.parseBoolean(scan.nextLine());

				System.out.println("Enter number of 2-Room units: ");
				int num2Rooms = Integer.parseInt(scan.nextLine());

				System.out.println("Enter number of 3-Room units: ");
				int num3Rooms = Integer.parseInt(scan.nextLine());

				System.out.println("Enter Application Opening Date (YYYY-MM-DD): ");
				String openingDate = scan.nextLine();

				System.out.println("Enter Application Closing Date (YYYY-MM-DD): ");
				String closingDate = scan.nextLine();

				System.out.println("Enter available HDB Officer Slots: ");
				int availableOfficerSlots = Integer.parseInt(scan.nextLine());
    
    
				manager.createProject(projName, neighborhood, visibility, num2Rooms, num3Rooms, openingDate, closingDate, availableOfficerSlots);
				break;
				
			case 2: // user inputs for editing and then updating it through parameters in the method edit
				ArrayList<Project> myProjects = ProjectList.getUserProjects(manager);
                    if (myProjects.isEmpty()) {
                        System.out.println("You have no projects to edit.");
                    } 
					else {
                        System.out.println("Your Projects:");
                        for (int i = 0; i < myProjects.size(); i++) {
                            System.out.println((i+1) + ") ");
                            myProjects.get(i).print();
                            System.out.println();
                        }
                        System.out.print("Enter the number of the project you want to edit: ");
                        int projIndex = Integer.parseInt(scan.nextLine());
                        if (projIndex < 1 || projIndex > myProjects.size()) {
                            System.out.println("Invalid selection.");
                        } 
						else {
                            Project projToEdit = myProjects.get(projIndex - 1);
                            System.out.println("Editing Project: " + projToEdit.getName());
                            
                            System.out.print("Enter new Project Name (or press Enter to keep \"" + projToEdit.getName() + "\"): ");
                            String newName = scan.nextLine();
                            if (newName.isEmpty()) {
                                newName = projToEdit.getName();
                            }
                            
                            System.out.print("Enter new Neighborhood (or press Enter to keep \"" + projToEdit.getNeighborhood() + "\"): ");
                            String newNeighborhood = scan.nextLine();
                            if (newNeighborhood.isEmpty()) {
                                newNeighborhood = projToEdit.getNeighborhood();
                            }
                            System.out.print("Enter new Visibility (true/false) (or press Enter to keep current (" 
                                               + projToEdit.getVisibility() + ")): ");
                            String visInput = scan.nextLine();
                            boolean newVisibility = visInput.isEmpty() ? projToEdit.getVisibility() : Boolean.parseBoolean(visInput);
                            
                            System.out.print("Enter new number of 2-Room units (or press Enter to keep current (" 
                                               + projToEdit.getNum2Rooms() + ")): ");
                            String num2Input = scan.nextLine();
                            int newNum2Rooms = num2Input.isEmpty() ? projToEdit.getNum2Rooms() : Integer.parseInt(num2Input);
                            
                            System.out.print("Enter new number of 3-Room units (or press Enter to keep current (" 
                                               + projToEdit.getNum3Rooms() + ")): ");
                            String num3Input = scan.nextLine();
                            int newNum3Rooms = num3Input.isEmpty() ? projToEdit.getNum3Rooms() : Integer.parseInt(num3Input);
                            
                            System.out.print("Enter new Application Opening Date (YYYY-MM-DD) (or press Enter to keep current (" 
                                               + projToEdit.getOpeningDate() + ")): ");
                            String newOpeningDate = scan.nextLine();
                            if (newOpeningDate.isEmpty()) {
                                newOpeningDate = projToEdit.getOpeningDate();
                            }
                            
                            System.out.print("Enter new Application Closing Date (YYYY-MM-DD) (or press Enter to keep current (" 
                                               + projToEdit.getClosingDate() + ")): ");
                            String newClosingDate = scan.nextLine();
                            if (newClosingDate.isEmpty()) {
                                newClosingDate = projToEdit.getClosingDate();
                            }
                            
                            System.out.print("Enter new Available HDB Officer Slots (or press Enter to keep current (" 
                                               + projToEdit.getAvailOfficerSlots() + ")): ");
                            String slotInput = scan.nextLine();
                            int newAvailableOfficerSlots = slotInput.isEmpty() ? projToEdit.getAvailOfficerSlots() : Integer.parseInt(slotInput);
                            
                            manager.editProject(projToEdit, newName, newNeighborhood, newVisibility, newNum2Rooms, newNum3Rooms, newOpeningDate, newClosingDate, newAvailableOfficerSlots);
						}
					}


				
				break;
			
			case 3:
				ArrayList<Project> myProjectsForDelete = ProjectList.getUserProjects(manager);
				if (myProjectsForDelete.isEmpty()) {
					System.out.println("No projects to delete.");
				} 
				else {
					System.out.println("Your Projects:");
					for (int i = 0; i < myProjectsForDelete.size(); i++) {
						System.out.println((i+1) + ") " + myProjectsForDelete.get(i).getName());
					}
					System.out.print("Select a project to delete (number): ");
					int delIndex = Integer.parseInt(scan.nextLine());
					if (delIndex < 1 || delIndex > myProjectsForDelete.size()) {
					System.out.println("Invalid selection.");
					} 
					else {
						Project projToDelete = myProjectsForDelete.get(delIndex - 1);
						manager.deleteProject(projToDelete);
					}
				}
				break;
				
			case 4:
				manager.viewMyProjects();
				break;
			
			case 5:

			ArrayList<Project> myProjectsForOfficer = ProjectList.getUserProjects(manager);
			//check for projects
			if (myProjectsForOfficer.isEmpty()) {
				System.out.println("No projects available.");
			} else {
				System.out.println("Select a project to manage officer registrations:");
				for (int i = 0; i < myProjectsForOfficer.size(); i++) {
					System.out.println((i+1) + ") " + myProjectsForOfficer.get(i).getName());
				}
				int projIndexOfficer = Integer.parseInt(scan.nextLine());

				//checks if index of officer in the officerlist is valid
				if (projIndexOfficer < 1 || projIndexOfficer > myProjectsForOfficer.size()) {
					System.out.println("Invalid project selection.");
				} else {
					Project selectedProject = myProjectsForOfficer.get(projIndexOfficer - 1);
					// Josh pls have this in officerlist.  OfficerList.getPendingRegistrations(project) returns pending officer registrations
					ArrayList<Registration> pendingReg = RegistrationList.getPendingRegistrations(selectedProject);
					ArrayList<Officer> pendingOfficers = new ArrayList<>();
					for (Registration r : pendingReg) {
						pendingOfficers.add(r.getOfficer());
					}
					if (pendingOfficers.isEmpty()) {
						System.out.println("No pending officer registrations for this project.");
					} else {
						System.out.println("Pending Officer Registrations:");
						for (int i = 0; i < pendingOfficers.size(); i++) {
							System.out.println((i+1) + ") " + pendingOfficers.get(i).getName());
						}

						System.out.print("Select an officer to process: ");
						int officerIndex = Integer.parseInt(scan.nextLine());

						if (officerIndex < 1 || officerIndex > pendingOfficers.size()) {
							System.out.println("Invalid selection.");
						} else {
							Officer selectedOfficer = pendingOfficers.get(officerIndex - 1);
							System.out.print("Enter 1 to Approve, 2 to Reject: ");
							int decision = Integer.parseInt(scan.nextLine());

							if (decision == 1) {
								manager.approveOfficerRegistration(selectedOfficer, selectedProject);
							} 
							else if (decision == 2) {
								manager.rejectOfficerRegistration(selectedOfficer, selectedProject);
							} 
							else {
								System.out.println("Invalid decision.");
							}
						}
					}
				}
			}
			break;
				
				
			
			case 6:
				//create arraylist of pending applications
				ArrayList<Application> pendingApps = ApplicationList.getPendingApplicationsForManager(manager);

				if( pendingApps.isEmpty()){
					System.out.println("No pending applicant applications");
				}
				
				else{
					//lists out all the pending applications
					for (int i = 0; i < pendingApps.size(); i++) {
						Application app = pendingApps.get(i);
						System.out.println((i+1) + ") Applicant: " + app.getApplicant().getName() +
										   ", Project: " + app.getProject().getName() +
										   ", Flat Type: " + app.getFlatType());
					}

					System.out.println("Select application to process (number): ");
					int appIndex = Integer.parseInt(scan.nextLine());
					if( appIndex < 1 || appIndex > pendingApps.size() ){
						System.out.println("Invalid selection");
					}
					else{
						Application selectedApp = pendingApps.get(appIndex - 1);
						System.out.print("Enter 1 to Approve, 2 to Reject: ");

						int appDecision = Integer.parseInt(scan.nextLine());
						if (appDecision == 1) {
							manager.approveApplicantApplication(selectedApp.getApplicant());
						} else if (appDecision == 2) {
							manager.rejectApplicantApplication(selectedApp.getApplicant());
						} else {
							System.out.println("Invalid decision.");
						}
					}
				}
				break;
			
			case 7: 
				manager.viewAllEnquiry();
				break;
		
			case 8:
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
				
			
			case 9:
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
				
			case 10:	//NEED TO ASK WHETHER THE APPLICATIONS SHOW ARE SPECIFIC TO THE PROJECT THE MANAGER MANAGES
				
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

			case 11:
				break;
				
			case 0:
				running = false;
				break;
		}
		
		} while (running);
		
	}*/

}
