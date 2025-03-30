public abstract class User {
    private String name;
    private String nric;
    private String password;
    protected int age;
    protected boolean married;
    protected Application application;

    public User(String nric, String name,String password, int age, boolean married){
        this.name = name;
		this.nric = nric;
		this.age = age;
		this.married = married;
		this.application = null;
    }
    public String getNric(){return nric;}
    public String getName(){return name;}
    public int getAge(){return age;}
    public boolean getMaritalStatus(){return married;}
    public Application getApplication(){return application;}
}
