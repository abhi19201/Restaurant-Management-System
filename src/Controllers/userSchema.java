package Controllers;

import java.io.Serializable;

public class userSchema implements Serializable {

    public int userId;
    public String name;
    public String username;
    public String password;


    public int getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() { return password; }

}
