import java.util.ArrayList;
import java.util.List;

/**
 * Entity. <p>
 * This {@code ApplicantRepository} is for managing a static list of {@link Applicant} objects.
 * Provides methods to retrieve, update, and search for applicants by NRIC.
 * Also links applicants with their corresponding applications.
 */
public class ApplicantRepository {
    /**
     * The static list that stores all applicants.
     */
    private static List<Applicant> applicants = new ArrayList<>();

    /**
     * Returns the list of all applicants.
     *
     * @return the list of {@link Applicant} objects.
     */
    public static List<Applicant> getApplicants() {
        return applicants;
    }

    /**
     * Replaces the current list of applicants with a new list.
     *
     * @param newApplicants the new list of {@link Applicant} objects to set.
     */
    public static void setApplicants(List<Applicant> newApplicants) {
        applicants = newApplicants;
    }

    /**
     * For each applicant in the provided list, sets their application by
     * looking up the application using their NRIC via {@link ApplicationController}.
     *
     * @param newApplicants the list of applicants whose applications are to be set.
     */
    public static void setAllApplications(List<Applicant> newApplicants){
        for(Applicant a : newApplicants){
            a.setApplication(ApplicationController.getApplicationByNRIC(a.getNRIC()));
        }
    }

    /**
     * Searches for an applicant in the list by their NRIC (case-insensitive).
     *
     * @param nric the NRIC of the applicant to find.
     * @return the {@link Applicant} with the matching NRIC, or {@code null} if not found.
     */
    public static Applicant findApplicantByNRIC(String nric) {
        for (Applicant applicant : applicants) {
            if (applicant.getNRIC().equalsIgnoreCase(nric)) {
                return applicant;
            }
        }
        return null;
    }
}
