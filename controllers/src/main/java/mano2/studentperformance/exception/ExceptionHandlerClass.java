package mano2.studentperformance.exception;

import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.exceptions.ExistingLoginException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import java.util.*;
import java.util.stream.Collectors;


@ControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Map<String, Object> handleResourceNotFound(
            final ConstraintViolationException exception
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        List<String> errors = exception.getConstraintViolations()
                .stream()
                .map(x -> x.getMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);
        return body;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Map<String, Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex
    ) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        body.put("errors", errors);
        return body;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse handleResourceNotFound(
            final EntityNotFoundException exception, final HttpServletRequest request
    ) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage("No such entity");
        error.setRequestedURI(request.getRequestURI());
        return error;
    }

    @ExceptionHandler(ExistingLoginException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @ResponseBody
    ExceptionResponse handleExistingLogin(
            final ExistingLoginException exception, final HttpServletRequest request
    ) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage("Such login already exists");
        error.setRequestedURI(request.getRequestURI());
        return error;
    }


    @ExceptionHandler(JDBCConnectionException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse unnecessaryToJDBC(
            final JDBCConnectionException exception, final HttpServletRequest request
    ) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        return error;
    }

//    @ExceptionHandler(NullPointerException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public @ResponseBody
//    ExceptionResponse nullPointerException(
//            final NullPointerException exception, final HttpServletRequest request
//    ) {
//        ExceptionResponse error = new ExceptionResponse();
//        error.setErrorMessage("NullPointerException: " + exception);
//        error.setRequestedURI(request.getRequestURI());
//        return error;
//    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse dataIntegrityViolationException(
            final DataIntegrityViolationException exception, final HttpServletRequest request
    ) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        return error;
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse service(
            final ServiceException exception, final HttpServletRequest request
    ) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        return error;
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public @ResponseBody
//    ExceptionResponse wrongFormat(
//            final HttpMessageNotReadableException exception, final HttpServletRequest request
//    ) {
//        ExceptionResponse error = new ExceptionResponse();
//        error.setErrorMessage(exception.getMessage());
//        error.setRequestedURI(request.getRequestURI());
//        return error;
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse illegalArgument(
            final IllegalArgumentException exception, final HttpServletRequest request
    ) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        return error;
    }
}
