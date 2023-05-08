package com.spotlight.platform.userprofile.api.core.profile.handlers;

import com.spotlight.platform.userprofile.api.model.profile.UserEvent;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;

public interface IEventHandler {

    UserProfile handle(UserEvent userEvent);

    EventType supports();
}
