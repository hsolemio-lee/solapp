package com.sol.solapp.common.exception;

import com.sol.solapp.common.exception.code.ErrorCode;

public class ServiceException extends SolException {

    public ServiceException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ServiceException(ErrorCode errorCode, String invalidParam) {
        super(errorCode, invalidParam);
    }
}
