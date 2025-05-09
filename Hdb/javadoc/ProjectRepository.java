import java.util.ArrayList;

/**
 * Entity. <p>
 * This {@code ProjectRepository} manages a collection of {@link Project} instances.
 * <p>
 * Provides methods to add, retrieve, update, and delete projects.
 */
public class ProjectRepository {
    private static ArrayList<Project> projects = new ArrayList<>();

    /**
     * Adds a new project to the repository.
     *
     * @param project The project to add.
     */
    public static void addProject(Project project) {
        projects.add(project);
    }

    /**
     * Returns the list of all stored projects.
     *
     * @return The list of projects.
     */
    public static ArrayList<Project> getAllProjects() {
        return projects;
    }

    /**
     * Replaces the entire list of projects in the repository.
     *
     * @param loadedProjects The new list of projects.
     */
    public static void setProjects(ArrayList<Project> loadedProjects) {
        projects = loadedProjects;
    }

    /**
     * Checks if there are any projects in the repository.
     *
     * @return {@code true} if the repository is empty, {@code false} otherwise.
     */
    public static boolean isEmpty() {
        return projects.isEmpty();
    }

    /**
     * Deletes the specified project from the repository.
     *
     * @param project The project to remove.
     */
    public static void deleteProject(Project project){
        projects.remove(project);
    }

    // public static void updateProject(Project proj,String name, String neighbourhood, boolean visibility, int num2Rooms, int num3Rooms, String openingDate, String closingDate, 
     // int availOfficerSlots, Manager manager, int price2room, int price3room){
     //     for(Project p : projects){
     //         if(p.getName().equals(proj.getName())){
     //             p.setName(name);
     //             p.setNeighborhood(neighbourhood);
     //             p.setVisibility(visibility);
     //             p.setNum2Rooms(num2Rooms);
     //             p.setNum3Rooms(num3Rooms);
     //             p.setOpeningDate(openingDate);
     //             p.setClosingDate(closingDate);
     //             p.setAvailOfficerSlots(availOfficerSlots);
     //             p.setPrice2Room(price2room);
     //             p.setPrice3Room(price3room);
     //         }
     //     }
     // }
}
