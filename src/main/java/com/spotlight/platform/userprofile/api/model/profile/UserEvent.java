package com.spotlight.platform.userprofile.api.model.profile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserId;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;



public class UserEvent {
    @Valid @NotNull private UserId userId;

    @NotNull private EventType type;

    @Valid @NotNull private Map<@Valid UserProfilePropertyName, @Valid UserProfilePropertyValue> properties;


    @JsonCreator
    public UserEvent(@JsonProperty("userId") UserId userId,
                     @JsonProperty("type") EventType type,
                     @JsonProperty("properties") Map<UserProfilePropertyName, UserProfilePropertyValue> properties
                 ) {
        this.userId = userId;
        this.type = type;
        this.properties= properties;
    }

    @JsonProperty
    public UserId getUserId() {
        return userId;
    }


    @JsonProperty
    public EventType getType() {
        return type;
    }


    @JsonProperty
    public Map<UserProfilePropertyName, UserProfilePropertyValue> getProperties() {
        return properties;
    }


}
