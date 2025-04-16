import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides utility methods for loading and saving application data (Applicants, Officers, Managers,
 * Projects, Applications, Enquiries, Registrations) to and from CSV files.
 * <p>
 * Each method reads or writes a specific entity type, using a defined CSV format for each.
 * </p>
 */

public class FileService {
    
    /**
     * Loads a list of applicants from a CSV file.
     * The CSV must have columns: Name, NRIC, Age, MaritalStatus, Password.
     *
     * @param csvFilePath the path to the applicant CSV file
     * @return a list of {@link Applicant} objects loaded from the file
     */

    public List<Applicant> loadApplicants(String csvFilePath) {
        List<Applicant> applicants = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 5) {
                    String name = fields[0].trim();
                    String nric = fields[1].trim();
                    int age = Integer.parseInt(fields[2].trim());
                    boolean married = fields[3].trim().equalsIgnoreCase("Married");
                    String password = fields[4].trim();
                    Applicant applicant = new Applicant(nric, name, password, age, married);
                    applicants.add(applicant);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading applicants: " + e.getMessage());
        }
        return applicants;
    }
    /**
     * Loads a list of officers from a CSV file.
     * The CSV must have columns: Name, NRIC, Age, MaritalStatus, Password.
     *
     * @param csvFilePath the path to the officer CSV file
     * @return a list of {@link Officer} objects loaded from the file
     */
    public List<Officer> loadOfficers(String csvFilePath) {
        List<Officer> officers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 5) {
                    String name = fields[0].trim();
                    String nric = fields[1].trim();
                    int age = Integer.parseInt(fields[2].trim());
                    boolean married = fields[3].trim().equalsIgnoreCase("Married");
                    String password = fields[4].trim();
                    Officer officer = new Officer(nric, name, password, age, married);
                    officers.add(officer);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading officers: " + e.getMessage());
        }
        return officers;
    }
    /**
     * Loads a list of managers from a CSV file.
     * The CSV must have columns: Name, NRIC, Age, MaritalStatus, Password.
     *
     * @param csvFilePath the path to the manager CSV file
     * @return a list of {@link Manager} objects loaded from the file
     */
    public List<Manager> loadManagers(String csvFilePath) {
        List<Manager> managers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 5) {
                    String name = fields[0].trim();
                    String nric = fields[1].trim();
                    int age = Integer.parseInt(fields[2].trim());
                    boolean married = fields[3].trim().equalsIgnoreCase("Married");
                    String password = fields[4].trim();
                    Manager manager = new Manager(nric, name, password, age, married);
                    managers.add(manager);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading managers: " + e.getMessage());
        }
        return managers;
    }
    /**
     * Loads a list of projects from a CSV file.
     * The CSV must have columns: Project Name, Neighborhood, Type 1, Num2Rooms, Price, Type 2, Num3Rooms, Price, OpeningDate, ClosingDate, Manager, AvailOfficerSlots, Visibility.
     * The method will skip projects whose manager cannot be found in the system.
     *
     * @param csvFilePath the path to the project CSV file
     * @return a list of {@link Project} objects loaded from the file
     */
    public List<Project> loadProjects(String csvFilePath) {
        List<Project> projects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 9) {
                    String name = fields[0].trim();
                    String neighborhood = fields[1].trim();
                    String roomType1 = fields[2].trim();
                    int num2Rooms = Integer.parseInt(fields[3].trim());
                    int price2room = Integer.parseInt(fields[4].trim());
                    String roomType2 = fields[5].trim();
                    int num3Rooms = Integer.parseInt(fields[6].trim());
                    int price3room = Integer.parseInt(fields[7].trim());
                    String openingDate = fields[8].trim();
                    String closingDate = fields[9].trim();
                    String managerName = fields[10].trim();
                    int availOfficerSlots = Integer.parseInt(fields[11].trim());
                    boolean visibility;
                    if(Integer.parseInt(fields[12].trim())== 1){
                        visibility = true;
                    }else{
                        visibility = false;
                    }

                    Manager manager = ManagerRepository.findManagerByName(managerName);
                    if (manager == null) {
                        System.out.println("Manager " + managerName + " not found; skipping project record.");
                        continue;
                    }
                    Project project = new Project(name, neighborhood, visibility, num2Rooms,
                                                  num3Rooms, openingDate, closingDate, availOfficerSlots, manager,price2room,price3room);
                    projects.add(project);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading projects: " + e.getMessage());
        }
        return projects;
    }
    /**
     * Loads a list of applications from a CSV file.
     * The CSV must have columns: ApplicantNRIC, ProjectName, AppliedStatus, FlatType, WithdrawalRequested, WithdrawalStatus.
     * Applications referencing unknown applicants or projects are skipped.
     *
     * @param csvFilePath the path to the application CSV file
     * @return a list of {@link Application} objects loaded from the file
     */
    public List<Application> loadApplications(String csvFilePath) {
        List<Application> applications = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 6) {
                    String applicantNRIC = fields[0].trim();
                    String projectName = fields[1].trim();
                    String appliedStatus = fields[2].trim();
                    int flatType = Integer.parseInt(fields[3].trim());
                    boolean withdrawalRequested = Boolean.parseBoolean(fields[4].trim());
                    String withdrawalStatus = fields[5].trim();
                    Applicant applicant = ApplicantRepository.findApplicantByNRIC(applicantNRIC);
                    if(applicant == null){
                        applicant = OfficerRepository.findOfficerByNRIC(applicantNRIC);
                    }
                    Project project = ProjectController.findProjectByName(projectName);
                    if (applicant == null) {
                        continue;
                    }
                    if (project == null) {
                        continue;
                    }
                    
                    Application app = new Application(applicant, project);
                    app.setAppliedStatus(appliedStatus);
                    app.setFlatType(flatType);
                    if (withdrawalRequested) {
                        app.requestWithdrawal();
                    }
                    app.setWithdrawalStatus(withdrawalStatus);
                    applications.add(app);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading applications: " + e.getMessage());
        }
        return applications;
    }
    /**
     * Loads a list of enquiries from a CSV file.
     * The CSV must have columns: id, ApplicantNRIC, ProjectName, Message, Response, Answered.
     *
     * @param csvFilePath the path to the enquiry CSV file
     * @return a list of {@link Enquiry} objects loaded from the file
     */
    public List<Enquiry> loadEnquiries(String csvFilePath) {
        List<Enquiry> enquiries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 6) {
                    //not sure if i need id since its static instance count anyways
                    int id = Integer.parseInt(fields[0].trim());
                    String applicantNRIC = fields[1].trim();
                    String projectName = fields[2].trim();
                    String message = fields[3].trim();
                    String response = fields[4].trim();
                    boolean answered = Boolean.parseBoolean(fields[5].trim());
                    
                    Applicant applicant = ApplicantRepository.findApplicantByNRIC(applicantNRIC);
                    Project project = ProjectController.findProjectByName(projectName);
                    
                    Enquiry enquiry = new Enquiry(applicant,project);
                    enquiry.setMessage(message);
                    enquiry.setResponse(response);
                    enquiry.setAnswered(answered);
                    enquiries.add(enquiry);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading enquiries: " + e.getMessage());
        }
        return enquiries;
    }

    /**
     * Loads a list of officer registrations from a CSV file.
     * The CSV must have columns: OfficerNRIC, ProjectName, Status.
     * Registrations referencing unknown officers or projects are skipped.
     *
     * @param csvFilePath the path to the registration CSV file
     * @return a list of {@link Registration} objects loaded from the file
     */
    public List<Registration> loadRegistrations(String csvFilePath) {
        List<Registration> registrations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line= br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 3) {
                    String officerNRIC = fields[0].trim();
                    String projectName = fields[1].trim();
                    String status = fields[2].trim();
                    
                    Officer officer = OfficerRepository.findOfficerByNRIC(officerNRIC);
                    Project project = ProjectController.findProjectByName(projectName);
                    
                    if (project == null) {
                        continue;
                    }
                    officer.setAssignedProject(project);
                    Registration reg = new Registration(officer, project);
                    reg.setStatus(status);
                    registrations.add(reg);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading registrations: " + e.getMessage());
        }
        return registrations;
    }
    /**
     * Writes a list of applicants to a CSV file.
     * The CSV will have columns: Name, NRIC, Age, MaritalStatus, Password.
     *
     * @param csvFilePath the path to the output file
     * @param applicants  the list of applicants to write
     */
    public void writeApplicants(String csvFilePath, List<Applicant> applicants) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            bw.write("Name,NRIC,Age,MaritalStatus,Password");
            bw.newLine();
            for (Applicant a : applicants) {
                String maritalStatus = a.isMarried() ? "Married" : "Single";
                bw.write(a.getName() + "," + a.getNRIC() + "," + a.getAge() + "," + maritalStatus + "," + a.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing applicants: " + e.getMessage());
        }
    }
    /**
     * Writes a list of officers to a CSV file.
     * The CSV will have columns: Name, NRIC, Age, MaritalStatus, Password.
     *
     * @param csvFilePath the path to the output file
     * @param officers    the list of officers to write
     */
    public void writeOfficers(String csvFilePath, List<Officer> officers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            bw.write("Name,NRIC,Age,MaritalStatus,Password");
            bw.newLine();
            for (Officer o : officers) {
                String maritalStatus = o.isMarried() ? "Married" : "Single";
                bw.write(o.getName() + "," + o.getNRIC() + "," + o.getAge() + "," + maritalStatus + "," + o.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing officers: " + e.getMessage());
        }
    }
    /**
     * Writes a list of managers to a CSV file.
     * The CSV will have columns: Name, NRIC, Age, MaritalStatus, Password.
     *
     * @param csvFilePath the path to the output file
     * @param managers    the list of managers to write
     */
    public void writeManagers(String csvFilePath, List<Manager> managers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            bw.write("Name,NRIC,Age,MaritalStatus,Password");
            bw.newLine();
            for (Manager m : managers) {
                String maritalStatus = m.isMarried() ? "Married" : "Single";
                bw.write(m.getName() + "," + m.getNRIC() + "," + m.getAge() + "," + maritalStatus + "," + m.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing managers: " + e.getMessage());
        }
    }
    /**
     * Writes a list of applications to a CSV file.
     * The CSV will have columns: ApplicantNRIC, ProjectName, AppliedStatus, FlatType, WithdrawalRequested, WithdrawalStatus.
     *
     * @param csvFilePath   the path to the output file
     * @param applications  the list of applications to write
     */
    public void writeApplications(String csvFilePath, List<Application> applications) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            bw.write("ApplicantNRIC,ProjectName,AppliedStatus,FlatType,WithdrawalRequested,WithdrawalStatus");
            bw.newLine();
            for (Application a : applications) {
                String line = a.getApplicant().getNRIC() + "," +a.getProject().getName() + "," +a.getAppliedStatus() + "," +a.getFlatType() + "," + 
                              a.getWithdrawalRequest() + "," +a.getWithdrawalStatus();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing applications: " + e.getMessage());
        }
    }
    /**
     * Writes a list of registrations to a CSV file.
     * The CSV will have columns: OfficerNRIC, ProjectName, Status.
     *
     * @param csvFilePath    the path to the output file
     * @param registrations  the list of registrations to write
     */
    public void writeRegistrations(String csvFilePath, List<Registration> registrations) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            bw.write("OfficerNRIC,ProjectName,Status");
            bw.newLine();
            for (Registration r : registrations) {
                String line = r.getOfficer().getNRIC() + "," + r.getProject().getName() + "," + r.getStatus();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing registrations: " + e.getMessage());
        }
    }
    /**
     * Writes a list of enquiries to a CSV file.
     * The CSV will have columns: id, ApplicantNRIC, ProjectName, Message, Response, Answered.
     *
     * @param csvFilePath  the path to the output file
     * @param enquiries    the list of enquiries to write
     */
    public void writeEnquiries(String csvFilePath, List<Enquiry> enquiries) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            bw.write("id,ApplicantNRIC,ProjectName,Message,Response,Answered");
            bw.newLine();
            for (Enquiry e : enquiries) {
                String line = e.getID() + "," + (e.getUser() != null ? e.getUser().getNRIC() : "") + "," + (e.getProject() != null ? e.getProject().getName() : "") + "," + 
                              e.getMessage() + "," + (e.getResponse() != null ? e.getResponse() : "") + "," + e.isAnswered();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing enquiries: " + e.getMessage());
        }
    }
    /**
     * Writes a list of projects to a CSV file.
     * The CSV will have columns: Project Name, Neighborhood, Type 1, Num2Rooms, Price, Type 2, Num3Rooms, Price, OpeningDate, ClosingDate, Manager, AvailOfficerSlots, Visibility, Officer.
     *
     * @param csvFilePath the path to the output file
     * @param projects    the list of projects to write
     */
    public void writeProjects(String csvFilePath, List<Project> projects) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            bw.write("Project Name,Neighborhood,Type 1,Num2Rooms,Price,Type 2,Num3Rooms,Price,OpeningDate,ClosingDate,Manager,AvailOfficerSlots,Visibility,Officer");
            bw.newLine();
            int visibility;
            for (Project p : projects) {
                if(p.getVisibility()==true){
                    visibility = 1;
                }
                else{
                    visibility = 0;
                }
                String line = p.getName() + "," + p.getNeighborhood() + ",2-Room," + p.getNum2Rooms() + "," + p.getPrice2Room() + ",3-Room," + p.getNum3Rooms() + "," + 
                              p.getPrice2Room() + "," + p.getOpeningDate() + "," + p.getClosingDate() + "," + p.getManager().getName() + "," + p.getAvailOfficerSlots() 
                              + "," + visibility + "," + p.getOfficerName();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing projects: " + e.getMessage());
        }
    }
}
