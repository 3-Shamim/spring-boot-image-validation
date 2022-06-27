package com.learningStuff.imagevalidation.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim Molla
 * Email: shamim.molla@vivasoftltd.com
 */

@Target(value = {FIELD, LOCAL_VARIABLE, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidMultipartFileSizeImpl.class)
public @interface ValidMultipartFileSize {

    long value() default 2;

    String message() default "File is too large";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}