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

		//havent created registration class yet
		registeration = new Registration(this,selected);
		RegisterList.addRequest(registration);
		System.out.println("Your registration request has been submitted for Manager approval.");
	}

	public void viewRegistrationStatus(){
	}
	
}
