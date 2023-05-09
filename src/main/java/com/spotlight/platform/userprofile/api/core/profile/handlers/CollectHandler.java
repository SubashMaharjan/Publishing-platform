package com.spotlight.platform.userprofile.api.core.profile.handlers;

import com.spotlight.platform.userprofile.api.core.exceptions.ValidationException;
import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDao;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

public class CollectHandler extends AbstractEventHandler implements IEventHandler {

    @Inject
    protected CollectHandler(UserProfileDao userProfileDao) {
        super(userProfileDao);
    }

    @Override
    protected final Map.Entry<UserProfilePropertyName, UserProfilePropertyValue> processValue(
            Map.Entry<UserProfilePropertyName, UserProfilePropertyValue> data,
            UserProfilePropertyValue existingValue) {
        if (existingValue == null) {
            return data;
        }
        var existingData = existingValue.getValue();
        if (!(existingData instanceof Collection)) {
            throw new ValidationException("Existing Value is not Type Collection");
        }
        Collection<?> newData = (Collection<?>) data.getValue()
                                               .getValue();

        var newCollection = Stream.concat(((Collection<?>) existingData).stream(),
                                          newData.stream())
                                  .toList();
        return Map.entry(data.getKey(),UserProfilePropertyValue.valueOf(newCollection));
    }

    @Override
    public EventType supports() {
        return EventType.COLLECT;
    }


}
