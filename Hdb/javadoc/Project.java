import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.apple.eawt.Application;

/**
 * Entity.
 * Represents a housing project with details such as name, neighbourhood,
 * unit availability, pricing, application period, managing officer, and applicants.
 */
public class Project {
    private String name;
    private String neighbourhood;
    private boolean visibility;
    private int num2Rooms;
    private int num3Rooms;
    private String openingDate;
    private String closingDate;
    private int availOfficerSlots = 10;
    private Manager manager;
    private ArrayList<Applicant> peopleApplied;
    private int price2room;
    private int price3room;
    private ArrayList<Officer> officers;

    /**
     * Constructs a Project object with the specified details.
     *
     * @param name            the project name
     * @param neighbourhood   the neighbourhood of the project
     * @param visibility      visibility status (true if visible)
     * @param num2Rooms       number of 2-room units
     * @param num3Rooms       number of 3-room units
     * @param openingDate     application start date
     * @param closingDate     application end date
     * @param availOfficerSlots number of available officer slots
     * @param manager         the manager of the project
     * @param price2room      price for 2-room units
     * @param price3room      price for 3-room units
     */
    Project(String name, String neighbourhood, boolean visibility, int num2Rooms, int num3Rooms,
            String openingDate, String closingDate, int availOfficerSlots, Manager manager,
            int price2room, int price3room) {
        this.name = name;
        this.neighbourhood = neighbourhood;
        this.visibility = visibility;
        this.num2Rooms = num2Rooms;
        this.num3Rooms = num3Rooms;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.availOfficerSlots = availOfficerSlots;
        this.manager = manager;
        this.peopleApplied = new ArrayList<>();
        this.price2room = price2room;
        this.price3room = price3room;
        this.officers = new ArrayList<>();
    }

    // --- Getters ---

    public String getName() { return name; }
    public String getNeighborhood() { return neighbourhood; }
    public boolean getVisibility() { return visibility; }
    public int getNum2Rooms() { return num2Rooms; }
    public int getNum3Rooms() { return num3Rooms; }
    public String getOpeningDate() { return openingDate; }
    public String getClosingDate() { return closingDate; }
    public int getAvailOfficerSlots() { return availOfficerSlots; }
    public Manager getManager() { return manager; }
    public ArrayList<Applicant> getPeopleApplied() { return peopleApplied; }
    public int getPrice2Room() { return price2room; }
    public int getPrice3Room() { return price3room; }

    /**
     * Gets a list of applicants that have applied for this project.
     * 
     * @return list of applicants
     */
    public List<Applicant> getApplicantsForProject() {
        return ApplicationRepository.getAllApplications().stream()
                .map(Application::getApplicant)
                .collect(Collectors.toList());
    }

    /**
     * Gets a string containing the names of officers assigned to this project.
     * 
     * @return comma-separated string of officer names in quotes
     */
    public String getOfficerName() {
        List<String> names = new ArrayList<>();
        for (Officer o : officers) {
            names.add(o.getName());
        }
        String officerList = String.join(",", names);
        return "\"" + officerList + "\"";
    }

    // --- Setters ---

    public void setName(String name) { this.name = name; }
    public void setNeighborhood(String neighborhood) { this.neighbourhood = neighborhood; }
    public void setNum2Rooms(int num2Rooms) { this.num2Rooms = num2Rooms; }
    public void setNum3Rooms(int num3Rooms) { this.num3Rooms = num3Rooms; }
    public void setVisibility(boolean visibility) { this.visibility = visibility; }
    public void setOpeningDate(String openingDate) { this.openingDate = openingDate; }
    public void setClosingDate(String closingDate) { this.closingDate = closingDate; }
    public void setAvailOfficerSlots(int availOfficerSlots) { this.availOfficerSlots = availOfficerSlots; }
    public void setPrice2Room(int price2Room) { this.price2room = price2Room; }
    public void setPrice3Room(int price3Room) { this.price3room = price3Room; }

    // --- Modifiers ---

    /**
     * Decreases the number of 2-room units by one (if greater than 0).
     */
    public void minusNum2Rooms() {
        if (num2Rooms > 0) num2Rooms--;
    }

    /**
     * Decreases the number of 3-room units by one (if greater than 0).
     */
    public void minusNum3Rooms() {
        if (num3Rooms > 0) num3Rooms--;
    }

    /**
     * Decreases the available officer slots by one (if greater than 0).
     */
    public void minusAvailableOfficerSlots() {
        if (availOfficerSlots > 0) availOfficerSlots--;
    }

    /**
     * Toggles the visibility of the project.
     */
    public void toggleVisibility() {
        this.visibility = !this.visibility;
    }

    /**
     * Sets the officers in charge of the project based on approved registrations.
     */
    public void setOfficersInCharge() {
        this.officers = RegistrationRepository.getAllRegistrations().stream()
                .filter(r -> r.getProject().equals(this))
                .filter(r -> r.getStatus().equalsIgnoreCase("Approved"))
                .map(Registration::getOfficer)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Updates the list of people who applied to this project.
     */
    public void setAppliedPeople() {
        this.peopleApplied = ApplicationRepository.getAllApplications().stream()
                .filter(app -> app.getProject().equals(this))
                .map(Application::getApplicant)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Adds a person to the list of applicants.
     *
     * @param a the applicant to add
     */
    public void addPerson(Applicant a) {
        peopleApplied.add(a);
    }

    /**
     * Prints the project details to the console.
     */
    public void print() {
        System.out.println("Project Name: " + name);
        System.out.println("Neighborhood: " + neighbourhood);
        System.out.println("Visibility: " + (visibility ? "Visible" : "Hidden"));
        System.out.println("2-Room Units: " + num2Rooms);
        System.out.println("3-Room Units: " + num3Rooms);
        System.out.println("Application Period: " + openingDate + " to " + closingDate);
        System.out.println("Available Officer Slots: " + availOfficerSlots);
    }
}
