package org.mongodb.javaclient.application;

import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class Application {

    private static final Logger log = Logger.getLogger(Application.class.getName());

    private static final String DB_NAME = "tech-gig-demo";
    private static final String TABLE_NAME = "placeholder-table";

    public static void main(String... args) throws Exception {

        // 1. Insert Data
        DataDemo dataDemo = new DataDemo();
        Document dbObject = buildDBObject();
        dataDemo.insertData(DB_NAME, TABLE_NAME, dbObject);

        // 2. Bulk operations
        dataDemo.bulkOperations(DB_NAME, TABLE_NAME, multipleObjects());

        // 3. Finding data
        MongoCursor<Document> cursor = dataDemo.queryData(DB_NAME, TABLE_NAME, new Document().append("lastName", "Dadhich"));
        log.info("results found");
        while (cursor.hasNext()) {
            log.info(cursor.next().toJson());
        }

        // 4. Pagination
        cursor = dataDemo.paginate(DB_NAME, TABLE_NAME, 20, 3);
        log.info("results found");
        while (cursor.hasNext()) {
            log.info(cursor.next().toJson());
        }

    }

    private static Document buildDBObject() {
        return new Document().append("firstName", "harsh").append("lastName", "dadhich").append("age", "27");
    }

    private static List<Document> multipleObjects() throws InterruptedException {

        List<Document> toReturn = new ArrayList<>();
        for (int i = 0; i < 100; i++) {

            toReturn.add(new Document().append("firstName", "some guy").append("anotherField", i * i));
        }

        return toReturn;
    }
}
