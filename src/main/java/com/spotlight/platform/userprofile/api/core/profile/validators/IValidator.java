package com.spotlight.platform.userprofile.api.core.profile.validators;

import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

import java.util.Collection;

public interface IValidator {
     void validate(Collection<UserProfilePropertyValue> values);

     EventType supports();

}

