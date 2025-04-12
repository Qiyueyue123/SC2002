import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerController {
    private final Manager manager;
    private final Scanner scanner;
    private final ReportRepository reportRepository;

    // The ReportRepository (and other repositories) should be injected or accessed via a shared context.
    public ManagerController(Manager manager, ReportRepository reportRepository) {
        this.manager = manager;
        this.reportRepository = reportRepository;
        this.scanner = new Scanner(System.in);
    }
    
   
    public void createProject(String projName, String neighborhood, boolean visibility, int num2Rooms, int num3Rooms,
                              String openingDate, String closingDate, int availableOfficerSlots) {
      
        manager.createProject(projName, neighborhood, visibility, num2Rooms, num3Rooms, openingDate, closingDate, availableOfficerSlots);
    }
    
   
    public void editProject() {
        ArrayList<Project> myProjects = ProjectList.getUserProjects(manager);
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
        int newNum2Rooms = num2Input.isEmpty() ? projToEdit.getNum2Rooms() : Integer.parseInt(num2Input);
        System.out.print("Enter new number of 3-Room units (or press Enter to keep current (" + projToEdit.getNum3Rooms() + ")): ");
        String num3Input = scanner.nextLine();
        int newNum3Rooms = num3Input.isEmpty() ? projToEdit.getNum3Rooms() : Integer.parseInt(num3Input);
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
        
        manager.editProject(projToEdit, newName, newNeighborhood, newVisibility, newNum2Rooms, newNum3Rooms, newOpeningDate, newClosingDate, newAvailableOfficerSlots);
    }
    
    
    public void deleteProject() {
        ArrayList<Project> myProjectsForDelete = ProjectList.getUserProjects(manager);
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
        manager.deleteProject(projToDelete);
    }
    
    
    public void viewProjects() {
        manager.viewMyProjects();
    }
    
    
    public void approveOrRejectOfficerRegistration() {
        ArrayList<Project> myProjectsForOfficer = ProjectList.getUserProjects(manager);
        if (myProjectsForOfficer.isEmpty()) {
            System.out.println("No projects available.");
            return;
        }
        System.out.println("Select a project to manage officer registrations:");
        for (int i = 0; i < myProjectsForOfficer.size(); i++) {
            System.out.println((i + 1) + ") " + myProjectsForOfficer.get(i).getName());
        }
        int projIndexOfficer = Integer.parseInt(scanner.nextLine());
        if (projIndexOfficer < 1 || projIndexOfficer > myProjectsForOfficer.size()) {
            System.out.println("Invalid project selection.");
            return;
        }
        Project selectedProject = myProjectsForOfficer.get(projIndexOfficer - 1);
        ArrayList<Registration> pendingReg = RegistrationList.getPendingRegistrations(selectedProject);
        if (pendingReg.isEmpty()) {
            System.out.println("No pending officer registrations for this project.");
            return;
        }
        System.out.println("Pending Officer Registrations:");
        for (int i = 0; i < pendingReg.size(); i++) {
            System.out.println((i + 1) + ") " + pendingReg.get(i).getOfficer().getName());
        }
        System.out.print("Select an officer to process: ");
        int officerIndex = Integer.parseInt(scanner.nextLine());
        if (officerIndex < 1 || officerIndex > pendingReg.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        Registration selectedRegistration = pendingReg.get(officerIndex - 1);
        System.out.print("Enter 1 to Approve, 2 to Reject: ");
        int decision = Integer.parseInt(scanner.nextLine());
        if (decision == 1) {
            manager.approveOfficerRegistration(selectedRegistration.getOfficer(), selectedProject);
        } else if (decision == 2) {
            manager.rejectOfficerRegistration(selectedRegistration.getOfficer(), selectedProject);
        } else {
            System.out.println("Invalid decision.");
        }
    }
    
    
    public void approveOrRejectApplicantApplication() {
        ArrayList<Application> pendingApps = ApplicationList.getPendingApplicationsForManager(manager);
        if (pendingApps.isEmpty()) {
            System.out.println("No pending applicant applications.");
            return;
        }
        for (int i = 0; i < pendingApps.size(); i++) {
            Application app = pendingApps.get(i);
            System.out.println((i + 1) + ") Applicant: " + app.getApplicant().getName() +
                               ", Project: " + app.getProject().getName() +
                               ", Flat Type: " + app.getFlatType());
        }
        System.out.print("Select application to process (number): ");
        int appIndex = Integer.parseInt(scanner.nextLine());
        if (appIndex < 1 || appIndex > pendingApps.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        Application selectedApp = pendingApps.get(appIndex - 1);
        System.out.print("Enter 1 to Approve, 2 to Reject: ");
        int appDecision = Integer.parseInt(scanner.nextLine());
        if (appDecision == 1) {
            manager.approveApplicantApplication(selectedApp.getApplicant());
        } else if (appDecision == 2) {
            manager.rejectApplicantApplication(selectedApp.getApplicant());
        } else {
            System.out.println("Invalid decision.");
        }
    }
    
    
    public void viewAllEnquiries() {
        manager.viewAllEnquiry();
    }
    
    
    public void viewProjectEnquiries() {
        ArrayList<Project> myProjects = ProjectList.getUserProjects(manager);
        if (myProjects.isEmpty()) {
            System.out.println("There are no projects available.");
            return;
        }
        ProjectList.showUserProjects(manager);
        System.out.println("Which project's enquiries would you like to view?");
        int projNo = Integer.parseInt(scanner.nextLine());
        if (projNo < 1 || projNo > myProjects.size()) {
            System.out.println("Invalid project number.");
            return;
        }
        Project chosenProj = myProjects.get(projNo - 1);
        manager.viewProjectEnquiry(chosenProj);
    }
    
    
    public void replyToEnquiry() {
        ArrayList<Project> userProjs = ProjectList.getUserProjects(manager);
        if (userProjs.isEmpty()) {
            System.out.println("There are no projects available.");
            return;
        }
        ProjectList.showUserProjects(manager);
        System.out.println("Which project's enquiries would you like to view?");
        int projNo = Integer.parseInt(scanner.nextLine());
        if (projNo < 1 || projNo > userProjs.size()) {
            System.out.println("Invalid project selection.");
            return;
        }
        Project chosenProj = userProjs.get(projNo - 1);
        ArrayList<Enquiry> enquiries = EnquiryList.getProjEnquiries(chosenProj);
        if (enquiries.isEmpty()) {
            System.out.println("There are no enquiries for this project.");
            return;
        }
        System.out.println("List of Enquiries:");
        for (int i = 0; i < enquiries.size(); i++) {
            System.out.println((i + 1) + ". ");
            enquiries.get(i).print();
        }
        System.out.print("Enter the Enquiry ID to reply: ");
        int enquiryId = Integer.parseInt(scanner.nextLine());
        Enquiry selectedEnquiry = EnquiryList.selectEnquiry(enquiryId);
        if (selectedEnquiry == null) {
            System.out.println("Invalid enquiry ID.");
            return;
        }
        System.out.print("Enter your reply: ");
        String reply = scanner.nextLine();
        manager.replyEnquiry(selectedEnquiry, reply);
    }
    
    
    public void approveOrRejectWithdrawal() {
        ArrayList<Application> withdrawalRequested = ApplicationList.getWithdrawalRequested(manager);
        if (withdrawalRequested.isEmpty()) {
            System.out.println("There are no withdrawal requests.");
            return;
        }
        boolean processing = true;
        while (processing) {
            ApplicationList.showWithdrawalRequested(manager);
            System.out.println("Type in the NRIC of the application you wish to view (or 0 to exit):");
            String nric = scanner.nextLine();
            if (nric.equals("0")) {
                processing = false;
                break;
            }
            Application selectedApp = ApplicationList.selectApplication(nric);
            if (selectedApp == null) {
                continue;
            }
            selectedApp.print();
            System.out.println("Select: (1) Approve withdrawal, (2) Reject withdrawal, (0) Cancel:");
            int appChoice = Integer.parseInt(scanner.nextLine());
            if (appChoice == 1) {
                manager.approveWithdrawal(selectedApp);
            } else if (appChoice == 2) {
                manager.rejectWithdrawal(selectedApp);
            } else if (appChoice == 0) {
                continue;
            }
        }
    }
    
    
    public void generateReport() {
        viewApplicantReports();
    }
    
    
    public void viewApplicantReports() {
        List<Report> reports = reportRepository.getAllReports();
        List<Report> filteredReports = new ArrayList<>(reports);
        
        System.out.println("========== Applicant Report Filter ==========");
        System.out.println("(1) No Filter (Show all reports)");
        System.out.println("(2) Filter by Age Group");
        System.out.println("(3) Filter by Marital Status");
        System.out.println("(4) Filter by Project Name");
        System.out.println("(5) Filter by Flat Type");
        System.out.println("(0) Cancel");
        int filterChoice = Integer.parseInt(scanner.nextLine());
        switch (filterChoice) {
            case 0:
                System.out.println("Cancelling report generation.");
                return;
            case 1:
                // No filtering.
                break;
            case 2:
                System.out.println("Select Age Range: ");
                System.out.println("(1) Under 25");
                System.out.println("(2) 25 to 35");
                System.out.println("(3) 35 to 45");
                System.out.println("(4) Over 45");
                int ageChoice = Integer.parseInt(scanner.nextLine());
                switch (ageChoice) {
                    case 1:
                        filteredReports = reportRepository.filterUnder25();
                        break;
                    case 2:
                        filteredReports = reportRepository.filter25to35();
                        break;
                    case 3:
                        filteredReports = reportRepository.filter35to45();
                        break;
                    case 4:
                        filteredReports = reportRepository.filterOver45();
                        break;
                    default:
                        System.out.println("Invalid choice, no filter applied.");
                }
                break;
            case 3:
                System.out.println("Select: (1) Married, (2) Single");
                int maritalChoice = Integer.parseInt(scanner.nextLine());
                boolean isMarried = (maritalChoice == 1);
                filteredReports = reportRepository.filterMarried(isMarried);
                break;
            case 4:
                System.out.print("Enter Project Name: ");
                String projectName = scanner.nextLine();
                filteredReports = reportRepository.filterProjects(projectName);
                break;
            case 5:
                System.out.println("Select Flat Type: (1) 2-Room, (2) 3-Room");
                int roomChoice = Integer.parseInt(scanner.nextLine());
                boolean is2Room = (roomChoice == 1);
                filteredReports = reportRepository.filterByFlatType(is2Room);
                break;
            default:
                System.out.println("Invalid choice. No filter applied.");
        }
        
        System.out.println("========== Applicant Reports ==========");
        if (filteredReports.isEmpty()) {
            System.out.println("No reports match the selected criteria.");
        } else {
            int count = 1;
            for (Report r : filteredReports) {
                System.out.println("Report " + count + ":");
                ReportDisplay.printReport(r);
                System.out.println();
                count++;
            }
        }
    }
}
