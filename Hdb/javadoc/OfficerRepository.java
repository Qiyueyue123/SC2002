import java.util.ArrayList;
import java.util.List;

public class OfficerRepository {
    private static List<Officer> officers = new ArrayList<>();

    public static List<Officer> getOfficers() {
        return officers;
    }

    public static void setOfficers(List<Officer> newOfficers) {
        officers = newOfficers;
    }

    public static Officer findOfficerByNRIC(String nric) {
        for (Officer officer : officers) {
            if (officer.getNRIC().equalsIgnoreCase(nric)) {
                return officer;
            }
        }
        return null;
    }
}
