import java.util.ArrayList;

public class ApplicantController {
    private final Applicant applicant;

    public ApplicantController(Applicant applicant) {
        this.applicant = applicant;
    }

    public void viewEnquiries() {
        EnquiryList.showUserEnquiries(applicant);
    }

    public void createEnquiry(Project project, String msg) {
        Enquiry e = new Enquiry(applicant, project);
        e.setMessage(msg);
        EnquiryList.addEnquiry(e);
    }

    public void editEnquiry(Enquiry enquiry, String msg) {
        enquiry.setMessage(msg);
    }

    public void deleteEnquiry(Enquiry enquiry) {
        EnquiryList.getAllEnquiries().remove(enquiry);
    }

    public ArrayList<Project> viewProjects() {
        if ((!applicant.isMarried() && applicant.getAge() < 35) || applicant.getAge() < 21) {
            System.out.println("No projects available");
            return null;
        }

        ArrayList<Project> all = ProjectRepository.getAllProjects();
        ArrayList<Project> visible = new ArrayList<>();

        for (Project p : all) {
            if (p.getVisibility() && (p.getNum2Rooms() > 0 || p.getNum3Rooms() > 0)) {
                if (applicant.isMarried() || (!applicant.isMarried() && p.getNum2Rooms() > 0)) {
                    visible.add(p);
                    ProjectDisplay.printProj(p);
                }
            }
        }
        return visible;
    }

    public void applyProject(Project project, int flatTypeChoice) {
        if (applicant.getApplication() != null) {
            System.out.println("Cannot apply for a second project.");
            return;
        }

        Application app = new Application(applicant, project);
        app.setFlatType(flatTypeChoice);
        applicant.setApplication(app);
        ApplicationList.addApplication(app);
        project.addPerson(applicant);

        System.out.println("Successfully applied for " + project.getName());
        app.print();
    }

    public void viewAppliedProject() {
        if (applicant.getApplication() == null) {
            System.out.println("No applied projects found");
            return;
        }
        applicant.getApplication().print();
    }

    public void withdrawApplication() {
        Application app = applicant.getApplication();
        if (app != null && !app.getWithdrawalRequest()) {
            app.requestWithdrawal();
            System.out.println("Withdrawal requested.");
        } else {
            System.out.println("Already requested or no application found.");
        }
    }
}
