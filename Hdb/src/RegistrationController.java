import java.util.List;

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

    public static boolean isOfficerAlreadyPending(Project project, Officer officer) {
        return RegistrationRepository.hasPendingRegistration(officer, project);
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
}