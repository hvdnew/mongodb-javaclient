package org.mongodb.javaclient.application;

import com.mongodb.*;

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
        DBObject dbObject = buildDBObject();
        WriteResult writeResult = dataDemo.insertData(DB_NAME, TABLE_NAME, dbObject);
        log.info("write result: " + writeResult);

        // 2. Bulk operations
        BulkWriteResult bWriteResult = dataDemo.bulkOperations(DB_NAME, TABLE_NAME, orderedBulkWriteOperation());
        log.info("bulk write result: " + bWriteResult);

        // 3. Finding data
        DBObject query = buildQuery();
        log.info("results found");
        DBCursor cursor = dataDemo.queryData(DB_NAME, TABLE_NAME, query);
        while (cursor.hasNext()) {
            log.info(cursor.next().toString());
        }
        cursor.close();

    }

    private static DBObject buildDBObject() {
        return BasicDBObjectBuilder.start().add("firstName", "Harshvardhan").add("lastName", "Dadhich").get();
    }

    private static List<DBObject> orderedBulkWriteOperation() throws InterruptedException {

        List<DBObject> toReturn = new ArrayList<>();
        for (int i = 0; i < 100; i++) {

            toReturn.add(new BasicDBObject("anotherField", i + 1));
            Thread.sleep(100L);
        }

        for (int i = 0; i < 100; i++) {

            toReturn.add(new BasicDBObject("oneLast", i + 1));
            Thread.sleep(100L);
        }
        // we could handle find as well as remove in bulk operations
        /*builder.find(new BasicDBObject("_id", 1)).updateOne(new BasicDBObject("$set", new BasicDBObject("x", 2)));
        builder.find(new BasicDBObject("_id", 2)).removeOne();
        builder.find(new BasicDBObject("_id", 3)).replaceOne(new BasicDBObject("_id", 3).append("x", 4));*/

        return toReturn;
    }


    private static DBObject buildQuery() {

        return new BasicDBObject("lastName", "Dadhich");
    }

}
