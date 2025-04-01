import java.util.ArrayList;

public class Project {
    private String name;
	private boolean visibility; 
	private int num2Rooms;
	private int num3Rooms;
	private Manager manager;
	private ArrayList<Applicant> peopleApplied;
	
	//might separate the list bc a new project got like 0 peeps applying
	Project(String name, boolean visibility, int num2Rooms, int num3Rooms, Manager manager, ArrayList<Applicant> peopleApplied) {
		this.name = name;
		this.visibility = visibility;
		this.num2Rooms = num2Rooms;
		this.num3Rooms = num3Rooms;
		this.manager = manager;
		this.peopleApplied = peopleApplied;
	}

	public String getName() {
		return name;
	}
	public boolean getvisibility() {
		return visibility;
	}
	public int getNum2Rooms() {
		return num2Rooms;
	}
	public int getNum3Rooms() {
		return num3Rooms;
	}
	public Manager getManager() {
		return manager;
	}
	
	public void print() {
		System.out.println(name);
		System.out.println("2 Room: " + num2Rooms);
		System.out.println("3 Room: " + num3Rooms);
	}
	public void addPerson(Applicant a) {
		peopleApplied.add(a);
	}
}
