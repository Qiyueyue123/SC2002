import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Controller.
 * Handles operations related to project manipulation and filtering logic.
 * This includes sorting, searching, and filtering projects based on various criteria.
 */
public class ProjectController {

    /**
     * Sorts all projects by their names in ascending order and persists the sorted list.
     */
    public static void sortProjName() {
        ArrayList<Project> all = ProjectRepository.getAllProjects();
        all.sort(Comparator.comparing(Project::getName));
        ProjectRepository.setProjects(all); // persist sorted
    }

    /**
     * Retrieves all projects created by a specific manager, sorted by project name.
     *
     * @param user The manager whose projects are to be retrieved.
     * @return A list of projects managed by the given manager.
     */
    public static ArrayList<Project> getUserProjects(Manager user) {
        sortProjName();
        return ProjectRepository.getAllProjects().stream()
                .filter(p -> p.getManager().equals(user))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters the given list of projects to include only those in the specified neighbourhood.
     *
     * @param list The list of projects to filter.
     * @param neighbourhood The neighbourhood to filter by.
     * @return A list of projects in the specified neighbourhood.
     */
    public static ArrayList<Project> filterNeighbourhood(ArrayList<Project> list, String neighbourhood) {
        return list.stream()
                .filter(p -> p.getNeighborhood().equalsIgnoreCase(neighbourhood))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters the given list of projects based on flat type availability.
     *
     * @param list The list of projects to filter.
     * @param is2Room True to filter for projects with available 2-room flats, false for 3-room.
     * @return A list of projects matching the flat type criteria.
     */
    public static ArrayList<Project> filterFlatType(ArrayList<Project> list, boolean is2Room) {
        return list.stream()
                .filter(p -> is2Room ? p.getNum2Rooms() > 0 : p.getNum3Rooms() > 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Finds a project by its name (case-insensitive).
     *
     * @param name The name of the project to find.
     * @return The matching project if found, otherwise null.
     */
    public static Project findProjectByName(String name) {
        return ProjectRepository.getAllProjects().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
