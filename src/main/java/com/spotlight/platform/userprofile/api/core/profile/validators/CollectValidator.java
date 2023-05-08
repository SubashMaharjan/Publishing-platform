package com.spotlight.platform.userprofile.api.core.profile.validators;

import com.spotlight.platform.userprofile.api.core.exceptions.ValidationException;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

import java.util.Collection;

public class CollectValidator implements IValidator{

    @Override
    public void validate(Collection<UserProfilePropertyValue> values) {
        if (values.stream()
                  .map(UserProfilePropertyValue::getValue)
                  .anyMatch(e -> !(e instanceof Collection))) {
            throw new ValidationException("Collect event only support Collections DataType");
        }
    }

    @Override
    public EventType supports() {
        return EventType.COLLECT;
    }
}
