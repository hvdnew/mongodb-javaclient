package org.mongodb.javaclient.application;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;
import org.mongodb.javaclient.client.SingleMongoClient;

import java.net.UnknownHostException;
import java.util.List;

class DataDemo {

    void insertData(String dbName, String tableName, Document dbObject) throws UnknownHostException {

        // Accessing the database
        MongoCollection<Document> collection = getCollection(dbName, tableName);
        collection.insertOne(dbObject);
    }

    void insertMany(String dbName, String tableName, List<Document> objectsToInsert) throws UnknownHostException {

        // Accessing the database
        MongoCollection<Document> collection = getCollection(dbName, tableName);
        InsertManyOptions option = new InsertManyOptions();
        option.ordered(true);
        collection.insertMany(objectsToInsert, option);
    }

    MongoCursor<Document> queryData(String dbName, String tableName, Document ref) throws UnknownHostException {
        MongoCollection<Document> coll = getCollection(dbName, tableName);
        return coll.find(ref).iterator();
    }

    MongoCursor<Document> paginate(String dbName, String tableName, int pageSize, int pageNumber) throws UnknownHostException {
        MongoCollection<Document> collection = getCollection(dbName, tableName);
        return collection.find().skip(pageSize * (pageNumber - 1)).limit(pageSize).iterator();
    }

    private MongoCollection<Document> getCollection(String dbName, String tableName) throws UnknownHostException {
        MongoDatabase db = SingleMongoClient.getInstance().getClient().getDatabase(dbName);
        return db.getCollection(tableName);
    }

}
