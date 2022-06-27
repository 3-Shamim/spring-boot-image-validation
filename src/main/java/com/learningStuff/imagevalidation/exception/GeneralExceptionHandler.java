package com.learningStuff.imagevalidation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim Molla
 * Email: shamim.molla@vivasoftltd.com
 */

@Slf4j
@ControllerAdvice
public class GeneralExceptionHandler {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @ExceptionHandler(value = {MaxUploadSizeExceededException.class})
    public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException ex) {

        log.error(ex.getMessage(), ex);

        Map<String, Object> apiError = new HashMap<>();
        apiError.put("message", String.format("Your asset's weight is above %s, please upload an asset under that limit.", maxFileSize));

        return new ResponseEntity<>(apiError, HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {

        log.error(ex.getMessage(), ex);

        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        String message = errors.size() > 0 ? errors.get(0) : "Something went wrong. Requested variable not valid";

        Map<String, Object> apiError = new HashMap<>();
        apiError.put("message", message);
        apiError.put("errors", errors);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

    }

}
