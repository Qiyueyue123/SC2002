public class Report {
    private Application application;
	private Applicant applicant;
	
	//only create reports of booked projects
	Report(Application application, Applicant applicant) {
		this.application = application;
		this.applicant  = applicant;
	}
	
	public Application getApplication() {
		return application;
	}
	
	public Applicant getApplicant() {
		return applicant;
	}

	public void printReport() {
		String marriedStatus = "NIL";
		
		if (applicant.isMarried()) {
			marriedStatus = "Married";
		}
		else {
			marriedStatus = "Single";
		}
		
		Project project = application.getProject();
		System.out.println("============Report============");
		System.out.println("Applicant Details:");
		System.out.println("Age: " + applicant.getAge());
		System.out.println("Marital Status: " + marriedStatus);
		System.out.println("------------------------------");
		System.out.println("Booked Project Details:");
		System.out.println("Name: " + project.getName());
		System.out.println("Flat Tyoe: " + application.getFlatType() + " room");
		System.out.println();
	}
	
	public void printReceipt() {
		String marriedStatus = "NIL";
		
		if (applicant.isMarried()) {
			marriedStatus = "Married";
		}
		else {
			marriedStatus = "Single";
		}
		
		Project project = application.getProject();
		System.out.println("============Receipt============");
		System.out.println("Applicant Details:");
		System.out.println("Applicant: " + applicant.getName());
		System.out.println("NRIC: " + applicant.getNRIC());
		System.out.println("Age: " + applicant.getAge());
		System.out.println("Marital Status: " + marriedStatus);
		System.out.println("------------------------------");
		System.out.println("Booked Project Details:");
		System.out.println("Name: " + project.getName());
		System.out.println("Neigbourhood: " + project.getNeighborhood());
		System.out.println("Flat Tyoe: " + application.getFlatType() + " room");
		System.out.println();
	}
}
