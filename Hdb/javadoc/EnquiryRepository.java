import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity.<p>
 * Repository for managing enquiries within the system.
 * Provides static methods to add, retrieve, and query enquiries.
 */
public class EnquiryRepository {
    /**
     * The static list that stores all enquiries.
     */
    private static List<Enquiry> enquiries = new ArrayList<>();

    /**
     * Adds a new enquiry to the repository.
     *
     * @param enquiry the {@link Enquiry} to add
     */
    public static void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    /**
     * Retrieves all enquiries stored in the repository.
     *
     * @return a list of all {@link Enquiry} objects
     */
    public static List<Enquiry> getAllEnquiries() {
        return enquiries;
    }

    /**
     * Replaces the current list of enquiries with the provided list.
     *
     * @param enq the new list of {@link Enquiry} objects to set
     */
    public static void setEnquiries(ArrayList<Enquiry> enq) {
        enquiries = enq;
    }

    /**
     * Retrieves all enquiries made by a specific applicant, sorted by the project name.
     *
     * @param user the {@link Applicant} whose enquiries are to be retrieved
     * @return a list of enquiries made by the specified applicant, sorted by project name
     */
    public static List<Enquiry> getUserEnquiries(Applicant user) {
        return enquiries.stream()
                .filter(e -> e.getUser().equals(user))
                .sorted(Comparator.comparing(e -> e.getProject().getName()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all enquiries related to a specific project, sorted by the project name.
     *
     * @param project the {@link Project} whose enquiries are to be retrieved
     * @return a list of enquiries for the specified project, sorted by project name
     */
    public static List<Enquiry> getProjectEnquiries(Project project) {
        return enquiries.stream()
                .filter(e -> e.getProject().equals(project))
                .sorted(Comparator.comparing(e -> e.getProject().getName()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all unanswered enquiries for a specific project.
     *
     * @param project the {@link Project} whose unanswered enquiries are to be retrieved
     * @return a list of unanswered enquiries for the specified project
     */
    public static List<Enquiry> getUnansweredProjectEnquiries(Project project) {
        return getProjectEnquiries(project).stream()
                .filter(e -> !e.isAnswered())
                .collect(Collectors.toList());
    }

    /**
     * Finds an enquiry by its unique ID.
     *
     * @param id the unique identifier of the enquiry
     * @return the {@link Enquiry} with the specified ID, or {@code null} if not found
     */
    public static Enquiry getEnquiryById(int id) {
        return enquiries.stream()
                .filter(e -> e.getID() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks if the enquiry repository is empty.
     *
     * @return {@code true} if there are no enquiries, {@code false} otherwise
     */
    public static boolean isEmpty() {
        return enquiries.isEmpty();
    }
}
