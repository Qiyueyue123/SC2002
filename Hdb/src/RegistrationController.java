import java.util.List;
import java.util.ArrayList;

public class RegistrationController {

    public static void showPendingRegistrations(Project project) {
        List<Registration> pending = RegistrationRepository.getPendingRegistrations(project);
        if (pending.isEmpty()) {
            System.out.println("No pending officer registrations for this project.");
        } else {
            System.out.println("Pending Registrations for " + project.getName() + ":");
            for (Registration r : pending) {
                System.out.println("Officer: " + r.getOfficer().getName());
            }
        }
    }

    public static ArrayList<Registration> getPendingRegistrations(Project proj){
        return RegistrationRepository.getPendingRegistrations(proj);
    }

    public static boolean isOfficerAlreadyPending(Project project, Officer officer) {
        return RegistrationRepository.hasPendingRegistration(officer, project);
    }

    public static ArrayList<Registration> getOfficerApprovedRegistration(Officer o){
        return RegistrationRepository.getApprovedRegistrationsByOfficer(o);
    }

    public static void approveRegistration(Registration reg) {
        reg.setStatus("Approved");
        reg.getProject().minusAvailableOfficerSlots();
        System.out.println("Registration approved for Officer: " + reg.getOfficer().getName());
    }

    public static void rejectRegistration(Registration reg) {
        reg.setStatus("Rejected");
        System.out.println("Registration rejected for Officer: " + reg.getOfficer().getName());
    }

    public static void addRegistration(Officer officer, Project proj){
        Registration reg = new Registration(officer, proj);
        RegistrationRepository.addRegistration(reg);
    }

    public static List<Registration> getAllRegistrations(){
        return RegistrationRepository.getAllRegistrations();
    }
}