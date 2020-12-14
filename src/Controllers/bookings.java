package Controllers;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.SneakyThrows;
import org.bson.Document;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class bookings implements Initializable {
    public TableColumn<bookingSchema, Integer> Id;
    public TableColumn<bookingSchema, String> name;
    public TableColumn<bookingSchema, String> date;
    public TableColumn<bookingSchema, Integer> tableNumber;
    public TableColumn<bookingSchema, String> contact;
    public TableColumn<bookingSchema, String> status;
    public TableView<bookingSchema> restaurantTable;
    public TextField tables;
    public TextField deleteId;
    public TextField accept_id;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void init() throws UnknownHostException {
        Id.setCellValueFactory(new PropertyValueFactory<bookingSchema, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<bookingSchema, String>("name"));
        tableNumber.setCellValueFactory(new PropertyValueFactory<bookingSchema, Integer>("table_number"));
        date.setCellValueFactory(new PropertyValueFactory<bookingSchema, String>("date"));
        contact.setCellValueFactory(new PropertyValueFactory<bookingSchema, String>("contact"));
        status.setCellValueFactory(new PropertyValueFactory<bookingSchema, String>("status"));
        System.out.println();

        restaurantTable.setItems(db.getAllBookings());

    }

    public void manager(ActionEvent actionEvent) {
        try {
            root.main_stage.setScene(root.manager_scene);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void accepted(ActionEvent actionEvent) throws UnknownHostException {
        if(!accept_id.getText().equals("")){
            int accepted = Integer.parseInt(accept_id.getText());
            Document query = new Document();
            query.put("_bookingId", accepted);
            MongoCollection<Document> collection = DbManager.getColl("bookings");
            FindIterable<Document> userDoc = collection.find(query);

            if(userDoc!=null){
                BasicDBObject query1 = new BasicDBObject();
                query1.put("_bookingId", accepted);

                BasicDBObject document = new BasicDBObject();
                document.put("status", "Accepted");

                BasicDBObject update = new BasicDBObject();
                update.put("$set",document);

                collection.updateOne(query1, update);

            }else{
                System.out.println("No such booking Id Registered!");
            }

        }else{
            System.out.println("Field is Empty!");
        }

        restaurantTable.getItems().removeAll(restaurantTable.getItems());
        init();
        accept_id.clear();
    }

    public void editTables(ActionEvent actionEvent) throws UnknownHostException {
        if(!tables.getText().equals("")) {
            int table = Integer.parseInt(tables.getText());

            if (table > 0) {
                MongoCollection<Document> collection = DbManager.getColl("table");
                collection.drop();

                for(int i = 1; i<=table; i++){
                    tableSchema newTable = new tableSchema(i);
                    db.saveTable(newTable);
                }
            }
        }
        tables.clear();
    }

    public void completed(ActionEvent actionEvent) throws UnknownHostException {

        if(!deleteId.getText().equals("")){
            int completed = Integer.parseInt(deleteId.getText());
            Document query = new Document();
            query.put("_bookingId", completed);
            MongoCollection<Document> collection = DbManager.getColl("bookings");
            FindIterable<Document> userDoc = collection.find(query);

            if(userDoc!=null){
                int table = (Integer) userDoc.first().get("table_number");
                tableSchema newTable = new tableSchema(table);
                db.saveTable(newTable);

                collection.deleteOne(Filters.eq("_bookingId", completed));

            }else{
                System.out.println("No such booking Id Registered!");
            }

        }else{
            System.out.println("Field is Empty!");
        }

        restaurantTable.getItems().removeAll(restaurantTable.getItems());
        init();
        deleteId.clear();

    }

}
