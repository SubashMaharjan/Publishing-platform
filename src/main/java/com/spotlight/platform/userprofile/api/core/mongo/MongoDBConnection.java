package com.spotlight.platform.userprofile.api.core.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MongoDBConnection( String connectionUri, String database) {
}
