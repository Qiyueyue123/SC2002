import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileService fs = new FileService();

        loadAllData(fs);
        //just to confirm project is loaded
        List<Project> proj = ProjectRepository.getAllProjects();
        for(Project p : proj){
            System.out.println("Project: " + p.getName());
        }

        String role = Utils.checkRole(scanner);
        scanner.nextLine();
        User user = null;
        int attempts = 3;
        while (attempts > 0 && user == null) {
            user = Utils.loginAuthenticator(scanner,role);
            if (user == null) {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Invalid login. Attempts remaining: " + attempts);
                } else {
                    System.out.println("Too many failed attempts. Exiting application.");
                    return;
                }
            }
        }

        UserDisplay display;
        if (user instanceof Officer officer) {
            display = new OfficerDisplay(officer);
        } else if (user instanceof Applicant applicant) {
            display = new ApplicantDisplay(applicant);
        } else if (user instanceof Manager manager) {
            display = new ManagerDisplay(manager);
        } else {
            System.out.println("Unrecognized user type. Exiting.");
            return;
        }
        display.showDisplay();

        saveAllData(fs);
        System.out.println("Data saved. Exiting program.");
        /*User user = null;
        String role = "";
        while (user == null) {
            //Prompt for role
            role = Utils.checkRole();
            //Prompt for login details
            user = Utils.loginAuthenticator(role);
        }
        System.out.println("Login Successful for " + user.getName());*/
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