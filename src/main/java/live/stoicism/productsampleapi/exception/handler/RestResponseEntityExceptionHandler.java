package live.stoicism.productsampleapi.exception.handler;

import live.stoicism.productsampleapi.exception.DatabaseInvalidException;
import live.stoicism.productsampleapi.model.ResponseContent;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public RestResponseEntityExceptionHandler() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {
        logger.error("400 Status Code: MethodArgumentNotValidException", ex);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return handleExceptionInternal(ex, ResponseContent.fail().message("See error detail above").data(errors),
                headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status,
                                                                          WebRequest request) {
        logger.error("400 Status Code: MissingServletRequestParameterException", ex);
        String error = ex.getParameterName() + " is mandatory";
        return handleExceptionInternal(ex, ResponseContent.fail().message(error).build(),
                headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseContent<?>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String error = ex.getName() + " should be of type " + Objects.requireNonNull(ex.getRequiredType()).getName();
        logger.error("400 Status Code: MethodArgumentTypeMismatchException, " + error, ex);
        return ResponseEntity.badRequest().body(ResponseContent.error().message(error).build());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ResponseContent<?>> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        logger.error("400 Status Code: EmptyResultDataAccessException", ex);
        return ResponseEntity.badRequest().body(ResponseContent.error().message("Cannot find entity").build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        logger.error("400 Status Code: HttpMessageNotReadableException", ex);
        String trimmedMessage = ex.getMessage() != null ? ex.getMessage().substring(0, 50) : "";
        ResponseContent<Object> apiResponse = ResponseContent.fail().message("Input wrong format: " + trimmedMessage).build();
        return handleExceptionInternal(ex, apiResponse, headers, status, request);
    }

    @ExceptionHandler(DatabaseInvalidException.class)
    public ResponseEntity<ResponseContent<?>> handleDatabaseInvalidException(DatabaseInvalidException ex) {
        logger.error("400 Status Code: DatabaseInvalidException", ex);
        return ResponseEntity.badRequest().body(ResponseContent.error().message(ex.getMessage()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseContent<?>> handleGeneralException(Exception ex) {
        logger.error("500 Status Code: Exception", ex);
        return ResponseEntity.internalServerError().body(ResponseContent.error().message(ex.getMessage()).build());
    }
}
