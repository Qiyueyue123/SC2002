import java.util.ArrayList;

public abstract class UserController{
   
    public static boolean changePassword(User user, String oldPassword, String newPassword) {
        //verify that the oldPassword matches the user's current password.
        if (!user.getPassword().equals(oldPassword)) {
            return false;
        }
        user.setPassword(newPassword);
        return true;
    }    
    //can add more universal controller stuff here... view project? view enquiry? view application? idk
}