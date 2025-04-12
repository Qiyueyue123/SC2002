import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReportRepository {
    private List<Report> reports;
    
    public ReportRepository() {
        reports = new ArrayList<>();
    }
    
    public void addReport(Report report) {
        reports.add(report);
    }
    
    public List<Report> getAllReports() {
        sortByProjects();
        return new ArrayList<>(reports);
    }
    
    public void sortByProjects() {
        reports.sort(new Comparator<Report>() {
            @Override
            public int compare(Report r1, Report r2) {
                Project p1 = r1.getApplication().getProject();
                Project p2 = r2.getApplication().getProject();
                return p1.getName().compareTo(p2.getName());
            }
        });
    }
    
    public ArrayList<Report> filterUnder25() {
        return reports.stream()
                .filter(r -> r.getApplicant().getAge() < 25)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<Report> filter25to35() {
        return reports.stream()
                .filter(r -> r.getApplicant().getAge() >= 25 && r.getApplicant().getAge() < 35)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<Report> filter35to45() {
        return reports.stream()
                .filter(r -> r.getApplicant().getAge() >= 35 && r.getApplicant().getAge() < 45)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<Report> filterOver45() {
        return reports.stream()
                .filter(r -> r.getApplicant().getAge() >= 45)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public ArrayList<Report> filterProjects(String projectName) {
        return reports.stream()
                .filter(r -> r.getApplication().getProject().getName().equalsIgnoreCase(projectName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Report> filterMarried(boolean isMarried) {
        return reports.stream()
                .filter(r -> r.getApplicant().isMarried() == isMarried)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Report> filterByFlatType(boolean is2Room) {
        if (is2Room) {
            return reports.stream()
                .filter(r -> r.getApplication().getProject().getNum2Rooms() > 0)
                .collect(Collectors.toCollection(ArrayList::new));
        } else {
            return reports.stream()
                .filter(r -> r.getApplication().getProject().getNum3Rooms() > 0)
                .collect(Collectors.toCollection(ArrayList::new));
        }
    }
}