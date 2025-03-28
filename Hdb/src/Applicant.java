import java.util.ArrayList;
import java.util.Scanner;

public class Applicant extends User{
    
    String name;
	Scanner scan = new Scanner(System.in);
	
	public Applicant(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void viewEnquiry () { 
		EnquiryList.showUserEnquiries(this);
	}
	
	
    public void createEnquiry() {
        
      //show project list
        Enquiry enquiry = new Enquiry(this);
       
        System.out.println("Write your enquiry:");
        String msg = scan.nextLine(); //might use the end with # to save
        enquiry.setMessage(msg);
        
        EnquiryList.addEnquiry(enquiry);
    }

    
    public void editEnquiry() {
    	
    	EnquiryList.showUserEnquiries(this);
    	ArrayList<Enquiry> userEnquiries = EnquiryList.getUserEnquiries(this);
    	
    	System.out.println("Which enquiry would you like to edit?");
    	int enquiryNo = scan.nextInt();
    	scan.nextLine(); //needs this after every scanInt
    	Enquiry editedEnquiry = userEnquiries.get(enquiryNo-1); //the array starts from 0
    	
    	System.out.println("Write your enquiry:");
        String msg = scan.nextLine(); //might use the end with # to save
    	editedEnquiry.setMessage(msg);
    	
    }
    
    public void deleteEnquiry() {
    	EnquiryList.showUserEnquiries(this);
    	ArrayList<Enquiry> userEnquiries = EnquiryList.getUserEnquiries(this);
    	ArrayList<Enquiry> allEnquiries = EnquiryList.getAllEnquiries();
    	
    	System.out.println("Which enquiry would you like to delete?");
    	int enquiryNo = scan.nextInt();
    	scan.nextLine(); //needs this after every scanInt
    	Enquiry delEnquiry = userEnquiries.get(enquiryNo-1); //array starts from 0
    	
    	allEnquiries.remove(delEnquiry); 
    	userEnquiries.remove(delEnquiry);
    	delEnquiry = null;
    }
}
