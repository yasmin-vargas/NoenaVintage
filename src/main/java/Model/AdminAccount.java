package Model;
import Repository.OrderData;
import java.util.ArrayList;
@Entity
public class AdminAccount extends User {
    private int adminID;
    private String firstName;
    private String lastName;
    private String address;
    private int postalCode;
    private String city;
    private int phone;
    private String email;

    public AdminAccount (int adminID, String firstName, String lastName, String address, int postalCode, String city, int phone, String email) {
        this.adminID = adminID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }
    //Setters and Getters
    public int getAdminID() {
        return adminID;
    }
    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String categoryName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postalCode) {this.postalCode = postalCode;
        public String getCity() {return city;
        }
        public void setCity(String city) {this.city = city;
        }
        public int getPhone() {return phone;
        }
        public void setPhone(int phone) {this.phone = phone;
        }
        public String getEmail() {return email;
        }
        public void setEmail(String email) {this.email = email;
        }
    }
