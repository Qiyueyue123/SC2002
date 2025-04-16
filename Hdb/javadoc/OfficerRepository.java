import java.util.ArrayList;
import java.util.List;

/**
 * Entity.
 * Repository for managing a static list of {@link Officer} objects.
 * Provides methods to retrieve, update, and search for officers by NRIC.
 */
public class OfficerRepository {
    /**
     * The static list that stores all officers.
     */
    private static List<Officer> officers = new ArrayList<>();

    /**
     * Retrieves the list of all officers.
     *
     * @return the list of {@link Officer} objects.
     */
    public static List<Officer> getOfficers() {
        return officers;
    }

    /**
     * Replaces the current list of officers with a new list.
     *
     * @param newOfficers the new list of {@link Officer} objects to set.
     */
    public static void setOfficers(List<Officer> newOfficers) {
        officers = newOfficers;
    }

    /**
     * Searches for an officer in the list by their NRIC.
     *
     * @param nric the NRIC of the officer to find.
     * @return the {@link Officer} with the matching NRIC, or {@code null} if not found.
     */
    public static Officer findOfficerByNRIC(String nric) {
        for (Officer officer : officers) {
            if (officer.getNRIC().equalsIgnoreCase(nric)) {
                return officer;
            }
        }
        return null;
    }
}
