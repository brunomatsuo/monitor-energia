package com.fiap.challenge.monitorenergia.config;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class ValidatorBean {

    @Bean
    Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
