package com.cedaniel200.practice.console;

import com.cedaniel200.practice.domain.greeting.GreetingDomain;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class GreetingCommands {

    private GreetingDomain greetingDomain;

    public GreetingCommands(GreetingDomain greetingDomain) {
        this.greetingDomain = greetingDomain;
    }

    @ShellMethod(value = "Say hello according to the language [es-en-pt]")
    public String greet(String language){
        return greetingDomain.greet(language);
    }
}
