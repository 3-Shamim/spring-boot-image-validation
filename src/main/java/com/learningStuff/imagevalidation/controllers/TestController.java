package com.learningStuff.imagevalidation.controllers;

import com.learningStuff.imagevalidation.validators.ValidImage;
import com.learningStuff.imagevalidation.validators.ValidMultipartFileSize;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim Molla
 * Email: shamim.molla@vivasoftltd.com
 */

@Validated
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @PostMapping(value = "/image")
    public ResponseEntity<?> test(@RequestPart(value = "file") @ValidImage @ValidMultipartFileSize(value = 5) MultipartFile file) {

        return ResponseEntity.ok("Ok");
    }

}
