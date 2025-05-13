package org.datasource.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Service;

@Service
public class MongoDataSourceConnector {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "real_estate";

    // Cream o singură instanță de MongoClient
    private final MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);

    public MongoDatabase getMongoDatabase() {
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}
