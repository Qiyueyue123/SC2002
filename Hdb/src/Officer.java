public class Officer extends Applicant {
    private Project assignedProject;

    public Officer(String nric, String name, String password, int age, boolean married) {
        super(nric, name, password, age, married);
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
    }
    @Override
    public void display(){
        OfficerDisplay display = new OfficerDisplay(this);
        display.showDisplay();
    }
}
