package com.spotlight.platform.userprofile.api.core.profile;

import com.spotlight.platform.userprofile.api.core.profile.handlers.IEventHandler;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserEventHandlerFactory {

    private final Map<EventType, IEventHandler> eventHandlers;

    @Inject
    public UserEventHandlerFactory(Set<IEventHandler> handlers) {
        this.eventHandlers = handlers.stream()
                                     .collect(
                                             Collectors.toMap(e->e.supports(),
                                                              Function.identity()
                                             )
                                     );
    }


    public IEventHandler getHandler(EventType eventType) {
        if (!eventHandlers.containsKey(eventType)) {
            throw new IllegalArgumentException("Cannot Handle this eventType");
        }
        return eventHandlers.get(eventType);
    }
}
