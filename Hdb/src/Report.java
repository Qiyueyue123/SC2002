public class Report {
    private Application application;
    private Applicant applicant;
    
    public Report(Application application, Applicant applicant) {
        this.application = application;
        this.applicant  = applicant;
    }
    
    public Application getApplication() {
        return application;
    }
    
    public Applicant getApplicant() {
        return applicant;
    }
}