package com.spotlight.platform.userprofile.api.web.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDao;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserEventFixtures;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserId;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfileFixtures;
import com.spotlight.platform.userprofile.api.web.UserProfileApiApplication;
import com.spotlight.platform.userprofile.api.web.exceptionmappers.ErrorResponse;
import com.spotlight.platform.userprofile.api.web.modules.MongoModule;
import org.assertj.core.api.Assertions;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import ru.vyarus.dropwizard.guice.test.ClientSupport;
import ru.vyarus.dropwizard.guice.test.jupiter.ext.TestDropwizardAppExtension;

import javax.ws.rs.client.Entity;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@Execution(ExecutionMode.SAME_THREAD)
class UserEventResourceTest {

    @RegisterExtension
    static TestDropwizardAppExtension APP = TestDropwizardAppExtension.forApp(UserProfileApiApplication.class)
                                                                      .hooks(builder -> builder.modulesOverride(new AbstractModule() {
                                                                          @Provides
                                                                          @Singleton
                                                                          public UserProfileDao getUserProfileDao() {
                                                                              return mock(UserProfileDao.class);
                                                                          }
                                                                      }).disableModules(MongoModule.class))
                                                                      .randomPorts()
                                                                      .create();

    @BeforeEach
    void beforeEach(UserProfileDao userProfileDao) {
        reset(userProfileDao);
    }


    private static final String USER_ID_PATH_PARAM = "userId";
    private static final String URL = "/user/event".formatted(USER_ID_PATH_PARAM);
    private static final String URL2 = "/user/events".formatted(USER_ID_PATH_PARAM);

    @Test
    void replace_existing(ClientSupport client,
                          UserProfileDao userProfileDao) throws JsonProcessingException {
        when(userProfileDao.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE_2));
        var response = client.targetRest()
                             .path(URL)
                             .request()
                             .post(Entity.json(UserEventFixtures.REPLACE));

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
        var responseBody=response.readEntity(UserProfile.class);
        Assertions.assertThat(responseBody.userProfileProperties()).usingRecursiveComparison()
                .isEqualTo(UserProfileFixtures.USER_PROFILE_REPLACE_RESPONSE.userProfileProperties());
    }

    @Test
    void replace_WithDifferent(ClientSupport client,
                          UserProfileDao userProfileDao) throws JsonProcessingException {
        when(userProfileDao.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE_3));
        var response = client.targetRest()
                             .path(URL)
                             .request()
                             .post(Entity.json(UserEventFixtures.REPLACE));

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
        var responseBody=response.readEntity(UserProfile.class);
        Assertions.assertThat(responseBody.userProfileProperties()).usingRecursiveComparison()
                  .isEqualTo(UserProfileFixtures.USER_PROFILE_REPLACE_RESPONSE_2.userProfileProperties());
    }

    @Test
    void incrementMisMatchExistingData_returns400(ClientSupport client,
                                    UserProfileDao userProfileDao) {
        when(userProfileDao.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE));

         var response = client.targetRest()
                                .path(URL)
                                .request()
                                .post(Entity.json(UserEventFixtures.INCREMENT));

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST_400);
        Assertions.assertThat(response.readEntity(ErrorResponse.class).message())
                .isEqualTo("Existing Value is a Not a Number");
    }

    @Test
    void incrementCorrectData_returns200(ClientSupport client,
                                                  UserProfileDao userProfileDao) {
        when(userProfileDao.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE_INCREMENT));

        var response = client.targetRest()
                             .path(URL)
                             .request()
                             .post(Entity.json(UserEventFixtures.INCREMENT));

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
        var responseBody=response.readEntity(UserProfile.class);
        Assertions.assertThat(responseBody.userProfileProperties()).usingRecursiveComparison()
                  .isEqualTo(UserProfileFixtures.USER_PROFILE_INCREMENT_RESPONSE.userProfileProperties());

    }


    @Test
    void collectMisMatchExistingData_returns400(ClientSupport client,
                                                  UserProfileDao userProfileDao) {
        when(userProfileDao.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE));

        var response = client.targetRest()
                             .path(URL)
                             .request()
                             .post(Entity.json(UserEventFixtures.COLLECT));

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST_400);
        Assertions.assertThat(response.readEntity(ErrorResponse.class).message())
                  .isEqualTo("Existing Value is not Type Collection");
    }

    @Test
    void collectCorrectData_returns200(ClientSupport client,
                                       UserProfileDao userProfileDao) {
        when(userProfileDao.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE_2));

        var response = client.targetRest()
                             .path(URL)
                             .request()
                             .post(Entity.json(UserEventFixtures.COLLECT));

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
        var responseBody = response.readEntity(UserProfile.class);
        Assertions.assertThat(responseBody.userProfileProperties())
                  .usingRecursiveComparison()
                  .isEqualTo(UserProfileFixtures.USER_PROFILE_COLLECT_RESPONSE.userProfileProperties());

    }


    @Test
    void Batch_returns200(ClientSupport client,
                                       UserProfileDao userProfileDao) {
        when(userProfileDao.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE_2));

        var response = client.targetRest()
                             .path(URL2)
                             .request()
                             .post(Entity.json(List.of(UserEventFixtures.COLLECT, UserEventFixtures.REPLACE)));

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);

    }


}


