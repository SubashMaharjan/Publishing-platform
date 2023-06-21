package com.spotlight.platform.userprofile.api.web.modules;


import com.spotlight.platform.userprofile.api.model.configuration.UserProfileApiConfiguration;
import ru.vyarus.dropwizard.guice.module.support.DropwizardAwareModule;

public class UserProfileApiModule extends DropwizardAwareModule<UserProfileApiConfiguration> {
    @Override
    public void configure() {
        install(new JsonModule());
//        install(new ProfileModule());
        install(new MongoModule());
    }
}
