import java.util.Scanner;

/**
 * {@code Utils} is a utility class providing shared functionalities like role checking,
 * login authentication, and password changing for the BTO System.
 */
public class Utils {

    /**
     * Prompts the user to select a role (Applicant, Officer, or Manager) by entering a number.
     * Keeps prompting until a valid input is provided.
     *
     * @param sc The Scanner object used for user input.
     * @return A string representing the selected role.
     */
    public static String checkRole(Scanner sc) {
        int response = -1;
        System.out.println("Welcome to the BTO System");
        while (true) {
            System.out.println("Please enter your role: applicant / officer / manager (Enter 1, 2 or 3)");
            System.out.println("1. Applicant");
            System.out.println("2. HDB Officer");
            System.out.println("3. HDB Manager");
            try {
                response = sc.nextInt();
                sc.nextLine(); // Consume leftover newline
                if (response == 1) {
                    return "Applicant";
                } else if (response == 2) {
                    return "Officer";
                } else if (response == 3) {
                    return "Manager";
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2 or 3.\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. Please enter 1, 2 or 3.\n");
                sc.nextLine(); // Consume invalid input
            }
        }
    }

    /**
     * Authenticates the login credentials of a user based on their role.
     *
     * @param sc   The Scanner object used for user input.
     * @param role The role of the user ("Applicant", "Officer", or "Manager").
     * @return A User object if authentication is successful, null otherwise.
     */
    public static User loginAuthenticator(Scanner sc, String role) {
        System.out.println("Please input NRIC: ");
        String nric = sc.nextLine().trim();
        System.out.println("Please input password (default password is 'password'): ");
        String password = sc.nextLine().trim();

        if ("Applicant".equalsIgnoreCase(role)) {
            Applicant applicant = ApplicantRepository.findApplicantByNRIC(nric);
            if (applicant != null && applicant.getPassword().equals(password)) {
                return applicant;
            }
        } else if ("Officer".equalsIgnoreCase(role)) {
            Officer officer = OfficerRepository.findOfficerByNRIC(nric);
            if (officer != null && officer.getPassword().equals(password)) {
                return officer;
            }
        } else if ("Manager".equalsIgnoreCase(role)) {
            Manager manager = ManagerRepository.findManagerByNRIC(nric);
            if (manager != null && manager.getPassword().equals(password)) {
                return manager;
            }
        }
        System.out.println("Invalid NRIC or password. Please try again.");
        return null;
    }

    /**
     * Changes the user's password after verifying the old password.
     *
     * @param user        The user whose password is to be changed.
     * @param oldPassword The user's current password.
     * @param newPassword The new password to be set.
     * @return true if the password is changed successfully, false otherwise.
     */
    public static boolean changePassword(User user, String oldPassword, String newPassword) {
        if (!user.getPassword().equals(oldPassword)) {
            System.out.println("Old password is incorrect.");
            return false;
        }
        user.setPassword(newPassword);
        System.out.println("Password successfully changed.");
        return true;
    }
}
