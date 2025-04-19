import java.util.ArrayList;

public class ProjectDisplay {

    public static void showAllProjects() {
        ArrayList<Project> sortedProjects = ProjectRepository.getAllProjects();
        ProjectController.sortProjName();

        if (sortedProjects.isEmpty()) {
            System.out.println("There are no projects.");
            return;
        }

        int i = 1;
        for (Project p : sortedProjects) {
            System.out.println("Project " + i + ":");
            printProj(p);
            System.out.println();
            i++;
        }
    }

    public static void showUserProjects(Manager user) {
        ArrayList<Project> userProjects = ProjectController.getUserProjects(user);

        if (userProjects.isEmpty()) {
            System.out.println("There are no projects.");
            return;
        }

        System.out.println("List of Projects:");
        int i = 1;
        for (Project p : userProjects) {
            System.out.println("Project " + i + ":");
            printProj(p);
            System.out.println();
            i++;
        }
    }
    

    public static void printProj(Project p) {
        System.out.println("Project Name: " + p.getName());
        System.out.println("Neighborhood: " + p.getNeighborhood());
        System.out.println("Visibility: " + (p.getVisibility()));
        System.out.println("2-Room Units: " + p.getNum2Rooms());
        System.out.println("Price of 2-Room units: $"+ p.getPrice2Room());
        System.out.println("3-Room Units: " + p.getNum3Rooms());
        System.out.println("Price of 3-Room units: $"+ p.getPrice3Room());
        System.out.println("Application Period: " + p.getOpeningDate() + " to " + p.getClosingDate());
        System.out.println("Available Officer Slots: " + p.getAvailOfficerSlots());
    }
}