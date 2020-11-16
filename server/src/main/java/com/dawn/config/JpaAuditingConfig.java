package com.dawn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@Configuration
public class JpaAuditingConfig {

    @Bean
    public BaseAuditorAware auditorProvider() {
        return new BaseAuditorAware();
    }

    @PostConstruct
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
    }
}
