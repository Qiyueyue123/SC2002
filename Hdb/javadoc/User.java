public abstract class User {
    
    protected String name;
    protected int age;
    protected boolean married;
    protected String password;
    protected String nric;

    public User(String nric, String name, String password, int age, boolean married) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.married = married;
        this.nric = nric;
        
    }

    public String getName(){
        return name;
    }

    public String getNRIC(){
        return nric;
    }

    public String getPassword() {
        return password;
    }

    public int getAge(){
        return age;
    }

    public boolean isMarried(){
        return married;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
