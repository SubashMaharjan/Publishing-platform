package com.spotlight.platform.userprofile.api.model.profile.primitives;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Set;

public enum EventType {
    REPLACE,
    INCREMENT,
    COLLECT;


    public static final Set<EventType> eventTypes= Set.of(EventType.values());

    @JsonCreator
    public static EventType forValues(String value) {
        for (var type : eventTypes) {
            if (type.name()
                    .equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new  IllegalArgumentException("Please provide valid type. Currently SupportType are %s".formatted(eventTypes.toString()));
    }



}
