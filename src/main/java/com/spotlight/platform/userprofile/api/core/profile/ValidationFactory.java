package com.spotlight.platform.userprofile.api.core.profile;

import com.spotlight.platform.userprofile.api.core.profile.validators.IValidator;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;

import javax.inject.Inject;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ValidationFactory {

    private final Map<EventType, IValidator> validators;


    @Inject
    public ValidationFactory(Set<IValidator> validators) {
        this.validators = validators.stream()
                                  .collect(
                                          Collectors.toMap(e->e.supports(),
                                                           Function.identity()
                                          )
                                  );
    }

    public IValidator getValidator(EventType eventType) {
        return validators.get(eventType);
    }
}
