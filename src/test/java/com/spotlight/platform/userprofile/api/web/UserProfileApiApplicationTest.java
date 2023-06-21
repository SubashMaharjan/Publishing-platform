package com.spotlight.platform.userprofile.api.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.spotlight.platform.userprofile.api.core.json.JsonMapper;
import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDao;
import com.spotlight.platform.userprofile.api.web.exceptionmappers.EntityNotFoundExceptionMapper;

import com.spotlight.platform.userprofile.api.web.modules.MongoModule;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import io.dropwizard.core.setup.Environment;
import org.junit.jupiter.api.extension.RegisterExtension;
import ru.vyarus.dropwizard.guice.test.jupiter.TestDropwizardApp;
import ru.vyarus.dropwizard.guice.test.jupiter.ext.TestDropwizardAppExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class UserProfileApiApplicationTest {

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

    @Test
    void objectMapper_IsOfSameInstance(ObjectMapper objectMapper) {
        assertThat(objectMapper).isEqualTo(JsonMapper.getInstance());
    }

    @Test
    void exceptionMappers_AreRegistered(Environment environment) {
        assertThat(getRegisteredSingletonClasses(environment)).containsOnlyOnce(EntityNotFoundExceptionMapper.class);
    }

    @Test
    void dummyHealthCheck_IsRegistered(Environment environment) {
        assertThat(environment.healthChecks().getNames()).contains("preventing-startup-warning-healthcheck");
    }

    protected Set<Class<?>> getRegisteredSingletonClasses(Environment environment) {
        return environment.jersey().getResourceConfig().getSingletons().stream().map(Object::getClass).collect(Collectors.toSet());
    }
}