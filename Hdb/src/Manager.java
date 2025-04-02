import java.util.ArrayList;

import com.apple.eawt.Application;

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

   // project management

   public void createProject(String name, String neighborhood, boolean visibility, int num2Rooms, int num3Rooms,
                            String openingDate, String closingDate, int availOfficerSlots)
							
		{
			Project project = new Project(name, neighborhood, visibility, num2Rooms, num3Rooms, openingDate, closingDate, availOfficerSlots, this);
			ProjectList.addProject(project);
			System.out.println("Project " + name + "created successfully.\n");
		}

   public void editProject(Project project,String newName, String newNeighborhood,
   							boolean newVisibility, int newNum2Rooms, int newNum3Rooms,
   							String newOpeningDate, String newClosingDate, int newAvailableOfficerSlots)
		{
			project.setName(newName);
			project.setNeighborhood(newNeighborhood);
			project.setVisibility(newVisibility);
			project.setNum2Rooms(newNum2Rooms);
			project.setNum3Rooms(newNum3Rooms);
			project.setOpeningDate(newOpeningDate);
			project.setClosingDate(newClosingDate);
			project.setAvailOfficerSlots(newAvailableOfficerSlots);

			System.out.println("Project" + name + " updated successfully.");
				
   		}
		
	public void deleteProject(Project project){
		ProjectList.getAllProjects().remove(project);
		System.out.println("Project " + project.getName() + " removed successfully.");
	}

	public void toggleVisibility(Project p){
		p.toggleVisibility();

		if(p.visibility == true){
			System.out.println("Project " + p.getName() + " is now ON(visible to applicants)");
		}
		else{
			System.out.println("Project " + p.getName() + " is now OFF (hidden from applicants)");
		}
		
	}

	public void viewMyProjects(){
		// create array of projects under current manager
		ArrayList<Project> myProjects = new ArrayList<>();

		for( Project p : ProjectList.getAllProjects() ){
			if( p.getManager().equals(this) ){
				myProjects.add(p);
			}
		}

		if( myProjects.isEmpty() ){
			System.out.println(" No projects under this manager yet ");
		}else{
			System.out.println(" Projects under this manager: ");

			for( Project p: myProjects ){
				p.print();
				System.out.println();
			}
		}
	}

	// Approve BTO for applicant 
	// now parameters are the applicant object, i dont know if there is a need to manually pass the flattype in

	public void approveApplicantApplication(Applicant applicant){

		Application app = applicant.getApplication();
		Project project = app.getProject();
		int flattype = app.getFlatType();

		if (flatType == 2 && project.getNum2Rooms() > 0) {
			app.setAppliedStatus("Successful");
			project.minusNum2Rooms();
			System.out.println("Applicant " + applicant.getName() + 
							   " application approved for a 2-room flat in project " + project.getName() + ".\n");
		} else if (flatType == 3 && project.getNum3Rooms() > 0) {
			app.setAppliedStatus("Successful");
			project.minusNum3Rooms();
			System.out.println("Applicant " + applicant.getName() + 
							   " application approved for a 3-room flat in project " + project.getName() + ".\n");
		} else {
			System.out.println("No available flats of the selected type in project " + project.getName() + ".\n");
		}
	}

	public void rejectApplicantApplication(Applicant applicant) {
        applicant.getApplication().setAppliedStatus("Unsuccessful");
        System.out.println("Applicant " + applicant.getName() + "'s application has been rejected.\n");
    }

	//approve officer registration to a project
	public void approveOfficerRegistration(Officer officer, Project project) {
		if (project.getAvailOfficerSlots() > 0) {
			// wait for josh to set the method in officer class
			// officer.setRegistrationStatus("Approved");
			project.minusAvailableOfficerSlots();
			System.out.println("Officer " + officer.getName() + " approved for project " 
							   + project.getName() + ". Remaining officer slots: " 
							   + project.getAvailOfficerSlots() + ".\n");
		} else {
			System.out.println("No available HDB officer slots for project " + project.getName() + ".\n");
		}
	}
	
	//reject officer registration
	public void rejectOfficerRegistration(Officer officer, Project project) {
		// wait for josh to set method
		// officer.setRegistrationStatus("Rejected");
		System.out.println("Officer " + officer.getName() + "'s registration for project " 
						   + project.getName() + " has been rejected.\n");
	}




	public void generateReport() {
        System.out.println("Generating report of applicants and their flat booking details...\n");
        // Dk what to report yet..
    }
	
}
