
package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void shouldReturnExpectedMessage() {
        App app = new App();

        String result = app.getMessage();

        assertEquals("Hello from Java and Jenkins!", result);
    }
}
