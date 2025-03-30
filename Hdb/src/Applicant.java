import java.util.ArrayList;
import java.util.Scanner;

//applicant can do evrything needed with enquiries
//reply enquiry will be moved when officer and manager class is settled
public class Applicant extends User{
    String name;
	Scanner scan = new Scanner(System.in);
	
	public Applicant(String nric, String name,String password, int age, boolean married) {
		super(nric,name,password,age,married);
	}
	
	public String getName() {
		return name;
	}
	
	public void viewEnquiry () { 
		EnquiryList.showUserEnquiries(this);
	}
	
    public void createEnquiry(Project chosenProj, String msg) {
	    Enquiry enquiry = new Enquiry(this, chosenProj);    
	    enquiry.setMessage(msg);
	    EnquiryList.addEnquiry(enquiry);
    	}
    	
    public void editEnquiry(Enquiry editedEnquiry, String msg) {
    	editedEnquiry.setMessage(msg);
    }
    
    public void deleteEnquiry(Enquiry delEnquiry) {
    	ArrayList<Enquiry> allEnquiries = EnquiryList.getAllEnquiries();
    	allEnquiries.remove(delEnquiry); 
    	delEnquiry = null;
    }
    
    // reply enquiry has 2 ways for it to go, one is the all projects enquiry that a manager can do, 
    // one is the specific proj enq for officers 
    // for now this coded so that all unanswered enquiries are shown and can be replied
   public void replyEnquiry(Enquiry unansweredEnquiry, String msg) {
	        unansweredEnquiry.setResponse(msg);	
    }
}
