import java.util.Scanner;
import java.io.*;

public class Utils {
    public static String checkRole(Scanner sc) {
        //Scanner sc = new Scanner(System.in);
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
                    System.out.println("Invalid choice. Please enter 1, 2 or 3.\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. Please enter 1, 2 or 3.\n");
                sc.nextLine(); 
            }
        }
    }

    public static User loginAuthenticator(Scanner sc,String role) {
        System.out.println("Please input NRIC: ");
        String nric = sc.nextLine().trim();
        System.out.println("Please input password (default password is 'password'): ");
        String password = sc.nextLine().trim();
        
        if ("Applicant".equalsIgnoreCase(role)) {
            Applicant applicant = ApplicantRepository.findApplicantByNRIC(nric);
            if (applicant != null && applicant.getPassword().equals(password)) {
                return applicant;
            }
        } else if ("Officer".equalsIgnoreCase(role)) {
            Officer officer = OfficerRepository.findOfficerByNRIC(nric);
            if (officer != null && officer.getPassword().equals(password)) {
                return officer;
            }
        } else if ("Manager".equalsIgnoreCase(role)) {
            Manager manager = ManagerRepository.findManagerByNRIC(nric);
            if (manager != null && manager.getPassword().equals(password)) {
                return manager;
            }
        }
        System.out.println("Invalid NRIC or password. Please try again.");
        return null;
    }

    public boolean changePassword(User user, String oldPassword, String newPassword) {
        // Verify the current password.
        if (!user.getPassword().equals(oldPassword)) {
            System.out.println("Old password is incorrect.");
            return false;
        }
        user.setPassword(newPassword);

        String fileName = "";
        if (user instanceof Applicant)
            fileName = "../database/ApplicantList.csv";
        else if (user instanceof Officer)
            fileName = "../database/OfficerList.csv";
        else if (user instanceof Manager)
            fileName = "../database/ManagerList.csv";
        else {
            System.out.println("Invalid user type.");
            return false;
        }

        try {
            File inputFile = new File(fileName);
            // Create a temporary file to write updated contents.
            File tempFile = new File("temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean updated = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Assuming parts[1] holds NRIC and parts[4] holds the password.
                if (parts.length >= 5 && parts[1].trim().equalsIgnoreCase(user.getNRIC())) {
                    parts[4] = newPassword; // Update the password.
                    String newLine = String.join(",", parts);
                    writer.write(newLine);
                    updated = true;
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

            reader.close();
            writer.close();

            if (!inputFile.delete()) {
                System.out.println("Could not delete the original file.");
                return false;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename temporary file.");
                return false;
            }

            if (updated) {
                System.out.println("Password successfully changed.");
                return true;
            } else {
                System.out.println("User record not found. Password not changed.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
            return false;
        }
    }
}