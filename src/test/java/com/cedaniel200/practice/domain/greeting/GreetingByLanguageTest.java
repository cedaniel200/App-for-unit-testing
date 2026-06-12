package com.cedaniel200.practice.domain.greeting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingByLanguageTest {

    private static Stream<Arguments> greetings() {
        return Stream.of(
                Arguments.of("es", "Hola"),
                Arguments.of("en", "Hello"),
                Arguments.of("pt", "Ola"),
                Arguments.of("it", "unsupported language")
        );
    }

    @ParameterizedTest
    @MethodSource("greetings")
    void getGreeting_shouldReturnCorrectMessageBasedOnLanguage(String language, String expected){
        String actual = GreetingByLanguage.getGreeting(language);
        assertEquals(expected, actual);
    }

}
