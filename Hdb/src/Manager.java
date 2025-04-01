public class Manager extends User{
    public Manager(String nric, String name, String password, int age, boolean married) {
		super(nric,name,password,age,married);
	}

	
	public void viewAllEnquiry() {
		EnquiryList.showAllEnquiries();
	}
	
	public void viewProjectEnquiry(Project project) {
		EnquiryList.showProjEnquiries(project);
	}

   public void replyEnquiry(Enquiry unansweredEnquiry, String msg) {
	        unansweredEnquiry.setResponse(msg);	
    }

   public void approveWithdrawal(Application application) {
	   application.approveWithdrawal();
	   System.out.println("Withdrawal has been approved.");
	   System.out.println();
   }
   
   public void rejectWithdrawal(Application application) {
	   application.rejectWithdrawal();
	   System.out.println("Withdrawal has been rejected");
	   System.out.println();
   }
}
