public class Application {
    Project appliedProject;
	String appliedStatus;
    int flatType;

    public Application() {
        appliedStatus = "Unapplied";
    }

    public void setAppliedProject(Project p) {
        appliedProject = p;
    }
    public void setAppliedStatus(String s) {
        appliedStatus = s;
    }
    public String getAppliedStatus() {
        return appliedStatus;
    }
    public void setFlatType(int r) {
        flatType = r;
    }
    public void print() {
        System.out.println("Project: " + appliedProject.getName());
        System.out.println("Application Status: " + appliedStatus);
        System.out.println("FlatType: " + flatType);
    }
    
}
