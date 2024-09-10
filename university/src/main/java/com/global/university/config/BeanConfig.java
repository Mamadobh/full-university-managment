package com.global.university.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BeanConfig {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource sourse = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:8888","http://localhost:4200"));
        config.setAllowedHeaders(
                Arrays.asList(
                        ORIGIN,
                        AUTHORIZATION,
                        CONTENT_TYPE,
                        ACCEPT,
                        ACCESS_CONTROL_ALLOW_ORIGIN,
                        ACCESS_CONTROL_ALLOW_HEADERS


                ));
        config.setAllowedMethods(Arrays.asList(
                "GET",
                "POST",
                "DELETE",
                "PUT",
                "PATCH",
                "OPTIONS"
        ));
        sourse.registerCorsConfiguration("/**", config);
        return new CorsFilter(sourse);

    }
    @Bean
    public AuditorAwareImpl auditorAware() {
        return new AuditorAwareImpl();
    }
}
