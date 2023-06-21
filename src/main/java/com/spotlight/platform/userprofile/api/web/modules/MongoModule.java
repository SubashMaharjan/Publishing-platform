package com.spotlight.platform.userprofile.api.web.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.spotlight.platform.userprofile.api.core.mongo.MongoDBConnection;
import com.spotlight.platform.userprofile.api.core.mongo.MongoDBFactoryConnection;
import com.spotlight.platform.userprofile.api.core.mongo.MongoDBManaged;
import com.spotlight.platform.userprofile.api.model.configuration.UserProfileApiConfiguration;


public class MongoModule extends AbstractModule {

    @Provides
    @Singleton
    public MongoClient getMongoClient(UserProfileApiConfiguration configuration) {
        final var mongoDBManagerConn = new MongoDBFactoryConnection(configuration.getMongoDBConnection());
        var client = mongoDBManagerConn.getClient();
        return client;
    }


    @Provides
    @Singleton
    public MongoDatabase mongoDatabase(MongoClient mongoClient, UserProfileApiConfiguration configuration) {
        final var database = mongoClient.getDatabase(configuration.getMongoDBConnection().database());
        return database;
    }

    @Provides
    @Singleton
    public MongoDBManaged getMongoClient(MongoClient mongoClient) {
        return new MongoDBManaged(mongoClient);
    }



}
