import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Prompt for role and force 1, 2, or 3
        while (true) {
            System.out.println("Select Role (Enter 1, 2 or 3)");
            System.out.println("1. Applicant");
            System.out.println("2. HDB Officer");
            System.out.println("3. HDB Manager");
            try {
                int response = sc.nextInt();
                if (response == 1 || response == 2 || response == 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                System.out.println();
                sc.nextLine(); //what is this clear buffer line why tf do i need it????
            }
        }

        //Prompt for login details
        sc.nextLine(); //why do i need this line???
        System.out.println("Please input NRIC: ");
        String nric = sc.nextLine();
        System.out.println("Please input password (default password is 'password'): ");
        String password  = sc.nextLine();
        //System.out.println(nric);
        //System.out.println(password);
        //Add logic to check if NRIC and Password matches with that in the respective file based on response (1/2/3)
    }
}