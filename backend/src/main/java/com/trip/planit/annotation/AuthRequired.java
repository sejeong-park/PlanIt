package com.trip.planit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})

// Annotation이 얼마나 유지 될지 -> Runtime동안만 유지된다.
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthRequired {

}
