import java.util.ArrayList;
import java.util.List;

/**
 * Entity.<p>
 * Repository for managing a static list of {@link Manager} objects.
 * Provides methods to retrieve, update, and search for managers by name or NRIC.
 */
public class ManagerRepository {
    /**
     * The static list that stores all managers.
     */
    private static List<Manager> managers = new ArrayList<>();

    /**
     * Retrieves the list of all managers.
     *
     * @return the list of {@link Manager} objects.
     */
    public static List<Manager> getManagers() {
        return managers;
    }

    /**
     * Replaces the current list of managers with a new list.
     *
     * @param newManagers the new list of {@link Manager} objects to set
     */
    public static void setManagers(List<Manager> newManagers) {
        managers = newManagers;
    }

    /**
     * Searches for a manager in the list by their name (case-insensitive).
     *
     * @param name the name of the manager to find
     * @return the {@link Manager} with the matching name, or {@code null} if not found
     */
    public static Manager findManagerByName(String name) {
        for (Manager manager : managers) {
            if (manager.getName().equalsIgnoreCase(name)) {
                return manager;
            }
        }
        return null;
    }

    /**
     * Searches for a manager in the list by their NRIC (case-insensitive).
     *
     * @param nric the NRIC of the manager to find
     * @return the {@link Manager} with the matching NRIC, or {@code null} if not found
     */
    public static Manager findManagerByNRIC(String nric) {
        for (Manager manager : managers) {
            if (manager.getNRIC().equalsIgnoreCase(nric)) {
                return manager;
            }
        }
        return null;
    }
}
