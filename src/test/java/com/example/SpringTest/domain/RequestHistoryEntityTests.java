package com.example.SpringTest.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static  org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Class RequestHistoryEntity")
class RequestHistoryEntityTests {

    @DisplayName("have correct constructor")
    @Test
    void shouldHaveCorrectConstructor() {
        String actualTemperature = "20";
        Long actualUserId = 1L;
        RequestHistoryEntity entity = new RequestHistoryEntity(actualTemperature, actualUserId);

        assertEquals("20", entity.getTemperature());
        assertEquals(1L, entity.getUserId());
    }
}
