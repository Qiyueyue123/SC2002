import java.util.Scanner;
public class OfficerDisplay implements UserDisplay{
    private Officer officer;
    private ApplicantDisplay appDp;
    Scanner sc = new Scanner(System.in);
    
    OfficerDisplay(Officer officer) {
		this.officer = officer;
	}

    public void showDisplay() {
        System.out.println("Continue as?:");
        System.out.println("1. Applicant");
        System.out.println("2. Officer");
        int choice = sc.nextInt();
        if(choice==1){
            appDp = new ApplicantDisplay((Applicant)officer);
            appDp.showDisplay();
        }
        else if(choice==2){
            System.out.println("========== Officer User Menu ==========");
			System.out.println("|Below are the actions you can take:    |");
			System.out.println("|(1) Register for a Project             |");
			System.out.println("|(2) View Registration Status           |");
			System.out.println("|(3) View Project details               |");
			System.out.println("|(4) View Project Enquiries             |");
			System.out.println("|(5) Reply Project Enquiries            |");
			System.out.println("|(6) Book Flat for Applicant            |");
			System.out.println("|(7) Generate Receipt                   |");
			System.out.println("|(0) Exit                               |");

            int choice2 = sc.nextInt();
            switch(choice2){
                case 1:

            }
        }
    }
}
