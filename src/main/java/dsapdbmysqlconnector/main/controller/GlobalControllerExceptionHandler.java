package dsapdbmysqlconnector.main.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import dsapdbmysqlconnector.main.utility.Keys;
import org.apache.log4j.Logger;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(GlobalControllerExceptionHandler.class);


    /**
     * Exception to be thrown when validation on an argument annotated with @Valid fails.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException errors) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(errors.getMessage(), errors);
        } else {
            LOGGER.error(errors.getMessage());
        }

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, "There are validation issues, please provide valid inputs");
        errorMap.put(Keys.USER_MESSAGE, "Please provide valid inputs");
        errorMap.put(Keys.MORE_INFO, errors.getMessage());
        errorMap.put(Keys.ERROR_CODE, HttpStatus.BAD_REQUEST);
        Map<String, List<Map<String, String>>> fieldErrors = new HashMap<>();
        for (FieldError error : errors.getBindingResult().getFieldErrors()) {
            errorMap.put(error.getField(), error.getDefaultMessage());

            // Add this error to the list of errors for this field
            List<Map<String, String>> errorsForField = fieldErrors.get(error.getField());
            if (errorsForField == null) {
                errorsForField = new ArrayList<>();
            }

            Map<String, String> fieldError = new HashMap<>();
            fieldError.put("code", error.getCode());
            fieldError.put("message", error.getDefaultMessage());
            errorsForField.add(fieldError);

            fieldErrors.put(error.getField(), errorsForField);
        }
        errorMap.put("fieldErrors", fieldErrors);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.BAD_REQUEST);
    }


  //  @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception error) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(error.getMessage(), error);
        } else {
            LOGGER.error(error.getMessage());
        }

        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, error.getMessage());
        errorMap.put(Keys.USER_MESSAGE, error.getMessage());
        errorMap.put(Keys.MORE_INFO, error.getStackTrace()[0]);
        errorMap.put(Keys.ERROR_CODE, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException error) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(error.getMessage(), error);
        } else {
            LOGGER.error(error.getMessage());
        }

        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, error.getMessage());
        errorMap.put(Keys.USER_MESSAGE, error.getMessage());
        errorMap.put(Keys.MORE_INFO, error.getStackTrace()[0]);
        errorMap.put(Keys.ERROR_CODE, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalStateException(IllegalStateException error) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(error.getMessage(), error);
        } else {
            LOGGER.error(error.getMessage());
        }

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, error.getMessage());
        errorMap.put(Keys.USER_MESSAGE, "");
        errorMap.put(Keys.MORE_INFO, error.getStackTrace()[0]);
        errorMap.put(Keys.ERROR_CODE, HttpStatus.CONFLICT);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.CONFLICT);
    }

    //Service layer Exceptions

    /**
     * Exception thrown when a request handler does not support a specific request method.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException error) {

        String errorMessage = error.getMethod() + " supports only " + error.getSupportedHttpMethods()
                + ", change the request type to " + error.getSupportedHttpMethods();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(errorMessage, error);
        } else {
            LOGGER.error(errorMessage);
        }

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, errorMessage);
        errorMap.put(Keys.USER_MESSAGE, "");
        errorMap.put(Keys.MORE_INFO, error.getMessage());
        errorMap.put(Keys.ERROR_CODE, HttpStatus.METHOD_NOT_ALLOWED);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Exception thrown when a client POSTs, PUTs, or PATCHes content of a type not supported   by
     * request handler.
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException error) {

        String errorMessage = "the requested content type [" + error.getContentType() + "] is not supported";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(errorMessage, error);
        } else {
            LOGGER.error(errorMessage);
        }

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, errorMessage);
        errorMap.put(Keys.USER_MESSAGE, "");
        errorMap.put(Keys.MORE_INFO, error.getMessage());
        errorMap.put(Keys.ERROR_CODE, HttpStatus.UNSUPPORTED_MEDIA_TYPE);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * indicates a missing parameter.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, Object>> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException error) {

        String errorMessage = "the request parameter ["
                + error.getParameterName() + "] of type [" + error.getParameterType() + "] is missing";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(errorMessage, error);
        } else {
            LOGGER.error(errorMessage);
        }

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, errorMessage);
        errorMap.put(Keys.USER_MESSAGE, "please provide all the values");
        errorMap.put(Keys.MORE_INFO, error.getMessage());
        errorMap.put(Keys.ERROR_CODE, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception thrown when no suitable editor or converter can be found for a bean property.
     */
    @ExceptionHandler(ConversionNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleConversionNotSupportedException(
            ConversionNotSupportedException error) {

        String errorMessage = error.getPropertyName() + "with value " + error.getValue()
                + "is not compatable to" + error.getRequiredType();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(errorMessage, error);
        } else {
            LOGGER.error(errorMessage);
        }

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put("errorMessage", errorMessage);
        errorMap.put(Keys.USER_MESSAGE, "Please check the input with value " + error.getValue());
        errorMap.put(Keys.MORE_INFO, error.getMessage());
        errorMap.put(Keys.ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Thrown to indicate that the application has attempted to convert a string to one of the numeric
     * types, but that the string does not have the appropriate format.
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Map<String, Object>> handleNumberFormatException(
            NumberFormatException error) {

        LOGGER.error(error.getMessage(), error);

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, error.getMessage());
        errorMap.put(Keys.USER_MESSAGE, "");
        errorMap.put(Keys.MORE_INFO, error.getStackTrace()[0]);
        errorMap.put(Keys.ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception thrown on a type mismatch when trying to set a bean property.
     */
    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatchException(TypeMismatchException error) {

        String errorMessage = "Value '" + error.getValue() + "' is not compatable with " + error.getRequiredType();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(errorMessage, error);
        } else {
            LOGGER.error(errorMessage);
        }

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, errorMessage);
        errorMap.put(Keys.USER_MESSAGE, "Please check the input value '" + error.getValue() + "'");
        errorMap.put(Keys.MORE_INFO, error.getMessage());
        errorMap.put(Keys.ERROR_CODE, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.BAD_REQUEST);
    }

    /**
     * Specialized sub-class of JsonMappingException that is used when the underlying problem appears
     * to be that of bad formatting of a value to deserialize.
     */
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidFormatException(InvalidFormatException error) {

        String errorMessage = error.getValue() + " cannot be converted to " + error.getTargetType() +
                "change '" + error.getValue() + "' To match target type" + error.getTargetType();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(errorMessage, error);
        } else {
            LOGGER.error(errorMessage);
        }

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, errorMessage);
        errorMap.put(Keys.USER_MESSAGE, "Please enter a correct value in place of '" + error.getValue() + "'");
        errorMap.put(Keys.MORE_INFO, error.getMessage());
        errorMap.put(Keys.ERROR_CODE, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.BAD_REQUEST);
    }


    /**
     * Thrown when an application attempts to use null in a case where an object is required. These
     * include: Calling the instance method of a null object. Accessing or modifying the field of a
     * null object. Taking the length of null as if it were an array. Accessing or modifying the slots
     * of null as if it were an array. Throwing null as if it were a Throwable value.
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, Object>> handleNullPointerException(NullPointerException error) {

        LOGGER.error(error.getMessage(), error);

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, "Null Pointer Exception: " + error.getStackTrace()[0]);
        errorMap.put(Keys.USER_MESSAGE, "");
        errorMap.put(Keys.MORE_INFO, error.getStackTrace()[0]);
        errorMap.put(Keys.ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Thrown by HttpMessageConverter implementations when the read method fails..
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException error) {

        String errorMessage = "Error parsing Json request to corresponding object. Error: " + error.getMessage();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(errorMessage, error);
        } else {
            LOGGER.error(errorMessage);
        }

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, "Error parsing  Json request to corresponding object."
                + " please form the correct JSON String ");
        errorMap.put(Keys.USER_MESSAGE, "");
        errorMap.put(Keys.MORE_INFO, error.getMessage());
        errorMap.put(Keys.ERROR_CODE, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.BAD_REQUEST);
    }

    /**
     * Thrown by HttpMessageConverter implementations when the write method fails.
     */
    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotWritableException(HttpMessageNotWritableException error) {

        String errorMessage = "Error parsing Object to JSON response";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(error.getMessage(), error);
        } else {
            LOGGER.error(errorMessage + ". Error: " + error.getMessage());
        }

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, errorMessage);
        errorMap.put(Keys.USER_MESSAGE, "");
        errorMap.put(Keys.MORE_INFO, error.getMessage());
        errorMap.put(Keys.ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Signals that an error has been reached unexpectedly while parsing.
     */
    @ExceptionHandler(ParseException.class)
    public ResponseEntity<Map<String, Object>> handleParseException(ParseException error) {

        String errorMessage = error.getMessage() + "at position " + error.getErrorOffset();

        LOGGER.error(errorMessage, error);

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, errorMessage);
        errorMap.put(Keys.USER_MESSAGE, error.getMessage());
        errorMap.put(Keys.MORE_INFO, error.getMessage());
        errorMap.put(Keys.ERROR_CODE, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String, Object>> handleIOException(IOException ex) {

        LOGGER.error(ex.getMessage(), ex);

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put(Keys.HAS_ERROR, "true");
        errorMap.put(Keys.DEVELOPER_MESSAGE, "An IOException occurred while performing the request.");
        errorMap.put(Keys.USER_MESSAGE, "An error occurred while reading or writing a file or data. Please try again later.");
        errorMap.put(Keys.MORE_INFO, ex.getMessage());
        errorMap.put(Keys.ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
