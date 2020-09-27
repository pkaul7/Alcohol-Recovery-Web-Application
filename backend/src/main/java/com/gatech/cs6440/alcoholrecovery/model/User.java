package  com.gatech.cs6440.alcoholrecovery.model;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
@Entity
@Data
@Table(name="users")
public final class User {

    @Transient
    private static User INSTANCE;
    @Id
    private Integer id;


    private String email;
    private String firstName;
    private String lastName;

    @Column(unique=true)
    private String userName;

    private String password;
    private String birthDate;
    //in ibs
    private int weight;
    //in inches
    private int height;
    //true if gender is male
    private boolean genderMale;
    
    private boolean loggedIn = false;
    
    private String condition;

    @Transient
    private ArrayList<Milestone> milestoneList = new ArrayList<Milestone>();
    
    public User() {
        
    }

    public void setUser(int id, String email, String firstName, String lastName, String userName, String password, String birthDate, int weight, int height, boolean genderMale, String condition){
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.birthDate = birthDate;
        this.weight = weight;
        this.height = height;
        this.genderMale = genderMale;
        this.loggedIn = true;
        this.condition = condition;
    }
    
    public static User getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new User();
        }
         
        return INSTANCE;
    }


    public static void setCurrentUser(User user){
        INSTANCE = user;
    }
    public static void clearInstance() {
        INSTANCE = new User();
    }

    public int getId(){
        return id;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public void setLoggedIn(boolean logged) {
        loggedIn = logged;
    }
    
    public String getFName(){
        return firstName;
    }
    
    public String getcondition(){
        return condition;
    }
    
    public void setFName(String firstname){
        this.firstName = firstname;
    }
    
    public void setCondition(String condition){
        this.condition = condition;
    }

    public String getLName(){
        return lastName;
    }

    public String getUName(){
        return userName;
    }
    
    public void setLName(String lastname){
        this.lastName = lastname;
    }

    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setPass(String pass){
        this.password = pass;
    }
    
    public void setBDate(String bDate){
        this.birthDate = bDate;
    }
    
    public void setHeight(String height){
        this.height = Integer.parseInt(height);
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getWeight(){
        return weight;
    }
    
    public void setWeight(String weight){
        this.weight = Integer.parseInt(weight);
    }
    
    //true if gender is male
    public void setGender(boolean gender){
        this.genderMale = gender;
    }

    public void addMilestone(Milestone m){
        milestoneList.add(m);
    }
    //update
    public ArrayList<Milestone> getMilestones(){
        return milestoneList;
    }
}
