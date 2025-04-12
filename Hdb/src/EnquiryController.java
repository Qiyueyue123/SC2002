import java.util.List;

public class EnquiryController {

    public static void showAllEnquiries() {
        List<Enquiry> all = EnquiryRepository.getAllEnquiries();
        if (all.isEmpty()) {
            System.out.println("There are no enquiries.");
            return;
        }
        System.out.println("List of Enquiries:");
        all.forEach(Enquiry::print);
    }

    public static void showUserEnquiries(Applicant user) {
        List<Enquiry> userEnquiries = EnquiryRepository.getUserEnquiries(user);
        if (userEnquiries.isEmpty()) {
            System.out.println("There are no enquiries.");
            return;
        }
        System.out.println("List of Your Enquiries:");
        userEnquiries.forEach(Enquiry::print);
    }

    public static void showProjectEnquiries(Project project) {
        List<Enquiry> projEnquiries = EnquiryRepository.getProjectEnquiries(project);
        if (projEnquiries.isEmpty()) {
            System.out.println("There are no enquiries for this project.");
            return;
        }
        System.out.println("Enquiries for project: " + project.getName());
        projEnquiries.forEach(Enquiry::print);
    }

    public static void showUnansweredProjectEnquiries(Project project) {
        List<Enquiry> unanswered = EnquiryRepository.getUnansweredProjectEnquiries(project);
        if (unanswered.isEmpty()) {
            System.out.println("There are no unanswered enquiries for this project.");
            return;
        }
        System.out.println("Unanswered Enquiries for project: " + project.getName());
        unanswered.forEach(Enquiry::print);
    }

    public static Enquiry getEnquiryById(int id) {
        return EnquiryRepository.getEnquiryById(id);
    }
}