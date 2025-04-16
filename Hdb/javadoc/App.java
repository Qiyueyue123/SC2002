import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Entry point for the HDB BTO application system.
 * Handles loading and saving of all data, user authentication, and launching the appropriate user interface
 * based on the logged-in user's role (Applicant, Officer, or Manager).
 */
public class App {

    /**
     * Main method to start the application.
     * Loads all data from CSV files, authenticates the user, and launches the appropriate display.
     * On exit, saves all data back to the CSV files.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileService fs = new FileService();

        loadAllData(fs);

        // Just to confirm projects are loaded
        List<Project> proj = ProjectRepository.getAllProjects();
        for (Project p : proj) {
            System.out.println("Project: " + p.getName());
        }

        String role = Utils.checkRole(scanner);
        scanner.nextLine();
        User user = null;
        int attempts = 3;
        while (attempts > 0 && user == null) {
            user = Utils.loginAuthenticator(scanner, role);
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

        // Launch the appropriate display based on user type
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

    /**
     * Loads all data for the system from CSV files using the provided {@link FileService}.
     * Initializes repositories for applicants, officers, managers, projects, applications, enquiries, and registrations.
     * Also sets up project-officer and project-applicant relationships.
     *
     * @param fs the {@link FileService} used to load data
     */
    private static void loadAllData(FileService fs) {
        ApplicantRepository.setApplicants(fs.loadApplicants("Hdb/database/ApplicantList.csv"));
        OfficerRepository.setOfficers(fs.loadOfficers("Hdb/database/OfficerList.csv"));
        ManagerRepository.setManagers(fs.loadManagers("Hdb/database/ManagerList.csv"));
        ProjectRepository.setProjects((ArrayList<Project>) fs.loadProjects("Hdb/database/ProjectList.csv"));
        ApplicationRepository.setApplications((ArrayList<Application>) fs.loadApplications("Hdb/database/ApplicationList.csv"));
        EnquiryRepository.setEnquiries((ArrayList<Enquiry>) fs.loadEnquiries("Hdb/database/EnquiryList.csv"));
        RegistrationRepository.setRegistrations((ArrayList<Registration>) fs.loadRegistrations("Hdb/database/RegistrationList.csv"));
        for (Project p : ProjectRepository.getAllProjects()) {
            p.setOfficersInCharge();
            p.setAppliedPeople();
        }
        for (Officer o : OfficerRepository.getOfficers()) {
            Registration r = RegistrationRepository.hasSuccesfulRegistration(o);
            if (r != null) {
                o.setAssignedProject(r.getProject());
            }
        }
        ApplicantRepository.setAllApplications(ApplicantRepository.getApplicants());
    }

    /**
     * Saves all system data to CSV files using the provided {@link FileService}.
     * Persists applicants, officers, managers, projects, applications, enquiries, and registrations.
     *
     * @param fs the {@link FileService} used to save data
     */
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