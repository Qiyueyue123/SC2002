import java.util.List;

/**
 * Controller. <p>
 * This {@code RegistrationController} class manages registration approvals and rejections
 * for officers applying to projects.
 * <p>
 * This class interacts with the {@link RegistrationRepository} to retrieve and modify
 * registration data and performs business logic such as displaying pending registrations,
 * approving or rejecting them.
 */
public class RegistrationController {

    /**
     * Displays all pending registrations for a given project.
     *
     * @param project The project to show pending officer registrations for.
     */
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

    /**
     * Checks if a given officer already has a pending registration for the specified project.
     *
     * @param project The project in question.
     * @param officer The officer to check.
     * @return {@code true} if a pending registration exists; {@code false} otherwise.
     */
    public static boolean isOfficerAlreadyPending(Project project, Officer officer) {
        return RegistrationRepository.hasPendingRegistration(officer, project);
    }

    /**
     * Approves the given registration and updates the available officer slots in the project.
     *
     * @param reg The registration to approve.
     */
    public static void approveRegistration(Registration reg) {
        reg.setStatus("Approved");
        reg.getProject().minusAvailableOfficerSlots();
        System.out.println("Registration approved for Officer: " + reg.getOfficer().getName());
    }

    /**
     * Rejects the given registration by setting its status to "Rejected".
     *
     * @param reg The registration to reject.
     */
    public static void rejectRegistration(Registration reg) {
        reg.setStatus("Rejected");
        System.out.println("Registration rejected for Officer: " + reg.getOfficer().getName());
    }
}
