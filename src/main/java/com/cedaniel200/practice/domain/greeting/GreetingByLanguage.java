package com.cedaniel200.practice.domain.greeting;

public class GreetingByLanguage {

    private GreetingByLanguage() {
    }

    public static String getGreeting(String language){
        String greeting = "unsupported language";
        switch(language.toLowerCase()){
            case "es":
                greeting = "Hola";
                break;
            case "en":
                greeting = "Hello";
                break;
            case "pt":
                greeting = "Ola";
                break;
        }
        return greeting;
    }
}
