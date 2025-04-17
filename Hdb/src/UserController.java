import java.util.ArrayList;

public abstract class UserController{
   
    public static boolean changePassword(User user, String oldPassword, String newPassword) {
        //verify that the oldPassword matches the user's current password.
        if (!user.getPassword().equals(oldPassword)) {
            System.out.println("Old password is incorrect.");
            return false;
        }
        user.setPassword(newPassword);
        System.out.println("Password successfully changed.");
        return true;
    }    
    //can add more universal controller stuff here... view project? view enquiry? view application? idk
}