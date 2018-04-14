package br.com.carrinho.handler;

import br.com.carrinho.handler.domain.ResponseError;
import br.com.carrinho.service.exception.NullParameterException;
import br.com.carrinho.service.exception.RecurseNotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class CarrinhoApiHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<ResponseError> erros = Arrays.asList(new ResponseError(
                this.messageSource.getMessage("information.invalid", null, LocaleContextHolder.getLocale()),
                ex.getCause().toString()));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<ResponseError> erros = this.createErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class, NullParameterException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        return throwException(ex, request, "operation.failed", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ RecurseNotFoundException.class })
    public ResponseEntity<Object> handleRecurseNotFoundException(RecurseNotFoundException ex, WebRequest request) {
        return throwException(ex, request, "recurse.not.found", HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> throwException(Exception ex, WebRequest request, String message, HttpStatus status){
        List<ResponseError> erros = Arrays.asList(new ResponseError(
                this.messageSource.getMessage(message, null, LocaleContextHolder.getLocale()),
                ExceptionUtils.getRootCauseMessage(ex)));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), status, request);
    }

    private List<ResponseError> createErros(BindingResult bindingResult) {
        List<ResponseError> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            erros.add(new ResponseError(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()),
                    fieldError.toString()));
        }

        return erros;
    }

}