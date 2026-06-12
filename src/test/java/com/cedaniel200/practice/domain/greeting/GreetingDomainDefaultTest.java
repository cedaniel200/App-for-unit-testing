package com.cedaniel200.practice.domain.greeting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingDomainDefaultTest {

    private GreetingDomain greetingDomain;

    @BeforeEach
    void setup() {
        greetingDomain = new GreetingDomainDefault();
    }

    @Test
    void greetSpanish_shouldReturnLocalizedGreeting() {
        String expectedGreeting = "Hola";
        try (MockedStatic<GreetingByLanguage> greetingByLanguage = Mockito.mockStatic(GreetingByLanguage.class)) {
            greetingByLanguage.when(() -> GreetingByLanguage.getGreeting("es"))
                    .thenReturn(expectedGreeting);

            String greeting = greetingDomain.greet("es");

            assertEquals(expectedGreeting, greeting);
        }
    }

    @Test
    void greet_unsupportedLang_shouldReturnDefaultMessage() {
        String expectedGreeting = "unsupported language";
        try (MockedStatic<GreetingByLanguage> greetingByLanguage = Mockito.mockStatic(GreetingByLanguage.class)) {
            greetingByLanguage.when(() -> GreetingByLanguage.getGreeting("it"))
                    .thenReturn(expectedGreeting);

            String greeting = greetingDomain.greet("it");

            assertEquals(expectedGreeting, greeting);
        }
    }

}
