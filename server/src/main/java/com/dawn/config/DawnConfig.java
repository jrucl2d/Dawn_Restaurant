package com.dawn.config;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.Charset;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class DawnConfig {

    @Bean
    public Storage storage() {
        return StorageOptions.getDefaultInstance().getService();
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}

