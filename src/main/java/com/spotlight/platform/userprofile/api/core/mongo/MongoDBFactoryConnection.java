package com.spotlight.platform.userprofile.api.core.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBFactoryConnection {

    private final MongoDBConnection mongoDBConnection;

    public MongoDBFactoryConnection(final MongoDBConnection mongoDBConnection) {
        this.mongoDBConnection = mongoDBConnection;
    }


    public MongoClient getClient() {
//        final Credentials configCredentials = mongoDBConnection.credentials();
//
//        final MongoCredential credentials = MongoCredential.createPlainCredential(
//                configCredentials.username(),
//                mongoDBConnection.database(),
//                configCredentials.password());

//        final MongoClient client = MongoClients.create(
//                MongoClientSettings.builder()
//                                   .credential(credentials)
//                                   .applyToClusterSettings(builder -> builder.hosts(getServers())).build()
//        );
        final MongoClient client = MongoClients.create(mongoDBConnection.connectionUri()
        );

        return client;
    }


//    private List<ServerAddress> getServers() {
//        final List<Seed> seeds = mongoDBConnection.seeds();
//        return seeds.stream()
//                    .map(seed -> {
//                        final ServerAddress serverAddress = new ServerAddress(seed.host(), seed.port());
//                        return serverAddress;
//                    })
//                    .collect(Collectors.toList());
//    }
}
