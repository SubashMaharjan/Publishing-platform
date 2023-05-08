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

    @Valid @NotNull private Map<UserProfilePropertyName, UserProfilePropertyValue> properties;


    @JsonCreator
    public UserEvent(@JsonProperty("userId") UserId userId,
                     @JsonProperty("type") EventType type,
                     @JsonProperty("properties") Map<UserProfilePropertyName, UserProfilePropertyValue> properties
                 ) {
        this.userId = userId;
        this.type = type;
        this.properties= properties;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Map<UserProfilePropertyName, UserProfilePropertyValue> getProperties() {
        return properties;
    }

    public void setProperties(Map<UserProfilePropertyName, UserProfilePropertyValue> properties) {
        this.properties = properties;
    }
}
