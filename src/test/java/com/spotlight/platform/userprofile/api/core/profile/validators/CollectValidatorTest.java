package com.spotlight.platform.userprofile.api.core.profile.validators;


import com.spotlight.platform.userprofile.api.core.exceptions.ValidationException;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CollectValidatorTest {

    private final IValidator validator = new CollectValidator();


    @Test
    public void validate_whenWrongData(){
        assertThatThrownBy(()->validator.validate(List.of(UserProfilePropertyValue.valueOf(1))))
                .isExactlyInstanceOf(ValidationException.class)
                .hasMessage("Collect event only support Collections DataType");
    }


    @Test
    public void validate_whenCorrect(){
        Assertions.assertDoesNotThrow(()->validator.validate(List.of(UserProfilePropertyValue.valueOf(List.of(1,2)))));
    }

    @Test
    public void support(){
        Assertions.assertEquals(EventType.COLLECT, validator.supports());
    }

}