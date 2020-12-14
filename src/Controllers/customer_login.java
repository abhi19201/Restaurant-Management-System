package Controllers;

import com.mongodb.client.FindIterable;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.bson.Document;

import java.net.UnknownHostException;

public class customer_login {
    public PasswordField password;
    public TextField username;
    public AnchorPane scene;
    public TextField Rusername;
    public TextField Rname;
    public PasswordField Rpassword2;
    public PasswordField Rpassword1;
    static String name;
    public TextField success;

    public void submit(ActionEvent actionEvent) throws UnknownHostException {

        Document query = new Document();
        query.put("username", username.getText());
        FindIterable<Document> userDoc = DbManager.getColl("users").find(query);

        if(userDoc.first()!=null){
            for(Document user : userDoc){
                this.name = user.get("name").toString();
                customer.name.setText(name);

                if (md5.decrypt(user.get("password").toString()).equals(password.getText())) {
                    try {
                        root.main_stage.setScene(root.customer_scene);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    Alert newAlert = new Alert(Alert.AlertType.ERROR, "Wrong Username or Password!", ButtonType.OK );

                    newAlert.showAndWait();
                    System.out.println("Wrong Username or Password");
                }
            }
        }else{
            Alert newAlert = new Alert(Alert.AlertType.ERROR, "User Does not Exist.", ButtonType.OK );

            newAlert.showAndWait();
            System.out.println("User Does not Exist");
        }
        username.clear();
        password.clear();
        success.clear();

    }

    public void register(ActionEvent actionEvent) throws UnknownHostException {
        success.setText("In progress...");
        if(Rpassword1.getText().equals(Rpassword2.getText()) && !Rpassword1.getText().equals("") ){

            if(Rpassword1.getText().length()>=6){
                int id;
                if(db.getAllUsers().size() == 0){
                    id = 1;
                }else{

                    FindIterable<Document> cursor = DbManager.getColl("users").find().sort(new Document("userId", -1)).limit(1);
                    id = (Integer) cursor.first().get("userId") + 1;
                }

                userSchema new_user = new userSchema();
                new_user.userId = id;
                new_user.name = Rname.getText();
                new_user.username = Rusername.getText();
                new_user.password = md5.encrypt(Rpassword1.getText());

                db.saveUser(new_user);
                success.setText("Registration Successful");

                Rname.clear();
                Rusername.clear();
                Rpassword1.clear();
                Rpassword2.clear();
            }else{
                Alert newAlert = new Alert(Alert.AlertType.ERROR, "Password too short. Length must be at least 6", ButtonType.OK );

                newAlert.showAndWait();
                success.clear();

                Rpassword1.clear();
                Rpassword2.clear();
            }

        }else {
            Alert newAlert = new Alert(Alert.AlertType.ERROR, "Password didn't match!", ButtonType.OK );

            newAlert.showAndWait();
            System.out.println("Password didn't match!");
            success.clear();

            Rname.clear();
            Rusername.clear();
        }

    }
}
