import java.util.Scanner;

public class LoginDisplay {

    public static void showDisplay(Scanner scanner) {
        String role = LoginController.checkRole(scanner);
        scanner.nextLine();
        User user = null;
        int attempts = 3;
        while (attempts > 0 && user == null) {
            user = LoginController.loginAuthenticator(scanner,role);
            if (user == null) {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Attempts remaining: " + attempts);
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
