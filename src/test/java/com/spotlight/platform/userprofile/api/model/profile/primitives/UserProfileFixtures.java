package com.spotlight.platform.userprofile.api.model.profile.primitives;

import com.spotlight.platform.helpers.FixtureHelpers;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class UserProfileFixtures {
    public static final UserId USER_ID = UserId.valueOf("existing-user-id");
    public static final UserId NON_EXISTING_USER_ID = UserId.valueOf("non-existing-user-id");
    public static final UserId INVALID_USER_ID = UserId.valueOf("invalid-user-id-%");

    public static final Instant LAST_UPDATE_TIMESTAMP = Instant.parse("2021-06-01T09:16:36.123Z");

    public static final UserProfile USER_PROFILE = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
                                                                   Map.of(UserProfilePropertyName.valueOf("property1"),
                                                                          UserProfilePropertyValue.valueOf(
                                                                                  "property1Value")));

    public static final String SERIALIZED_USER_PROFILE = FixtureHelpers.fixture(
            "/fixtures/model/profile/userProfile.json");

    public static final UserProfile USER_PROFILE_2 = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
                                                                     Map.of(UserProfilePropertyName.valueOf("property1"),
                                                                            UserProfilePropertyValue.valueOf(
                                                                                    List.of(1, 2, 3))));


    public static final UserProfile USER_PROFILE_3 = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
                                                                     Map.of(UserProfilePropertyName.valueOf("property2"),
                                                                            UserProfilePropertyValue.valueOf(
                                                                                    1)));

    public static final UserProfile USER_PROFILE_REPLACE_RESPONSE = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
                                                                     Map.of(UserProfilePropertyName.valueOf("property1"),
                                                                            UserProfilePropertyValue.valueOf(
                                                                                    5)));
    public static final UserProfile USER_PROFILE_REPLACE_RESPONSE_2 = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
                                                                                      Map.of(UserProfilePropertyName.valueOf(
                                                                                                     "property1"),
                                                                                             UserProfilePropertyValue.valueOf(
                                                                                                     5),
                                                                                             UserProfilePropertyName.valueOf(
                                                                                                     "property2"),
                                                                                             UserProfilePropertyValue.valueOf(
                                                                                                     1)));

    public static final UserProfile USER_PROFILE_INCREMENT_RESPONSE_2 = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
                                                                                      Map.of(UserProfilePropertyName.valueOf(
                                                                                                     "property1"),
                                                                                             UserProfilePropertyValue.valueOf(
                                                                                                     1),
                                                                                             UserProfilePropertyName.valueOf(
                                                                                                     "property2"),
                                                                                             UserProfilePropertyValue.valueOf(
                                                                                                     1)));

    public static final UserProfile USER_PROFILE_INCREMENT_RESPONSE_3 = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
                                                                                      Map.of(UserProfilePropertyName.valueOf(
                                                                                                     "property1"),
                                                                                             UserProfilePropertyValue.valueOf(
                                                                                                     1)));

    public static final UserProfile USER_PROFILE_COLLECT_RESPONSE_2 = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
                                                                                      Map.of(UserProfilePropertyName.valueOf(
                                                                                                     "property1"),
                                                                                             UserProfilePropertyValue.valueOf(
                                                                                                     List.of(5)),
                                                                                             UserProfilePropertyName.valueOf(
                                                                                                     "property2"),
                                                                                             UserProfilePropertyValue.valueOf(
                                                                                                     1)));


    public static final UserProfile USER_PROFILE_INCREMENT = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
                                                                     Map.of(UserProfilePropertyName.valueOf("property1"),
                                                                            UserProfilePropertyValue.valueOf(1)));

    public static final UserProfile USER_PROFILE_INCREMENT_RESPONSE = new UserProfile(USER_ID, Instant.now(),
                                                                     Map.of(UserProfilePropertyName.valueOf("property1"),
                                                                            UserProfilePropertyValue.valueOf(2)));

    public static final UserProfile USER_PROFILE_COLLECT_RESPONSE =
            new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
                            Map.of(UserProfilePropertyName.valueOf(
                                           "property1"),
                                   UserProfilePropertyValue.valueOf(
                                           List.of(1,
                                                   2,
                                                   3,
                                                   5))));
}
