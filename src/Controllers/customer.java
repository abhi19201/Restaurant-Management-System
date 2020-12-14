package Controllers;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;
import org.bson.Document;

import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class customer implements Initializable {
    public TextField name_;
    public TextField contact_;
    public AnchorPane scene;
    public DatePicker date;
    public ChoiceBox<Integer> choiceBox;
    public TextField screen;
    static TextField name;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.name = name_;
        try {
            choiceBox.setItems(db.getTable());;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void view_menu(ActionEvent actionEvent) {
        try {
            root.main_stage.setScene(root.view_menu_scene);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void Contact(ActionEvent actionEvent) {
        try {
            root.main_stage.setScene(root.Contact_scene);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void book(ActionEvent actionEvent) throws UnknownHostException {

        if(choiceBox.getValue()==null || name_.getText()==null || contact_.getText()==null || date.getValue()==null){
            Alert newAlert = new Alert(Alert.AlertType.ERROR, "Missing Fields detected!", ButtonType.OK );

            newAlert.showAndWait();
        }else if(contact_.getText().length()!=10){
            Alert newAlert = new Alert(Alert.AlertType.ERROR, "Wrong Contact number entered!!", ButtonType.OK );

            newAlert.showAndWait();
            contact_.clear();
        }else if(date.getValue().isBefore(LocalDate.now())){
            Alert newAlert = new Alert(Alert.AlertType.ERROR, "Invalid Date Selected!", ButtonType.OK );

            newAlert.showAndWait();
            date.setValue(null);
        }else if(date.getValue().isEqual(LocalDate.now())){
            Alert newAlert = new Alert(Alert.AlertType.ERROR, "You cannot Book table for today.", ButtonType.OK );

            newAlert.showAndWait();
            date.setValue(null);
        }
        else{
            Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm to Book", ButtonType.OK , ButtonType.CANCEL);

            newAlert.showAndWait();

            if(newAlert.getResult()==ButtonType.OK){
                int tableChoice = choiceBox.getValue();
                int bookingId;

                if(db.getAllBookings().size() == 0 ){
                    bookingId = 1;
                }else{
                    FindIterable<Document> cursor = DbManager.getColl("bookings").find().sort(new Document("_bookingId", -1)).limit(1);
                    bookingId = (Integer) cursor.first().get("_bookingId") + 1;

                }

                bookingSchema newbooking = new bookingSchema(bookingId, name_.getText(),tableChoice, date.getValue().toString(),contact_.getText(), "Queued");

                db.saveBooking(newbooking);

                MongoCollection<Document> collection = DbManager.getColl("table");
                collection.deleteMany(Filters.eq("tableNumber", tableChoice));
                screen.setText("Booking in progress. You will get informed once Booking gets confirmed.");
            }
            contact_.clear();
            date.setValue(null);
            choiceBox.setValue(null);
            try {
                choiceBox.setItems(db.getTable());;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void refresh(ActionEvent actionEvent) {
        Document query = new Document();
        query.put("name", customer_login.name);
        FindIterable<Document> userDoc = DbManager.getColl("bookings").find(query);

        if(userDoc.first()!=null){

            if(userDoc.first().get("status").equals("Accepted")){
                screen.setText("Your booking is Successfully Done!. Enjoy your meal at our restaurant on "+userDoc.first().get("date")+". Your Table number will be "+userDoc.first().get("table_number"));

            }else{
                screen.setText("Booking in progress. You will get informed once Booking gets confirmed by Restaurant.");
            }

        }else{
            screen.setText("There are no bookings placed on your name!!");
        }

    }

    public void logout(ActionEvent actionEvent) {
        try {
            Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure!", ButtonType.OK , ButtonType.CANCEL);

            newAlert.showAndWait();

            if(newAlert.getResult()==ButtonType.OK) {
                root.main_stage.setScene(root.customer_login_scene);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        screen.clear();
    }

}
