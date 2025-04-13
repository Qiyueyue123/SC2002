import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerDisplay implements UserDisplay {
	private final ManagerController controller;
	private final Scanner scanner;
	private Manager manager;

	public ManagerDisplay(Manager manager) {
		this.scanner = new Scanner(System.in);
		// Pass the repository instance to the controller.
		this.manager = manager;
		this.controller = new ManagerController(manager);
	}

	@Override
    public void showDisplay() {
        boolean running = true;
        do {
            System.out.println("=========== Manager User Menu ===========");
            System.out.println("| (1) Create a project                    |");
            System.out.println("| (2) Edit a project                      |");
            System.out.println("| (3) Delete a project                    |");
            System.out.println("| (4) View all my projects                |");
            System.out.println("| (5) Approve/Reject Officer Registration |");
            System.out.println("| (6) Approve/Reject Applicant Application|");
            System.out.println("| (7) View all Enquiries                  |");
            System.out.println("| (8) View Project Enquiries              |");
            System.out.println("| (9) Reply to an Enquiry                 |");
            System.out.println("| (10) Approve/Reject Withdrawal of App   |");
            System.out.println("| (11) Generate Report                    |");
			System.out.println("| (12) Toggle Visibility                  |");
            System.out.println("| (0) Exit                                |");
            System.out.println("==========================================");
            System.out.print("Please enter your choice: ");
            
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    System.out.print("Enter Project Name: ");
                    String projName = scanner.nextLine();
                    System.out.print("Enter Neighborhood: ");
                    String neighborhood = scanner.nextLine();
                    System.out.print("Enter Visibility (true/false): ");
                    boolean visibility = Boolean.parseBoolean(scanner.nextLine());
                    System.out.print("Enter number of 2-Room units: ");
                    int num2Rooms = Integer.parseInt(scanner.nextLine());
					System.out.println("Enter price of 2-Room units: ");
					int price2room = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter number of 3-Room units: ");
                    int num3Rooms = Integer.parseInt(scanner.nextLine());
					System.out.println("Enter price of 3-Room units: ");
					int price3room = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Application Opening Date (YYYY-MM-DD): ");
                    String openingDate = scanner.nextLine();
                    System.out.print("Enter Application Closing Date (YYYY-MM-DD): ");
                    String closingDate = scanner.nextLine();
                    System.out.print("Enter available HDB Officer Slots: ");
                    int availableOfficerSlots = Integer.parseInt(scanner.nextLine());
                    controller.createProject(projName,neighborhood,visibility,num2Rooms,num3Rooms,openingDate,closingDate,availableOfficerSlots,
					manager,price2room,price3room);
                    break;
                case 2:
					ArrayList<Project> myProjects = ProjectController.getUserProjects(manager);
					if (myProjects.isEmpty()) {
						System.out.println("You have no projects to edit.");
						return;
					}
					System.out.println("Your Projects:");
					for (int i = 0; i < myProjects.size(); i++) {
						System.out.println((i + 1) + ") ");
						myProjects.get(i).print(); 
						System.out.println();
					}
					System.out.print("Enter the number of the project you want to edit: ");
					int projIndex = Integer.parseInt(scanner.nextLine());
					if (projIndex < 1 || projIndex > myProjects.size()) {
						System.out.println("Invalid selection.");
						return;
					}
					Project projToEdit = myProjects.get(projIndex - 1);
					System.out.println("Editing Project: " + projToEdit.getName());
					
					System.out.print("Enter new Project Name (or press Enter to keep \"" + projToEdit.getName() + "\"): ");
					String newName = scanner.nextLine();
					if (newName.isEmpty()) {
						newName = projToEdit.getName();
					}
					System.out.print("Enter new Neighborhood (or press Enter to keep \"" + projToEdit.getNeighborhood() + "\"): ");
					String newNeighborhood = scanner.nextLine();
					if (newNeighborhood.isEmpty()) {
						newNeighborhood = projToEdit.getNeighborhood();
					}
					System.out.print("Enter new Visibility (true/false) (or press Enter to keep current (" + projToEdit.getVisibility() + ")): ");
					String visInput = scanner.nextLine();
					boolean newVisibility = visInput.isEmpty() ? projToEdit.getVisibility() : Boolean.parseBoolean(visInput);

					System.out.print("Enter new number of 2-Room units (or press Enter to keep current (" + projToEdit.getNum2Rooms() + ")): ");
					String num2Input = scanner.nextLine();
					int newNum2Room = num2Input.isEmpty() ? projToEdit.getNum2Rooms() : Integer.parseInt(num2Input);

					System.out.print("Enter new price of 2-Room units (or press Enter to keep current (" + projToEdit.getPrice2Room() + ")): ");
					String price2Input = scanner.nextLine();
					int newPrice2Rooms = price2Input.isEmpty() ? projToEdit.getPrice2Room() : Integer.parseInt(price2Input);

					System.out.print("Enter new number of 3-Room units (or press Enter to keep current (" + projToEdit.getNum3Rooms() + ")): ");
					String num3Input = scanner.nextLine();
					int newNum3Rooms = num3Input.isEmpty() ? projToEdit.getNum3Rooms() : Integer.parseInt(num3Input);

					System.out.print("Enter new price of 3-Room units (or press Enter to keep current (" + projToEdit.getPrice3Room() + ")): ");
					String price3Input = scanner.nextLine();
					int newPrice3Rooms = price3Input.isEmpty() ? projToEdit.getPrice2Room() : Integer.parseInt(price2Input);

					System.out.print("Enter new Application Opening Date (YYYY-MM-DD) (or press Enter to keep current (" + projToEdit.getOpeningDate() + ")): ");
					String newOpeningDate = scanner.nextLine();
					if (newOpeningDate.isEmpty()) {
						newOpeningDate = projToEdit.getOpeningDate();
					}
					System.out.print("Enter new Application Closing Date (YYYY-MM-DD) (or press Enter to keep current (" + projToEdit.getClosingDate() + ")): ");
					String newClosingDate = scanner.nextLine();
					if (newClosingDate.isEmpty()) {
						newClosingDate = projToEdit.getClosingDate();
					}
					System.out.print("Enter new Available HDB Officer Slots (or press Enter to keep current (" + projToEdit.getAvailOfficerSlots() + ")): ");
					String slotInput = scanner.nextLine();
					int newAvailableOfficerSlots = slotInput.isEmpty() ? projToEdit.getAvailOfficerSlots() : Integer.parseInt(slotInput);
					controller.editProject(projToEdit,newName,newNeighborhood,newVisibility,newNum2Room,newNum3Rooms,newOpeningDate,newClosingDate,newAvailableOfficerSlots,
					manager,newPrice2Rooms,newPrice3Rooms);
                    break;
                case 3:
					ArrayList<Project> myProjectsForDelete = ProjectController.getUserProjects(manager);
					if (myProjectsForDelete.isEmpty()) {
						System.out.println("No projects to delete.");
						return;
					}
					System.out.println("Your Projects:");
					for (int i = 0; i < myProjectsForDelete.size(); i++) {
						System.out.println((i + 1) + ") " + myProjectsForDelete.get(i).getName());
					}
					System.out.print("Select a project to delete (number): ");
					int delIndex = Integer.parseInt(scanner.nextLine());
					if (delIndex < 1 || delIndex > myProjectsForDelete.size()) {
						System.out.println("Invalid selection.");
						return;
					}
					Project projToDelete = myProjectsForDelete.get(delIndex - 1);
                    controller.deleteProject(projToDelete);
                    break;
                case 4:
                    controller.viewOwnProjects();
                    break;
                case 5:
                    controller.approveOrRejectOfficerRegistration();
                    break;
                case 6:
                    controller.approveOrRejectApplicantApplication();
                    break;
                case 7:
                    controller.viewAllEnquiries();
                    break;
                case 8:
					List<Enquiry> enquiries = controller.viewProjectEnquiries();
                    if(controller.viewProjectEnquiries()==null){
						System.out.println("Try Again.");
					}else if(enquiries.isEmpty()){
						System.out.println("There is no enquiries currently!");
					}else{
						System.out.println("List of Enquiries:");
						for (Enquiry e : enquiries) {
							e.print();
						}		
					}
                    break;
                case 9:
                    controller.replyToEnquiry();
                    break;
                case 10:
                    controller.approveOrRejectWithdrawal();
                    break;
                case 11:
                    controller.generateReport();
                    break;
				case 12:
					controller.toggleVisibility();
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (running);
    }
}