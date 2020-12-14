package Controllers;

import javafx.event.ActionEvent;

public class Contact {
    public void Customer(ActionEvent actionEvent) {
        try {
            root.main_stage.setScene(root.customer_scene);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
