package Controllers;

import com.mongodb.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.net.UnknownHostException;


public class db {

    private static final String DBNAME = "restaurant";
    private static final String BOOKING_COLL_NAME = "bookings";
    private static final String MENU_COLL_NAME = "menu";
    private static final String TABLE_COLL_NAME = "table";
    private static final String USER_COLL_NAME = "users";



    /************************DATABASE FOR BOOKINGS*****************************/
    public static void saveBooking(bookingSchema booking)

            throws UnknownHostException {
        DBObject dbObject = BasicDBObjectBuilder.start()
                .add("_bookingId",booking.getId())
                .add("name",booking.getName())
                .add("table_number",booking.getTable_number())
                .add("date",booking.getDate())
                .add("contact",booking.getContact())
                .add("status",booking.getStatus())
                .get();

        DB db = DbManager.getDb(DBNAME);
        DBCollection dbCollection = db.getCollection(BOOKING_COLL_NAME);
        dbCollection.save(dbObject);
    }

    public static ObservableList<bookingSchema> getAllBookings()
            throws UnknownHostException{
        DB db = DbManager.getDb(DBNAME);
        DBCollection dbCollection = db.getCollection(BOOKING_COLL_NAME);
        DBCursor dbCursor = dbCollection.find();
        ObservableList<bookingSchema> allBookings = FXCollections.observableArrayList();

        while ( dbCursor.hasNext()){
            DBObject dbObject = dbCursor.next();
            Integer _bookingId = (Integer) dbObject.get("_bookingId");
            String name = String.valueOf(dbObject.get("name"));
            Integer table_number = (Integer) dbObject.get("table_number");
            String date = (String) dbObject.get("date");
            String contact = String.valueOf(dbObject.get("contact"));
            String status = String.valueOf(dbObject.get("status"));
            bookingSchema booking = new bookingSchema(_bookingId, name, table_number, date, contact,status);
            allBookings.add(booking);
        }

        return allBookings;
    }


    /************************DATABASE FOR RESTAURANT Menu*****************************/
    public static void saveMenu(menuSchema menu)

            throws UnknownHostException {
        DBObject dbObject = BasicDBObjectBuilder.start()
                .add("_id",menu.getId())
                .add("dish",menu.getDish())
                .add("description",menu.getDescription())
                .add("price",menu.getPrice())
                .get();
        DB db = DbManager.getDb(DBNAME);
        DBCollection dbCollection = db.getCollection(MENU_COLL_NAME);
        dbCollection.save(dbObject);
    }

    public static ObservableList<menuSchema> getMenu()
            throws UnknownHostException{
        DB db = DbManager.getDb(DBNAME);
        DBCollection dbCollection = db.getCollection(MENU_COLL_NAME);
        DBCursor dbCursor = dbCollection.find();
        ObservableList<menuSchema> allMenuItems = FXCollections.observableArrayList();

        while ( dbCursor.hasNext()){
            DBObject dbObject = dbCursor.next();
            Integer _id = (Integer) dbObject.get("_id");
            String dish = String.valueOf(dbObject.get("dish"));
            String description = String.valueOf(dbObject.get("description"));
            String price = String.valueOf(dbObject.get("price"));
            menuSchema menu = new menuSchema(_id, dish, description, price);
            allMenuItems.add(menu);
        }

        return allMenuItems;
    }



    /************************DATABASE FOR Available Tables*****************************/
    public static void saveTable(tableSchema tableNumber)

            throws UnknownHostException {
        DBObject dbObject = BasicDBObjectBuilder.start()
                .add("tableNumber",tableNumber.getTable())
                .get();
        DB db = DbManager.getDb(DBNAME);
        DBCollection dbCollection = db.getCollection(TABLE_COLL_NAME);
        dbCollection.save(dbObject);
    }

    public static ObservableList<Integer> getTable()
            throws UnknownHostException{
        DB db = DbManager.getDb(DBNAME);
        DBCollection dbCollection = db.getCollection(TABLE_COLL_NAME);
        DBCursor dbCursor = dbCollection.find();
        ObservableList<Integer> allTables = FXCollections.observableArrayList();

        while ( dbCursor.hasNext()){
            DBObject dbObject = dbCursor.next();
            Integer tableNumber = (Integer) dbObject.get("tableNumber");
            allTables.add(tableNumber);
        }

        return allTables;
    }




    /************************DATABASE FOR Customers Username and Password*****************************/
    public static void saveUser(userSchema users)

            throws UnknownHostException {
        DBObject dbObject = BasicDBObjectBuilder.start()
                .add("userId",users.getId())
                .add("name",users.getName())
                .add("username",users.getUsername())
                .add("password",users.getPassword())
                .get();

        DB db = DbManager.getDb(DBNAME);
        DBCollection dbCollection = db.getCollection(USER_COLL_NAME);
        dbCollection.save(dbObject);
    }

    public static ObservableList<userSchema> getAllUsers()
            throws UnknownHostException{
        DB db = DbManager.getDb(DBNAME);
        DBCollection dbCollection = db.getCollection(USER_COLL_NAME);
        DBCursor dbCursor = dbCollection.find();
        ObservableList<userSchema> allUsers = FXCollections.observableArrayList();

        while ( dbCursor.hasNext()){
            DBObject dbObject = dbCursor.next();
            Integer userId = (Integer) dbObject.get("userId");
            String name = String.valueOf(dbObject.get("name"));
            String username = String.valueOf(dbObject.get("username"));
            String password = String.valueOf(dbObject.get("password"));
            userSchema users = new userSchema();
            users.userId = userId;
            users.name = name;
            users.username = username;
            users.password = password;
            allUsers.add(users);
        }

        return allUsers;
    }
}

