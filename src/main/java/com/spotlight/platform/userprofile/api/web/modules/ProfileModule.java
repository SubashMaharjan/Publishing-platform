package com.spotlight.platform.userprofile.api.web.modules;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.inject.AbstractModule;

import com.google.inject.multibindings.Multibinder;
import com.spotlight.platform.userprofile.api.core.profile.UserEventService;
import com.spotlight.platform.userprofile.api.core.profile.UserEventHandlerFactory;
import com.spotlight.platform.userprofile.api.core.profile.UserProfileService;
import com.spotlight.platform.userprofile.api.core.profile.ValidationFactory;
import com.spotlight.platform.userprofile.api.core.profile.handlers.CollectHandler;
import com.spotlight.platform.userprofile.api.core.profile.handlers.IEventHandler;
import com.spotlight.platform.userprofile.api.core.profile.handlers.IncrementEventHandler;
import com.spotlight.platform.userprofile.api.core.profile.handlers.ReplaceEventHandler;
import com.spotlight.platform.userprofile.api.core.profile.persistence.MongoUserProfileDao;
import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDao;
import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDaoInMemory;
import com.spotlight.platform.userprofile.api.core.profile.validators.CollectValidator;
import com.spotlight.platform.userprofile.api.core.profile.validators.IValidator;
import com.spotlight.platform.userprofile.api.core.profile.validators.IncrementValidator;
import com.spotlight.platform.userprofile.api.model.configuration.UserProfileApiConfiguration;
import ru.vyarus.dropwizard.guice.module.support.DropwizardAwareModule;
import ru.vyarus.dropwizard.guice.module.yaml.bind.Config;

import javax.inject.Inject;
import javax.inject.Singleton;

public class ProfileModule extends DropwizardAwareModule<UserProfileApiConfiguration> {

    @Inject
    @Config("databaseType")
    private String databaseType;

    @Override
    protected void configure() {
        addEventHandler();
        addValidators();
        bind(UserProfileDao.class).to(UserProfileDaoInMemory.class).in(Singleton.class);
        bind(UserProfileService.class).in(Singleton.class);
        bind(UserEventHandlerFactory.class).in(Singleton.class);
        bind(ValidationFactory.class).in(Singleton.class);
        bind(UserEventService.class).in(Singleton.class);
    }

    private void addValidators() {
        Multibinder<IValidator> multibinder
                = Multibinder.newSetBinder(binder(), IValidator.class);
        multibinder.addBinding().to(CollectValidator.class).in(Singleton.class);
        multibinder.addBinding().to(IncrementValidator.class).in(Singleton.class);

    }

    private void addEventHandler() {
        Multibinder<IEventHandler> multibinder
                = Multibinder.newSetBinder(binder(), IEventHandler.class);
        multibinder.addBinding().to(CollectHandler.class).in(Singleton.class);
        multibinder.addBinding().to(IncrementEventHandler.class).in(Singleton.class);
        multibinder.addBinding().to(ReplaceEventHandler.class).in(Singleton.class);
    }
}
