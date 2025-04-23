import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class OfficerController {
    private Officer officer;
    private Scanner scanner = new Scanner(System.in);

    public OfficerController(Officer officer) {
        this.officer = officer;
    }

    public void registerAsOIC() {
        List<Project> availableProjects = ProjectController.getAvailProjects();

        if (availableProjects.isEmpty()) {
            System.out.println("No available projects for officer registration.");
            return;
        }

        System.out.println("Available Projects:");
        for (int i = 0; i < availableProjects.size(); i++) {
            System.out.println((i + 1) + ". " + availableProjects.get(i).getName());
        }

        System.out.print("Choose project to request OIC role: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > availableProjects.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Project selected = availableProjects.get(choice - 1);

        if(RegistrationController.isOfficerAlreadyPending(selected, officer)){
            System.out.println("You have already registered for this project");
            return;
        } 
        if (ApplicationController.getApplicationByNRICAndProject(officer.getNRIC(), selected) != null) {
            System.out.println("You cannot register for a project you have applied for");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        List<Registration> approvedRegistrations = RegistrationController.getOfficerApprovedRegistration(officer);
        LocalDate selectedOpening = LocalDate.parse(selected.getOpeningDate(),formatter);
        LocalDate selectedClosing = LocalDate.parse(selected.getClosingDate(),formatter);
        for (Registration reg : approvedRegistrations) {
            Project approvedProject = reg.getProject();
            LocalDate approvedOpening = LocalDate.parse(approvedProject.getOpeningDate(),formatter);
            LocalDate approvedClosing = LocalDate.parse(approvedProject.getClosingDate(),formatter);
        
            if (!selectedOpening.isAfter(approvedClosing) && !approvedOpening.isAfter(selectedClosing)) {
                System.out.println("Registration conflict: You are already approved as OIC for project '"
                + approvedProject.getName() + "', whose registration period overlaps with the selected project.");
                return;
            }
        }
        // Registration reg = new Registration(officer, selected);
        // RegistrationRepository.addRegistration(reg);
        RegistrationController.addRegistration(officer, selected);
        System.out.println("Registration submitted and pending manager approval.");
    }

    public void viewAssignedProject() {
        if (officer.getAssignedProject() != null) {
            System.out.println("Assigned Project: ");
            ProjectDisplay.printProj(officer.getAssignedProject());
        } else {
            System.out.println("No assigned project.");
        }
    }

    public void viewRegistrationStatus() {
        List<Registration> all = RegistrationController.getAllRegistrations();
        boolean found = false;
        for (Registration r : all) {
            if (r.getOfficer().equals(officer)) {
                System.out.println("Project: " + r.getProject().getName() + " | Status: " + r.getStatus());
                found = true;
            }
        }
        if (!found) System.out.println("No registration found.");
    }

    public void replyToEnquiry(Enquiry enquiry, String response) {
        enquiry.setResponse(response);
    }

    public void bookFlat() {
        Project assignedProject = officer.getAssignedProject();
        if (assignedProject == null) {
            System.out.println("You are not assigned to any project.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Applicant NRIC: ");
        String applicantNRIC = sc.nextLine().trim();
        Application application = ApplicationController.getApplicationByNRICAndProject(applicantNRIC, assignedProject);

        if (application == null) {
            System.out.println("No application found for this applicant in your project.");
            return;
        }

        if (!application.getAppliedStatus().equalsIgnoreCase("successful")) {
            System.out.println("Applicant's status is not successful. Cannot proceed with booking.");
            return;
        }
        int flatType = application.getFlatType();
        if(flatType == 2){
            if (assignedProject.getNum2Rooms() > 0){
                assignedProject.minusNum2Rooms();
            }
            else{
                System.out.println("No more " + flatType  + "-Room flats available");
                return;
            }
        }
        else if(flatType == 3){
            if (assignedProject.getNum3Rooms() > 0){
                assignedProject.minusNum3Rooms();
            }
            else{
                System.out.println("No more " + flatType + "-Room flats available");
                return;
            }
        }
        application.setAppliedStatus("booked");
        System.out.println("Flat booked successfully for applicant: " + application.getApplicant().getName());
    }

    public ArrayList<Application> getApprovedApplications(){
        // return new ArrayList<>(ApplicationRepository.getAllApplications().stream()
        //         .filter(a -> a.getProject() == officer.getAssignedProject() && "Successful".equalsIgnoreCase(a.getAppliedStatus())).toList());
        return ApplicationController.getApprovedApplications(officer);
    }

    public void generateReceipt(){
        Project assignedProject = officer.getAssignedProject();
        ArrayList<Application> bookedApplications = new ArrayList<>();
        for (Application a : ApplicationController.getAllApplications()){
            if((a.getProject() == assignedProject) && (a.getAppliedStatus().equals("booked"))) {
                bookedApplications.add(a);
            }
        }
        if (bookedApplications.isEmpty()) {
            System.out.println("No booked flats for your assigned project. ");
            System.out.println();
            return;
        }
        int i = 1;
        int choice = 0;
        System.out.println("Select Applicant to generate reciept for: ");
        for (Application a : bookedApplications){
            System.out.println(i + ". " +  a.getApplicant().getName() + ", " + a.getApplicant().getNRIC() + ", " + a.getFlatType());
            i++;
        }  System.out.println("0. Return to homepage");
        choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 0) {
            return;
        }
        Application a = bookedApplications.get(choice-1);
        
        String marriedStatus = a.getApplicant().isMarried() ? "Married" : "Single";
        System.out.println("============ Receipt ============");
        System.out.println("Applicant Details:");
        System.out.println("Applicant: " + a.getApplicant().getName());
        System.out.println("NRIC: " + a.getApplicant().getNRIC());
        System.out.println("Age: " + a.getApplicant().getAge());
        System.out.println("Marital Status: " + marriedStatus);
        System.out.println("------------------------------");
        System.out.println("Booked Project Details:");
        System.out.println("Name: " + a.getProject().getName());
        System.out.println("Neighborhood: " + a.getProject().getNeighborhood());
        System.out.println("Flat Type: " + a.getFlatType() + " room");
        if(a.getFlatType()==2){
            System.out.println("Price: $" + a.getProject().getPrice2Room());
        }
        else{
            System.out.println("Price: $" + a.getProject().getPrice3Room());
        }
        System.out.println();
    }
    }
