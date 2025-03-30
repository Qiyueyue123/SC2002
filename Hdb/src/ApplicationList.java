import java.util.ArrayList;
import java.util.Comparator;

//for BTO Applications

//List of BTO Applications:
//NRIC: SXXXXXXXE | All Groves | Status: Pending

public class ApplicationList {
    private static ArrayList<Application> applications;
	
	ApplicationList() {
		applications = new ArrayList<Application>();
	}
	
	public static void sortByProjects() {
		applications.sort(new Comparator<Application>() {
			@Override
			public int compare(Application a1, Application a2) {
				Project p1 = a1.getProject();
				Project p2 = a2.getProject();
				return p1.getName().compareTo(p2.getName());
			}
		});
	}
	
	public static ArrayList<Application> getAllApplications() {
		sortByProjects();
		return applications;
	}
	
	public static void showAllApplications() {
		sortByProjects();
		
		if (applications.isEmpty()) {
			System.out.println("There are no applications.");
		}
		else {
			System.out.println("List of BTO Applications:");
			for (Application a: applications) {
				Applicant applicant = a.getApplicant();
				Project appliedProject = a.getProject();
				System.out.println("NRIC: " + applicant.getNRIC() + " | " + appliedProject.getName() + " | Status: " + a.getAppliedStatus());
			}
		}
	}
	
	public static Application selectApplication(String nric) {
		for (Application a: applications) {
			Applicant applicant = a.getApplicant();
			if (applicant.getNRIC()== nric) {
				return a;
			}
		}
		System.out.println("Application does not exist!");
		return null;
	}
	
	public static void addApplication(Application application) {
		applications.add(application);
	}
	
	
	
	public static boolean isEmpty() {
		return applications.isEmpty();
	}
}
