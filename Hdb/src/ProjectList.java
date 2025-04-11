import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

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
			System.out.println("Project" + i + ":");
			p.print();
			System.out.println();
			i++;
		}
	}

	public static ArrayList<Project> getAllProjects() {
		sortProjName(); //sort list before returning
		return projects;
	}

	//To display enquiry list specific to Manager IC of Project
		public static void showUserProjects(Manager user) {
			int i= 1;
			sortProjName();
			ArrayList<Project> userProjects = getUserProjects(user);
			
			if (userProjects.isEmpty()) {
				System.out.println("There are no projects.");
			}
			
			else {
				System.out.println("List of Projects:");
				for (Project p : userProjects) {
					if (p.getManager().equals(user)) {
						System.out.println("Project" + i + ":");
						p.print();
						System.out.println();
						i++;
						}
					}
				}
			}
			

		
		//To get enquiry list specific to manager
		public static ArrayList<Project> getUserProjects(Manager user) {
			 sortProjName();
			 ArrayList<Project> userProjects = projects.stream()
		                .filter(p -> p.getManager().equals(user))
		                .collect(Collectors.toCollection(ArrayList::new)); // Create filtered ArrayList but the objects within are still the og ones
		        
		        return userProjects;
		}

		public static ArrayList<Project> filterLocations(ArrayList<Project> list, String neighbourhood) {
		 ArrayList<Project> locationList = list.stream()
	               .filter(p -> p.getNeighborhood().equals(neighbourhood))
	               .collect(Collectors.toCollection(ArrayList::new)); // Create filtered ArrayList but the objects within are still the og ones
	       
	       return locationList;
		}

		public static ArrayList<Project> filterFlatType(ArrayList<Project> list, boolean is2Room) {
			 ArrayList<Project> twoRoomList = list.stream()
		              .filter(p -> p.getNum2Rooms() > 0)
		              .collect(Collectors.toCollection(ArrayList::new)); 
			 ArrayList<Project> threeRoomList = list.stream()
		              .filter(p -> p.getNum3Rooms() > 0)
		              .collect(Collectors.toCollection(ArrayList::new)); //returns two room if true, three room if false
			 ArrayList<Project> selectRoomList = is2Room ? twoRoomList : threeRoomList; 
		      return selectRoomList;
		}


	
	public static void addProject(Project project) {
		projects.add(project);
	}
	
	public static boolean isEmpty() {
		return projects.isEmpty();
	}
}
