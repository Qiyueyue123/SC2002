
public class Enquiry {
    private static int instanceCount = 1;
	private final int id;
	private String message;
    private String response;
    private final User user;
    private boolean answered;
    private final Project project;


    Enquiry(User user, Project project) {
    	this.id = instanceCount++;
        this.message = null;
        this.response = null;
        this.user = user;
        this.project = project;
        this.answered = false;
    }
    
    public int getID() {
    	return id;
    }

    public void setMessage(String message) {
    	this.message = message;
    }
    
    public String getMessage() {
    	return message;
    }
    
    public String getResponse() {
    	return response;
    }
    
    public void setResponse(String response) {
    	this.response = response;
    	this.answered = true;
    }
    
    public Applicant getUser() {
    	return user;
    }


	public Project getProject() {
		return project;
	}
    
    public boolean isAnswered() {
    	return answered;
    }

	
}
