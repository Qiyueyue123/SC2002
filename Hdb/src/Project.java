import java.util.ArrayList;

public class Project {
    private String name;
	private String neighbourhood;
	private boolean visibility; 
	private int num2Rooms;
	private int num3Rooms;
	private String openingDate;
	private String closingDate;
	private  int availOfficerSlots = 10;
	private Manager manager;
	private ArrayList<Applicant> peopleApplied;
	
	//might separate the list bc a new project got like 0 peeps applying
	Project(String name, String neighbourhood, boolean visibility, int num2Rooms, int num3Rooms, String openingDate, String closingDate, int availOfficerSlots, Manager manager) {
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
	}

	public String getName() {
		return name;
	}

	public String getNeighborhood(){
		return neighbourhood;
	}

	public boolean getVisibility() {
		return visibility;
	}
	public int getNum2Rooms() {
		return num2Rooms;
	}
	public int getNum3Rooms() {
		return num3Rooms;
	}

	public String getOpeningDate(){
		return openingDate;
	}

	public String getClosingDate(){
		return closingDate;
	}

	public  int getAvailOfficerSlots(){
		return availOfficerSlots;
	}

	public Manager getManager() {
		return manager;
	}

	public ArrayList<Applicant> getPeopleApplied() {
        return peopleApplied;
    }

	public void setName(String name) {
        this.name = name;
    }

	public void setNeighborhood(String neighborhood) {
        this.neighbourhood = neighborhood;
    }

	public void setNum2Rooms(int num2Rooms) {
		this.num2Rooms = num2Rooms;
	}
	
	public void setNum3Rooms(int num3Rooms) {
		this.num3Rooms = num3Rooms;
	}

	public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

	public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }
    
    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }
    
    // Dont need to set available officer slots since max is 10 (idk yet lol )
	public void setAvailOfficerSlots(int availOfficerSlots){
		this.availOfficerSlots = availOfficerSlots;
	}

	public void minusNum2Rooms() {
        if (num2Rooms > 0) {
            num2Rooms--;
        }
    }

	public void minusNum3Rooms() {
        if (num3Rooms > 0) {
            num3Rooms--;
        }
    }

	public void minusAvailableOfficerSlots() {
        if (availOfficerSlots > 0) {
            availOfficerSlots--;
        }
    }

	public void toggleVisibility(){
		this.visibility = !this.visibility;
	}


	public void print() {
        System.out.println("Project Name: " + name);
        System.out.println("Neighborhood: " + neighbourhood);
        System.out.println("Visibility: " + (visibility ? "On" : "Off"));
        System.out.println("2-Room Units: " + num2Rooms);
        System.out.println("3-Room Units: " + num3Rooms);
        System.out.println("Application Period: " + openingDate + " to " + closingDate);
        System.out.println("Available Officer Slots: " + availOfficerSlots);
    }

	// add applicant to list
	public void addPerson(Applicant a) {
		peopleApplied.add(a);
	}
}
