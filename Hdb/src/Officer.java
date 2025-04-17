import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Officer extends Applicant {
    private Project assignedProject;

    public Officer(String nric, String name, String password, int age, boolean married) {
        super(nric, name, password, age, married);
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
    }
    @Override
    public void display(){
        OfficerDisplay display = new OfficerDisplay(this);
        display.showDisplay();
    }
    /*
	private Project assignedProject;
	private Registration registration;
    public Officer(String nric, String name, String password, int age, boolean married) {
		super(nric,name,password,age,married);
	}

	public Project getAssginedProject(){
		return assignedProject;
	}

	public void setAssignedProject(Project project){
		this.assignedProject = project;
	}

	public void registerAsOIC() {
        List<Project> projects = ProjectRepository.getAllProjects();
        Scanner sc = new Scanner(System.in);
        int i = 0;
        System.out.println("Available Projects:");
        for (Project p : projects) {
            if (p.getAvailOfficerSlots() > 0) {
                System.out.println((i + 1) + ". " + p.getName());
                i++;
            }
        }

        System.out.print("Choose project to request OIC role: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > projects.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Project selected = projects.get(choice - 1);
        if (ApplicationList.hasUserAppliedToProject(this.getNRIC(), selected)) {
            System.out.println("You cannot register as OIC for a project you have applied for.");
            return;
        }

		registration = new Registration(this,selected);
		RegistrationList.addRegistration(registration);
		System.out.println("Your registration request has been submitted for Manager approval.");
	}

	public void viewRegistrationStatus(){
        if (registration == null) {
            System.out.println();
            System.out.println("No applied registration");
            System.out.println();
            return;
        } else {
            registration.print();
        }
	}

    public void viewHandledProject() {
        if (registration == null || registration.getStatus() != "Approved" ) {
            System.out.println("You are currently not handling any projects");    
            System.out.println();                  
        } else {
            registration.getProject().print();
            System.out.println();
        }
    }

    public void viewProjectEnquiry() {
        if (registration == null || registration.getStatus() != "Approved" ) {
            System.out.println("You are currently not handling any projects");    
            System.out.println();                  
        } else {
            EnquiryList.showProjEnquiries(registration.getProject());
        }
    }
    public void replyEnquiry() {
        if (registration == null || registration.getStatus() != "Approved" ) {
            System.out.println("You are currently not handling any projects");    
            System.out.println();                  
        } else {
            ArrayList<Enquiry> unansweredEnquiries = EnquiryList.getProjEnquiries(registration.getProject());
            if (!unansweredEnquiries.isEmpty()) {
                EnquiryList.showProjEnquiries(registration.getProject());
                
                System.out.println("Which enquiry would you like to reply? Input ID:");
                int enquiryNum = scan.nextInt();
                scan.nextLine(); 
                Enquiry unansweredEnquiry = EnquiryList.selectEnquiry(enquiryNum); 
                
                System.out.println("Write your reply:");
                String reply = scan.nextLine();
                unansweredEnquiry.setResponse(reply);	

            }
            else {
                System.out.println("There are no enquiries to reply.");
            }
        }        
    }

        public void bookFlat() {
            if (assignedProject == null) {
                System.out.println("You are not assigned to any project.");
                return;
            }
    
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Applicant NRIC: ");
            String applicantNRIC = sc.nextLine().trim();
    
            Application application = getApplicationByNRICAndProject(applicantNRIC, assignedProject);
    
            if (application == null) {
                System.out.println("No application found for this applicant in your project.");
                return;
            }
    
            if (!application.getAppliedStatus().equalsIgnoreCase("successful")) {
                System.out.println("Applicant's status is not successful. Cannot proceed with booking.");
                return;
            }
    
            System.out.println("Choose flat type to book: ");
            System.out.println("1. 2-Room");
            System.out.println("2. 3-Room");
            String flatType;
            int choice = sc.nextInt();
            sc.nextLine();
    
            if(choice == 1){
                flatType = "2-Room";
                if (assignedProject.getNum2Rooms() > 0){
                    assignedProject.minusNum2Rooms();
                }
                else{
                    System.out.println("No more " + flatType + " flats available");
                    return;
                }
            }
            else{
                flatType = "3-room";
                if (assignedProject.getNum3Rooms() > 0){
                    assignedProject.minusNum3Rooms();
                }
                else{
                    System.out.println("No more " + flatType + " flats available");
                    return;
                }
            }
    
            application.setAppliedStatus("booked");
            application.setFlatType(choice);
    
            System.out.println("Flat booked successfully for applicant: " + application.getApplicant().getName());
    }

    private Application getApplicationByNRICAndProject(String nric, Project project) {
        ArrayList<Application> applications = ApplicationList.getAllApplications();
        for (Application app : applications) {
            if (app.getApplicant().getNRIC().equalsIgnoreCase(nric) &&
                app.getProject().equals(project)) {
                return app;
            }
        }
        return null;
    }*/
}
