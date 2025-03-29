
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

// this is to create a single list to handle all enquiries so dupes of ennquiries are not created
// has get and display methods for all, unanswered, user-specific, project-specific enquiries
// add, select, isEmpty functions included 

// when shown it is displayed in form: 
// List of Enquiries:
// [ID 23] GrovesProject | dummyUser123: Message
// | Response: Reply (if answered)

public class EnquiryList {
	private static ArrayList<Enquiry> enquiries; 
	
	EnquiryList() {
		enquiries = new ArrayList<Enquiry>();
	}
	
	//sort by projects in alphabetical order
	public static void sortByProjects() {
		enquiries.sort(new Comparator<Enquiry>() {
			@Override
			public int compare(Enquiry e1, Enquiry e2) {
				Project p1 = e1.getProject();
				Project p2 = e2.getProject();
				return p1.getName().compareTo(p2.getName());
			}
		});
	}
	
	
	
	public static void showAllEnquiries() { 
		sortByProjects();
		
		if (enquiries.isEmpty()) {
			System.out.println("There are no enquiries.");
		}
		
		else {
			System.out.println("List of Enquiries:");
			for (Enquiry e : enquiries) {
				Applicant user = e.getUser();
				Project proj = e.getProject();
				System.out.println("[ID " + e.getID() + "] " + proj.getName() + " | " + user.getName() + ": " + e.getMessage());
				if (e.isAnswered()) {
					System.out.println("| Response:" + e.getResponse());
				}
			}
		}
	}
	
	public static ArrayList<Enquiry> getAllEnquiries() {
		sortByProjects();
		return enquiries;
	}
	
	
	
	//show all unanswered enquiries
	public static void showUnansweredEnquiries() {
		sortByProjects();
		
		if (enquiries.isEmpty()) {
			System.out.println("There are no enquiries.");
		}
		
		else {
			System.out.println("List of Enquiries:");
			for (Enquiry e : enquiries) {
				if (!e.isAnswered()) {
					Applicant user = e.getUser();
					Project proj = e.getProject();
					System.out.println("[ID " + e.getID() + "] " + proj.getName() + " | " + user.getName() + ": " + e.getMessage());
					if (e.isAnswered()) {
						System.out.println("| Response:" + e.getResponse());
					}
				}
			}
		}
	}
	
	//get all unanswered enquiries 
	public static ArrayList<Enquiry> getUnansweredEnquiries() {
		sortByProjects();
		ArrayList<Enquiry> unansweredEnquiries = enquiries.stream()
                .filter(e -> !e.isAnswered())
                .collect(Collectors.toCollection(ArrayList::new));
		return unansweredEnquiries;
	}
	
	
	
	//To display enquiry list specific to user
	public static void showUserEnquiries(Applicant user) {
		sortByProjects();
		
		if (enquiries.isEmpty()) {
			System.out.println("There are no enquiries.");
		}
		
		else {
			System.out.println("List of Enquiries:");
			for (Enquiry e : enquiries) {
				if (e.getUser().equals(user)) {
					Project proj = e.getProject();
					System.out.println("[ID " + e.getID() + "] " + proj.getName() + " | " + user.getName() + ": " + e.getMessage());
					if (e.isAnswered()) {
						System.out.println("| Response:" + e.getResponse());
					}
				}
			}
		}
		
	}
	
	//To get enquiry list specific to user
	public static ArrayList<Enquiry> getUserEnquiries(Applicant user) {
		 sortByProjects();
		 ArrayList<Enquiry> userEnquiries = enquiries.stream()
	                .filter(e -> e.getUser().equals(user))
	                .collect(Collectors.toCollection(ArrayList::new)); // Create filtered ArrayList but the objects within are still the og ones
	        
	        return userEnquiries;
	}
	
	
	
	
	//To display enquiry list specific to project
	public static void showProjEnquiries(Project project) {
		sortByProjects();
		
		if (enquiries.isEmpty()) {
			System.out.println("There are no enquiries.");
		}
		
		else {
			System.out.println("List of Enquiries:");
			for (Enquiry e : enquiries) {
				if (e.getProject().equals(project)) {
					Applicant user = e.getUser();
					System.out.println("[ID " + e.getID() + "] " + project.getName() + " | " + user.getName() + ": " + e.getMessage());
					if (e.isAnswered()) {
						System.out.println("| Response:" + e.getResponse());
					}
				}
			}
		}
	}	
	
	//To get enquiry list specific to project
	public static ArrayList<Enquiry> getProjEnquiries(Project project) {
		 sortByProjects();
		 ArrayList<Enquiry> projEnquiries = enquiries.stream()
	                .filter(e -> e.getProject().equals(project))
	                .collect(Collectors.toCollection(ArrayList::new)); // Create filtered ArrayList but the objects within are still the og ones
	        
	        return projEnquiries;
	}
	
	
	 public static Enquiry selectEnquiry(int no) {
		 for (Enquiry e: enquiries) {
			 if (e.getID() == (no)) {
				 return e;
			 }
		 }
		 System.out.println("Enquiry does not exist!");
		 return null;
	 }
	
	
	public static void addEnquiry(Enquiry enquiry) {
		enquiries.add(enquiry);
	}


	public static boolean isEmpty() {
		return enquiries.isEmpty();
	}
	
}
