package com.spotlight.platform.userprofile.api.core.profile.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoDatabase;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserId;
import org.bson.UuidRepresentation;
import org.mongojack.JacksonMongoCollection;

import javax.inject.Inject;
import java.util.Optional;

public class MongoUserProfileDao implements UserProfileDao {

    private final JacksonMongoCollection<UserProfile> userProfileCollections;

    @Inject
    public MongoUserProfileDao(MongoDatabase mongoDatabase,
                               ObjectMapper objectMapper) {


        this.userProfileCollections = JacksonMongoCollection
                .builder()
                .withObjectMapper(objectMapper)
                .build(mongoDatabase, "user_profile",
                       UserProfile.class,
                       UuidRepresentation.UNSPECIFIED);


    }

    @Override
    public Optional<UserProfile> get(UserId userId) {
        return Optional.ofNullable(userProfileCollections.findOneById(userId));
    }

    @Override
    public void put(UserProfile userProfile) {
        if (this.get(userProfile.userId())
                .isPresent()) {
            userProfileCollections.replaceOneById(userProfile.userId(), userProfile);
        } else {
            userProfileCollections.insert(userProfile);
        }
    }
}
