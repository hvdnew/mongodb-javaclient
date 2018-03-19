package org.mongodb.javaclient;

import com.mongodb.*;
import org.mongodb.javaclient.client.SingleMongoClient;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String... args) throws Exception {

        MongoClient client = SingleMongoClient.getInstance().getClient();

        // Accessing the database
        DB db = client.getDB("test");

        DBObject object = BasicDBObjectBuilder.start().add("firstName", "Harshvardhan").add("lastName", "Dadhich").get();

        WriteResult writeResult = db.getCollection("new-table").insert(object);

        System.out.println(writeResult);

    }

}
