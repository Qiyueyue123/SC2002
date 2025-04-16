import java.util.ArrayList;
import java.util.List;

public class ApplicantRepository {
    private static List<Applicant> applicants = new ArrayList<>();

    public static List<Applicant> getApplicants() {
        return applicants;
    }

    public static void setApplicants(List<Applicant> newApplicants) {
        applicants = newApplicants;
    }

    public static void setAllApplications(List<Applicant> newApplicants){
        for(Applicant a:newApplicants){
            a.setApplication(ApplicationController.getApplicationByNRIC(a.getNRIC()));
        }
    }

    public static Applicant findApplicantByNRIC(String nric) {
        for (Applicant applicant : applicants) {
            if (applicant.getNRIC().equalsIgnoreCase(nric)) {
                return applicant;
            }
        }
        return null;
    }

}
