package com.evan.blog.interceptor;

import com.evan.blog.model.enums.UserLevel;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLevel {
    UserLevel[] value() default {};
    UserLevel[] roles() default {};
}
