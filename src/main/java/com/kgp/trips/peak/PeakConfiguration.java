package com.kgp.trips.peak;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = PeakConfiguration.class)
@EntityScan(basePackageClasses = PeakConfiguration.class)
@EnableJpaRepositories(basePackageClasses = PeakConfiguration.class)
public class PeakConfiguration {
}
