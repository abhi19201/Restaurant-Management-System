package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.SneakyThrows;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class view_menu implements Initializable {

    public TableView<menuSchema> menuTable;
    public TableColumn<menuSchema, Integer> menuTableId;
    public TableColumn<menuSchema, String> menuTableDish;
    public TableColumn<menuSchema, String> menuTableDescription;
    public TableColumn<menuSchema, String> menuTablePrice;

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

    public void customer(ActionEvent actionEvent) {
        try {
            root.main_stage.setScene(root.customer_scene);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
