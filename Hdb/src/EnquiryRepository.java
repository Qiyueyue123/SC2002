import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EnquiryRepository {
    private static List<Enquiry> enquiries = new ArrayList<>();

    public static void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    public static List<Enquiry> getAllEnquiries() {
        return enquiries;
    }

    public static void setEnquiries(ArrayList<Enquiry> enq) {
        enquiries = enq;
    }

    public static List<Enquiry> getUserEnquiries(Applicant user) {
        return enquiries.stream()
                .filter(e -> e.getUser().equals(user))
                .sorted(Comparator.comparing(e -> e.getProject().getName()))
                .collect(Collectors.toList());
    }

    public static List<Enquiry> getProjectEnquiries(Project project) {
        return enquiries.stream()
                .filter(e -> e.getProject().equals(project))
                .sorted(Comparator.comparing(e -> e.getProject().getName()))
                .collect(Collectors.toList());
    }

    public static List<Enquiry> getUnansweredProjectEnquiries(Project project) {
        return getProjectEnquiries(project).stream()
                .filter(e -> !e.isAnswered())
                .collect(Collectors.toList());
    }

    public static Enquiry getEnquiryById(int id) {
        return enquiries.stream()
                .filter(e -> e.getID() == id)
                .findFirst()
                .orElse(null);
    }

    public static boolean isEmpty() {
        return enquiries.isEmpty();
    }
}