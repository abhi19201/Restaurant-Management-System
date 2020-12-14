package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class manager_login {
    static String user;
    public AnchorPane scene;
    public TextField username;
    public PasswordField password;


    public void submit(ActionEvent actionEvent) {
        this.user = username.getText();
        if(username.getText().equals("") || password.getText().equals("")){
            Alert newAlert = new Alert(Alert.AlertType.ERROR, "Empty Field detected!", ButtonType.OK );

            newAlert.showAndWait();
            System.out.println("Empty Field detected!");
        }else{
            if(username.getText().equals("admin") && password.getText().equals("admin")){
                try {
                    root.main_stage.setScene(root.manager_scene);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                username.clear();
                password.clear();
            }else{
                Alert newAlert = new Alert(Alert.AlertType.ERROR, "Wrong Username or Password!", ButtonType.OK );

                newAlert.showAndWait();
                System.out.println("Wrong Username or Password");
                username.clear();
                password.clear();
            }
        }

    }
}
