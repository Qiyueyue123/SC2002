import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ReportList {
    public static ArrayList<Report> reports;
	
ReportList() {
    reports = new ArrayList<Report>();
}

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
	
	public static ArrayList<Report> getAllReports() {
		sortByProjects();
		return reports;
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
	
	//AGE FILTERS
	public static ArrayList<Report> filterUnder25(ArrayList<Report> list) {
		 ArrayList<Report> under25List = list.stream()
	               .filter(r -> r.getApplicant().getAge() < 25)
	               .collect(Collectors.toCollection(ArrayList::new)); // Create filtered ArrayList but the objects within are still the og ones
	       
	       return under25List;
		}
	
	public static ArrayList<Report> filter25to35(ArrayList<Report> list) {
		 ArrayList<Report> inclusive25to35List = list.stream()
	               .filter(r -> r.getApplicant().getAge() >= 25 && r.getApplicant().getAge() < 35)
	               .collect(Collectors.toCollection(ArrayList::new)); // Create filtered ArrayList but the objects within are still the og ones
	       
	       return inclusive25to35List;
		}
	
	public static ArrayList<Report> filter35to45(ArrayList<Report> list) {
		 ArrayList<Report> inclusive35to45List = list.stream()
	               .filter(r -> r.getApplicant().getAge() >= 35 && r.getApplicant().getAge() < 45)
	               .collect(Collectors.toCollection(ArrayList::new)); // Create filtered ArrayList but the objects within are still the og ones
	       
	       return inclusive35to45List;
		}
	
	public static ArrayList<Report> filterOver45(ArrayList<Report> list) {
		 ArrayList<Report> over45List = list.stream()
	               .filter(r -> r.getApplicant().getAge() >= 45)
	               .collect(Collectors.toCollection(ArrayList::new)); // Create filtered ArrayList but the objects within are still the og ones
	       
	       return over45List;
		}
	
	
	
	
	public static ArrayList<Report> filterProjects(ArrayList<Report> list, String project) {
		 ArrayList<Report> projectList = list.stream()
	               .filter(r -> r.getApplication().getProject().getName().equals(project))
	               .collect(Collectors.toCollection(ArrayList::new)); // Create filtered ArrayList but the objects within are still the og ones
	       
	       return projectList;
		}
	
	public static ArrayList<Report> filterMarried(ArrayList<Report> list, boolean isMarried) {
		sortByProjects();
		ArrayList<Report> marriedReports = list.stream()
	        .filter(r -> r.getApplicant().isMarried())
	        .collect(Collectors.toCollection(ArrayList::new)); 
		ArrayList<Report> singleReports = list.stream()
		        .filter(r -> !(r.getApplicant().isMarried()))
		        .collect(Collectors.toCollection(ArrayList::new)); 
		ArrayList<Report> selectReports = isMarried ? marriedReports : singleReports;
		return selectReports;
	}
	
	public static ArrayList<Report> filterFlatType(ArrayList<Report> list, boolean is2Room) {
		 ArrayList<Report> twoRoomList = list.stream()
	              .filter(r -> r.getApplication().getProject().getNum2Rooms() > 0)
	              .collect(Collectors.toCollection(ArrayList::new)); 
		 ArrayList<Report> threeRoomList = list.stream()
	              .filter(r -> r.getApplication().getProject().getNum3Rooms() > 0)
	              .collect(Collectors.toCollection(ArrayList::new)); //returns two room if true, three room if false
		 ArrayList<Report> selectRoomList = is2Room ? twoRoomList : threeRoomList; 
	      return selectRoomList;
		}

}
