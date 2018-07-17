package com.cedaniel200.practice.config;

import com.cedaniel200.practice.domain.calculator.Calculator;
import com.cedaniel200.practice.domain.calculator.CalculatorDefault;
import com.cedaniel200.practice.domain.user.UserDomain;
import com.cedaniel200.practice.domain.user.UserDomainDefault;
import com.cedaniel200.practice.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public Calculator providesCalculatorDefaultInstance(){
        return new CalculatorDefault();
    }

    @Bean
    public UserDomain providesUserDomainDefaultInstance(UserRepository repository){
        return new UserDomainDefault(repository);
    }

}
