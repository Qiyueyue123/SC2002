import java.util.ArrayList;

public class ProjectRepository {
    private static ArrayList<Project> projects = new ArrayList<>();

    public static void addProject(Project project) {
        projects.add(project);
    }

    public static ArrayList<Project> getAllProjects() {
        return projects;
        //or return new ArrayList<>(projects) to retain encapsulation?
    }

    public static void setProjects(ArrayList<Project> loadedProjects) {
        projects = loadedProjects;
    }

    public static boolean isEmpty() {
        return projects.isEmpty();
    }

    public static void deleteProject(Project project){
        projects.remove(project);
    }

    public static void updateProject(Project proj,String name, String neighbourhood, boolean visibility, int num2Rooms, int num3Rooms, String openingDate, String closingDate, 
    int availOfficerSlots, Manager manager, int price2room, int price3room){
        for(Project p : projects){
            if(p.getName().equals(proj.getName())){
                p.setName(name);
                p.setNeighborhood(neighbourhood);
                p.setVisibility(visibility);
                p.setNum2Rooms(num2Rooms);
                p.setNum3Rooms(num3Rooms);
                p.setOpeningDate(openingDate);
                p.setClosingDate(closingDate);
                p.setAvailOfficerSlots(availOfficerSlots);
                p.setPrice2Room(price2room);
                p.setPrice3Room(price3room);
            }
        }
    }
}
