package com.sol.solapp.common.rest;

import com.sol.solapp.common.exception.ErrorDescription;
import com.sol.solapp.common.exception.ServiceException;
import com.sol.solapp.common.exception.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlingController {

    private Map<String, List<Map<String, Object>>> responseFormat(ErrorDescription errorDescription, Exception exception) {
        Map<String, List<Map<String,Object>>> responseMap = new HashMap<>();
        responseMap.put("errors", Collections.singletonList(errorDescription.toMap()));

        return responseMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public Map<String, List<Map<String, Object>>> serviceException(ServiceException ex) {
        ErrorDescription errorDescription = ex.getErrorDescription();
        return responseFormat(errorDescription, ex);
    }

    @ExceptionHandler(InvalidPropertyException.class)
    public Map<String, List<Map<String, Object>>> serviceException(InvalidPropertyException ex) {
        ErrorDescription errorDescription = new ErrorDescription(ErrorCode.PARAMETER_INVALID.getCode(), ErrorCode.PARAMETER_INVALID.getMessage(), ex.getPropertyName());
        return responseFormat(errorDescription, ex);
    }

    @ExceptionHandler(Exception.class)
    public Map<String, List<Map<String, Object>>> unknownException(Exception ex) {
        ErrorDescription errorDescription = new ErrorDescription(ErrorCode.UNKNOWN_ERROR.getCode(), ex.getMessage());
        return responseFormat(errorDescription, ex);
    }

}