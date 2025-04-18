/**
 * Controller. <p>
 * This {@code UserController} class is an <b>abstract</b> base controller class for user-related operations.
 * Provides utility methods that can be used by concrete user controllers.
 */
public abstract class UserController {

    /**
     * Changes the password for the specified user if the old password matches.
     *
     * @param user        the {@link User} whose password is to be changed
     * @param oldPassword the user's current password (for verification)
     * @param newPassword the new password to set
     * @return {@code true} if the password was changed successfully, {@code false} otherwise
     */
    public static boolean changePassword(User user, String oldPassword, String newPassword) {
        // Verify that the oldPassword matches the user's current password.
        if (!user.getPassword().equals(oldPassword)) {
            return false;
        }
        user.setPassword(newPassword);
        return true;
    }

   //can add more universal controller stuff here... view project? view enquiry? view application? idk
}
