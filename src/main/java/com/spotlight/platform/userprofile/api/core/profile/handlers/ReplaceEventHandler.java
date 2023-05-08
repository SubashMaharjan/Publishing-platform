package com.spotlight.platform.userprofile.api.core.profile.handlers;

import com.spotlight.platform.userprofile.api.model.profile.UserEvent;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;

public class ReplaceEventHandler implements IEventHandler{

    @Override
    public UserProfile handle(UserEvent userEvent) {
        return null;
    }

    @Override
    public EventType supports() {
        return EventType.REPLACE;
    }
}
