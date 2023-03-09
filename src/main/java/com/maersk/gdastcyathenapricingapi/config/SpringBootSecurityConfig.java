package com.maersk.gdastcyathenapricingapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.JwtIssuerValidator;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtClaimValidator;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Configuration
public class SpringBootSecurityConfig {

    @Value("${resource-server.jwt.issuer-uri}")
    private String issuerUri;

    @Value("${resource-server.jwt.jwk-set-uri}")
    private String setURI;

    @Value("${resource-server.audience}")
    private String audiences;

    @Bean
    ReactiveJwtDecoder jwtDecoder() {
        NimbusReactiveJwtDecoder decoder = NimbusReactiveJwtDecoder.withJwkSetUri(setURI).build();
        decoder.setJwtValidator(jwtValidator());
        return decoder;
    }


    private OAuth2TokenValidator<Jwt> jwtValidator() {
        List<String> listOfAudiences = Arrays.asList(audiences.split("\\s*,\\s*"));
        List<OAuth2TokenValidator<Jwt>> validators = new ArrayList<>();
        validators.add(new JwtIssuerValidator(issuerUri));
        validators.add(new JwtClaimValidator<>(JwtClaimNames.AUD, audiencePredicate(listOfAudiences)));
        validators.add(new JwtTimestampValidator());
        return new DelegatingOAuth2TokenValidator<>(validators);
    }

    Predicate<Object> audiencePredicate(List<String> audiences) {
        return aud -> {
            if (aud == null) {
                return false;
            } else if (aud instanceof String) {
                return audiences.contains(aud);
            } else if (aud instanceof List) {
                return !Collections.disjoint((List<?>) aud, audiences);
            } else {
                return false;
            }
        };
    }
}