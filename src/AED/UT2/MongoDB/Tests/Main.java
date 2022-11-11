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
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;

public class Main {
    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
            databases.forEach(db -> System.out.println(db.toJson()));
        }

//        findDocument();
//        insertDocument();
//        deleteDocument();
//        editDocument();
    }

    private static void editDocument() {

        try(MongoClient mongoClient = new MongoClient("localhost", 27017)){

            MongoDatabase mongoDatabase = mongoClient.getDatabase("Test_ddbb");

            MongoCollection<Document> coll = mongoDatabase.getCollection("clientes");

            coll.updateMany(eq("Name", "Peng"), new Document("$set",
                    new Document("Age",69)));
        }
    }


    private static void deleteDocument(){

        try(MongoClient mongoClient = new MongoClient("localhost", 27017)){

            MongoDatabase mongoDatabase = mongoClient.getDatabase("Test_ddbb");

            MongoCollection<Document> coll = mongoDatabase.getCollection("clientes");

            coll.deleteOne(eq("Name", "Nico"));
        }

    }

    private static void findDocument() {
        try(MongoClient mongoClient = new MongoClient("localhost", 27017)){

            MongoDatabase mongoDatabase = mongoClient.getDatabase("Test_ddbb");

            MongoCollection<Document> coll = mongoDatabase.getCollection("clientes");

            try (MongoCursor<Document> cursor = coll.find().iterator()) {

                for (int i = 0; i < coll.countDocuments(); i++) {

                    Document doc = cursor.next();
                    System.out.println(doc.getString("Name"));
                    System.out.println(doc.getInteger("Age"));
                    System.out.println(doc.getString("Phone"));
                }

            }
        }
    }

    private static void insertDocument() {
        try(MongoClient mongoClient = new MongoClient("localhost", 27017)){

        MongoDatabase mongoDatabase = mongoClient.getDatabase("Test_ddbb");

        MongoCollection<Document> coll = mongoDatabase.getCollection("clientes");

        Document doc = new Document("Name", "Nico")
                .append("Age", 14)
                .append("Phone", "696969696");

        coll.insertOne(doc);
        }

        System.out.println("Guardado!");
    }
}
