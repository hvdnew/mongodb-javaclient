package org.mongodb.javaclient.application;

import com.mongodb.*;
import org.mongodb.javaclient.client.SingleMongoClient;

import java.net.UnknownHostException;
import java.util.List;

class DataDemo {

    WriteResult insertData(String dbName, String tableName, DBObject dbObject) throws UnknownHostException {

        // Accessing the database
        DBCollection collection = getCollection(dbName, tableName);
        return collection.insert(dbObject);
    }

    BulkWriteResult bulkOperations(String dbName, String tableName, List<DBObject> objectsToInsert) throws UnknownHostException {

        // Accessing the database
        DBCollection collection = getCollection(dbName, tableName);

        // 1. Ordered bulk operation
        BulkWriteOperation builder = collection.initializeOrderedBulkOperation(); // ordered
        objectsToInsert.forEach(builder::insert);
        return builder.execute();

        // 2. Unordered bulk operation - no guarantee of order of operation
        /*builder = coll.initializeUnorderedBulkOperation();
        builder.find(new BasicDBObject("_id", 1)).removeOne();
        builder.find(new BasicDBObject("_id", 2)).removeOne();

        result = builder.execute();*/
    }

    DBCursor queryData(String dbName, String tableName, DBObject ref) throws UnknownHostException {
        DBCollection coll = getCollection(dbName, tableName);
        return coll.find(ref);
    }

    private DBCollection getCollection(String dbName, String tableName) throws UnknownHostException {
        DB db = SingleMongoClient.getInstance().getClient().getDB(dbName);
        return db.getCollection(tableName);
    }

}
