/**
 * Entity. <p>
 * The {@code Report} class represents a report that links an {@code Application}
 * to the corresponding {@code Applicant}.
 * It is typically used to generate or manage reports involving application details.
 */
public class Report {

    /** The application associated with the report. */
    private Application application;

    /** The applicant who submitted the application. */
    private Applicant applicant;

    /**
     * Constructs a new {@code Report} with the given application and applicant.
     *
     * @param application the application being reported on
     * @param applicant the applicant who submitted the application
     */
    public Report(Application application, Applicant applicant) {
        this.application = application;
        this.applicant  = applicant;
    }

    /**
     * Returns the application associated with this report.
     *
     * @return the application
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Returns the applicant associated with this report.
     *
     * @return the applicant
     */
    public Applicant getApplicant() {
        return applicant;
    }
}
