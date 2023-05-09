package com.spotlight.platform.userprofile.api.core.profile.handlers;

import com.spotlight.platform.userprofile.api.core.exceptions.ValidationException;
import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDao;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserEventFixtures;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserId;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfileFixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CollectHandlerTest {

    private final UserProfileDao userProfileDaoMock = mock(UserProfileDao.class);
    private final CollectHandler collectHandler = new CollectHandler(userProfileDaoMock);


    @Test
    public void handle_handleCorrectDataExistingData(){
        when(userProfileDaoMock.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE_2));

        var profile = collectHandler.handle(UserEventFixtures.COLLECT);
        Assertions.assertThat(profile.userProfileProperties()).usingRecursiveComparison()
                .isEqualTo(UserProfileFixtures.USER_PROFILE_COLLECT_RESPONSE.userProfileProperties());

    }

    @Test
    public void handle_handleErrorDataType(){
        when(userProfileDaoMock.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE));
        assertThatThrownBy(() -> collectHandler.handle(UserEventFixtures.COLLECT)).isExactlyInstanceOf(
                ValidationException.class).hasMessage("Existing Value is not Type Collection");

    }


    @Test
    public void handle_handleCorrectDataExistingWithDifferent(){
        when(userProfileDaoMock.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE_3));

        var profile = collectHandler.handle(UserEventFixtures.COLLECT);
        Assertions.assertThat(profile.userProfileProperties()).usingRecursiveComparison()
                  .isEqualTo(UserProfileFixtures.USER_PROFILE_COLLECT_RESPONSE_2.userProfileProperties());

    }

    @Test
    public void testSupports(){
        Assertions.assertThat(EventType.COLLECT).isEqualTo(collectHandler.supports());
    }

}