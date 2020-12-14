package Controllers;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbManager{

    public static String getMangoDBUrl() throws IOException {
        Properties prop = new Properties();
        String fileName = "app.properties";
        InputStream fileStream = new FileInputStream(fileName);
        prop.load(fileStream);
        return prop.getProperty("DbURL");
    }

    //private static final MongoClientURI url = new MongoClientURI("mongodb://admin_abhijeet:<password>@cluster0-shard-00-00.ismwj.mongodb.net:27017,cluster0-shard-00-01.ismwj.mongodb.net:27017,cluster0-shard-00-02.ismwj.mongodb.net:27017/<dbname>?ssl=true&replicaSet=atlas-hwhcyv-shard-0&authSource=admin&retryWrites=true&w=majority");
    //private static final MongoClientURI url = new MongoClientURI();

    private static DB db;

    public static DB getDb (String name) {

        try {
            String DBUrl = getMangoDBUrl();
            MongoClientURI url = new MongoClientURI(DBUrl);


        MongoClient mongo = new MongoClient(url);

        if ( db == null){
            db = mongo.getDB(name);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return db;
    }

    public static MongoCollection<Document> getColl (String db_coll_name){

        //MongoClientURI url = new MongoClientURI("mongodb://localhost:27017/restaurant");
        MongoCollection<Document> collection = null;
        try {
            MongoClientURI url = new MongoClientURI(getMangoDBUrl());


        String db_name = "restaurant";

        // Connecting to the mongodb server using the given client uri.
        MongoClient mongo_client = new MongoClient(url);

        // Fetching the database from the mongodb.
        MongoDatabase database = mongo_client.getDatabase(db_name);

        // Fetching the collection from the mongodb.
        collection = database.getCollection(db_coll_name);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return collection;
    }
}