// this is to test the displays and the functions within those displays
// this is NOT meant to replace the actual main app
// hence it does not have any of the log in functions 

import java.util.ArrayList;

public class TestingApp {
    public static void main(String[] args) {
        new EnquiryList(); //singular enquiry list created to handle projects, bc obj need to be created b4 can be used
		new ProjectList(); //singular project list created to handle projects, bc obj need to be craeted b4 can be used
		Application a = new Application();
		ArrayList<Applicant> dummyList = new ArrayList<Applicant>();
		Applicant Joe = new Applicant("Joe", 40, true, a);
		Applicant Tim = new Applicant("Tim", 23, true, a);
		
		Project AllGroves = new Project("All  Groves", true, 3, 4, dummyList);
		Project ZedWays = new Project("ZedWays", true, 0, 5, dummyList);
		Project BummerGroves = new Project("BummerGroves", true, 12, 0, dummyList);
		
		ProjectList.addProject(ZedWays);
		ProjectList.addProject(AllGroves);
		ProjectList.addProject(BummerGroves);
		ProjectList.showAllProjects();
		

		
		UserDisplay chosenDisplay = new ApplicantDisplay(Joe); 
		chosenDisplay.showDisplay();
		
    }
}
