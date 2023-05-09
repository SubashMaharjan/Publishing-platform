package com.spotlight.platform.userprofile.api.model.profile.primitives;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTypeTest {

    @Test
    public void forValues_Valid(){
        Assertions.assertThat(EventType.forValues("increment")).isEqualTo(EventType.INCREMENT);
    }

    @Test
    public void forValues_InValid(){
        Assertions.assertThatException().isThrownBy(()->EventType.forValues("increment1"))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

}