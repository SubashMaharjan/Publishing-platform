package com.spotlight.platform.userprofile.api.core.profile.handlers;

import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDao;
import com.spotlight.platform.userprofile.api.model.profile.UserEvent;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

public class IncrementEventHandler implements IEventHandler {

    private final UserProfileDao userProfileDao;
    private static final Logger LOG = LoggerFactory.getLogger(IncrementEventHandler.class);

    @Inject
    public IncrementEventHandler(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    @Override
    public UserProfile handle(UserEvent userEvent) {
        LOG.info("Handling userEvent of [userId: {}]", userEvent.getUserId());
        var userProfile = userProfileDao.get(userEvent.getUserId())
                                        .map(profile -> processEvent(profile, userEvent))
                                        .orElseGet(() -> new UserProfile(userEvent.getUserId(),
                                                                         Instant.now(),
                                                                         userEvent.getProperties()));
        userProfileDao.put(userProfile);
        return userProfile;
    }

    private UserProfile processEvent(UserProfile userProfile,
                                     UserEvent userEvent) {
        var properties = userEvent.getProperties()
                                  .entrySet()
                                  .stream()
                                  .map(data -> processValue(data,
                                                            userProfile.userProfileProperties()
                                                                       .get(data.getKey())))
                                  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var existing=userProfile.userProfileProperties()
                   .entrySet()
                   .stream()
                   .filter(data -> !properties.containsKey(data.getKey()))
                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        properties.putAll(existing);
        return new UserProfile(userProfile.userId(), Instant.now(), properties);
    }

    private Map.Entry<UserProfilePropertyName, UserProfilePropertyValue> processValue(Map.Entry<UserProfilePropertyName, UserProfilePropertyValue> data,
                                                                                      UserProfilePropertyValue existingValue) {
        if (existingValue == null) {
            return data;
        }
        String value = String.valueOf(existingValue.getValue());
        var newValue = NumberUtils.toInt(data.getValue()
                                             .getValue()
                                             .toString());
        if (!NumberUtils.isDigits(value)) {
            throw new IllegalArgumentException("Existing Value is a Not a Number");
        }
        var propertyValue = UserProfilePropertyValue.valueOf(newValue + NumberUtils.toInt(value, 0));
        data.setValue(propertyValue);
        return data;
    }


    @Override
    public EventType supports() {
        return EventType.INCREMENT;
    }
}
