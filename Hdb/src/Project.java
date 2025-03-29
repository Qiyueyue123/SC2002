import java.util.ArrayList;

public class Project {
    private String name;
	private boolean visibility; 
	private int num2Rooms;
	private int num3Rooms;
	private ArrayList<Applicant> peopleApplied;
	
	Project(String name, boolean visibility, int num2Rooms, int num3Rooms, ArrayList<Applicant> peopleApplied) {
		this.name = name;
		this.visibility = visibility;
		this.num2Rooms = num2Rooms;
		this.num3Rooms = num3Rooms;
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
	public void print() {
		System.out.println(name);
		System.out.println("2 Room: " + num2Rooms);
		System.out.println("3 Room: " + num3Rooms);
	}
	public void addPerson(Applicant a) {
		peopleApplied.add(a);
	}
}
