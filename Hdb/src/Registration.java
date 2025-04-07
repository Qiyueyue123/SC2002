public class Registration {
    Officer officer;
    Project project;
    String status;

    public Registration(Officer o, Project p) {
        officer = o;
        project = p;
        status = "Approved"; // can be set by manager to pending, approved or rejected
    }

    public void print() {
        System.out.println();
        System.out.println("Project Name: " + project.getName());;
        System.out.println("Officer Name: " + officer.getName());
        System.out.println("Registration Status: " + status);
        System.out.println();
    }
}
