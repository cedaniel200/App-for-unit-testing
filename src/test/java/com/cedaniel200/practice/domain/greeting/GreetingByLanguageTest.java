package com.cedaniel200.practice.domain.greeting;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class GreetingByLanguageTest {

    private Object[] greetings() {
        return new Object[] {
                new Object[] { "es", "Hola"},
                new Object[] { "en", "Hello"},
                new Object[] { "pt", "Ola"},
                new Object[] { "it", "unsupported language"},
        };
    }

    @Test
    @Parameters(method = "greetings")
    public void mustBeSuccessfulIfReturnGreetingCorrectByLanguage(String language, String expected){
        String actual = GreetingByLanguage.getGreeting(language);

        Assert.assertEquals(expected, actual);
    }

}
