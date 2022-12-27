package com.itt.reporting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * Annotation used for linking TCs to tests.
 * <code>@Tracking(TC = 0000)</code>
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tracking {
    String TC();
    String similarTestID() default "";
}