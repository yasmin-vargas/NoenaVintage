package Model;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Set;
import java.sql.Timestamp;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;
    private String userName;
    private String userPassword;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
    @Column(name = "reg_date")
    private Timestamp regDate; // using Timestamp for a date
    public User(long userID, String userName, String userPassword, String firstName, String lastName, String phone, String email, Timestamp regDate){
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.regDate = regDate;
    }

    //Getters and setters
    public long getUserID(){return userID;}
    public void setUserID(long userID) {this.userID = userID;}
    public String getUserName(){return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getUserPassword(){return userPassword;}
    public void setUserPassword(String userPassword) {this.userPassword = userPassword;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public Timestamp getRegDate() {return regDate;}
    public void setRegDate(Timestamp regDate) {this.regDate = regDate;}

    //Login method
    public boolean login(String enteredPassword) {
         // Check if enteredPassword matches the stored password
         return enteredPassword.equals(this.userPassword);
    }
}
