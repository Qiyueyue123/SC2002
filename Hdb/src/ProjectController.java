import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ProjectController {
    public static void sortProjName() {
        ArrayList<Project> all = ProjectRepository.getAllProjects();
        all.sort(Comparator.comparing(Project::getName));
        ProjectRepository.setProjects(all); // persist sorted
    }

    public static ArrayList<Project> getUserProjects(Manager user) {
        sortProjName();
        return ProjectRepository.getAllProjects().stream()
                .filter(p -> p.getManager().equals(user))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void showAllProjects(){
        ProjectDisplay.showAllProjects();
    }

    public static ArrayList<Project> filterNeighbourhood(ArrayList<Project> list, String neighbourhood) {
        return list.stream()
                .filter(p -> p.getNeighborhood().equalsIgnoreCase(neighbourhood))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Project> filterFlatType(ArrayList<Project> list, boolean is2Room) {
        return list.stream()
                .filter(p -> is2Room ? p.getNum2Rooms() > 0 : p.getNum3Rooms() > 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Project findProjectByName(String name) {
        return ProjectRepository.getAllProjects().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static ArrayList<Project> getAllProjects(){
        return ProjectRepository.getAllProjects();
    }

    public static void updateProject(Project proj,String name, String neighbourhood, boolean visibility, int num2Rooms, int num3Rooms, String openingDate, String closingDate, 
    int availOfficerSlots, Manager manager, int price2room, int price3room){
        ArrayList<Project> projects = ProjectRepository.getAllProjects();
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

    public static void deleteProject(Project proj){
        ProjectRepository.deleteProject(proj);
    }

    public static List<Project> getAvailProjects(){
        return ProjectRepository.getAllProjects().stream()
        .filter(p -> p.getAvailOfficerSlots() > 0)
        .toList();
    }
}
