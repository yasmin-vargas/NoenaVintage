package Model;
@Entity
public class User {
    private int userID;
    private String userName;
    private String userPassword;

    public User(int userID, String username, String password){
        this.userID = userID;
        this.userName = username;
        this.userPassword = password;
    }

    //Getters and setters
    public int getUserID(){

        return userID;
    }
    public void setUserID(int userID) {

        this.userID = userID;
    }
    public int getUserName(){

        return userName;
    }
    public void setUserName(int userName) {

        this.userName = userName;
    }
    public int getUserPassword(){

        return userPassword;
    }
    public void setUserPassword(int userPassword) {

        this.userPassword = userPassword;
    }
    public boolean login(String enteredPassword){
        // checks if the entered password == the stored password
        return enteredPassword.equals(userPassword);
    }

    // Missing a method for Registering a new User account
}
