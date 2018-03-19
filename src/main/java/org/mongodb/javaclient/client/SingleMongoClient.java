package org.mongodb.javaclient.client;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class SingleMongoClient {
    private static SingleMongoClient ourInstance = new SingleMongoClient();

    public static SingleMongoClient getInstance() {
        return ourInstance;
    }

    private SingleMongoClient() {
    }


    public MongoClient getClient() throws Exception{
        return  new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    }


}
