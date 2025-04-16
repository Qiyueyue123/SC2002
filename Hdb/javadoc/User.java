/**
 * Entity
 * An abstract representation of a User in the system.
 * This class provides basic attributes and methods shared by all user types
 * such as Applicants, Officers, and Managers.
 */
public abstract class User {

    /** The user's full name. */
    protected String name;

    /** The user's age. */
    protected int age;

    /** The user's marital status. */
    protected boolean married;

    /** The user's account password. */
    protected String password;

    /** The user's NRIC (unique identifier). */
    protected String nric;

    /**
     * Constructs a new User with the given attributes.
     *
     * @param nric the NRIC of the user
     * @param name the name of the user
     * @param password the user's password
     * @param age the age of the user
     * @param married the marital status of the user
     */
    public User(String nric, String name, String password, int age, boolean married) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.married = married;
        this.nric = nric;
    }

    /**
     * Returns the name of the user.
     *
     * @return the user's name
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the NRIC of the user.
     *
     * @return the user's NRIC
     */
    public String getNRIC(){
        return nric;
    }

    /**
     * Returns the user's password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the age of the user.
     *
     * @return the user's age
     */
    public int getAge(){
        return age;
    }

    /**
     * Returns the marital status of the user.
     *
     * @return {@code true} if married, {@code false} otherwise
     */
    public boolean isMarried(){
        return married;
    }

    /**
     * Updates the user's password.
     *
     * @param password the new password
     */
    public void setPassword(String password){
        this.password = password;
    }
}
