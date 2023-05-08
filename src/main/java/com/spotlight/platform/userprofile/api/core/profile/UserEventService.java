package com.spotlight.platform.userprofile.api.core.profile;

import com.spotlight.platform.userprofile.api.model.profile.UserEvent;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;

import javax.inject.Inject;

public class UserEventService {

    private final UserEventHandlerFactory userEventHandlerFactory;


    @Inject
    public UserEventService(UserEventHandlerFactory userEventHandlerFactory) {
        this.userEventHandlerFactory = userEventHandlerFactory;
    }

    public UserProfile handleEvent(UserEvent userEvent){
        var handler=userEventHandlerFactory.getHandler(userEvent.getType());
        return handler.handle(userEvent);
    }
}
