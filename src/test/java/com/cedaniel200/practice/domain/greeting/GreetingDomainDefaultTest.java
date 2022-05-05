package com.cedaniel200.practice.domain.greeting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class GreetingDomainDefaultTest {

    private GreetingDomain greetingDomain;

    @Before
    public void setup() {
        greetingDomain = new GreetingDomainDefault();
    }

    //metodo static
    @Test
    public void mustBeSuccessfulIfReturnHola() {
        String expectedGreeting = "Hola";
        try (MockedStatic<GreetingByLanguage> greetingByLanguage = Mockito.mockStatic(GreetingByLanguage.class)) {
            greetingByLanguage.when(() -> GreetingByLanguage.getGreeting("es"))
                    .thenReturn(expectedGreeting);

            String greeting = greetingDomain.greet("es");

            Assert.assertEquals(expectedGreeting, greeting);
        }
    }

    @Test
    public void mustBeSuccessfulIfReturnUnsupportedLanguage() {
        String expectedGreeting = "unsupported language";
        try (MockedStatic<GreetingByLanguage> greetingByLanguage = Mockito.mockStatic(GreetingByLanguage.class)) {
            greetingByLanguage.when(() -> GreetingByLanguage.getGreeting("it"))
                    .thenReturn(expectedGreeting);

            String greeting = greetingDomain.greet("it");

            Assert.assertEquals(expectedGreeting, greeting);
        }
    }

}
