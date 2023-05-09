package com.spotlight.platform.userprofile.api.model.profile;

import com.spotlight.platform.userprofile.api.model.profile.primitives.UserEventFixtures;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class UserEventTest {
    @Test
    void serialization_WorksAsExpected() {
        assertThatJson(UserEventFixtures.INCREMENT).isEqualTo(UserEventFixtures.SERIALIZED_USER_EVENT);
    }
}