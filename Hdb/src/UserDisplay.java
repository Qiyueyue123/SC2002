import java.util.Scanner;

public interface UserDisplay {
    public void showDisplay();

    default void changeUserPassword(Scanner scanner, User user) {
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine().trim();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine().trim();
        if (UserController.changePassword(user, currentPassword, newPassword)) {
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Password change failed. Please check your current password and try again.");
        }
    }
}
