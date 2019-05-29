package com.cedaniel200.practice.domain.greeting;

import com.cedaniel200.practice.repository.greeting.GreetingByLanguage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GreetingByLanguage.class)
public class GreetingDomainDefaultTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private File greetingTxt;
    private GreetingDomain greetingDomain;

    @Before
    public void setup() throws IOException {
        greetingTxt = temporaryFolder.newFile("greetings.txt");
        greetingDomain = new GreetingDomainDefault();
    }

    //metodo static
    @Test
    public void mustBeSuccessfulIfReturnHola() throws Exception {
        PowerMockito.mockStatic(GreetingByLanguage.class);
        String expectedGreeting = "Hola";
        when(GreetingByLanguage.getGreeting(anyString())).thenReturn(expectedGreeting);

        String greeting = greetingDomain.greet("es");

        Assert.assertEquals(expectedGreeting, greeting);
    }

    @Test
    public void mustBeSuccessfulIfReturnUnsupportedLanguage() throws Exception {
        PowerMockito.mockStatic(GreetingByLanguage.class);
        String expectedGreeting = "unsupported language";
        BDDMockito.given(GreetingByLanguage.getGreeting("it")).willReturn(expectedGreeting);

        String greeting = greetingDomain.greet("it");

        Assert.assertEquals(expectedGreeting, greeting);
    }

}
