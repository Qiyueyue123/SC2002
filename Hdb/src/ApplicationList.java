import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.apple.eawt.Application;

//withdrawal req might have 2 change bc its prob specific to the project the manager and officer manages
//for BTO Applications

//List of BTO Applications:
//NRIC: SXXXXXXXE | All Groves | Status: Pending
//| Withdrawal requested: true


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
				System.out.println("| Withdrawal requested: " + a.getWithdrawalRequest());
				System.out.println();
			}
		}
	}
	
	
	public static void showWithdrawalRequested(Manager manager) {
		sortByProjects();
		ArrayList<Application> withdrawalRequested = getWithdrawalRequested(manager);
		
		if (withdrawalRequested.isEmpty()) {
			System.out.println("There are no applications.");
		}
		else {
			System.out.println("List of BTO Applications:");
			for (Application a: withdrawalRequested) {
				if (a.getWithdrawalRequest()) {
					Applicant applicant = a.getApplicant();
					Project appliedProject = a.getProject();
					System.out.println("NRIC: " + applicant.getNRIC() + " | " + appliedProject.getName() + " | Status: " + a.getAppliedStatus());
					System.out.println("| Withdrawal requested: " + a.getWithdrawalRequest());
					System.out.println();
				}
			}
		}
	}
	
	public static ArrayList<Application> getWithdrawalRequested(Manager manager) {
		sortByProjects();
		ArrayList<Application> withdrawalRequested = applications.stream()
                .filter(a -> a.getWithdrawalRequest() && a.getProject().getManager().equals(manager))
                .collect(Collectors.toCollection(ArrayList::new));
		return withdrawalRequested;
	}
	
	public static Application selectApplication(String nric) {
		for (Application a: applications) {
			Applicant applicant = a.getApplicant();
			if (applicant.getNRIC().equals(nric)) {
				return a;
			}
		}
		System.out.println("Application does not exist!");
		return null;
	}
	
	public static void addApplication(Application application) {
		applications.add(application);
	}


	public static ArrayList<Application> getPendingApplicationsForManager(Manager manager) {
        ArrayList<Application> pendingApps = new ArrayList<>();
        for (Application app : applications) {
            // Check if the application's status is "Pending" and if the project manager matches the given manager
            if (app.getAppliedStatus().equalsIgnoreCase("Pending") && app.getProject().getManager().equals(manager)) {
                pendingApps.add(app);
            }
        }
        return pendingApps;
    }
	
	
	public static boolean isEmpty() {
		return applications.isEmpty();
	}
	
	public static boolean hasUserAppliedToProject(String nric, Project project) {
        ArrayList<Application> applications = ApplicationList.getAllApplications();
        for (Application app : applications) {
            if (app.getApplicant().getNRIC().equalsIgnoreCase(nric)
                && app.getProject().equals(project)) {
                return true;
            }
        }
        return false;
    }
}

