package com.spotlight.platform.userprofile.api.core.profile.validators;

import com.spotlight.platform.userprofile.api.core.exceptions.ValidationException;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Collection;

public class IncrementValidator implements IValidator{

    @Override
    public void validate(Collection<UserProfilePropertyValue> values) {
        if (values.stream()
                  .map(e -> String.valueOf(e.getValue()))
                  .anyMatch(data -> !NumberUtils.isDigits(data))) {

            throw new ValidationException("Increment event type only support number dataType");
        }
    }

    @Override
    public EventType supports() {
        return EventType.INCREMENT;
    }
}
