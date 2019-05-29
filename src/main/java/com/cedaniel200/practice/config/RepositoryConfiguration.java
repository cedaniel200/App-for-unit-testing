package com.cedaniel200.practice.config;

import com.cedaniel200.practice.repository.user.UserRepository;
import com.cedaniel200.practice.repository.user.UserRepositoryDefault;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public UserRepository providesUserRepositoryDefaultInstance(Retrofit retrofit){
        return new UserRepositoryDefault(retrofit);
    }

}
