package com.learningStuff.imagevalidation.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim Molla
 * Email: shamim.molla@vivasoftltd.com
 */

@Target(value = {FIELD, LOCAL_VARIABLE, PARAMETER, TYPE_USE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidImageImpl.class})
public @interface ValidImage {

    String message() default "Invalid image";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
