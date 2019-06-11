package com.cedaniel200.practice.config;

import com.cedaniel200.practice.domain.calculator.Calculator;
import com.cedaniel200.practice.domain.calculator.CalculatorDefault;
import com.cedaniel200.practice.domain.calculator.operation.*;
import com.cedaniel200.practice.domain.email.EmailDomain;
import com.cedaniel200.practice.domain.email.EmailDomainDefault;
import com.cedaniel200.practice.domain.email.EmailHandler;
import com.cedaniel200.practice.domain.email.EmailHandlerDefault;
import com.cedaniel200.practice.domain.greeting.GreetingDomain;
import com.cedaniel200.practice.domain.greeting.GreetingDomainDefault;
import com.cedaniel200.practice.domain.user.UserDomain;
import com.cedaniel200.practice.domain.user.UserDomainDefault;
import com.cedaniel200.practice.repository.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class DomainConfiguration {

    @Bean
    public Adder providesAdderDefaultInstance(){
        return new AdderDefault();
    }

    @Bean
    public Subtractor providesSubtractorDefaultInstance(){
        return new SubtractorDefault();
    }

    @Bean
    public Multiplier providesMultiplierDefaultInstance(){
        return new MultiplierDefault();
    }

    @Bean
    public Divider providesDividerDefaultInstance(){
        return new DividerDefault();
    }

    @Bean
    public Calculator providesCalculatorDefaultInstance(Adder adder, Subtractor subtractor, Multiplier multiplier, Divider divider){
        return new CalculatorDefault(adder, subtractor, multiplier, divider);
    }

    @Bean
    public UserDomain providesUserDomainDefaultInstance(UserRepository repository){
        return new UserDomainDefault(repository);
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("your email");
        mailSender.setPassword("Mail password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public EmailHandler providesEmailHandlerDefaultInstance(JavaMailSender javaMailSender){
        return new EmailHandlerDefault(javaMailSender);
    }

    @Bean
    public EmailDomain providesEmailDomainDefaultInstance(EmailHandler emailHandler){
        return new EmailDomainDefault(emailHandler);
    }

    @Bean
    public GreetingDomain providesGreetingDomainDefaultInstance(){
        return new GreetingDomainDefault();
    }

}
