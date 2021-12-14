package com.kgp.trips.user;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = UserConfiguration.class)
@EntityScan(basePackageClasses = UserConfiguration.class)
@EnableJpaRepositories(basePackageClasses = UserConfiguration.class)
public class UserConfiguration {


}
