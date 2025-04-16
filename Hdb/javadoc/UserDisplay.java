import java.util.Scanner;

/**
 * Boundary. <p>
 * This {@code UserDisplay}is a interface representing a user display for different user roles in the system.
 * Provides methods for showing user-specific menus and changing passwords.
 */
public interface UserDisplay {

    /**
     * Displays the interface or menu relevant to the user role.
     * Implementing classes must define their own version of this method.
     */
    public void showDisplay();

    /**
     * Allows the user to change their password.
     * Prompts the user for the current password and a new password.
     * Validates the current password and updates it if correct.
     *
     * @param scanner the Scanner object for reading user input
     * @param user the user whose password is to be changed
     */
    default void changeUserPassword(Scanner scanner, User user) {
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine().trim();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine().trim();
        if (Utils.changePassword(user, currentPassword, newPassword)) {
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Password change failed. Please check your current password and try again.");
        }
    }
}
