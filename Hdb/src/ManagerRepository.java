import java.util.ArrayList;
import java.util.List;

public class ManagerRepository {
    private static List<Manager> managers = new ArrayList<>();

    public static List<Manager> getManagers() {
        return managers;
    }

    public static void setManagers(List<Manager> newManagers) {
        managers = newManagers;
    }
    
    public static Manager findManagerByName(String name) {
        for (Manager manager : managers) {
            if (manager.getName().equalsIgnoreCase(name)) {
                return manager;
            }
        }
        return null;
    }
}
