package com.cedaniel200.practice.domain.greeting;

public class GreetingDomainDefault implements GreetingDomain {

    @Override
    public String greet(String language) {
        return GreetingByLanguage.getGreeting(language);
    }
}
