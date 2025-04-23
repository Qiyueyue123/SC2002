//applicant can do evrything needed with enquiries
//reply enquiry will be moved when officer and manager class is settled
public class Applicant extends User{
	private Application application;

    public Applicant(String nric, String name, String password, int age, boolean married) {
        super(nric, name, password, age, married);
		this.application = null;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application app) {
        this.application = app;
    }

        @Override
	public void display() {
		ApplicantDisplay display = new ApplicantDisplay(this);
		display.showDisplay();
	}
}