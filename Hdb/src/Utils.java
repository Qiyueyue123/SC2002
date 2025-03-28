import java.util.Scanner;

public class Utils {
    public static String checkRole() {
        Scanner sc = new Scanner(System.in);
        int response = -1;
        //Prompt for role and force 1, 2, or 3
        while (true) {
            System.out.println("Select Role (Enter 1, 2 or 3)");
            System.out.println("1. Applicant");
            System.out.println("2. HDB Officer");
            System.out.println("3. HDB Manager");
            try {
                response = sc.nextInt();
                if (response == 1) {
                    return "Applicant";
                } else if (response ==2) {
                    return "Officer";
                } else if (response ==3) {
                    return "Manager";
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                System.out.println();
                sc.nextLine(); 
            }
        }
    }

    public static User loginAuthenticator(String role) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input NRIC: ");
        String nric = sc.nextLine();
        System.out.println("Please input password (default password is 'password'): ");
        String password  = sc.nextLine();
        if (role.equals("Applicant")){
            //check applicant csv
            return new Applicant();
        } else if (role.equals("Officer")) {
            //check officer csv
            return new Officer();
        } else {
            //check manager csv
            return new Manager();
        }
    }
}
