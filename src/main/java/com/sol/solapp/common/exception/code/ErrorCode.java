package com.sol.solapp.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PARAMETER_EMPTY("SOL400-001", "Parameter is empty"),
    PARAMETER_INVALID("SOL400-002", "Parameter is invalid"),
    ID_EXIST("SOL400-003", "ID already exists"),
    UNKNOWN_ERROR("SOL500-001", "Unknown error"),
    ;

    private String code;
    private String message;
}
