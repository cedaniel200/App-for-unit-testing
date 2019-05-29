package com.cedaniel200.practice.domain.greeting;

        import com.cedaniel200.practice.repository.greeting.GreetingByLanguage;

public class GreetingDomainDefault implements GreetingDomain {

    @Override
    public String greet(String language) {
        return GreetingByLanguage.getGreeting(language);
    }
}
