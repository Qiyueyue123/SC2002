import java.util.ArrayList;
import java.util.stream.Collectors;

public class EnquiryList {
    private static ArrayList<Enquiry> enquiries; 
	
	public EnquiryList() {
		enquiries = new ArrayList<Enquiry>();
	}
	
	public static void showAllEnquiries() { //shows all enquiries and bool on all of them to show unanswered?
		int i = 1;
		
		if (enquiries.isEmpty()) {
			System.out.println("There are no enquiries.");
		}
		
		for (Enquiry e : enquiries) {
			Applicant user = e.getUser();
			System.out.println(i + ". " + user.getName() + ", " + /* e.getProject() + */ ": " + e.getMessage());
			i++;
		}
	}
	
	public static ArrayList<Enquiry> getAllEnquiries() {
		return enquiries;
	}
	
	public static void showUserEnquiries(Applicant user) {
		int i = 1;
		
		if (enquiries.isEmpty()) {
			System.out.println("There are no enquiries.");
		}
		
		for (Enquiry e : enquiries) {
			if (e.getUser().equals(user)) {
				System.out.println(i + ". " +/*+ e.getProject() + */ ": " + e.getMessage());
				i++;
			}
		}
	}
	
	public static ArrayList<Enquiry> getUserEnquiries(Applicant user) {
		 ArrayList<Enquiry> userEnquiries = enquiries.stream()
	                .filter(e -> e.getUser().equals(user))
	                .collect(Collectors.toCollection(ArrayList::new)); // Create filtered ArrayList but the objects within are still the og ones
	        
	        return userEnquiries;
	}
	
	/* THIS IS FOR PROJECTS BUT IT IS UNDEFINED FOR NOW
	public static void showProjEnquiries(Project project) {
		int i = 1;
		
		if (enquiries.isEmpty()) {
			System.out.println("There are no enquiries.");
		}
		
		for (Enquiry e : enquiries) {
			if (e.getProject().equals(project)) {
				Applicant user = e.getUser();
				System.out.println(i + ". " + user.getName() + ": " + e.getMessage());
				}
		}
	}	
	
	public static ArrayList<Enquiry> getProjEnquiries(Project project) {
		 ArrayList<Enquiry> projEnquiries = enquiries.stream()
	                .filter(e -> e.getProject().equals(project))
	                .collect(Collectors.toCollection(ArrayList::new)); // Create filtered ArrayList but the objects within are still the og ones
	        
	        return userEnquiries;
	}
	
	*/
	
	
	
	public static void addEnquiry(Enquiry enquiry) {
		enquiries.add(enquiry);
	}
}
