package Controllers;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.SneakyThrows;
import org.bson.Document;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class manager implements Initializable {

    public TextField name;
    public TextField price;
    public TextArea description;
    public TextField removeId;
    public TableView<menuSchema> menuTable;
    public TableColumn<menuSchema, String> menuTablePrice;
    public TableColumn<menuSchema, String> menuTableDescription;
    public TableColumn<menuSchema, String> menuTableDish;
    public TableColumn<menuSchema, Integer> menuTableId;


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
        menuTableId.setCellValueFactory(new PropertyValueFactory<menuSchema, Integer>("id"));
        menuTableDish.setCellValueFactory(new PropertyValueFactory<menuSchema, String>("dish"));
        menuTableDescription.setCellValueFactory(new PropertyValueFactory<menuSchema, String>("description"));
        menuTablePrice.setCellValueFactory(new PropertyValueFactory<menuSchema, String>("price"));

        menuTable.setItems(db.getMenu());

    }


    public void logout(ActionEvent actionEvent) {
        try {
            root.main_stage.setScene(root.manager_login_scene);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void menu(ActionEvent actionEvent) {
        try {
            root.main_stage.setScene(root.manager_scene);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void view_bookings(ActionEvent actionEvent) {
        try {
            root.main_stage.setScene(root.bookings_scene);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void add(ActionEvent actionEvent) throws UnknownHostException {

        if(name.getText().equals("") || price.getText().equals("") || description.getText().equals("")){
            Alert newAlert = new Alert(Alert.AlertType.ERROR, "Empty fields Detected..!", ButtonType.OK );

            newAlert.showAndWait();
        }else{
            int id;

            if(db.getMenu().size() == 0 ){
                id = 1;
            }else{
                FindIterable<Document> cursor = DbManager.getColl("menu").find().sort(new Document("_id", -1)).limit(1);
                id = (Integer) cursor.first().get("_id") + 1;

            }

            menuSchema newMenuDish = new menuSchema(id,name.getText(),description.getText(),price.getText());
            db.saveMenu(newMenuDish);
            menuTable.getItems().removeAll(menuTable.getItems());
            init();

            name.clear();
            description.clear();
            price.clear();
        }

    }

    public void removeDish(ActionEvent actionEvent) throws UnknownHostException {
        if(!removeId.getText().equals("")) {
            int remove = Integer.parseInt(removeId.getText());

            if (remove > 0) {
                MongoCollection<Document> collection = DbManager.getColl("menu");
                collection.deleteOne(Filters.eq("_id", remove));
            }
        }

        menuTable.getItems().removeAll(menuTable.getItems());
        init();
        removeId.clear();
    }
}
