package com.embosoft.PicPay_Simplificado.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFoundException(NotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("O recurso informado não foi encontrado");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> badRequestException(BadRequestException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Solicitação incorreta");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<StandardError> unauthorizedException(BadRequestException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setError("Não autorizado");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<StandardError> serviceUnavailableException(BadRequestException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        error.setError("Solicitação incorreta");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }
}
