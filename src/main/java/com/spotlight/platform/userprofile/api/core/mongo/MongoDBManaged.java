package com.spotlight.platform.userprofile.api.core.mongo;

import com.mongodb.client.MongoClient;
import io.dropwizard.lifecycle.Managed;

public class MongoDBManaged implements Managed {

    private MongoClient mongoClient;



    public MongoDBManaged(final MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void stop() throws Exception {
        System.out.println("closing");
        mongoClient.close();
    }
}
