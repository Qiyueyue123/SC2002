import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerController {
    private final Manager manager;
    private final Scanner scanner;
    private final ReportRepository reportRepository = new ReportRepository();

    // The ReportRepository (and other repositories) should be injected or accessed via a shared context.
    public ManagerController(Manager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);

    }
    
   
    public void createProject(String name, String neighbourhood, boolean visibility, int num2Rooms, int num3Rooms, 
    String openingDate, String closingDate, int availOfficerSlots, Manager manager, int price2room, int price3room) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        List<Project> projList = ProjectController.getUserProjects(manager);
        try {
            LocalDate newOpening = LocalDate.parse(openingDate,formatter);
            LocalDate newClosing = LocalDate.parse(closingDate, formatter);
            for (Project p : projList) {
                LocalDate existingOpening = LocalDate.parse(p.getOpeningDate(), formatter);
                LocalDate existingClosing = LocalDate.parse(p.getClosingDate(),formatter);
                if (!(newClosing.isBefore(existingOpening) || newOpening.isAfter(existingClosing))) {
                    System.out.println("The new project's date range (" + newOpening + " to " + newClosing +
                    ") overlaps with the project '" + p.getName() + "' date range (" + existingOpening + " to " + existingClosing + ").");
                    return;
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
            return;
        }
        Project newProject = new Project(name,neighbourhood,visibility,num2Rooms,num3Rooms,openingDate,closingDate,availOfficerSlots,manager,price2room,price3room);
        ProjectRepository.addProject(newProject);
        System.out.println("Project successfully created!");
    }
    
   
    public void editProject(Project proj,String name, String neighbourhood, boolean visibility, int num2Rooms, int num3Rooms, 
    String openingDate, String closingDate, int availOfficerSlots, Manager manager1, int price2room, int price3room) {
        ProjectController.updateProject(proj,name,neighbourhood,visibility,num2Rooms,num3Rooms,openingDate,closingDate,availOfficerSlots,
        manager1,price2room,price3room);
    }
    
    
    public void deleteProject(Project projToDelete) {
        ProjectController.deleteProject(projToDelete);
    }
    
    public void viewAllProjects(){
        ProjectController.showAllProjects();
    }
    public void viewOwnProjects() {
        List<Project> projects = ProjectController.getUserProjects(manager);
        if (projects.isEmpty()) {
            System.out.println("You have no projects assigned.");
            return;
        }
        ProjectDisplay.showUserProjects(manager);
    }

    public void approveOrRejectOfficerRegistration() {
        ArrayList<Project> myProjectsForOfficer = ProjectController.getUserProjects(manager);
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
        ArrayList<Registration> pendingReg = RegistrationController.getPendingRegistrations(selectedProject);
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
            RegistrationController.approveRegistration(selectedRegistration);
        } else if (decision == 2) {
            RegistrationController.rejectRegistration(selectedRegistration);
        } else {
            System.out.println("Invalid decision.");
        }
    }
    
    public void approveOrRejectApplicantApplication() {
        List<Application> pendingApps = ApplicationController.getManagerPendingApplications(manager);
        if (pendingApps.isEmpty()) {
            System.out.println("No pending applicant applications.");
            return;
        }
        for (int i = 0; i < pendingApps.size(); i++) {
            Application app = pendingApps.get(i);
            System.out.println((i + 1) + ") Applicant: " + app.getApplicant().getName() +
                               ", Project: " + app.getProject().getName() +
                               ", Flat Type: " + app.getFlatType() + "-Room");
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
            selectedApp.setAppliedStatus("successful");
            System.out.println(selectedApp.getApplicant().getName() + "'s application for " + selectedApp.getProject().getName() + " is approved.");
        } else if (appDecision == 2) {
            selectedApp.setAppliedStatus("unsucessful");
            System.out.println(selectedApp.getApplicant().getName() + "'s application for " + selectedApp.getProject().getName() + " is rejected.");
        } else {
            System.out.println("Invalid decision.");
        }
    }
    
    
    public void viewAllEnquiries() {
        EnquiryController.showAllEnquiries();
    }
    
    
    public void viewProjectEnquiries() {
        ArrayList<Project> myProjects = ProjectController.getUserProjects(manager);
        if (myProjects.isEmpty()) {
            System.out.println("There are no projects available.");
            return;
        }
        ProjectDisplay.showUserProjects(manager);
        System.out.println("Which project's enquiries would you like to view?");
        int projNo = Integer.parseInt(scanner.nextLine());
        if (projNo < 1 || projNo > myProjects.size()) {
            System.out.println("Invalid project selection.");
            return;
        }
        Project chosenProj = myProjects.get(projNo - 1);
        EnquiryController.showUnansweredProjectEnquiries(chosenProj);
    }
    
    
    public void replyToEnquiry() {
        ArrayList<Project> userProjs = ProjectController.getUserProjects(manager);
        if (userProjs.isEmpty()) {
            System.out.println("There are no projects available.");
            return;
        }
        ProjectDisplay.showUserProjects(manager);
        System.out.println("Which project's enquiries would you like to view?");
        int projNo = Integer.parseInt(scanner.nextLine());
        if (projNo < 1 || projNo > userProjs.size()) {
            System.out.println("Invalid project selection.");
            return;
        }
        Project chosenProj = userProjs.get(projNo - 1);
        EnquiryController.showUnansweredProjectEnquiries(chosenProj);
        System.out.print("Enter the Enquiry ID to reply: ");
        int enquiryId = Integer.parseInt(scanner.nextLine());
        Enquiry selectedEnquiry = EnquiryController.getEnquiryById(enquiryId);
        if (selectedEnquiry == null || selectedEnquiry.getProject() != chosenProj) {
            System.out.println("Invalid enquiry ID.");
            return;
        }
        System.out.print("Enter your reply: ");
        String reply = scanner.nextLine();
        selectedEnquiry.setResponse(reply);
        selectedEnquiry.setAnswered(true);
    }
    
    
    public void approveOrRejectWithdrawal() {
        boolean processing = true;
        while (processing) {
            ApplicationController.showWithdrawalRequested(manager);
            System.out.println("Type in the NRIC of the application you wish to view (or 0 to exit):");
            String nric = scanner.nextLine();
            if (nric.equals("0")) {
                processing = false;
                break;
            }
            Application selectedApp = ApplicationController.getApplicationByNRIC(nric);
            if (selectedApp == null) {
                continue;
            }
            selectedApp.print();
            System.out.println("Select: (1) Approve withdrawal, (2) Reject withdrawal, (0) Cancel:");
            int appChoice = Integer.parseInt(scanner.nextLine());
            if (appChoice == 1) {
                selectedApp.approveWithdrawal();
                System.out.println(selectedApp.getApplicant().getName() + "'s withdrawal of application for " + selectedApp.getProject().getName() + " is approved.");
                if(selectedApp.getAppliedStatus().equals("booked")){
                    int flatType = selectedApp.getFlatType();
                    System.out.println("THIS IS :" + flatType);
                    if(flatType == 2){
                        int num2Room = selectedApp.getProject().getNum2Rooms();
                        num2Room++;
                        selectedApp.getProject().setNum2Rooms(num2Room);
                        System.out.println("this is also: " + num2Room);
                    }
                    else if(flatType == 3){
                        int num3Room = selectedApp.getProject().getNum3Rooms();
                        num3Room++;
                        selectedApp.getProject().setNum3Rooms(num3Room);
                    }
                }
            } else if (appChoice == 2) {
                selectedApp.rejectWithdrawal();
                System.out.println(selectedApp.getApplicant().getName() + "'s withdrawal of application for " + selectedApp.getProject().getName() + " is rejected.");
            } else if (appChoice == 0) {
                continue;
            }
        }
    }
    
    
    public void generateReport() {
        List<Application> appList = ApplicationController.getAllApplications();
        for(Application a : appList){
            if(a.getAppliedStatus().equals("booked")){
                Report rep = new Report(a,a.getApplicant());
                if(!reportRepository.containsReport(rep)){
                    reportRepository.addReport(rep);
                }
            }
        }
        viewApplicantReports();
    }
    
    
    public void viewApplicantReports() {
        ArrayList<Report> reports = new ArrayList<>();
        reports = ReportRepository.getAllReports();
        ArrayList<Report> filteredReports = new ArrayList<>(reports);

        boolean filtering = true;
        while (filtering) {
        System.out.println("========== Applicant Report Filter ==========");
        System.out.println("(1) No Filter / Generate Reports");
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
                System.out.println("Finalizing filters and generating report...");
                filtering = false;
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
                        filteredReports = ReportRepository.filterUnder25(filteredReports);
                        break;
                    case 2:
                        filteredReports = ReportRepository.filter25to35(filteredReports);
                        break;
                    case 3:
                        filteredReports = ReportRepository.filter35to45(filteredReports);
                        break;
                    case 4:
                        filteredReports = ReportRepository.filterOver45(filteredReports);
                        break;
                    default:
                        System.out.println("Invalid choice, no filter applied.");
                }
                break;
            case 3:
                System.out.println("Select: (1) Married, (2) Single");
                int maritalChoice = Integer.parseInt(scanner.nextLine());
                boolean isMarried = (maritalChoice == 1);
                filteredReports = ReportRepository.filterMarried(filteredReports,isMarried);
                break;
            case 4:
                System.out.print("Enter Project Name: ");
                String projectName = scanner.nextLine();
                filteredReports = ReportRepository.filterProjects(filteredReports,projectName);
                break;
            case 5:
                System.out.println("Select Flat Type: (1) 2-Room, (2) 3-Room");
                int roomChoice = Integer.parseInt(scanner.nextLine());
                boolean is2Room = (roomChoice == 1);
                filteredReports = ReportRepository.filterFlatType(filteredReports,is2Room);
                break;
            default:
                System.out.println("Invalid choice. No filter applied.");
        }
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

    public void toggleVisibility(){
        List<Project> projects = ProjectController.getUserProjects(manager);
        if (projects.isEmpty()) {
            System.out.println("You are currently in charge of no projects.");
            return;
        }
        System.out.println("Projects you're in charge of:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ". " + projects.get(i).getName());
        }
        System.out.print("Choose project to toggle visibility of: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > projects.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Project selectedProject = projects.get(choice - 1);

        selectedProject.setVisibility(!selectedProject.getVisibility());
        System.out.println("Project '" + selectedProject.getName() + "' visibility is set to " + selectedProject.getVisibility());
    }
}
