package com.spotlight.platform.userprofile.api.model.profile.primitives;

import com.spotlight.platform.helpers.FixtureHelpers;
import com.spotlight.platform.userprofile.api.model.profile.UserEvent;

import java.util.List;
import java.util.Map;

public class UserEventFixtures {

    public static final UserEvent INCREMENT = new UserEvent(
            UserProfileFixtures.USER_ID, EventType.INCREMENT,
            Map.of(UserProfilePropertyName.valueOf("property1"),
                   UserProfilePropertyValue.valueOf(1)));

    public static final UserEvent REPLACE = new UserEvent(
            UserProfileFixtures.USER_ID, EventType.REPLACE,
            Map.of(UserProfilePropertyName.valueOf("property1"),
                   UserProfilePropertyValue.valueOf(5)));
    public static final UserEvent COLLECT = new UserEvent(
            UserProfileFixtures.USER_ID, EventType.COLLECT,
            Map.of(UserProfilePropertyName.valueOf("property1"),
                   UserProfilePropertyValue.valueOf(List.of(5))));

    public static final String SERIALIZED_USER_EVENT= FixtureHelpers.fixture("/fixtures/model/event/userEvent.json");

}
