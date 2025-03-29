import java.util.ArrayList;
import java.util.Comparator;

//needs more work very rudimentary stuff avalaible for now
//refer to enquiry list for more comprehensive look

public class ProjectList {
    private static ArrayList<Project> projects; 
	
	public ProjectList() {
		projects = new ArrayList<Project>();
	}
	
	//To sort by alphabetical order
	public static void sortProjName() {
		projects.sort(new Comparator<Project>() {
			@Override
			public int compare(Project p1, Project p2) {
				return p1.getName().compareTo(p2.getName());
			}
		});
	}
	
	public static void showAllProjects() {
		int i = 1;
		sortProjName();
		
		if (projects.isEmpty()) {
			System.out.println("There are no projects.");
		}
		
		for (Project p : projects) {
			System.out.println(i + ". " + p.getName());
			i++;
		}
	}
	
	public static ArrayList<Project> getAllProjects() {
		sortProjName(); //sort list before returning
		return projects;
	}
	
	public static void addProject(Project project) {
		projects.add(project);
	}
	
	public static boolean isEmpty() {
		return projects.isEmpty();
	}
}
