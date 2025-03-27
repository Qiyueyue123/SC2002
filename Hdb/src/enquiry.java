
public class Enquiry {
    private String message;
    private String response;
    private final Applicant user;
//    private Project project;

   
    //initial response is not set
    //pretend project does not exist yet bc i need to figure out if i want another project manager
    public Enquiry(Applicant user) {
        this.message = null;
        this.response = null;
        this.user = user;
  //      this.project = project;
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
    }
    
    public Applicant getUser() {
    	return user;
    }


	//public Project getProject() {
	//	return project;
//	}

}
