package com.peixeurbano.pablo.desafio.controller;

import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.peixeurbano.pablo.desafio.dto.ErrorDTO;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdviceExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        final Optional<ConstraintViolation<?>> constraintViolation = ex.getConstraintViolations().stream().findFirst();
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        final ErrorDTO error = new ErrorDTO();
        error.setTimestamp(new Date());
        error.setStatus(httpStatus.value());
        error.setError(httpStatus.getReasonPhrase());
        final String messageKey = constraintViolation.isPresent() ? constraintViolation.get().getMessage() : "unexpected_error";
        error.setMessageKey(messageKey);
        error.setPath(request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleDataIntegrityViolationException(final DataIntegrityViolationException ex, final WebRequest request) {
        LOGGER.error("", ex);
        final ErrorDTO error = new ErrorDTO();
        error.setTimestamp(new Date());
        error.setMessageKey("data_integrity_violation");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
