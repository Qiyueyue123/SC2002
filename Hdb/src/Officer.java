import java.util.ArrayList;
import java.util.Scanner;

public class Officer extends Applicant {
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
        ArrayList<Project> projects = ProjectList.getAllProjects();
        Scanner sc = new Scanner(System.in);
        //need to add check for avail officer slots < 10
        System.out.println("Available Projects:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ". " + projects.get(i).getName());
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
        if (registration == null || registration.status != "Approved" ) {
            System.out.println("You are currently not handling any projects");    
            System.out.println();                  
        } else {
            registration.project.print();
            System.out.println();
        }
    }

    public void viewProjectEnquiry() {
        if (registration == null || registration.status != "Approved" ) {
            System.out.println("You are currently not handling any projects");    
            System.out.println();                  
        } else {
            EnquiryList.showProjEnquiries(registration.project);
        }
    }
    public void replyEnquiry() {
        if (registration == null || registration.status != "Approved" ) {
            System.out.println("You are currently not handling any projects");    
            System.out.println();                  
        } else {
            ArrayList<Enquiry> unansweredEnquiries = EnquiryList.getProjEnquiries(registration.project);
            if (!unansweredEnquiries.isEmpty()) {
                EnquiryList.showProjEnquiries(registration.project);
                
                System.out.println("Which enquiry would you like to reply? Input ID:");
                int enquiryNum = scan.nextInt();
                scan.nextLine(); //needs this after every scanInt
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

	
}
