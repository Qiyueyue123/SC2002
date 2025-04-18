import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileService fs = new FileService();

        loadAllData(fs);

        LoginDisplay.showDisplay(scanner);

        saveAllData(fs);
        System.out.println("Data saved. Exiting program.");
    }

    private static void loadAllData(FileService fs) {
        ApplicantRepository.setApplicants(fs.loadApplicants("Hdb/database/ApplicantList.csv"));
        OfficerRepository.setOfficers(fs.loadOfficers("Hdb/database/OfficerList.csv"));
        ManagerRepository.setManagers(fs.loadManagers("Hdb/database/ManagerList.csv"));
        ProjectRepository.setProjects((ArrayList<Project>) fs.loadProjects("Hdb/database/ProjectList.csv"));
        ApplicationRepository.setApplications((ArrayList<Application>) fs.loadApplications("Hdb/database/ApplicationList.csv"));
        EnquiryRepository.setEnquiries((ArrayList<Enquiry>) fs.loadEnquiries("Hdb/database/EnquiryList.csv"));
        RegistrationRepository.setRegistrations((ArrayList<Registration>) fs.loadRegistrations("Hdb/database/RegistrationList.csv"));
        for(Project p : ProjectRepository.getAllProjects()){
            p.setOfficersInCharge();
            p.setAppliedPeople();
        }
        for(Officer o : OfficerRepository.getOfficers()){
            Registration r = RegistrationRepository.hasSuccesfulRegistration(o);
            if(r != null){
                o.setAssignedProject(r.getProject());
            }
        }
        ApplicantRepository.setAllApplications(ApplicantRepository.getApplicants());
    }

    private static void saveAllData(FileService fs) {
        fs.writeApplicants("Hdb/database/ApplicantList.csv", ApplicantRepository.getApplicants());
        fs.writeOfficers("Hdb/database/OfficerList.csv", OfficerRepository.getOfficers());
        fs.writeManagers("Hdb/database/ManagerList.csv", ManagerRepository.getManagers());
        fs.writeProjects("Hdb/database/ProjectList.csv", ProjectRepository.getAllProjects());
        fs.writeApplications("Hdb/database/ApplicationList.csv", ApplicationRepository.getAllApplications());
        fs.writeEnquiries("Hdb/database/EnquiryList.csv", EnquiryRepository.getAllEnquiries());
        fs.writeRegistrations("Hdb/database/RegistrationList.csv", RegistrationRepository.getAllRegistrations());
    }
} 