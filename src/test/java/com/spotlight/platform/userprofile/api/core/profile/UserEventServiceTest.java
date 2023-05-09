package com.spotlight.platform.userprofile.api.core.profile;


import com.spotlight.platform.userprofile.api.core.profile.handlers.IEventHandler;
import com.spotlight.platform.userprofile.api.core.profile.validators.IValidator;
import com.spotlight.platform.userprofile.api.model.profile.UserEvent;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserEventFixtures;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfileFixtures;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserEventServiceTest {

    private final UserEventHandlerFactory userEventHandlerFactory = mock(UserEventHandlerFactory.class);
    private final ValidationFactory validationFactory = mock(ValidationFactory.class);
    private final IValidator validator = mock(IValidator.class);
    private final IEventHandler eventHandler = mock(IEventHandler.class);
    private final UserEventService userProfileService = new UserEventService(userEventHandlerFactory, validationFactory);


    @Test
    public void handle_haveValidator(){
        when(userEventHandlerFactory.getHandler(any(EventType.class))).thenReturn(eventHandler);
        when(validationFactory.getValidator(any(EventType.class))).thenReturn(validator);
        when(eventHandler.handle(any(UserEvent.class))).thenReturn(UserProfileFixtures.USER_PROFILE);

        userProfileService.handleEvent(UserEventFixtures.INCREMENT);

        verify(eventHandler, times(1)).handle(any(UserEvent.class));
        verify(validator, times(1)).validate(any(Collection.class));

    }


    @Test
    public void handle_NoValidator(){
        when(userEventHandlerFactory.getHandler(any(EventType.class))).thenReturn(eventHandler);
        when(validationFactory.getValidator(any(EventType.class))).thenReturn(null);
        when(eventHandler.handle(any(UserEvent.class))).thenReturn(UserProfileFixtures.USER_PROFILE);

        userProfileService.handleEvent(UserEventFixtures.INCREMENT);

        verify(eventHandler, times(1)).handle(any(UserEvent.class));
        verify(validator, times(0)).validate(any(Collection.class));

    }


    @Test
    public void handles_multipleEvent(){
        when(userEventHandlerFactory.getHandler(any(EventType.class))).thenReturn(eventHandler);
        when(validationFactory.getValidator(EventType.COLLECT)).thenReturn(validator);
        when(validationFactory.getValidator(EventType.REPLACE)).thenReturn(null);
        when(eventHandler.handle(any(UserEvent.class))).thenReturn(UserProfileFixtures.USER_PROFILE);


        userProfileService.handleEvents(List.of(UserEventFixtures.COLLECT, UserEventFixtures.REPLACE));

        verify(eventHandler, times(2)).handle(any(UserEvent.class));
        verify(validator, times(1)).validate(any(Collection.class));
    }

}