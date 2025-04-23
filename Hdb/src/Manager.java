

public class Manager extends User{
	public Manager(String nric, String name, String password, int age, boolean married) {
        super(nric, name, password, age, married);
    }
	public void display(){
		ManagerDisplay display = new ManagerDisplay(this);
		display.showDisplay();
	}
}
