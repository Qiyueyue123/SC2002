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
}
