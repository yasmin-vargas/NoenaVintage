package Model;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;
import java.sql.Timestamp;
import java.time.LocalDate;

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
    private String email;
    private String phone;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private RoleEnum userRole;
    @Column(name = "regDate")
    private Timestamp regDate;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> userAddresses;
    public User(String userName, String userPassword, String firstName, String lastName, String email, String phone, LocalDate birthDate, RoleEnum userRole, Timestamp regDate){
        this.userName = userName;
        this.userPassword = userPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.userRole = userRole;
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
    public LocalDate getBirthDate() {return birthDate;}
    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}
    public RoleEnum getUserRole() {return userRole;}
    public void setUserRole(RoleEnum userRole) {this.userRole = userRole;}
    public Timestamp getRegDate() {return regDate;}
    public void setRegDate(Timestamp regDate) {this.regDate = regDate;}

    //Login method
    public boolean login(String enteredPassword) {
         // Check if enteredPassword matches the stored password
         return enteredPassword.equals(this.userPassword);
    }
}
