package com.spotlight.platform.userprofile.api.core.profile;

import com.spotlight.platform.userprofile.api.core.exceptions.ValidationException;
import com.spotlight.platform.userprofile.api.core.profile.handlers.IEventHandler;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserEventHandlerFactoryTest {


    private final IEventHandler eventHandler= mock(IEventHandler.class);
    private  UserEventHandlerFactory userEventHandlerFactory;

    @Test
    public void getHandler_NoHandler(){
        when(eventHandler.supports()).thenReturn(EventType.COLLECT);
        userEventHandlerFactory = new UserEventHandlerFactory(Set.of(eventHandler));
        Assertions.assertThatThrownBy(()->userEventHandlerFactory.getHandler(EventType.REPLACE))
                .isExactlyInstanceOf(ValidationException.class);
    }

    @Test
    public void getHandler_HasHandler(){
        when(eventHandler.supports()).thenReturn(EventType.COLLECT);
        userEventHandlerFactory = new UserEventHandlerFactory(Set.of(eventHandler));
        var handler= userEventHandlerFactory.getHandler(EventType.COLLECT);
        org.junit.jupiter.api.Assertions.assertNotNull(handler);
    }


}