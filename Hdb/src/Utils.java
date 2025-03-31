import java.util.Scanner;
import java.io.*;

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
        String line;
        String fileName = "../database/ApplicantList.csv";
        boolean married;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input NRIC: ");
        String nric = sc.nextLine();
        System.out.println("Please input password (default password is 'password'): ");
        String password  = sc.nextLine();
        switch (role) {
            case "Applicant":
                fileName = "Hdb/database/ApplicantList.csv";
                break;
            case "Officer":
                fileName = "Hdb/database/OfficerList.csv";
                break;
            case "Manager":
                fileName = "Hdb/database/ManagerList.csv";
                break;
            default:
                System.out.println("Invalid role.");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
                String name = values[0].trim();
                String csvNric = values[1].trim();
                int age = Integer.parseInt(values[2].trim());
                String martialStatus = values[3].trim();
                if(martialStatus.equals("Married")){
                    married = true;
                }
                else{
                    married = false;
                }
                String csvPassword = values[4].trim();
                if (csvNric.equalsIgnoreCase(nric)&&csvPassword.equals(password)){
                    if (role.equals("Applicant")) {
                        return new Applicant(csvNric, name,csvPassword, age, married);
                    } else if (role.equals("Officer")) {
                        return new Officer(csvNric, name,csvPassword, age, married);
                    } else if (role.equals("Manager")) {
                        return new Manager(csvNric, name,csvPassword, age, married);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        System.out.println("Invalid NRIC or password. Please try again.");
        return null;
    }
}
