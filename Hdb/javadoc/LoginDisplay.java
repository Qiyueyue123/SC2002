import java.util.Scanner;

/**
 * Boundary <p>
 * This {@code LoginDisplay} class handles the login display and authentication process for users of the BTO system.
 * Prompts the user to select their role, authenticates their credentials, and launches
 * the appropriate user interface based on the authenticated user type.
 */
public class LoginDisplay {

    /**
     * Displays the login prompt, handles authentication, and launches the appropriate user interface.
     * Allows up to three login attempts before exiting the application.
     *
     * @param scanner the {@link Scanner} used to read user input
     */
    public static void showDisplay(Scanner scanner) {
        String role = LoginController.checkRole(scanner);
        scanner.nextLine();
        User user = null;
        int attempts = 3;
        while (attempts > 0 && user == null) {
            user = LoginController.loginAuthenticator(scanner, role);
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
        System.out.println("Login Successful for " + user.getName());
        user.display(); //allows for new users to be created without ammending this class
    }
}
