// this is to test the displays and the functions within those displays
// this is NOT meant to replace the actual main app
// hence it does not have any of the log in functions 

import java.util.ArrayList;

public class TestingOfficerApp {
    public static void main(String[] args) {
        new EnquiryList(); //singular enquiry list created to handle projects, bc obj need to be created b4 can be used
		new ProjectList(); //singular project list created to handle projects, bc obj need to be craeted b4 can be used
		new ApplicationList();
        new RegistrationList();
		
		Officer O = new Officer("T1234567J", "John", "Password", 32, true);
		Manager Lee = new Manager("T1233366E","Lee", "password", 37, false );
		Applicant Joe = new Applicant("S1234567E","Joe", "password",40, true );

		Project AllGroves = new Project("All  Groves", "n1", true, 3, 4, "opening date", "closing date", 10, Lee);
		Project ZedWays = new Project("ZedWays", "neighbourhood 2", true, 0, 5, "opening date", "closing date", 10, Lee);
		Project BummerGroves = new Project("BummerGroves", "neighbourhood 3", true, 12, 0, "opening date", "closing date", 10, Lee);
		
		ProjectList.addProject(ZedWays);
		ProjectList.addProject(AllGroves);
		ProjectList.addProject(BummerGroves);
		//ProjectList.showAllProjects();
        Enquiry e = new Enquiry(Joe, BummerGroves);
        e.setMessage("cao ni ma");
		EnquiryList.addEnquiry(e);
		UserDisplay chosenDisplay = new OfficerDisplay(O); 
		chosenDisplay.showDisplay();		
    }
}