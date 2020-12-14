package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class root extends Application {

    static Scene scene1, manager_login_scene, customer_scene, manager_scene, view_menu_scene, Contact_scene, bookings_scene, customer_login_scene;
    static Stage main_stage;
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Restaurant Management  System");
        Parent scene1 = FXMLLoader.load(getClass().getResource("../fxml/screen1.fxml"));
        Scene root = new Scene(scene1);
        this.scene1 = root;
        scene1.getStylesheets().addAll("Styles/styles.css");

        Parent manager_login = FXMLLoader.load(getClass().getResource("../fxml/manager_login.fxml"));
        Scene manager_login_scene = new Scene(manager_login);
        this.manager_login_scene = manager_login_scene;
        manager_login_scene.getStylesheets().addAll("Styles/styles.css");

        Parent manager = FXMLLoader.load(getClass().getResource("../fxml/manager.fxml"));
        Scene manager_scene = new Scene(manager);
        this.manager_scene = manager_scene;
        manager_scene.getStylesheets().addAll("Styles/styles.css");

        Parent customer = FXMLLoader.load(getClass().getResource("../fxml/customer.fxml"));
        Scene customer_scene = new Scene(customer);
        this.customer_scene = customer_scene;
        customer_scene.getStylesheets().addAll("Styles/styles.css");


        Parent view_menu = FXMLLoader.load(getClass().getResource("../fxml/view_menu.fxml"));
        Scene view_menu_scene = new Scene(view_menu);
        this.view_menu_scene = view_menu_scene;
        view_menu_scene.getStylesheets().addAll("Styles/styles.css");


        Parent Contact = FXMLLoader.load(getClass().getResource("../fxml/Contact.fxml"));
        Scene Contact_scene = new Scene(Contact);
        this.Contact_scene = Contact_scene;
        Contact_scene.getStylesheets().addAll("Styles/styles.css");

        Parent bookings = FXMLLoader.load(getClass().getResource("../fxml/bookings.fxml"));
        Scene bookings_scene = new Scene(bookings);
        this.bookings_scene = bookings_scene;
        bookings_scene.getStylesheets().addAll("Styles/styles.css");

        Parent customer_login = FXMLLoader.load(getClass().getResource("../fxml/customer_login.fxml"));
        Scene customer_login_scene = new Scene(customer_login);
        this.customer_login_scene = customer_login_scene;
        customer_login_scene.getStylesheets().addAll("Styles/styles.css");


        stage.setScene(root);
        stage.setMinHeight(800.0);
        stage.setMinWidth(1200.0);
        stage.setResizable(false);
        this.main_stage = stage;
        stage.show();
    }
}
