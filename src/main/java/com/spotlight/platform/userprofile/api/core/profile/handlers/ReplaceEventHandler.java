package com.spotlight.platform.userprofile.api.core.profile.handlers;

import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDao;
import com.spotlight.platform.userprofile.api.model.profile.UserEvent;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

import javax.inject.Inject;
import java.util.Map;

public class ReplaceEventHandler extends AbstractEventHandler implements IEventHandler {


    @Inject

    public ReplaceEventHandler(UserProfileDao userProfileDao) {
        super(userProfileDao);
    }

    @Override
    protected Map.Entry<UserProfilePropertyName, UserProfilePropertyValue> processValue(
            Map.Entry<UserProfilePropertyName, UserProfilePropertyValue> data,
            UserProfilePropertyValue existingValue) {
        return data;
    }

    @Override
    public EventType supports() {
        return EventType.REPLACE;
    }
}
