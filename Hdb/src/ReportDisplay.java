public class ReportDisplay {
    
    public static void printReport(Report report) {
        String marriedStatus = report.getApplicant().isMarried() ? "Married" : "Single";
        System.out.println("============ Report ============");
        System.out.println("Applicant Details:");
        System.out.println("Age: " + report.getApplicant().getAge());
        System.out.println("Marital Status: " + marriedStatus);
        System.out.println("------------------------------");
        System.out.println("Booked Project Details:");
        System.out.println("Name: " + report.getApplication().getProject().getName());
        System.out.println("Flat Type: " + report.getApplication().getFlatType() + " room");
        System.out.println();
    }
    
    public static void printReceipt(Report report) {
        String marriedStatus = report.getApplicant().isMarried() ? "Married" : "Single";
        System.out.println("============ Receipt ============");
        System.out.println("Applicant Details:");
        System.out.println("Applicant: " + report.getApplicant().getName());
        System.out.println("NRIC: " + report.getApplicant().getNRIC());
        System.out.println("Age: " + report.getApplicant().getAge());
        System.out.println("Marital Status: " + marriedStatus);
        System.out.println("------------------------------");
        System.out.println("Booked Project Details:");
        System.out.println("Name: " + report.getApplication().getProject().getName());
        System.out.println("Neighborhood: " + report.getApplication().getProject().getNeighborhood());
        System.out.println("Flat Type: " + report.getApplication().getFlatType() + " room");
        System.out.println();
    }

    public static void printReportList(ArrayList<Report> list) {
		int i = 1;
		for (Report r: list) {
			System.out.println("Report " + i + ".");
			r.printReport();
			System.out.println();
			i++;
		}
	}
}
