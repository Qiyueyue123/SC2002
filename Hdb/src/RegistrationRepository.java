import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationRepository {
    private static List<Registration> registrations = new ArrayList<>();

    public static void addRegistration(Registration reg) {
        registrations.add(reg);
    }

    public static List<Registration> getAllRegistrations() {
        return registrations;
    }

    public static void setRegistrations(ArrayList<Registration> regis) {
        registrations = regis;
    }

    public static ArrayList<Registration> getPendingRegistrations(Project project) {
        return registrations.stream()
                .filter(r -> r.getProject().equals(project))
                .filter(r -> r.getStatus().equalsIgnoreCase("Pending"))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static boolean hasPendingRegistration(Officer officer, Project project) {
        return registrations.stream()
                .anyMatch(r -> r.getOfficer().equals(officer) &&
                              r.getProject().equals(project) &&
                              r.getStatus().equalsIgnoreCase("Pending"));
    }

    public static boolean hasRegistration(Officer officer, Project project) {
        return registrations.stream()
                .anyMatch(r -> r.getOfficer().equals(officer) &&
                              r.getProject().equals(project));
    }

    public static Registration hasSuccesfulRegistration(Officer officer) {
        for(Registration a: getAllRegistrations()){
            if(a.getOfficer().equals(officer) && a.getStatus().equalsIgnoreCase("Approved")){
                return a;
            }
        }
        return null;
    }

    public static ArrayList<Registration> getApprovedRegistrationsByOfficer(Officer officer) {
        return registrations.stream()
                .filter(r -> r.getOfficer().equals(officer))
                .filter(r -> r.getStatus().equalsIgnoreCase("Approved"))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void removeRegistration(Registration reg) {
        registrations.remove(reg);
    }

    public static List<Registration> getRegistrationsByOfficer(Officer officer) {
        return registrations.stream()
                .filter(r -> r.getOfficer().equals(officer))
                .collect(Collectors.toList());
    }
    

    public static List<Registration> getRegistrationsByStatus(String status) {
        return registrations.stream()
                .filter(r -> r.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public static void clearAll() {
        registrations.clear();
    }
}