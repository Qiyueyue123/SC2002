import java.util.Scanner;

/**
 * Utility class for handling user role selection, authentication,
 * and password management in the BTO System.
 */
public class Utils {

    /**
     * Prompts the user to select their role in the BTO system.
     * Continues prompting until a valid option is entered (1, 2, or 3).
     *
     * @param sc A {@link Scanner} object used for reading user input.
     * @return A {@code String} representing the selected role: "Applicant", "Officer", or "Manager".
     */
    public static String checkRole(Scanner sc) {
        int response = -1;
        // Prompt for role and force 1, 2, or 3
        System.out.println("Welcome to the BTO System");
        while (true) {
            System.out.println("Please enter your role: applicant / officer / manager (Enter 1, 2 or 3)");
            System.out.println("1. Applicant");
            System.out.println("2. HDB Officer");
            System.out.println("3. HDB Manager");
            try {
                response = sc.nextInt();
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
                sc.nextLine();
            }
        }
    }

    /**
     * Authenticates the login for a user based on their role.
     * Prompts for NRIC and password, and returns a matching user object if credentials are correct.
     *
     * @param sc   A {@link Scanner} object used for reading user input.
     * @param role A {@code String} indicating the user's role: "Applicant", "Officer", or "Manager".
     * @return A {@link User} object if authentication is successful; {@code null} otherwise.
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
     * Changes the password of a user if the old password matches the current password.
     *
     * @param user        The {@link User} whose password is to be changed.
     * @param oldPassword The user's current password.
     * @param newPassword The new password to set.
     * @return {@code true} if the password was successfully changed; {@code false} otherwise.
     */
    public static boolean changePassword(User user, String oldPassword, String newPassword) {
        // verify that the oldPassword matches the user's current password.
        if (!user.getPassword().equals(oldPassword)) {
            System.out.println("Old password is incorrect.");
            return false;
        }
        user.setPassword(newPassword);
        System.out.println("Password successfully changed.");
        return true;
    }
}
