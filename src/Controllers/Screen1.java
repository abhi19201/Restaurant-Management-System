package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Screen1 {

    public AnchorPane scene;

    public void manager_login_click(MouseEvent mouseEvent) {
        try {
            root.main_stage.setScene(root.manager_login_scene);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void customer_click(MouseEvent mouseEvent) {

        try {
            root.main_stage.setScene(root.customer_login_scene);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void click_mode(ActionEvent check) {
        if (check.getSource() instanceof CheckBox) {
            CheckBox chk = (CheckBox) check.getSource();
            if(chk.isSelected()){
                scene.getStylesheets().addAll("Styles/styles.css");
                root.manager_login_scene.getStylesheets().addAll("Styles/styles.css");
                root.manager_scene.getStylesheets().addAll("Styles/styles.css");
                root.customer_scene.getStylesheets().addAll("Styles/styles.css");
                root.customer_login_scene.getStylesheets().addAll("Styles/styles.css");
                root.view_menu_scene.getStylesheets().addAll("Styles/styles.css");
                root.Contact_scene.getStylesheets().addAll("Styles/styles.css");
                root.bookings_scene.getStylesheets().addAll("Styles/styles.css");

            }else{
                scene.getStylesheets().removeAll("Styles/styles.css");
                root.manager_login_scene.getStylesheets().removeAll("Styles/styles.css");
                root.manager_scene.getStylesheets().removeAll("Styles/styles.css");
                root.customer_scene.getStylesheets().removeAll("Styles/styles.css");
                root.customer_login_scene.getStylesheets().removeAll("Styles/styles.css");
                root.view_menu_scene.getStylesheets().removeAll("Styles/styles.css");
                root.Contact_scene.getStylesheets().removeAll("Styles/styles.css");
                root.bookings_scene.getStylesheets().removeAll("Styles/styles.css");
            }

        }
    }
}
