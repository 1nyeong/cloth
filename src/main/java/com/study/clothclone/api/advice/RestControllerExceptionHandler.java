package com.study.clothclone.api.advice;

import com.study.clothclone.dto.CMRespDto;
import com.study.clothclone.exception.CustomInternalServerErrorException;
import com.study.clothclone.exception.CustomValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> validationErrorException(CustomValidationException e) {

        return ResponseEntity.badRequest().body(new CMRespDto<>(e.getMessage(), e.getErrorMap()));
    }

    @ExceptionHandler(CustomInternalServerErrorException.class)
    public ResponseEntity<?> internalServerErrorException(CustomInternalServerErrorException e) {

        return ResponseEntity.internalServerError().body(new CMRespDto<>(e.getMessage(), null));
    }

}