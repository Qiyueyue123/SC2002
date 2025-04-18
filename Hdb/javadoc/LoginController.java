import java.util.Scanner;

/**
 * Controller. <p>
 * This {@code LoginController} class handles user role selection and authentication for the BTO system.
 * Provides methods for prompting users to select their role and for verifying login credentials.
 */
public class LoginController {

    /**
     * Prompts the user to select their role (Applicant, Officer, or Manager) using the provided scanner.
     * Only accepts valid input (1, 2, or 3) and returns the corresponding role as a string.
     *
     * @param sc the {@link Scanner} used to read user input
     * @return the selected role as a string: "Applicant", "Officer", or "Manager"
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
     * Authenticates a user by prompting for NRIC and password, then checks credentials
     * against the repository for the specified role.
     *
     * @param sc   the {@link Scanner} used to read user input
     * @param role the role of the user ("Applicant", "Officer", or "Manager")
     * @return the authenticated {@link User} object if credentials are valid, or {@code null} otherwise
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
}
