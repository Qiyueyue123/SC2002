public abstract class User {
    
    protected String name;
    protected int age;
    protected boolean married;
    protected String password;
    protected String nric;

    public User(String name, int age, boolean married, String nric) {
        this.name = name;
        this.age = age;
        this.married = married;
        this.nric = nric;
    }

    public String getName(){
        return name;
    }

    public String getNRIC(){
        return nric;
    }

    public int getAge(){
        return age;
    }

    public boolean isMarried(){
        return married;
    }

    

}
