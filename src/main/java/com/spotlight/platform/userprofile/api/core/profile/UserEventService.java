package com.spotlight.platform.userprofile.api.core.profile;

import com.spotlight.platform.userprofile.api.model.profile.UserEvent;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;

import javax.inject.Inject;
import java.util.List;

public class UserEventService {

    private final UserEventHandlerFactory userEventHandlerFactory;

    private final ValidationFactory validationFactory;


    @Inject
    public UserEventService(UserEventHandlerFactory userEventHandlerFactory,
                            ValidationFactory validationFactory) {
        this.userEventHandlerFactory = userEventHandlerFactory;
        this.validationFactory = validationFactory;
    }

    public UserProfile handleEvent(UserEvent userEvent){
        var validator=validationFactory.getValidator(userEvent.getType());
        if (validator != null) {
            validator.validate(userEvent.getProperties()
                                        .values());
        }
        var handler=userEventHandlerFactory.getHandler(userEvent.getType());
        return handler.handle(userEvent);
    }

    public List<UserProfile> handleEvents(List<UserEvent> userEvents) {
        return userEvents.stream()
                         .map(this::handleEvent)
                         .toList();
    }
}
