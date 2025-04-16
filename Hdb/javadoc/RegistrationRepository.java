import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity. <p>
 * This {@code RegistrationRepository} handles the storage and retrieval of
 * registration data for officers and projects. Provides utility
 * methods to query, filter, and modify the registration list.
 */
public class RegistrationRepository {

    /** Static list to hold all registration entries. */
    private static List<Registration> registrations = new ArrayList<>();

    /**
     * Adds a new registration to the list.
     *
     * @param reg the Registration object to add
     */
    public static void addRegistration(Registration reg) {
        registrations.add(reg);
    }

    /**
     * Returns all registrations.
     *
     * @return list of all registrations
     */
  
    /**
     * Returns the list of all registrations.
     *
     * @return list of Registration objects
     */
    public static List<Registration> getAllRegistrations() {
        return registrations;
    }

    /**
     * Sets the list of registrations, replacing the existing list.
     *
     * @param regis the new list of registrations
     */
    public static void setRegistrations(ArrayList<Registration> regis) {
        registrations = regis;
    }

    /**
     * Returns all pending registrations for a specific project.
     *
     * @param project the project to filter registrations for
     * @return list of pending Registration objects
     */
    public static ArrayList<Registration> getPendingRegistrations(Project project) {
        return registrations.stream()
                .filter(r -> r.getProject().equals(project))
                .filter(r -> r.getStatus().equalsIgnoreCase("Pending"))
                .collect(Collectors.toCollection(ArrayList::new));
    }
  
     /**
     * Checks if an officer has a pending registration for a given project.
     *
     * @param officer the officer to check
     * @param project the project involved
     * @return true if a pending registration exists, false otherwise
     */
    public static boolean hasPendingRegistration(Officer officer, Project project) {
        return registrations.stream()
                .anyMatch(r -> r.getOfficer().equals(officer) &&
                               r.getProject().equals(project) &&
                               r.getStatus().equalsIgnoreCase("Pending"));
    }


    /**
     * Checks if an officer has any registration for a given project.
     *
     * @param officer the officer to check
     * @param project the project to check
     * @return true if any registration exists, false otherwise
     */
    public static boolean hasRegistration(Officer officer, Project project) {
        return registrations.stream()
                .anyMatch(r -> r.getOfficer().equals(officer) &&
                               r.getProject().equals(project));
    }

    /**
     * Returns a successful (approved) registration for the given officer.
     *
     * @param officer the officer to check
     * @return the approved registration if found, null otherwise
     */
    public static Registration hasSuccesfulRegistration(Officer officer) {
        for (Registration a : getAllRegistrations()) {
            if (a.getOfficer().equals(officer) && a.getStatus().equalsIgnoreCase("Approved")) {
                return a;
            }
        }
        return null;
    }

    /**
     * Gets all approved registrations for a specific officer.
     *
     * @param officer the officer to filter by
     * @return list of approved registrations
     */
    public static ArrayList<Registration> getApprovedRegistrationsByOfficer(Officer officer) {
        return registrations.stream()
                .filter(r -> r.getOfficer().equals(officer))
                .filter(r -> r.getStatus().equalsIgnoreCase("Approved"))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Removes a given registration from the repository.
     *
     * @param reg the registration to remove
     */
    public static void removeRegistration(Registration reg) {
        registrations.remove(reg);
    }

    /**
     * Gets all registrations submitted by a specific officer.
     *
     * @param officer the officer to filter by
     * @return list of registrations by the officer
     */
    public static List<Registration> getRegistrationsByOfficer(Officer officer) {
        return registrations.stream()
                .filter(r -> r.getOfficer().equals(officer))
                .collect(Collectors.toList());
    }

    /**
     * Gets all registrations that match a specific status.
     *
     * @param status the registration status (e.g., "Pending", "Approved")
     * @return list of registrations with the given status
     */
    public static List<Registration> getRegistrationsByStatus(String status) {
        return registrations.stream()
                .filter(r -> r.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    /**
     * Clears all registrations from the repository.
     */
    public static void clearAll() {
        registrations.clear();
    }
}
