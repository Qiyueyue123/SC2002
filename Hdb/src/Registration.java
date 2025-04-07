public class Registration {
    Officer officer;
    Project project;
    String status;

    public Registration(Officer o, Project p) {
        officer = o;
        project = p;
        //Hardcode status to Approved if want to test the enquiry/booking stuff
        status = "Pending"; // can be set by manager to pending, approved or rejected
    }

    public void print() {
        System.out.println();
        System.out.println("Project Name: " + project.getName());;
        System.out.println("Officer Name: " + officer.getName());
        System.out.println("Registration Status: " + status);
        System.out.println();
    }
}
