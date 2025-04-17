import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity. <p>
 * This {@code ReportRepository} manages a collection of reports and provides
 * methods to add, retrieve, sort, and filter them based on criteria
 * such as age, project, marital status, and flat type.
 */
public class ReportRepository {
    
    /** Static list holding all report entries. */
    private static List<Report> reports;

    /**
     * Constructs an empty ReportRepository.
     */
    public ReportRepository() {
        reports = new ArrayList<>();
    }

    /**
     * Adds a report to the repository.
     *
     * @param report the Report object to be added
     */
    public void addReport(Report report) {
        reports.add(report);
    }

    /**
     * Returns a sorted list of all reports by project name.
     *
     * @return a new ArrayList containing sorted reports
     */
    public static ArrayList<Report> getAllReports() {
        sortByProjects();
        return new ArrayList<>(reports);
    }

    /**
     * Sorts the internal list of reports by project name alphabetically.
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

    // === AGE FILTERS ===

    /**
     * Filters applicants younger than 25 years old.
     *
     * @param list input list of reports
     * @return filtered list of reports with applicants under 25
     */
    public static ArrayList<Report> filterUnder25(ArrayList<Report> list) {
        return list.stream()
                   .filter(r -> r.getApplicant().getAge() < 25)
                   .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters applicants aged between 25 and 34 (inclusive of 25, exclusive of 35).
     *
     * @param list input list of reports
     * @return filtered list of reports
     */
    public static ArrayList<Report> filter25to35(ArrayList<Report> list) {
        return list.stream()
                   .filter(r -> r.getApplicant().getAge() >= 25 && r.getApplicant().getAge() < 35)
                   .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters applicants aged between 35 and 44 (inclusive of 35, exclusive of 45).
     *
     * @param list input list of reports
     * @return filtered list of reports
     */
    public static ArrayList<Report> filter35to45(ArrayList<Report> list) {
        return list.stream()
                   .filter(r -> r.getApplicant().getAge() >= 35 && r.getApplicant().getAge() < 45)
                   .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters applicants aged 45 and above.
     *
     * @param list input list of reports
     * @return filtered list of reports
     */
    public static ArrayList<Report> filterOver45(ArrayList<Report> list) {
        return list.stream()
                   .filter(r -> r.getApplicant().getAge() >= 45)
                   .collect(Collectors.toCollection(ArrayList::new));
    }

    // === PROJECT, MARITAL STATUS, AND FLAT TYPE FILTERS ===

    /**
     * Filters reports by a specific project name.
     *
     * @param list input list of reports
     * @param project the name of the project to filter by
     * @return filtered list of reports belonging to the given project
     */
    public static ArrayList<Report> filterProjects(ArrayList<Report> list, String project) {
        return list.stream()
                   .filter(r -> r.getApplication().getProject().getName().equals(project))
                   .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters reports based on applicant marital status.
     *
     * @param list input list of reports
     * @param isMarried true for married applicants, false for single
     * @return filtered list of reports
     */
    public static ArrayList<Report> filterMarried(ArrayList<Report> list, boolean isMarried) {
        sortByProjects();
        ArrayList<Report> marriedReports = list.stream()
                                               .filter(r -> r.getApplicant().isMarried())
                                               .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Report> singleReports = list.stream()
                                              .filter(r -> !r.getApplicant().isMarried())
                                              .collect(Collectors.toCollection(ArrayList::new));
        return isMarried ? marriedReports : singleReports;
    }

    /**
     * Filters reports based on whether a project offers 2-room or 3-room flats.
     *
     * @param list input list of reports
     * @param is2Room true to filter 2-room flats, false for 3-room
     * @return filtered list of reports with the selected flat type
     */
    public static ArrayList<Report> filterFlatType(ArrayList<Report> list, boolean is2Room) {
        ArrayList<Report> twoRoomList = list.stream()
                                            .filter(r -> r.getApplication().getProject().getNum2Rooms() > 0)
                                            .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Report> threeRoomList = list.stream()
                                              .filter(r -> r.getApplication().getProject().getNum3Rooms() > 0)
                                              .collect(Collectors.toCollection(ArrayList::new));
        return is2Room ? twoRoomList : threeRoomList;
    }
}
