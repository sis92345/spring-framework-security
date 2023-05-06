package com.cos.security.base.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Target( ElementType.TYPE)
@Retention( RetentionPolicy.RUNTIME)
@Documented
public @interface SecurityFilterChain {
}
