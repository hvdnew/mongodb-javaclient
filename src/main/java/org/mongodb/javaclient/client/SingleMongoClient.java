package org.mongodb.javaclient.client;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import java.net.UnknownHostException;
import java.util.Arrays;

public class SingleMongoClient {
    private static SingleMongoClient ourInstance = new SingleMongoClient();

    public static SingleMongoClient getInstance() {
        return ourInstance;
    }

    private SingleMongoClient() {
    }


    public MongoClient getClient() throws UnknownHostException {

        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        //client.setReadPreference(ReadPreference.secondaryPreferred()); // this is related to sharding

        /*MongoCredential credential = MongoCredential.createCredential(userName, database, password);
        MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));*/
        return client;
    }

    private MongoClient getReplica() throws Exception {

        // You can connect to a replica set using the Java driver by passing a ServerAddress list to the MongoClient constructor. For example:
        MongoClient mongoClient = new MongoClient(Arrays.asList(
                new ServerAddress("localhost", 27017),
                new ServerAddress("localhost", 27018),
                new ServerAddress("localhost", 27019)));

        return mongoClient;
    }


}
