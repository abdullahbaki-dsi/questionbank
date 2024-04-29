package org.dsi.com.questionService.utils;

import jakarta.ws.rs.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.rmi.NoSuchObjectException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        log.error("Exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Unique Value constraint failed");
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
        log.error("Exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Bad Request");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        log.error("Exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Required Parameter Missing");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElement(NoSuchElementException  ex){
        log.error("Exception : no object found");
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Such Resource");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException  ex){
        log.error("Exception : no object found");
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Such Resource");
    }
    @ExceptionHandler(NoSuchObjectException.class)
    public ResponseEntity<?> handlerNoObjectFound(NoSuchObjectException ex){
        log.error("Exception :{}" , ex.getMessage(),ex);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("No Such Resource");
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handlerDataIntegrityViolationException(DataIntegrityViolationException ex){
        log.error("Exception :{}" , ex.getMessage(),ex);
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("unique Constraint failed");
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        log.error("Exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred in the server!!!");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        log.error("Exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred in the server!!!!");
    }
}
