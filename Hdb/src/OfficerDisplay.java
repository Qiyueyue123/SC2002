import java.util.Scanner;
public class OfficerDisplay implements UserDisplay{
    private Officer officer;
    Scanner sc = new Scanner(System.in);
    
    OfficerDisplay(Officer officer) {
		this.officer = officer;
	}

    public void showDisplay() {
        System.out.println("Continue as?:");
        System.out.println("1. Applicant");
        System.out.println("2. Officer");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                
        }
    }
}
