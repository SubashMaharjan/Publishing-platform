package com.spotlight.platform.userprofile.api.core.profile.handlers;

import com.spotlight.platform.userprofile.api.core.exceptions.ValidationException;
import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDao;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;

public class IncrementEventHandler extends AbstractEventHandler implements IEventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(IncrementEventHandler.class);

    @Inject
    public IncrementEventHandler(UserProfileDao userProfileDao) {
        super(userProfileDao);
    }


    protected final Map.Entry<UserProfilePropertyName, UserProfilePropertyValue> processValue(
            Map.Entry<UserProfilePropertyName, UserProfilePropertyValue> data,
            UserProfilePropertyValue existingValue) {
        if (existingValue == null) {
            return data;
        }
        String value = String.valueOf(existingValue.getValue());
        var newValue = NumberUtils.toInt(data.getValue()
                                             .getValue()
                                             .toString());
        if (!NumberUtils.isDigits(value)) {
            throw new ValidationException("Existing Value is a Not a Number");
        }
        var propertyValue = UserProfilePropertyValue.valueOf(newValue + NumberUtils.toInt(value, 0));
        return Map.entry(data.getKey(), propertyValue);
    }


    @Override
    public EventType supports() {
        return EventType.INCREMENT;
    }
}
