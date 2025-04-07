import java.util.ArrayList;
import java.util.Comparator;

public class RegistrationList {
    private static ArrayList<Registration> registrations;

    RegistrationList() {
        registrations = new ArrayList<Registration>();
    }

    //sort by projects in alphabetical order
	public static void sortByProj() {
		registrations.sort(new Comparator<Registration>() {
			@Override
			public int compare(Registration r1, Registration r2) {
				Project p1 = r1.getProject();
				Project p2 = r2.getProject();
				return p1.getName().compareTo(p2.getName());
			}
		});
	}

        //sort by projects in alphabetical order
	public static void sortByOfficer() {
		registrations.sort(new Comparator<Registration>() {
			@Override
			public int compare(Registration r1, Registration r2) {
				Officer o1 = r1.getOfficer();
				Officer o2 = r2.getOfficer();
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

    public static void addRegistration(Registration r) {
		registrations.add(r);
	}
    public static void print() {
        for (Registration r : registrations) {
            r.print();
        }
    }
    public static boolean isEmpty() {
        return registrations.isEmpty();
    }
}
