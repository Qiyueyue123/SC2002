import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller. <p>
 * This {@code ProjectController} class handles operations related to project manipulation and filtering logic.
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
     * Returns all projects created by a specific manager, sorted by project name.
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
     * Displays all projects using ProjectDisplay method.
     */
    public static void showAllProjects(){
        ProjectDisplay.showAllProjects();
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
    /**
     * Returns a list of all projects in the repository.
     *
     * @return An {@link ArrayList} of all projects.
     */
    public static ArrayList<Project> getAllProjects(){
         return ProjectRepository.getAllProjects();
     }
     
    /**
     * Updates the attributes of an existing project with new values.
     *
     * @param proj             The existing {@link Project} to update.
     * @param name             The new project name.
     * @param neighbourhood    The new neighbourhood.
     * @param visibility       The new visibility status.
     * @param num2Rooms        The new number of 2-room flats.
     * @param num3Rooms        The new number of 3-room flats.
     * @param openingDate      The new opening date.
     * @param closingDate      The new closing date.
     * @param availOfficerSlots The new number of available officer slots.
     * @param manager          The manager of the project.
     * @param price2room       The price for 2-room flats.
     * @param price3room       The price for 3-room flats.
     */
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
      /**
     * Deletes the specified project from the repository.
     *
     * @param proj The {@link Project} to delete.
     */
     public static void deleteProject(Project proj){
         ProjectRepository.deleteProject(proj);
     }
     
     /**
     * Returns a list of all projects that have available officer slots.
     *
     * @return A list of {@link Project} instances with available officer slots.
     */
     public static List<Project> getAvailProjects(){
         return ProjectRepository.getAllProjects().stream()
         .filter(p -> p.getAvailOfficerSlots() > 0)
         .toList();
     }
}
