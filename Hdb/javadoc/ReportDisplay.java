import java.util.ArrayList;

/**
 * Boundary. <p>
 * Utility class for displaying reports and receipts related to BTO applications.
 * Provides static methods to print formatted output for a single report or a list of reports.
 */
public class ReportDisplay {

    /**
     * Prints a simple report showing the applicant's name, NRIC, age, marital status,
     * and the booked project details such as name, neighborhood and flat type.
     *
     * @param report the report to print
     */
    public static void printReport(Report report) {
        String marriedStatus = report.getApplicant().isMarried() ? "Married" : "Single";
        System.out.println("============ Report ============");
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

    /**
     * Prints a receipt including detailed information such as the applicant's name, NRIC, age,
     * marital status, and the booked project's name, neighborhood, and flat type.
     *
     * @param report the report to print the receipt for
     */
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

    /**
     * Prints a numbered list of reports using {@code printReport()} for each.
     *
     * @param list the list of reports to print
     */
    public static void printReportList(ArrayList<Report> list) {
        int i = 1;
        for (Report r : list) {
            System.out.println("Report " + i + ".");
            printReport(r); // fixed from r.printReport()
            System.out.println();
            i++;
        }
    }
}
