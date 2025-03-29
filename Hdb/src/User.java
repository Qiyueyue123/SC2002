public abstract class User {
    private String name;
    private String nric;
    private String password;
    private int age;
    private boolean married;

    public User(String nric, String name,String password, int age, boolean married){
        this.nric = nric;
        this.name = name;
        this.password = password;
        this.age = age;
        this.married = married;
    }
    public String getNric(){return nric;}
    public String getName(){return name;}
    public int getAge(){return age;}
    public boolean getMaritalStatus(){return married;}
}
