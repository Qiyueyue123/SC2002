import java.util.ArrayList;

public class RegistrationList {
    private static ArrayList<Registration> registrations;

    RegistrationList() {
        registrations = new ArrayList<Registration>();
    }
    public static void addRegistration(Registration r) {
		registrations.add(r);
	}
}
