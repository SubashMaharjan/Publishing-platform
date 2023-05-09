package com.spotlight.platform.userprofile.api.core.profile.validators;

import com.spotlight.platform.userprofile.api.core.exceptions.ValidationException;
import com.spotlight.platform.userprofile.api.model.profile.primitives.EventType;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IncrementValidatorTest {

    private final IValidator validator = new IncrementValidator();


    @Test
    public void validate_whenWrongData(){
        assertThatThrownBy(()->validator.validate(List.of(UserProfilePropertyValue.valueOf(List.of(1)))))
                .isExactlyInstanceOf(ValidationException.class)
                .hasMessage("Increment event type only support number dataType");
    }

    @Test
    public void validate_whenWrongData_String(){
        assertThatThrownBy(()->validator.validate(List.of(UserProfilePropertyValue.valueOf("asdfasdf"))))
                .isExactlyInstanceOf(ValidationException.class)
                .hasMessage("Increment event type only support number dataType");
    }


    @Test
    public void validate_whenCorrect(){
        Assertions.assertDoesNotThrow(()->validator.validate(List.of(UserProfilePropertyValue.valueOf(1))));
    }

    @Test
    public void support(){
        Assertions.assertEquals(EventType.INCREMENT, validator.supports());
    }


}