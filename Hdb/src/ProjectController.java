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

    public static void getAllProjects(){
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
}
