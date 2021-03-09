package com.sol.solapp.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PARAMETER_EMPTY("SOL400-001", "Parameter is empty"),
    PARAMETER_INVALID("SOL400-002", "Parameter is invalid"),
    ID_EXIST("SOL400-003", "ID already exists"),
    INTERNAL_SERVICE_EXCEPTION("SOL500-001", "Internal service exception."),
    UNKNOWN_ERROR("SOL500-002", "Unknown error"),
    INVALID_CSV_FILE("SOL500-003", "Invalid CSV file"),
    ;

    private String code;
    private String message;
}
