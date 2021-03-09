package com.sol.solapp.common.exception;

import com.sol.solapp.common.exception.code.ErrorCode;
import lombok.Getter;

@Getter
public abstract class SolException extends RuntimeException{
    protected final ErrorDescription errorDescription;

    public SolException(ErrorCode errorCode) {
        this.errorDescription = new ErrorDescription(errorCode.getCode(), errorCode.getMessage());
    }

    public SolException(ErrorCode errorCode, String invalidParam) {
        this.errorDescription = new ErrorDescription(errorCode.getCode(), errorCode.getMessage(), invalidParam);
    }

}
