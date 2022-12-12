package com.itt.configurations.test;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({METHOD})
public @interface EnvConfig {

    /** Start up activities that needs to perform before test case execution. */
    String[] startUp() default {};

    /** Clean up activities that needs to perform irrespective of the test case status. */
    String[] cleanUp() default {};

}
