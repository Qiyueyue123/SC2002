import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * ArrayList class for storing and filtering BTO application reports.
 * Provides methods to sort, retrieve, and filter reports based on
 * age, project name, marital status, and flat type.
 */
public class ReportList {
    
    /** 
     * Static list holding all reports in the system.
     */
    public static ArrayList<Report> reports;

    /**
     * Constructor initializing the report list.
     */
    public ReportList() {
        reports = new ArrayList<Report>();
    }

    /**
     * Sorts the report list based on the project names in alphabetical order.
     */
    public static void sortByProjects() {
        reports.sort(new Comparator<Report>() {
            @Override
            public int compare(Report r1, Report r2) {
                Project p1 = r1.getApplication().getProject();
                Project p2 = r2.getApplication().getProject();
                return p1.getName().compareTo(p2.getName());
            }
        });
    }

    /**
     * Returns the list of all reports sorted by project name.
     *
     * @return sorted list of all reports
     */
    public static ArrayList<Report> getAllReports() {
        sortByProjects();
        return reports;
    }

    /**
     * Prints the details of all reports in a list with a numbered format.
     *
     * @param list the list of reports to print
     */
    public static void printReportList(ArrayList<Report> list) {
        int i = 1;
        for (Report r : list) {
            System.out.println("Report " + i + ".");
            ReportDisplay.printReport(r);
            System.out.println();
            i++;
        }
    }

    // === AGE FILTERS ===

    /**
     * Filters the report list to include only applicants younger than 25.
     *
     * @param list the input report list
     * @return filtered list of reports
     */
    public static ArrayList<Report> filterUnder25(ArrayList<Report> list) {
        return list.stream()
                   .filter(r -> r.getApplicant().getAge() < 25)
                   .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters the report list to include applicants aged between 25 and 34.
     *
     * @param list the input report list
     * @return filtered list of reports
     */
    public static ArrayList<Report> filter25to35(ArrayList<Report> list) {
        return list.stream()
                   .filter(r -> r.getApplicant().getAge() >= 25 && r.getApplicant().getAge() < 35)
                   .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters the report list to include applicants aged between 35 and 44.
     *
     * @param list the input report list
     * @return filtered list of reports
     */
    public static ArrayList<Report> filter35to45(ArrayList<Report> list) {
        return list.stream()
                   .filter(r -> r.getApplicant().getAge() >= 35 && r.getApplicant().getAge() < 45)
                   .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters the report list to include applicants aged 45 and above.
     *
     * @param list the input report list
     * @return filtered list of reports
     */
    public static ArrayList<Report> filterOver45(ArrayList<Report> list) {
        return list.stream()
                   .filter(r -> r.getApplicant().getAge() >= 45)
                   .collect(Collectors.toCollection(ArrayList::new));
    }

    // === PROJECT AND MARITAL FILTERS ===

    /**
     * Filters the report list by a specific project name.
     *
     * @param list    the input report list
     * @param project the name of the project to filter by
     * @return filtered list of reports for the specified project
     */
    public static ArrayList<Report> filterProjects(ArrayList<Report> list, String project) {
        return list.stream()
                   .filter(r -> r.getApplication().getProject().getName().equals(project))
                   .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters the report list based on marital status.
     *
     * @param list      the input report list
     * @param isMarried true to filter married applicants, false for single
     * @return filtered list of reports
     */
    public static ArrayList<Report> filterMarried(ArrayList<Report> list, boolean isMarried) {
        return list.stream()
                   .filter(r -> r.getApplicant().isMarried() == isMarried)
                   .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters the report list based on flat type availability in the project.
     *
     * @param list    the input report list
     * @param is2Room true to filter for projects with 2-room flats, false for 3-room
     * @return filtered list of reports
     */
    public static ArrayList<Report> filterFlatType(ArrayList<Report> list, boolean is2Room) {
        if (is2Room) {
            return list.stream()
                       .filter(r -> r.getApplication().getProject().getNum2Rooms() > 0)
                       .collect(Collectors.toCollection(ArrayList::new));
        } else {
            return list.stream()
                       .filter(r -> r.getApplication().getProject().getNum3Rooms() > 0)
                       .collect(Collectors.toCollection(ArrayList::new));
        }
    }
}
