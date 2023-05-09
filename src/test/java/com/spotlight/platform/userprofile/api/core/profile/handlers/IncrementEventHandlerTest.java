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

class IncrementEventHandlerTest {

    private final UserProfileDao userProfileDaoMock = mock(UserProfileDao.class);
    private final IEventHandler eventHandler = new IncrementEventHandler(userProfileDaoMock);


    @Test
    public void handle_handleCorrectDataExistingData(){
        when(userProfileDaoMock.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE_INCREMENT));

        var profile = eventHandler.handle(UserEventFixtures.INCREMENT);
        Assertions.assertThat(profile.userProfileProperties()).usingRecursiveComparison()
                  .isEqualTo(UserProfileFixtures.USER_PROFILE_INCREMENT_RESPONSE.userProfileProperties());

    }

    @Test
    public void handle_handleErrorDataType(){
        when(userProfileDaoMock.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE));
        assertThatThrownBy(() -> eventHandler.handle(UserEventFixtures.INCREMENT)).isExactlyInstanceOf(
                ValidationException.class).hasMessage("Existing Value is a Not a Number");

    }


    @Test
    public void handle_handleCorrectDataExistingWithDifferent(){
        when(userProfileDaoMock.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE_3));

        var profile = eventHandler.handle(UserEventFixtures.INCREMENT);
        Assertions.assertThat(profile.userProfileProperties()).usingRecursiveComparison()
                  .isEqualTo(UserProfileFixtures.USER_PROFILE_INCREMENT_RESPONSE_2.userProfileProperties());

    }

    @Test
    public void handle_handleCreateNew(){
        when(userProfileDaoMock.get(any(UserId.class))).thenReturn(Optional.empty());

        var profile = eventHandler.handle(UserEventFixtures.INCREMENT);
        Assertions.assertThat(profile.userProfileProperties()).usingRecursiveComparison()
                  .isEqualTo(UserProfileFixtures.USER_PROFILE_INCREMENT_RESPONSE_3.userProfileProperties());

    }

    @Test
    public void testSupports(){
        Assertions.assertThat(EventType.INCREMENT).isEqualTo(eventHandler.supports());
    }

}