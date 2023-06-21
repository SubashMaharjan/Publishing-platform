package com.spotlight.platform.userprofile.api.model.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotlight.platform.userprofile.api.core.mongo.MongoDBConnection;
import io.dropwizard.core.Configuration;
import ru.vyarus.dropwizard.guice.module.yaml.bind.Config;

public class UserProfileApiConfiguration extends Configuration {
    public static final String APPLICATION_NAME = "User Profile API";


    @JsonProperty("mongoDBConnection")
    private MongoDBConnection mongoDBConnection;

    @JsonProperty("databaseType")
    private String databaseType;

    public MongoDBConnection getMongoDBConnection() {
        return mongoDBConnection;
    }
}
