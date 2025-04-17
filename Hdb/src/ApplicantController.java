import java.util.ArrayList;

public class ApplicantController extends UserController {
    private final Applicant applicant;

    public ApplicantController(Applicant applicant) {
        this.applicant = applicant;
    }

    public void viewEnquiries() {
        EnquiryController.showUserEnquiries(applicant);
    }

    public void createEnquiry(Project project, String msg) {
        Enquiry e = new Enquiry(applicant, project);
        e.setMessage(msg);
        EnquiryRepository.addEnquiry(e);
    }

    public void editEnquiry(Enquiry enquiry, String msg) {
        enquiry.setMessage(msg);
    }

    public void deleteEnquiry(Enquiry enquiry) {
        EnquiryRepository.getAllEnquiries().remove(enquiry);
    }

    public ArrayList<Project> viewProjects() {
        if ((!applicant.isMarried() && applicant.getAge() < 35) || applicant.getAge() < 21) {
            System.out.println("No projects available");
            return null;
        }

        ArrayList<Project> all = ProjectRepository.getAllProjects();
        ArrayList<Project> visible = new ArrayList<>();
        String officerList = "";
        int i = 0;
        for (Project p : all) {
            officerList = p.getOfficerName();
            if (p.getVisibility() && (p.getNum2Rooms() > 0 || p.getNum3Rooms() > 0) && !(officerList.contains(applicant.getName()))) {
                if (applicant.isMarried() || (!applicant.isMarried() && p.getNum2Rooms() > 0)) {
                    visible.add(p);
                    i++;
                    System.out.println("Project " + i + ":");
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
        //if officer, check if registered for the project
        if (applicant instanceof Officer) {
            Officer o = (Officer) applicant;
            if (RegistrationRepository.hasRegistration(o, project)){
                System.out.println("You cannot apply for a project you are registered for.");
                return;
            }
        }

        Application app = new Application(applicant, project);
        app.setFlatType(flatTypeChoice);
        applicant.setApplication(app);
        ApplicationRepository.addApplication(app);
        project.addPerson(applicant);

        System.out.println("Successfully applied for " + project.getName());
        app.print();
    }

    public void viewAppliedProject() {
        if(ApplicationController.getApplicationByNRIC(applicant.getNRIC())==null){
            System.out.println("No applied projects found");
            return;
        };
        ApplicationController.getApplicationByNRIC(applicant.getNRIC()).print();;
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
