package com.spotlight.platform.userprofile.api.core.profile;


import com.spotlight.platform.userprofile.api.core.profile.validators.IValidator;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValidationFactoryTest {
    private final IValidator validator= mock(IValidator.class);
    private  ValidationFactory validationFactory;

    @Test
    public void getHandler_NoHandler(){
        when(validator.supports()).thenReturn(EventType.COLLECT);
        validationFactory = new ValidationFactory(Set.of(validator));
        Assertions.assertTrue(validationFactory.getValidator(EventType.REPLACE)==null);
    }

    @Test
    public void getHandler_HasHandler(){
        when(validator.supports()).thenReturn(EventType.COLLECT);
        validationFactory = new ValidationFactory(Set.of(validator));
        Assertions.assertTrue(validationFactory.getValidator(EventType.COLLECT)!=null);

    }

}