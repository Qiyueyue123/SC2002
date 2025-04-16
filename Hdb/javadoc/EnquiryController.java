import java.util.List;

/**
 * Controller. <p>
 * This {@code EnquiryController} provides static utility methods for displaying and retrieving
 * enquiries from the {@link EnquiryRepository}.
 */
public class EnquiryController {

    /**
     * Displays all enquiries in the system.
     * If there are no enquiries, prints a message indicating so.
     */
    public static void showAllEnquiries() {
        List<Enquiry> all = EnquiryRepository.getAllEnquiries();
        if (all.isEmpty()) {
            System.out.println("There are no enquiries.");
            return;
        }
        System.out.println("List of Enquiries:");
        all.forEach(Enquiry::print);
    }

    /**
     * Displays all enquiries submitted by a specific applicant.
     * If there are no enquiries, prints a message indicating so.
     *
     * @param user the {@link Applicant} whose enquiries to display
     */
    public static void showUserEnquiries(Applicant user) {
        List<Enquiry> userEnquiries = EnquiryRepository.getUserEnquiries(user);
        if (userEnquiries.isEmpty()) {
            System.out.println("There are no enquiries.");
            return;
        }
        System.out.println("List of Your Enquiries:");
        userEnquiries.forEach(Enquiry::print);
    }

    /**
     * Displays all enquiries for a specific project.
     * If there are no enquiries for the project, prints a message indicating so.
     *
     * @param project the {@link Project} whose enquiries to display
     */
    public static void showProjectEnquiries(Project project) {
        List<Enquiry> projEnquiries = EnquiryRepository.getProjectEnquiries(project);
        if (projEnquiries.isEmpty()) {
            System.out.println("There are no enquiries for this project.");
            return;
        }
        System.out.println("Enquiries for project: " + project.getName());
        projEnquiries.forEach(Enquiry::print);
    }

    /**
     * Displays all unanswered enquiries for a specific project.
     * If there are no unanswered enquiries, prints a message indicating so.
     *
     * @param project the {@link Project} whose unanswered enquiries to display
     */
    public static void showUnansweredProjectEnquiries(Project project) {
        List<Enquiry> unanswered = EnquiryRepository.getUnansweredProjectEnquiries(project);
        if (unanswered.isEmpty()) {
            System.out.println("There are no unanswered enquiries for this project.");
            return;
        }
        System.out.println("Unanswered Enquiries for project: " + project.getName());
        unanswered.forEach(Enquiry::print);
    }

    /**
     * Returns an enquiry by its unique identifier.
     *
     * @param id the unique ID of the enquiry
     * @return the {@link Enquiry} with the specified ID, or {@code null} if not found
     */
    public static Enquiry getEnquiryById(int id) {
        return EnquiryRepository.getEnquiryById(id);
    }
}
