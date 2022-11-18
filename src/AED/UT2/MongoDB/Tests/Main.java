package AED.UT2.MongoDB.Tests;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.MongoClientSettings;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

//        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
//            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
//            databases.forEach(db -> System.out.println(db.toJson()));
//        }

        String username = scanner.nextLine();
        String password = scanner.nextLine();

        logIn(username,password);
//        insertDocument();
//        deleteDocument();
//        editDocument();
    }

    private static void editDocument() {

        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {

            MongoDatabase mongoDatabase = mongoClient.getDatabase("Test_ddbb");

            MongoCollection<Document> coll = mongoDatabase.getCollection("clientes");

            coll.updateMany(eq("Name", "Peng"), new Document("$set",
                    new Document("Age", 69)));
        }
    }


    private static void deleteDocument() {

        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {

            MongoDatabase mongoDatabase = mongoClient.getDatabase("Test_ddbb");

            MongoCollection<Document> coll = mongoDatabase.getCollection("clientes");

            coll.deleteOne(eq("Name", "Nico"));
        }

    }

    /**
     *  TODO LogIn
     */
    private static void logIn(String username,String password) {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {

            MongoDatabase mongoDatabase = mongoClient.getDatabase("social_network");

            MongoCollection<Document> coll = mongoDatabase.getCollection("users");

            Document doc = coll.find(eq("_id", username)).first();

            if (doc != null) {
                if (doc.getString("password").equals(password)) {
//                    System.out.println("You logged in!");
                }else {
//                    System.out.println("Wrong password!");
                }
            } else {
//                System.out.println("User don't exits");
            }
        }
    }

//    private static void findDocument() {
//        try(MongoClient mongoClient = new MongoClient("localhost", 27017)){
//
//            MongoDatabase mongoDatabase = mongoClient.getDatabase("social_network");
//
//            MongoCollection<Document> coll = mongoDatabase.getCollection("users");
//
//            try (MongoCursor<Document> cursor = coll.find(eq("title", "The Room")).iterator()) {
//
//                for (int i = 0; i < coll.countDocuments(); i++) {
//
//                    Document doc = cursor.next();
//                    System.out.println(doc.getString("_id"));
//                    System.out.println(doc.getString("password"));
//                }
//
//            }
//        }
//    }

    /**
     * TODO Register
     */
    private static void register(String username, String password, String email) {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {

            MongoDatabase mongoDatabase = mongoClient.getDatabase("social_network");

            MongoCollection<Document> coll = mongoDatabase.getCollection("users");

            Document doc = new Document("_id", username)
                    .append("password", password)
                    .append("email", email);

            coll.insertOne(doc);
        }

        System.out.println("Guardado!");
    }

//    private static void insertDocument() {
//        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
//
//            MongoDatabase mongoDatabase = mongoClient.getDatabase("Test_ddbb");
//
//            MongoCollection<Document> coll = mongoDatabase.getCollection("clientes");
//
//            Document doc = new Document("Name", "Nico")
//                    .append("Age", 14)
//                    .append("Phone", "696969696");
//
//            coll.insertOne(doc);
//        }
//
//        System.out.println("Guardado!");
//    }
}
