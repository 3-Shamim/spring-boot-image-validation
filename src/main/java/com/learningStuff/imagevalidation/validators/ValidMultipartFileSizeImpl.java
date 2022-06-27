package com.learningStuff.imagevalidation.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim Molla
 * Email: shamim.molla@vivasoftltd.com
 */

@Slf4j
public class ValidMultipartFileSizeImpl implements ConstraintValidator<ValidMultipartFileSize, MultipartFile> {

    private long sizeLimit = 2;

    @Override
    public void initialize(ValidMultipartFileSize constraintAnnotation) {
        sizeLimit = constraintAnnotation.value();

    }

    @Override
    public boolean isValid(MultipartFile file, final ConstraintValidatorContext context) {

        log.info("{}",sizeLimit);
        log.info("{}",sizeLimit * 1024);
        log.info("{}",file.getSize() / 1024.0);
        log.info("{}",file.getSize() / (Math.pow(1024.0, 2)));

        context.buildConstraintViolationWithTemplate(String.format("File must not be more than %dMB", sizeLimit))
                .addConstraintViolation();

        double fileSizeInMB = file.getSize() / (Math.pow(1024.0, 2));

        return fileSizeInMB < sizeLimit;
    }

}