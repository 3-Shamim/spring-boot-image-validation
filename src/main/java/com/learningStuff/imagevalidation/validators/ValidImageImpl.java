package com.learningStuff.imagevalidation.validators;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim Molla
 * Email: shamim.molla@vivasoftltd.com
 */

public class ValidImageImpl implements ConstraintValidator<ValidImage, MultipartFile> {

    @Override
    public void initialize(ValidImage constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

        if (file == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Image file is missing.").addConstraintViolation();
            return false;
        }

        String contentType = file.getContentType();

        return contentType != null && isSupportedContentType(contentType);
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.startsWith("image");
    }
}

